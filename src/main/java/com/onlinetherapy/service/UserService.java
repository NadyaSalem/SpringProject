package com.onlinetherapy.service;

import com.onlinetherapy.models.dto.bindingModels.requests.MakeRequestDTO;
import com.onlinetherapy.models.dto.bindingModels.messages.MessageDTO;
import com.onlinetherapy.models.dto.viewModels.requests.CompleteRequestsIdView;
import com.onlinetherapy.models.dto.viewModels.requests.RequestDetailView;
import com.onlinetherapy.models.dto.viewModels.requests.UserRequestsView;
import com.onlinetherapy.models.dto.viewModels.appointments.AppointmentViewInCart;
import com.onlinetherapy.models.dto.viewModels.users.AllUsersView;
import com.onlinetherapy.models.entity.*;
import com.onlinetherapy.models.enums.RequestStatusEnum;
import com.onlinetherapy.models.enums.UserRoleEnum;
import com.onlinetherapy.repository.MessageRepository;
import com.onlinetherapy.repository.RequestRepository;
import com.onlinetherapy.repository.UserRepository;
import com.onlinetherapy.repository.UserRoleRepository;
import com.onlinetherapy.service.AppointmentService;
import com.onlinetherapy.service.ChoseAppointmentsService;
import com.onlinetherapy.service.PurchasedAppointmentsService;
import com.onlinetherapy.service.RequestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RequestRepository requestRepository;
    private final MessageRepository messageRepository;
    private final RequestService requestService;
    private final AppointmentService appointmentService;
    private final ChoseAppointmentsService choseAppointmentsService;
    private final PurchasedAppointmentsService purchasedAppointmentsService;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserService(ModelMapper modelMapper, UserRepository userRepository,
                       AppointmentService appointmentService,
                       RequestRepository requestRepository,
                       MessageRepository messageRepository,
                       RequestService requestService,
                       ChoseAppointmentsService choseAppointmentsService,
                       PurchasedAppointmentsService purchasedAppointmentsService,
                       UserRoleRepository userRoleRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.requestRepository = requestRepository;
        this.appointmentService = appointmentService;
        this.messageRepository = messageRepository;
        this.requestService = requestService;
        this.choseAppointmentsService = choseAppointmentsService;
        this.purchasedAppointmentsService = purchasedAppointmentsService;
        this.userRoleRepository = userRoleRepository;
    }

    public void addAppointmentToChoseList(Long id, Principal principal) {
        User user = getUserByPrincipal(principal);
        Appointment appointment = this.appointmentService.getAppointmentById(id);
        ChoseAppointments choseAppointment = mapAppointmentToChoseAppointment(appointment);


        if (user.getChoseAppointments().stream().anyMatch(a -> a.getImg().equals(appointment.getImg()))) {
            choseAppointment = user.findByImg(appointment.getImg());
            choseAppointment.setCount(choseAppointment.getCount() + 1);
        } else {
            user.getChoseAppointments().add(choseAppointment);
            choseAppointment.setCount(1);
        }
        choseAppointment.setAppointmentSum(choseAppointment.getPrice().multiply(BigDecimal.valueOf(choseAppointment.getCount())));
        this.choseAppointmentsService.save(choseAppointment);
        this.userRepository.save(user);
    }

    public void removeAppointmentFromChoseList(Long appointmentId, Principal username) {
        User user = getUserByPrincipal(username);
        user.removeAppointmentFromChoseList(appointmentId);
        this.choseAppointmentsService.deleteById(appointmentId);
        this.userRepository.save(user);
    }

    public Long requestAppointments(MakeRequestDTO makeRequestDTO, Principal username) {
        User client = getUserByPrincipal(username);
        Request request = new Request();

        List<PurchasedAppointments> appointmentsToAddInDB = client.getChoseAppointments().stream()
                .map(this::mapAppointmentToPurchaseAppointment).toList();

        List<PurchasedAppointments> appointmentsWithId = this.purchasedAppointmentsService.addAppointments(appointmentsToAddInDB);

        request.getRequestAppointments().addAll(appointmentsToAddInDB);
        request.setDateRequested(LocalDate.now());
        request.setClient(client);
        request.setRequestStatusEnum(RequestStatusEnum.OPEN);
        request.setRequestSum(sumForAllPurchaseAppointment(username));
        this.requestRepository.save(request);

        client.setAddress(makeRequestDTO.getAddress());
        client.setPhoneNumber(makeRequestDTO.getPhoneNumber());

        for (PurchasedAppointments appointment : appointmentsToAddInDB) {
            client.addAppointmentToPurchaseList(appointment);
        }
        client.getChoseAppointments().clear();
        client.getRequests().add(request);

        this.userRepository.save(client);
        this.choseAppointmentsService.deleteAll();
        return request.getId();
    }

    public Set<AppointmentViewInCart> getChoseListByUserToViewInShoppingCard(Principal principal) {
        User user = getUserByPrincipal(principal);

        return user.getChoseAppointments().stream()
                .map(a -> modelMapper.map(a, AppointmentViewInCart.class)).collect(Collectors.toSet());
    }

    public Integer countOfItemInCart(Principal principal) {
        return getChoseListByUserToViewInShoppingCard(principal).stream()
                .mapToInt(AppointmentViewInCart::getCount).sum();
    }

    public BigDecimal sumForAllPurchaseAppointment(Principal principal) {
        return getChoseListByUserToViewInShoppingCard(principal).stream()
                .map(AppointmentViewInCart::getAppointmentsSum)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public User getUserByPrincipal(Principal principal) {
        return this.userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new Error("User not found!"));
    }

    public PurchasedAppointments mapAppointmentToPurchaseAppointment(ChoseAppointments choseAppointment) {
        PurchasedAppointments purchasedAppointment = new PurchasedAppointments();
        purchasedAppointment.setName(choseAppointment.getName());
        purchasedAppointment.setPrice(choseAppointment.getPrice());
        purchasedAppointment.setImg(choseAppointment.getImg());
        purchasedAppointment.setCount(choseAppointment.getCount());
        return purchasedAppointment;
    }

    public ChoseAppointments mapAppointmentToChoseAppointment(Appointment appointment) {
        ChoseAppointments choseAppointments = new ChoseAppointments();
        choseAppointments.setName(appointment.getName());
        choseAppointments.setPrice(appointment.getPrice());
        choseAppointments.setImg(appointment.getImg());
        return choseAppointments;
    }

    public RequestDetailView getRequestDetailsById(Principal principal, Long requestId) {
        User client = getUserByPrincipal(principal);
        RequestDetailView order = this.modelMapper.map(client.findRequestById(requestId), RequestDetailView.class);
        order.setClientFirstName(client.getFirstName());
        order.setClientFullName(client.getUserFullName(client.getId()));
        order.setClientAddress(client.getAddress());
        return order;
    }

    public List<UserRequestsView> getAllRequests(Principal principal) {
        User client = getUserByPrincipal(principal);
        return client.getRequests().stream().map(r -> modelMapper.map(r, UserRequestsView.class)).toList()
                .stream().sorted((r2, r1) -> r1.getRequestStatus().compareTo(r2.getRequestStatus()))
                .collect(Collectors.toList());
    }

    public List<CompleteRequestsIdView> getCompletedRequestsWithoutMessage(Principal principal) {
        return getAllRequests(principal)
                .stream()
                .filter(r -> r.getRequestStatus().equals(RequestStatusEnum.COMPLETED))
                .filter(r -> r.getMessage() == null)
                .map(r -> modelMapper.map(r, CompleteRequestsIdView.class)).toList();
    }

    public void addMessage(MessageDTO messageDTO, Principal principal) {
        User client = getUserByPrincipal(principal);
        Request request = this.requestService.findById(Long.valueOf(messageDTO.getRequestId()));

        Message message = new Message();
        message.setAuthor(client);
        message.setDescription(messageDTO.getDescription());
        this.messageRepository.save(message);

        client.getMessages().add(message);
        this.userRepository.save(client);

        request.setMessage(message);
        this.requestRepository.save(request);
    }

    public List<AllUsersView> getAllUsers() {
        return this.userRepository.findAll()
                .stream()
                .filter(user -> user.getId() > 1)
                .map(u -> modelMapper.map(u, AllUsersView.class))
                .toList();
    }

    public void changeUserRole(Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new Error("User not found!"));
        UserRoleEnum userRole = user.getUserRoles().stream().findFirst().get().getUserRole();
        if (userRole.equals(UserRoleEnum.ADMIN)) {
            user.getUserRoles().clear();
            user.setUserRoles(userRoleRepository.findByUserRole(UserRoleEnum.CLIENT));
        } else {
            user.getUserRoles().clear();
            user.setUserRoles(userRoleRepository.findByUserRole(UserRoleEnum.ADMIN));
        }
        this.userRepository.save(user);
    }

    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username).get();
    }

    public void save(User user) {
        this.userRepository.save(user);
    }
}

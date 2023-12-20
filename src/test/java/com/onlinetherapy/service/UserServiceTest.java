package com.onlinetherapy.service;

import com.onlinetherapy.models.dto.bindingModels.messages.MessageDTO;
import com.onlinetherapy.models.dto.viewModels.requests.RequestDetailView;
import com.onlinetherapy.models.dto.viewModels.users.AllUsersView;
import com.onlinetherapy.models.entity.*;
import com.onlinetherapy.models.enums.UserRoleEnum;
import com.onlinetherapy.repository.MessageRepository;
import com.onlinetherapy.repository.RequestRepository;
import com.onlinetherapy.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private final String NEW_USERNAME = "plamena";
    private final String RAW_PASSWORD = "plamena";
    private final String ENCODED_PASSWORD = "%($)GGPPP3fdfd";

    private final Long VALID_ID = 1L;
    private final String FIRST_NAME = "Plamena";
    private final String LAST_NAME = "Plamenova";
    private final String EMAIL = "plamena@example.com";

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private MessageRepository mockMessageRepository;
    @Mock
    private RequestRepository mockRequestRepository;
    @Mock
    private ModelMapper mockMapper;
    @Mock
    private RequestService mockRequestService;
    @Mock
    private AppointmentService mockAppointmentService;
    @Mock
    private PasswordEncoder mockPasswordEncoder;
    @Mock
    private User user;
    @Mock
    Principal principal;
    @Mock
    Message message;
    @Spy
    @InjectMocks
    private UserService toTest;

    @Captor
    private ArgumentCaptor<Message> messageCaptor;
    @Captor
    private ArgumentCaptor<User> userCaptor;
    @Captor
    private ArgumentCaptor<Request> orderCaptor;

    private AllUsersView allUsersView;

    private Request request;
    private Appointment appointment;
    private ChoseAppointments choseProduct;
    private PurchasedAppointments purchasedAppointments;
    private MessageDTO messageDTO;
    private RequestDetailView requestDetailView = new RequestDetailView();
    private List<Message> messages = new ArrayList<> ();
    private List<ChoseAppointments> chosechoseAppointments = new ArrayList<> ();
    private List<Request> requests = new ArrayList<> ();

    private Role testRole;

    @BeforeEach
    void setUp() {
        testRole = new Role ();
        testRole.setUserRole (UserRoleEnum.CLIENT);

        messages.add (message);
        chosechoseAppointments.add (choseProduct);

        user = User.builder ()
                .username (NEW_USERNAME)
                .password (mockPasswordEncoder.encode (RAW_PASSWORD))
                .firstName (FIRST_NAME)
                .lastName (LAST_NAME)
                .email (EMAIL)
                .messages (messages)
                .requests(requests)
                .choseAppointments(chosechoseAppointments)
                .build ();
        appointment = Appointment.builder ()
                .name ("test appointment")
                .price (BigDecimal.valueOf (35))
                .description ("Test appointment description")
                .build ();
        appointment.setId (VALID_ID);
        choseProduct = ChoseAppointments.builder ()
                .name ("test appointment")
                .price (BigDecimal.valueOf (35))
                .count(1)
                .appointmentSum (BigDecimal.valueOf (35))
                .build ();
        choseProduct.setId (VALID_ID);

        purchasedAppointments = PurchasedAppointments.builder ()
                .name ("test appointment")
                .price (BigDecimal.valueOf (35))
                .count(1)
                .appointmentSum(BigDecimal.valueOf (35))
                .build ();
        request = Request.builder ()
                .client (user)
                .RequestSum(BigDecimal.valueOf (35))
                .dateRequested(LocalDate.now ())
                .build ();
        request.setId (VALID_ID.longValue ());
        requests.add (request);
        requestDetailView = RequestDetailView.builder ()
                .id (VALID_ID)
                .clientAddress ("Varna")
                .clientFirstName (FIRST_NAME)
                .clientFullName (FIRST_NAME + " " + LAST_NAME)
                .requestSum (BigDecimal.valueOf (35))
                .build ();

        allUsersView = AllUsersView.builder ()
                .id (VALID_ID)
                .username (NEW_USERNAME)
                .email (EMAIL)
                .roles (List.of (testRole))
                .build ();
        message = Message.builder ()
                .author (user)
                .description ("Test Message Test Message Test Message")
                .build ();
        messageDTO = MessageDTO.builder ()
                .requestId (1)
                .description ("Test Message Test Message Test Message")
                .build ();

        lenient ().when (mockUserRepository.findByUsername (NEW_USERNAME)).thenReturn (Optional.of (user));
        Mockito.<Optional<User>>when (mockUserRepository.findByUsername (NEW_USERNAME)).thenReturn (Optional.of (user));
        lenient ().when (principal.getName ()).thenReturn (NEW_USERNAME);
        lenient ().when ((toTest.getUserByPrincipal (principal))).thenReturn (user);
    }

    @Test
    @WithMockUser(username = "client", roles = {"CLIENT"})
    void testAddMessage() {
        when (mockRequestService.findById (VALID_ID)).thenReturn (request);
        toTest.addMessage (messageDTO, principal);

        verify (mockMessageRepository, times (1)).save (any (Message.class));
        verify (mockUserRepository, times (1)).save (userCaptor.capture ());
        verify (mockRequestRepository, times (1)).save (orderCaptor.capture ());
    }

    @Test
    void testGetAllUsersFromRepo() {
        lenient ().when (mockUserRepository.findAll ()).thenReturn (List.of (user));
        lenient ().when (mockMapper.map (user, AllUsersView.class)).thenReturn (allUsersView);

        AllUsersView allUsersView1 = toTest.getAllUsers ().get (0);

        Assertions.assertEquals (allUsersView1.getUsername (), (allUsersView.getUsername ()));

        Assertions.assertEquals (user.getUsername (), allUsersView.getUsername ());
    }

    @Test
    void testGetOrderDetailsById() {
        lenient ().when (mockMapper.map (request, RequestDetailView.class)).thenReturn (requestDetailView);
        requestDetailView.setClientAddress ("гр.Варна ул.Тестова №3");
        requestDetailView.setClientFirstName (FIRST_NAME + " " + LAST_NAME);
        requestDetailView.setRequestSum (BigDecimal.valueOf (75));
        requestDetailView.setClientFullName (FIRST_NAME);
    }

    @Test
    void testMapProductToChoseProduct() {
        lenient ().when (toTest.mapAppointmentToChoseAppointment(appointment)).thenReturn (choseProduct);
        Assertions.assertEquals (appointment.getName (), choseProduct.getName ());
        Assertions.assertEquals (appointment.getImg (), choseProduct.getImg ());
    }

    @Test
    void testMapProductToPurchaseProduct() {
        lenient ().when (toTest.mapAppointmentToPurchaseAppointment(choseProduct)).thenReturn (purchasedAppointments);
        Assertions.assertEquals (choseProduct.getName (), purchasedAppointments.getName ());
        Assertions.assertEquals (choseProduct.getImg (), purchasedAppointments.getImg ());
    }

    @Test
    void testOrderDetailView() {
        lenient ().when (mockMapper.map (request, RequestDetailView.class)).thenReturn (requestDetailView);
        lenient ().when (toTest.getRequestDetailsById(principal, VALID_ID)).thenReturn (requestDetailView);
        Assertions.assertEquals (request.getRequestSum(), requestDetailView.getRequestSum ());
    }
}

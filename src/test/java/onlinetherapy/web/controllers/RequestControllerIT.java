package onlinetherapy.web.controllers;

import com.onlinetherapy.models.dto.bindingModels.requests.MakeRequestDTO;
import com.onlinetherapy.models.dto.viewModels.appointments.AppointmentViewInCart;
import com.onlinetherapy.models.entity.*;
import com.onlinetherapy.models.enums.TherapistTypeEnum;
import com.onlinetherapy.repository.PurchaseAppointmentsRepository;
import com.onlinetherapy.repository.UserRepository;
import com.onlinetherapy.service.PurchasedAppointmentsService;
import com.onlinetherapy.service.RequestService;
import com.onlinetherapy.service.UserService;
import org.apache.el.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.lenient;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RequestControllerIT {
    private final String NEW_USERNAME = "test";

    @Mock
    Principal principal;
    @Mock
    private ModelMapper mockMapper;
    @Mock
    private Stream mockReducer;
    @MockBean
    private RequestService requestService;
    @Mock
    private PurchasedAppointmentsService purchasedAppointmentsService;
    @Mock
    private PurchaseAppointmentsRepository purchasedAppointmentsRepository;
    @Mock
    private UserRepository userRepository;
    @Spy
    @InjectMocks
    private UserService toTest;
    @Autowired
    private MockMvc mockMvc;
    private MakeRequestDTO makeRequestDTO;
    private ChoseAppointments choseAppointment;
    private AppointmentViewInCart appointmentViewInCart;
    private PurchasedAppointments purchasedAppointment;
    private Therapist therapist;
    private Appointment appointment;
    private User client;
    List<ChoseAppointments> choseAppointments = new ArrayList<> ();
    List<AppointmentViewInCart> appointmentsViewInCart = new ArrayList<> ();

    @BeforeEach
    void setUp() {
        therapist = Therapist.builder ()
                .name (TherapistTypeEnum.INDIVIDUAL_COUNSELOR)
                .build ();
        makeRequestDTO = MakeRequestDTO.builder ()
                .address ("Test test")
                .phoneNumber ("0899988888")
                .build ();
        client = User.builder ()
                .username (NEW_USERNAME)
                .password ("test")
                .email ("test@test.test")
                .firstName ("Test")
                .lastName ("Test")
                .choseAppointments(choseAppointments)
                .build ();
        appointment = Appointment.builder ()
                .name ("chose appointment name")
                .price (BigDecimal.valueOf (35))
                .description ("Test description Test description")
                .therapist(therapist)
                .build ();
        choseAppointment = ChoseAppointments.builder ()
                .name ("chose appointment name")
                .img ("img")
                .count(1)
                .appointmentSum (BigDecimal.valueOf (35))
                .price (BigDecimal.valueOf (35))
                .build ();
        choseAppointments.add (choseAppointment);
        appointmentViewInCart = AppointmentViewInCart.builder ()
                .name ("chose appointment name")
                .img ("img")
                .count(1)
                .appointmentsSum(BigDecimal.valueOf (35))
                .price (BigDecimal.valueOf (35))
                .build ();
        appointmentsViewInCart.add (appointmentViewInCart);
        purchasedAppointment = PurchasedAppointments.builder ()
                .name ("chose appointment name")
                .img ("img")
                .count(1)
                .appointmentSum(BigDecimal.valueOf (35))
                .price (BigDecimal.valueOf (35))
                .build ();
        userRepository.save (client);

        lenient ().when (userRepository.findByUsername (NEW_USERNAME)).thenReturn (Optional.of (client));
        Mockito.<Optional<User>>when (userRepository.findByUsername (NEW_USERNAME)).thenReturn (Optional.of (client));
        lenient ().when (principal.getName ()).thenReturn (NEW_USERNAME);
        lenient ().when ((toTest.getUserByPrincipal (principal))).thenReturn (client);
    }

    @Test
    @WithMockUser(username = "client", roles = {"CLIENT"})
    void testCart() throws Exception {
        mockMvc.perform (get ("/cart"))
                .andExpect (status ().is2xxSuccessful ())
                .andExpect (model ().attributeExists ("cartCashAppointment"))
                .andExpect (model ().attributeExists ("count"))
                .andExpect (model ().attributeExists ("sumForAllAppointments"))
                .andExpect (view ().name ("cart"));
    }

    @Test
    @WithMockUser(username = "client", roles = {"CLIENT"})
    void testOrderByClientId() throws Exception {
        mockMvc.perform (get ("/requests"))
                .andExpect (status ().is2xxSuccessful ())
                .andExpect (model ().attributeExists ("clientRequests"))
                .andExpect (model ().attributeExists ("completedRequests"))
                .andExpect (view ().name ("requests"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testCloseOrderById() throws Exception {
        mockMvc.perform (get ("/request/close/1"))
                .andExpect (status ()
                        .is3xxRedirection ())
                .andExpect (redirectedUrl ("/user/admin"));
    }
}

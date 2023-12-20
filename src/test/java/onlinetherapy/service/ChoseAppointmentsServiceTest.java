package onlinetherapy.service;

import com.example.onlinetherapy.models.entity.ChoseAppointments;
import com.example.onlinetherapy.repository.ChoseAppointmentsRepository;
import com.example.onlinetherapy.service.ChoseAppointmentsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class ChoseAppointmentsServiceTest {
    @Mock
    private ChoseAppointmentsRepository choseAppointmentsRepository;
    private ChoseAppointmentsService toTest;
    private ChoseAppointments choseProduct;

    @Captor
    private ArgumentCaptor<ChoseAppointments> choseProductCaptor;

    @BeforeEach
    void setUp(){
        toTest = new ChoseAppointmentsService(choseAppointmentsRepository);

        choseProduct = new ChoseAppointments();
        choseProduct.setName("test product");
        choseProduct.setPrice(BigDecimal.valueOf(35));
        choseProduct.setCount(1);
    }

    @Test
    void testSave(){
        toTest.save(choseProduct);

        verify(choseAppointmentsRepository, times(1))
                .save(choseProductCaptor.capture());
        ChoseAppointments argument = choseProductCaptor.getValue();
        Assertions.assertEquals(choseProduct.getCount(), argument.getCount());
        Assertions.assertEquals(choseProduct.getName(), argument.getName());
        Assertions.assertEquals(choseProduct.getPrice(), argument.getPrice());
    }

    @Test
    void testDeleteById(){
        toTest.deleteById(1L);
        verify(choseAppointmentsRepository, times(1))
                .deleteById(1L);
    }

    @Test
    void testDeleteAll(){
        toTest.deleteAll();
        verify(choseAppointmentsRepository, times(1))
                .deleteAll();
    }

    @Test
    void testGetByImg(){
        toTest.getByImg("img");
        verify(choseAppointmentsRepository, times(1))
                .findByImg("img");
    }

}

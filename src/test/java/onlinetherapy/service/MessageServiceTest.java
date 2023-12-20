package onlinetherapy.service;

import com.example.myknitshop.models.dto.viewModels.messages.MessagesView;
import com.example.myknitshop.models.entity.Message;
import com.example.myknitshop.models.entity.User;
import com.example.myknitshop.repository.MessageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;
    @Mock
    private ModelMapper mockMapper;
    private MessageService toTest;
    private Message message;

    @BeforeEach
    void setUp() {
        User user = User.builder()
                .firstName("Petar")
                .lastName("Petrov")
                .build();
        toTest = new MessageService(
                messageRepository, mockMapper);

        message = new Message();
        message.setAuthor(user);
        message.setDescription("Test comment to view on home page");
    }

    @Test
    public void getAllMessagesViewOnHomePageTest() {

        lenient().when(messageRepository.findAll()).thenReturn(List.of(message));

        MessagesView testMessages = toTest.getAllMessagesViewOnHomePage().get(0);
        Assertions.assertEquals(testMessages.getAuthorFullName(), "Petar Petrov");
        Assertions.assertEquals(testMessages.getDescription(), message.getDescription());
    }
}

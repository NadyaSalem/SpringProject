package onlinetherapy.web.rest;

import com.example.myknitshop.models.dto.viewModels.messages.MessagesView;
import com.example.myknitshop.models.entity.Message;
import com.example.myknitshop.models.entity.User;
import com.example.myknitshop.repository.MessageRepository;
import com.example.myknitshop.repository.UserRepository;
import com.example.myknitshop.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class MessageRestControllerIT {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MessageService messageService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private MessageRepository messageRepository;

    @Test
    void testGetComments() throws Exception {
        when(messageService.getAllMessagesViewOnHomePage()).thenReturn(List.of(createComment()));

        mockMvc.perform(get("/api/comments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].authorFullName", is("Test Test")))
                .andExpect(jsonPath("$.[0].description", is("Test message Test message")));
    }

    private MessagesView createComment(){
        User author = User.builder()
                .username("test")
                .password("test")
                .email("test@test.test")
                .firstName("Test")
                .lastName("Test")
                .build();
        return MessagesView.builder()
                .description("Test message Test message")
                .authorFullName(author.getFirstName() + " " + author.getLastName())
                .build();
    }

}

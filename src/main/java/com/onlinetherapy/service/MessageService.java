package com.onlinetherapy.service;

import com.onlinetherapy.models.dto.viewModels.messages.MessagesView;
import com.onlinetherapy.models.entity.Message;
import com.onlinetherapy.repository.MessageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MessageService(MessageRepository messageRepository,
                          ModelMapper modelMapper) {
        this.messageRepository = messageRepository;
        this.modelMapper = modelMapper;
    }

    public List<MessagesView> getAllMessagesViewOnHomePage() {
        return  this.messageRepository.findAll().stream()
                .map(this::mapMessageDTO)
                .collect(Collectors.toList());
    }

    private MessagesView mapMessageDTO(Message message) {
        MessagesView messageDTO = new MessagesView ();
        messageDTO.setDescription(message.getDescription());
        messageDTO.setAuthorFullName ((message.getAuthor().getFirstName ()) + " " + (message.getAuthor().getLastName ()));
        return messageDTO;
    }

    public List<MessagesView> getAllMessages() {
        return this.messageRepository.findAll ()
                .stream ()
                .map (m -> {return modelMapper.map (m, MessagesView.class);
                }).toList ();
    }

    public void deleteMessageById(Long messageId) {
        this.messageRepository.deleteById (messageId);
    }

}
package com.example.voiture.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.voiture.entity.Conversation;
import com.example.voiture.entity.Message;
import com.example.voiture.repository.MessageRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message insererMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> listMessagesByConversation(Conversation conversation) {
        return messageRepository.findByConversation(conversation);
    }


}

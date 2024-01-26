package com.example.voiture.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.voiture.entity.Conversation;
import com.example.voiture.entity.Message;
import com.example.voiture.entity.Utilisateur;
import com.example.voiture.repository.ConversationRepository;
import com.example.voiture.repository.MessageRepository;
import com.example.voiture.repository.UtilisateurRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    public Message insererMessage(Message message) {
        Utilisateur utilisateur1 = utilisateurRepository.findByEmailAndPassword(message.getConversation().getUtilisateur1().getEmail(), message.getConversation().getUtilisateur1().getPassword());
        Utilisateur utilisateur2 = utilisateurRepository.findByEmailAndPassword(message.getConversation().getUtilisateur2().getEmail(), message.getConversation().getUtilisateur2().getPassword());
        Conversation conversation = conversationRepository.findByUtilisateur1AndUtilisateur2(utilisateur1, utilisateur2);
        Utilisateur sender = utilisateurRepository.findByEmailAndPassword(message.getSender().getEmail(), message.getSender().getPassword());

        Message newMessage = new Message(conversation, sender, message.getContenuMessage(), message.getDateHeure());
        return messageRepository.save(newMessage);
    }

    public List<Message> listMessagesByConversation(Conversation conversation) {
        Utilisateur utilisateur1 = utilisateurRepository.findByEmailAndPassword(conversation.getUtilisateur1().getEmail(), conversation.getUtilisateur1().getPassword());
        Utilisateur utilisateur2 = utilisateurRepository.findByEmailAndPassword(conversation.getUtilisateur2().getEmail(), conversation.getUtilisateur2().getPassword());

        Conversation newConversation = conversationRepository.findByUtilisateur1AndUtilisateur2(utilisateur1, utilisateur2);
        return messageRepository.findByConversation(newConversation);
    }
}

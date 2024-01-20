package com.example.voiture.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.voiture.entity.Conversation;
import com.example.voiture.entity.Utilisateur;
import com.example.voiture.repository.ConversationRepository;

@Service
public class ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    public Conversation insererConversation(Utilisateur utilisateur1, Utilisateur utilisateur2) {
        Conversation conversation = new Conversation(utilisateur1, utilisateur2);
        return conversationRepository.save(conversation);
    }

    public List<Conversation> listConversationsByUser(Utilisateur utilisateur) {
        return conversationRepository.findByUtilisateur(utilisateur);
    }

    public Conversation getConversationById(String id) {
        Optional<Conversation> conversationOptional = conversationRepository.findById(id);
        return conversationOptional.orElse(null);
    }
}

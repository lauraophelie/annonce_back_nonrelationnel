package com.example.voiture.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.voiture.entity.Conversation;
import com.example.voiture.entity.Utilisateur;
import com.example.voiture.repository.ConversationRepository;
import com.example.voiture.repository.UtilisateurRepository;

@Service
public class ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Conversation insererConversation(Utilisateur utilisateur1, Utilisateur utilisateur2) {
        Utilisateur u1 = utilisateurRepository.findByEmailAndPassword(utilisateur1.getEmail(), utilisateur1.getPassword());
        Utilisateur u2 = utilisateurRepository.findByEmailAndPassword(utilisateur2.getEmail(), utilisateur2.getPassword());
        Conversation conversation = new Conversation(u1, u2);
        return conversationRepository.save(conversation);
    }

    public List<Conversation> listConversationsByUser(Utilisateur utilisateur) {
        Utilisateur u = utilisateurRepository.findByEmailAndPassword(utilisateur.getEmail(), utilisateur.getPassword());
        return conversationRepository.findByUtilisateur(u);
    }

    public Conversation getConversationById(String id) {
        Optional<Conversation> conversationOptional = conversationRepository.findById(id);
        return conversationOptional.orElse(null);
    }

    public Conversation getConversationByUtilisateur1AndUtilisateur2(Utilisateur utilisateur1, Utilisateur utilisateur2) {
        return conversationRepository.findByUtilisateur1AndUtilisateur2(utilisateur1, utilisateur2);
    }
}

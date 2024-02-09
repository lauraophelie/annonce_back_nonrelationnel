package com.example.voiture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.voiture.entity.Conversation;
import com.example.voiture.entity.Message;
import com.example.voiture.entity.Utilisateur;
import com.example.voiture.response.Response;
import com.example.voiture.services.ConversationService;
import com.example.voiture.services.FCMService;
import com.example.voiture.services.MessageService;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private FCMService notificationService;

    @PostMapping("/ajout")
    public Response ajouterMessage(@RequestBody Message message) {
        Response response = new Response();
        Message messageAjoute = messageService.insererMessage(message);
        response.setDonner(messageAjoute);
        Utilisateur sender = messageAjoute.getSender();
        Utilisateur destinataire = null;
        if (sender.getId() == messageAjoute.getConversation().getUtilisateur1().getId()) {
            destinataire = messageAjoute.getConversation().getUtilisateur2();
        } else if (sender.getId() == messageAjoute.getConversation().getUtilisateur2().getId()) {
            destinataire = messageAjoute.getConversation().getUtilisateur1();
        }
        @SuppressWarnings("null")
        String nomDestinataire = destinataire.getNomComplet();
        // Envoyer une notification après avoir inséré un nouveau message
        String titreNotification = "Nouveau message reçu";
        String corpsNotification = "Vous avez reçu un nouveau message de " + nomDestinataire;
        
        // Appelez la méthode sendNotification avec les informations nécessaires
        // notificationService.sendNotification(titreNotification, titreNotification, corpsNotification);
        if (response.getDonner() != null) {
            response.setErreur(false);
            response.setInformation("Message ajoute avec succes");
        } else {
            response.setInformation("Erreur en essayant d'ajouter un message");
        }
        return response;
    }

    @GetMapping("/messages")
    public Response listMessagesByConversation(@RequestParam String conversationId) {
        Response response = new Response();
        Conversation conversation = conversationService.getConversationById(conversationId);
        List<Message> message = messageService.listMessagesByConversation(conversation);
        response.setDonner(message);
        if (response.getDonner() != null) {
            response.setErreur(false);
            response.setInformation("Liste des messages pour une conversation");
        } else {
            response.setInformation("Erreur en essayant de lister des messages");
        }
        return response;
    }
}

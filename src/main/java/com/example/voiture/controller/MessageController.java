package com.example.voiture.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.voiture.entity.Conversation;
import com.example.voiture.entity.Message;
import com.example.voiture.response.Response;
import com.example.voiture.services.ConversationService;
import com.example.voiture.services.MessageService;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ConversationService conversationService;

    @PostMapping("/ajout")
    public Response ajouterMessage(@RequestBody Message message) {
        Response response = new Response();
        Message messageAjoute = messageService.insererMessage(message);
        response.setDonner(messageAjoute);
        if (response.getDonner() != null) {
            response.setErreur(false);
            response.setInformation("Message ajoute avec succes");
        } else {
            response.setInformation("Erreur en essayant d'ajouter un message");
        }
        return response;
    }

    @GetMapping("/messages")
    public Response listConversationsByUser(@RequestParam String conversationId) {
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

package com.example.voiture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.voiture.authentification.JwtUtil;
import com.example.voiture.entity.Conversation;
import com.example.voiture.entity.Utilisateur;
import com.example.voiture.response.Response;
import com.example.voiture.services.ConversationService;
import com.example.voiture.services.UtilisateurService;

import io.jsonwebtoken.Claims;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/conversation")
public class ConversationController {
    private final JwtUtil jwtUtil;
    @Autowired
    private ConversationService conversationService;

    @Autowired
    private UtilisateurService utilisateurService;

    public ConversationController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/ajout")
    public Response insererConversation(@RequestBody Conversation conversationRequest) {
        Response response = new Response();
        Conversation conversation = conversationService.insererConversation(conversationRequest.getUtilisateur1(),
                conversationRequest.getUtilisateur2());
        response.setDonner(conversation);
        if (response.getDonner() != null) {
            response.setErreur(false);
            response.setInformation("Ajout de conversation reussi");
        } else {
            response.setInformation("Erreur en essayant d'ajouter une conversation");
        }
        return response;
    }

    @GetMapping("/conversations")
    public Response listConversationsByUser(@RequestHeader(name = "Authorization") String authorizationHeader) {
        Response response = new Response();
        String utilisateurId = "";
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            Claims claims = jwtUtil.parseJwtClaims(token);
            utilisateurId = JwtUtil.getUserId(claims);
        }
        Utilisateur utilisateur = utilisateurService.getUtilisateurById(utilisateurId);
        List<Conversation> conversation = conversationService.listConversationsByUser(utilisateur);
        response.setDonner(conversation);
        if (response.getDonner() != null) {
            response.setErreur(false);
            response.setInformation("Liste des conversations pour une utilisateur");
        } else {
            response.setInformation("Erreur en essayant de lister des conversations");
        }
        return response;
    }
}

package com.example.voiture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.voiture.entity.Utilisateur;
import com.example.voiture.response.Response;
import com.example.voiture.services.UtilisateurService;

@RestController
@RequestMapping("/auth")
public class AuthentificationController {

    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping("/login")
    public Response login(@RequestBody Utilisateur utilisateur) {
        Response response = new Response();
        String email = utilisateur.getEmail();
        String password = utilisateur.getPassword();
        Utilisateur u = utilisateurService.getUtilisateur(email, password);
        response.setDonner(u);
        if (response.getDonner() != null) {
            response.setErreur(false);
            response.setInformation("Authentification réussie");
        } else {
            response.setErreur(true);
            response.setInformation("Identifiants invalides");
        }
        return response;
    }
}

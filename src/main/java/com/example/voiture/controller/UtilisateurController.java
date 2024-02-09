package com.example.voiture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.voiture.entity.Utilisateur;
import com.example.voiture.response.Response;
import com.example.voiture.services.UtilisateurService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/utilisateur")
@CrossOrigin(origins = "*")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping("/inscription")
    public Response ajouterUtilisateur(@RequestBody Utilisateur utilisateur) {
        Response response = new Response();
        Utilisateur utilisateurAjoute = utilisateurService.insererUtilisateur(utilisateur);
        response.setDonner(utilisateurAjoute);
        if (response.getDonner() != null) {
            response.setErreur(false);
            response.setInformation("Inscription reussi");
        } else {
            response.setInformation("Erreur en essayant de s'inscrire");
        }
        return response;
    }

    @GetMapping("/utilisateurs")
    public Response getOneUtilisateur(@RequestParam String utilisateurId) {
        Response response = new Response();
        Utilisateur utilisateurRecupere = utilisateurService.getUtilisateurById(utilisateurId);
        System.out.println("utilisateurRecupere " + utilisateurRecupere.getNomComplet());
        response.setDonner(utilisateurRecupere);
        if (response.getDonner() != null) {
            response.setErreur(false);
            response.setInformation("L'utilisateur selectionné");
        } else {
            response.setInformation("L'utilisateur est introuvable");
        }
        return response;
    }
}


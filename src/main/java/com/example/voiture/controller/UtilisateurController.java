package com.example.voiture.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import com.example.voiture.authentification.JwtUtil;
import com.example.voiture.entity.Utilisateur;
import com.example.voiture.entity.response.ErrorRes;
import com.example.voiture.entity.response.LoginRes;
import com.example.voiture.repository.UtilisateurRepository;
import com.example.voiture.response.Response;
import com.example.voiture.services.UtilisateurService;


@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {

    private final AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;
    private final UtilisateurRepository r_Admin;

    @Autowired
    private UtilisateurService utilisateurService;

    public UtilisateurController(AuthenticationManager authenticationManager, JwtUtil jwtUtil,UtilisateurRepository r_Admin) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.r_Admin = r_Admin;
    }

    // @PostMapping("/inscription")
    // public Response ajouterUtilisateur(@RequestBody Utilisateur utilisateur) {
    //     Response response = new Response();
    //     Utilisateur utilisateurAjoute = utilisateurService.insererUtilisateur(utilisateur);
    //     response.setDonner(utilisateurAjoute);
    //     if (response.getDonner() != null) {
    //         response.setErreur(false);
    //         response.setInformation("Inscription reussi");
    //     } else {
    //         response.setInformation("Erreur en essayant de s'inscrire");
    //     }

    //     return response;
    // }

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


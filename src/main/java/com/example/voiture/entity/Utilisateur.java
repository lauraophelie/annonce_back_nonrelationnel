package com.example.voiture.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "utilisateur")
public class Utilisateur {

    @Id
    @JsonProperty("_id")
    private String id;

    @JsonProperty("nom_complet")
    private String nomComplet;

    @JsonProperty("date_naissance")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private LocalDate dateNaissance;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("role")
    private int role;

    public Utilisateur() {
    }

    public Utilisateur(String id, String nomComplet, LocalDate dateNaissance, String email, String password, int role) {
        this.id = id;
        this.nomComplet = nomComplet;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        if (this.role == 1) {
            return "ADMIN";
        }
        return "USER";
    }

    public void setRole(String admin) {
        if (admin.equalsIgnoreCase("ROLE_ADMIN")) {
            this.role = 1;
        } else {
            this.role = 0;
        }
    }
}

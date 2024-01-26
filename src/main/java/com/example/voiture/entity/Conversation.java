package com.example.voiture.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;


@Document(collection = "conversation")
public class Conversation {
    @Id
    @JsonProperty("_id")
    private String id;

    @JsonProperty("utilisateur1")
    private Utilisateur utilisateur1;

    @JsonProperty("utilisateur2")
    private Utilisateur utilisateur2;

    public Conversation(Utilisateur utilisateur1, Utilisateur utilisateur2) {
        this.utilisateur1 = utilisateur1;
        this.utilisateur2 = utilisateur2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur1() {
        return utilisateur1;
    }

    public void setUtilisateur1(Utilisateur utilisateur1) {
        this.utilisateur1 = utilisateur1;
    }

    public Utilisateur getUtilisateur2() {
        return utilisateur2;
    }

    public void setUtilisateur2(Utilisateur utilisateur2) {
        this.utilisateur2 = utilisateur2;
    }
}

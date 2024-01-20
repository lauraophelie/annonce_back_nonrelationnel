package com.example.voiture.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

import jakarta.persistence.Id;

@Document(collection = "message")
public class Message {
    @Id
    @JsonProperty("_id")
    private String id;

    @JsonProperty("conversation")
    private Conversation conversation;

    @JsonProperty("sender")
    private Utilisateur sender;

    @JsonProperty("contenu_message")
    private String contenuMessage;

    @JsonProperty("date_heure")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private LocalDate dateHeure;

    public Message(String id, Conversation conversation, Utilisateur sender, String contenuMessage,
            LocalDate dateHeure) {
        this.id = id;
        this.conversation = conversation;
        this.sender = sender;
        this.contenuMessage = contenuMessage;
        this.dateHeure = dateHeure;
    }

    public Message() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public Utilisateur getSender() {
        return sender;
    }

    public void setSender(Utilisateur sender) {
        this.sender = sender;
    }

    public String getContenuMessage() {
        return contenuMessage;
    }

    public void setContenuMessage(String contenuMessage) {
        this.contenuMessage = contenuMessage;
    }

    public LocalDate getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(LocalDate dateHeure) {
        this.dateHeure = dateHeure;
    }
}

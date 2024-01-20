package com.example.voiture.response;

public class Response {
    boolean erreur = true;
    String information;
    Object donner;

    public boolean isErreur() {
        return erreur;
    }
    public void setErreur(boolean erreur) {
        this.erreur = erreur;
    }
    public Object getDonner() {
        return donner;
    }
    public void setDonner(Object donner) {
        this.donner = donner;
    }
    public String getInformation() {
        return information;
    }
    public void setInformation(String information) {
        this.information = information;
    }
}
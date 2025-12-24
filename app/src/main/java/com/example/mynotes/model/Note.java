package com.example.mynotes.model;

public class Note {

    private String nom;
    private String description;
    private String date;
    private String priorite;

    public Note(String nom, String description, String date, String priorite) {
        this.nom = nom;
        this.description = description;
        this.date = date;
        this.priorite = priorite;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getPriorite() {
        return priorite;
    }
}

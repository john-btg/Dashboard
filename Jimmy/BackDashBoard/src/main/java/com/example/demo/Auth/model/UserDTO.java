package com.example.demo.Auth.model;

public class UserDTO {
    private String username;
    private String mdp;
    private String nom;
    private String prenom;
    private String num;
    private boolean enabledUser;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return mdp;
    }

    public void setPassword(String mdp) {
        this.mdp = mdp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public boolean isEnabledUser() {
        return enabledUser;
    }

    public void setEnabledUser(boolean enabledUser) {
        this.enabledUser = enabledUser;
    }
}
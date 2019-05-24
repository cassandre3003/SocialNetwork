package fr.ul.miage.SocialNetwork.graph;

import java.util.HashSet;

public class Noeud {
    private String nom;
    private String id;
    private int position; // pour la recherche
    private HashSet<String> liens; // les ID des liens

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Noeud(String nom, String id, HashSet<String> liens) {
        this.nom = nom;
        this.id = id;
        this.liens = liens;
    }

    public Noeud() {
    }

    public HashSet<String> getLiens() {
        return liens;
    }

    public void setLiens(HashSet<String> liens) {
        this.liens = liens;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Noeud(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }




}

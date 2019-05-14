package fr.ul.miage.SocialNetwork.graph;

import java.util.HashSet;

public class Noeud {
    private String nom;
    private String id;
    private HashSet<String> liens;

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


    public HashSet<String> getLiens() {
        return liens;
    }

    public void setLiens(HashSet<String> liens) {
        this.liens = liens;
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

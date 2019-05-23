package fr.ul.miage.SocialNetwork.graph;

import java.util.HashSet;

public class Lien {
    private String noeudA;  // Anna avec un id
    private String noeudB;  // Elsa avec un id
    private String id;
    private String direction; // (inexistante, AB, BA) --> A voir une énumération ?????
    private String type;   // siblings
    private HashSet<String> attributs; // since=2013

    public Lien(String noeudA, String noeudB, String id, String direction, String type, HashSet<String> attributs) {
        this.noeudA = noeudA;
        this.noeudB = noeudB;
        this.id = id;
        this.direction = direction;
        this.type = type;
        this.attributs = attributs;
    }


    public Lien(String noeudA, String noeudB, String direction, String type) {
        this.noeudA = noeudA;
        this.noeudB = noeudB;
        this.direction = direction;
        this.type = type;
    }

    public Lien(Lien lien, String id){
        this.noeudA = lien.noeudA;
        this.noeudB = lien.noeudB;
        this.id = id;
        this.direction = lien.direction;
        this.type = lien.type;
        this.attributs = lien.attributs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Lien{" +
                "noeudA='" + noeudA + '\'' +
                ", noeudB='" + noeudB + '\'' +
                ", id='" + id + '\'' +
                ", direction='" + direction + '\'' +
                ", type='" + type + '\'' +
                ", attributs=" + attributs +
                '}';
    }

    public Lien(String noeudA, String noeudB, String direction, String type, HashSet<String> attributs) {
        this.noeudA = noeudA;
        this.noeudB = noeudB;
        this.direction = direction;
        this.type = type;
        this.attributs = attributs;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }public HashSet<String> getAttributs() {
        return attributs;
    }

    public void setAttributs(HashSet<String> attributs) {
        this.attributs = attributs;
    }

    public String getNoeudA() {
        return noeudA;
    }

    public String getNoeudB() {
        return noeudB;
    }

    public String getType() {
        return type;
    }


    public void setNoeudA(String noeudA) {
        this.noeudA = noeudA;
    }

    public void setNoeudB(String noeudB) {
        this.noeudB = noeudB;
    }

    public void setType(String type) {
        this.type = type;
    }

}

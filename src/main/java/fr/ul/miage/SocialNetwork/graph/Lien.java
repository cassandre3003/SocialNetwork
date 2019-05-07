package fr.ul.miage.SocialNetwork.graph;

import java.util.HashSet;
import java.util.Set;

public class Lien {
    private Noeud noeudA;  // Anna
    private Noeud noeudB;  // Elsa
    private String direction; // (inexistante, AB, BA) --> A voir une énumération ?????
    private String type;   // siblings
    private HashSet<String> attributs; // since=2013


    public Lien(Noeud noeudA, Noeud noeudB, String direction, String type, HashSet<String> attributs) {
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

    public Noeud getNoeudA() {
        return noeudA;
    }

    public Noeud getNoeudB() {
        return noeudB;
    }

    public String getType() {
        return type;
    }


    public void setNoeudA(Noeud noeudA) {
        this.noeudA = noeudA;
    }

    public void setNoeudB(Noeud noeudB) {
        this.noeudB = noeudB;
    }

    public void setType(String type) {
        this.type = type;
    }

}

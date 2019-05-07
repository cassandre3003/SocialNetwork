package fr.ul.miage.SocialNetwork.file;


/*
* Notre ligne est déjà vérifiée... il ne peut pas y avoir d'erreur de construction
*/

import fr.ul.miage.SocialNetwork.graph.Lien;
import fr.ul.miage.SocialNetwork.graph.Noeud;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class Interpreteur {
    private String ligne;

    public Interpreteur(String ligne) {
        this.ligne = ligne;
    }

    public String getLigne() {
        return ligne;
    }

    public void setLigne(String ligne) {
        this.ligne = ligne;
    }

    public Noeud getNoeudA(){
        int fin = ligne.indexOf("-");
        return new Noeud(ligne.substring(0,fin));

    }

    public Noeud getNoeudB() {
        int debut;
        if (ligne.contains("<")){
            debut = ligne.lastIndexOf("<");
        }else if (ligne.contains(">")){
            debut = ligne.lastIndexOf(">");
        }else {
            debut = ligne.lastIndexOf("-");
        }
        return new Noeud(ligne.substring(debut+1));
    }

    public Lien getLien() {
        // Elisabeth--likes[since=2018]-->Elasticsearch
        String[] test = ligne.split("--");
        String liens = test[1];

        int finNom = liens.indexOf("[");
        String type = liens.substring(0, finNom);

        String[] attributs = liens.substring(finNom + 1, liens.indexOf("]")).split(";");
        HashSet<String> attributsSet = new HashSet<String>(Arrays.asList(attributs));
        Noeud noeudA = this.getNoeudA();
        Noeud noeudB = this.getNoeudB();


        Lien lien = new Lien(noeudA, noeudB, this.getSens(), type, attributsSet);

        return lien;
    }

    public String getSens(){
        if (ligne.contains(">")){
            return "AB";
        }else if (ligne.contains("<")){
            return "BA";
        }else {
            return "inexistante";
        }
    }

    public void creerGraph(){

    }
}

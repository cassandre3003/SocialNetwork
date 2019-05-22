package fr.ul.miage.SocialNetwork.recherche;

import fr.ul.miage.SocialNetwork.file.Reader;
import fr.ul.miage.SocialNetwork.graph.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

public class Recherche {
    // Critères de recherche
    private String parcours;
    private String noeudID;
    private String typeLien;
    private String direction;
    private int profondeur; //Si la profondeur est à 0, on fait tout l'arbre
    private String unicite;

    public String getParcours() {
        return parcours;
    }

    public void setParcours(String parcours) {
        this.parcours = parcours;
    }

    public String getNoeudID() {
        return noeudID;
    }

    public void setNoeudID(String noeudID) {
        this.noeudID = noeudID;
    }

    public String getTypeLien() {
        return typeLien;
    }

    public void setTypeLien(String typeLien) {
        this.typeLien = typeLien;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getProfondeur() {
        return profondeur;
    }

    public void setProfondeur(int profondeur) {
        this.profondeur = profondeur;
    }

    public String getUnicite() {
        return unicite;
    }

    public void setUnicite(String unicite) {
        this.unicite = unicite;
    }


    public HashSet<Noeud> recherche() throws IOException { // Recherche général
        Reader read = new Reader();
        Graph graph = read.creerGraph();
        rechercheParcoursProfondeurNoeud(graph);


        return null;
    }

    public HashSet<String> rechercheParcoursProfondeurNoeud(Graph graph){ // Filtre le résultat de la recherche selon le parcours en profondeur
        Stack<Lien> pileLiens = graph.getPileLiensByIdNoeud(noeudID); //création d'une pile pour voir si on est passé plusieurs fois sur un noeud
        HashSet<String> idLienMarque = new HashSet<>();
        Lien lien;
        Noeud noeudVoisin;
        HashSet<String> res = new HashSet<>();
        while(!pileLiens.empty()){ // Tant que la pile n'est pas vide
            lien = pileLiens.pop(); //On retire un lien de la pile
            if(lienValide(lien) && !idLienMarque.contains(lien.getId())){ //Si le lien est valide
                idLienMarque.add(lien.getId()); // Marquer le lien
                if(!lien.getNoeudB().equals(noeudID)){ //Si le noeud n'est pas celui sur lequel on est
                    noeudVoisin = graph.getNoeudById(lien.getNoeudB()); // prend le noeud voisin
                    res.add(noeudVoisin.getNom());
                    pileLiens.addAll(graph.getPileLiensByIdNoeud(lien.getNoeudB()));
                }
            }
        }
        return res;
    }

    public boolean lienValide(Lien lien){
        if(lien.getNoeudB().equals(noeudID)){
            if(direction.equals("AB")) {
                return (lien.getType().equals(typeLien) && lien.getDirection().equals("BA"));
            }
            else if(direction.equals("BA")) {
                return (lien.getType().equals(typeLien) && lien.getDirection().equals("AB"));
            }
            else{
                return lien.getType().equals(typeLien) && lien.getDirection().equals(direction);
            }
        }
        return (lien.getType().equals(typeLien) && lien.getDirection().equals(direction));
    }

    public String rechercheParcoursLargeur(){return null;} // Filtre le résultat de la recherche selon le parcours en largeur

    public String rechercheNoeudID(){ return null;} //Filtre le résultat de la recherche général selon l'ID du Noeud

    public String rechercheTypeLien(){return null;} //Filtre le résultat de la recherche général selon le type de lien (-> veut dire ami)

    public String rechercheDirection(){return null;} //Filtre le résultat de la recherche général selon le sens du type (exemple: X -> Y ou x <- Y)

    public String rechercheNiveauProfondeur(){ return null;} //Filtre le résultat de la recherche général selon le numéro de la profondeur (à quel point...)

    public String RechercheUniciteLien(){ return null;} // Filtre le résultat de la recherche général selon le fait qu'on passe une fois ou autre sur un lien

    public String RechercheUniciteNoeud(){ return null;} // Filtre le résultat de la recherche général selon le fait qu'on passe une fois ou plus dans un noeud

}

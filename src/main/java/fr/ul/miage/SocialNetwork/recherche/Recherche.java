package fr.ul.miage.SocialNetwork.recherche;

import com.sun.org.apache.regexp.internal.RE;
import fr.ul.miage.SocialNetwork.file.Reader;
import fr.ul.miage.SocialNetwork.graph.*;

import java.io.IOException;

import java.util.*;

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
    private HashSet<String> attributs;

    public Recherche(String direction, String noeudID, String typeLien) {
        this.direction = direction;
        this.noeudID = noeudID;
        this.typeLien = typeLien;
    }

    public Recherche(String parcours, int profondeur, String unicite, String direction, String noeudID, String typeLien, HashSet<String> attributs) {
        this.direction = direction;
        this.noeudID = noeudID;
        this.typeLien = typeLien;
        this.parcours = parcours;
        this.profondeur = profondeur;
        this.unicite = unicite;
        this.attributs=attributs;
    }

    public Recherche() {

    }

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

    public HashSet<String> recherche() throws IOException { // Recherche général
        HashSet<String> resultat;
        Reader read = new Reader();
        Graph graph = read.creerGraph();
        if (profondeur == 0) {
            if (parcours.equals("Longueur d'abord")) {
                if (unicite.equals("NoeudGlobal")) {
                    resultat = rechercheParcoursProfondeurNoeud(graph);
                } else {
                    resultat = rechercheParcoursProfondeurLien(graph);
                }
            } else {
                if (unicite.equals("NoeudGlobal")) {
                    resultat = rechercheParcoursLargeurNoeud(graph);
                } else {
                    resultat = rechercheParcoursLargeurLien(graph);
                }
            }
        } else {
            resultat = rechercheNiveauProfondeur();
        }
        return resultat;
    }

    public HashSet<String> rechercheParcoursProfondeurNoeud(Graph graph) { // Filtre le résultat de la recherche selon le parcours en profondeur où on ne peut passer qu'une seule fois par un même noeud
        Stack<Lien> pileLiens = graph.getPileLiensByIdNoeud(noeudID); //création d'une pile pour voir si on est passé plusieurs fois sur un noeud
        HashSet<String> idLienMarque = new HashSet<>();
        Lien lien;
        Noeud noeudVoisin;
        HashSet<String> res = new HashSet<>();
        while (!pileLiens.empty()) { // Tant que la pile n'est pas vide
            lien = pileLiens.pop(); //On retire un lien de la pile
            if (lienValide(lien) && !idLienMarque.contains(lien.getId())) { //Si le lien est valide
                idLienMarque.add(lien.getId()); // Marquer le lien
                if (!lien.getNoeudB().equals(noeudID)) { //Si le noeud n'est pas celui sur lequel on est
                    noeudVoisin = graph.getNoeudById(lien.getNoeudB()); // prend le noeud voisin
                    res.add(noeudVoisin.getNom());
                    pileLiens.addAll(graph.getPileLiensByIdNoeud(lien.getNoeudB()));
                }
            }
        }
        return res;
    }

    public HashSet<String> rechercheParcoursProfondeurLien(Graph graph) {
        HashSet<String> resultat = new HashSet<>();
        ArrayList<String> idLienMarques = new ArrayList();
        Stack<Noeud> pileNoeuds = new Stack();
        HashSet<String> noeudsVoisin = new HashSet<>();
        String idNoeudVoisin;
        Lien lien;
        pileNoeuds.push(graph.getNoeudById(noeudID));
        while (!pileNoeuds.empty()) {
            Noeud noeud = pileNoeuds.pop();
            if (!noeud.getId().equals(noeudID)) {
                resultat.add(noeud.getNom());
            }
            noeudsVoisin = graph.getNoeudsVoisinsById(this, idLienMarques);
            Iterator noeudsIt = noeudsVoisin.iterator();
            while (noeudsIt.hasNext()) {
                idNoeudVoisin = (String) noeudsIt.next();
                pileNoeuds.push(graph.getNoeudById(idNoeudVoisin));
            }
        }
        return resultat;
    }

    public HashSet<String> rechercheParcoursLargeurNoeud(Graph graph) { // Filtre le résultat de la recherche selon le parcours en largeur où on ne peut passer q'une seule fois par un même noeud
        LinkedList<Lien> fileLiens = graph.getFileLiensByIdNoeud(noeudID);
        HashSet<String> idLienMarque = new HashSet<>();
        Lien lien;
        Noeud noeudVoisin;
        HashSet<String> res = new HashSet<>();
        while (!fileLiens.isEmpty()) { // Tant que la pile n'est pas vide
            lien = fileLiens.pop(); //On retire un lien de la pile
            if (lienValide(lien) && !idLienMarque.contains(lien.getId())) { //Si le lien est valide
                idLienMarque.add(lien.getId()); // Marquer le lien
                if (!lien.getNoeudB().equals(noeudID)) { //Si le noeud n'est pas celui sur lequel on est
                    noeudVoisin = graph.getNoeudById(lien.getNoeudB()); // prend le noeud voisin
                    res.add(noeudVoisin.getNom());
                    fileLiens.addAll(graph.getFileLiensByIdNoeud(lien.getNoeudB()));
                }
            }
        }
        return res;
    }

    public HashSet<String> rechercheParcoursLargeurLien(Graph graph) {
        HashSet<String> resultat = new HashSet<>();
        ArrayList<String> idLienMarques = new ArrayList();
        LinkedList<Noeud> fileNoeuds = new LinkedList<>();
        HashSet<String> noeudsVoisin = new HashSet<>();
        String idNoeudVoisin;
        Lien lien;
        fileNoeuds.push(graph.getNoeudById(noeudID));
        while (!fileNoeuds.isEmpty()) {
            Noeud noeud = fileNoeuds.pop();
            if (!noeud.getId().equals(noeudID)) {
                resultat.add(noeud.getNom());
            }
            noeudsVoisin = graph.getNoeudsVoisinsById(this, idLienMarques);
            Iterator noeudsIt = noeudsVoisin.iterator();
            while (noeudsIt.hasNext()) {
                idNoeudVoisin = (String) noeudsIt.next();
                fileNoeuds.push(graph.getNoeudById(idNoeudVoisin));
            }
        }
        return resultat;
    }


    public boolean lienValide(Lien lien) {
        if (lien.getNoeudB().equals(noeudID)) {
            if (direction.equals("AB")) {
                return (lien.getType().equals(typeLien) && lien.getDirection().equals("BA"));
            } else if (direction.equals("BA")) {
                return (lien.getType().equals(typeLien) && lien.getDirection().equals("AB"));
            } else {
                return lien.getType().equals(typeLien) && lien.getDirection().equals(direction);
            }
        }
        return (lien.getType().equals(typeLien) && lien.getDirection().equals(direction));
    }

    //Filtre le résultat de la recherche selon le numéro de la profondeur (à quel point...)
    public HashSet<String> rechercheNiveauProfondeur() {
        HashSet<String> resultat = new HashSet<>();
        return resultat;
    }
}

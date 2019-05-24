package fr.ul.miage.SocialNetwork.recherche;

import fr.ul.miage.SocialNetwork.file.Reader;
import fr.ul.miage.SocialNetwork.graph.Graph;
import fr.ul.miage.SocialNetwork.graph.Lien;
import fr.ul.miage.SocialNetwork.graph.Noeud;

import java.io.IOException;
import java.util.*;


public class Recherche {
    // Critères de recherche
    private String parcours;
    private String noeudID;
    private String typeLien;
    private String direction;
    private int profondeur; //Si la profondeur est à 0, on fait tout l'arbre
    private String unicite;

    public Recherche(String direction, String noeudID, String typeLien) {
        this.direction = direction;
        this.noeudID = noeudID;
        this.typeLien= typeLien;
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
        HashSet<String>  resultat;
        Reader read = new Reader();
        Graph graph = read.creerGraph();
        if(profondeur == 0){
            profondeur = Integer.MAX_VALUE;
        }
        if(parcours.equals("Longueur d'abord")){
            if(unicite.equals("NoeudGlobal")){
                resultat = rechercheParcoursProfondeurNoeud(graph);
            }
            else{
                resultat = rechercheParcoursProfondeurLien(graph);
            }
        }
        else{
            if(unicite.equals("NoeudGlobal")){
                resultat = rechercheParcoursLargeurNoeud(graph);
            }
            else{
                resultat = rechercheParcoursLargeurLien(graph);
            }
        }
        return resultat;
    }

    public HashSet<Lien> ajoutDePositionLien(HashSet<Lien> liens, int position){
        liens.forEach(lien -> {
            lien.setPosition(position);
        });
        return liens;
    }

    public HashSet<Noeud> ajoutDePositionNoeud(HashSet<String> ids, Graph graph, int position){
        HashSet<Noeud> noeuds = new HashSet<>();
        Noeud noeud;
        for (String id : ids) {
            noeud = graph.getNoeudById(id);
            noeud.setPosition(position);
            noeuds.add(noeud);
        }
        return noeuds;
    }

    public HashSet<String> rechercheParcoursProfondeurNoeud(Graph graph) { // Filtre le résultat de la recherche selon le parcours en profondeur où on ne peut passer qu'une seule fois par un même noeud
        HashSet<Lien> liens = graph.getLiensByIdNoeud(noeudID); //création d'une pile pour voir si on est passé plusieurs fois sur un noeud
        int position = 1;
        Stack<Lien> pileLiens = new Stack<>();
        pileLiens.addAll(ajoutDePositionLien(liens, position));
        Lien lien;
        Noeud noeudVoisin;
        ArrayList<String> idLienMarques = new ArrayList();
        HashSet<String> res = new HashSet<>();
        while (!pileLiens.empty()) { // Tant que la pile n'est pas vide
            lien = pileLiens.pop(); //On retire un lien de la pile
            position = lien.getPosition()+1;
            if (lienValide(lien)&& !idLienMarques.contains(lien.getId())) { //Si le lien est valide
                idLienMarques.add(lien.getId()); // Marquer le lien
                if (!lien.getNoeudB().equals(noeudID)) { //Si le noeud n'est pas celui sur lequel on est
                    noeudVoisin = graph.getNoeudById(lien.getNoeudB()); // prend le noeud voisin
                    if (profondeur >= lien.getPosition()){
                        res.add(noeudVoisin.getNom());
                        ajoutDePositionLien(graph.getLiensByIdNoeud(lien.getNoeudB()),position);
                        pileLiens.addAll(ajoutDePositionLien(graph.getLiensByIdNoeud(lien.getNoeudB()),position));
                    }
                }
            }
        }
        return res;
    }

    public HashSet<String> rechercheParcoursProfondeurLien(Graph graph){
        HashSet<String> resultat = new HashSet<>();
        int position=0;
        ArrayList<String> idLienMarques = new ArrayList();
        Stack<Noeud> pileNoeuds = new Stack();
        HashSet<String> idNoeudsVoisin = new HashSet<>();
        HashSet<Noeud> noeudsVoisin = new HashSet<>();
        Noeud noeudVoisin;
        Lien lien;
        Noeud noeud = graph.getNoeudById(noeudID);
        noeud.setPosition(position);
        pileNoeuds.push(noeud);
        while(!pileNoeuds.empty()){
            noeud = pileNoeuds.pop();
            position = noeud.getPosition()+1;
            if (!noeud.getId().equals(noeudID) && noeud.getPosition() <= profondeur){
                resultat.add(noeud.getNom());
            }
            idNoeudsVoisin = graph.getNoeudsVoisinsById(this,idLienMarques, noeud.getId());
            noeudsVoisin = ajoutDePositionNoeud(idNoeudsVoisin, graph, position);
            Iterator noeudsIt = noeudsVoisin.iterator();
            while(noeudsIt.hasNext()){
                noeudVoisin = (Noeud)noeudsIt.next();
                pileNoeuds.push(noeudVoisin);
            }
        }
        return resultat;
    }


    public HashSet<String> rechercheParcoursLargeurNoeud(Graph graph) { // Filtre le résultat de la recherche selon le parcours en largeur où on ne peut passer q'une seule fois par un même noeud
        HashSet<Lien> liens = graph.getLiensByIdNoeud(noeudID);
        int position = 1;
        Lien lien;
        HashSet<String> idLienMarque = new HashSet<>();
        liens = ajoutDePositionLien(liens, position);
        LinkedList<Lien> fileLiens = graph.getFileLiensByIdNoeud(noeudID);
        Noeud noeudVoisin;
        HashSet<String> res = new HashSet<>();
        while (!fileLiens.isEmpty()) { // Tant que la pile n'est pas vide
            lien = fileLiens.pop(); //On retire un lien de la pile
            if (lienValide(lien) && !idLienMarque.contains(lien.getId())) { //Si le lien est valide
                idLienMarque.add(lien.getId()); // Marquer le lien
                if (!lien.getNoeudB().equals(noeudID) && lien.getPosition() <= profondeur) { //Si le noeud n'est pas celui sur lequel on est
                    noeudVoisin = graph.getNoeudById(lien.getNoeudB()); // prend le noeud voisin
                    res.add(noeudVoisin.getNom());
                    liens = graph.getLiensByIdNoeud(lien.getNoeudB());
                    position = lien.getPosition()+1;
                    liens = ajoutDePositionLien(liens, position);
                    fileLiens.addAll(graph.getFileLiensByIdNoeud(lien.getNoeudB()));

                }
            }

        }
        return res;
    }

    public HashSet<String> rechercheParcoursLargeurLien(Graph graph){
        HashSet<String> resultat = new HashSet<>();
        int position = 0;
        ArrayList<String> idLienMarques = new ArrayList();
        LinkedList<Noeud> fileNoeuds = new LinkedList<>();
        HashSet<String> idsNoeudsVoisin = new HashSet<>();
        HashSet<String> noeudsVoisin = new HashSet<>();
        String idNoeudVoisin;
        Lien lien;
        Noeud noeud = graph.getNoeudById(noeudID);
        noeud.setPosition(position);
        fileNoeuds.push(noeud);
        while(!fileNoeuds.isEmpty()){
            noeud = fileNoeuds.pop();
            if (!noeud.getId().equals(noeudID) && noeud.getPosition() <= profondeur) {
                resultat.add(noeud.getNom());
            }
            idsNoeudsVoisin = graph.getNoeudsVoisinsById(this,idLienMarques, noeud.getId());
            position = 1 + noeud.getPosition();
            for (String id : idsNoeudsVoisin) {
                noeud = graph.getNoeudById(id);
                if (!fileNoeuds.contains(noeud)){
                    noeud.setPosition(position);
                    fileNoeuds.add(noeud);
                }

            }
        }
        return resultat;
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

    //Filtre le résultat de la recherche selon le numéro de la profondeur (à quel point...)
    public HashSet<String> rechercheNiveauProfondeur(){
        HashSet<String> resultat = new HashSet<>();


        return resultat;
    }

}

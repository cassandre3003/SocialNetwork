package fr.ul.miage.SocialNetwork.graph;

import fr.ul.miage.SocialNetwork.recherche.Recherche;

import java.util.*;

public class Graph {
    private HashSet<Lien> liens;
    private HashSet<Noeud> noeuds;

    public Graph(HashSet<Lien> liens) {
        this.liens = liens;
    }

    public HashSet<Lien> getLiens() {
        return liens;
    }

    public void setLiens(HashSet<Lien> liens) {
        this.liens = liens;
    }

    public Graph(HashSet<Lien> liens, HashSet<Noeud> noeuds) {
        this.liens = liens;
        this.noeuds = noeuds;
    }

    public HashSet<Noeud> getNoeuds() {
        return noeuds;
    }

    public void setNoeuds(HashSet<Noeud> noeuds) {
        this.noeuds = noeuds;
    }

    public boolean exist(Noeud a) {
        for (Noeud noeud : noeuds) {
            if (noeud.getNom().equals(a.getNom())) {
                return true;
            }
        }
        return false;
    }

    public Noeud getNoeudByNom(String nom){
        for (Noeud noeud : noeuds) {
            if (noeud.getNom().equals(nom)) {
                return noeud;
            }
        }
        return null;
    }

    public HashSet<String> getTypesLiens(){
        HashSet<String> types = new HashSet<String>();
        liens.forEach(lien -> {
            if (!types.contains(lien.getType())){
                types.add(lien.getType());
            }
        });
        return types;
    }


    // les noms des noeuds pour l'affichage
    public HashSet<String> getNomNoeuds(){
        HashSet<String> noeudsString = new HashSet<String>();
        for (Noeud noeud : noeuds) {
            noeudsString.add(noeud.getNom());
        }
        return noeudsString;
    }

    public HashSet<String> getAttributsByIdNoeudEtTypeLien(String type, String id){
        HashSet<String> resultat = new HashSet<>();
        liens = getLiensByIdNoeud(id);
        liens.forEach(lien -> {
            if (lien.getType().equals(type)){
                lien.getAttributs().forEach(att -> {
                    resultat.add(att);
                });
            }
        });
        return resultat;
    }

    // liens par Noeud
    public HashSet<Lien> getLiensByNomNoeud(String nom){
        Noeud noeud = getNoeudByNom(nom);
        HashSet<Lien> resultat = new HashSet<Lien>();
        for (String s : noeud.getLiens()) {
            resultat.add(getLienById(s));
        }
        return resultat;
    }

    // liens par Noeud
    public HashSet<Lien> getLiensByIdNoeud(String id){
        Noeud noeud = getNoeudById(id);
        HashSet<Lien> resultat = new HashSet<Lien>();
        for (String s : noeud.getLiens()) {
            resultat.add(getLienById(s));
        }
        return resultat;
    }

    // pile de liens par Noeud
    public Stack<Lien> getPileLiensByIdNoeud(String id){
        Noeud noeud = getNoeudById(id);
        Stack<Lien> resultat = new Stack();
        for (String s : noeud.getLiens()) {
            resultat.push(getLienById(s));
        }
        return resultat;
    }


    // file de liens par Noeud
    public LinkedList<Lien> getFileLiensByIdNoeud(String id){
        Noeud noeud = getNoeudById(id);
        LinkedList<Lien> resultat = new LinkedList<Lien>();
        for (String s : noeud.getLiens()) {
            resultat.push(getLienById(s));
        }
        return resultat;
    }




    public HashSet<String> getNoeudsVoisinsById(Recherche recherche, ArrayList<String> liensMarques, String idNoeud){
        HashSet<String> resultattmp = new HashSet<String>();
        Recherche recherche2 = recherche;
        recherche2.setNoeudID(idNoeud);
        Noeud noeud = getNoeudById(idNoeud);
        HashSet<String> liens = noeud.getLiens();
        Lien lien;
        String tmp;
        for (String s : liens) {
            tmp = s;
            if (!liensMarques.contains(tmp)) {
                resultattmp.add(tmp);
            }
        }
        HashSet<String> resultat = new HashSet<>();
        for (String idLien : resultattmp) {
            lien = getLienById(idLien);
            if (recherche2.lienValide(lien)) {
                if (lien.getNoeudA().equals(idNoeud)) {
                    resultat.add(lien.getNoeudB());
                } else {
                    resultat.add(lien.getNoeudA());
                }
                liensMarques.add(lien.getId());
            }
        }
        return resultat;
    }

    // type de liens par Noeud
    public HashSet<String> typeLienParNomNoeud(String nomNoeud){
        HashSet<String> typesLiens = new HashSet<String>();
        String tmp;
        HashSet<Lien> ensLiens = getLiensByNomNoeud(nomNoeud);
        for (Lien lien : ensLiens) {
            tmp = lien.getType();
            typesLiens.add(tmp);
        }
        return typesLiens;
    }

    private boolean lienExist(HashSet<String> typesLiens, String tmp) {
        return false;
    }

    public Lien getLienById(String id) {
        for (Lien lien : liens) {
            if (lien.getId().equals(id)) {
                return lien;
            }
        }
        return null;
    }

    public Noeud getNoeudById(String id) {
        for (Noeud noeud : noeuds) {
            if (noeud.getId().equals(id)) {
                return noeud;
            }
        }
        return null;
    }




}

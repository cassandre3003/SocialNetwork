package fr.ul.miage.SocialNetwork.graph;

import java.util.HashSet;
import java.util.Iterator;

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


/*
    public HashSet<Noeud> getNoeuds(){
        HashSet<Noeud> noeuds = new HashSet<>();
        Iterator liensIt = liens.iterator();
        while (liensIt.hasNext()){
            Lien value = (Lien) liensIt.next();
            Noeud A = value.getNoeudA();
            Noeud B = value.getNoeudB();
            if (!exist(noeuds,A)){
                noeuds.add(A);
            }
            if (!exist(noeuds, B)){
                noeuds.add(B);
            }
        }
        return noeuds;
    }
*/
public boolean exist(Noeud a) {
    Iterator noeudsIt = noeuds.iterator();
    while (noeudsIt.hasNext()) {
        Noeud tmp = (Noeud) noeudsIt.next();
        if (tmp.getNom().equals(a.getNom())) {
            return true;
        }
    }
    return false;
}

    public Noeud getNoeudByNom(String nom){
        Iterator noeudsIt = noeuds.iterator();
        Noeud tmp;
        while (noeudsIt.hasNext()) {
            tmp = (Noeud) noeudsIt.next();
            if (tmp.getNom().equals(nom)) {
                return tmp;
            }
        }
        return null;
    }

    // les noms des noeuds pour l'affichage
    public HashSet<String> getNomNoeuds(){
        Noeud tmp;
        HashSet<String> noeudsString = new HashSet<String>();
        Iterator noeudsIt = noeuds.iterator();
        while (noeudsIt.hasNext()){
            tmp = (Noeud) noeudsIt.next();
            noeudsString.add(tmp.getNom());
        }
        return noeudsString;
    }



    // liens par Noeud
    public HashSet<Lien> getLiensByNomNoeud(String nom){
        Noeud noeud = getNoeudByNom(nom);
        String idLien;
        Lien lien;
        HashSet<Lien> resultat = new HashSet<Lien>();
        Iterator liensIt = noeud.getLiens().iterator();
        while (liensIt.hasNext()){
            idLien = (String) liensIt.next();
            lien = getLienById(idLien);
            resultat.add(lien);
        }
        return resultat;
    }

    // type de liens par Noeud
    public HashSet<String> typeLienParNomNoeud(String nomNoeud){
        HashSet<String> typesLiens = new HashSet<String>();
        String tmp;
        Lien lien;
        HashSet<Lien> ensLiens = getLiensByNomNoeud(nomNoeud);
        Iterator liensIterator = ensLiens.iterator();
        while (liensIterator.hasNext()){
            lien = (Lien) liensIterator.next();
            tmp = lien.getType();
            typesLiens.add(tmp);
        }
        return typesLiens;
    }

    private boolean lienExist(HashSet<String> typesLiens, String tmp) {
        return false;
    }

    public Lien getLienById(String id) {
        Iterator liensIt = liens.iterator();
        Lien tmp;
        while (liensIt.hasNext()) {
            tmp = (Lien) liensIt.next();
            if (tmp.getId().equals(id)) {
                return tmp;
            }
        }
        return null;
    }

    public Noeud getNoeudById(String id) {
        Iterator noeudIterator = noeuds.iterator();
        Noeud tmp;
        while (noeudIterator.hasNext()) {
            tmp = (Noeud) noeudIterator.next();
            if (tmp.getId().equals(id)) {
                return tmp;
            }
        }
        return null;
    }









}

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
        while (noeudsIt.hasNext()) {
            Noeud tmp = (Noeud) noeudsIt.next();
            if (tmp.getNom().equals(nom)) {
                return tmp;
            }
        }
        return null;
    }




}

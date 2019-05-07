package fr.ul.miage.SocialNetwork.graph;

import java.util.HashSet;
import java.util.Iterator;

public class Graph {
    private HashSet<Lien> liens;

    public Graph(HashSet<Lien> liens) {
        this.liens = liens;
    }

    public HashSet<Lien> getLiens() {
        return liens;
    }

    public void setLiens(HashSet<Lien> liens) {
        this.liens = liens;
    }

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

    private boolean exist(HashSet<Noeud> noeuds, Noeud a) {
        Iterator noeudsIt = noeuds.iterator();
        while (noeudsIt.hasNext()){
            Noeud tmp = (Noeud) noeudsIt.next();
            if (tmp.getNom().equals(a.getNom())){
                return true;
            }
        }
        return false;
    }
}

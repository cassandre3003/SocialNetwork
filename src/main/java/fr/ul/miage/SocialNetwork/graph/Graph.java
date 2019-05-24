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

    public HashSet<String> getTypesLiens(){
        Iterator liensIt = liens.iterator();
        HashSet<String> types = new HashSet<String>();
        while (liensIt.hasNext()) {
            Lien tmp = (Lien) liensIt.next();
            if (!types.contains(tmp.getType())){
                types.add(tmp.getType());
            }
        }
        return types;
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

    // liens par Noeud
    public HashSet<Lien> getLiensByIdNoeud(String id){
        Noeud noeud = getNoeudById(id);
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

    // pile de liens par Noeud
    public Stack<Lien> getPileLiensByIdNoeud(String id){
        Noeud noeud = getNoeudById(id);
        String idLien;
        Lien lien;
        Stack<Lien> resultat = new Stack();
        Iterator liensIt = noeud.getLiens().iterator();
        while (liensIt.hasNext()){
            idLien = (String) liensIt.next();
            lien = getLienById(idLien);
            resultat.push(lien);
        }
        return resultat;
    }


    // file de liens par Noeud
    public LinkedList<Lien> getFileLiensByIdNoeud(String id){
        Noeud noeud = getNoeudById(id);
        String idLien;
        Lien lien;
        LinkedList<Lien> resultat = new LinkedList<Lien>();
        Iterator liensIt = noeud.getLiens().iterator();
        while (liensIt.hasNext()){
            idLien = (String) liensIt.next();
            lien = getLienById(idLien);
            resultat.push(lien);
        }
        return resultat;
    }




    public HashSet<String> getNoeudsVoisinsById(Recherche recherche, ArrayList<String> liensMarques, String idNoeud){
        HashSet<String> resultattmp = new HashSet<String>();
        Recherche recherche2 = recherche;
        recherche2.setNoeudID(idNoeud);
        Noeud noeud = getNoeudById(idNoeud);
        Lien lien;
        HashSet<String> liens = noeud.getLiens();
        String tmp;
        Iterator it = liens.iterator();
        while (it.hasNext()){
            tmp = (String) it.next();
            if (!liensMarques.contains(tmp)){
                resultattmp.add(tmp);
            }
        }
        it = resultattmp.iterator();
        HashSet<String> resultat = new HashSet<>();
        while (it.hasNext()){
            lien = getLienById((String) it.next());
            if(recherche2.lienValide(lien)){
                if (lien.getNoeudA().equals(idNoeud)){
                    resultat.add(lien.getNoeudB());
                }else {
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

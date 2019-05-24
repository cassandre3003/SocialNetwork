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

<<<<<<< HEAD
    public Noeud getNoeudByNom(String nom) {
        Iterator noeudsIt = noeuds.iterator();
        Noeud tmp;
        while (noeudsIt.hasNext()) {
            tmp = (Noeud) noeudsIt.next();
            if (tmp.getNom().equals(nom)) {
                return tmp;
=======
    public Noeud getNoeudByNom(String nom){
        for (Noeud noeud : noeuds) {
            if (noeud.getNom().equals(nom)) {
                return noeud;
>>>>>>> 2c66d7f5a28a90986884031361a7c8efc3e9ffcd
            }
        }
        return null;
    }

<<<<<<< HEAD
    public HashSet<String> getTypesLiens() {
        Iterator liensIt = liens.iterator();
        HashSet<String> types = new HashSet<String>();
        while (liensIt.hasNext()) {
            Lien tmp = (Lien) liensIt.next();
            if (!types.contains(tmp.getType())) {
                types.add(tmp.getType());
=======
    public HashSet<String> getTypesLiens(){
        HashSet<String> types = new HashSet<String>();
        liens.forEach(lien -> {
            if (!types.contains(lien.getType())){
                types.add(lien.getType());
>>>>>>> 2c66d7f5a28a90986884031361a7c8efc3e9ffcd
            }
        });
        return types;
    }


    // les noms des noeuds pour l'affichage
<<<<<<< HEAD
    public HashSet<String> getNomNoeuds() {
        Noeud tmp;
        HashSet<String> noeudsString = new HashSet<String>();
        Iterator noeudsIt = noeuds.iterator();
        while (noeudsIt.hasNext()) {
            tmp = (Noeud) noeudsIt.next();
            noeudsString.add(tmp.getNom());
=======
    public HashSet<String> getNomNoeuds(){
        HashSet<String> noeudsString = new HashSet<String>();
        for (Noeud noeud : noeuds) {
            noeudsString.add(noeud.getNom());
>>>>>>> 2c66d7f5a28a90986884031361a7c8efc3e9ffcd
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
    public HashSet<Lien> getLiensByNomNoeud(String nom) {
        Noeud noeud = getNoeudByNom(nom);
        HashSet<Lien> resultat = new HashSet<Lien>();
<<<<<<< HEAD
        Iterator liensIt = noeud.getLiens().iterator();
        while (liensIt.hasNext()) {
            idLien = (String) liensIt.next();
            lien = getLienById(idLien);
            resultat.add(lien);
=======
        for (String s : noeud.getLiens()) {
            resultat.add(getLienById(s));
>>>>>>> 2c66d7f5a28a90986884031361a7c8efc3e9ffcd
        }
        return resultat;
    }

    // liens par Noeud
    public HashSet<Lien> getLiensByIdNoeud(String id) {
        Noeud noeud = getNoeudById(id);
        HashSet<Lien> resultat = new HashSet<Lien>();
<<<<<<< HEAD
        Iterator liensIt = noeud.getLiens().iterator();
        while (liensIt.hasNext()) {
            idLien = (String) liensIt.next();
            lien = getLienById(idLien);
            resultat.add(lien);
=======
        for (String s : noeud.getLiens()) {
            resultat.add(getLienById(s));
>>>>>>> 2c66d7f5a28a90986884031361a7c8efc3e9ffcd
        }
        return resultat;
    }

    // pile de liens par Noeud
    public Stack<Lien> getPileLiensByIdNoeud(String id) {
        Noeud noeud = getNoeudById(id);
        Stack<Lien> resultat = new Stack();
<<<<<<< HEAD
        Iterator liensIt = noeud.getLiens().iterator();
        while (liensIt.hasNext()) {
            idLien = (String) liensIt.next();
            lien = getLienById(idLien);
            resultat.push(lien);
=======
        for (String s : noeud.getLiens()) {
            resultat.push(getLienById(s));
>>>>>>> 2c66d7f5a28a90986884031361a7c8efc3e9ffcd
        }
        return resultat;
    }


    // file de liens par Noeud
    public LinkedList<Lien> getFileLiensByIdNoeud(String id) {
        Noeud noeud = getNoeudById(id);
        LinkedList<Lien> resultat = new LinkedList<Lien>();
<<<<<<< HEAD
        Iterator liensIt = noeud.getLiens().iterator();
        while (liensIt.hasNext()) {
            idLien = (String) liensIt.next();
            lien = getLienById(idLien);
            resultat.push(lien);
=======
        for (String s : noeud.getLiens()) {
            resultat.push(getLienById(s));
>>>>>>> 2c66d7f5a28a90986884031361a7c8efc3e9ffcd
        }
        return resultat;
    }

<<<<<<< HEAD
    public HashSet<String> getNoeudsVoisinsById(Recherche recherche, ArrayList<String> liensMarques) {
        String idNoeud = recherche.getNoeudID();
=======



    public HashSet<String> getNoeudsVoisinsById(Recherche recherche, ArrayList<String> liensMarques, String idNoeud){
>>>>>>> 2c66d7f5a28a90986884031361a7c8efc3e9ffcd
        HashSet<String> resultattmp = new HashSet<String>();
        Recherche recherche2 = recherche;
        recherche2.setNoeudID(idNoeud);
        Noeud noeud = getNoeudById(idNoeud);
        HashSet<String> liens = noeud.getLiens();
        Lien lien;
        String tmp;
<<<<<<< HEAD
        Iterator it = liens.iterator();
        while (it.hasNext()) {
            tmp = (String) it.next();
=======
        for (String s : liens) {
            tmp = s;
>>>>>>> 2c66d7f5a28a90986884031361a7c8efc3e9ffcd
            if (!liensMarques.contains(tmp)) {
                resultattmp.add(tmp);
            }
        }
        HashSet<String> resultat = new HashSet<>();
<<<<<<< HEAD
        while (it.hasNext()) {
            lien = getLienById((String) it.next());
            if (recherche.lienValide(lien)) {
=======
        for (String idLien : resultattmp) {
            lien = getLienById(idLien);
            if (recherche2.lienValide(lien)) {
>>>>>>> 2c66d7f5a28a90986884031361a7c8efc3e9ffcd
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
    public HashSet<String> typeLienParNomNoeud(String nomNoeud) {
        HashSet<String> typesLiens = new HashSet<String>();
        String tmp;
        HashSet<Lien> ensLiens = getLiensByNomNoeud(nomNoeud);
<<<<<<< HEAD
        Iterator liensIterator = ensLiens.iterator();
        while (liensIterator.hasNext()) {
            lien = (Lien) liensIterator.next();
=======
        for (Lien lien : ensLiens) {
>>>>>>> 2c66d7f5a28a90986884031361a7c8efc3e9ffcd
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

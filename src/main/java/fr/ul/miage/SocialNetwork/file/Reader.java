package fr.ul.miage.SocialNetwork.file;

import fr.ul.miage.SocialNetwork.graph.Graph;
import fr.ul.miage.SocialNetwork.graph.Lien;
import fr.ul.miage.SocialNetwork.graph.Noeud;

import java.io.*;
import java.util.HashSet;
import java.util.regex.*;

public class Reader {
    private final String FILENAME = "/fichier-donnees.txt";
    private File file = new File(getClass().getResource(FILENAME).getFile());
    private BufferedReader br = new BufferedReader(new FileReader(file.toString()));
    private static final String MOT = "(\\w|é|è|à| |'|,|-)";
    private static final String LIGNE = "^" +
           MOT +
            "+--" +
           MOT +
            "+\\[" +
           MOT +
            "+\\=" +
           MOT +
            "+(;" +
           MOT +
            "+" +
           MOT +
            "+=" +
           MOT +
            "+)*\\]--[<|>]?" +
           MOT +
            "+$";
    private static final Pattern pattern = Pattern.compile(LIGNE);

    /*private static final Pattern pattern = Pattern.compile("^" +
            "(\\w|\\é|\\è|\\à|\\ |\\'|\\,|\\-)" +
            "+--" +
            "(\\w|\\é|\\è|\\à|\\ |\\'|\\,|\\-)" +
            "+\\[(\\w|\\é|\\è|\\à|\\ |\\'|\\,|\\-)" +
            "+\\=" +
            "(\\w|\\é|\\è|\\à|\\ |\\'|\\,|\\-)" +
            "+(\\;" +
            "(\\w|\\é|\\è|\\à|\\ |\\'|\\,|\\-)" +
            "+" +
            "(\\w|\\é|\\è|\\à|\\ |\\'|\\,|\\-)" +
            "+\\=" +
            "(\\w|\\é|\\è|\\à|\\ |\\'|\\,|\\-)" +
            "+)*\\]--[\\<|\\>]?" +
            "(\\w|\\é|\\è|\\à|\\ |\\'|\\,|\\-)" +
            "+$");*/

    public Reader() throws FileNotFoundException {}

    public String getFILENAME() {
        return FILENAME;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Boolean ligneValide(String ligne){
        Matcher m = pattern.matcher(ligne);
        return m.matches();
    }
    
    public Boolean fichierValide() throws IOException {
        String ligne;
        while ( (ligne = br.readLine()) != null){
            if (!ligneValide(ligne)){
                return false;
            }
        }

        return true;
    }

    public Graph creerGraph() throws IOException {
        Graph graph = new Graph(new HashSet<Lien>(), new HashSet<Noeud>());
        Noeud noeud;
        Noeud tmp;
        Lien lien;
        String ligne;
        //if (fichierValide()) {
            while ((ligne = br.readLine()) != null) {
                Interpreteur interpreteur = new Interpreteur(ligne);
                lien = new Lien(interpreteur.getLien(), String.valueOf(graph.getLiens().size() + 1));
                noeud = interpreteur.getNoeudA();
                if (!graph.exist(noeud)) {
                    noeud.setId(String.valueOf(graph.getNoeuds().size() + 1));
                    noeud.getLiens().add(lien.getId());
                    graph.getNoeuds().add(noeud);
                    lien.setNoeudA(noeud.getId());
                } else {
                    tmp = graph.getNoeudByNom(noeud.getNom());
                    graph.getNoeuds().remove(tmp);
                    tmp.getLiens().add(lien.getId());
                    lien.setNoeudA(tmp.getId());
                    graph.getNoeuds().add(tmp);
                }
                noeud = interpreteur.getNoeudB();
                if (!graph.exist(noeud)) {
                    noeud.setId(String.valueOf(graph.getNoeuds().size() + 1));
                    noeud.getLiens().add(lien.getId());
                    graph.getNoeuds().add(noeud);
                    lien.setNoeudB(noeud.getId());
                } else {
                    tmp = graph.getNoeudByNom(noeud.getNom());
                    graph.getNoeuds().remove(tmp);
                    tmp.getLiens().add(lien.getId());
                    graph.getNoeuds().add(tmp);
                    lien.setNoeudB(tmp.getId());
                }
                graph.getLiens().add(lien);
            }
        //}
        return graph;
    }
}

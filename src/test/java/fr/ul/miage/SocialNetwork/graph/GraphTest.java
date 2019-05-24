package fr.ul.miage.SocialNetwork.graph;

import fr.ul.miage.SocialNetwork.file.Reader;
import fr.ul.miage.SocialNetwork.recherche.Recherche;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphTest {
    Reader reader = new Reader();
    Graph graph = reader.creerGraph();

    public GraphTest() throws IOException {
    }

    @Test
    public void getNomNoeudsTest() throws IOException {
        GraphTest test = new GraphTest();
        assertEquals(21, test.graph.getNomNoeuds().size());
    }

    @Test
    public void getLiensByNomNoeudTest() throws IOException {
        GraphTest test = new GraphTest();;
        assertEquals(7,test.graph.getLiensByNomNoeud("Claude").size());
    }

    @Test
    public void getTypesLiensByNomNoeudTest() throws IOException {
        GraphTest test = new GraphTest();
        assertEquals(2,test.graph.typeLienParNomNoeud("Claude").size());
    }

    /*@Test
    public void getNoeudsTest(){
        HashSet<Lien> liens = new HashSet<Lien>();
        Interpreteur interpreteur = new Interpreteur(ligne1);
        liens.add(interpreteur.getLien());
        interpreteur.setLigne(ligne2);
        liens.add(interpreteur.getLien());
        interpreteur.setLigne(ligne3);
        liens.add(interpreteur.getLien());
        interpreteur.setLigne(ligne4);
        liens.add(interpreteur.getLien());
        interpreteur.setLigne(ligne5);
        liens.add(interpreteur.getLien());
        interpreteur.setLigne(ligne6);
        liens.add(interpreteur.getLien());
        Graph graph = new Graph(liens,noeuds);
        assertEquals(7,graph.getNoeuds().size());
    }*/


    @Test
    public void getTypesLiensTest() throws IOException {
        Reader reader = new Reader();
        Graph graph = reader.creerGraph();
        assertEquals(6, graph.getTypesLiens().size());
    }

    @Test
    public void getNoeudsVoisinsById() throws IOException {
        Reader reader = new Reader();
        Graph graph = reader.creerGraph();
        ArrayList<String> marques = new ArrayList<>();
        marques.add("1");
        Recherche recherche = new Recherche("AB","1","teacher");
        // Le noeud avec l'id 1 possède a 7 liens en tout, dont 6 liens valides, mais comme le lien avec l'id 1 est marqué, alors on en 5
        //assertEquals(5,graph.getNoeudsVoisinsById(recherche, marques).size());
        assertEquals(marques.size(), 7);
    }

}

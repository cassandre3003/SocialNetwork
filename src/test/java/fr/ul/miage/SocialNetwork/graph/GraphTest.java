package fr.ul.miage.SocialNetwork.graph;

<<<<<<< HEAD
import fr.ul.miage.SocialNetwork.file.Interpreteur;
import fr.ul.miage.SocialNetwork.file.Reader;
=======
import fr.ul.miage.SocialNetwork.file.*;
>>>>>>> 156c18a477c56fea6a0a20567c2c465b6f62d94c
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;

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
        assertEquals(6,graph.getTypesLiens().size());
    }

}

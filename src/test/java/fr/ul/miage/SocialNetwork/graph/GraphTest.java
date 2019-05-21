package fr.ul.miage.SocialNetwork.graph;

import fr.ul.miage.SocialNetwork.file.Interpreteur;
import fr.ul.miage.SocialNetwork.file.Reader;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphTest {
    String ligne1 = "Claude--teacher[since=2016]-->Pierre";
    String ligne2 = "Claude--teacher[since=2016]-->Laure";
    String ligne3 = "Claude--teacher[since=2016]-->Karima";
    String ligne4 = "Claude--teacher[since=2016]-->Ahmed";
    String ligne5 = "Claude--teacher[since=2016]-->Cassandra";
    String ligne6 = "Claude--teacher[since=2016]-->Patricia";

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

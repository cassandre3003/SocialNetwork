package fr.ul.miage.SocialNetwork.recherche;

import fr.ul.miage.SocialNetwork.file.Reader;
import fr.ul.miage.SocialNetwork.graph.Graph;
import fr.ul.miage.SocialNetwork.graph.Lien;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RechercheTest {


    @Test
    public void lienValideTest(){
        Recherche recherche = new Recherche();
        recherche.setDirection("AB");
        recherche.setTypeLien("friend");
        Lien lien = new Lien("AB", "friend");
        assertTrue(recherche.lienValide(lien));
    }

    @Test
    public void lienNonValideTest(){
        Recherche recherche = new Recherche();
        recherche.setDirection("BA");
        recherche.setTypeLien("friend");
        Lien lien = new Lien("AB", "friend");
        assertTrue(recherche.lienValide(lien));
    }

    @Test
    public void rechercheProfondeurNoeudTest() throws IOException {  // Refaire une VRAI test !
        Recherche recherche = new Recherche();
        Reader reader = new Reader();
        Graph graphique = reader.creerGraph();
        recherche.setTypeLien("type");
        recherche.setDirection("BA");
        recherche.setNoeudID(graphique.getNoeudByNom("Databases").getId());
        System.out.println(recherche.rechercheParcoursProfondeurNoeud(graphique).toString());

    }
}

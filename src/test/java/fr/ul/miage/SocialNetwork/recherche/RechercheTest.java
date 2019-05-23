package fr.ul.miage.SocialNetwork.recherche;

import fr.ul.miage.SocialNetwork.file.Reader;
import fr.ul.miage.SocialNetwork.graph.Graph;
import fr.ul.miage.SocialNetwork.graph.Lien;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class RechercheTest {


    @Test
    public void lienValideTest(){
        Recherche recherche = new Recherche();
        recherche.setDirection("AB");
        recherche.setTypeLien("friend");
        recherche.setNoeudID("1");
        Lien lien = new Lien("1","2","AB", "friend");
        assertTrue(recherche.lienValide(lien));
    }

    @Test
    public void lienNonValideTest(){
        Recherche recherche = new Recherche();
        recherche.setDirection("BA");
        recherche.setTypeLien("friend");
        recherche.setNoeudID("1");
        Lien lien = new Lien("1", "2","AB","friend");
        assertFalse(recherche.lienValide(lien));
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

    @Test
    public void rechercheLargeurNoeudTest() throws IOException {
        Recherche recherche = new Recherche();
        Reader reader = new Reader();
        Graph graphique = reader.creerGraph();
        recherche.setTypeLien("teacher");
        recherche.setDirection("AB");
        recherche.setNoeudID(graphique.getNoeudByNom("Claude").getId());
        System.out.println(recherche.rechercheParcoursLargeurNoeud(graphique).toString());
    }

    @Test
    public void rechercheProfondeurLienTest() throws IOException {
        Recherche recherche = new Recherche();
        Reader reader = new Reader();
        Graph graphique = reader.creerGraph();
        recherche.setTypeLien("teacher");
        recherche.setDirection("AB");
        recherche.setNoeudID(graphique.getNoeudByNom("Claude").getId());
        assertEquals(6,recherche.rechercheParcoursProfondeurLien(graphique).size());
    }

    @Test
    public void rechercheLargeurLienTest() throws IOException {
        Recherche recherche = new Recherche();
        Reader reader = new Reader();
        Graph graphique = reader.creerGraph();
        recherche.setNoeudID(graphique.getNoeudByNom("Refactoring").getId());
        recherche.setTypeLien("author");
        recherche.setDirection("AB");
        assertEquals(6,recherche.rechercheParcoursLargeurLien(graphique).size());
    }

    @Test
    public void rechercheTest() throws IOException {
        Recherche recherche = new Recherche();
        Reader reader = new Reader();
        Graph graphique = reader.creerGraph();
    }
}

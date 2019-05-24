package fr.ul.miage.SocialNetwork.recherche;

import fr.ul.miage.SocialNetwork.file.Reader;
import fr.ul.miage.SocialNetwork.graph.Graph;
import fr.ul.miage.SocialNetwork.graph.Lien;
import org.junit.jupiter.api.Test;

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
        recherche.setDirection("AB");
        recherche.setTypeLien("teacher");
        recherche.setNoeudID("5");
        Lien lien = new Lien("12", "5","AB","teacher");
        assertFalse(recherche.lienValide(lien));
        assertTrue(recherche.lienValide(lien));
    }

    @Test
    public void rechercheProfondeurNoeudTest() throws IOException {  // Refaire une VRAI test !
        Recherche recherche = new Recherche();
        Reader reader = new Reader();
        Graph graphique = reader.creerGraph();
        recherche.setTypeLien("teacher");
        recherche.setDirection("AB");
        recherche.setProfondeur(1);
        recherche.setNoeudID(graphique.getNoeudByNom("Claude").getId());
        System.out.println(recherche.rechercheParcoursProfondeurNoeud(graphique).toString());

    }


    @Test
    public void rechercheLargeurNoeudTest() throws IOException {
        Recherche recherche = new Recherche();
        Reader reader = new Reader();
        Graph graphique = reader.creerGraph();
        recherche.setTypeLien("teacher");
        recherche.setDirection("AB");
        recherche.setProfondeur(2);
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
        recherche.setProfondeur(3);
        recherche.setNoeudID(graphique.getNoeudByNom("Claude").getId());
        assertEquals(13,recherche.rechercheParcoursProfondeurLien(graphique).size());
    }

    @Test
    public void rechercheLargeurLienTest() throws IOException {
        Recherche recherche = new Recherche();
        Reader reader = new Reader();
        Graph graphique = reader.creerGraph();
        recherche.setNoeudID(graphique.getNoeudByNom("Claude").getId());
        recherche.setTypeLien("teacher");
        recherche.setDirection("AB");
        recherche.setProfondeur(1);
        assertEquals(6,recherche.rechercheParcoursLargeurLien(graphique).size());
    }

    @Test
    public void rechercheTest() throws IOException {
        Recherche recherche = new Recherche();
        Reader reader = new Reader();
        Graph graphique = reader.creerGraph();
    }
}

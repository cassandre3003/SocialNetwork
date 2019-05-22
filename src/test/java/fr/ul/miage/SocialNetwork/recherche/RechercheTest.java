package fr.ul.miage.SocialNetwork.recherche;

import fr.ul.miage.SocialNetwork.graph.Lien;
import org.junit.jupiter.api.Test;

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
}

package fr.ul.miage.SocialNetwork.file;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class InterpreteurTest {

    private final String LIGNESIMPLE = "Elisabeth--likes[since=2018]--Elasticsearch";
    private final String LIGNECOMPLEXE = "Joseph H--employee[role=teacher;hired=Jan-2003]-->IDMC";


    @Test
    public void getNoeudATest(){
        Interpreteur interpreteurSimple = new Interpreteur(LIGNESIMPLE);
        Interpreteur interpreteurComplexe = new Interpreteur(LIGNECOMPLEXE);

        assertEquals("Elisabeth",interpreteurSimple.getNoeudA().getNom());
        assertEquals("Joseph H", interpreteurComplexe.getNoeudA().getNom());
    }

    @Test
    public void getNoeudBTest(){
        Interpreteur interpreteurSimple = new Interpreteur(LIGNESIMPLE);
        Interpreteur interpreteurComplexe = new Interpreteur(LIGNECOMPLEXE);

        assertEquals("Elasticsearch",interpreteurSimple.getNoeudB().getNom());
        assertEquals("IDMC", interpreteurComplexe.getNoeudB().getNom());
    }

    @Test void getLiensTest(){
        Interpreteur interpreteurSimple = new Interpreteur(LIGNESIMPLE);
        Interpreteur interpreteurComplexe = new Interpreteur(LIGNECOMPLEXE);

        HashSet<String> simple = new HashSet<String>();
        simple.add("since=2018");
        HashSet<String> complexe = new HashSet<String>();
        complexe.add("role=teacher");
        complexe.add("hired=Jan-2003");

        assertEquals("Elisabeth", interpreteurSimple.getLien().getNoeudA().getNom());
        assertEquals("Elasticsearch", interpreteurSimple.getLien().getNoeudB().getNom());
        assertEquals("likes", interpreteurSimple.getLien().getType());
        assertEquals(simple, interpreteurSimple.getLien().getAttributs());

        assertEquals("Joseph H", interpreteurComplexe.getLien().getNoeudA().getNom());
        assertEquals("IDMC", interpreteurComplexe.getLien().getNoeudB().getNom());
        assertEquals("employee", interpreteurComplexe.getLien().getType());
        assertEquals(complexe, interpreteurComplexe.getLien().getAttributs());
    }
}

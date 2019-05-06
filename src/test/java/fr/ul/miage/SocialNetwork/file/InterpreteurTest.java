package fr.ul.miage.SocialNetwork.file;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InterpreteurTest {

    private final String LIGNESIMPLE = "Elisabeth--likes[since=2018]--Elasticsearch";
    private final String LIGNECOMPLEXE = "Joseph H--employee[role=teacher;hired=Jan-2003]-->IDMC";


    @Test
    public void getNoeudATest(){
        Interpreteur interpreteurSimple = new Interpreteur(LIGNESIMPLE);
        Interpreteur interpreteurComplexee = new Interpreteur(LIGNECOMPLEXE);

        assertEquals("Elisabeth",interpreteurSimple.getNoeudA());
        assertEquals("Joseph H", interpreteurComplexee.getNoeudA());
    }

    @Test
    public void getNoeudBTest(){
        Interpreteur interpreteurSimple = new Interpreteur(LIGNESIMPLE);
        Interpreteur interpreteurComplexee = new Interpreteur(LIGNECOMPLEXE);

        assertEquals("Elasticsearch",interpreteurSimple.getNoeudB());
        assertEquals("IDMC", interpreteurComplexee.getNoeudB());
    }

    @Test void getLiensTest(){
        
    }
}

package fr.ul.miage.SocialNetwork.file;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ReaderTest {

    @Test
    void fileExistTest() throws IOException {
        Reader reader = new Reader();
        File file = reader.getFile();
        assertTrue(file.exists());
    }


    @Test
    void ligneSimpleValideTest() throws FileNotFoundException {
        Reader reader = new Reader();
        assertTrue(reader.ligneValide("Claude--teacher[since=2016]-->Pierre"));
    }

    @Test
    void ligneComplexeValideTest() throws FileNotFoundException {
        Reader reader = new Reader();
        assertTrue(reader.ligneValide("Claude--friend[since=2016;shares=boo-ks]-->Pierre"));
    }

    @Test
    void ligneSimpleNonValideTest() throws FileNotFoundException {
        Reader reader = new Reader();
        assertFalse(reader.ligneValide("Claude--friend[since2016]-->Pierre"));
    }

    @Test
    void ligneComplexeNonValideTest() throws FileNotFoundException {
        Reader reader = new Reader();
        assertFalse(reader.ligneValide("Claude--friend[since=2016;sharesbooks]-->Pierre"));
    }

    @Test
    void fichierValideTest() throws IOException {
        Reader reader = new Reader();
        assertTrue(reader.fichierValide());
    }

    @Test
    void creerGraphLiensTest() throws IOException{
        Reader reader = new Reader();
        assertEquals(51, reader.creerGraph().getLiens().size());
    }

    @Test
    void creerGraphNoeudTest() throws IOException{
        Reader reader = new Reader();
        assertEquals(21, reader.creerGraph().getNoeuds().size());
    }
}

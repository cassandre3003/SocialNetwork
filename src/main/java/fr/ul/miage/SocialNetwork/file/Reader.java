package fr.ul.miage.SocialNetwork.file;

import java.io.*;
import java.util.regex.*;

public class Reader {
    private final String FILENAME = "/fichier-donnees.txt";
    private File file = new File(getClass().getResource(FILENAME).getFile());
    private BufferedReader br = new BufferedReader(new FileReader(file.toString()));
    private static final String MOT = "(\\w|é|è|à| |'|,|-)";
    private static final String LIGNE = "^" +
           MOT +
            "+--" +
           MOT +
            "+\\[" +
           MOT +
            "+\\=" +
           MOT +
            "+(;" +
           MOT +
            "+" +
           MOT +
            "+=" +
           MOT +
            "+)*\\]--[<|>]?" +
           MOT +
            "+$";
    private static final Pattern pattern = Pattern.compile(LIGNE);

    /*private static final Pattern pattern = Pattern.compile("^" +
            "(\\w|\\é|\\è|\\à|\\ |\\'|\\,|\\-)" +
            "+--" +
            "(\\w|\\é|\\è|\\à|\\ |\\'|\\,|\\-)" +
            "+\\[(\\w|\\é|\\è|\\à|\\ |\\'|\\,|\\-)" +
            "+\\=" +
            "(\\w|\\é|\\è|\\à|\\ |\\'|\\,|\\-)" +
            "+(\\;" +
            "(\\w|\\é|\\è|\\à|\\ |\\'|\\,|\\-)" +
            "+" +
            "(\\w|\\é|\\è|\\à|\\ |\\'|\\,|\\-)" +
            "+\\=" +
            "(\\w|\\é|\\è|\\à|\\ |\\'|\\,|\\-)" +
            "+)*\\]--[\\<|\\>]?" +
            "(\\w|\\é|\\è|\\à|\\ |\\'|\\,|\\-)" +
            "+$");*/

    public Reader() throws FileNotFoundException {}

    public String getFILENAME() {
        return FILENAME;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Boolean ligneValide(String ligne){
        Matcher m = pattern.matcher(ligne);
        return m.matches();
    }
    
    public Boolean fichierValide() throws IOException {
        String ligne;
        while ( (ligne = br.readLine()) != null){
            if (!ligneValide(ligne)){
                return false;
            }
        }
        return true;
    }
}

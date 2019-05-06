package fr.ul.miage.SocialNetwork.file;


/*
* Notre ligne est déjà vérifiée... il ne peut pas y avoir d'erreur de construction
*/

public class Interpreteur {
    private String ligne;

    public Interpreteur(String ligne) {
        this.ligne = ligne;
    }

    public String getLigne() {
        return ligne;
    }

    public void setLigne(String ligne) {
        this.ligne = ligne;
    }

    public String getNoeudA(){
        int fin = ligne.indexOf("-");
        return ligne.substring(0,fin);
    }

    public String getNoeudB() {
        int debut;
        if (ligne.contains("<")){
            debut = ligne.lastIndexOf("<");
        }else if (ligne.contains(">")){
            debut = ligne.lastIndexOf(">");
        }else {
            debut = ligne.lastIndexOf("-");
        }
        return ligne.substring(debut+1);
    }
}

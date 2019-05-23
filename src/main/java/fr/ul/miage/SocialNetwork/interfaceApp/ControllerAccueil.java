package fr.ul.miage.SocialNetwork.interfaceApp;

import fr.ul.miage.SocialNetwork.file.Reader;
import fr.ul.miage.SocialNetwork.graph.Graph;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;

public class ControllerAccueil implements Initializable {
    @FXML
    private ComboBox lien;
    @FXML
    private ComboBox listeDeDepart;
    @FXML
    private ComboBox parcours;
    @FXML
    private ComboBox level;
    @FXML
    private ComboBox unicite;
    @FXML
    private Button recherche;
    @FXML
    private Label resultat;


    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Reader reader = new Reader();
            Graph graphe = reader.creerGraph();

            //ajout des options par défaut et d'éléments ne nécessitant aucun traitement particulier
            parcours.getItems().addAll("Longueur d'abord", "Largeur d'abord");
            unicite.getItems().addAll("NoeudGlobal", "RelationGlobal");
            listeDeDepart.getItems().add("Choisir un nom");
            level.getItems().add("0 (par défaut)");

            //ajout de l'intégralité des noms présents ds le fichier
            for (String str : graphe.getNomNoeuds()) {
                listeDeDepart.getItems().add(str);
            }

            /* ajout du nombre pour  la profondeur
             * ce nombre est obtenu à partir du nombre total de liens,
             * la profondeur ne pourra donc pas dépasser ce nombre
            */
            for (int i=0; i< graphe.getLiens().size();i++){
                level.getItems().add(i);
            }
            //affichage de l'option par défaut
            parcours.setValue(parcours.getItems().get(0));
            unicite.setValue(unicite.getItems().get(0));
            level.setValue(level.getItems().get(0));
            listeDeDepart.setValue(listeDeDepart.getItems().get(0));

            listeDeDepart.valueProperty().addListener((ChangeListener<String>) (arg0, oldvalue, newvalue) -> {
                lien.getItems().clear();
                for (String ch : graphe.typeLienParNomNoeud(newvalue)) {
                    lien.getItems().add(ch);
                }
                lien.setValue(lien.getItems().get(0));
            });


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void search(ActionEvent e) throws IOException {
        /*Reader reader = new Reader();
        Graph graphe = reader.creerGraph();
        lien.getSelectionModel().getSelectedItem().toString();  //pour récupérer le lien
        listeDeDepart.getSelectionModel().getSelectedItem().toString();
        level.getSelectionModel().getSelectedItem().toString();
        unicite.getSelectionModel().getSelectedItem().toString();
        parcours.getSelectionModel().getSelectedItem().toString();

*/

        //à partir de la fonction de Ouaoua et Cassou
        String [] elements = new String[] { "E", "F" };
        HashSet<String> res = new HashSet<String>(Arrays.asList(elements));
        String chaine ="";
        for ( String str :  res){
             chaine = chaine + str +"\n";
        }
        resultat.setText(chaine);

    }
}
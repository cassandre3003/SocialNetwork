package fr.ul.miage.SocialNetwork.interfaceApp;

import fr.ul.miage.SocialNetwork.file.Reader;
import fr.ul.miage.SocialNetwork.graph.Graph;
import fr.ul.miage.SocialNetwork.graph.Lien;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;

public class ControllerAccueil implements Initializable {
    @FXML
    private ChoiceBox lien;
    @FXML
    private ChoiceBox listeDeDepart;
    @FXML
    private ChoiceBox parcours;
    @FXML
    private ChoiceBox level;
    @FXML
    private ChoiceBox unicite;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        try {
            parcours.getItems().addAll("En longueur d'abord","En largeur d'abord");
            unicite.getItems().addAll("NoeudGlobal", "relationGlobal");


            Reader reader = new Reader();
            Graph graphe  =  reader.creerGraph();

            for (String str : graphe.getNomNoeuds()) {
                listeDeDepart.getItems().add(str);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void adapteLien (ActionEvent e) throws IOException{
        Reader reader = new Reader();
        Graph graphe  =  reader.creerGraph();
        System.out.println(listeDeDepart.getSelectionModel().getSelectedItem().toString());
        for (String ch : graphe.typeLienParNomNoeud("Cassandra")) {
            lien.getItems().add(ch);
        }


    }

}

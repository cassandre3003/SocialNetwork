package fr.ul.miage.SocialNetwork.interfaceApp;


import fr.ul.miage.SocialNetwork.file.Reader;
import fr.ul.miage.SocialNetwork.graph.Graph;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAccueil  implements Initializable {
    @FXML
    private ChoiceBox lien;
    @FXML
    private ChoiceBox listeDeDepart;
    @FXML
    private ListView parcours;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        Reader reader = null;
        try {
            reader = new Reader();
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

    public void adapteLien (ActionEvent e) {


    }

}

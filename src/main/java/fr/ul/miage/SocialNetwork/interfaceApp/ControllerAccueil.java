package fr.ul.miage.SocialNetwork.interfaceApp;


import fr.ul.miage.SocialNetwork.file.Reader;
import fr.ul.miage.SocialNetwork.graph.Graph;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAccueil {
    @FXML
    private ListView<String> lien;
    @FXML
    private ListView<String> listeDeDépart;
    @FXML
    private ListView parcours;

    void initialize(URL url, ResourceBundle rb) throws IOException {
        Reader reader = new Reader();
        Graph graphe  =  reader.creerGraph();
        //lien.setItems(graphe.getLiens());
        System.out.println(lien.toString());
        listeDeDépart.getItems().add("Cassandre");
        listeDeDépart.getItems().add("Salem");
        listeDeDépart.getItems().add("Ouaoua");

    }
}

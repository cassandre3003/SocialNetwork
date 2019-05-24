package fr.ul.miage.SocialNetwork.interfaceApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    //methodes
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/Graphe.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Projet Genie Logiciel");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

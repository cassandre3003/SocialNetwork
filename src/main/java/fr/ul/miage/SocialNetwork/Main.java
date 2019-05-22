package fr.ul.miage.SocialNetwork;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        StackPane pane = new StackPane();
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
       // pane.getChildren().add(ControlerGraphe.getFXGraph(new Graph(new HashSet<>())));
        primaryStage.show();
    }
}

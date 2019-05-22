package fr.ul.miage.SocialNetwork.interfacegraphe;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

    public static void main(String[] args){
        launch(Main.class,args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/Graphe.fxml"));
        Parent parent = loader.load();
        ControlerGraphe controller = loader.getController();
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(event -> {
            if (!controller.getExecutorService().isShutdown() && !controller.getExecutorService().isTerminated())
            {
                controller.getExecutorService().shutdownNow();
            }
        });
        primaryStage.show();
    }

}

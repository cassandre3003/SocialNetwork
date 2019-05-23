package fr.ul.miage.SocialNetwork.interfacegraphe;

import fr.ul.miage.SocialNetwork.file.Reader;
import fr.ul.miage.SocialNetwork.graph.Graph;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import org.graphstream.graph.Edge;
import org.graphstream.graph.implementations.MultiGraph;

import org.graphstream.ui.view.Viewer;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;

public class ControlerGraphe implements Initializable
{

    @FXML
    public BorderPane pane;


    public void initialize(URL location, ResourceBundle resources) {
        Reader reader = null; //pour lire le fichier
        try {
            reader = new Reader();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Graph graphFichier = reader.creerGraph(); //pour créer le graphe a partir de la methode creerGraph
            //pane.setCenter(convertGraph(graphFichier));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

/*
    public Node convertGraph(Graph cGraph){
        SwingNode fxGraph = new SwingNode();
        org.graphstream.graph.Graph graph = new MultiGraph("test");
        /*Noeud claude = cGraph.getNoeudByNom("Claude");
        Noeud pierre = cGraph.getNoeudByNom("Pierre");
        Lien lien1 = cGraph.getLienById("1");

        org.graphstream.graph.Node claudeNode = graph.addNode(claude.getNom());
        org.graphstream.graph.Node pierreNode = graph.addNode(pierre.getNom());
        Edge pierreClaudeLink = graph.addEdge(lien1.getAttributs().toString(), claudeNode, pierreNode);
        claudeNode.addAttribute("ui.label", claudeNode.getId());
        pierreNode.addAttribute("ui.label", pierreNode.getId());
        pierreClaudeLink.addAttribute("ui.label", pierreClaudeLink.getId());


        HashSet<String> types = cGraph.getTypesLiens();
        float r;
        float g;
        float b;
        ArrayList<String> couleurs = new ArrayList<>();
        for (String type : types) {
            r = (float) Math.random();
            g = (float) Math.random();
            b = (float) Math.random();
            Color col = new Color(r,g,b);
            couleurs.add(type + ";rgb(" + col.getRed() + "," + col.getGreen() + "," + col.getBlue() + ")");

        }

        pane.setBottom(creationLegende(couleurs));

        cGraph.getNoeuds().forEach(noeud -> {
            org.graphstream.graph.Node node = graph.addNode(noeud.getId());
            node.addAttribute("ui.label",noeud.getNom());
        });
        cGraph.getLiens().forEach(lien -> {
            String idA = lien.getNoeudA();
            String idB = lien.getNoeudB();
            org.graphstream.graph.Node nodeA = graph.getNode(idA);
            org.graphstream.graph.Node nodeB = graph.getNode(idB);
            Edge edge = graph.addEdge(lien.getId(), nodeA, nodeB);
            edge.addAttribute("ui.label",lien.getAttributs().toString());
            for (String couleur : couleurs) {
                if (couleur.contains(lien.getType())){
                    edge.addAttribute("ui.style", "fill-color : "+couleur.substring(couleur.indexOf(";")+1) + ";");
                }
            }
        });


        //convertir graphe en streamGraphe : convertir nos noeuds en noeuds de la bibiolthèque streamgraphe
        Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        viewer.enableAutoLayout();
        fxGraph.setContent(viewer.addDefaultView(false));
        return fxGraph;
    }

    public Pane creationLegende(ArrayList<String> couleurs) {
        Pane legende = new Pane();
        int i = 0;
        for (String couleur : couleurs) {
            Label label = new Label();
            label.setText(couleur.substring(0,couleur.indexOf(";")));
            label.setStyle("-fx-text-fill : "+couleur.substring(couleur.indexOf(";")+1) + ";");
            legende.getChildren().add(label);
            if (i<8){
                legende.getChildren().get(i).setLayoutX(i*75);
            }else {
                legende.getChildren().get(i).setLayoutX((i-8)*75);
                legende.getChildren().get(i).setLayoutY((i/8)*10);
            }

            i++;
        }
        return legende;
    }*/
}

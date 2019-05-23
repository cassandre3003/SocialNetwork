package fr.ul.miage.SocialNetwork.interfacegraphe;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import fr.ul.miage.SocialNetwork.file.Reader;
import fr.ul.miage.SocialNetwork.graph.Graph;
import fr.ul.miage.SocialNetwork.graph.Lien;
import fr.ul.miage.SocialNetwork.graph.Noeud;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
//import org.graphstream.graph.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.graphstream.graph.Edge;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;

import org.graphstream.ui.spriteManager.*;

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
            pane.setCenter(convertGraph(graphFichier));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


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
        pierreClaudeLink.addAttribute("ui.label", pierreClaudeLink.getId());*/



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
            edge.addAttribute("ui.color");
        });


        //convertir graphe en streamGraphe : convertir nos noeuds en noeuds de la bibiolthèque streamgraphe
        Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        viewer.enableAutoLayout();
        fxGraph.setContent(viewer.addDefaultView(false));
        return fxGraph;
    }


    public static void main(String[] args) {

    }


}

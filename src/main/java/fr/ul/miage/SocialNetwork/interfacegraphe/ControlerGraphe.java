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
import javafx.scene.Node;
//import org.graphstream.graph.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;

public class ControlerGraphe
{
    @FXML
    public AnchorPane anchor ;
    @FXML
    public Pane pane;

    public static Node getFXGraph(Graph myGraph) throws IOException {
        SwingNode fxGraph = new SwingNode();
        org.graphstream.graph.Graph graph = new SingleGraph("test");

        // construire le graphe a partir de myGraph
        Reader reader = new Reader(); //pour lire le fichier
        Graph graphFichier = reader.creerGraph(); //pour créer le graphe a partir de la methode creerGraph
        HashSet<Noeud> noeuds = graphFichier.getNoeuds(); //pour récupérer les noeuds
        HashSet<Lien> liens = graphFichier.getLiens(); //pour récupérer les liens


        Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        fxGraph.setContent(viewer.addDefaultView(false));
        return fxGraph;
    }

    public ExecutorService getExecutorService() {
        return this.getExecutorService();
    }

    public  Node convertGraph(Graph cGraph){
        SwingNode fxGraph = new SwingNode();
        org.graphstream.graph.Graph graph = new SingleGraph("test");

        Noeud claude = cGraph.getNoeudByNom("Claude");
        Noeud pierre = cGraph.getNoeudByNom("Pierre");
        Lien lien1 = cGraph.getLienById("1");

        graph.addNode(claude.getNom());
        graph.addNode(pierre.getNom());
        graph.addEdge(lien1.getAttributs().toString(), (org.graphstream.graph.Node) graph.getNode(claude.getNom()),graph.getNode(pierre.getNom()));

        //convertir graphe en streamGraphe : convertir nos noeuds en noeuds de la bibiolthèque streamgraphe
        Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        fxGraph.setContent(viewer.addDefaultView(false));
        return fxGraph;

    }


    public static void main(String[] args) {

    }


}

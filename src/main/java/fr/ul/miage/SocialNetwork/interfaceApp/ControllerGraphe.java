package fr.ul.miage.SocialNetwork.interfaceApp;

import fr.ul.miage.SocialNetwork.file.Reader;
import fr.ul.miage.SocialNetwork.graph.Graph;
import javafx.beans.value.ChangeListener;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.graphstream.graph.Edge;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.view.Viewer;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;

public class ControllerGraphe implements Initializable {
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
    private BorderPane pane;


    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        Reader reader = null; //pour lire le fichier
        try {
            reader = new Reader();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try{
            Graph graphe = reader.creerGraph();
            pane.setCenter(convertGraph(graphe));
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
        try {
            Reader reader = new Reader();
            Graph graphe = reader.creerGraph();
            String lienRecupere = lien.getSelectionModel().getSelectedItem().toString();  //pour récupérer le lien
            String nomRecupere = listeDeDepart.getSelectionModel().getSelectedItem().toString();
            String profondeurRecupere = level.getSelectionModel().getSelectedItem().toString();
            String uniciteRecupere = unicite.getSelectionModel().getSelectedItem().toString();
            String parcoursRecupere = parcours.getSelectionModel().getSelectedItem().toString();

            //à partir de la fonction de Ouaoua et Cassou
            String [] elements = new String[] { "E", "F" };
            HashSet<String> res = new HashSet<String>(Arrays.asList(elements));
            String chaine ="";
            for ( String str :  res){
                chaine = chaine + str +"\n";
            }
            resultat.setText(chaine);
        }
        catch (NullPointerException ex) {
                resultat.setText("Veuillez sélectionner un nom et un lien !");
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
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpidev;

import Entity.Formateur;
import Services.ServiceFormateur;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import utils.ConnexionBase;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXMLAfficheFormateurController implements Initializable {

    @FXML
    TableView<Formateur> table;

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;

    /* @FXML
     private TableColumn<Formateur, String> nom;
     @FXML
     private TableColumn<Formateur, String> prenom;*/
    public ObservableList<Formateur> cls = FXCollections.observableArrayList(); //on utilise ObservableList pcq on a un tableView
    @FXML
    private ImageView retour;

    @FXML
    private ImageView image;
    private FileInputStream fis;
    public static Formateur fo;
    private FileChooser fileChooser;
    private File file;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       
        TableColumn nom = new TableColumn("Nom");
        TableColumn prenom = new TableColumn("Prenom");
        table.getColumns().addAll(nom, prenom);

        ServiceFormateur sf = new ServiceFormateur();
        nom.setCellValueFactory(new PropertyValueFactory<Formateur, String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<Formateur, String>("prenom"));

        try {
            table.setItems(sf.getAllFormateurs());
        } catch (SQLException ex) {
            Logger.getLogger(FXMLAfficheFormateurController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Retour(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Formateur.fxml"));
        Parent root = loader.load();
        FormateurController acc = loader.getController();
        retour.getScene().setRoot(root);
    }

    @FXML
    private void display(MouseEvent event) throws SQLException, IOException {
        System.out.println("aaaaaaaaaaaaaaaaaaaa");
        Formateur f = table.getSelectionModel().getSelectedItem();

        ServiceFormateur sf = new ServiceFormateur();
        nom.setText(f.getNom());
        prenom.setText(f.getPrenom());

        System.out.println(f.getNom());

        image.setImage(sf.getFormateurImageByID(f.getId()));
    }

}

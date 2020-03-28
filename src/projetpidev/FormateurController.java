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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FormateurController implements Initializable {
    //int c;
    //File pDir;
    //File pfile;
   // int file = 0;
    //String lien;

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
   
    @FXML
    private Button ajouterBtn;
    @FXML
    private Button afficherBtn;
    @FXML
    private Button supprimerBtn;
    @FXML
    private Button rechercherBtn;
    @FXML
    private ImageView nomImage;
    @FXML
    private Button InformationsBtn;
    @FXML
    private Button FormationsBtn;
    @FXML
    private Button FormateurBtn;
    @FXML
    private Button EvenementsBtn;
    @FXML
    private Button EspecesBtn;
    @FXML
    private Button BoutiqueBtn;
    @FXML
    private ImageView retour;
      @FXML
    private TextArea path;
        @FXML
    private Button modifierBtn;
  //pour le fonction parcourir (image)
    private FileChooser fileChooser;
    private File fill;
    private Image image;
    private FileInputStream fis;
     private Stage stage;
  
   // private Object parent;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       // file = 0;
     //   type.getItems().addAll("Chasse", "Peche");
        //c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
       // pDir = new File("src/images/addimage" + c + ".jpg");
        //lien = "images/addimage" + c + ".jpg";

    }    

    @FXML
    private void ajouter(ActionEvent event) throws FileNotFoundException {
          //if (verifChamps() && verifNom()) {
            String Nom = nom.getText();
            String Prenom = prenom.getText();
            //String Img = img.getText();
            //String NomImage = lien;
            

            System.out.println(Nom);
            System.out.println(Prenom);
            //System.out.println(Img);
           // System.out.println(Image);
               fis = new FileInputStream(fill);
            Formateur u = new Formateur(Nom, Prenom, (InputStream) fis);
            int status = ServiceFormateur.ajouter(u); //ligne hedhi tekhou valeur retournée par insert (eli heya st sammineha status o besh naamlou beha comparaison)

            if (status > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Ajouter utilisateur");
                alert.setHeaderText("Dialogue information");
                alert.setContentText("Ajout avec succés");

                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Ajouter utilisateur");
                alert.setHeaderText("Dialogue ERREUR");
                alert.setContentText("Un probléme est survenu");

                alert.showAndWait();
            }

//s        }
    }

    @FXML
    private void modifier(ActionEvent event) {
         String Nom = nom.getText();
        String Prenom = prenom.getText();
      //  String Img = img.getText();
       // String NomImage = lien;

         Formateur f = new Formateur(Nom, Prenom, (InputStream) fis);

        int status = ServiceFormateur.update(f, Nom);
        System.out.println(status);
        if (status == 0) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Modifier formateur");
            alert.setHeaderText("Dialogue information");
            alert.setContentText("Modification avec succés");

            alert.showAndWait();

        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Modifier formateur");
            alert.setHeaderText("Dialogue ERREUR");
            alert.setContentText("Un probléme est survenu");

            alert.showAndWait();
        }
    }

    @FXML
    private void afficher(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAfficherFormateur.fxml"));
        Parent root = loader.load();
        FXMLAfficheFormateurController acc = loader.getController();
        afficherBtn.getScene().setRoot(root);
    }

    @FXML
    private void supprimer(ActionEvent event) {
          String Nom = nom.getText(); //yekhou nom eli aatithoulou besh yfasakh bih

        int status = ServiceFormateur.delete(Nom);
        if (status > 0) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Supprimer Formateur");
            alert.setHeaderText("Dialogue information");
            alert.setContentText("SUPPRESSION avec succés");

            alert.showAndWait();

        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Supprimer Formateur");
            alert.setHeaderText("Dialogue ERREUR");
            alert.setContentText("Un probléme est survenu");

            alert.showAndWait();

        }
        nom.clear();
        prenom.clear();
        //img.clear();
     // image.clear();
    }

    @FXML
    private void rechercher(ActionEvent event) throws SQLException {
        String name = nom.getText();

        Formateur f = ServiceFormateur.getFormateur(name);

        nom.setText((f.getNom()));
        prenom.setText((f.getPrenom()));
        //img.setText(f.getImg());
        //nomImage.setText(f.getNomImage());
    }

    
     public boolean copier(File source, File dest) {
        try (InputStream sourceFile = new java.io.FileInputStream(source);
                OutputStream destinationFile = new FileOutputStream(dest)) {
            // Lecture par segment de 0.5Mo  
            byte buffer[] = new byte[512 * 1024];
            int nbLecture;
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, nbLecture);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Erreur 
        }
        return true; // Résultat OK  
    }
    @FXML
    private void AddImage(MouseEvent event) throws MalformedURLException {
         fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files", "*.png", "*.jpg", "*.gif"));
        //Stage stagec = (Stage) parent.getScene().getWindow();
        //fill = fileChooser.showOpenDialog(stagec);
        Window stage = null;
        fill = fileChooser.showOpenDialog(stage);
        if (fill != null) {
            //desktop.open(file);
            image = new Image(fill.toURI().toString());
            nomImage.setImage(image);
            path.setText(fill.getAbsolutePath());

        }
    
    }

    @FXML
    private void Retour(MouseEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMenu.fxml"));
        Parent root = loader.load();
        FXMLMenuController acc = loader.getController();
        retour.getScene().setRoot(root);
    }

    

    
    
}

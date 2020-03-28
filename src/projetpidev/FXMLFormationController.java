package projetpidev;

import Entity.Formateur;
import Entity.Formation;
import Services.ServiceFormateur;
import Services.ServiceFormation;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
public class FXMLFormationController implements Initializable {

    Connection cnx;
    @FXML
    private Button BoutiqueBtn;
    @FXML
    private Button EspecesBtn;
    @FXML
    private Button EvenementsBtn;
    @FXML
    private Button FormateurBtn;
    @FXML
    private Button FormationsBtn;
    @FXML
    private Button InformationsBtn;
    @FXML
    private TextField nom;
    @FXML
    private ComboBox<String> type;
    @FXML
    private TextField lieu;
    @FXML
    private TextArea description;
    @FXML
    private TextField heure;
   
    @FXML
    private TextField nbrplace;
    @FXML
    private ComboBox<String> formateur;

    //   ObservableList<String> id = FXCollections.observableArrayList();
    ObservableList<String> listnom = FXCollections.observableArrayList();
    @FXML
    private Button ajouterBtn;
    @FXML
    private DatePicker datepicker;

    @FXML
    private Button afficher;

    @FXML
    private TextArea path;

      private FileChooser fileChooser;
    private File fill;
    private Image image;
    private FileInputStream fis;
     private Stage stage;
    Formateur f = new Formateur();

    ServiceFormateur sf = new ServiceFormateur();
    @FXML
    private ImageView nomImage;
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         type.getItems().addAll("Chasse", "Peche");

        try {
            fillcombo();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLFormationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void fillcombo() throws SQLException {
        ServiceFormateur ser = new ServiceFormateur();
        List<Formateur> list = ser.readformateur();
        ObservableList<Formateur> cls = FXCollections.observableArrayList();
        for (Formateur aux : list) {
            cls.add(new Formateur(aux.getNom()));
            listnom.add(aux.getNom());
        }
        formateur.setItems(listnom);
    }

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException, FileNotFoundException {

        ServiceFormation ser = new ServiceFormation();

        String Nom = nom.getText();
        String Type = type.getValue();
        Date d = Date.valueOf(datepicker.getValue());
        String Lieu = lieu.getText();
        String Description = description.getText();
        String Heure = heure.getText();
        // String nomImage = lien;
        int Nbrplace = (int) Integer.valueOf(this.nbrplace.getText());
        String formateur1 = formateur.getValue();
        int id = ser.findid(formateur1);

        System.out.println(Nom);
        System.out.println(Type);
        System.out.println(d);
        System.out.println(Lieu);
        System.out.println(Description);
        System.out.println(Heure);
        // System.out.println(nomImage);
        System.out.println(Nbrplace);
        System.out.println(formateur1);

        fis = new FileInputStream(fill);
        Formation f = new Formation(Nom, Type, d, Lieu, Description, Heure, Nbrplace,id,(InputStream) fis);
        Formation f1 = new Formation(nom.getText(), type.getValue(), d, Lieu, Description, Heure, Nbrplace,id,(InputStream) fis);
        /*for (Formateur formateur : type.getItems()) {
         Formation f = new Formation(Formateur.getNom());*/
        ser.ajouter(f1); //ligne hedhi tekhou valeur retournée par insert (eli heya st sammineha status o besh naamlou beha comparaison)
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        /*
         if (status > 0) {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Ajouter Formation");
         alert.setHeaderText("Dialogue information");
         alert.setContentText("Formation ajoutée avec succés");

         alert.showAndWait();
         } else {
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Ajouter Formation");
         alert.setHeaderText("Dialogue ERREUR");
         alert.setContentText("Un probléme est survenu");

         alert.showAndWait();
         }
         */
    }

    /*public int findbynomformateur(String name) throws SQLException
     {
     String req="Select * FROM formateur";
     Statement st = cnx.createStatement();
     ResultSet res=st.executeQuery(req);
     while(res.next()){
     if(res.getString("nom").equals(name))
                    
     {
     String val=res.getString("id");
     System.out.println(val);
     int valeur=Integer.parseInt(val);
     return valeur;
                    
     }
     }
     int valeur=0;
     return valeur;
     }    */
//tb3in image hdhom bch njrbhom
  /*  public static boolean copier(File source, File dest) {
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
     }*/
    /*public static void infoBox(String infoMessage, String titleBar, String headerMessage) {
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
     alert.setTitle(titleBar);
     alert.setHeaderText(headerMessage);
     alert.setContentText(infoMessage);
     alert.showAndWait();
     }*/
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
    private void afficher(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAfficherFormation.fxml"));
        Parent root = loader.load();
        FXMLAfficherFormationController acc = loader.getController();
        afficher.getScene().setRoot(root);
    }

}

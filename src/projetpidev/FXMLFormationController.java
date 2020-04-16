package projetpidev;

import Entity.Formateur;
import Entity.Formation;
import Entity.SendMail;
import Services.ServiceFormateur;
import Services.ServiceFormation;
import com.jfoenix.controls.JFXTimePicker;
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
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.FadeTransition;
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
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;


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
    private JFXTimePicker heure;

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
    @FXML
    private ImageView retour;
    @FXML
    private Pane pane11;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane4;
    @FXML
    private ImageView logout;
    @FXML
    private Button stat;
    @FXML
    private Button addformation;
    @FXML
    private Button listeformateurs;
    @FXML
    private Button listeformations;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pane11.setStyle("-fx-background-image: url(\"/images/4444.jpg\")");
        pane2.setStyle("-fx-background-image: url(\"/images/2222.jpg\")");
        pane3.setStyle("-fx-background-image: url(\"/images/3333.jpg\")");
        pane4.setStyle("-fx-background-image: url(\"/images/4444.jpg\")");

        backgroundAnimation();
        
        type.getItems().addAll("Chasse", "Peche");

        try {
            fillcombo();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLFormationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
 private void backgroundAnimation() {

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), pane4);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        fadeTransition.setOnFinished(event -> {

            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(3), pane3);
            fadeTransition1.setFromValue(1);
            fadeTransition1.setToValue(0);
            fadeTransition1.play();

            fadeTransition1.setOnFinished(event1 -> {
                FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(3), pane2);
                fadeTransition2.setFromValue(1);
                fadeTransition2.setToValue(0);
                fadeTransition2.play();

                fadeTransition2.setOnFinished(event2 -> {

                    FadeTransition fadeTransition0 = new FadeTransition(Duration.seconds(3), pane2);
                    fadeTransition0.setToValue(1);
                    fadeTransition0.setFromValue(0);
                    fadeTransition0.play();

                    fadeTransition0.setOnFinished(event3 -> {

                        FadeTransition fadeTransition11 = new FadeTransition(Duration.seconds(3), pane3);
                        fadeTransition11.setToValue(1);
                        fadeTransition11.setFromValue(0);
                        fadeTransition11.play();

                        fadeTransition11.setOnFinished(event4 -> {

                            FadeTransition fadeTransition22 = new FadeTransition(Duration.seconds(3), pane4);
                            fadeTransition22.setToValue(1);
                            fadeTransition22.setFromValue(0);
                            fadeTransition22.play();

                            fadeTransition22.setOnFinished(event5 -> {
                                backgroundAnimation();
                            });

                        });

                    });

                });
            });

        });

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
       //  Boolean test1;
       // test1 = true;
        int day = datepicker.getValue().getDayOfMonth();
        int month = datepicker.getValue().getMonthValue();
        int year = datepicker.getValue().getYear()-1900;
        
        Date d = new Date(year, month, day);
        Date s = new Date(LocalDate.now().getYear()-1900, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
       if (verifChamps() && verifNom() && verifLieu() && verifDescription()  && verifNum() && verifDate()){
        ServiceFormation ser = new ServiceFormation();

        String Nom = nom.getText();
        String Type = type.getValue();
        String Lieu = lieu.getText();
        String Description = description.getText();
        String Heure = heure.getValue().toString();
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
        Formation f = new Formation(Nom, Type, d, Lieu, Description, heure.getValue().toString(), Nbrplace, id, (InputStream) fis);
       Formation f1 = new Formation(nom.getText(), type.getValue(), d, Lieu, Description, heure.getValue().toString(), Nbrplace, id, (InputStream) fis);
      //  Formation f1 = new Formation(nom.getText(), type.getValue(), d, Lieu, Description, heure.getValue().toString(),lien, Nbrplace, id );
        /*for (Formateur formateur : type.getItems()) {
         Formation f = new Formation(Formateur.getNom());*/
        ser.ajouter(f1); //ligne hedhi tekhou valeur retournée par insert (eli heya st sammineha status o besh naamlou beha comparaison)
    /*    Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajouter formateur");
        alert.setHeaderText("Dialogue information");
        alert.setContentText("Ajout avec succés");*/
       // System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        SendMail.sendMail("tesnime.ammar@esprit.tn", "Une nouvelle Formation ", "La nouvelle formation nommée" + f.getNom() + "de type" + f.getType() + "se déroulera le" + f.getDate() +" à "+ f.getHeure() + "à" + f.getLieu() + "ayant pour but de" + f.getDescription() );
    }
    }

   
//tb3in image hdhom bch njrbhom
  /* public static boolean copier(File source, File dest) {
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
  */
  
   
    
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAfficherFormation.fxml"));//FXMLAfficherFormation.fxml
        Parent root = loader.load();
        FXMLAfficherFormationController acc = loader.getController();
        afficher.getScene().setRoot(root);
    }

    @FXML
    private void retour(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMenu.fxml"));
        Parent root = loader.load();
        FXMLMenuController acc = loader.getController();
        retour.getScene().setRoot(root);
    }
    
     private boolean verifChamps() {
        if (nom.getText().isEmpty() | type.getValue().isEmpty()  | lieu.getText().isEmpty() | description.getText().isEmpty() | heure.getValue().toString().isEmpty()| nbrplace.getText().isEmpty()| formateur.getValue().isEmpty()|path.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation des champs");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir tous les champs");
            alert.showAndWait();
            return false;
        }
        return true;
}
     
      private boolean verifNom() {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(nom.getText());
        if (m.find() && m.group().equals(nom.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation des champs");
            alert.setHeaderText(null);
            alert.setContentText("Verifier le nom de la formation");
            alert.showAndWait();
            return false;
        }
      }
       private boolean verifDate() {
        int day = datepicker.getValue().getDayOfMonth();
        int month = datepicker.getValue().getMonthValue();
        int year = datepicker.getValue().getYear()-1900;
        Date d = new Date(year, month, day);
        Date s = new Date(LocalDate.now().getYear()-1900, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        if (d.before(s)) {
          
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Validation des champs");
            alert.setHeaderText(null);
            alert.setContentText("La date entrée est inférieure à la date actuelle");
            alert.showAndWait();
            return false;
        }
        else {return true;}
      }
      
       /* private boolean verifHeure() {
        int hour = heure.getValue().getHour();
        int minute = heure.getValue().getMinute();
      
      //  Time d1;
        d1 = new Time(int heure.getValue().getHour(), int heure.getValue().getMinute());
        Time s1 = new Time(CurrentTime);
        if (d1.before(s1)) {
          
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Validation des champs");
            alert.setHeaderText(null);
            alert.setContentText("La date entrée est inférieure à la date actuelle");
            alert.showAndWait();
            return false;
        }
        else {return true;}
      }
      */
       private boolean verifLieu() {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(lieu.getText());
        if (m.find() && m.group().equals(lieu.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation des champs");
            alert.setHeaderText(null);
            alert.setContentText("Verifier le lieu de la formation");
            alert.showAndWait();
            return false;
        }
      }
        private boolean verifDescription() {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(description.getText());
        if (m.find() && m.group().equals(description.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation des champs");
            alert.setHeaderText(null);
            alert.setContentText("Verifier la description de la formation");
            alert.showAndWait();
            return false;
        }
      }
         private boolean verifNum() {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(nbrplace.getText());
       
        if (m.find() && m.group().equals(nbrplace.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation de nombre de places");
            alert.setHeaderText(null);
            alert.setContentText("Verifier le nombre de places saisis");
            alert.showAndWait();
            return false;
        }
    }

    private void redirection(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Formateur.fxml"));
        Parent root = loader.load();
        FormateurController acc = loader.getController();
        FormateurBtn.getScene().setRoot(root);
        
    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLyassine.fxml"));
        Parent root = loader.load();
        FXMLLoginController acc = loader.getController();
        logout.getScene().setRoot(root);
    }

    @FXML
    private void redirectionformation(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFormation.fxml"));
        Parent root = loader.load();
        FXMLFormationController acc = loader.getController();
        FormationsBtn.getScene().setRoot(root);
    }

     @FXML
    private void listeformations(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAfficherFormation.fxml"));
        Parent root = loader.load();
        FXMLAfficherFormationController acc = loader.getController();
        listeformations.getScene().setRoot(root);
    }

    @FXML
    private void listeformateurs(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAfficherFormateur.fxml"));
        Parent root = loader.load();
        FXMLAfficheFormateurController acc = loader.getController();
        listeformateurs.getScene().setRoot(root);
    }

    @FXML
    private void addformation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFormation.fxml"));
        Parent root = loader.load();
        FXMLFormationController acc = loader.getController();
        addformation.getScene().setRoot(root);
        
    }

    @FXML
    private void stat(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLstatreservation.fxml"));
        Parent root = loader.load();
        FXMLstatreservationController acc = loader.getController();
        stat.getScene().setRoot(root);
        
    }

}
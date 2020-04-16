/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpidev;

import Services.SMS;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class SMSController implements Initializable {
    @FXML
    private TextArea Message;
    @FXML
    private Label monnum;
    @FXML
    private TextField num;
    @FXML
    private Button send;
    @FXML
    private Button EspecesBtn;
    @FXML
    private Button EvenementsBtn;
    @FXML
    private Button InformationsBtn;
    @FXML
    private Button BoutiqueBtn;
    @FXML
    private Button FormationsBtn;
    @FXML
    private Pane pane11;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane4;
    @FXML
    private Label Lhello;
    @FXML
    private ImageView sms;
    @FXML
    private ImageView sms1;
    @FXML
    private Button mescadeaux;
    @FXML
    private Button listeformations;
    @FXML
    private Button mesres;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void send(ActionEvent event) {
         SMS.sendTrait(Message.getText());
        

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Envoyer Message");
        alert.setHeaderText("Dialogue information");
        alert.setContentText("Envoi du message avec succés");
        alert.showAndWait();
    }


    @FXML
    private void envoyersms(MouseEvent event) {
    }

    @FXML
    private void mescadeaux(ActionEvent event) {
        Alert alertu = new Alert(Alert.AlertType.ERROR);
            alertu.setTitle("Pas de choix!");
            alertu.setHeaderText("Vous devez consulter la liste des formations pour obtenir le QrCode");
            Optional<ButtonType> result = alertu.showAndWait();
    }

    @FXML
    private void mesres(ActionEvent event) {
    }

   /* @FXML
    private void redirectionEvent(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("evenement.fxml"));
        Parent root = loader.load();
        EvenementController acc = loader.getController();
        EvenementsBtn.getScene().setRoot(root);
    }
    

    @FXML
    private void redirectionBoutique(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFrontProduit.fxml"));
        Parent root = loader.load();
        FXMLFrontProduitController acc = loader.getController();
        BoutiqueBtn.getScene().setRoot(root);
    }
*/
    @FXML
    private void redirectionFormation(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("FormationFront.fxml"));
        Parent root = loader.load();
        FormationFrontController acc = loader.getController();
        FormationsBtn.getScene().setRoot(root);
    }

    @FXML
    private void redirectionEvent(ActionEvent event) {
    }

    @FXML
    private void redirectionBoutique(ActionEvent event) {
    }
    
}

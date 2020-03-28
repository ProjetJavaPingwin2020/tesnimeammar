/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpidev;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXMLMenuFrontController implements Initializable {
    @FXML
    private Pane Pane1;
    @FXML
    private Pane Pane2;
    @FXML
    private Pane Pane3;
    @FXML
    private Pane Pane4;
    @FXML
    private Button accueilBtn;
    @FXML
    private Button boutiqueBtn;
    @FXML
    private Button especesBtn;
    @FXML
    private Button evenementsBtn;
    @FXML
    private Button formationBtn;
    @FXML
    private Button informationsBtn;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Pane1.setStyle("-fx-background-image: url(\"/images/111.jpg\")");
        Pane2.setStyle("-fx-background-image: url(\"/images/444.jpg\")");
        Pane3.setStyle("-fx-background-image: url(\"/images/333.jpg\")");
        Pane4.setStyle("-fx-background-image: url(\"/images/111.jpg\")");

        backgroundAnimation();
        // TODO
    }    

       private void backgroundAnimation() {

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), Pane4);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        fadeTransition.setOnFinished(event -> {

            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(3), Pane3);
            fadeTransition1.setFromValue(1);
            fadeTransition1.setToValue(0);
            fadeTransition1.play();

            fadeTransition1.setOnFinished(event1 -> {
                FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(3), Pane2);
                fadeTransition2.setFromValue(1);
                fadeTransition2.setToValue(0);
                fadeTransition2.play();

                fadeTransition2.setOnFinished(event2 -> {

                    FadeTransition fadeTransition0 = new FadeTransition(Duration.seconds(3), Pane2);
                    fadeTransition0.setToValue(1);
                    fadeTransition0.setFromValue(0);
                    fadeTransition0.play();

                    fadeTransition0.setOnFinished(event3 -> {

                        FadeTransition fadeTransition11 = new FadeTransition(Duration.seconds(3), Pane3);
                        fadeTransition11.setToValue(1);
                        fadeTransition11.setFromValue(0);
                        fadeTransition11.play();

                        fadeTransition11.setOnFinished(event4 -> {

                            FadeTransition fadeTransition22 = new FadeTransition(Duration.seconds(3), Pane4);
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

    @FXML
    private void accueil(ActionEvent event) {
    }

    @FXML
    private void boutique(ActionEvent event) {
    }

    @FXML
    private void especes(ActionEvent event) {
    }

    @FXML
    private void evenement(ActionEvent event) {
    }

    @FXML
    private void formation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FormationFront.fxml"));
        Parent root = loader.load();
        FormationFrontController acc = loader.getController();
        formationBtn.getScene().setRoot(root);
    }

    @FXML
    private void informations(ActionEvent event) {
    }
    
   
    

    
}

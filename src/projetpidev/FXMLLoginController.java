package projetpidev;

import Entity.FosUser;
import Services.UserSevice;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.ConnexionBase;
import Services.UserSevice;

/**
 * FXML Controller class
 *
 * @author Yassiine
 */
public class FXMLLoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private PasswordField password;

    @FXML
    private Button login;

    @FXML
    private TextField username;

    @FXML
    private Button register;

    
    
    @FXML
    void login(ActionEvent event) throws SQLException, IOException {

        UserSevice s1 = new UserSevice();
       s1.deletelogin();
        FosUser u1 = s1.existLogin(username.getText(), password.getText());

         if (u1 != null)
       {
         s1.ajouterlogin(u1);
       String ss= s1.getlogin() ;
       int result = Integer.parseInt(ss);			
			
                        System.out.println(result);

        
           }      
       else 
       {
           System.out.println("Invalid Cred");
       }
       
     try {
               
               if (u1.getRoles().equals("Client"))
               {
              
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLMenuFront.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
               }
               else {
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLMenu.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
               }
               
           } catch (IOException ex) {
               Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
           }
                ((Node) event.getSource()).getScene().getWindow().hide();   
    
    }
    
    
    
 

    public void redirect(ActionEvent event) {
        try {

            Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLRegister.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(AnchorPane);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ((Node) event.getSource()).getScene().getWindow().hide();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}

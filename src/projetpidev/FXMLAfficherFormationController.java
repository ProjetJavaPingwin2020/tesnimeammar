/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpidev;

import Entity.Formateur;
import Entity.Formation;
import Services.ServiceFormateur;
import Services.ServiceFormation;
import java.io.IOException;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;
import utils.ConnexionBase;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXMLAfficherFormationController implements Initializable {
     private Connection cnx;
    private Statement st;
    @FXML
    private TableView<Formation> table;
   
     
 
    @FXML
    private ImageView image;
    @FXML
    private TextField nom;
    @FXML
    private TextField type;
    @FXML
    private TextField date;
    @FXML
    private TextField lieu;
    @FXML
    private TextField description;
    @FXML
    private TextField heure;
    @FXML
    private TextField nbrplace;
    @FXML
    private TextField formateur;
    @FXML
    private Button supprimer;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
          TableColumn nom = new TableColumn("Nom");
        TableColumn type = new TableColumn("Type");
        TableColumn date = new TableColumn("Date");
        TableColumn lieu = new TableColumn("Lieu");
        TableColumn description = new TableColumn("Description");
        TableColumn heure = new TableColumn("Heure");
        TableColumn nbrplace = new TableColumn("Nbrplace");
        TableColumn formateur = new TableColumn("Formateur");
        table.getColumns().addAll(nom, type,date,lieu,description,heure,nbrplace,formateur);
        
          ServiceFormation sf = new ServiceFormation();
        
         nom.setCellValueFactory(new PropertyValueFactory<Formation, String>("nom"));
        type.setCellValueFactory(new PropertyValueFactory<Formation, String>("type"));
        date.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date"));
        lieu.setCellValueFactory(new PropertyValueFactory<Formation, String>("lieu"));
        description.setCellValueFactory(new PropertyValueFactory<Formation, String>("description"));
        heure.setCellValueFactory(new PropertyValueFactory<Formation, String>("heure"));
        nbrplace.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("nbrplace"));
        formateur.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("formateur"));
       // type.setCellValueFactory(new PropertyValueFactory<Formation, String>("type"));
     
        try {
            table.setItems(sf.getAllFormations());
        } catch (SQLException ex) {
            Logger.getLogger(FXMLAfficherFormationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }   
        public void Aff() throws SQLException{
            
            //data.clear();
      
       
        
         TableColumn nom = new TableColumn("Nom");
        TableColumn type = new TableColumn("Type");
        TableColumn date = new TableColumn("Date");
        TableColumn lieu = new TableColumn("Lieu");
        TableColumn description = new TableColumn("Description");
        TableColumn heure = new TableColumn("Heure");
        TableColumn nbrplace = new TableColumn("Nbrplace");
        TableColumn formateur = new TableColumn("Formateur");
          ServiceFormation ser = new ServiceFormation();
        List<Formation> list = ser.readAll();
        for (Formation aux : list) { 
      table.getColumns().addAll(nom, type,date,lieu,description,heure,nbrplace,formateur);
        
        }
        
        ServiceFormation sf = new ServiceFormation();
        nom.setCellValueFactory(new PropertyValueFactory<Formation, String>("nom"));
        type.setCellValueFactory(new PropertyValueFactory<Formation, String>("type"));
        date.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date"));
        lieu.setCellValueFactory(new PropertyValueFactory<Formation, String>("lieu"));
        description.setCellValueFactory(new PropertyValueFactory<Formation, String>("description"));
        heure.setCellValueFactory(new PropertyValueFactory<Formation, String>("heure"));
        nbrplace.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("nbrplace"));
        formateur.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("formateur"));
       // type.setCellValueFactory(new PropertyValueFactory<Formation, String>("type"));
     
        try {
            table.setItems(sf.getAllFormations());
        } catch (SQLException ex) {
            Logger.getLogger(FXMLAfficheFormateurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
          
    }
    
    
  /*  public void Aff()  throws SQLException{
                        /*try {
         cnx = ConnexionBase.getInstance().getCnx();
            st = cnx.createStatement();
                        data.clear();

            ResultSet rs = st.executeQuery("select * from formation");
            while(rs.next()){
                data.add(new Formation(rs.getString(1),rs.getString(2),rs.getDate(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getInt(9)));
            }

        } catch (Exception e) {
                //Logger.getLogger(tab)
        }*/
      
            
         /*   data.clear();
        ServiceFormation ser = new ServiceFormation();
        List<Formation> list = ser.readAll();
        for (Formation aux : list)
        {
          data.add(new Formation(aux.getNom(),aux.getType(), aux.getDate(), aux.getLieu(),aux.getDescription(),aux.getHeure(),aux.getNomImage(),aux.getNbrplace(),aux.getFormateur()));  
                   
        }
        
               
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
            description.setCellValueFactory(new PropertyValueFactory<>("description"));
            heure.setCellValueFactory(new PropertyValueFactory<>("heure"));
            nomImage.setCellValueFactory(new PropertyValueFactory<>("NomImage"));
            nbrplace.setCellValueFactory(new PropertyValueFactory<>("nbrplace"));
            formateur.setCellValueFactory(new PropertyValueFactory<>("formateur"));

            table.setItems(data);
            /*table.setEditable(true);
            nom.setCellFactory(TextFieldTableCell.forTableColumn());
            type.setCellFactory(TextFieldTableCell.forTableColumn());
            date.setCellFactory(TextFieldTableCell.forTableColumn());
            
            date.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            id_enseignanttab.setCellFactory(TextFieldTableCell.forTableColumn());*/

    @FXML
    private void display(MouseEvent event) throws SQLException, IOException {
          System.out.println("aaaaaaaaaaaaaaaaaaaa");
        Formation f = table.getSelectionModel().getSelectedItem();
        
        ServiceFormation sf = new ServiceFormation();
        nom.setText(f.getNom());
        type.setText(f.getType());
        //date.setDate(f.getDate());
        description.setText(f.getDescription());
                        
        
System.out.println(f.getNom());

         
        image.setImage(sf.getFormationImageByID(f.getId()));
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
            
      /*      table.setItems(data);
             String Nom = nom.getText(); 
          int status = ServiceFormation.delete(Nom);
        if (status > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Supprimer Formation");
            alert.setHeaderText("Dialogue information");
            alert.setContentText("SUPPRESSION avec succés");

            alert.showAndWait();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Supprimer Formation");
            alert.setHeaderText("Dialogue ERREUR");
            alert.setContentText("Un probléme est survenu");

            alert.showAndWait();

        }
//             Aff();
             //RechercheAV();
        */
       //  Formation f = table.getSelectionModel().getSelectedItem();
       // System.out.println(asso.getId()); 
      //  ServiceFormation serf= new ServiceFormation();
       // serf.delete(f.getId());
       // table.setItems(data);
      /*  table.setItems(data);

             ObservableList<Formation> allFormations,SingleFormation ;
             allFormations=table.getItems();
             SingleFormation=table.getSelectionModel().getSelectedItems();
             Formation A = SingleFormation.get(0);
             ServiceFormation serf = new ServiceFormation(); // STD = Service TAB DEMANDE
             serf.delete(A.getNom());
             SingleFormation.forEach(allFormations::remove);
             table.setItems(data);
    }
*/       
         String Nom = nom.getText(); //yekhou nom eli aatithoulou besh yfasakh bih

        int status = ServiceFormation.delete(Nom);
        if (status > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Supprimer Formation");
            alert.setHeaderText("Dialogue information");
            alert.setContentText("SUPPRESSION avec succés");
            //table.setItems(data);
            alert.showAndWait();
            ServiceFormation sf = new ServiceFormation();
             try {
            table.setItems(sf.getAllFormations());
        } catch (SQLException ex) {
            Logger.getLogger(FXMLAfficherFormationController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Supprimer Formation");
            alert.setHeaderText("Dialogue ERREUR");
            alert.setContentText("Un probléme est survenu");
            /// table.setItems(data);
            alert.showAndWait();
            ServiceFormation sf = new ServiceFormation();
             try {
            table.setItems(sf.getAllFormations());
        } catch (SQLException ex) {
            Logger.getLogger(FXMLAfficherFormationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        }
         
        
        
                           		
           /* table.setItems(data);

             ObservableList<Formation> allFormations,SingleFormations ;
             allFormations=table.getItems();
             SingleFormations=table.getSelectionModel().getSelectedItems();
             Formation F = SingleFormations.get(0);
             ServiceFormation serf = new ServiceFormation(); // STD = Service TAB DEMANDE
             serf.delete(F.getId());
             SingleFormations.forEach(allFormations::remove);
        */
        
        
             
        
     
}
}
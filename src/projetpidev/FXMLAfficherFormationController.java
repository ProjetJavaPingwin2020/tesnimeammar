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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
    @FXML
    private ImageView retour;
  
    ServiceFormation sf=new ServiceFormation();
    
     private ObservableList<Formation> data = FXCollections.observableArrayList();
    @FXML
    private TextField recherche;
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
        
          //ServiceFormation sf = new ServiceFormation();
        
         nom.setCellValueFactory(new PropertyValueFactory<Formation, String>("nom"));
        type.setCellValueFactory(new PropertyValueFactory<Formation, String>("type"));
        date.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date"));
        lieu.setCellValueFactory(new PropertyValueFactory<Formation, String>("lieu"));
        description.setCellValueFactory(new PropertyValueFactory<Formation, String>("description"));
        heure.setCellValueFactory(new PropertyValueFactory<Formation, String>("heure"));
        nbrplace.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("nbrplace"));
        formateur.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("formateur"));
       // type.setCellValueFactory(new PropertyValueFactory<Formation, String>("type"));
         table.setItems(data);
            table.setEditable(true);
            nom.setCellFactory(TextFieldTableCell.forTableColumn());
            type.setCellFactory(TextFieldTableCell.forTableColumn());
           // nbhtab.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        //  date.setCellFactory(TextFieldTableCell.forTableColumn());
             lieu.setCellFactory(TextFieldTableCell.forTableColumn());
              description.setCellFactory(TextFieldTableCell.forTableColumn());
               heure.setCellFactory(TextFieldTableCell.forTableColumn());
                nbrplace.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
     
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
          RechercheAV();
        
          
    }
    
    
 

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
        
     
}

    
    @FXML
    private void retour(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFormation.fxml"));
        Parent root = loader.load();
        FXMLFormationController acc = loader.getController();
        retour.getScene().setRoot(root);
    }
     
    public void Change_Nom(TableColumn.CellEditEvent bb) throws SQLException{
     Formation tab_Formationselected = table.getSelectionModel().getSelectedItem();
     tab_Formationselected.setNom(bb.getNewValue().toString());
     sf.updatetab(tab_Formationselected);
 }
     
     
     @FXML
 public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Formation> filteredData = new FilteredList<>(data, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(formation -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (formation.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (formation.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(formation.getLieu()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Formation> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
    }
}
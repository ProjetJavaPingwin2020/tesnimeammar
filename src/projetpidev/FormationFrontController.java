package projetpidev;

//import Utils.session;
import java.io.IOException;

import Services.*;
import Entity.Formation;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import utils.ConnexionBase;

public class FormationFrontController implements Initializable {

    private Label Ltest;
  
    

    private Stage stage;

    UserSevice ms = new UserSevice();
    private Connection cnx;
    ServiceFormation sf = new ServiceFormation();
    private ObservableList<Formation> data;
    int CurrentFormation = 0;
    int i = 0;
  //    private Image image;
       private File fill;
 
    private FileInputStream fis;
    private Object afficher;
    @FXML
    private TextField type;
    @FXML
    private TextField Date;
     @FXML
    private ImageView image;
    @FXML
    private TextField lieu;
    @FXML
    private TextField description;
    @FXML
    private TableView<Formation> table;
    @FXML
    private ImageView deconnexion;
   
   

    @Override
    public void initialize(URL url, ResourceBundle resources) {

        data = FXCollections.observableArrayList();
        cnx = ConnexionBase.getInstance().getCnx();
         TableColumn nom = new TableColumn("Nom de la Formation ");
          nom.setCellValueFactory(new PropertyValueFactory<Formation,String>("nom"));
           table.getColumns().addAll(nom);
           try {
            table.setItems(sf.getAllFormations());
        } catch (SQLException ex) {
            Logger.getLogger(FXMLAfficherFormationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

       /* try {
            data.addAll(sf.readAll());
        } catch (SQLException ex) {
            Logger.getLogger(FormationFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            getUserData(CurrentFormation);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FormationFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
          
           /*try {
            ObservableList<ImageView> ivs = sf.getAllFormationsImages();

            ObservableList<Formation> als = sf.getAllFormations();
            ObservableList<Label> labels = FXCollections.observableArrayList();
            

            for (int i = 0; i < als.size(); i++) {
                labels.add(new Label(als.get(i).getNom()));
                   
            }
            
            final double MAX_FONT_SIZE = 25.0;

            ObservableList<HBox> hbs = FXCollections.observableArrayList();
            HBox hb = new HBox();
            HBox hb2 = new HBox();
            HBox hb3 = new HBox();

            for (int i = 0; i < ivs.size(); i++) {
                //hb.getChildren().addAll(ivs.get(0),labels.get(0));
                labels.get(i).setFont(new Font(MAX_FONT_SIZE));
                labels.get(i).setPadding(new Insets(50, 0, 0, 0));
                hbs.add(new HBox(ivs.get(i), labels.get(i)));

            }
            for (int i = 0; i < ivs.size(); i++) {
                hbs.get(i).setSpacing(100);
            }
//                Label l2 = new Label();
//                l2.setText(als.get(2).getNom_association());
//                hb2.getChildren().addAll(ivs.get(2),labels.get(2));
//                hbs.add(hb2);
//                
//                hb3.getChildren().addAll(ivs.get(3),l);
//                hbs.add(hb3);

//            LVlist.setItems(as.getAllAssociationImages());
            LVlist.setItems(hbs);

        } catch (SQLException ex) {
            Logger.getLogger(FormationFrontController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FormationFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }*/
 /*   public ArrayList<ImageView> ListImagesf = new ArrayList<>();
    public ArrayList<Label> ListTextflowf = new ArrayList<>();
    public ArrayList<Label> Listlabeltitleformation = new ArrayList<>();
    public ArrayList<Label> Listdateformation = new ArrayList<>();
    public ArrayList<AnchorPane> Listpaneformation = new ArrayList<>();
   //  ObservableList<Pane> panes = FXCollections.observableArrayList();
     //ObservableList<ImageView> ivs = sf.getAllFormationsImages();

    //public ArrayList<AnchorPane> ListPaneformationsfx = new ArrayList<>();
   

    private void getUserData(int CurrentFormation) throws FileNotFoundException {
        Listpaneformation.add(Paneformationsfx);
        Listpaneformation.add(Paneformationsfx);
        Listpaneformation.add(Paneformationsfx);

        ListImagesf.add(imageformationpanefx);
        ListImagesf.add(imageformationpanefx);
        ListImagesf.add(imageformationpanefx);

        ListTextflowf.add(typeformationspanefx);
        ListTextflowf.add(typeformationspanefx1);
        ListTextflowf.add(typeformationspanefx2);
        Listlabeltitleformation.add(nomformationspanefx);
        Listlabeltitleformation.add(nomformationspanefx1);
        Listlabeltitleformation.add(nomformationspanefx2);

        Listdateformation.add(Dateformationinterface);
        Listdateformation.add(Dateformationinterface1);
        Listdateformation.add(Dateformationinterface2);

        //int Nombre = sf.numberformation();
                   /* ObservableList<Label> labels = FXCollections.observableArrayList();
//        int i = CurrentEvent;
              ObservableList<HBox> hbs = FXCollections.observableArrayList();
            HBox hb = new HBox();
            HBox hb2 = new HBox();
            HBox hb3 = new HBox();
   final double MAX_FONT_SIZE = 30.0;

            for (int i = 0; i < ivs.size(); i++) {
                //hb.getChildren().addAll(ivs.get(0),labels.get(0));
                labels.get(i).setFont(new Font(MAX_FONT_SIZE));
                labels.get(i).setPadding(new Insets(70, 0, 0, 0));
                hbs.add(new HBox(ivs.get(i), labels.get(i)));

            }
            for (int i = 0; i < ivs.size(); i++) {
                hbs.get(i).setSpacing(80);
            }*/
      /*  for (i = CurrentFormation; i < CurrentFormation + 3; i++) {
            System.out.println(data.get(i).getImage());
           //  fis = new FileInputStream(fill);
           

         //  Image image = new Image("http://localhost/java/ProjetPidev/src/images" + data.get(i).getImage());
//           Image image = new Image(fill.toURI().toString()+ data.get(i).getImage());
  //             ListImagesf.get(i).setImage(image);
           Listlabeltitleformation.get(i).setText(data.get(i).getNom());
            Listdateformation.get(i).setText(data.get(i).getDate().toString());
//             ListTextflowf.get(i).setText(data.get(i).getLieu());
            Listpaneformation.get(i).setVisible(true);

        }
    }*/

    //@FXML
    //private void movetopage(MouseEvent event) throws IOException {
       /*  ServiceFormation sf = new ServiceFormation();
        HBox hb = new HBox();
        hb = LVlist.getSelectionModel().getSelectedItem();
           try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLAfficherFormationDetails.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
    } catch(IOException e) {
        e.printStackTrace();
    }*/
        // HBox f = LVlist.getSelectionModel().getSelectedItem();
        
       // ServiceFormation sf = new ServiceFormation();
       // nom.setText(f.getNom());
        //type.setText(f.getType());
        //date.setDate(f.getDate());
       // Date.setText(f.getDate().totring());
                        
      //   LVlist.setItems(hbs);
//System.out.println(f.getNom());

    @FXML
    private void Appuie(MouseEvent event) throws SQLException, IOException {
          System.out.println("aaaaaaaaaaaaaaaaaaaa");
        Formation f = table.getSelectionModel().getSelectedItem();
        
        ServiceFormation sf = new ServiceFormation();
        Date.setText(f.getDate().toString());
        type.setText(f.getType());
         image.setImage(sf.getFormationImageByID(f.getId()));
       description.setText(f.getDescription());
        lieu.setText(f.getLieu());
                        
        
       System.out.println(f.getNom());

         
        image.setImage(sf.getFormationImageByID(f.getId()));
    }

    @FXML
    private void deconnexion(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLLogin.fxml"));
        Parent root = loader.load();
        FXMLLoginController acc = loader.getController();
        deconnexion.getScene().setRoot(root);
    }
    
    
    
    }





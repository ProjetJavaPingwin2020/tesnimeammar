package Services;

import Entity.Formateur;
import Entity.Formation;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.ConnexionBase;

/**
 *
 * @author asus
 */
public class ServiceFormateur {

    private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    //private Connection con;
    public ServiceFormateur() {
        cnx = ConnexionBase.getInstance().getCnx();
    }


     public static int ajouter(Formateur t) {

        int st = 0;

        try {
            String requeteInsert = "INSERT INTO `formateur` (`nom`, `prenom`, `image`) VALUES ( ?,?,?);";
            Connection con = ConnexionBase.getInstance().getCnx();
            PreparedStatement pre = (PreparedStatement) con.prepareStatement(requeteInsert);
            pre.setString(1, t.getNom());
            pre.setString(2, t.getPrenom());
           // pre.setString(4, t.getNomImage());
            pre.setBinaryStream(3, t.getImage());

            st = pre.executeUpdate();
            System.out.println("formateur ajouté avec succés");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return st;

    }

    public static int delete(String nom) {
        int st = 0; //st mtaa l aada hahaha
        try {
            String req = "delete from formateur where nom='" + nom + "'";
            Connection con = ConnexionBase.getInstance().getCnx();
            PreparedStatement pre = (PreparedStatement) con.prepareStatement(req);
            st = pre.executeUpdate(req);
            System.out.println("Formateur supprimé avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return st;
    }

    public static int update(Formateur f, String name) {
        int st = 0;
        try {
          String req = "UPDATE Formateur SET `nom`='" + f.getNom() + "', `prenom`='" + f.getPrenom()+ "', `image`='" + f.getImage()  + "' WHERE `nom` ='" + name + "'";
            Connection con = ConnexionBase.getInstance().getCnx();
            PreparedStatement pre = (PreparedStatement) con.prepareStatement(req);
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return st;
    }

    public static Formateur getFormateur(String name) throws SQLException {
        String req = "SELECT * from formateur where nom='" + name + "'";
        Connection con = ConnexionBase.getInstance().getCnx();
        PreparedStatement pre = (PreparedStatement) con.prepareStatement(req);
        Formateur f = new Formateur();
        ResultSet rs = pre.executeQuery(req);

        while (rs.next()) {

            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
           // String img = rs.getString("img");
           // String Image = rs.getString("Image");
            InputStream image = rs.getBinaryStream("image");

            Formateur ff = new Formateur(nom, prenom, image);
            f = ff;
        }
        return f;
    }
    
    public static List<Formateur> readAll() throws SQLException {
        List<Formateur> arr = new ArrayList<Formateur>();
        String req = "SELECT* from Formateur";
        Connection con = ConnexionBase.getInstance().getCnx();
        PreparedStatement pre = (PreparedStatement) con.prepareStatement(req);

        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            Formateur f = new Formateur();
            
            f.setNom(rs.getString(2));
            f.setPrenom(rs.getString(3));
           // f.setImg(rs.getString(4));
            //f.setImage(rs.getBinaryStream(4));
            
            arr.add(f);

        }
        return arr;
    }
    
     public Image getFormateurImageByID(int id) throws SQLException, FileNotFoundException, IOException {
        PreparedStatement ps = cnx.prepareStatement("SELECT image FROM formateur WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            InputStream is = rs.getBinaryStream("image");
            OutputStream os = new FileOutputStream(new File("photo.jpg"));
            byte[] content = new byte[1024];
            int size = 0;
            while ((size = is.read(content)) != -1) {
                os.write(content, 0, size);
            }
            os.close();
            is.close();

        }
        Image image = new Image("file:photo.jpg");
        return image;
    
    
    
     }
    public List<Formateur> readformateur() throws SQLException {
        List<Formateur> AL = new ArrayList<>();
        String req = "SELECT* from formateur";
        Connection con = ConnexionBase.getInstance().getCnx();
        PreparedStatement pre = (PreparedStatement) con.prepareStatement(req);

        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            String nom = rs.getString("nom");
            Formateur f = new Formateur(nom);
            AL.add(f);
        }
        return AL;
    }
    /*
    public List<Formateur> readfor() throws SQLException {
          
        List<Formateur> AL = new ArrayList<>();
        st = cnx.createStatement();
        ResultSet rs = pre.executeQuery("select * from formateur");
        while (rs.next()) {
            String nom = rs.getString("nom");
            Formateur t = new Formateur(nom);
            AL.add(t);
        }
        return AL;
    }*/
    
   /* public int findbynomformateur(String name) throws SQLException
    {
        String req= "SELECT * FROM formateur";
        st = cnx.createStatement();
        ResultSet res = st.executeQuery(req);
        while (res.next()){
            if(res.getString("nom").equals(name))
            {
                String val=res.getString("id");
                System.out.println(val);
                int valeur = Integer.parseInt(val);
                return valeur;
                       
                        }
        }
        int valeur=0;
        return valeur;
    }
  */
     private final String GET_All_Formateur = "select id,nom,prenom from formateur ";
     
     public ObservableList<Formateur> getAllFormateurs() throws SQLException {
        ObservableList<Formateur> d = FXCollections.observableArrayList();
        PreparedStatement ps = cnx.prepareStatement(GET_All_Formateur);
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            d.add(ResultsToFormateur(rs.getInt(1),rs.getString(2), rs.getString(3)));
        }

        return d;
    } 
     private Formateur ResultsToFormateur(int id,String nom, String prenom) {
       return new Formateur(id , nom, prenom);
    } 
}
    

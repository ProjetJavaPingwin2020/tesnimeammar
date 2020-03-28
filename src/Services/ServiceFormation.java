/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Formateur;
import Entity.Formation;
import Entity.FosUser;
import IServices.IService;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.ConnexionBase;

/**
 *
 * @author asus
 */
public class ServiceFormation implements IService<Formation>{

    private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    private ResultSet res ;

    public ServiceFormation() {
                cnx = ConnexionBase.getInstance().getCnx();

    }
    
    @Override
    public void ajouter(Formation f) throws SQLException {


            String requeteInsert = "INSERT INTO `formation` (`nom`, `type`, `date`,`lieu`, `description`,`heure`,`nbrplace`,`formateur`,`image`) VALUES ( ?,?,?,?,?,?,?,?,?);";
            Connection con = ConnexionBase.getInstance().getCnx();
            PreparedStatement pre = (PreparedStatement) con.prepareStatement(requeteInsert);
            pre.setString(1, f.getNom());
            pre.setString(2, f.getType());
            pre.setDate(3, f.getDate());
            pre.setString(4, f.getLieu());
            pre.setString(5, f.getDescription());
            pre.setString(6, f.getHeure());
             
            pre.setInt(7, f.getNbrplace());
             pre.setInt(8, f.getFormateur());
            pre.setBinaryStream(9, f.getImage());
            //  pre.setString(9, f.getFormateur().getNom());
           
                pre.executeUpdate();
            System.out.println("Formation ajoutée avec succés");        
    }

    
      public int findid(String id) {
          int idf = -1;
         String requete = " select * from formateur where nom='"+id+"'" ;
        try {
           
            st = cnx.createStatement();
            res=st.executeQuery(requete);
            if (res.next())
            {idf = res.getInt("id");
                System.out.println(idf);}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFormation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idf ;
        
    }
    /*  public List<Formateur> readfor() throws SQLException {
          
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
     
    @Override
    public List<Formation> readAll() throws SQLException {
        List<Formation> AL = new ArrayList<>();
        st = cnx.createStatement();
        ResultSet rs = st.executeQuery("select * from formation");
        while (rs.next()) {
            //int id = rs.getInt(1);
            String nom = rs.getString("nom");
             String type = rs.getString("type");
             Date date=rs.getDate("date");
             String lieu = rs.getString("lieu");
             String description = rs.getString("description");
             String heure = rs.getString("heure");
            InputStream image = rs.getBinaryStream("image");
             int nbrplace = rs.getInt(8);
             
           // String formateur = rs.getString(9);
              int formateur = rs.getInt(9);
             
            Formation f = new Formation(nom, type,date,lieu,description, heure,image,nbrplace,formateur);
            AL.add(f);
        }
        return AL;
    }
    public Image getFormationImageByID(int id) throws SQLException, FileNotFoundException, IOException {
        PreparedStatement ps = cnx.prepareStatement("SELECT image FROM formation WHERE id = ?");
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

    private final String GET_All_Formation = "select id,nom,type,date,lieu,description,heure,nbrplace,formateur from formation ";
     
     public ObservableList<Formation> getAllFormations() throws SQLException {
        ObservableList<Formation> data = FXCollections.observableArrayList();
        PreparedStatement ps = cnx.prepareStatement(GET_All_Formation);
     // UserSevice usrs = new UserSevice();
      
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            FosUser u = new FosUser();
           // //u=usrs.getUserByid
            data.add(ResultsToFormation(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getDate(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getInt(9)));
        }

        return data;
    } 
     private Formation ResultsToFormation(int id,String nom, String type,Date date,String lieu,String description,String heure,int nbrplace,int formateur) {
       return new Formation(id , nom, type,date,lieu,description,heure,nbrplace,formateur);
    } 
    /*@Override
    public void delete(int id) throws SQLException {
       PreparedStatement PS = cnx.prepareStatement("DELETE FROM `test1.1`.`formation` WHERE `id`=?");
        PS.setInt(1,id);
        PS.executeUpdate();
    }
*/
     public static int delete(String nom) {
        int st = 0; //st mtaa l aada hahaha
        try {
            String req = "delete from formation where nom='" + nom + "'";
            Connection con = ConnexionBase.getInstance().getCnx();
            PreparedStatement pre = (PreparedStatement) con.prepareStatement(req);
            st = pre.executeUpdate(req);
            System.out.println("Formation supprimé avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return st;
    }
    @Override
    public void update(Formation t, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   /*  private final String SupprimerFormation = "delete from formation where id=?";
      public void delete(int id) throws SQLException {
        PreparedStatement ps = cnx.prepareStatement(SupprimerFormation);
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("Formation supprimée avec succès");

    }*/
    
  /*  @Override
    public void delete(int id) throws SQLException {
        PreparedStatement PS = cnx.prepareStatement("DELETE FROM `test1.1`.`formation` WHERE `id`=?");
        PS.setInt(1,id);
        PS.executeUpdate();
    }*/
  /*  public static int delete(String nom) {
        int st = 0; //st mtaa l aada hahaha
        try {
            String req = "delete from formation where nom='" + nom + "'";
            Connection con = ConnexionBase.getInstance().getCnx();
            PreparedStatement pre = (PreparedStatement) con.prepareStatement(req);
            st = pre.executeUpdate(req);
            System.out.println("Formation supprimé avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return st;
    }*/


   /*@Override
    public void delete(int id) throws SQLException {
        PreparedStatement PS = cnx.prepareStatement("DELETE FROM `test1.1`.`formation` WHERE `id`=?");
        PS.setInt(1,id);
        PS.executeUpdate();
    }*/

   /* @Override
    public void delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    public ObservableList<ImageView> getAllFormationsImages() throws SQLException, FileNotFoundException, IOException {
        ObservableList<ImageView> img = FXCollections.observableArrayList();
        PreparedStatement ps = cnx.prepareStatement("SELECT image FROM formation");
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
            Image image = new Image("file:photo.jpg");
            ImageView iv = new ImageView(image);
            img.add(iv);
        }
        return img;
    }
    @Override
    public void delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.InputStream;
import java.sql.Date;


/**
 *
 * @author asus
 */
public class Formation {

    private int id;
    private String nom;
    private String type;
    private Date date;
    private String lieu;
    private String description;
    private String heure;
   // private String img;
   // private String nomImage;
    private int nbrplace;
    private int formateur;
     private InputStream image;
    

    public Formation() {
    }

    public Formation(int id, String nom, String type, Date date, String lieu, String description, String heure, InputStream image, int nbrplace, int formateur) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.date = date;
        this.lieu = lieu;
        this.description = description;
        this.heure = heure;
        this.image = image;
        this.nbrplace = nbrplace;
        this.formateur = formateur;
    }

    public Formation(int id, String nom, String type, Date date, String lieu, String description, String heure, int nbrplace, int formateur, InputStream image) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.date = date;
        this.lieu = lieu;
        this.description = description;
        this.heure = heure;
        this.nbrplace = nbrplace;
        this.formateur = formateur;
        this.image = image;
    }

    public Formation(String nom, String type, Date date, String lieu, String description, String heure, int nbrplace, int formateur, InputStream image) {
        this.nom = nom;
        this.type = type;
        this.date = date;
        this.lieu = lieu;
        this.description = description;
        this.heure = heure;
        this.nbrplace = nbrplace;
        this.formateur = formateur;
        this.image = image;
    }

    public Formation(String nom, String type, Date date, String lieu, String description, String heure, int nbrplace, int formateur) {
        this.nom = nom;
        this.type = type;
        this.date = date;
        this.lieu = lieu;
        this.description = description;
        this.heure = heure;
        this.nbrplace = nbrplace;
        this.formateur = formateur;
    }

    
  

    

    
    

    public Formation(String nom, String type, Date date, String lieu, String description, String heure, InputStream image, int nbrplace, int formateur) {
        this.nom = nom;
        this.type = type;
        this.date = date;
        this.lieu = lieu;
        this.description = description;
        this.heure = heure;
        this.image = image;
        this.nbrplace = nbrplace;
        this.formateur = formateur;
    }

    public Formation(int id, String nom, String type, Date date, String lieu, String description, String heure, int nbrplace, int formateur) {
      
        this.id=id;
        this.nom = nom;
        this.type = type;
        this.date = date;
        this.lieu = lieu;
        this.description = description;
        this.heure = heure;
        this.nbrplace = nbrplace;
        this.formateur = formateur;
    }

    public Formation(int id, String nom, String type, Date date, String lieu, String description, String heure, int nbrplace) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Formation(String nom, String type, Date date, String lieu, String description, String heure, int nbrplace) {
        this.nom = nom;
        this.type = type;
        this.date = date;
        this.lieu = lieu;
        this.description = description;
        this.heure = heure;
        this.nbrplace = nbrplace;
    }

    

    @Override
    public String toString() {
        return "Formation{" + "id=" + id + ", nom=" + nom + ", type=" + type + ", date=" + date + ", lieu=" + lieu + ", description=" + description + ", heure=" + heure + ", image=" + image + ", nbrplace=" + nbrplace + ", formateur=" + formateur + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

   

    public int getNbrplace() {
        return nbrplace;
    }

    public void setNbrplace(int nbrplace) {
        this.nbrplace = nbrplace;
    }

    public int getFormateur() {
        return formateur;
    }

    public void setFormateur(int formateur) {
        this.formateur = formateur;
    }
  

    
    
   

}

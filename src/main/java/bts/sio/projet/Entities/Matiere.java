package bts.sio.projet.Entities;

import bts.sio.projet.Tools.ConnexionBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Matiere {
    private int id;
    private String designation;
    private String sousMatiere;
    private Connection unCnx;
    private PreparedStatement ps;
    private ResultSet rs;
    public Matiere()
    {
        unCnx = ConnexionBDD.getCnx();
    }

    public Matiere(int id, String designation, String sousMatiere) {
        this.id = id;
        this.designation = designation;
        this.sousMatiere = sousMatiere;
    }

    // Fonction pour avoir les objet matières
    public ObservableList<Matiere> GetAllMatiereObj() throws SQLException {
        ObservableList<Matiere> lesMatieresObj = FXCollections.observableArrayList();

        //Requête SQL
        ps = unCnx.prepareStatement("SELECT matiere.id, matiere.designation, matiere.sous_matiere \n"
                +"FROM `matiere`\n");

        rs = ps.executeQuery();
        while(rs.next())
        {
            //instancier un objet
            Matiere laMatiere = new Matiere(rs.getInt(1), rs.getString(2), rs.getString(3));
            //ajouter à une collection
            lesMatieresObj.add(laMatiere);
        }
        return lesMatieresObj;
    }

    // Fonction pour avoir les matières
    public ObservableList<String> GetAllMatiere() throws SQLException {
        ObservableList<String> lesMatieres = FXCollections.observableArrayList();

        //Requête SQL
        ps = unCnx.prepareStatement("SELECT matiere.designation \n"
                +"FROM `matiere`\n");

        rs = ps.executeQuery();
        while(rs.next())
        {
            //ajouter à une collection
            lesMatieres.add(rs.getString("designation"));
        }
        return lesMatieres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {this.id = id;}

    public String getDesignation() {return designation;}

    public void setDesignation(String designation) {this.designation = designation;}

    public String getSousMatiere() {return sousMatiere;}

    public void setSousMatiere(String sousMatiere) {this.sousMatiere = sousMatiere;}
}

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


    public Matiere(int id, String designation, String sousMatiere) {
        this.id = id;
        this.designation = designation;
        this.sousMatiere = sousMatiere;
    }

    // Fonction pour avoir les objet matières

    public int getId() {
        return id;
    }

    public void setId(int id) {this.id = id;}

    public String getDesignation() {return designation;}

    public void setDesignation(String designation) {this.designation = designation;}

    public String getSousMatiere() {return sousMatiere;}

    public void setSousMatiere(String sousMatiere) {this.sousMatiere = sousMatiere;}
}

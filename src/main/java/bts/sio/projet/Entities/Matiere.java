package bts.sio.projet.Entities;

import bts.sio.projet.Tools.ConnexionBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Matiere {
    private int idMatiere;
    private String designation;
    private String sousMatiere;
    private String nouvelleDesignation;

    public Matiere(int idMatiere, String designation, String sousMatiere) {
        this.idMatiere = idMatiere;
        this.designation = designation;
        this.sousMatiere = sousMatiere;
    }

    public Matiere(String designation, String sousMatiere, String nouvelleDesignation) {
        this.designation = designation;
        this.sousMatiere = sousMatiere;
        this.nouvelleDesignation = nouvelleDesignation;
    }

    public Matiere(String sousMatiere) {
        this.sousMatiere = sousMatiere;
    }

    public void setId(int idMatiere) {this.idMatiere = idMatiere;}

    public String getDesignation() {return designation;}

    public void setDesignation(String designation) {this.designation = designation;}

    public String getSousMatiere() {return sousMatiere;}

    public void setSousMatiere(String sousMatiere) {this.sousMatiere = sousMatiere;}

    public int getIdMatiere() {
        return idMatiere;
    }

    public String getNouvelleDesignation() {
        return nouvelleDesignation;
    }
}

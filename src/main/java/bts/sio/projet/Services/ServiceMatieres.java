package bts.sio.projet.Services;

import bts.sio.projet.Entities.Matiere;
import bts.sio.projet.Entities.User;
import bts.sio.projet.Tools.ConnexionBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceMatieres {
    private Connection unCnx;
    private PreparedStatement ps;
    private ResultSet rs;
    public ServiceMatieres()
    {
        unCnx = ConnexionBDD.getCnx();
    }

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

    // Fonction pour avoir les sous matières
    public ObservableList<String> GetAllSousMatiere(String designation) throws SQLException {
        ObservableList<String> lesSousMatieres = FXCollections.observableArrayList();

        ps = unCnx.prepareStatement("SELECT matiere.sous_matiere \n"
                + "FROM matiere\n"
                + "WHERE matiere.designation = ?");

        ps.setString(1, designation);
        rs = ps.executeQuery();

        rs.next();
        String sousMatiere = rs.getString("sous_matiere");
        String[] splitSousMatiere = sousMatiere.split("#");
        lesSousMatieres.addAll(splitSousMatiere);

        return lesSousMatieres;
    }
}

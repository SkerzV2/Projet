package bts.sio.projet.Tools.Services;

import bts.sio.projet.Entities.Matiere;
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

    public ObservableList<Matiere> GetAllMatiereObj() throws SQLException
    {
        ObservableList<Matiere> lesMatieresObj = FXCollections.observableArrayList();

        ps = unCnx.prepareStatement("SELECT matiere.id, matiere.designation, matiere.sous_matiere \n"
                +"FROM `matiere`\n");

        rs = ps.executeQuery();
        while(rs.next())
        {
            Matiere laMatiere = new Matiere(rs.getInt(1), rs.getString(2), rs.getString(3));
            lesMatieresObj.add(laMatiere);
        }
        return lesMatieresObj;
    }

    // Fonction pour avoir les matières
    public ObservableList<String> GetAllMatiere() throws SQLException
    {
        ObservableList<String> lesMatieres = FXCollections.observableArrayList();

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

    // Fonction pour avoir les sous matières
    // En cours
    public ObservableList<String> GetAllSousMatiere(String designation) throws SQLException
    {
        ObservableList<String> lesSousMatieres = FXCollections.observableArrayList();

        ps = unCnx.prepareStatement("SELECT matiere.sous_matiere FROM matiere WHERE matiere.designation = ?");
        ps.setString(1, designation);
        rs = ps.executeQuery();

        while (rs.next())
        {
            String sousMatiere = rs.getString("sous_matiere");
            String[] splitSousMatiere = sousMatiere.split("#");
            for (String uneSousMatiere : splitSousMatiere)
            {
                if (!uneSousMatiere.isEmpty())
                {
                    lesSousMatieres.add(uneSousMatiere);
                }
            }
        }
        return lesSousMatieres;
    }

    public ObservableList<Matiere> GetAllSousMatiereOBJ(String designation) throws SQLException
    {
        ObservableList<Matiere> lesSousMatieres = FXCollections.observableArrayList();

        ps = unCnx.prepareStatement("SELECT matiere.sous_matiere FROM matiere WHERE matiere.designation = ?");
        ps.setString(1, designation);
        rs = ps.executeQuery();

        while (rs.next())
        {
            String sousMatiere = rs.getString("sous_matiere");
            String[] splitSousMatiere = sousMatiere.split("#");
            for (String uneSousMatiere : splitSousMatiere)
            {
                if (!uneSousMatiere.isEmpty())
                {
                    Matiere uneMatiere = new Matiere(uneSousMatiere);
                    lesSousMatieres.add(uneMatiere);
                }
            }
        }
        return lesSousMatieres;
    }
}

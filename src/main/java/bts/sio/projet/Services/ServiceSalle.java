package bts.sio.projet.Services;

import bts.sio.projet.Entities.Matiere;
import bts.sio.projet.Entities.Salle;
import bts.sio.projet.Tools.ConnexionBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceSalle {
    private Connection unCnx;
    private PreparedStatement ps;
    private ResultSet rs;
    public ServiceSalle()
    {
        unCnx = ConnexionBDD.getCnx();
    }

    public void CreeSalle(Salle laSalle) throws SQLException {
        ps = unCnx.prepareStatement("INSERT INTO Salle ( id, code_salle , etage)" +
                "VALUES (?,?,?)");
        ps.setInt(1, laSalle.getId());
        ps.setString(2, laSalle.getCodeSalle());
        ps.setInt(3, laSalle.getEtage());

        ps.executeUpdate();
    }

    public boolean VerifSalle(Salle laSalle) throws SQLException {
        boolean existe = false;
        ps = unCnx.prepareStatement("SELECT salle.id, salle.code_salle, salle.etage\n" +
                "FROM salle\n" +
                "WHERE salle.id = ?\n" +
                "AND salle.code_salle = ?\n" +
                "AND salle.etage = ?");
        ps.setInt(1, laSalle.getId());
        ps.setString(2, laSalle.getCodeSalle());
        ps.setInt(3, laSalle.getEtage());

        rs = ps.executeQuery();
        if(rs.next()){
            existe = true;
        }
        return existe;
    }

    public ObservableList<String> GetAllSalle() throws SQLException
    {
        ObservableList<String> lesSalles = FXCollections.observableArrayList();

        ps = unCnx.prepareStatement("SELECT salle.code_salle \n"
                +"FROM `salle`\n");

        rs = ps.executeQuery();
        while(rs.next())
        {
            lesSalles.add(rs.getString("code_salle"));
        }
        return lesSalles;
    }
    public ObservableList<Integer> GetAllIdSalle() throws SQLException
    {
        ObservableList<Integer> lesIdSalles = FXCollections.observableArrayList();

        ps = unCnx.prepareStatement("SELECT salle.id \n"
                +"FROM `salle`\n");

        rs = ps.executeQuery();
        while(rs.next())
        {
            lesIdSalles.add(rs.getInt("id"));
        }
        return lesIdSalles;
    }
    public void ModifierSalle(Salle laSalle) throws SQLException {
        ps = unCnx.prepareStatement("UPDATE salle SET salle.id = ?, salle.code_salle = ?, salle.etage = ? "+
                " WHERE salle.id = ? ");
        ps.setInt(1, laSalle.getNvId());
        ps.setString(2, laSalle.getCodeSalle());
        ps.setInt(3, laSalle.getEtage());
        ps.setInt(4, laSalle.getId());

        ps.executeUpdate();
    }
}

package bts.sio.projet.Services;

import bts.sio.projet.Entities.Matiere;
import bts.sio.projet.Entities.Salle;
import bts.sio.projet.Tools.ConnexionBDD;

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
}

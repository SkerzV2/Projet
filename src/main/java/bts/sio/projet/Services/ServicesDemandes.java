package bts.sio.projet.Services;

import bts.sio.projet.Entities.Demande;
import bts.sio.projet.Entities.User;
import bts.sio.projet.Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServicesDemandes {
    private Connection unCnx;
    private PreparedStatement ps;
    private ResultSet rs;
    public ServicesDemandes()
    {
        unCnx = ConnexionBDD.getCnx();
    }

    public void creationDemande(Demande uneDemande) throws SQLException {
        ps = unCnx.prepareStatement("INSERT INTO demande (demande.date_fin_demande, demande.date_updated, demande.sous_matiere, demande.id_user, demande.id_matiere, demande.status)\n" +
                "        VALUES (?, ?, ?, ?, ?, ?);");
        // Exécuter la requête
        ps.setString(1, uneDemande.getDateFin());
        ps.setString(2, uneDemande.getDateDebut());
        ps.setString(3, uneDemande.getSousMatiere());
        ps.setInt(4, uneDemande.getIdUser());
        ps.setInt(5, uneDemande.getIdMatiere());
        ps.setInt(6, 1);
        // Parcourir la rs
        ps.executeUpdate();
    }
}

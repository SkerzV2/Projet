package bts.sio.projet.Services;

import bts.sio.projet.Entities.Competence;
import bts.sio.projet.Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceCompetences
{
    private Connection unCnx;
    private PreparedStatement ps;
    private ResultSet rs;
    public ServiceCompetences()
    {
        unCnx = ConnexionBDD.getCnx();
    }

    public void creationCompetence(Competence uneCompetence, int idMatiere) throws SQLException, SQLException {
        ps = unCnx.prepareStatement("INSERT INTO competence (competence.sous_matiere, competence.id_user,competence.id_Matiere, competence.statut)\n" +
                "        VALUES (?, ?, ?, ?);");
        // Exécuter la requête
        ps.setString(1, uneCompetence.getMatiereSecondaire());
        ps.setInt(2, uneCompetence.getIdUser());
        ps.setInt(3, idMatiere);
        ps.setInt(4,1);
        // Parcourir la rs
        ps.executeUpdate();
    }
}

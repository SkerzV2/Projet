package bts.sio.projet.Tools.Services;

import bts.sio.projet.Entities.Demande;
import bts.sio.projet.Tools.ConnexionBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceSoutients {
        private Connection unCnx;
        private PreparedStatement ps;
        private ResultSet rs;
        public ServiceSoutients()
        {
            unCnx = ConnexionBDD.getCnx();
        }
    public ObservableList<Demande> getAllDemandesTv(int idUser) throws SQLException
    {
        ps = unCnx.prepareStatement("SELECT demande.id, demande.id_matiere, demande.date_updated, demande.date_fin_demande, matiere.designation, demande.sous_matiere "
                + "FROM demande "
                + "JOIN matiere ON demande.id_matiere = matiere.id "
                + "WHERE demande.id_user = ?");

        // Exécuter la requête
        ps.setInt(1, idUser);
        rs = ps.executeQuery();

        ObservableList<Demande> lesDemandes = FXCollections.observableArrayList();

        while (rs.next())
        {
            String matiereDesignation = rs.getString("matiere.designation");
            String sousMatiere = rs.getString("demande.sous_matiere");
            int idMatiere = rs.getInt("demande.id_matiere");
            String dateDebut = rs.getString("demande.date_updated");
            String dateFin = rs.getString("demande.date_fin_demande");
            int idDemande = rs.getInt("demande.id");

            Demande uneDemande = new Demande(dateDebut, dateFin, sousMatiere,idUser ,idMatiere, matiereDesignation, idDemande);

            lesDemandes.add(uneDemande);
        }
        return lesDemandes;
    }
}

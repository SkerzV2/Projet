package bts.sio.projet.Tools.Services;

import bts.sio.projet.Entities.Demande;
import bts.sio.projet.Entities.User;
import bts.sio.projet.Tools.ConnexionBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        ArrayList<String> mesCompetences;
        mesCompetences=GetMesCompetences(idUser);
        System.out.println(mesCompetences);
        ps = unCnx.prepareStatement("SELECT user.id, user.nom, user.prenom, user.niveau, demande.id, demande.id_matiere, demande.date_updated, demande.date_fin_demande, matiere.designation, demande.sous_matiere "
                + "FROM demande "
                + "JOIN matiere ON demande.id_matiere = matiere.id "
                + "JOIN user ON user.id = demande.id_user "
                + "WHERE demande.id_user != ? "); // Exclut l'utilisateur actuel

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
            int idUserdem = rs.getInt("user.id");
            String nomUser = rs.getString("user.nom");
            String prenomUser = rs.getString("user.prenom");
            String niveauUser = rs.getString("user.niveau");
            String[] splitSousMatiereDem = sousMatiere.split("#");
            String sousMatiereDem="";
            for (String item : splitSousMatiereDem) {
                if (!item.isEmpty()) {
                    for (String competence : mesCompetences) {
                        if (item.equals(competence)) {
                            sousMatiereDem += "#" + item;
                        }
                    }
                }
            }
                System.out.println(sousMatiereDem);
                if (!sousMatiereDem.equals("")) {
                    Demande uneDemande = new Demande(dateDebut, dateFin, sousMatiereDem, idMatiere, matiereDesignation, idDemande, idUserdem, nomUser, prenomUser, niveauUser);

                    lesDemandes.add(uneDemande);
                }
            }
        return lesDemandes;
    }
    public ArrayList GetMesCompetences(int idUser) throws SQLException {
        ArrayList<String> mesCompetences = new ArrayList<>();
        ps = unCnx.prepareStatement("SELECT competence.sous_matiere "
                + "FROM competence "
                + "WHERE competence.id_user = ?");

        // Exécuter la requête
        ps.setInt(1, idUser);
        rs = ps.executeQuery();
        while (rs.next()) {
            String competences = rs.getString("sous_matiere");
            String[] splitSousMatiere = competences.split("#");
            for (String item : splitSousMatiere) {
                if (!item.isEmpty()) {
                    mesCompetences.add(item);
                }
            }
        }
        return mesCompetences ;
    }
}

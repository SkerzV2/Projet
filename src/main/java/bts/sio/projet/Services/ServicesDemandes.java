package bts.sio.projet.Services;

import bts.sio.projet.Entities.Demande;
import bts.sio.projet.Entities.Matiere;
import bts.sio.projet.Tools.ConnexionBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.TreeMap;

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

    public void modificationDemande(Demande demandeModifiee) throws SQLException {
        String sql = "UPDATE demande "
                + "SET demande.date_updated = ?, demande.date_fin_demande = ?,  demande.sous_matiere = ?, demande.id_user = ?, demande.id_matiere = ? "
                +" WHERE id_demande = ?";

        ps = unCnx.prepareStatement(sql);

        // Remplacez les paramètres de la requête par les valeurs de demandeModifiee
        ps.setString(1, demandeModifiee.getDateDebut());
        ps.setString(2, demandeModifiee.getDateFin());
        ps.setString(3, demandeModifiee.getSousMatiere());
        ps.setInt(4, demandeModifiee.getIdUser());
        ps.setInt(5, demandeModifiee.getIdMatiere());
        ps.setObject(6, demandeModifiee);

        // Exécutez la requête de mise à jour
        ps.executeUpdate();
    }


    public HashMap<String, TreeMap<String, ObservableList<String>>> getAllDemandes(int idUser) throws SQLException {
        ps = unCnx.prepareStatement("SELECT demande.id_matiere, demande.date_updated, demande.date_fin_demande, matiere.designation, demande.sous_matiere "
                + "FROM demande "
                + "JOIN matiere ON demande.id_matiere = matiere.id "
                + "WHERE demande.id_user = ?");

        // Exécuter la requête
        ps.setInt(1, idUser);
        rs = ps.executeQuery();

        HashMap<String, TreeMap<String, ObservableList<String>>> lesDemandes = new HashMap<>();

        while (rs.next()) {
            String matiereDesignation = rs.getString("matiere.designation");
            String sousMatiere = rs.getString("demande.sous_matiere");
            int idMatiere = rs.getInt("demande.id_matiere");
            String dateDebut = rs.getString("demande.date_updated");
            String dateFin = rs.getString("demande.date_fin_demande");

            Matiere matiere = new Matiere(idMatiere, matiereDesignation, sousMatiere);

            if (!lesDemandes.containsKey(matiereDesignation)) {
                ObservableList<String> lesSousMatieres = FXCollections.observableArrayList();
                lesSousMatieres.add(sousMatiere);
                TreeMap<String, ObservableList<String>> lesDates = new TreeMap<>();
                lesDates.put(dateDebut + " au " + dateFin, lesSousMatieres);
                lesDemandes.put(matiereDesignation, lesDates);
            } else {
                TreeMap<String, ObservableList<String>> lesDates = lesDemandes.get(matiereDesignation);
                if (!lesDates.containsKey(dateDebut)) {
                    ObservableList<String> lesSousMatieres = FXCollections.observableArrayList();
                    lesSousMatieres.add(sousMatiere);
                    lesDates.put(dateDebut + " au " + dateFin, lesSousMatieres);
                } else {
                    lesDates.get(dateDebut + " au " + dateFin).add(sousMatiere);
                }
            }
        }
        return lesDemandes;
    }


    public ObservableList<Demande> getAllDemandesTv(int idUser) throws SQLException {
        ps = unCnx.prepareStatement("SELECT demande.id_matiere, demande.date_updated, demande.date_fin_demande, matiere.designation, demande.sous_matiere "
                + "FROM demande "
                + "JOIN matiere ON demande.id_matiere = matiere.id "
                + "WHERE demande.id_user = ?");

        // Exécuter la requête
        ps.setInt(1, idUser);
        rs = ps.executeQuery();

        ObservableList<Demande> lesDemandes = FXCollections.observableArrayList();

        while (rs.next()) {
            String matiereDesignation = rs.getString("matiere.designation");
            String sousMatiere = rs.getString("demande.sous_matiere");
            int idMatiere = rs.getInt("demande.id_matiere");
            String dateDebut = rs.getString("demande.date_updated");
            String dateFin = rs.getString("demande.date_fin_demande");

            Demande uneDemande = new Demande(dateDebut, dateFin, sousMatiere,idUser ,idMatiere, matiereDesignation);

            lesDemandes.add(uneDemande);
        }
        return lesDemandes;
    }
}

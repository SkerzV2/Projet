package bts.sio.projet.Tools.Services;

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

public class ServiceDemandes {
    private Connection unCnx;
    private PreparedStatement ps;
    private ResultSet rs;
    public ServiceDemandes()
    {
        unCnx = ConnexionBDD.getCnx();
    }

    public void creationDemande(Demande uneDemande) throws SQLException {
        ps = unCnx.prepareStatement("INSERT INTO demande (demande.date_fin_demande, demande.date_updated, demande.sous_matiere, demande.id_user, demande.id_matiere, demande.status)\n" +
                "        VALUES (?, ?, ?, ?, ?, ?);");
        ps.setString(1, uneDemande.getDateFin());
        ps.setString(2, uneDemande.getDateDebut());
        ps.setString(3, uneDemande.getSousMatiere());
        ps.setInt(4, uneDemande.getIdUser());
        ps.setInt(5, uneDemande.getIdMatiere());
        ps.setInt(6, 1);
        ps.executeUpdate();
        ps.close();
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
        ps.close();
    }


    public HashMap<String, TreeMap<String, ObservableList<String>>> getAllDemandes(int idUser) throws SQLException
    {
        ps = unCnx.prepareStatement("SELECT demande.id_matiere, demande.date_updated, demande.date_fin_demande, matiere.designation, demande.sous_matiere "
                + "FROM demande "
                + "JOIN matiere ON demande.id_matiere = matiere.id "
                + "WHERE demande.id_user = ?");

        ps.setInt(1, idUser);
        rs = ps.executeQuery();

        HashMap<String, TreeMap<String, ObservableList<String>>> lesDemandes = new HashMap<>();

        while (rs.next())
        {
            String matiereDesignation = rs.getString("matiere.designation");
            String sousMatiere = rs.getString("demande.sous_matiere");
            String dateDebut = rs.getString("demande.date_updated");
            String dateFin = rs.getString("demande.date_fin_demande");

            if (!lesDemandes.containsKey(matiereDesignation))
            {
                ObservableList<String> lesSousMatieres = FXCollections.observableArrayList();
                lesSousMatieres.add(sousMatiere);
                TreeMap<String, ObservableList<String>> lesDates = new TreeMap<>();
                lesDates.put(dateDebut + " au " + dateFin, lesSousMatieres);
                lesDemandes.put(matiereDesignation, lesDates);
            } else
            {
                TreeMap<String, ObservableList<String>> lesDates = lesDemandes.get(matiereDesignation);
                if (!lesDates.containsKey(dateDebut))
                {
                    ObservableList<String> lesSousMatieres = FXCollections.observableArrayList();
                    lesSousMatieres.add(sousMatiere);
                    lesDates.put(dateDebut + " au " + dateFin, lesSousMatieres);
                } else
                {
                    lesDates.get(dateDebut + " au " + dateFin).add(sousMatiere);
                }
            }
        }
        rs.close();
        ps.close();
        return lesDemandes;
    }

    public ObservableList<Demande> getAllSousMatieresDemande(int idDemande) throws SQLException {
        ObservableList<Demande> sousMatieres = FXCollections.observableArrayList();
        ps = unCnx.prepareStatement("SELECT demande.sous_matiere "
                + "FROM demande "
                + "JOIN matiere ON demande.id_matiere = matiere.id "
                + "WHERE demande.id = ?");

        // Exécuter la requête
        ps.setInt(1, idDemande);
        rs = ps.executeQuery();
        rs.next();
        String sousMatiere = rs.getString(1);
        String[] splitSousMatiere = sousMatiere.split("#");
        for (String uneSousMatiere : splitSousMatiere)
        {
            if (!uneSousMatiere.isEmpty())
            {
                Demande uneDemande=new Demande(uneSousMatiere);
                sousMatieres.add(uneDemande);
            }
        }
        return sousMatieres;
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
        rs.close();
        ps.close();
        return lesDemandes;
    }

    public void modifDemande(int idUser, String dateDebut, String dateFin, String lesSousMatieres, int idDemande) throws SQLException {
        // Vous devez exécuter une requête UPDATE pour modifier la demande.
        ps = unCnx.prepareStatement("UPDATE demande "
                + "SET date_updated = ?, date_fin_demande = ?, sous_matiere = ? "
                + "WHERE id_user = ? "
                + "AND demande.id = ? ");

        // Remplacez les "?" dans la requête par les valeurs que vous avez.
        ps.setString(1, dateDebut);
        ps.setString(2, dateFin);
        ps.setString(3, lesSousMatieres);
        ps.setInt(4, idUser);
        ps.setInt(5, idDemande);

        ps.executeUpdate();
        ps.close();
    }
    public boolean supprimerDemande(int idDemande) throws SQLException {
        ps = unCnx.prepareStatement("SELECT soutien.id FROM soutien\n" +
                "WHERE soutien.id_demande = ?");

        ps.setInt(1, idDemande);
        rs = ps.executeQuery();

        if (rs.next()) {
            ps.close();
            rs.close();
            return true;
        }else{
             ps = unCnx.prepareStatement("DELETE FROM demande "
                    + "WHERE id = ? ");

            ps.setInt(1, idDemande);

            ps.executeUpdate();
            ps.close();
        }
        return false;
    }
}

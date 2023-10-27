package bts.sio.projet.Services;

import bts.sio.projet.Entities.Demande;
import bts.sio.projet.Entities.Matiere;
import bts.sio.projet.Entities.User;
import bts.sio.projet.Tools.ConnexionBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
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

    public HashMap<Matiere,TreeMap<String, ObservableList<String>>> getAllDemandes(int idUser) throws SQLException {
        ps = unCnx.prepareStatement("SELECT demande.id_matiere, demande.date_updated, demande.date_fin_demande, matiere.designation, demande.sous_matiere "
                +"FROM demande "
                +"JOIN matiere ON demande.id_matiere = matiere.id "
                +"WHERE demande.id_user = ?");

        // Exécuter la requête
        ps.setInt(1, idUser);
        rs= ps.executeQuery();

        HashMap<Matiere, TreeMap<String, ObservableList<String>>> lesDemandes = new HashMap<>();

        while(rs.next())
        {
            String matiereDesignation = rs.getString("matiere.designation");
            String sousMatiere = rs.getString("demande.sous_matiere");
            int idMatiere = rs.getInt("demande.id_matiere");
            String dateDebut = rs.getString("demande.date_updated");
            String dateFin = rs.getString("demande.date_fin_demande");

            Matiere matiere = new Matiere(idMatiere, matiereDesignation, sousMatiere);

            if(!lesDemandes.containsKey(matiere))
            {
                ObservableList<String>lesSousMatieres = FXCollections.observableArrayList();
                lesSousMatieres.add(sousMatiere);
                TreeMap<String, ObservableList<String>> lesDates = new TreeMap<>();
                lesDates.put(dateDebut, lesSousMatieres);
                lesDemandes.put(matiere,lesDates);
            }
            else if(!lesDemandes.get(matiere).containsKey(dateDebut))
            {
                ObservableList<String>lesSousMatieres = FXCollections.observableArrayList();
                lesSousMatieres.add(sousMatiere);
                lesDemandes.get(dateDebut).put(dateDebut, lesSousMatieres);
            }
            else
            {
                lesDemandes.get(matiere).get(dateDebut).add(sousMatiere);
            }
        }
        return lesDemandes;
    }
}

package bts.sio.projet.Services;

import bts.sio.projet.Entities.Competence;
import bts.sio.projet.Entities.Matiere;
import bts.sio.projet.Tools.ConnexionBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

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
    public TreeMap<String, ObservableList<String>> getAllCompetences(int idUser) throws SQLException {
    ps = unCnx.prepareStatement("SELECT competence.id_matiere, matiere.designation, competence.sous_matiere "
            + "FROM competence "
            + "JOIN matiere ON competence.id_matiere = matiere.id "
            + "WHERE competence.id_user = ?");

    // Exécuter la requête
    ps.setInt(1, idUser);
    rs = ps.executeQuery();

    TreeMap<String, ObservableList<String>> lesCompetences = new TreeMap<>();

    while (rs.next()) {
        String matiereDesignation = rs.getString("matiere.designation");
        String sousMatiere = rs.getString("competence.sous_matiere");
        int idMatiere = rs.getInt("competence.id_matiere");

        Matiere matiere = new Matiere(idMatiere, matiereDesignation, sousMatiere);

        if (!lesCompetences.containsKey(matiereDesignation)) {
            ObservableList<String> lesSousMatieres = FXCollections.observableArrayList();
            lesSousMatieres.add(sousMatiere);
            lesCompetences.put(matiereDesignation, lesSousMatieres);
        } else {
            ObservableList<String> lesSousMatieres = FXCollections.observableArrayList();
            lesSousMatieres.add(sousMatiere);
        }
    }
    return lesCompetences;
}
}

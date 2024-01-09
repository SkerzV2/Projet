package bts.sio.projet.Services;

import bts.sio.projet.Entities.Competence;
import bts.sio.projet.Tools.ConnexionBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class ServiceCompetences
{
    private Connection unCnx;
    private PreparedStatement ps;
    private ResultSet rs;
    private HashMap<Integer, ArrayList<String>> lescompetences;

    public ServiceCompetences()
    {
        unCnx = ConnexionBDD.getCnx();
    }

    public boolean creationCompetence(Competence uneCompetence) throws SQLException, SQLException {
        ps = unCnx.prepareStatement("Select id from competence where id_user=? AND id_matiere = ?");
        ps.setInt(1, uneCompetence.getIdUser());
        ps.setInt(2, uneCompetence.getIdMatiere());
        rs=ps.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            ps.close();
            ps = unCnx.prepareStatement("INSERT INTO competence (competence.sous_matiere, competence.id_user,competence.id_Matiere, competence.statut)\n" +
                    "        VALUES (?, ?, ?, ?);");
            ps.setString(1, uneCompetence.getSousMatiere());
            ps.setInt(2, uneCompetence.getIdUser());
            ps.setInt(3, uneCompetence.getIdMatiere());
            ps.setInt(4, 1);
            ps.executeUpdate();
            ps.close();
        }
        return false;
    }
    public TreeMap<String, ObservableList<String>> getAllCompetences(int idUser) throws SQLException {
        ps = unCnx.prepareStatement("SELECT matiere.designation, competence.sous_matiere\n " +
                "FROM competence \n " +
                "JOIN matiere ON competence.id_matiere = matiere.id "+
                "WHERE competence.id_user = ? \n ");

        ps.setInt(1, idUser);
        rs = ps.executeQuery();

        TreeMap<String, ObservableList<String>> lesCompetences = new TreeMap<>();

        while (rs.next()) {
            String matiereDesignation = rs.getString("matiere.designation");
            String sousMatiere = rs.getString("competence.sous_matiere");

                ObservableList<String> lesSousMatieres = FXCollections.observableArrayList();
                lesSousMatieres.add(sousMatiere);
                lesCompetences.put(matiereDesignation, lesSousMatieres);
        }
        return lesCompetences;
    }
    public ObservableList<Competence> getAllCompetenceObj(int idUser) throws SQLException {
        ObservableList<Competence> lesCompetences = FXCollections.observableArrayList();
        ps = unCnx.prepareStatement("SELECT matiere.designation, competence.sous_matiere ,competence.id " +
                "FROM competence \n " +
                "JOIN matiere ON competence.id_matiere = matiere.id "+
                "WHERE competence.id_user = ? \n " +
                "AND competence.statut = 1");

        ps.setInt(1, idUser);
        rs = ps.executeQuery();
        while(rs.next()){
            Competence uneCompetence = new Competence( rs.getString(1),rs.getString(2),rs.getInt(3));
            lesCompetences.add(uneCompetence);
        }
        return lesCompetences;
    }
    public void updateCompetence(int idCompetence,String lesSousMatieres) throws SQLException {
        ps = unCnx.prepareStatement("UPDATE competence "
                + "SET sous_matiere = ? "
                + "WHERE competence.id = ? ");

        ps.setString(1, lesSousMatieres);
        ps.setInt(2, idCompetence);

        ps.executeUpdate();
        ps.close();
    }
    public void supprimerCompetence(int idCompetence) throws SQLException {
        ps = unCnx.prepareStatement("DELETE FROM competence "
                + "WHERE id = ? ");

        ps.setInt(1, idCompetence);

        ps.executeUpdate();
        ps.close();
    }
    public HashMap<Integer, ArrayList<String>> getMesCompetences(int idUser) throws SQLException {
        lescompetences = new HashMap<>();
        ArrayList<String> mesCompetences = new ArrayList<>();
        ps = unCnx.prepareStatement("SELECT competence.sous_matiere ,competence.id "
                + "FROM competence "
                + "WHERE competence.id_user = ?");

        ps.setInt(1, idUser);
        rs = ps.executeQuery();
        while (rs.next()) {
            String competences = rs.getString("sous_matiere");
            String[] splitSousMatiere = competences.split("#");
            for (String uneSousMatiere : splitSousMatiere) {
                if (!uneSousMatiere.isEmpty()) {
                    mesCompetences.add(uneSousMatiere);
                }
            }
            lescompetences.put(rs.getInt(2),mesCompetences);
        }
        return lescompetences ;
    }
    public int getUneCompetenceUser(int idUser,String nomMatiere) throws SQLException {
        ps = unCnx.prepareStatement("SELECT competence.id "
                + "FROM competence " +
                "JOIN matiere on matiere.id = competence.id_matiere "
                + "WHERE competence.id_user = ? " +
                "AND matiere.designation = ? ");
        ps.setInt(1, idUser);
        ps.setString(2, nomMatiere);
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
}

package bts.sio.projet.Services;

import bts.sio.projet.Entities.Soutient;
import bts.sio.projet.Tools.ConnexionBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ServiceSoutients {
    private Connection unCnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public ServiceSoutients()
    {
        unCnx = ConnexionBDD.getCnx();
    }

    public void CreeSoutient(int idDemande, int idCompetence, String dateDuSoutient, String dateUptdated, String description,int status) throws SQLException {
        ps = unCnx.prepareStatement("INSERT INTO soutien (id_demande, id_competence, date_du_soutien, date_updated,description, status) "+
                "VALUES (?,?,?,?,?,?)");

        ps.setInt(1, idDemande);
        ps.setInt(2, idCompetence);
        ps.setString(3, dateDuSoutient);
        ps.setString(4, dateUptdated);
        ps.setString(5, description);
        ps.setInt(6, 1);

        ps.executeUpdate();

        ps.close();
        ps = unCnx.prepareStatement("UPDATE Demande set status = ? " +
                "Where id= ? ");


        ps.setInt(1, 1);
        ps.setInt(2, idDemande);

        ps.executeUpdate();

        ps.close();

    }

    public ObservableList<Soutient> GetAllSoutiens() throws SQLException
    {
        ObservableList<Soutient> lesSoutiens = FXCollections.observableArrayList();
        ps = unCnx.prepareStatement("SELECT id_demande, id, id_salle, date_du_soutien, date_updated, description, status"+
                " FROM soutien WHERE status = ? ");
        ps.setInt(1,1);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Soutient unSoutien = new Soutient(
                    rs.getInt(2),
                    rs.getInt(1),
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getInt(7));
            lesSoutiens.add(unSoutien);
        }
        return lesSoutiens;
    }

    public void updateSoutient(Soutient unSoutien) throws SQLException {
        ps = unCnx.prepareStatement(
                "UPDATE soutien set status = ?, description = ?, id_salle = ? " +
                "Where id= ? "
        );
        ps.setInt(1, unSoutien.getStatus());
        ps.setString(2, unSoutien.getDescription());
        ps.setInt(3, unSoutien.getIdSalle());
        ps.setInt(4, unSoutien.getId());
        ps.executeUpdate();
        ps.close();
    }

    public HashMap<String, ArrayList<Integer>> getDatasGraphiqueSoutienRealiser(int idUser){
        HashMap<String, ArrayList<Integer>> datas = new HashMap<>();
        try {
            ps = unCnx.prepareStatement("SELECT m.id, m.designation AS nom_matiere, COUNT(s.id) AS nombre_de_soutiens "
                    + "FROM matiere m "
                    + "LEFT JOIN competence c ON m.id = c.id_matiere "
                    + "LEFT JOIN soutien s ON c.id = s.id_competence "
                    + "LEFT JOIN user u ON c.id_user = u.id "
                    + "WHERE u.id = ? "
                    + "GROUP BY m.id, m.designation;");

            ps.setInt(1, idUser);

            rs = ps.executeQuery();
            while(rs.next())
            {
                if(!datas.containsKey(rs.getString(1)))
                {
                    ArrayList<Integer> values = new ArrayList<>();
                    values.add(rs.getInt(3));
                    datas.put(rs.getString(2),values);
                }
                else{
                    datas.get(rs.getString(2)).add(rs.getInt(3));
                }

            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return datas;
    }

    public HashMap<String,Integer> getDatasGraphiqueSoutienParMatiere(int idUser)
    {
        HashMap<String, Integer> datas = new HashMap();
        try {
            ps = unCnx.prepareStatement("SELECT m.id, m.designation AS nom_matiere, COUNT(s.id) AS nombre_de_soutiens "
                    + "FROM matiere m "
                    + "JOIN competence c ON m.id = c.id_matiere "
                    + "JOIN soutien s ON c.id = s.id_competence "
                    + "JOIN user u ON c.id_user = u.id "
                    + "WHERE u.id = ? "
                    + "GROUP BY m.id, m.designation;");
            ps.setInt(1, idUser);

            rs = ps.executeQuery();
            while(rs.next())
            {
                datas.put(rs.getString(2), rs.getInt(3));
            }
            rs.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return datas;
    }

    public HashMap<String, ArrayList<Integer>> getDatasGraphiqueSoutientNonRealiser(int idUser){
        HashMap<String, ArrayList<Integer>> datas = new HashMap<>();
        try {
            ps = unCnx.prepareStatement("SELECT m.id, m.designation AS nom_matiere, COUNT(d.id) AS nombre_de_soutiens_non_realiser "
                    + "FROM matiere m "
                    + "LEFT JOIN demande d ON m.id = d.id_matiere "
                    + "AND d.id_user = ? "
                    + "AND (d.status = 0 OR d.status = 1) "
                    + "GROUP BY m.id, m.designation;");

            ps.setInt(1, idUser);

            rs = ps.executeQuery();
            while(rs.next())
            {
                if(!datas.containsKey(rs.getString(1)))
                {
                    ArrayList<Integer> values = new ArrayList<>();
                    values.add(rs.getInt(3));
                    datas.put(rs.getString(2),values);
                }
                else{
                    datas.get(rs.getString(2)).add(rs.getInt(3));
                }

            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return datas;
    }

    public HashMap<String, Integer> getSoutiensParEtudiant() throws SQLException {
        HashMap<String, Integer> datas = new HashMap<>();
        ps = unCnx.prepareStatement("SELECT user.prenom, COUNT(soutien.id), soutien.id_salle\n" +
                "FROM user\n" +
                "JOIN competence on user.id=competence.id_user\n" +
                "JOIN soutien on competence.id=soutien.id_competence\n" +
                "WHERE soutien.id_salle IS NOT NULL\n" +
                "GROUP BY user.prenom");


        rs = ps.executeQuery();

        while (rs.next()) {
            datas.put(rs.getString(1), rs.getInt(2));
        }
        rs.close();
        return datas;

    }

    public HashMap<String, Integer> getNbMatiereDemande() throws SQLException {
        HashMap<String, Integer> datas = new HashMap<>();
        ps = unCnx.prepareStatement("SELECT matiere.designation, COUNT(soutien.id_competence)\n" +
                "FROM matiere\n" +
                "JOIN competence on matiere.id=competence.id_matiere\n" +
                "JOIN soutien on competence.id=soutien.id_competence\n" +
                "WHERE soutien.id_salle IS NOT NULL\n" +
                "GROUP BY matiere.designation");


        rs = ps.executeQuery();

        while (rs.next()) {
            datas.put(rs.getString(1), rs.getInt(2));
        }
        rs.close();
        return datas;
    }

}
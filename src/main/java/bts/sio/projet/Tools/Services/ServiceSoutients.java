package bts.sio.projet.Tools.Services;

import bts.sio.projet.Entities.Demande;
import bts.sio.projet.Entities.Soutient;
import bts.sio.projet.Tools.ConnexionBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ServiceSoutients {
    private HashMap<Integer, String> dictionnaire;

    private Connection unCnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public ServiceSoutients()
    {
        unCnx = ConnexionBDD.getCnx();
    }

    /*
    public int getIntNiveau(int idUser) throws SQLException {
        String leNiveau = getNiveau(idUser);

        lesNiveaux = new HashMap<>();
        lesNiveaux.put(5, "Master 2");
        lesNiveaux.put(4, "Master 1");
        lesNiveaux.put(3, "Bachelor");
        lesNiveaux.put(2, "BTS 2");
        lesNiveaux.put(1, "BTS 1");
        lesNiveaux.put(0, "Terminale");

        int leIntNiveau = 0;

        for (int intNiveau : lesNiveaux.keySet()) {
            if (leNiveau.equals(lesNiveaux.get(intNiveau))) {
                leIntNiveau = intNiveau;
                break;
            }
        }
        return leIntNiveau;
    }
    public String getRequetNiveauCondition(int IntNiveau){
        int niveauMoinDeux = IntNiveau-2;
        String niveauCondition = "";
        if (niveauMoinDeux >= 0){
            for (int i = 0; i <= niveauMoinDeux; i++) {
                if (!niveauCondition.isEmpty()) {
                    niveauCondition = niveauCondition + " OR user.niveau = ?";
                } else {
                    niveauCondition = "user.niveau = ?";
                }
            }
        }
        return niveauCondition;
    }
    */

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

}
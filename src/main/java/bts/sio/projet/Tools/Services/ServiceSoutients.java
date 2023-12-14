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
    private HashMap<Integer, ArrayList<String>> lescompetences;
    HashMap<Integer, ArrayList<String>> mesCompetences;
    public ServiceSoutients()
    {
        unCnx = ConnexionBDD.getCnx();
    }
    public ObservableList<Soutient> getAllDemandesTv(int idUser) throws SQLException {
        mesCompetences = getMesCompetences(idUser);
        int leIntNiveau = getNiveau(idUser);
        /*
        ArrayList<String> mesCompetences = getMesCompetences(idUser);
        int leIntNiveau = getIntNiveau(idUser);
        String niveauCondition = getRequetNiveauCondition(leIntNiveau);
        if (leIntNiveau >= 2) {

     */
        ps = unCnx.prepareStatement("SELECT user.id, user.nom, user.prenom, user.niveau, demande.id, demande.id_matiere, demande.date_updated, demande.date_fin_demande, matiere.designation, demande.sous_matiere "
                + "FROM demande "
                + "JOIN matiere ON demande.id_matiere = matiere.id "
                + "JOIN user ON user.id = demande.id_user "
                + "WHERE demande.id_user != ? "
                + "AND user.niveau <= ? "
                +"AND demande.status = 0");

        ps.setInt(1, idUser);

        int parametreNiveau = leIntNiveau - 2;
        ps.setInt(2, parametreNiveau);
        rs = ps.executeQuery();

        ObservableList<Soutient> lesDemandes = FXCollections.observableArrayList();

        while (rs.next()) {
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
            String sousMatiereDem = "";
            int idCompetence = 0;
            for (String uneSousMatiere : splitSousMatiereDem) {
                if (!uneSousMatiere.isEmpty()) {
                    for (int idComp : mesCompetences.keySet()) {
                        for (String competence : mesCompetences.get(idComp)) {
                            if (uneSousMatiere.equals(competence)) {
                                sousMatiereDem += "#" + uneSousMatiere;
                            }
                        }
                        idCompetence=idComp;
                    }
                }
            }
            if (!sousMatiereDem.equals("")) {
                Soutient unSoutient = new Soutient(nomUser, prenomUser, matiereDesignation, idDemande,idCompetence, dateDebut, dateFin, sousMatiere, idUser, idMatiere);

                lesDemandes.add(unSoutient);
            }
        }
            return lesDemandes;
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
    public int getNiveau(int idUser) throws SQLException {
        int niveau;
        ps = unCnx.prepareStatement("SELECT user.niveau"
                + " FROM user "
                + " WHERE user.id = ? ");
        ps.setInt(1, idUser);
        rs = ps.executeQuery();

        rs.next();
        niveau = rs.getInt(1);
        return niveau;
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

}
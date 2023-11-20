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
import java.util.Date;
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
    public ObservableList<Demande> getAllDemandesTv(int idUser) throws SQLException
    {
        String leNiveau = getNiveau(idUser);
        ArrayList<String> mesCompetences = getMesCompetences(idUser);

        // Correspondance entre les valeurs numériques et les niveaux
        HashMap<Integer, String> lesNiveaux = new HashMap<>();
        lesNiveaux.put(5, "Master 2");
        lesNiveaux.put(4, "Master 1");
        lesNiveaux.put(3, "Bachelor");
        lesNiveaux.put(2, "BTS 2");
        lesNiveaux.put(1, "BTS 1");
        lesNiveaux.put(0, "Etudiant");

        int leIntNiveau = -1;

        // Trouver la valeur numérique correspondant au niveau de l'utilisateur
        for (int intNiveau : lesNiveaux.keySet()) {
            if (leNiveau.equals(lesNiveaux.get(intNiveau))) {
                leIntNiveau = intNiveau;
                break;
            }
        }

        if (leIntNiveau == -1) {
            // Gérer le cas où le niveau de l'utilisateur n'est pas dans la correspondance
            throw new IllegalArgumentException("Niveau non pris en charge: " + leNiveau);
        }

        // Construire la partie de la requête avec les OR
        StringBuilder niveauCondition = new StringBuilder();
        for (int i = 0; i <= leIntNiveau; i++) {
            if (niveauCondition.length() > 0) {
                niveauCondition.append(" OR ");
            }
            niveauCondition.append("user.niveau = ?");
        }

        ps = unCnx.prepareStatement("SELECT user.id, user.nom, user.prenom, user.niveau, demande.id, demande.id_matiere, demande.date_updated, demande.date_fin_demande, matiere.designation, demande.sous_matiere "
                + "FROM demande "
                + "JOIN matiere ON demande.id_matiere = matiere.id "
                + "JOIN user ON user.id = demande.id_user "
                + "WHERE demande.id_user != ? " // Exclut l'utilisateur actuel
                + "AND (" + niveauCondition + ")"); // Utilisation des valeurs numériques avec des OR

        // Exécuter la requête
        ps.setInt(1, idUser);

        // Utiliser les valeurs numériques pour les niveaux autorisés
        for (int i = 0; i <= leIntNiveau; i++) {
            ps.setString(i + 2, lesNiveaux.get(i));
        }

        rs = ps.executeQuery();

        ObservableList<Demande> lesDemandes = FXCollections.observableArrayList();

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
                for (String uneSousMatiere : splitSousMatiereDem) {
                    if (!uneSousMatiere.isEmpty()) {
                        for (String competence : mesCompetences) {
                            if (uneSousMatiere.equals(competence)) {
                                sousMatiereDem += "#" + uneSousMatiere;
                            }
                        }
                    }
                }
                System.out.println(sousMatiereDem);
                if (!sousMatiereDem.equals("")) {
                    Demande uneDemande = new Demande(dateDebut, dateFin, sousMatiere, idMatiere, matiereDesignation, idDemande, idUserdem, nomUser, prenomUser, niveauUser);

                    lesDemandes.add(uneDemande);
                }
            }
        return lesDemandes;
    }
    public ArrayList getMesCompetences(int idUser) throws SQLException {
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
            for (String uneSousMatiere : splitSousMatiere) {
                if (!uneSousMatiere.isEmpty()) {
                    mesCompetences.add(uneSousMatiere);
                }
            }
        }
        return mesCompetences ;
    }

    public void CreeSoutient(int idDemande, int idCompetence, String dateDuSoutient, String dateUptdated, String description,int status) throws SQLException {
        ps = unCnx.prepareStatement("INSERT INTO soutient (id_demande, id_competence, date_du_soutient, date_updated,description, status) "+
                "VALUES (?,?,?,?,?,?)");

// Exécuter la requête
        ps.setInt(1, idDemande);
        ps.setInt(2, idCompetence);
        ps.setString(3, dateDuSoutient);
        ps.setString(4, dateUptdated);
        ps.setString(5, description);
        ps.setInt(6, status);
        rs = ps.executeQuery();
    }
    public String getNiveau(int idUser) throws SQLException {
        String niveau;
        ps = unCnx.prepareStatement("SELECT user.niveau"
                + " FROM user "
                + " WHERE user.id = ? ");
        ps.setInt(1, idUser);  // Correction : définir la valeur du paramètre
        rs = ps.executeQuery();

        rs.next();
        niveau = rs.getString(1);
        return niveau;
    }
}


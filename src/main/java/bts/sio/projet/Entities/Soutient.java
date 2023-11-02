package bts.sio.projet.Entities;

public class Soutient {
    private int idDemande;
    private int idCompetence;
    private int idSalle;
    private String dateDebut;
    private String dateFin;
    private String sousMatière;
    private int idUser;
    private int idMatiere;
    private int status;

    public Soutient(int idDemande, int idCompetence, int idSalle, String dateDebut, String dateFin, String sousMatière, int idUser, int idMatiere, int status) {
        this.idDemande = idDemande;
        this.idCompetence = idCompetence;
        this.idSalle = idSalle;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.sousMatière = sousMatière;
        this.idUser = idUser;
        this.idMatiere = idMatiere;
        this.status = status;
    }

    public int getIdDemande() {
        return idDemande;
    }

    public int getIdCompetence() {
        return idCompetence;
    }

    public int getIdSalle() {
        return idSalle;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public String getSousMatière() {
        return sousMatière;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getIdMatiere() {
        return idMatiere;
    }

    public int getStatus() {
        return status;
    }
}

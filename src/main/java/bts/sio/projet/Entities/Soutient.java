package bts.sio.projet.Entities;

public class Soutient {
    private int idDemande;
    private int idCompetence;
    private int idSalle;
    private String dateDebut;
    private String dateFin;
    private String sousMatiere;
    private int idUser;
    private int idMatiere;
    private int status;
    private String description;

    private String nom;
    private String prenom;
    private String matiere;

    public Soutient(int idDemande, int idCompetence, int idSalle, String dateDebut, String dateFin, String sousMatiere, int idUser, int idMatiere, int status) {
        this.idDemande = idDemande;
        this.idCompetence = idCompetence;
        this.idSalle = idSalle;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.sousMatiere = sousMatiere;
        this.idUser = idUser;
        this.idMatiere = idMatiere;
        this.status = status;
    }

    public Soutient(String nom, String prenom, String matiere, int idDemande, int idCompetence, String dateDebut, String dateFin, String sousMatiere, int idUser, int idMatiere) {
        this.idDemande = idDemande;
        this.idCompetence = idCompetence;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.sousMatiere = sousMatiere;
        this.idUser = idUser;
        this.idMatiere = idMatiere;
        this.prenom = prenom;
        this.nom = nom;
        this.matiere = matiere;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMatiere() {
        return matiere;
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

    public String getSousMatiere() {
        return sousMatiere;
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

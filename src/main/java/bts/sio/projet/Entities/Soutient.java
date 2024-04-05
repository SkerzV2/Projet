package bts.sio.projet.Entities;

import java.lang.invoke.StringConcatFactory;

public class Soutient {
    private int idDemande;
    private int id;
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

    public Soutient(int idDemande, int id, int idCompetence, int idSalle, String dateDebut, String dateFin, String sousMatiere, int idUser, int idMatiere, int status) {
        this.idDemande = idDemande;
        this.id = id;
        this.idCompetence = idCompetence;
        this.idSalle = idSalle;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.sousMatiere = sousMatiere;
        this.idUser = idUser;
        this.idMatiere = idMatiere;
        this.status = status;
    }

    public Soutient(int id, int idDemande, int idSalle, String dateDebut, String dateFin, String description, int status)
    {
        this.idDemande = idDemande;
        this.id = id;
        this.idSalle = idSalle;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
        this.status = status;
    }

    public Soutient(int status, String description, int idSalle, int id)
    {
        this.status = status;
        this.description = description;
        this.idSalle = idSalle;
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
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
    public int getId() {return id;}

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

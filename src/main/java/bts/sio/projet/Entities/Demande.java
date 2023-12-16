package bts.sio.projet.Entities;

public class Demande {

    private int niveau;
    private String prenom;
    private String nom;
    private String dateDebut;
    private String dateFin;
    private String sousMatiere;
    private int idUser;
    private int idMatiere;
    private String designation;
    private int idDemande;
    private int status;

//constructeur modifier les demande
    public Demande(String dateDebut, String dateFin, String sousMatiere, int idUser, int idMatiere, String designation, int idDemande) {

        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.sousMatiere = sousMatiere;
        this.idUser = idUser;
        this.idMatiere = idMatiere;
        this.designation = designation;
        this.idDemande = idDemande;
    }
//constructeur visualiser les demande

    public Demande(String dateDebut, String dateFin, String sousMatiere, int idUser, int idMatiere, int status, String designation) {

        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.sousMatiere = sousMatiere;
        this.idUser = idUser;
        this.idMatiere = idMatiere;
        this.designation = designation;
        this.status = status;
    }


    public int getNiveau() {
        return niveau;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

//constructeur pour s'inscrire a un soutient
    public Demande(String dateDebut, String dateFin, String sousMatiere, int idMatiere, String designation, int idDemande, int idUser, String nom, String prenom, int niveau) {
        this.idDemande=idDemande;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.sousMatiere = sousMatiere;
        this.idMatiere = idMatiere;
        this.designation = designation;
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.niveau = niveau;
    }
    public int getIdMatiere() {return idMatiere;}

    public void setIdMatiere(int idMatiere) {this.idMatiere = idMatiere;}

    public String getSousMatiere() {return sousMatiere;}

    public void setSousMatiere(String sousMatiere) {this.sousMatiere = sousMatiere;}

    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getDesignation() {
        return designation;
    }

    public int getIdDemande() {
        return idDemande;
    }
    public Demande(String sousMatiere) {
        this.sousMatiere = sousMatiere;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}


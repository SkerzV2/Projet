package bts.sio.projet.Entities;

public class Demande {
    private int idUser;
    private String dateDebut;
    private String dateFin;
    private String matiere;
    private String sousMatiere;

    public Demande(int idUser, String dateDebut, String dateFin, String matiere, String sousMatiere) {
        this.idUser = idUser;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.matiere = matiere;
        this.sousMatiere = sousMatiere;
    }

    public int getIdUser() {return idUser;}

    public void setIdUser(int idUser) {this.idUser = idUser;}

    public String getDateDebut() {return dateDebut;}

    public void setDateDebut(String dateDebut) {this.dateDebut = dateDebut;}

    public String getDateFin() {return dateFin;}

    public void setDateFin(String dateFin) {this.dateFin = dateFin;}

    public String getMatiere() {return matiere;}

    public void setMatiere(String matiere) {this.matiere = matiere;}

    public String getSousMatiere() {return sousMatiere;}

    public void setSousMatiere(String sousMatiere) {this.sousMatiere = sousMatiere;}

}

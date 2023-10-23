package bts.sio.projet.Entities;

public class Demande {
    private int id;
    private String dateDebut;
    private String dateFin;
    private int matiere;
    private int sousMatiere;

    public Demande(int id, String dateDebut, String dateFin, int matiere, int sousMatiere) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.matiere = matiere;
        this.sousMatiere = sousMatiere;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getDateDebut() {return dateDebut;}

    public void setDateDebut(String dateDebut) {this.dateDebut = dateDebut;}

    public String getDateFin() {return dateFin;}

    public void setDateFin(String dateFin) {this.dateFin = dateFin;}

    public int getMatiere() {return matiere;}

    public void setMatiere(int matiere) {this.matiere = matiere;}

    public int getSousMatiere() {return sousMatiere;}

    public void setSousMatiere(int sousMatiere) {this.sousMatiere = sousMatiere;}

}

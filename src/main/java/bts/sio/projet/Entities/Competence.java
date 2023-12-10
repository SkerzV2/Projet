package bts.sio.projet.Entities;

public class Competence
{
    private String designation;
    private String sousMatiere;
    private int idUser;
    private int idMatiere;
    private int idCompetence;


    public int getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public Competence(String designation, String sousMatiere, int idUser, int idMatiere)
    {
        this.designation = designation;
        this.sousMatiere = sousMatiere;
        this.idUser = idUser;
        this.idMatiere = idMatiere;
    }
    public Competence(String designation, String sousMatiere, int idCompetence)
    {
        this.designation = designation;
        this.sousMatiere = sousMatiere;
        this.idCompetence = idCompetence;
    }

    public int getIdCompetence() {
        return idCompetence;
    }

    public String getDesignation()
    {
        return designation;
    }
    public String getSousMatiere()
    {
        return sousMatiere;
    }
    public int getIdUser()
    {
        return idUser;
    }

    public void setMatiere(String designation)
    {
        this.designation = designation;
    }
    public void setSousMatiere(String sousMatiere)
    {
        this.sousMatiere = sousMatiere;
    }
}
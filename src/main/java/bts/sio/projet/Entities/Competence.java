package bts.sio.projet.Entities;

public class Competence
{
    private int id;
    private String matierePrincipale;
    private String matiereSecondaire;

    public Competence(int id, String matierePrincipale, String matiereSecondaire)
    {
        this.id = id;
        this.matierePrincipale = matierePrincipale;
        this.matiereSecondaire = matiereSecondaire;
    }

    public int getId() {
        return id;
    }
    public String getMatierePrincipale()
    {
        return matierePrincipale;
    }
    public String getMatiereSecondaire()
    {
        return matiereSecondaire;
    }
    public void setMatierePrincipale(String matierePrincipale)
    {
        this.matierePrincipale = matierePrincipale;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public void setMatiereSecondaire(String matiereSecondaire)
    {
        this.matiereSecondaire = matiereSecondaire;
    }
}

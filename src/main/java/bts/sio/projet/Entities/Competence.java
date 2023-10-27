package bts.sio.projet.Entities;

public class Competence
{
    private String matierePrincipale;
    private String matiereSecondaire;
    private int idUser;

    public Competence( String matierePrincipale, String matiereSecondaire, int idUser)
    {
        this.matierePrincipale = matierePrincipale;
        this.matiereSecondaire = matiereSecondaire;
        this.idUser = idUser;
    }


    public String getMatierePrincipale()
    {
        return matierePrincipale;
    }
    public String getMatiereSecondaire()
    {
        return matiereSecondaire;
    }
    public int getIdUser()
    {
        return idUser;
    }

    public void setMatierePrincipale(String matierePrincipale)
    {
        this.matierePrincipale = matierePrincipale;
    }
    public void setMatiereSecondaire(String matiereSecondaire)
    {
        this.matiereSecondaire = matiereSecondaire;
    }
}
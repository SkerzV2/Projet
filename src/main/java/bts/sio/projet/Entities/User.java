package bts.sio.projet.Entities;

public class User {
    private String nom;
    private String email;
    private String password;

    public User(String nom, String email, String password) {
        this.nom = nom;
        this.email = email;
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresseMail() {
        return email;
    }

    public void setAdresseMail(String adresseMail) {
        this.email = adresseMail;
    }

    public String getMotDePasse() {
        return password;
    }

    public void setMotDePasse(String password) {
        this.password = password;
    }
}

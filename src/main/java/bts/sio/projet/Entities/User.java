package bts.sio.projet.Entities;

public class User {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String role;
    private String niveau;
    private int sexe;
    private int telephone;

    public User(int id, String nom, String prenom, String email, String password, String role, String niveau, int sexe, int telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.role = role;
        this.niveau = niveau;
        this.sexe = sexe;
        this.telephone = telephone;
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

package bts.sio.projet.Entities;

public class User {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String role;
    private int niveau;
    private int sexe;
    private int telephone;

    public User(int id, String nom, String prenom, String email, String password, String role, int niveau, int sexe, int telephone) {
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
    public User(int id, String nom, String prenom, int niveau) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.niveau = niveau;
    }


    public int getId() {
        return id;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public int getNiveau() {
        return niveau;
    }

    public int getSexe() {
        return sexe;
    }

    public int getTelephone() {
        return telephone;
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

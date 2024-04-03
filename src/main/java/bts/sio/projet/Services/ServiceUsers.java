package bts.sio.projet.Services;

import bts.sio.projet.Tools.ConnexionBDD;
import bts.sio.projet.Entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceUsers {
    private Connection uneCnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public ServiceUsers() {
        uneCnx = ConnexionBDD.getCnx();
    }

    public User GetConnectionUser(String email, String password) throws SQLException {
        // Écrire la requête
        ps = uneCnx.prepareStatement("SELECT id, nom, prenom, role, niveau, sexe, telephone FROM user WHERE email=? AND password=?");
        // Exécuter la requête
        ps.setString(1, email);
        ps.setString(2, password);
        // Parcourir la rs
        rs = ps.executeQuery();
        User user = null;
        if (rs.next()) {
            user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), email, password, rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7));
        }
        return user;
    }

    public int getNiveau(int idUser) throws SQLException {
        int niveau;
        ps = uneCnx.prepareStatement("SELECT user.niveau"
                + " FROM user "
                + " WHERE user.id = ? ");
        ps.setInt(1, idUser);
        rs = ps.executeQuery();

        rs.next();
        niveau = rs.getInt(1);
        return niveau;
    }

    public int getAllNiveau(){

        return 0;
    }
}
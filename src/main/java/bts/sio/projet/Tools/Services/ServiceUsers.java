package bts.sio.projet.Tools.Services;

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
}
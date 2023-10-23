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

    public String GetConnectionUser(String email, String password) throws SQLException {
        String rep ="";
        // ecrire la requete
        ps = uneCnx.prepareStatement("SELECT nom FROM user WHERE email=? AND password=?");
        //executer la requete
        ps.setString(1, email);
        ps.setString(2, password);
        //parcourir la rs
        rs = ps.executeQuery();
        if (rs.next()) {
            rep = rs.getString(1);
        }
        return rep;
    }
}

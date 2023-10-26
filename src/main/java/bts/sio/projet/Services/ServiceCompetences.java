package bts.sio.projet.Services;

import bts.sio.projet.Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ServiceCompetences
{
    private Connection unCnx;
    private PreparedStatement ps;
    private ResultSet rs;
    public ServiceCompetences()
    {
        unCnx = ConnexionBDD.getCnx();
    }

}

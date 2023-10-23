package bts.sio.projet;

import bts.sio.projet.Tools.ConnexionBDD;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import bts.sio.projet.Entities.*;
import bts.sio.projet.Services.ServiceUsers;
import java.sql.Connection;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProjetController implements Initializable {
    ConnexionBDD maCnx;
    ServiceUsers serviceUsers =new ServiceUsers();

    @javafx.fxml.FXML
    private Button btnConnexion;
    @javafx.fxml.FXML
    private TextField txtEmail;
    @javafx.fxml.FXML
    private TextField txtPassword;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            maCnx = new ConnexionBDD();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void btnConnexionClicked(Event event) throws SQLException{
        serviceUsers = new ServiceUsers();
        String rep = serviceUsers.GetConnectionUser(txtEmail.getText(), txtPassword.getText());
        if (rep.equals("")) {
            System.out.println("sa passe pas");
        } else {
            System.out.println("sa passe");}
    }

}

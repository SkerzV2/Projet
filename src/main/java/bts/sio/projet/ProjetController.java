package bts.sio.projet;

import bts.sio.projet.Tools.ConnexionBDD;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import bts.sio.projet.Entities.*;
import bts.sio.projet.Services.ServiceUsers;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProjetController implements Initializable {
    ConnexionBDD maCnx;
    ServiceUsers serviceUsers = new ServiceUsers();

    User rep;

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
    public void btnConnexionClicked(Event event) throws SQLException, IOException {
        String erreurs = null;
        if (txtEmail.getText().equals("")){
            erreurs = "Veuillez remplir le champ mail!";
        }
        if (txtPassword.getText().equals("")){
             erreurs += "\nVeuillez remplir le champ mot de passe!";
        }
        serviceUsers = new ServiceUsers();
        rep = serviceUsers.GetConnectionUser(txtEmail.getText(), txtPassword.getText());
        if (rep == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de sélection");
            alert.setContentText(erreurs);
            alert.setHeaderText("");
            alert.showAndWait();
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setTitle("Menu");
            stage.setScene(scene);
            stage.show();
        }
    }
}

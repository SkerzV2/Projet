package bts.sio.projet;

import bts.sio.projet.Tools.ConnexionBDD;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import bts.sio.projet.Entities.*;
import bts.sio.projet.Services.ServiceUsers;
import javafx.stage.Stage;

import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProjetController implements Initializable
{
    ConnexionBDD maCnx;
    ServiceUsers serviceUsers = new ServiceUsers();
    User user;

    EtudiantController etudiantController;
    AdminController adminController;


    @javafx.fxml.FXML
    private Button btnConnexion;
    @javafx.fxml.FXML
    private TextField txtEmail;
    @javafx.fxml.FXML
    private PasswordField PwTxtPassword;

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
        String erreurs = "";
        if (txtEmail.getText().equals("")){
            erreurs = "Veuillez remplir le champ mail!";
        }
        if (PwTxtPassword.getText().equals("")){
            erreurs += "\nVeuillez remplir le champ mot de passe!";
        }
        serviceUsers = new ServiceUsers();
        user = serviceUsers.GetConnectionUser(txtEmail.getText(), PwTxtPassword.getText());
        if (user == null) {
            if (erreurs.equals("")){
                erreurs="Mail ou mot de passe invalide!";
            }
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de sélection");
            alert.setContentText(erreurs);
            alert.setHeaderText("");
            alert.showAndWait();
        }
        else {
            if(user.getRole().equals("Etudiant")) {
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("etudiant-view.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root);
                etudiantController = fxmlLoader.getController();
                etudiantController.setUser(user); // Transmettez l'objet User à MenuController
                Stage newStage = new Stage();
                newStage.setTitle("Menu");
                newStage.setScene(scene);
                newStage.show();
            }
            else{
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin-view.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root);
                adminController = fxmlLoader.getController();
                adminController.setUser(user); // Transmettez l'objet User à MenuController
                Stage newStage = new Stage();
                newStage.setTitle("Menu");
                newStage.setScene(scene);
                newStage.show();
            }
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                                           //
    //                                                                                                          //
    //                                           Partie de Claude                                              //
    //                                                                                                        //
    //                                                                                                       //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    public User getUser()
    {
        return this.user;
    }

}

package bts.sio.projet;

import bts.sio.projet.Entities.Demande;
import bts.sio.projet.Entities.Matiere;
import bts.sio.projet.Entities.User;
import bts.sio.projet.Services.ServiceMatieres;
import bts.sio.projet.Services.ServicesDemandes;
import bts.sio.projet.Tools.ConnexionBDD;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SupprimerDemandeController implements Initializable {
    private Demande laDemande;
    ConnexionBDD maCnx;
    User user;
    ServiceMatieres serviceMatieres;
    ServicesDemandes servicesDemandes = new ServicesDemandes();
    MenuController menuController = new MenuController();
    ObservableList<Matiere> lesMatieres = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    private Button btnSupprimerDemande;
    @javafx.fxml.FXML
    private Button btnAnnulerDemandeSupp;
    @javafx.fxml.FXML
    private Label lbMatiereSupp;
    @javafx.fxml.FXML
    private Label lbDateDebutSupp;
    @javafx.fxml.FXML
    private Label lbSousMatiereSupp;
    @javafx.fxml.FXML
    private Label lbDateFinSupp;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try
        {
            maCnx = new ConnexionBDD();
        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void initDatas(Demande demandeSelectionnee) {
        laDemande = demandeSelectionnee;

        lbMatiereSupp.setText(laDemande.getDesignation());
        lbDateDebutSupp.setText(laDemande.getDateDebut());
        lbDateFinSupp.setText(laDemande.getDateFin());
        lbSousMatiereSupp.setText(laDemande.getSousMatiere());

    }

    @javafx.fxml.FXML
    public void btnSupprimerDemandeClicked(Event event) throws IOException, SQLException {
        servicesDemandes.supprimerDemande(laDemande.getIdDemande());
        //menuController.refreshTvDemande(initUser(user));
        Stage stage = (Stage) btnSupprimerDemande.getScene().getWindow();
        stage.close();
    }

    @javafx.fxml.FXML
    public void btnAnnulerDemandeSuppClicked(Event event)
    {
        Stage stage = (Stage) btnAnnulerDemandeSupp.getScene().getWindow();
        stage.close();
    }

    public User initUser(User user)
    {
        this.user = user;
        return user;
    }
}

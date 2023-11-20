package bts.sio.projet;

import bts.sio.projet.Entities.Demande;
import bts.sio.projet.Entities.Matiere;
import bts.sio.projet.Entities.User;
import bts.sio.projet.Tools.ConnexionBDD;
import bts.sio.projet.Tools.Services.ServiceDemandes;
import bts.sio.projet.Tools.Services.ServiceMatieres;
import bts.sio.projet.Tools.Services.ServiceSoutients;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.DatePicker;
import javafx.event.Event;


import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CreeSoutientController implements Initializable {
    private Demande laDemande;
    ConnexionBDD maCnx;
    User user;
    ServiceSoutients serviceSoutients = new ServiceSoutients();
    @javafx.fxml.FXML
    private DatePicker dateSoutient;
    @javafx.fxml.FXML
    private ComboBox cboMatiereSoutient;
    @javafx.fxml.FXML
    private MenuButton mbSousMatiereSoutient;
    @javafx.fxml.FXML
    private Button btnValiderSoutient;
    @javafx.fxml.FXML
    private Button btnAnnulerSoutient;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            maCnx = new ConnexionBDD();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }}
    public void initDatas(Demande demandeSelectionnee) {
        laDemande = demandeSelectionnee;
        cboMatiereSoutient.getItems().add(laDemande.getDesignation());
        cboMatiereSoutient.getSelectionModel().selectFirst();
        String[] sousMatieresArray = demandeSelectionnee.getSousMatiere().split("#");
        ObservableList<String> lesSousMatieres = FXCollections.observableArrayList();
        for (String sousMatiere : sousMatieresArray) {
            if (!sousMatiere.isEmpty()) {
                lesSousMatieres.add(sousMatiere);
            }
        }
        mbSousMatiereSoutient.getItems().clear();

        for (String sousMatiere : lesSousMatieres)
        {
            CustomMenuItem customMenuItem = new CustomMenuItem(new CheckBox(sousMatiere));
            customMenuItem.setHideOnClick(false);
            mbSousMatiereSoutient.getItems().add(customMenuItem);
        }
    }
    public User initUser(User user) {
        this.user = user;
        return user;
    }

    @javafx.fxml.FXML
    public void btnValiderSoutientClicked(Event event) {
        String dateNow = LocalDate.now().toString();
        //serviceSoutients.CreeSoutient(laDemande.getIdMatiere());
    }

    @javafx.fxml.FXML
    public void btnAnnulerSoutientClicked(Event event) {
    }
}

package bts.sio.projet;

import bts.sio.projet.Entities.Demande;
import bts.sio.projet.Entities.Soutient;
import bts.sio.projet.Entities.User;
import bts.sio.projet.Tools.ConnexionBDD;
import bts.sio.projet.Tools.Services.ServiceSoutients;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.Event;
import javafx.stage.Stage;


import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CreeSoutientController implements Initializable {
    private Soutient laDemande;
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
    @javafx.fxml.FXML
    private TextArea txtDescriptionSoutien;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            maCnx = new ConnexionBDD();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }}
    public void initDatas(Soutient demandeSoutien) {
        laDemande = demandeSoutien;
        cboMatiereSoutient.getItems().add(laDemande.getMatiere());
        cboMatiereSoutient.getSelectionModel().selectFirst();
        String[] sousMatieresArray = demandeSoutien.getSousMatiere().split("#");
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
    public void btnValiderSoutientClicked(Event event) throws SQLException {
        String dateNow = LocalDate.now().toString();
        String erreurs="";
        Alert alert = new Alert(Alert.AlertType.WARNING);
        if (txtDescriptionSoutien.getText().isEmpty()) {
            erreurs += "La description est obligatoire";
        }
        if (dateSoutient.getValue() != null)
        {
            LocalDate now = LocalDate.now();
            LocalDate date = dateSoutient.getValue();
            LocalDate max = LocalDate.parse(laDemande.getDateFin());

            if (now.isAfter(date))
            {
                erreurs += "La date du soutien ne peut pas être avant la date de debut\n";
            }
           if( date.isAfter(max)){
               erreurs += "La date du soutien ne peut pas être après la date de fin\n";
           }
        }
        if (!erreurs.isEmpty())
        {
            alert.setTitle("Erreurs de sélection");
            alert.setHeaderText("");
            alert.setContentText(erreurs);
            alert.showAndWait();
        }else {
            serviceSoutients.CreeSoutient(laDemande.getIdDemande(), laDemande.getIdCompetence(), dateSoutient.getValue().toString(), dateNow, txtDescriptionSoutien.getText(), laDemande.getStatus());
            Stage stage = (Stage) btnValiderSoutient.getScene().getWindow();
            stage.close();
        }
    }

    @javafx.fxml.FXML
    public void btnAnnulerSoutientClicked(Event event) {
        Stage stage = (Stage) btnAnnulerSoutient.getScene().getWindow();
        stage.close();
    }
}

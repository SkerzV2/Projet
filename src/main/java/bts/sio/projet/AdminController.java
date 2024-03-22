package bts.sio.projet;

import bts.sio.projet.Entities.Matiere;
import bts.sio.projet.Entities.Salle;
import bts.sio.projet.Entities.User;
import bts.sio.projet.Services.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    ServiceUsers serviceUsers;
    ServiceMatieres serviceMatieres;
    ServiceDemandes serviceDemandes;
    ServiceCompetences serviceCompetences;
    ServiceSoutients serviceSoutients;
    ServiceSalle serviceSalle;
    private User user;
    @javafx.fxml.FXML
    private AnchorPane apCreeMatiere;
    @javafx.fxml.FXML
    private VBox vbxDemande;
    @javafx.fxml.FXML
    private Button btnCreerMatiere;
    @javafx.fxml.FXML
    private Button btnMenuMatiere;
    @javafx.fxml.FXML
    private Button btnMenuSalle;
    @javafx.fxml.FXML
    private Button btnMenuSoutien;
    @javafx.fxml.FXML
    private Button btnMenuStatistique;
    @javafx.fxml.FXML
    private TextField txtCreeMatiereNomMatiere;
    @javafx.fxml.FXML
    private TableView tvCreeMatiereSousMatiere;
    @javafx.fxml.FXML
    private TableColumn tcCreeMatiereSousMatiere;
    @javafx.fxml.FXML
    private TextField txtCreeMatiereSousMatiere;
    @javafx.fxml.FXML
    private Button btnCreeMatiereAjouterSousMatiere;
    @javafx.fxml.FXML
    private Button btnCreeMatiereCreer;
    @javafx.fxml.FXML
    private Button btnCreeMatiereAnnuler;
    @javafx.fxml.FXML
    private Button btnCreeMatiereSupprSousMatiere;
    @javafx.fxml.FXML
    private AnchorPane apModifMatiere;
    @javafx.fxml.FXML
    private TableView tvModifMatiereSousMatiere;
    @javafx.fxml.FXML
    private TableColumn tcModifMatiereSousMatiere;
    @javafx.fxml.FXML
    private TextField txtModifMatiereSousMatiere;
    @javafx.fxml.FXML
    private Button btnModifMatiereAjouterSousMatiere;
    @javafx.fxml.FXML
    private Button btnModifMatiereModifier;
    @javafx.fxml.FXML
    private Button btnModifMatiereAnnuler;
    @javafx.fxml.FXML
    private Button btnModifMatiereSupprSousMatiere;
    @javafx.fxml.FXML
    private TextField txtModifMatiereNomMatiere;
    @javafx.fxml.FXML
    private ComboBox cbModifMatiereSelectionnerMatiere;
    @javafx.fxml.FXML
    private AnchorPane apCreeSalle;
    @javafx.fxml.FXML
    private ComboBox cboCreeSalleEtage;
    @javafx.fxml.FXML
    private TextField txtCreeSalleId;
    @javafx.fxml.FXML
    private Button btnCreeSalle;
    @javafx.fxml.FXML
    private Button btnMenuCreeSalle;
    @javafx.fxml.FXML
    private Button btnMenuModifierMatiere;
    @javafx.fxml.FXML
    private AnchorPane appModifierSalle;
    @javafx.fxml.FXML
    private ComboBox cboModifierSalleEtage;
    @javafx.fxml.FXML
    private TextField txtModifierSalleIdSalle;
    @javafx.fxml.FXML
    private Button btnModifierSalleModifier;
    @javafx.fxml.FXML
    private ComboBox cboModifierSalleNomSalle;
    @javafx.fxml.FXML
    private Button btnModifierSalleAnnuler;
    @javafx.fxml.FXML
    private Button btnMenuModifierSalle;

    public void setUser(User user) {
        this.user = user;

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        serviceCompetences = new ServiceCompetences();
        serviceDemandes = new ServiceDemandes();
        serviceSoutients = new ServiceSoutients();
        serviceMatieres = new ServiceMatieres();
        serviceUsers = new ServiceUsers();
        serviceSalle = new ServiceSalle();
        apCreeMatiere.toFront();

        tcCreeMatiereSousMatiere.setCellValueFactory(new PropertyValueFactory<>("sousMatiere"));
        ObservableList<String> lesEtages = FXCollections.observableArrayList();
        lesEtages.add("Etage 1");
        lesEtages.add("Etage 2");
        lesEtages.add("Etage 3");
        lesEtages.add("Etage 4");
        cboCreeSalleEtage.setItems(lesEtages);
        cboModifierSalleEtage.setItems(lesEtages);
        cboCreeSalleEtage.getSelectionModel().selectFirst();

        tcModifMatiereSousMatiere.setCellValueFactory(new PropertyValueFactory<>("sousMatiere"));

        cbModifMatiereSelectionnerMatiere.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    String matiereSelectionne = newValue;
                    try {
                        tvModifMatiereSousMatiere.setItems(serviceMatieres.GetAllSousMatiereOBJ(matiereSelectionne));
                        txtModifMatiereNomMatiere.setText(cbModifMatiereSelectionnerMatiere.getSelectionModel().getSelectedItem().toString());} catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        cboModifierSalleNomSalle.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    String matiereSelectionne = newValue;
                    try {
                        cboModifierSalleNomSalle.setItems(serviceSalle.GetAllSalle());
                        txtModifierSalleIdSalle.setText(cboModifierSalleNomSalle.getSelectionModel().getSelectedItem().toString().replace("Salle ", "").trim());
                        cboModifierSalleEtage.setValue("Etage "+cboModifierSalleNomSalle.getSelectionModel().getSelectedItem().toString().replace("Salle ", "").trim().charAt(0));
                        } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 //                                           CREATION D UNE MATIERE                                                                          //
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @javafx.fxml.FXML
    public void btnCreerMatiereClicked(Event event) throws SQLException {
        apCreeMatiere.toFront();
    }
    @javafx.fxml.FXML
    public void btnCreeMatiereCreerClicked(Event event) throws SQLException {
        if(txtCreeMatiereNomMatiere.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Veuillez saisir une matière");
            alert.setHeaderText("");
            alert.showAndWait();
        }
        else
        {
            apCreeMatiere.toFront();
            Matiere uneMatiere = new Matiere(0,txtCreeMatiereNomMatiere.getText(),getStringObservable(tvCreeMatiereSousMatiere.getItems()));
            serviceMatieres.CreeMatiere(uneMatiere);
        }
    }

    @javafx.fxml.FXML
    public void btnCreeMatiereAjouterSousMatiereClicked(Event event) {
        if(txtCreeMatiereSousMatiere.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Veuillez saisir une sous-matière");
            alert.setHeaderText("");
            alert.showAndWait();
        }else{
            Matiere uneMatiere = new Matiere(txtCreeMatiereSousMatiere.getText());
            tvCreeMatiereSousMatiere.getItems().add(uneMatiere);
            txtCreeMatiereSousMatiere.setText(null);
        }
    }

    @javafx.fxml.FXML
    public void btnCreeMatiereSupprSousMatiereClicked(Event event) {
        if(tvCreeMatiereSousMatiere.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de sélection");
            alert.setContentText("Veuillez sélectionner une sous-matière");
            alert.setHeaderText("");
            alert.showAndWait();
        }else{
            tvCreeMatiereSousMatiere.getItems().remove(tvCreeMatiereSousMatiere.getSelectionModel().getSelectedItem());
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                           MODIFICATION MATIERE                                                                             //
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @javafx.fxml.FXML
    public void btnMenuModifierMatiereClicked(Event event) throws SQLException {
        cbModifMatiereSelectionnerMatiere.setItems(serviceMatieres.GetAllMatiere());
        apModifMatiere.toFront();
    }

    @javafx.fxml.FXML
    public void btnModifMatiereAjouterSousMatiereClicked(Event event) {
        if(txtModifMatiereSousMatiere.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Veuillez saisir une sous-matière");
            alert.setHeaderText("");
            alert.showAndWait();
        }else{
            Matiere uneMatiere = new Matiere(txtModifMatiereSousMatiere.getText());
            tvModifMatiereSousMatiere.getItems().add(uneMatiere);
            txtModifMatiereSousMatiere.setText(null);
        }
    }

    @javafx.fxml.FXML
    public void btnModifMatiereModifierClicked(Event event) throws SQLException {
        Matiere matiere = new Matiere(cbModifMatiereSelectionnerMatiere.getSelectionModel().getSelectedItem().toString(),getStringObservable(tvModifMatiereSousMatiere.getItems()),txtModifMatiereNomMatiere.getText());
        serviceMatieres.ModifierMatiere(matiere);
        cbModifMatiereSelectionnerMatiere.setValue("");
        txtModifMatiereSousMatiere.setText("");
        txtModifMatiereNomMatiere.setText("");
        tvModifMatiereSousMatiere.getItems().clear();
    }

    @javafx.fxml.FXML
    public void btnModifMatiereAnnulerClicked(Event event) {
        cbModifMatiereSelectionnerMatiere.setValue("");
        txtModifMatiereSousMatiere.setText("");
        txtModifMatiereNomMatiere.setText("");
        tvModifMatiereSousMatiere.getItems().clear();
    }

    @javafx.fxml.FXML
    public void btnModifMatiereSupprSousMatiereClicked(Event event) {
        if(tvModifMatiereSousMatiere.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de sélection");
            alert.setContentText("Veuillez sélectionner une sous-matière");
            alert.setHeaderText("");
            alert.showAndWait();
        }else{
            tvModifMatiereSousMatiere.getItems().remove(tvModifMatiereSousMatiere.getSelectionModel().getSelectedItem());
        }
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                           CREATION SALLE                                                                                   //
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @javafx.fxml.FXML
    public void btnCreeSalleClicked(Event event) throws SQLException {
        String etageString =cboCreeSalleEtage.getSelectionModel().getSelectedItem().toString();
        String numeroEtage = etageString.replace("Etage ", "").trim();
        String code_salle = "Salle "+ txtCreeSalleId.getText();
        int etage =Integer.parseInt(numeroEtage);
        int idSalle = Integer.parseInt(txtCreeSalleId.getText());
        Salle uneSalle = new Salle(idSalle,code_salle,etage);
        serviceSalle.CreeSalle(uneSalle);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                           MODIFICATION SALLE                                                                               //
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @javafx.fxml.FXML
    public void btnMenuMatiereClicked(Event event) {
    }

    @javafx.fxml.FXML
    public void btnModifierSalleCreeClicked(Event event) throws SQLException {
        String etageString = cboModifierSalleEtage.getSelectionModel().getSelectedItem().toString();
        String numeroEtage = etageString.replace("Etage ", "").trim();
        String code_salle = "Salle "+ txtModifierSalleIdSalle.getText();
        int etage =Integer.parseInt(numeroEtage);
        int encienIdSalle = Integer.parseInt(cboModifierSalleNomSalle.getSelectionModel().getSelectedItem().toString().replace("Salle","").trim());
        int idSalle = Integer.parseInt(txtModifierSalleIdSalle.getText());
        Salle uneSalle = new Salle(encienIdSalle,code_salle,etage,idSalle);
        serviceSalle.ModifierSalle(uneSalle);
    }

    @javafx.fxml.FXML
    public void btnModifierSalleAnnulerClicked(Event event) {
        txtModifierSalleIdSalle.setText("");
        cboModifierSalleEtage.setValue("");
        cboModifierSalleNomSalle.setValue("");
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////                                           MODIFICATION SALLE                                                                              //
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @javafx.fxml.FXML
    public void btnMenuSalleClicked(Event event) {
    }

    @javafx.fxml.FXML
    public void btnMenuSoutienClicked(Event event) {
    }

    @javafx.fxml.FXML
    public void btnMenuStatistiqueClicked(Event event) {
    }
    public String getStringObservable(ObservableList<Matiere> leSousMatieres) {
        String result = "";
        for (Matiere uneSousMatiere : leSousMatieres) {
            result += "#"+uneSousMatiere.getSousMatiere();
        }

        return result;
    }

    public ObservableList<String> getObservableSplit(String sousMatiere){
        ObservableList<String> leSousMatieres = FXCollections.observableArrayList();
        String[] splitSousMatiere = sousMatiere.split("#");
        for (String uneSousMatiere : splitSousMatiere)
        {
            if (!uneSousMatiere.isEmpty())
            {
                leSousMatieres.add(uneSousMatiere);
            }
        }
        return leSousMatieres;
    }


    @javafx.fxml.FXML
    public void btnMenuCreerSalleClicked(Event event) {
        apCreeSalle.toFront();
    }

    @javafx.fxml.FXML
    public void btnMenuModifierSalleClicked(Event event) throws SQLException {
        appModifierSalle.toFront();
        cboModifierSalleNomSalle.setItems(serviceSalle.GetAllSalle());
    }
}

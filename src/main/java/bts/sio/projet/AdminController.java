package bts.sio.projet;

import bts.sio.projet.Entities.Matiere;
import bts.sio.projet.Entities.User;
import bts.sio.projet.Services.*;
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
    private User user;
    @javafx.fxml.FXML
    private AnchorPane apCreeMatiere;
    @javafx.fxml.FXML
    private VBox vbxDemande;
    @javafx.fxml.FXML
    private Button btnCreerMatiere;
    @javafx.fxml.FXML
    private Button btnModifierMatiere;
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
        apCreeMatiere.toFront();
        tcCreeMatiereSousMatiere.setCellValueFactory(new PropertyValueFactory<>("sousMatiere"));
    }
    @javafx.fxml.FXML
    public void btnCreeMatiereCreerClicked(Event event) throws SQLException {
        if(txtCreeMatiereNomMatiere.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Veuillez saisir une matière");
            alert.setHeaderText("");
            alert.showAndWait();
        }else{
            //on doit mettre un "#" devant chaque sous_matiere
            /*ObservableList<String> listesSousMatieres = tvCreeMatiereSousMatiere.getItems();
            for (int i = 0; i < listesSousMatieres.size(); i++) {
                listesSousMatieres.set(i, "#" + listesSousMatieres.get(i));
            }
            System.out.println(listesSousMatieres);*/
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

    @javafx.fxml.FXML
    public void btnModifierMatiereClicked(Event event) {
    }

    @javafx.fxml.FXML
    public void btnMenuMatiereClicked(Event event) {
    }

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
    public void btnCreerMatiereClicked(Event event) throws SQLException {

    }
}

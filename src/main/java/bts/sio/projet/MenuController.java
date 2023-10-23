package bts.sio.projet;

import bts.sio.projet.Entities.Matiere;
import bts.sio.projet.Entities.User;
import bts.sio.projet.Services.ServiceUsers;
import bts.sio.projet.Tools.ConnexionBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MenuController implements Initializable
{
    ConnexionBDD maCnx;
    Matiere matiere ;
    @javafx.fxml.FXML
    private Button btnCreerDemande;
    @javafx.fxml.FXML
    private Button btnModifDemande;
    @javafx.fxml.FXML
    private Button btnVoirDemande;
    @javafx.fxml.FXML
    private Button btnEnregistrerComp;
    @javafx.fxml.FXML
    private Button btnModifComp;
    @javafx.fxml.FXML
    private Button btnVoirComp;
    @javafx.fxml.FXML
    private Button btnVoirStats;
    @javafx.fxml.FXML
    private AnchorPane apFaireDemande;
    @javafx.fxml.FXML
    private Button btnValiderDem;
    @javafx.fxml.FXML
    private DatePicker datepDebutDem;
    @javafx.fxml.FXML
    private DatePicker datepFinDem;
    @javafx.fxml.FXML
    private TreeView tvMesDemandes;
    @javafx.fxml.FXML
    private Button btnAnnulerDem;
    @javafx.fxml.FXML
    private ComboBox cboMatiereDem;
    @javafx.fxml.FXML
    private ChoiceBox chboxSousMatiereDem;

    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            maCnx = new ConnexionBDD();
            //marche pas encore
            //cboMatiereDem.getItems().addAll(matiere.GetAllMatiere());
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
    // Partie de Léo
    // Bouton Créer une demande (et voir ses demandes)
    @javafx.fxml.FXML
    public void btnCreerDemandeClicked(Event event)
    {
        apFaireDemande.toFront();
    }
    // Bouton valider une demande
    @javafx.fxml.FXML
    public void btnValiderDemClicked(Event event)
    {
        if(datepDebutDem.getValue() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de sélection");
            alert.setContentText("Veuillez sélectionner une date de début pour votre demande");
            alert.setHeaderText("");
            alert.showAndWait();
        }
        else if(datepFinDem.getValue() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de sélection");
            alert.setContentText("Veuillez sélectionner une date de fin pour votre demande");
            alert.setHeaderText("");
            alert.showAndWait();
        }
        else if(cboMatiereDem.getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de sélection");
            alert.setContentText("Veuillez sélectionner une matière pour votre demande");
            alert.setHeaderText("");
            alert.showAndWait();
        }
        else if(chboxSousMatiereDem.getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de sélection");
            alert.setContentText("Veuillez sélectionner une ou plusieurs sous-matière(s) pour votre demande");
            alert.setHeaderText("");
            alert.showAndWait();
        }
        else
        {
            String dateDébutDemande = datepDebutDem.getValue().toString();
            String datefinDemande = datepDebutDem.getValue().toString();
            String matiere = cboMatiereDem.getSelectionModel().getSelectedItem().toString();
            String sousMatiere = chboxSousMatiereDem.getSelectionModel().getSelectedItem().toString();


        }
    }

    // Bouton annuler une demande
    @javafx.fxml.FXML
    public void btnAnnulerDemClicked(Event event)
    {
    }

    // Bouton modifier une demande
    @javafx.fxml.FXML
    public void btnModifDemandeClicked(Event event)
    {
    }

    // Bouton voir les demandes des autres (et les siennes)
    @javafx.fxml.FXML
    public void btnVoirDemandeClicked(Event event)
    {
    }

    // Partie de Pierre

    @javafx.fxml.FXML
    public void btnEnregistrerCompClicked(Event event) {
    }

    @javafx.fxml.FXML
    public void btnModifCompClicked(Event event) {
    }

    @javafx.fxml.FXML
    public void btnVoirCompClicked(Event event) {
    }


    // Partie de Claude

    @javafx.fxml.FXML
    public void btnVoirStatsClicked(Event event) {
    }
}

package bts.sio.projet;

import bts.sio.projet.Entities.Matiere;
import bts.sio.projet.Entities.User;
import bts.sio.projet.Services.ServiceMatieres;
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
    ServiceMatieres serviceMatieres = new ServiceMatieres();
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
    private AnchorPane apEnregistrerComp;
    @javafx.fxml.FXML
    private ChoiceBox cbCompPrincipale;
    @javafx.fxml.FXML
    private ChoiceBox cbCompSecondaire;
    @javafx.fxml.FXML
    private Button btnValiderComp;
    @javafx.fxml.FXML
    private Button btnAnnulerComp;
    @javafx.fxml.FXML
    private AnchorPane apStat;
    @javafx.fxml.FXML
    private MenuButton menuSousMatiere;

    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            maCnx = new ConnexionBDD();
            serviceMatieres = new ServiceMatieres();
            ObservableList<Matiere> lesMatieres = FXCollections.observableArrayList();
            lesMatieres = serviceMatieres.GetAllMatiereObj();
            for(Matiere uneMatiere : lesMatieres)
            {
                cboMatiereDem.getItems().add(uneMatiere.getDesignation());
            }

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
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(datepDebutDem.getValue() == null)
        {
            alert.setTitle("Erreur de sélection");
            alert.setContentText("Veuillez sélectionner une date de début pour votre demande");
            alert.setHeaderText("");
            alert.showAndWait();
        }
        else if(datepFinDem.getValue() == null)
        {
            alert.setTitle("Erreur de sélection");
            alert.setContentText("Veuillez sélectionner une date de fin pour votre demande");
            alert.setHeaderText("");
            alert.showAndWait();
        }
        else if(cboMatiereDem.getSelectionModel().getSelectedItem() == null)
        {
            alert.setTitle("Erreur de sélection");
            alert.setContentText("Veuillez sélectionner une matière pour votre demande");
            alert.setHeaderText("");
            alert.showAndWait();
        }
        else
        {
            String dateDébutDemande = datepDebutDem.getValue().toString();
            String datefinDemande = datepDebutDem.getValue().toString();
            String matiere = cboMatiereDem.getSelectionModel().getSelectedItem().toString();
            récupérerLesCasesCochées();
        }
    }

    // initializer la check box

    @Deprecated
    public void chboxSousMatiereDem(Event event) throws SQLException {
    }
    public void récupérerLesCasesCochées() {
        String sousMatiere = "";
        ObservableList<MenuItem> items = menuSousMatiere.getItems();
        for (MenuItem item : items) {
            if (item instanceof CustomMenuItem) {
                CustomMenuItem customItem = (CustomMenuItem) item;
                CheckBox checkBox = (CheckBox) customItem.getContent();

                if (checkBox.isSelected()) {
                    String sousMatiereSelectioner = checkBox.getText();
                     sousMatiere += "#"+sousMatiereSelectioner;
                }
            }
        }
        System.out.println(sousMatiere);
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
    @javafx.fxml.FXML
    public void btnValiderCompClicked(Event event) {
    }

    @javafx.fxml.FXML
    public void btnAnnulerCompClicked(Event event) {
    }


    // Partie de Claude

    @javafx.fxml.FXML
    public void btnVoirStatsClicked(Event event) {
        apStat.toFront();
    }

    @javafx.fxml.FXML
    public void cboMatiereDemClicked(Event event) {
    }

    @javafx.fxml.FXML
    public void menuSousMatiereClicked(Event event) throws SQLException {
        if (cboMatiereDem.getSelectionModel().getSelectedItem() == null) {
            return;
        } else {
            String matiereSelectionne = cboMatiereDem.getSelectionModel().getSelectedItem().toString();
            ObservableList<String> sousMatieres = serviceMatieres.GetAllSousMatiere(matiereSelectionne);
            menuSousMatiere.getItems().clear();

            for (String sousMatiere : sousMatieres) {
                CustomMenuItem customMenuItem = new CustomMenuItem(new CheckBox(sousMatiere));
                customMenuItem.setHideOnClick(false);
                menuSousMatiere.getItems().add(customMenuItem);
            }
        }
    }
}

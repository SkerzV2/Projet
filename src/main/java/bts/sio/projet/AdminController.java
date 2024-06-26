package bts.sio.projet;

import bts.sio.projet.Entities.Matiere;
import bts.sio.projet.Entities.Salle;
import bts.sio.projet.Entities.Soutient;
import bts.sio.projet.Entities.User;
import bts.sio.projet.Services.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class AdminController implements Initializable {
    ServiceUsers serviceUsers;
    ServiceMatieres serviceMatieres;
    ServiceDemandes serviceDemandes;
    ServiceCompetences serviceCompetences;
    ServiceSoutients serviceSoutients;
    ServiceSalle serviceSalle;
    ObservableList lesSoutiens;
    Soutient unSoutient;
    private User user;
    @javafx.fxml.FXML
    private AnchorPane apCreeMatiere;
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
    @javafx.fxml.FXML
    private Button btnGererSoutiensAssigner;
    @javafx.fxml.FXML
    private Button btnGererSoutiensAnnuler;
    @javafx.fxml.FXML
    private ComboBox cboGererSoutiensSelectionnerSalle;
    @javafx.fxml.FXML
    private ComboBox cboGererSoutiensStatut;
    @javafx.fxml.FXML
    private AnchorPane apGererSoutiens;
    @javafx.fxml.FXML
    private TableView tvGererSoutiensSoutiens;
    @javafx.fxml.FXML
    private TableColumn tcGererSoutiensID;
    @javafx.fxml.FXML
    private TableColumn tcGererSoutiensDate;
    @javafx.fxml.FXML
    private TextArea txtGererSoutiensDescription;
    @javafx.fxml.FXML
    private AnchorPane apStatsDemande;
    @javafx.fxml.FXML
    private ComboBox cboStatsDemandeNiveau;
    @javafx.fxml.FXML
    private BarChart bcStatsDemandeParNiveauMatiere;
    @javafx.fxml.FXML
    private Button btnMenuStatsDemande;
    @javafx.fxml.FXML
    private AnchorPane apStatsSoutiens;
    @javafx.fxml.FXML
    private BarChart bcStatsSoutiensParEtudiant;
    @javafx.fxml.FXML
    private Button btnMenuStatsSoutiens;
    @javafx.fxml.FXML
    private Button btnMenuStatsSousMatieres;
    @javafx.fxml.FXML
    private BarChart bcStatsSoutiensParMatiere;
    @javafx.fxml.FXML
    private VBox vbxSalle;
    @javafx.fxml.FXML
    private VBox vbxStatistiques;
    @javafx.fxml.FXML
    private Button btnMenuGererSoutiens1;
    @javafx.fxml.FXML
    private VBox vbxMatiere;
    @javafx.fxml.FXML
    private VBox vbxSoutien;

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
        vbxMatiere.toFront();

        tcGererSoutiensID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcGererSoutiensDate.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));

        ObservableList<Integer> lesNiveaux = FXCollections.observableArrayList();
        for (int i = 0; i <= 5; i++) {
            lesNiveaux.add(i);
        }
        cboStatsDemandeNiveau.setItems(lesNiveaux);
        cboStatsDemandeNiveau.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                cboStatsDemandeNiveauClicked();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        tcCreeMatiereSousMatiere.setCellValueFactory(new PropertyValueFactory<>("sousMatiere"));
        ObservableList<String> lesEtages = FXCollections.observableArrayList();
        lesEtages.add("Etage 1");
        lesEtages.add("Etage 2");
        lesEtages.add("Etage 3");
        lesEtages.add("Etage 4");
        cboCreeSalleEtage.setItems(lesEtages);
        cboModifierSalleEtage.setItems(lesEtages);
        cboCreeSalleEtage.getSelectionModel().selectFirst();

        ObservableList<String> lesStatuts = FXCollections.observableArrayList();
        lesStatuts.add("Validé");
        cboGererSoutiensStatut.setItems(lesStatuts);
        cboGererSoutiensStatut.getSelectionModel().selectFirst();

        tcModifMatiereSousMatiere.setCellValueFactory(new PropertyValueFactory<>("sousMatiere"));

        cbModifMatiereSelectionnerMatiere.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    String matiereSelectionne = newValue;
                    try {
                        tvModifMatiereSousMatiere.setItems(serviceMatieres.GetAllSousMatiereOBJ(matiereSelectionne));
                        } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        cboModifierSalleNomSalle.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    String salleSelect = newValue;
                    try {
                        cboModifierSalleNomSalle.setItems(serviceSalle.GetAllSalle());
                        txtModifierSalleIdSalle.clear();
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
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(txtCreeMatiereNomMatiere.getText().isEmpty() || tvCreeMatiereSousMatiere.getItems().isEmpty()){
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Veuillez saisir une matière et des sous-matières");
            alert.setHeaderText("");
            alert.showAndWait();
        }
        else
        {
            apCreeMatiere.toFront();
            Matiere uneMatiere = new Matiere(0,txtCreeMatiereNomMatiere.getText(),getStringObservable(tvCreeMatiereSousMatiere.getItems()));

            if(serviceMatieres.VerifMatiereExiste(uneMatiere) == true){
                alert.setTitle("Erreur de saisie");
                alert.setContentText("Cette matière existe déja");
                alert.setHeaderText("");
                alert.showAndWait();

                txtCreeMatiereNomMatiere.setText("");
                txtCreeMatiereSousMatiere.setText("");
                tvCreeMatiereSousMatiere.setItems(null);
            }else{
                serviceMatieres.CreeMatiere(uneMatiere);

                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Création réussi");
                info.setContentText("La matière a bien été créée");
                info.setHeaderText("");
                info.showAndWait();
            }
        }
    }

    @javafx.fxml.FXML
    public void btnCreeMatiereAjouterSousMatiereClicked(Event event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(txtCreeMatiereNomMatiere.getText().isEmpty()){
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Veuillez saisir une matière");
            alert.setHeaderText("");
            alert.showAndWait();
        }
        else if(txtCreeMatiereSousMatiere.getText().isEmpty()){
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
    public void btnCreeMatiereAnnulerClicked(Event event) {
        txtCreeMatiereNomMatiere.setText(null);
        txtCreeMatiereSousMatiere.setText(null);
        tvCreeMatiereSousMatiere.setItems(null);
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
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(cbModifMatiereSelectionnerMatiere.getSelectionModel().getSelectedItem() == null)
        {
            alert.setTitle("Erreur de sélection");
            alert.setContentText("Veuillez sélectionner une matière");
            alert.setHeaderText("");
            alert.showAndWait();
        }
        if(txtModifMatiereSousMatiere.getText().isEmpty()){
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Veuillez saisir une sous-matière");
            alert.setHeaderText("");
            alert.showAndWait();
        }
        else
        {
            Matiere uneMatiere = new Matiere(txtModifMatiereSousMatiere.getText());
            tvModifMatiereSousMatiere.getItems().add(uneMatiere);
            txtModifMatiereSousMatiere.setText(null);
        }
    }

    @javafx.fxml.FXML
    public void btnModifMatiereModifierClicked(Event event) throws SQLException {
        if(cbModifMatiereSelectionnerMatiere.getSelectionModel().getSelectedItem() == null|| tvModifMatiereSousMatiere.getItems().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Veuillez saisir une sous-matière et un nom de matière");
            alert.setHeaderText("");
            alert.showAndWait();
        }
        else {
            Matiere matiere = new Matiere(cbModifMatiereSelectionnerMatiere.getSelectionModel().getSelectedItem().toString(), getStringObservable(tvModifMatiereSousMatiere.getItems()), cbModifMatiereSelectionnerMatiere.getSelectionModel().getSelectedItem().toString());
            serviceMatieres.ModifierMatiere(matiere);
            cbModifMatiereSelectionnerMatiere.setValue("");
            txtModifMatiereSousMatiere.setText("");
            tvModifMatiereSousMatiere.getItems().clear();

            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Modification réussi");
            info.setContentText("La matière a bien été modifiée");
            info.setHeaderText("");
            info.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void btnModifMatiereAnnulerClicked(Event event) {
        cbModifMatiereSelectionnerMatiere.setValue("");
        txtModifMatiereSousMatiere.setText("");
        tvModifMatiereSousMatiere.getItems().clear();
    }

    @javafx.fxml.FXML
    public void btnModifMatiereSupprSousMatiereClicked(Event event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(cbModifMatiereSelectionnerMatiere.getSelectionModel().getSelectedItem() == null){
            alert.setTitle("Erreur de sélection");
            alert.setContentText("Veuillez sélectionner une matière");
            alert.setHeaderText("");
            alert.showAndWait();
        }
        if(tvModifMatiereSousMatiere.getSelectionModel().getSelectedItem() == null){
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
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(txtCreeSalleId.getText().isEmpty())
        {
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Veuillez saisir un identifiant pour la salle");
            alert.setHeaderText("");
            alert.showAndWait();
        }
        else {
            String etageString = cboCreeSalleEtage.getSelectionModel().getSelectedItem().toString();
            String numeroEtage = etageString.replace("Etage ", "").trim();
            String code_salle = "Salle " + txtCreeSalleId.getText();
            int etage = parseInt(numeroEtage);
            int idSalle = parseInt(txtCreeSalleId.getText());
            Salle uneSalle = new Salle(idSalle, code_salle, etage);

            int premierChiffre = idSalle;
            while (premierChiffre >= 10) {
                premierChiffre /= 10;
            }

            if(serviceSalle.VerifSalleExiste(uneSalle) == true){
                alert.setTitle("Erreur de saisie");
                alert.setContentText("La salle que vous essayez de créer existe déja");
                alert.setHeaderText("");
                alert.showAndWait();
            }
            if(premierChiffre != etage){
                alert.setTitle("Erreur de saisie");
                alert.setContentText("Identifiant de la salle ne correspond pas à l'étage (1er étage : 100, 2ème étage : 200, ect... )");
                alert.setHeaderText("");
                alert.showAndWait();
            }
            else{
                serviceSalle.CreeSalle(uneSalle);
                cboCreeSalleEtage.getSelectionModel().selectFirst();
                txtCreeSalleId.setText(null);
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Création réussi");
                info.setContentText("La salle a bien été créée");
                info.setHeaderText("");
                info.showAndWait();
            }
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                           MODIFICATION SALLE                                                                               //
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @javafx.fxml.FXML
    public void btnMenuMatiereClicked(Event event) {
        vbxMatiere.toFront();
    }

    @javafx.fxml.FXML
    public void btnModifierSalleCreeClicked(Event event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(txtModifierSalleIdSalle.getText().isEmpty() || cboModifierSalleNomSalle.getSelectionModel().getSelectedItem().equals(""))
        {
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Veuillez saisir un identifiant pour la salle");
            alert.setHeaderText("");
            alert.showAndWait();
        }else {
            String etageString = cboModifierSalleEtage.getSelectionModel().getSelectedItem().toString();
            String numeroEtage = etageString.replace("Etage ", "").trim();
            String code_salle = "Salle " + txtModifierSalleIdSalle.getText();
            int etage = parseInt(numeroEtage);
            int encienIdSalle = parseInt(cboModifierSalleNomSalle.getSelectionModel().getSelectedItem().toString().replace("Salle", "").trim());
            int idSalle = parseInt(txtModifierSalleIdSalle.getText());
            Salle uneSalle = new Salle(encienIdSalle, code_salle, etage, idSalle);

            if(serviceSalle.VerifModifSalle(uneSalle) == true){
                alert.setTitle("Erreur de saisie");
                alert.setContentText("La salle été déja été assignée à un soutien, il ne peut donc pas être modifié");
                alert.setHeaderText("");
                alert.showAndWait();
            }else{
                serviceSalle.ModifierSalle(uneSalle);

                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Modification réussi");
                info.setContentText("La salle a bien été modifiée");
                info.setHeaderText("");
                info.showAndWait();
            }
        }
    }

    @javafx.fxml.FXML
    public void btnModifierSalleAnnulerClicked(Event event) {
        cboModifierSalleNomSalle.setValue(null);
        txtModifierSalleIdSalle.setText("");
        cboModifierSalleEtage.setValue(null);
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////                                           MODIFICATION SALLE                                                                              //
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @javafx.fxml.FXML
    public void btnMenuSalleClicked(Event event)
    {
        vbxSalle.toFront();
    }

    @javafx.fxml.FXML
    public void btnMenuSoutienClicked(Event event)
    {
        vbxSoutien.toFront();
    }

    @javafx.fxml.FXML
    public void btnMenuStatistiqueClicked(Event event)
    {
        vbxStatistiques.toFront();
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


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////                                    GESTION SOUTIENS ET ASSIGNATION SALLES                                                                              //
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @javafx.fxml.FXML
    public void btnMenuGererSoutiensClicked(Event event) throws SQLException
    {
        tvGererSoutiensSoutiens.setItems(serviceSoutients.GetAllSoutiens());
        apGererSoutiens.toFront();
    }

    @javafx.fxml.FXML
    public void tvGererSoutiensSoutiensClicked(Event event) throws SQLException
    {
        cboGererSoutiensSelectionnerSalle.setItems(serviceSalle.GetAllIdSalle());
        cboGererSoutiensSelectionnerSalle.getSelectionModel().selectFirst();
        ObservableList lesSoutients = serviceSoutients.GetAllSoutiens();
        String desc = ((Soutient)tvGererSoutiensSoutiens.getSelectionModel().getSelectedItem()).getDescription();
        txtGererSoutiensDescription.setText(desc);
    }

    @javafx.fxml.FXML
    public void btnGererSoutiensAssignerClicked(Event event) throws SQLException
    {
        if(!tvGererSoutiensSoutiens.getSelectionModel().getSelectedItems().isEmpty())
        {
            int statusSoutien = 1;
            if (!cboGererSoutiensSelectionnerSalle.getSelectionModel().getSelectedItem().toString().isEmpty())
            {
            statusSoutien = 2;
            }

            int unID = ((Soutient)tvGererSoutiensSoutiens.getSelectionModel().getSelectedItem()).getId();
            int idSalle = parseInt(cboGererSoutiensSelectionnerSalle.getSelectionModel().getSelectedItem().toString());
            String desc = txtGererSoutiensDescription.getText();
            Soutient unSoutient = new Soutient(statusSoutien, desc, idSalle, unID);

            serviceSoutients.updateSoutient(unSoutient);
            apGererSoutiens.toFront();
            cboGererSoutiensSelectionnerSalle.setItems(serviceSalle.GetAllIdSalle());
            tvGererSoutiensSoutiens.setItems(serviceSoutients.GetAllSoutiens());

            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Assignation réussi");
            info.setContentText("Le soutien a bien été assigné");
            info.setHeaderText("");
            info.showAndWait();
        }
        else if (tvGererSoutiensSoutiens.getSelectionModel().getSelectedItems().isEmpty())
        {
            Alert info = new Alert(Alert.AlertType.WARNING);
            info.setTitle("Erreur de sélection");
            info.setContentText("Veuillez sélectionner un soutien");
            info.setHeaderText("");
            info.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void btnGererSoutiensAnnulerClicked(Event event)
    {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Annulation");
        info.setContentText("Vous avez annulé la modification");
        info.setHeaderText("");
        info.showAndWait();

        apGererSoutiens.toFront();
        txtGererSoutiensDescription.setText("");
        cboGererSoutiensSelectionnerSalle.setItems(null);
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////                                    STATISTIQUES                                                                            //
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////
//                 STATS 1                   //
//////////////////////////////////////////////

    @javafx.fxml.FXML
    public void btnMenuStatsDemandeClicked(Event event)
    {
        apStatsDemande.toFront();
        cboStatsDemandeNiveau.getSelectionModel().selectFirst();;
    }

    @javafx.fxml.FXML
    public void cboStatsDemandeNiveauClicked() throws SQLException {
        bcStatsDemandeParNiveauMatiere.getData().clear();
        int niveau =((int)cboStatsDemandeNiveau.getSelectionModel().getSelectedItem());

        HashMap<String, Integer> donnees = serviceDemandes.getDemandeParNiveau(niveau);

        for (String matiere : donnees.keySet()) {
            XYChart.Series<String, Integer> serieGraphVisualiserDemandesParNiveau = new XYChart.Series<>();
            serieGraphVisualiserDemandesParNiveau.setName(matiere);

            int valeur = donnees.get(matiere);
            serieGraphVisualiserDemandesParNiveau.getData().add(new XYChart.Data<>("Total", valeur));

            bcStatsDemandeParNiveauMatiere.getData().add(serieGraphVisualiserDemandesParNiveau);
        }

    }



////////////////////////////////////////////////
//                 STATS 2                   //
//////////////////////////////////////////////

    @javafx.fxml.FXML
    public void btnMenuStatsSoutiensClicked(Event event) throws SQLException {
        apStatsSoutiens.toFront();
        bcStatsSoutiensParMatiere.setVisible(false);
        bcStatsSoutiensParEtudiant.setVisible(true);
        bcStatsSoutiensParEtudiant.getData().clear();

        HashMap<String, Integer> donnees = serviceSoutients.getSoutiensParEtudiant();

        for (String prenom : donnees.keySet()) {
            XYChart.Series<String, Integer> serieGraphVisualiserSoutiensParUser = new XYChart.Series<>();
            serieGraphVisualiserSoutiensParUser.setName(prenom);

            int valeur = donnees.get(prenom);
            serieGraphVisualiserSoutiensParUser.getData().add(new XYChart.Data<>("Total", valeur));

            bcStatsSoutiensParEtudiant.getData().add(serieGraphVisualiserSoutiensParUser);
        }
    }

////////////////////////////////////////////////
//                 STATS 3                   //
//////////////////////////////////////////////

    @javafx.fxml.FXML
    public void btnMenuStatsSousMatieresClicked(Event event) throws SQLException {
        apStatsSoutiens.toFront();
        bcStatsSoutiensParMatiere.setVisible(true);
        bcStatsSoutiensParEtudiant.setVisible(false);
        bcStatsSoutiensParMatiere.getData().clear();

        HashMap<String, Integer> donnees = serviceSoutients.getNbMatiereDemande();

        for (String matiere : donnees.keySet()) {
            XYChart.Series<String, Integer> serieGraphVisualiserSoutiensParMatiere = new XYChart.Series<>();
            serieGraphVisualiserSoutiensParMatiere.setName(matiere);

            int valeur = donnees.get(matiere);
            serieGraphVisualiserSoutiensParMatiere.getData().add(new XYChart.Data<>("Total", valeur));

            bcStatsSoutiensParMatiere.getData().add(serieGraphVisualiserSoutiensParMatiere);
        }
    }

}

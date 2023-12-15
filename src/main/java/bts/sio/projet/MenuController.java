package bts.sio.projet;

import bts.sio.projet.Entities.*;
import bts.sio.projet.Tools.Services.*;
import bts.sio.projet.Tools.ConnexionBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class MenuController implements Initializable {
    ConnexionBDD maCnx;
    ServiceMatieres serviceMatieres;
    ServiceDemandes serviceDemandes;
    ServiceCompetences serviceCompetences;
    ServiceSoutients serviceSoutients;
    ObservableList<Demande> lesDemandes;
    TreeMap<String, ObservableList<String>> lesCompetences;
    ObservableList<Matiere> lesMatieres;
    User user;
    ObservableList lesDemandesTv;
    TreeItem root;
    TreeItem rootComp;

    // AnchorPane ------------------------------------------------------------------------------------------------------

    @javafx.fxml.FXML
    private AnchorPane apFaireDemande;

    @javafx.fxml.FXML
    private AnchorPane apVisualiserDemandes;

    @javafx.fxml.FXML
    private AnchorPane apVisualiserComp;

    @javafx.fxml.FXML
    private AnchorPane apModifierDemande;

    @javafx.fxml.FXML
    private AnchorPane apVoirLesDemande;

    @javafx.fxml.FXML
    private AnchorPane apModificationDemande;

    @javafx.fxml.FXML
    private AnchorPane apEnregistrerCompetence;

    @javafx.fxml.FXML
    private AnchorPane apCreeSoutient;

    @javafx.fxml.FXML
    private AnchorPane apModificationCompetence;

    @javafx.fxml.FXML
    private AnchorPane apModifierComp;

    // Vbox ------------------------------------------------------------------------------------------------------------

    @javafx.fxml.FXML
    private VBox vbxDemande;

    @javafx.fxml.FXML
    private VBox vbxCompetence;

    @javafx.fxml.FXML
    private VBox vbxSoutenir;

    @javafx.fxml.FXML
    private VBox vbxStatistique;

    // Crée Demande ----------------------------------------------------------------------------------------------------

    @javafx.fxml.FXML
    private DatePicker datepFinDem;

    @javafx.fxml.FXML
    private TableView<Matiere> tvMatiereCreeDemande;

    @javafx.fxml.FXML
    private TableColumn tcMatiereCreeDemande;

    @javafx.fxml.FXML
    private TableView tvSousMatiereCreeDemande;

    @javafx.fxml.FXML
    private TableColumn tcSousMatiereCreeDemande;


    // Modifier Demande ------------------------------------------------------------------------------------------------

    @javafx.fxml.FXML
    private TableView<Demande> tvModifDemandes;

    @javafx.fxml.FXML
    private TableColumn tcMatiere;

    @javafx.fxml.FXML
    private TableColumn tcDateDebut;

    @javafx.fxml.FXML
    private TableColumn tcDateFin;

    @javafx.fxml.FXML
    private TableColumn tcSousMatieres;

    @javafx.fxml.FXML
    private TableColumn tcSousMatiereModifierDemande;

    @javafx.fxml.FXML
    private DatePicker dpModifierDemande;

    @javafx.fxml.FXML
    private ComboBox cboMatiereModifierDemande;

    @javafx.fxml.FXML
    private TableView tvSousMatiereModifierDemande;

    // Visualiser Demande ----------------------------------------------------------------------------------------------

    // Crée Compétence -------------------------------------------------------------------------------------------------
    @javafx.fxml.FXML
    private TableColumn tcMatiereCreeCompetence;

    @javafx.fxml.FXML
    private TableView tvSousMatiereCreeCompetence;

    @javafx.fxml.FXML
    private TableColumn tcSousMatiereCreeCompetence;

    @javafx.fxml.FXML
    private TableView<Matiere> tvMatiereCreeCompetence;

    // Visualiser Compétence -------------------------------------------------------------------------------------------

    @javafx.fxml.FXML
    private TreeView tvVisualiserComp;

    // Modifier Compétence ---------------------------------------------------------------------------------------------

    @javafx.fxml.FXML
    private TableColumn tcMatiereModifierCompetence;

    @javafx.fxml.FXML
    private TableColumn tcSousMatiereModifierCompetence;

    @javafx.fxml.FXML
    private ComboBox cboMatiereModificationCompetence;

    @javafx.fxml.FXML
    private TableView tvSousMatiereModificationCompetence;

    @javafx.fxml.FXML
    private TableColumn tcSousMatiereModificationCompetence;

    @javafx.fxml.FXML
    private TableView<Competence> tvModifComp;

    //Crée Soutient ----------------------------------------------------------------------------------------------------

    @javafx.fxml.FXML
    private ComboBox cboCreeSoutien;

    @javafx.fxml.FXML
    private DatePicker dpCreeSoutien;

    @javafx.fxml.FXML
    private TableView<Demande>tvCreeSoutien;

    @javafx.fxml.FXML
    private TableColumn tcSousMatiereCreeSoutien;

    @javafx.fxml.FXML
    private TextArea txtCreeSoutien;

    @javafx.fxml.FXML
    private TableView<Soutient> tvVisualiserAutresDemandes;

    @javafx.fxml.FXML
    private TableColumn tcNom;

    @javafx.fxml.FXML
    private TableColumn tcPrenom;

    @javafx.fxml.FXML
    private TableColumn tcMatiereSoutient;

    @javafx.fxml.FXML
    private TableColumn tcDateDebutSoutient;

    @javafx.fxml.FXML
    private TableColumn tcDateFinSoutient;

    @javafx.fxml.FXML
    private TableColumn tcSousMatiereSoutient;
    @javafx.fxml.FXML
    private Button btnAnnulerCreeSoutien;
    @javafx.fxml.FXML
    private Button btnValiderCreeSoutien;
    @javafx.fxml.FXML
    private Button btnModiferDemande;
    @javafx.fxml.FXML
    private Button BtnSupprimerDemande;
    @javafx.fxml.FXML
    private Button BtnModifierLaCompetence;
    @javafx.fxml.FXML
    private Button BtnSupprimerLaCompetence;
    @javafx.fxml.FXML
    private Button btnAnnulerDem;
    @javafx.fxml.FXML
    private Button btnValiderDem;
    @javafx.fxml.FXML
    private Button btnCreerDemande;
    @javafx.fxml.FXML
    private Button btnVoirDemande;
    @javafx.fxml.FXML
    private Button btnModifDemande;
    @javafx.fxml.FXML
    private Button btnCreeCompetence;
    @javafx.fxml.FXML
    private Button btnVoirComp;
    @javafx.fxml.FXML
    private Button btnModifComp;
    @javafx.fxml.FXML
    private Button btnVoirLesDemande;
    @javafx.fxml.FXML
    private Button btnVoirStats;
    @javafx.fxml.FXML
    private Button btnMenuDemande;
    @javafx.fxml.FXML
    private Button btnMenuCompetence;
    @javafx.fxml.FXML
    private Button btnMenuSoutenir;
    @javafx.fxml.FXML
    private Button btnMenuStatistique;
    @javafx.fxml.FXML
    private Button btnSoutien;
    @javafx.fxml.FXML
    private Button btnValiderComp;
    @javafx.fxml.FXML
    private Button btnAnnulerCreeComp;
    @javafx.fxml.FXML
    private Button BtnValiderModifier;
    @javafx.fxml.FXML
    private Button BtnAnnulerModifier;
    @javafx.fxml.FXML
    private Button btnAnnulerModificationCompetence;
    @javafx.fxml.FXML
    private Button btnValiderModificationCompetence;
    @javafx.fxml.FXML
    private TableView tvVisualiserMesDemandes;
    @javafx.fxml.FXML
    private TableColumn tcDateDebutDemVisualiser;
    @javafx.fxml.FXML
    private TableColumn tcDateFinDemandeVisualiser;
    @javafx.fxml.FXML
    private TableColumn tcMatiereDemandeVisualiser;
    @javafx.fxml.FXML
    private TableColumn tcSousMatiereDemandeVisualiser;
    @javafx.fxml.FXML
    private TableColumn tcStatusDemandeVisualiser;
    @javafx.fxml.FXML
    private Button BtnVoirHistoriqueDemande;
    @javafx.fxml.FXML
    private Button BtnVoirDemandeEnCours;


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    public void setUser(User user) {
        this.user = user;
    }

    public void setProjetController(ProjetController projetController) {
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            maCnx = new ConnexionBDD();
            serviceMatieres = new ServiceMatieres();
            serviceDemandes = new ServiceDemandes();
            serviceCompetences = new ServiceCompetences();
            serviceSoutients = new ServiceSoutients();
            lesMatieres = FXCollections.observableArrayList();
            lesMatieres.setAll(serviceMatieres.GetAllMatiereObj());
            tvSousMatiereModifierDemande.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            apFaireDemande.toFront();

            //Léo

            tcDateDebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
            tcDateFin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
            tcMatiere.setCellValueFactory(new PropertyValueFactory<>("designation"));
            tcSousMatieres.setCellValueFactory(new PropertyValueFactory<>("sousMatiere"));

            tvSousMatiereCreeDemande.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            tcMatiereCreeDemande.setCellValueFactory(new PropertyValueFactory<>("designation"));
            tcSousMatiereCreeDemande.setCellValueFactory(new PropertyValueFactory<>("sousMatiere"));

            tcDateDebutDemVisualiser.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
            tcDateFinDemandeVisualiser.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
            tcMatiereDemandeVisualiser.setCellValueFactory(new PropertyValueFactory<>("designation"));
            tcSousMatiereDemandeVisualiser.setCellValueFactory(new PropertyValueFactory<>("sousMatiere"));
            tcStatusDemandeVisualiser.setCellValueFactory(new PropertyValueFactory<>("status"));

            //Pierre
            tvSousMatiereCreeCompetence.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            tcMatiereCreeCompetence.setCellValueFactory(new PropertyValueFactory<>("designation"));
            tcSousMatiereCreeCompetence.setCellValueFactory(new PropertyValueFactory<>("sousMatiere"));

            tcMatiereModifierCompetence.setCellValueFactory(new PropertyValueFactory<>("designation"));
            tcSousMatiereModifierCompetence.setCellValueFactory(new PropertyValueFactory<>("sousMatiere"));

            tvSousMatiereModificationCompetence.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            tcSousMatiereModificationCompetence.setCellValueFactory(new PropertyValueFactory<>("sousMatiere"));

            //Claude
            tcDateDebutSoutient.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
            tcDateFinSoutient.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
            tcMatiereSoutient.setCellValueFactory(new PropertyValueFactory<>("matiere"));
            tcSousMatiereSoutient.setCellValueFactory(new PropertyValueFactory<>("sousMatiere"));
            tcNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            tcPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));

            tcSousMatiereCreeSoutien.setCellValueFactory(new PropertyValueFactory<>("sousMatiere"));

            root = new TreeItem("Mes demandes");
            rootComp = new TreeItem("Mes Compétences");
            tvVisualiserComp.setRoot(rootComp);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                                           //
    //                                                                                                          //
    //                                           Partie de Léo                                                 //
    //                                                                                                        //
    //                                                                                                       //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                           CREATION D UNE DEMANDE                                                                         //
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // afficher l'anchor pane création demande
    @javafx.fxml.FXML
    public void btnCreerDemandeClicked(Event event){
        tvMatiereCreeDemande.setItems(lesMatieres);
        apFaireDemande.toFront();
    }

    // Création demande / afficher les sous matières
    @javafx.fxml.FXML
    public void tvMatiereCreeDemandeClicked(Event event) throws SQLException {
        ObservableList<Matiere> sousMatieres = serviceMatieres.GetAllSousMatiereOBJ(tvMatiereCreeDemande.getSelectionModel().getSelectedItem().getDesignation());
        tvSousMatiereCreeDemande.setItems(sousMatieres);
    }

    // button de valider pour la création d'une demande
    @javafx.fxml.FXML
    public void btnValiderDemClicked(Event event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        String dateNow = LocalDate.now().toString();
        String erreurs = "";

        if (datepFinDem.getValue() == null)
        {
            erreurs += "Veuillez sélectionner une date de fin valide (ultérieure à la date de début)\n";
        }

        if (datepFinDem.getValue() != null)
        {
            LocalDate debut = LocalDate.parse(dateNow);
            LocalDate fin = datepFinDem.getValue();

            if (debut.isAfter(fin))
            {
                erreurs += "La date de fin ne peut pas être avant la date d'aujourd'hui\n";
            }
        }

        if (tvMatiereCreeDemande.getSelectionModel().isEmpty())
        {
            erreurs += "Veuillez sélectionner une matière\n";
        }

        if (tvSousMatiereCreeDemande.getSelectionModel().getSelectedItems().isEmpty())
        {
            erreurs += "Veuillez sélectionner une ou plusieurs sous-matières\n";
        }

        if (!erreurs.isEmpty())
        {
            alert.setTitle("Erreurs de sélection");
            alert.setHeaderText("");
            alert.setContentText(erreurs);
            alert.showAndWait();
        } else
        {
            ObservableList<Matiere> lesSousMatiere=tvSousMatiereCreeDemande.getSelectionModel().getSelectedItems();
            String dateFinDemande = datepFinDem.getValue().toString();
            String nomMatiereSelectionnee = tvMatiereCreeDemande.getSelectionModel().getSelectedItem().getDesignation();
            String sousmatiere =getStringObservable(lesSousMatiere) ;
            int idMatiere = tvMatiereCreeDemande.getSelectionModel().getSelectedItem().getIdMatiere();


            Demande uneDemande = new Demande(dateNow, dateFinDemande, sousmatiere, user.getId(), idMatiere, 1, nomMatiereSelectionnee);

            serviceDemandes.creationDemande(uneDemande);
            datepFinDem.setValue(null);
            tvSousMatiereCreeDemande.setItems(null);
            tvMatiereCreeDemande.setItems(null);
            tvMatiereCreeDemande.setItems(lesMatieres);
        }
    }

    // Bouton annuler une demande (mettre les valeurs par défaut)
    @javafx.fxml.FXML
    public void btnAnnulerDemClicked(Event event){
        datepFinDem.setValue(null);
        tvSousMatiereCreeDemande.setItems(null);
        tvMatiereCreeDemande.setItems(null);
        tvMatiereCreeDemande.setItems(lesMatieres);
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                           VISUALISER SES DEMANDES                                                                         //
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // afficher l'anchor pane visualisé demande
    @javafx.fxml.FXML
    public void btnVoirDemandeClicked(Event event) throws SQLException
    {
        apVisualiserDemandes.toFront();
        BtnVoirDemandeEnCours.setVisible(false);
        BtnVoirHistoriqueDemande.setVisible(true);
        lesDemandes = serviceDemandes.getAllDemandesEnCours(user.getId());
    }

    @javafx.fxml.FXML
    public void BtnVoirHistoriqueDemandeClicked(Event event) throws SQLException {
        BtnVoirDemandeEnCours.setVisible(true);
        BtnVoirHistoriqueDemande.setVisible(false);
        refreshTv(tvVisualiserMesDemandes,serviceDemandes.getAllDemandesEncienne(user.getId()));
    }
    @javafx.fxml.FXML
    public void BtnVoirDemandeEnCoursClicked(Event event) throws SQLException {
        BtnVoirDemandeEnCours.setVisible(false);
        BtnVoirHistoriqueDemande.setVisible(true);
        refreshTv(tvVisualiserMesDemandes,serviceDemandes.getAllDemandesEnCours(user.getId()));
    }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                           MODIFICATION D UNE DEMANDE                                                                      //
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // afficher l'anchor pane modification demande
    @javafx.fxml.FXML
    public void btnModifDemandeClicked(Event event) throws SQLException {
        apModifierDemande.toFront();
        lesDemandesTv = serviceDemandes.getAllDemandesTv(user.getId());
        tvModifDemandes.setItems(lesDemandesTv);
    }

    // permet charger la view de modifer une demande
    @javafx.fxml.FXML
    public void btnModiferDemandeClicked(Event event) throws IOException, SQLException {
        Demande demandeSelectionnee = tvModifDemandes.getSelectionModel().getSelectedItem();

        if(demandeSelectionnee == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de sélection");
            alert.setContentText("Veuillez sélectionner une demande pour supprimer");
            alert.setHeaderText("");
            alert.showAndWait();
        }
        else
        {
            apModificationDemande.toFront();
            ObservableList<Matiere> sousMatieres = serviceMatieres.GetAllSousMatiereOBJ(demandeSelectionnee.getDesignation());
            ObservableList<String> demandeSousMatieres = getObservableSplit(demandeSelectionnee.getSousMatiere());
            tvSousMatiereModifierDemande.setItems(sousMatieres);

            for (String sousMatiere : demandeSousMatieres) {
                for (String demandesousMatiere : demandeSousMatieres) {
                    if (sousMatiere.equals(demandesousMatiere)) {
                        int index = demandeSousMatieres.indexOf(sousMatiere);
                        tvSousMatiereModifierDemande.getSelectionModel().select(index);
                    }
                }
            }
            dpModifierDemande.setValue(LocalDate.parse(demandeSelectionnee.getDateFin()));
            cboMatiereModifierDemande.setValue(demandeSelectionnee.getDesignation());
            cboMatiereModifierDemande.getSelectionModel().selectFirst();
            tcSousMatiereModifierDemande.setCellValueFactory(new PropertyValueFactory<>("sousMatiere"));

        }
    }
    @javafx.fxml.FXML
    public void BtnValiderModificationClicked(Event event) throws SQLException {
        String sousMatiere = getStringObservable(tvSousMatiereModifierDemande.getSelectionModel().getSelectedItems());
        if (sousMatiere == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de sélection");
            alert.setContentText("Veuillez sélectionner une Sous Matiere");
            alert.setHeaderText("");
            alert.showAndWait();

        } else {
            String dateUpdate = LocalDate.now().toString();
            String dateFin = dpModifierDemande.getValue().toString();

            int idDemande = tvModifDemandes.getSelectionModel().getSelectedItem().getIdDemande();

            serviceDemandes.modifDemande(user.getId(), dateUpdate, dateFin, sousMatiere, idDemande);
            apModifierDemande.toFront();
        }
    }

    // Je peux supprimer une demande uniquement si il y a pas de soutient en cours
    @javafx.fxml.FXML
    public void BtnSupprimerDemandeClicked(Event event) throws SQLException {
        Demande demandeSelectionnee = (Demande) tvModifDemandes.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.ERROR);
        if (demandeSelectionnee == null) {
            alert.setTitle("Erreur de sélection");
            alert.setContentText("Veuillez sélectionner une demande pour supprimer");
            alert.setHeaderText("");
            alert.showAndWait();
        } else {
            if (demandeSelectionnee != null) {
                lesDemandesTv = serviceDemandes.getAllDemandesTv(user.getId());
                tcDateDebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
                tcDateFin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
                tcMatiere.setCellValueFactory(new PropertyValueFactory<>("designation"));
                tcSousMatieres.setCellValueFactory(new PropertyValueFactory<>("sousMatiere"));
                tvModifDemandes.setItems(lesDemandesTv);
                boolean retour=serviceDemandes.supprimerDemande(demandeSelectionnee.getIdDemande());
                if (retour==true){
                    alert.setTitle("Erreur de suppréssion");
                    alert.setContentText("Vous avez déja un soutien en cours ");
                    alert.setHeaderText("");
                    alert.showAndWait();
                }
            }
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                                           //
    //                                                                                                          //
    //                                           Partie de Pierre                                              //
    //                                                                                                        //
    //                                                                                                       //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                           CREATION D UNE COMPETENCE                                                                       //
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @javafx.fxml.FXML
    public void btnCreeCompetenceClicked(Event event) {
        tvSousMatiereCreeCompetence.setItems(null);
        tvMatiereCreeCompetence.setItems(null);
        tvMatiereCreeCompetence.setItems(lesMatieres);
        apEnregistrerCompetence.toFront();
    }
    @javafx.fxml.FXML
    public void tvMatiereCreeCompetenceClicked(Event event) throws SQLException {
        tvSousMatiereCreeCompetence.setItems(serviceMatieres.GetAllSousMatiereOBJ(tvMatiereCreeCompetence.getSelectionModel().getSelectedItem().getDesignation()));
    }
    @javafx.fxml.FXML
    public void btnAnnulerCreeCompClicked(Event event)
    {
        tvSousMatiereCreeCompetence.setItems(null);
        tvMatiereCreeCompetence.setItems(null);
        tvMatiereCreeCompetence.setItems(lesMatieres);
        apEnregistrerCompetence.toFront();
    }

    @javafx.fxml.FXML
    public void btnValiderCompClicked(Event event) throws SQLException {
        apEnregistrerCompetence.toFront();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (tvMatiereCreeCompetence.getSelectionModel().getSelectedItems().isEmpty()) {
            alert.setTitle("Erreur de sélection");
            alert.setContentText("Veuillez sélectionner une compétence principale");
            alert.setHeaderText("");
            alert.showAndWait();
        } else if (tvSousMatiereCreeCompetence.getSelectionModel().getSelectedItems().isEmpty()) {
            alert.setTitle("Erreur de sélection");
            alert.setContentText("Veuillez sélectionner une compétence secondaire");
            alert.setHeaderText("");
            alert.showAndWait();
        } else {
            String matiere = getStringObservable(tvMatiereCreeCompetence.getSelectionModel().getSelectedItems());
            String sousMatiere = getStringObservable(tvSousMatiereCreeCompetence.getSelectionModel().getSelectedItems());
            int idMatiere= tvMatiereCreeCompetence.getSelectionModel().getSelectedItem().getIdMatiere();
            // Créer la compétence
            Competence uneCompetence = new Competence(matiere, sousMatiere, user.getId(),idMatiere);
            boolean retour=serviceCompetences.creationCompetence(uneCompetence);
            if (retour==true){
                alert.setTitle("Erreur d'enregistrement");
                alert.setContentText("Vous avez déja une compétence pour cette matière \n" +
                        "veuillez vous rediriger vers modifier compétences ");
                alert.setHeaderText("");
                alert.showAndWait();
            }else{
                tvMatiereCreeCompetence.setItems(null);
                tvSousMatiereCreeCompetence.setItems(null);
                tvMatiereCreeCompetence.setItems(lesMatieres);
                apEnregistrerCompetence.toFront();
            }

        }

    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                           VISUALISER LES COMPETENCES                                                                      //
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @javafx.fxml.FXML
    public void btnVoirCompClicked(Event event) throws SQLException {
        apVisualiserComp.toFront();
        lesCompetences = serviceCompetences.getAllCompetences(user.getId());

        RemplirTreeViewSesCompetences();
    }

    public void RemplirTreeViewSesCompetences()
    {
        rootComp.getChildren().clear();
        tvVisualiserComp.getRoot().getChildren().clear();

        for (String nomMatiere : lesCompetences.keySet())
        {
            TreeItem noeudMatiere = new TreeItem(nomMatiere);
            for (String sousMatiere : lesCompetences.get(nomMatiere))
            {
                String[] splitSousMatiere = sousMatiere.split("#");
                for (String item : splitSousMatiere)
                {
                    if (!item.isEmpty())
                    {
                        TreeItem noeudSousMatiere = new TreeItem(item);
                        noeudMatiere.getChildren().add(noeudSousMatiere);
                    }
                }
            }
            rootComp.getChildren().add(noeudMatiere);
            rootComp.setExpanded(true);
        }
        tvVisualiserComp.setRoot(rootComp);
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                           MODIFICATION D UNE COMPETENCE                                                                   //
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @javafx.fxml.FXML
    public void btnModifCompClicked(Event event) throws SQLException {
        refreshTv(tvModifComp,serviceCompetences.getAllCompetenceObj(user.getId()));
        apModifierComp.toFront();
    }
    @javafx.fxml.FXML
    public void BtnModifierLaCompetenceClicked(Event event) throws SQLException {
        ObservableList<Matiere> sousMatieres = serviceMatieres.GetAllSousMatiereOBJ(tvModifComp.getSelectionModel().getSelectedItem().getDesignation());
        ObservableList<String> demandeSousMatieres = getObservableSplit(tvModifComp.getSelectionModel().getSelectedItem().getSousMatiere());
        refreshTv(tvSousMatiereModificationCompetence,sousMatieres);
        for (String sousMatiere : demandeSousMatieres) {
            for (String demandesousMatiere : demandeSousMatieres) {
                if (sousMatiere.equals(demandesousMatiere)) {
                    int index = demandeSousMatieres.indexOf(sousMatiere);
                    tvSousMatiereModificationCompetence.getSelectionModel().select(index);
                }
            }
        }
        cboMatiereModificationCompetence.setValue(tvModifComp.getSelectionModel().getSelectedItem().getDesignation());
        apModificationCompetence.toFront();
    }
    @javafx.fxml.FXML
    public void BtnSupprimerLaCompetenceClicked(Event event) throws SQLException {
        serviceCompetences.supprimerCompetence(tvModifComp.getSelectionModel().getSelectedItem().getIdCompetence());
        refreshTv(tvModifComp,serviceCompetences.getAllCompetenceObj(user.getId()));
    }

    @javafx.fxml.FXML
    public void btnAnnulerModificationCompetenceClicked(Event event) throws SQLException {
        tvModifComp.setItems(null);
        refreshTv(tvModifComp,serviceCompetences.getAllCompetenceObj(user.getId()));
    }

    @javafx.fxml.FXML
    public void btnValiderModificationCompetenceCllicked(Event event) throws SQLException {
        Competence laCompetence =tvModifComp.getSelectionModel().getSelectedItem();
        String lesSousMatieres=getStringObservable(tvSousMatiereModificationCompetence.getSelectionModel().getSelectedItems());
        serviceCompetences.updateCompetence(laCompetence.getIdCompetence(),lesSousMatieres);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                                           //
    //                                                                                                          //
    //                                           Partie de Claude                                              //
    //                                                                                                        //
    //                                                                                                       //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                           CREATION D UN SOUTIENT                                                                          //
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @javafx.fxml.FXML
    public void btnVoirLesDemandeClicked(Event event) throws SQLException {
        apVoirLesDemande.toFront();
        ObservableList<Soutient> lesAutresDemandesTv = serviceSoutients.getAllDemandesTv(user.getId());
        refreshTv(tvVisualiserAutresDemandes,lesAutresDemandesTv);
    }

    @javafx.fxml.FXML
    public void btnSoutienClicked(Event event) throws IOException, SQLException {
        Soutient demandeSelectionnee = tvVisualiserAutresDemandes.getSelectionModel().getSelectedItem();

        if(demandeSelectionnee == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de sélection");
            alert.setContentText("Veuillez sélectionner une demande pour effectuer un soutient");
            alert.setHeaderText("");
            alert.showAndWait();
        }
        else
        {
            apCreeSoutient.toFront();
            tvCreeSoutien.setItems(serviceDemandes.getAllSousMatieresDemande(demandeSelectionnee.getIdDemande()));
            cboCreeSoutien.setValue(demandeSelectionnee.getMatiere());
            cboCreeSoutien.getSelectionModel().selectFirst();
        }
    }

    @javafx.fxml.FXML
    public void btnValiderCreeSoutienClicked(Event event) throws SQLException {
        Soutient laDemandeSoutient= tvVisualiserAutresDemandes.getSelectionModel().getSelectedItem();
        String dateNow = LocalDate.now().toString();
        String erreurs="";
        Alert alert = new Alert(Alert.AlertType.WARNING);
        if (txtCreeSoutien.getText().isEmpty()) {
            erreurs += "La description est obligatoire";
        }
        if (dpCreeSoutien.getValue() != null)
        {
            LocalDate now = LocalDate.now();
            LocalDate date = dpCreeSoutien.getValue();
            LocalDate max = LocalDate.parse(laDemandeSoutient.getDateFin());

            if (now.isAfter(date))
            {
                erreurs += "La date du soutien ne peut pas être avant la date de debut\n";
            }
            if(date.isAfter(max)){
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
            serviceSoutients.CreeSoutient(laDemandeSoutient.getIdDemande(), laDemandeSoutient.getIdCompetence(), dpCreeSoutien.getValue().toString(), dateNow, txtCreeSoutien.getText(), laDemandeSoutient.getStatus());
            apVoirLesDemande.toFront();
            dpCreeSoutien.setValue(null);
            tvCreeSoutien.setItems(null);
            ObservableList<Soutient> lesAutresDemandesTv = serviceSoutients.getAllDemandesTv(user.getId());
            refreshTv(tvVisualiserAutresDemandes,lesAutresDemandesTv);
        }
    }
    @javafx.fxml.FXML
    public void btnAnnulerCreeSoutienClicked(Event event) throws SQLException {
        apVoirLesDemande.toFront();
        dpCreeSoutien.setValue(null);
        tvCreeSoutien.setItems(null);
        ObservableList<Soutient> lesAutresDemandesTv = serviceSoutients.getAllDemandesTv(user.getId());
        refreshTv(tvVisualiserAutresDemandes,lesAutresDemandesTv);
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                           VOIR STATISTIQUES                                                                               //
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @javafx.fxml.FXML
    public void btnVoirStatsClicked(Event event) {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                                           //
    //                                                                                                          //
    //                                           Fonction en commun                                            //
    //                                                                                                        //
    //                                                                                                       //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////

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
    public void btnMenuDemandeClicked(Event event) {
        vbxDemande.toFront();
    }

    @javafx.fxml.FXML
    public void btnMenuCompetenceClicked(Event event) {
        vbxCompetence.toFront();
    }

    @javafx.fxml.FXML
    public void btnMenuSoutenirClicked(Event event) {
        vbxSoutenir.toFront();
    }

    @javafx.fxml.FXML
    public void btnMenuStatistiqueClicked(Event event) {
        vbxStatistique.toFront();
    }
    public void refreshTv(TableView tv,ObservableList o){
        tv.setItems(null);
        tv.setItems(o);
    }
}

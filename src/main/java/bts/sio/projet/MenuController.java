package bts.sio.projet;

import bts.sio.projet.Entities.Competence;
import bts.sio.projet.Entities.Demande;
import bts.sio.projet.Entities.Matiere;
import bts.sio.projet.Entities.User;
import bts.sio.projet.Services.*;
import bts.sio.projet.Tools.ConnexionBDD;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class MenuController implements Initializable {
    ConnexionBDD maCnx;
    ObservableList<Matiere> lesMatieres = FXCollections.observableArrayList();
    ServiceMatieres serviceMatieres = new ServiceMatieres();
    ServicesDemandes servicesDemandes = new ServicesDemandes();
    ServiceCompetences serviceCompetences = new ServiceCompetences();
    private ProjetController projetController;
    User user;
    Matiere matiere;
    HashMap<String, TreeMap<String, ObservableList<String>>> lesDemandes;
    TreeMap<String, ObservableList<String>> lesCompetences;
    ObservableList lesDemandesTv;
    TreeItem root;
    TreeItem rootComp;

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
    private Button btnAnnulerDem;
    @javafx.fxml.FXML
    private ComboBox cboMatiereDem;
    @javafx.fxml.FXML
    private AnchorPane apEnregistrerComp;
    @javafx.fxml.FXML
    private ChoiceBox cbCompPrincipale;
    @javafx.fxml.FXML
    private Button btnValiderComp;
    @javafx.fxml.FXML
    private Button btnAnnulerComp;
    @javafx.fxml.FXML
    private AnchorPane apStat;
    @javafx.fxml.FXML
    private MenuButton menuSousMatiere;
    @javafx.fxml.FXML
    private AnchorPane apVisualiserDemandes;
    @javafx.fxml.FXML
    private TreeView tvVisualiserDemandes;
    @javafx.fxml.FXML
    private MenuButton mbSousMatieres;
    @javafx.fxml.FXML
    private AnchorPane apModifierDemande;
    @javafx.fxml.FXML
    private TableView tvModifDemandes;
    @javafx.fxml.FXML
    private TableColumn tcMatiere;
    @javafx.fxml.FXML
    private TableColumn tcDateDebut;
    @javafx.fxml.FXML
    private TableColumn tcDateFin;
    @javafx.fxml.FXML
    private TableColumn tcSousMatieres;
    @javafx.fxml.FXML
    private AnchorPane apVisualiserComp;
    @javafx.fxml.FXML
    private TreeView tvVisualiserComp;

    public void setUser(User user) {

        this.user = user;
    }

    public void setProjetController(ProjetController projetController) {
        this.projetController = projetController;
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            apFaireDemande.toFront();
            maCnx = new ConnexionBDD();
            serviceMatieres = new ServiceMatieres();
            lesMatieres = serviceMatieres.GetAllMatiereObj();

            for (Matiere uneMatiere : lesMatieres) {
                cboMatiereDem.getItems().add(uneMatiere.getDesignation());
                cbCompPrincipale.getItems().add(uneMatiere.getDesignation());
            }

            root = new TreeItem("Mes demandes");
            rootComp = new TreeItem("Mes Compétences");
            tvVisualiserDemandes.setRoot(root);
            tvVisualiserComp.setRoot(rootComp);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Ajoutez un ChangeListener pour surveiller les changements de sélection dans le ComboBox de matières
        cboMatiereDem.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    String matiereSelectionne = newValue;
                    try {
                        updateSousMatieres(matiereSelectionne);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                                           //
    //                                                                                                          //
    //                                           Partie de Léo                                                 //
    //                                                                                                        //
    //                                                                                                       //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    // afficher l'anchor pane création demande
    @javafx.fxml.FXML
    public void btnCreerDemandeClicked(Event event)
    {
        apFaireDemande.toFront();
    }

    // button de valider pour la création d'une demande
    @javafx.fxml.FXML
    public void btnValiderDemClicked(Event event) throws SQLException
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        String erreurs = "";

        if (datepDebutDem.getValue() == null || datepDebutDem.getValue().isBefore(LocalDate.now()))
        {
            erreurs += "Veuillez sélectionner une date de début valide (aujourd'hui ou ultérieure)\n";
        }

        if (datepFinDem.getValue() == null)
        {
            erreurs += "Veuillez sélectionner une date de fin valide (ultérieure à la date de début)\n";
        }

        if (datepDebutDem.getValue() != null && datepFinDem.getValue() != null)
        {
            LocalDate debut = datepDebutDem.getValue();
            LocalDate fin = datepFinDem.getValue();

            if (debut.isAfter(fin))
            {
                erreurs += "La date de début ne peut pas être après la date de fin\n";
            }
        }

        if (cboMatiereDem.getSelectionModel().isEmpty())
        {
            erreurs += "Veuillez sélectionner une matière\n";
        }

        if (menuSousMatiere.getItems().isEmpty())
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
            String dateDebutDemande = datepDebutDem.getValue().toString();
            String dateFinDemande = datepFinDem.getValue().toString();
            String nomMatiereSelectionnee = (String) cboMatiereDem.getSelectionModel().getSelectedItem();

            Matiere matiereSelectionnee = null;

            int idMatiere = 0;
            for (Matiere uneMatiere : lesMatieres)
            {
                if (uneMatiere.getDesignation().equals(nomMatiereSelectionnee))
                {
                    matiereSelectionnee = uneMatiere;
                    break;
                }
            }
            if (matiereSelectionnee != null)
            {
                idMatiere = matiereSelectionnee.getIdMatiere();
            }
            MenuButton menu = menuSousMatiere;
            String sous_matiere = recupererLesCasesCochees(menu);

            Demande uneDemande = new Demande(dateDebutDemande, dateFinDemande, sous_matiere, user.getId(), idMatiere, matiereSelectionnee.getDesignation());

            servicesDemandes.creationDemande(uneDemande);
        }
    }

    // Bouton annuler une demande
    @javafx.fxml.FXML
    public void btnAnnulerDemClicked(Event event)
    {
        datepDebutDem.setValue(null);
        datepFinDem.setValue(null);
        cboMatiereDem.getSelectionModel().clearSelection();
        menuSousMatiere.getItems().forEach(item ->
        {
            if (item instanceof CustomMenuItem)
            {
                CustomMenuItem customItem = (CustomMenuItem) item;
                CheckBox checkBox = (CheckBox) customItem.getContent();
                checkBox.setSelected(false);
            }
        });
        menuSousMatiere.getItems().clear();
    }

    // Bouton voir ses demandes
    @javafx.fxml.FXML
    public void btnVoirDemandeClicked(Event event) throws SQLException
    {
        apVisualiserDemandes.toFront();
        lesDemandes = servicesDemandes.getAllDemandes(user.getId());

        RemplirTreeViewSesDemandes();
    }

    // remplir les sous-matieres
    private void updateSousMatieres(String matiereSelectionne) throws SQLException
    {
        // Insérez le code pour mettre à jour le menuSousMatiere en fonction de la matière sélectionnée.
        // Vous pouvez utiliser la variable matiereSelectionne pour obtenir la matière sélectionnée.
        ObservableList<String> sousMatieres = serviceMatieres.GetAllSousMatiere(matiereSelectionne);
        menuSousMatiere.getItems().clear();

        for (String sousMatiere : sousMatieres)
        {
            CustomMenuItem customMenuItem = new CustomMenuItem(new CheckBox(sousMatiere));
            customMenuItem.setHideOnClick(false);
            menuSousMatiere.getItems().add(customMenuItem);
        }
    }

    // afficher les demandes dans visualiser
    public void RemplirTreeViewSesDemandes()
    {
        root.getChildren().clear();
        tvVisualiserDemandes.getRoot().getChildren().clear();

        for (String nomMatiere : lesDemandes.keySet())
        {
            TreeItem noeudMatiere = new TreeItem(nomMatiere);
            for (String dateDebut : lesDemandes.get(nomMatiere).keySet())
            {
                TreeItem noeudDate = new TreeItem(dateDebut);
                for (String sousMatiere : lesDemandes.get(nomMatiere).get(dateDebut))
                {
                    String[] splitSousMatiere = sousMatiere.split("#");
                    for (String item : splitSousMatiere)
                    {
                        if (!item.isEmpty())
                        {
                            TreeItem noeudSousMatiere = new TreeItem(item);
                            noeudDate.getChildren().add(noeudSousMatiere);
                        }
                    }
                }
                noeudMatiere.getChildren().add(noeudDate);
            }
            root.getChildren().add(noeudMatiere);
            root.setExpanded(true);
        }
        tvVisualiserDemandes.setRoot(root);
    }

    // Bouton modifier une demande
    @javafx.fxml.FXML
    public void btnModifDemandeClicked(Event event) throws SQLException
    {
        apModifierDemande.toFront();
        lesDemandesTv = servicesDemandes.getAllDemandesTv(user.getId());

        tcDateDebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        tcDateFin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        tcMatiere.setCellValueFactory(new PropertyValueFactory<>("designation"));
        tcSousMatieres.setCellValueFactory(new PropertyValueFactory<>("sousMatiere"));


        tvModifDemandes.setItems(lesDemandesTv);
    }

    // permet charger la view de modifer une demande
    @javafx.fxml.FXML
    public void tvModifDemandesClicked(Event event) throws IOException
    {
        Demande demandeSelectionnee = (Demande) tvModifDemandes.getSelectionModel().getSelectedItem();

        if (demandeSelectionnee != null)
        {
            String matiere = demandeSelectionnee.getDesignation();
            String dateDebut = demandeSelectionnee.getDateDebut();
            String dateFin = demandeSelectionnee.getDateFin();
            String sousMatiere = demandeSelectionnee.getSousMatiere();

            // Ensuite, vous pouvez ouvrir la fenêtre de modification et passer ces valeurs au contrôleur de modification.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modifierDemande-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            ModifierDemandeController modifController = fxmlLoader.getController();
            modifController.initUser(user);
            modifController.initDatas(demandeSelectionnee, matiere, dateDebut, dateFin, sousMatiere);
            Stage stage = new Stage();
            stage.setTitle("Modification d'une demande");
            stage.setScene(scene);
            stage.show();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                                           //
    //                                                                                                          //
    //                                           Partie de Pierre                                              //
    //                                                                                                        //
    //                                                                                                       //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    @javafx.fxml.FXML
    public void btnEnregistrerCompClicked(Event event) {
        apEnregistrerComp.toFront();
    }

    @javafx.fxml.FXML
    public void mbSousMatieresClicked(Event event) throws SQLException {
        if (cbCompPrincipale.getSelectionModel().getSelectedItem() == null) {
        } else {
            String matiere = cbCompPrincipale.getSelectionModel().getSelectedItem().toString();
            ObservableList<String> sousMatieres = serviceMatieres.GetAllSousMatiere(matiere);
            mbSousMatieres.getItems().clear();

            for (String sousMatiere : sousMatieres) {
                CustomMenuItem customMenuItem = new CustomMenuItem(new CheckBox(sousMatiere));
                customMenuItem.setHideOnClick(false);
                mbSousMatieres.getItems().add(customMenuItem);
            }
        }
    }

    @javafx.fxml.FXML
    public void btnModifCompClicked(Event event)
    {

    }

    @javafx.fxml.FXML
    public void btnVoirCompClicked(Event event) throws SQLException {
        apVisualiserComp.toFront();
        lesCompetences = serviceCompetences.getAllCompetences(user.getId());

        RemplirTreeViewSesCompetences();
    }

    public void RemplirTreeViewSesCompetences()
    {
        root.getChildren().clear();
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
            root.getChildren().add(noeudMatiere);
            root.setExpanded(true);
        }
        tvVisualiserComp.setRoot(rootComp);
    }

    @javafx.fxml.FXML
    public void btnValiderCompClicked(Event event) throws SQLException {
        apEnregistrerComp.toFront();
        Alert alert = new Alert(Alert.AlertType.ERROR);

        if (cbCompPrincipale.getValue() == null) {
            alert.setTitle("Erreur de sélection");
            alert.setContentText("Veuillez sélectionner une compétence principale");
            alert.setHeaderText("");
            alert.showAndWait();
        } else if (mbSousMatieres.getItems().isEmpty()) {
            alert.setTitle("Erreur de sélection");
            alert.setContentText("Veuillez sélectionner une compétence secondaire");
            alert.setHeaderText("");
            alert.showAndWait();
        } else {
            String matiere = cbCompPrincipale.getValue().toString();
            String sousMatiere = mbSousMatieres.getItems().toString();


            Matiere matiereSelectionnee = null;

            int idMatiere = 0;
            for (Matiere uneMatiere : lesMatieres) {
                if (uneMatiere.getDesignation().equals(matiere)) {
                    matiereSelectionnee = uneMatiere;
                    break;
                }
            }
            if (matiereSelectionnee != null) {
                idMatiere = matiereSelectionnee.getIdMatiere();
            }
            MenuButton menu = mbSousMatieres;
            String sous_matiere = recupererLesCasesCochees(menu);


            // Créer la compétence
            Competence uneCompetence = new Competence(matiere, sous_matiere, user.getId());

            serviceCompetences.creationCompetence(uneCompetence, idMatiere);

        }

    }

    @javafx.fxml.FXML
    public void btnAnnulerCompClicked(Event event) {
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                                           //
    //                                                                                                          //
    //                                           Partie de Claude                                              //
    //                                                                                                        //
    //                                                                                                       //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    @javafx.fxml.FXML
    public void btnVoirStatsClicked(Event event) {
        apStat.toFront();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                                           //
    //                                                                                                          //
    //                                           Fonction en commun                                             //
    //                                                                                                        //
    //                                                                                                       //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String recupererLesCasesCochees(MenuButton menu) {
        String sousMatiere = "";
        ObservableList<MenuItem> items = menu.getItems();
        for (MenuItem item : items) {
            if (item instanceof CustomMenuItem) {
                CustomMenuItem customItem = (CustomMenuItem) item;
                CheckBox checkBox = (CheckBox) customItem.getContent();

                if (checkBox.isSelected()) {
                    String sousMatiereSelectioner = checkBox.getText();
                    sousMatiere += "#" + sousMatiereSelectioner;
                }
            }
        }
        return sousMatiere;
    }
}

package bts.sio.projet;

import bts.sio.projet.Entities.Competence;
import bts.sio.projet.Entities.Demande;
import bts.sio.projet.Entities.Matiere;
import bts.sio.projet.Entities.User;
import bts.sio.projet.Services.*;
import bts.sio.projet.Tools.ConnexionBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

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
    TreeItem root;


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

    public void setUser(User user) {

        this.user = user;
    }

    public void setProjetController(ProjetController projetController) {
        this.projetController = projetController;
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            maCnx = new ConnexionBDD();
            serviceMatieres = new ServiceMatieres();
            lesMatieres = serviceMatieres.GetAllMatiereObj();


            for (Matiere uneMatiere : lesMatieres) {
                cboMatiereDem.getItems().add(uneMatiere.getDesignation());
                cbCompPrincipale.getItems().add(uneMatiere.getDesignation());
            }

            root = new TreeItem("Mes demandes");
            tvVisualiserDemandes.setRoot(root);

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
    @javafx.fxml.FXML
    public void btnCreerDemandeClicked(Event event) {
        apFaireDemande.toFront();
    }

    @javafx.fxml.FXML
    public void cboMatiereDemCliked(Event event) {

    }
    @javafx.fxml.FXML
    public void btnValiderDemClicked(Event event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        String erreurs = "";

        if (datepDebutDem.getValue() == null || datepDebutDem.getValue().isBefore(LocalDate.now())) {
            erreurs += "Veuillez sélectionner une date de début valide (aujourd'hui ou ultérieure)\n";
        }

        if (datepFinDem.getValue() == null) {
            erreurs += "Veuillez sélectionner une date de fin valide (ultérieure à la date de début)\n";
        }

        if (datepDebutDem.getValue() != null && datepFinDem.getValue() != null) {
            LocalDate debut = datepDebutDem.getValue();
            LocalDate fin = datepFinDem.getValue();

            if (debut.isAfter(fin)) {
                erreurs += "La date de début ne peut pas être après la date de fin\n";
            }
        }

        if (cboMatiereDem.getSelectionModel().isEmpty()) {
            erreurs += "Veuillez sélectionner une matière\n";
        }

        if (menuSousMatiere.getItems().isEmpty()) {
            erreurs += "Veuillez sélectionner une ou plusieurs sous-matières\n";
        }

        if (!erreurs.isEmpty()) {
            alert.setTitle("Erreurs de sélection");
            alert.setContentText(erreurs);
            alert.setHeaderText("");
            alert.showAndWait();
        } else {
            String dateDebutDemande = datepDebutDem.getValue().toString();
            String dateFinDemande = datepFinDem.getValue().toString();
            String nomMatiereSelectionnee = (String) cboMatiereDem.getSelectionModel().getSelectedItem();

            Matiere matiereSelectionnee = null;

            int idMatiere = 0;
            for (Matiere uneMatiere : lesMatieres) {
                if (uneMatiere.getDesignation().equals(nomMatiereSelectionnee)) {
                    matiereSelectionnee = uneMatiere;
                    break;
                }
            }
            if (matiereSelectionnee != null) {
                idMatiere = matiereSelectionnee.getIdMatiere();
            }
            MenuButton menu = menuSousMatiere;
            String sous_matiere = recupererLesCasesCochees(menu);

            // Créer la demande
            Demande uneDemande = new Demande(dateDebutDemande, dateFinDemande, sous_matiere, user.getId(), idMatiere);

            servicesDemandes.creationDemande(uneDemande);
        }
    }


    // Bouton annuler une demande
    @javafx.fxml.FXML
    public void btnAnnulerDemClicked(Event event) {

    }

    // Bouton modifier une demande
    @javafx.fxml.FXML
    public void btnModifDemandeClicked(Event event) {

    }

    // Bouton voir les demandes des autres (et les siennes)
    @javafx.fxml.FXML
    public void btnVoirDemandeClicked(Event event) throws SQLException {
        apVisualiserDemandes.toFront();
        lesDemandes = servicesDemandes.getAllDemandes(user.getId());

        RemplirTreeViewDemandes();
    }

    public void RemplirTreeViewDemandes() {
        root.getChildren().clear();
        tvVisualiserDemandes.getRoot().getChildren().clear();

        for (String nomMatiere : lesDemandes.keySet()) {
            TreeItem noeudMatiere = new TreeItem(nomMatiere);
            for (String dateDebut : lesDemandes.get(nomMatiere).keySet()) {
                TreeItem noeudDate = new TreeItem(dateDebut);
                for (String sousMatiere : lesDemandes.get(nomMatiere).get(dateDebut)) {
                    String[] splitSousMatiere = sousMatiere.split("#");
                    for (String item : splitSousMatiere) {
                        if (!item.isEmpty()) {
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
    public void btnModifCompClicked(Event event) {

    }

    @javafx.fxml.FXML
    public void btnVoirCompClicked(Event event) {
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

    @javafx.fxml.FXML
    public void menuSousMatiereClicked(Event event) throws SQLException {
        if (cboMatiereDem.getSelectionModel().getSelectedItem() == null) {
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

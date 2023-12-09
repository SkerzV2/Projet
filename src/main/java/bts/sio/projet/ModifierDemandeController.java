package bts.sio.projet;

import bts.sio.projet.Entities.Demande;
import bts.sio.projet.Entities.Matiere;
import bts.sio.projet.Entities.User;
import bts.sio.projet.Tools.Services.ServiceDemandes;
import bts.sio.projet.Tools.Services.ServiceMatieres;
import bts.sio.projet.Tools.ConnexionBDD;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class ModifierDemandeController implements Initializable
{
    private Demande laDemande;
    @javafx.fxml.FXML
    private DatePicker datepFinModif;
    @javafx.fxml.FXML
    private ComboBox cboMatiereModif;
    @javafx.fxml.FXML
    private MenuButton mbSousMatiereModif;
    @javafx.fxml.FXML
    private Button btnValiderModif;
    @javafx.fxml.FXML
    private Button btnAnnulerModifier;

    ConnexionBDD maCnx;
    User user;
    ServiceMatieres serviceMatieres;
    ServiceDemandes serviceDemandes = new ServiceDemandes();
    MenuController menuController = new MenuController();
    ObservableList<Matiere> lesMatieres = FXCollections.observableArrayList();
    @FXML
    private Label lbDateNowModifier;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try
        {
            maCnx = new ConnexionBDD();
            String dateNow = LocalDate.now().toString();
            lbDateNowModifier.setText(dateNow);
        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        serviceMatieres = new ServiceMatieres();
        try
        {
            lesMatieres = serviceMatieres.GetAllMatiereObj();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        for (Matiere uneMatiere : lesMatieres)
        {
            cboMatiereModif.getItems().add(uneMatiere.getDesignation());

        }
        cboMatiereModif.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (newValue != null)
                {
                    String matiereSelectionne = newValue;
                    try
                    {
                        updateSousMatieres(matiereSelectionne);
                    }
                    catch (SQLException e)
                    {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    private void updateSousMatieres(String matiereSelectionne) throws SQLException {
        // Insérez le code pour mettre à jour le menuSousMatiere en fonction de la matière sélectionnée.
        // Vous pouvez utiliser la variable matiereSelectionne pour obtenir la matière sélectionnée.
        ObservableList<String> sousMatieres = serviceMatieres.GetAllSousMatiere(matiereSelectionne);
        mbSousMatiereModif.getItems().clear();

        for (String sousMatiere : sousMatieres)
        {
            CustomMenuItem customMenuItem = new CustomMenuItem(new CheckBox(sousMatiere));
            customMenuItem.setHideOnClick(false);
            mbSousMatiereModif.getItems().add(customMenuItem);
        }
    }

    public void initDatas(Demande demandeSelectionnee) {
        laDemande = demandeSelectionnee;

        datepFinModif.setValue(LocalDate.parse(demandeSelectionnee.getDateFin()));
        cboMatiereModif.setValue(demandeSelectionnee.getDesignation());

        String[] sousMatieresArray = demandeSelectionnee.getSousMatiere().split("#");
        for (MenuItem menuItem : mbSousMatiereModif.getItems())
        {
            if (menuItem instanceof CustomMenuItem)
            {
                CustomMenuItem customMenuItem = (CustomMenuItem) menuItem;
                if (customMenuItem.getContent() instanceof CheckBox)
                {
                    CheckBox checkBox = (CheckBox) customMenuItem.getContent();
                    String checkBoxText = checkBox.getText();
                    boolean isChecked = false;
                    for (String selectedSousMatiere : sousMatieresArray)
                    {
                        if (selectedSousMatiere.trim().equals(checkBoxText))
                        {
                            isChecked = true;
                            break;
                        }
                    }
                    checkBox.setSelected(isChecked);
                }
            }
        }

    }


    // pour afficher les sous matieres
    @javafx.fxml.FXML
    public void cboMatiereModifClicked(Event event) {
        String matiereSelectionne = (String) cboMatiereModif.getValue(); // Obtenir la matière sélectionnée
        if (matiereSelectionne != null)
        {
            try {
                updateSousMatieres(matiereSelectionne);
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    // valider les modif
    @FXML
    public void btnValiderModifClicked(Event event) throws SQLException, IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        String erreurs = "";

        if (datepFinModif.getValue() == null)
        {
            erreurs += "Veuillez sélectionner une date de fin valide (ultérieure à la date de début)\n";
        }

        if (datepFinModif.getValue() != null)
        {
            LocalDate debut = LocalDate.parse(lbDateNowModifier.getText());
            LocalDate fin = datepFinModif.getValue();

            if (debut.isAfter(fin))
            {
                erreurs += "La date de début ne peut pas être après la date de fin\n";
            }
        }

        if (cboMatiereModif.getSelectionModel().isEmpty())
        {
            erreurs += "Veuillez sélectionner une matière\n";
        }

        if (menuController.recupererLesCasesCochees(mbSousMatiereModif).isEmpty())
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
            String nomMatiere = cboMatiereModif.getSelectionModel().getSelectedItem().toString();
            Matiere matiereSelectionnee = null;
            int idMatiere = 0;
            for (Matiere uneMatiere : lesMatieres)
            {
                if (uneMatiere.getDesignation().equals(nomMatiere))
                {
                    matiereSelectionnee = uneMatiere;
                    break;
                }
            }
            if (matiereSelectionnee != null)
            {
                idMatiere = matiereSelectionnee.getIdMatiere();
            }

        }
    }

    // annuler les modif
    @javafx.fxml.FXML
    public void btnAnnulerModifierClicked(Event event)
    {
        Stage stage = (Stage) btnAnnulerModifier.getScene().getWindow();
        stage.close();
    }

    public User initUser(User user)
    {
        this.user = user;
        return user;
    }

}

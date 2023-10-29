package bts.sio.projet;

import bts.sio.projet.Entities.Demande;
import bts.sio.projet.Entities.Matiere;
import bts.sio.projet.Entities.User;
import bts.sio.projet.Services.ServiceMatieres;
import bts.sio.projet.Services.ServicesDemandes;
import bts.sio.projet.Tools.ConnexionBDD;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.TreeMap;


public class ModifierDemandeController implements Initializable
{
    private Demande laDemande;
    @javafx.fxml.FXML
    private DatePicker datepFinModif;
    @javafx.fxml.FXML
    private DatePicker datepDebutModif;
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
    ServicesDemandes servicesDemandes;
    ObservableList<Matiere> lesMatieres = FXCollections.observableArrayList();


    public void initialize(URL url, ResourceBundle resourceBundle) {
        try
        {
            maCnx = new ConnexionBDD();
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

    public void initDatas(Demande demandeSelectionnee, String matiere, String dateDebut, String dateFin, String sousMatiere) {
        laDemande = demandeSelectionnee;
        datepDebutModif.setValue(LocalDate.parse(dateDebut));
        datepFinModif.setValue(LocalDate.parse(dateFin));
        cboMatiereModif.setValue(matiere);

        String[] sousMatieresArray = sousMatiere.split("#");
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
    public void cboMatiereModifClicked(Event event) {
        String matiereSelectionne = (String) cboMatiereModif.getValue(); // Obtenir la matière sélectionnée
        if (matiereSelectionne != null)
        {
            try {
                updateSousMatieres(matiereSelectionne);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // valider les modif
    /*@javafx.fxml.FXML
    public void btnValiderModifClicked(Event event) throws SQLException
    {
        String matiereSelectionne = (String) cboMatiereModif.getValue();
        int idMatiere = Integer.parseInt(matiereSelectionne);

        String dateDebut = datepDebutModif.getValue().toString();
        String dateFin = datepFinModif.getValue().toString();

        MenuButton menu = mbSousMatiereModif;
        String sousMatiere = recupererLesCasesCocheesModif(menu);

        servicesDemandes.modifDemande(user.getId() , idMatiere, dateDebut, dateFin, sousMatiere);
    }*/

    // annuler les modif
    @javafx.fxml.FXML
    public void btnAnnulerModifierClicked(Event event)
    {
    }

    public String recupererLesCasesCocheesModif(MenuButton menu)
    {
        String sousMatiere = "";
        ObservableList<MenuItem> items = menu.getItems();
        for (MenuItem item : items)
        {
            if (item instanceof CustomMenuItem)
            {
                CustomMenuItem customItem = (CustomMenuItem) item;
                CheckBox checkBox = (CheckBox) customItem.getContent();

                if (checkBox.isSelected())
                {
                    String sousMatiereSelectioner = checkBox.getText();
                    sousMatiere += "#" + sousMatiereSelectioner;
                }
            }
        }
        return sousMatiere;
    }

    public void initUser(User user)
    {
        this.user = user;
    }
}

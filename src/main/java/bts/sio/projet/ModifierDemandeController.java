package bts.sio.projet;

import bts.sio.projet.Entities.Demande;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;


public class ModifierDemandeController
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

    public void initDatas(Demande uneDemande) {
        laDemande = uneDemande;

    }

    // pour afficher les sous matieres
    @javafx.fxml.FXML
    public void cboMatiereModifClicked(Event event) {
    }

    // valider les modif
    @javafx.fxml.FXML
    public void btnValiderModifClicked(Event event) {
    }

    // annuler les modif
    @javafx.fxml.FXML
    public void btnAnnulerModifierClicked(Event event) {
    }
}

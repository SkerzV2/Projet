package bts.sio.projet;

import bts.sio.projet.Tools.ConnexionBDD;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProjetController implements Initializable {
    ConnexionBDD maCnx;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            maCnx = new ConnexionBDD();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }
    }
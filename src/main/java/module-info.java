module bts.sio.projeet {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    opens bts.sio.projet.Tools;
    opens bts.sio.projet.Entities;
    opens bts.sio.projet.Tools.Services;

    exports bts.sio.projet;
    opens bts.sio.projet;

}
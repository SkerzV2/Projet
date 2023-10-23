module bts.sio.projeet {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    opens bts.sio.projet.Tools;
    opens bts.sio.projet.Entities;
    opens bts.sio.projet.Services;

    opens bts.sio.projet to javafx.fxml;
    exports bts.sio.projet;

}
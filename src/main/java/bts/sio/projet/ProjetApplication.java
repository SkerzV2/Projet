package bts.sio.projet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ProjetApplication extends Application {
    private Stage primaryStage; // Conservez une référence à la fenêtre principale

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage; // Initialisez la référence à la fenêtre principale
        FXMLLoader fxmlLoader = new FXMLLoader(ProjetApplication.class.getResource("projet-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Projet!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

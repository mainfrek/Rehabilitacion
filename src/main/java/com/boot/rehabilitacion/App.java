package com.boot.rehabilitacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.StageStyle;

/**
 * JavaFX App
 */
public class App extends Application {


    @Override
    public void start(Stage stage) throws IOException {
          try {

            Parent root = FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
            Scene scene = new Scene(root);
            stage.initStyle(StageStyle.DECORATED);
            stage.setScene(scene);
           
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

    public static void main(String[] args) {
        launch();
    }

}
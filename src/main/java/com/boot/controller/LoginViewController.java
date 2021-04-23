/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boot.controller;

import com.boot.model.OperationsPaciente;
import com.boot.rehabilitacion.Login;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author develop
 */
public class LoginViewController implements Initializable {

    @FXML
    private PasswordField jtxtPassword;

    @FXML
    private TextField jtxtUsername;

    @FXML
    private MFXButton btnLogin;

    @FXML
    private MFXButton btnCreateAcount;

    @FXML
    private Label btnClave;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void CreateAcount(javafx.event.ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SingupView.fxml"));

            Parent root = loader.load();

            SingupViewController controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

            stage.setOnCloseRequest(e -> controlador.closewindows());
            Stage stage1 = (Stage) this.btnCreateAcount.getScene().getWindow();
            stage1.close();

        } catch (IOException ex) {
            Logger.getLogger(SingupViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Login(ActionEvent event) {
        if (this.jtxtUsername.getText().isEmpty() || this.jtxtPassword.getText().isEmpty()) {
            Alert message = new Alert(Alert.AlertType.ERROR);
            message.setTitle("FISIOSPA");
            message.setContentText("Debe llenar los campos");
            message.setHeaderText(null);
            message.showAndWait();
        } else {
            String us = jtxtUsername.getText();
            String pass = jtxtPassword.getText();
            Login lg = new Login();
            lg.Conectar();
            int log = lg.loginadmin(us, pass);
            int log2 = lg.loginMedico(us, pass);
            int log3= lg.loginEnfermeria(us, pass);
            
            // Login Administrador
            if (log == 1) {

                try {

                    Alert message = new Alert(Alert.AlertType.INFORMATION);
                    message.setTitle("FISIO-SPA");
                    message.setContentText("Bienvenido");
                    message.setHeaderText(null);
                    message.showAndWait();
                    ((Node) (event.getSource())).getScene().getWindow().hide();

                    Stage home = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/views/PrincipalView.fxml"));

                    Scene scene = new Scene(root);
                    home.setScene(scene);
                    home.show();
                } catch (IOException e) {
                    Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, e);
                }

            } else {
                Alert message = new Alert(Alert.AlertType.ERROR);
                message.setTitle("FISIO-SPA");
                message.setContentText("Usuario o contraseña incorrectos");
                message.setHeaderText("¡Acceso denegado!");
                message.showAndWait();
                this.jtxtUsername.setText("");
                this.jtxtPassword.setText("");
            }
            
            //Login Medico
             if (log == 2) {

                try {

                    Alert message = new Alert(Alert.AlertType.INFORMATION);
                    message.setTitle("FISIO-SPA");
                    message.setContentText("Bienvenido");
                    message.setHeaderText(null);
                    message.showAndWait();
                    ((Node) (event.getSource())).getScene().getWindow().hide();

                    Stage home = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/views/PrincipalView.fxml"));

                    Scene scene = new Scene(root);
                    home.setScene(scene);
                    home.show();
                } catch (IOException e) {
                    Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, e);
                }

            } else {
                Alert message = new Alert(Alert.AlertType.ERROR);
                message.setTitle("FISIO-SPA");
                message.setContentText("Usuario o contraseña incorrectos");
                message.setHeaderText("¡Acceso denegado!");
                message.showAndWait();
                this.jtxtUsername.setText("");
                this.jtxtPassword.setText("");
            }
             //Login Enfermeria
              if (log == 3) {

                try {

                    Alert message = new Alert(Alert.AlertType.INFORMATION);
                    message.setTitle("FISIO-SPA");
                    message.setContentText("Bienvenido");
                    message.setHeaderText(null);
                    message.showAndWait();
                    ((Node) (event.getSource())).getScene().getWindow().hide();

                    Stage home = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/views/PrincipalView.fxml"));

                    Scene scene = new Scene(root);
                    home.setScene(scene);
                    home.show();
                } catch (IOException e) {
                    Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, e);
                }

            } else {
                Alert message = new Alert(Alert.AlertType.ERROR);
                message.setTitle("FISIO-SPA");
                message.setContentText("Usuario o contraseña incorrectos");
                message.setHeaderText("¡Acceso denegado!");
                message.showAndWait();
                this.jtxtUsername.setText("");
                this.jtxtPassword.setText("");
            }
            
        }
    }

}

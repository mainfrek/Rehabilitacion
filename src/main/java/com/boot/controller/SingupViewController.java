/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boot.controller;

import com.boot.model.Enfermeria;
import com.boot.model.Medico;
import com.boot.model.OperationsEnfermeria;
import com.boot.model.OperationsMedico;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.LocalDateStringConverter;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.textfield.CustomPasswordField;
import org.kordamp.ikonli.javafx.FontIcon;

/**
 * FXML Controller class
 *
 * @author develop
 */
public class SingupViewController implements Initializable {

    @FXML
    private MFXToggleButton btnAcountType;
    @FXML
    private MFXTextField jtxtNombre;
    @FXML
    private MFXTextField jtxtApellido;
    @FXML
    private MFXTextField jtxtEspecialidad;
    @FXML
    private MFXTextField jtxtTelefono;
    @FXML
    private MFXLegacyComboBox<String> cmbGenero;
    @FXML
    private MFXTextField jtxtCedula;
    @FXML
    private DatePicker jtxtFecha;
    @FXML
    private MFXTextField jtxtCorreo;
    @FXML
    private MFXTextField jtxtDireccion;
    @FXML
    private CustomPasswordField jtxtPassword;
    @FXML
    private MFXButton btnCancel;
    @FXML
    private MFXButton btnRegistrer;
    @FXML
    private Label lblEspecialidad;
    @FXML
    private FontIcon lblRestriction;
    @FXML
    private CustomPasswordField jtxtPasswordConfirm;
    @FXML
    private FontIcon iconConfirm;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MMM-dd");
 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jtxtFecha.setValue(LocalDate.now());
        cmbGenero.getItems().add("Masculino");
        cmbGenero.getItems().add("Femenino");
        cmbGenero.getItems().add("LGBTI");
        cmbGenero.getItems().add("Otro");
        selectType();
        btnRegistrer.setDisable(true);
        jtxtFecha.setConverter(new LocalDateStringConverter(FormatStyle.FULL));

    }

    void closewindows() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/LoginView.fxml"));

            Parent root = loader.load();

            LoginViewController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(SingupViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goRegister(ActionEvent event) {

        if (btnAcountType.selectedProperty().getValue() == true) {
            if (jtxtNombre.getText().isEmpty() || jtxtApellido.getText().isEmpty()
                    || cmbGenero.getValue().isEmpty() || jtxtCorreo.getText().isEmpty() || jtxtDireccion.getText().isEmpty()
                    || jtxtCedula.getText().isEmpty() || jtxtPassword.getText().isEmpty()) {

//                Alert message = new Alert(Alert.AlertType.ERROR);
//                message.setTitle("FISIOSPA");
//                message.setContentText("Debe llenar los campos");
//                message.setHeaderText(null);
//                message.showAndWait();
            } else {
                String categoria = "E";
                String activo = "SI";
                String nombre = jtxtNombre.getText();
                String apellido = jtxtApellido.getText();
                String genero = cmbGenero.getValue();
                String correo = jtxtCorreo.getText();
                String direccion = jtxtDireccion.getText();
                String cedula = jtxtCedula.getText();
                String pass = jtxtPassword.getText();
                String telefono = jtxtTelefono.getText();
                LocalDate fecha_nac = jtxtFecha.getValue();

                Enfermeria enf = new Enfermeria(cedula, nombre, apellido, direccion, telefono, genero, pass, correo, categoria, fecha_nac, activo);
                OperationsEnfermeria opEn = new OperationsEnfermeria();
                opEn.Conectar();
                int insert = opEn.InsertarEnfermeria(enf);
                

                if (insert > 0) {
                    Alert mesaage = new Alert(Alert.AlertType.INFORMATION);
                    mesaage.setTitle("Mensaje");
                    mesaage.setContentText("Usted se a registrado su cuenta en Enfermeria como : " + nombre + " " + apellido + "  ");
                    mesaage.setHeaderText("Buen trabajo");
                    mesaage.showAndWait();
                    // Cerrar ventana
                    closewindows();
                    Stage stage = (Stage) this.btnRegistrer.getScene().getWindow();
                    stage.close();

                } else {
                    Alert mesaage = new Alert(Alert.AlertType.ERROR);
                    mesaage.setTitle("Mensaje");
                    mesaage.setContentText("No se han podido guardar los datos...");
                    mesaage.setHeaderText("Error");
                    mesaage.showAndWait();
                }
            }

        } else {
            if (jtxtNombre.getText().isEmpty() || jtxtApellido.getText().isEmpty() || jtxtEspecialidad.getText().isEmpty()
                    || cmbGenero.getValue().isEmpty() || jtxtCorreo.getText().isEmpty() || jtxtDireccion.getText().isEmpty()
                    || jtxtCedula.getText().isEmpty() || jtxtPassword.getText().isEmpty()) {

                Alert message = new Alert(Alert.AlertType.ERROR);
                message.setTitle("FISIOSPA");
                message.setContentText("Debe llenar los campos");
                message.setHeaderText(null);
                message.showAndWait();
            } else {
                jtxtFecha.setConverter(new LocalDateStringConverter(formatter, null));
                String cedula = jtxtCedula.getText();
                String nombre = jtxtNombre.getText();
                String apellido = jtxtApellido.getText();
                String especialidad = jtxtEspecialidad.getText();
                String direccion = jtxtDireccion.getText();
                String telefono = jtxtTelefono.getText();
                String genero = cmbGenero.getValue();
                String clave = jtxtPassword.getText();
                String correo = jtxtCorreo.getText();
                String categoria = "M";
                String activo = "SI";
                LocalDate fecha_inicio = jtxtFecha.getValue();
                Medico med = new Medico();
                med.setCedula(cedula);
                med.setNombre(nombre);
                med.setApellido(apellido);
                med.setEspecialidad(especialidad);
                med.setDireccion(direccion);
                med.setTelefono(telefono);
                med.setGenero(genero);
                med.setClave(clave);
                med.setCorreo(correo);
                med.setCategoria(categoria);
                med.setActivo(activo);
                med.setFecha_inicio(fecha_inicio);
                OperationsMedico opmed = new OperationsMedico();
                opmed.Conectar();
                int insert2 = opmed.InsertarMedico(med);
                System.out.println(insert2);
                if (insert2 > 0) {
                    Alert mesaage = new Alert(Alert.AlertType.INFORMATION);
                    mesaage.setTitle("Mensaje");
                    mesaage.setContentText("Usted se a registrado como : " + nombre + "");
                    mesaage.setHeaderText("Buen trabajo");
                    mesaage.showAndWait();
                    // Cerrar ventana
                    closewindows();
                    Stage stage = (Stage) this.btnRegistrer.getScene().getWindow();
                    stage.close();

                } else {
                    Alert mesaage = new Alert(Alert.AlertType.ERROR);
                    mesaage.setTitle("Mensaje");
                    mesaage.setContentText("No se han podido guardar los datos...");
                    mesaage.setHeaderText("Error");
                    mesaage.showAndWait();
                }

            }

        }

    }

    @FXML
    private void selectType() {
        if (btnAcountType.selectedProperty().getValue() == true) {
            jtxtEspecialidad.setDisable(true);
            lblRestriction.setVisible(true);
            popOver();

        } else {
            jtxtEspecialidad.setDisable(false);
            lblEspecialidad.setDisable(false);
            lblRestriction.setVisible(false);
        }

    }

    @FXML
    private void popOver() {
        //Build PopOver look and feel
        Label lblName = new Label("Este campo no esta disponible para\n la cuenta de enfermeria");
        lblName.setTextAlignment(TextAlignment.CENTER);
        lblName.setFont(Font.font("System", FontWeight.NORMAL, 12));

        VBox vBox = new VBox(lblName);

        //Create PopOver and add look and feel
        PopOver popOver = new PopOver(vBox);

        lblRestriction.setOnMouseEntered(mouseEvent -> {
            //Show PopOver when mouse enters label
            popOver.show(lblRestriction);
        });

        lblRestriction.setOnMouseExited(mouseEvent -> {
            //Hide PopOver when mouse exits label
            popOver.hide();
        });

    }

    @FXML
    private void getPasswordConfirm(KeyEvent event) {
        String pass = jtxtPassword.getText();
        String passwordConfirm = jtxtPasswordConfirm.getText();
        if (!jtxtPassword.getText().isEmpty()) {
            if (pass.equals(passwordConfirm)) {
                iconConfirm.setFill(Paint.valueOf("#34eb77"));
                jtxtPassword.setStyle("-fx-border-color: #34eb77;");
                jtxtPasswordConfirm.setStyle("-fx-border-color: #34eb77;");
                btnRegistrer.setDisable(false);

            } else {
                iconConfirm.setFill(Paint.valueOf("#ed0000"));
                jtxtPassword.setStyle("-fx-border-color: #ed0000;");
                btnRegistrer.setDisable(true);
            }

        } else {

        }

    }

    @FXML
    private void cancelVentana(ActionEvent event) {
        closewindows();
        // Cerrar ventana
        Stage stage = (Stage) this.btnCancel.getScene().getWindow();
        stage.close();
    }

}

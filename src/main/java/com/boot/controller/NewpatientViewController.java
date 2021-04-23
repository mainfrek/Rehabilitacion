/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boot.controller;

import com.boot.model.OperationsPaciente;
import com.boot.model.Paciente;
import com.boot.model.NuneroHistoriaClinica;
import com.github.sarxos.webcam.Webcam;
import com.panemu.tiwulfx.form.NumberControl;
import com.panemu.tiwulfx.form.TextControl;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FilenameUtils;

/**
 * FXML Controller class
 *
 * @author develop
 */
public class NewpatientViewController implements Initializable {

    @FXML
    private Label lblnumHc;
    @FXML
    private Button btnSave;
    @FXML
    private MFXComboBox<String> cmbEstadoCiv;
    @FXML
    private TextControl jtxtNombre;
    @FXML
    private TextControl JtxtApellidop, jtxtApellidom;
    @FXML
    private TextControl jtxtCorreo, jtxtDireccion, jtxtOcupacion;
    @FXML
    private MFXDatePicker dtpFechanac;
    @FXML
    private MFXComboBox<String> cmbGenero;
    @FXML
    private TextField jtxtEdad, jtxtTelefono, jtxtCedula;
    @FXML
    private NumberControl<? extends Number> jtxtEstatura;
    @FXML
    private NumberControl<? extends Number> jtxtPeso;
    @FXML
    private MFXButton btnCamera;
    @FXML
    private Circle imgProfile;

    private Paciente paciente;
    private ObservableList<Paciente> pacientes;

    public static Webcam webcam; // iniciar camara

    public static boolean isCapture = false; // encender o parar camara

    private String pathFoto = null;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MMM-dd");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getNunHistoria();
        cmbGenero.getItems().add("Masculino");
        cmbGenero.getItems().add("Femenino");
        cmbGenero.getItems().add("LGBTI");
        cmbGenero.getItems().add("Otro");
        cmbEstadoCiv.getItems().add("Soltero");
        cmbEstadoCiv.getItems().add("Casado");
        cmbEstadoCiv.getItems().add("Divorciado");
        cmbEstadoCiv.getItems().add("Viudo");
        cmbEstadoCiv.getItems().add("Unión Libre");
        jtxtTelefono.addEventHandler(KeyEvent.KEY_TYPED, event -> onlyNumber(event));
        jtxtCedula.addEventHandler(KeyEvent.KEY_TYPED, event -> onlyNumber(event));
        jtxtEdad.addEventHandler(KeyEvent.KEY_TYPED, event -> onlyNumber(event));
    }

    public void initAttributtes(ObservableList<Paciente> paciente) {
        this.pacientes = paciente;
    }

    public void getNunHistoria() {
        OperationsPaciente op = new OperationsPaciente();
        op.Conectar();
        String numero = op.getNumHistoria();
        NuneroHistoriaClinica nhm = new NuneroHistoriaClinica();
        int dato = Integer.parseInt(numero);
        numero = nhm.NumHistoria(dato);
        lblnumHc.setText(numero);
        System.out.println(numero);

    }

//        
    @FXML
    private void save(ActionEvent event) {

        if (jtxtNombre.getValue().isEmpty() || jtxtCedula.getText().isEmpty() || jtxtPeso.getText().isEmpty()
                || jtxtCorreo.getValue().isEmpty() || jtxtEdad.getText().isEmpty() || jtxtDireccion.getValue().isEmpty()
                || jtxtTelefono.getText().isEmpty() || cmbGenero.getSelectedValue() == null || pathFoto == null
                || jtxtOcupacion.getValue().isEmpty() || jtxtEstatura.getText().isEmpty() || cmbEstadoCiv.getSelectedValue() == null) {

            Alert message = new Alert(Alert.AlertType.WARNING);
            message.setTitle("FISIO-SPA");
            message.setContentText("Debe llenar todos los campos y agregar una foto al paciente");
            message.setHeaderText("¡Advertencia!");
            message.showAndWait();

        } else {

            dtpFechanac.setDateFormatter(formatter);
            Paciente hc = new Paciente();
            hc.setCedula(jtxtCedula.getText());
            hc.setNombre(jtxtNombre.getValue());
            hc.setApellidop(JtxtApellidop.getValue());
            hc.setApellidom(jtxtApellidom.getValue());
            hc.setEstadocivil(cmbEstadoCiv.getSelectedValue());
            hc.setTelefono(jtxtTelefono.getText());
            hc.setEdad(Integer.parseInt(jtxtEdad.getText()));
            hc.setGenero(cmbGenero.getSelectedValue());
            hc.setDireccion(jtxtDireccion.getValue());
            hc.setFecha_nacimiento(dtpFechanac.getDate());
            hc.setMail(jtxtCorreo.getValue());
            hc.setEstatura(Double.parseDouble(jtxtEstatura.getText()));
            hc.setPeso(Double.parseDouble(jtxtPeso.getText()));
            hc.setOcupacion(jtxtOcupacion.getValue());
            hc.setImagen(pathFoto);

            OperationsPaciente opc = new OperationsPaciente();
            opc.Conectar();
//          Compruebo si la persona existe
            if (opc.InsertarPaciente(hc) > 0 && !pacientes.contains(hc)) {
//                this.paciente = hc;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Informacion");
                alert.setContentText("Se ha añadido correctamente");
                alert.showAndWait();

                // Cerrar ventana
                Stage stage = (Stage) this.btnSave.getScene().getWindow();
                stage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("La persona ya existe");
                alert.showAndWait();
            }
        }
    }

    @FXML
    public void chargePictures() {
        Stage open = new Stage();
        String destino = System.getProperty("user.home") + "/Documentos/imagesPacientes/";

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");
        File recordsDir = new File(System.getProperty("user.home"), "Documentos/imagesPacientes");
        if (!recordsDir.exists()) {
            recordsDir.mkdirs();
        }

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All imagenes", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        // Obtener la imagen seleccionada
        File imgFile = fileChooser.showOpenDialog(open);

        if (imgFile != null) {

            try {
                String ext1 = FilenameUtils.getExtension(imgFile.getCanonicalPath());
                if (ext1.compareTo("jpg") != 0 && ext1.compareTo("png") != 0) {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText("Tipo de archivo no valido");
                    error.setContentText("Seleccione archivos que sean solo imagenes con extencion (.jpg),(.png)");
                    error.showAndWait();
                }

                Image image = new Image("file:" + imgFile.getAbsolutePath());
                imgProfile.setFill(new ImagePattern(image));
//                btnSave.setOnAction(e -> {

                Path from = Paths.get(imgFile.toURI());
                Path to = Paths.get(destino + imgFile.getName());
                CopyOption[] options = new CopyOption[]{
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.COPY_ATTRIBUTES
                };
                try {
                    Files.copy(from, to, options);
                } catch (IOException ex) {
                    Logger.getLogger(NewpatientViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
                pathFoto = "file:" + to.toString();
                System.out.println(pathFoto);
//                });

            } catch (IOException ex) {
                Logger.getLogger(NewpatientViewController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText("Tipo de archivo no valido");
            error.setContentText("Seleccione uan imagen, .");
        }

    }

    public void onlyNumber(KeyEvent keyEvent) {
        try {
            char key = keyEvent.getCharacter().charAt(0);

            if (!Character.isDigit(key)) {
                keyEvent.consume();
            }

        } catch (Exception ex) {
        }
    }

    public Paciente getPaciente() {
        return paciente;
    }

}

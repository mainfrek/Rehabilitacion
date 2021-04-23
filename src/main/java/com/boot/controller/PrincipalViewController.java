/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boot.controller;

import com.boot.model.OperationsPaciente;
import com.boot.model.Paciente;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author develop
 */
public class PrincipalViewController implements Initializable {

    @FXML
    private Label lblHora;
    @FXML
    private MFXTextField txtEdad;
    @FXML
    private MFXTextField txtCedula;
    @FXML
    private MFXTextField txtHistoriaC;
    @FXML
    private MFXTextField txtNombre;
    @FXML
    private TableColumn<Paciente, Integer> clmHC;
    @FXML
    private TableColumn<Paciente, String> clmC;
    @FXML
    private TableColumn<Paciente, String> clmNombre;
    @FXML
    private TableColumn<Paciente, String> clmCedula;
    @FXML
    private TableColumn<Paciente, Integer> clmEdad;
    @FXML
    private TableColumn<Paciente, String> clmFoto;
    @FXML
    private TableColumn<Paciente, String> clmApellido;
    @FXML
    private TableView<Paciente> tblSignos;
    @FXML
    private TextField txtSearch;
    @FXML
    private Circle perfil;
    @FXML
    private DatePicker jtxtFecha;

    ObservableList<Paciente> pacientes;
    ObservableList<Paciente> filtropacientes;
    private int index = -1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        filtropacientes = FXCollections.observableArrayList();

        this.pacientes = cargarTabla();
        getHora(); // obterner hora actual

        // cargar columnas
        this.clmC.setCellValueFactory(new PropertyValueFactory<Paciente, String>("categoria"));
        this.clmHC.setCellValueFactory(new PropertyValueFactory<Paciente, Integer>("numhclinic"));
        this.clmNombre.setCellValueFactory(new PropertyValueFactory<Paciente, String>("nombre"));
        this.clmApellido.setCellValueFactory(new PropertyValueFactory<Paciente, String>("apellidop"));
        this.clmCedula.setCellValueFactory(new PropertyValueFactory<Paciente, String>("cedula"));
        this.clmEdad.setCellValueFactory(new PropertyValueFactory<Paciente, Integer>("edad"));
        this.clmFoto.setCellValueFactory(new PropertyValueFactory<Paciente, String>("imagen"));
        this.clmFoto.setVisible(false);
        //llenar tabla
        this.tblSignos.setItems(pacientes);
        jtxtFecha.setValue(LocalDate.now());

    }

    private ObservableList<Paciente> cargarTabla() {

        ObservableList<Paciente> hclinicas = FXCollections.observableArrayList();
        OperationsPaciente ophc = new OperationsPaciente();
        ophc.Conectar();
        ResultSet rst = ophc.Mostrarpacientes();

        try {

            while (rst.next()) {

                Paciente HClinica = new Paciente();
                HClinica.setNumhclinic(rst.getInt("numhclinic"));
                HClinica.setCategoria(rst.getString("categoria"));
                HClinica.setNombre(rst.getString("nombre"));
                HClinica.setApellidop(rst.getString("apellido_p"));
                HClinica.setCedula(rst.getString("cedula"));
                HClinica.setImagen(rst.getString("foto"));
                HClinica.setEdad(rst.getInt("edad"));
                hclinicas.add(HClinica);

            }

        } catch (SQLException e) {
        }
        return hclinicas;
    }

    @FXML
    private void selecPaciente(MouseEvent event) {

        index = tblSignos.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        txtHistoriaC.setText(clmC.getCellData(index).toString());
        txtNombre.setText(clmNombre.getCellData(index).toString());
        txtCedula.setText(clmCedula.getCellData(index).toString());
        txtEdad.setText(clmEdad.getCellData(index).toString());
//        jtxthClinica.setText(clmHclinica.getCellData(index).toString());
        Image profile2 = new Image(clmFoto.getCellData(index));
        perfil.setFill(new ImagePattern(profile2));
        getHora();

    }

    void filtrar(KeyEvent event) {
        String filtroNombre = this.txtSearch.getText();
        if (filtroNombre.isEmpty()) {
            this.tblSignos.setItems(pacientes);
        } else {

            this.filtropacientes.clear();

            for (Paciente paciente : pacientes) {
                if (paciente.getNombre().toLowerCase().contains(filtroNombre.toLowerCase())) {
                    this.filtropacientes.add(paciente);

                }
            }
            this.tblSignos.setItems(filtropacientes);
        }
    }

    void getHora() {

        LocalDateTime locaDate = LocalDateTime.now();
        String hours = Integer.toString(locaDate.getHour());
        String minutes = Integer.toString(locaDate.getMinute());
        String seconds = Integer.toString(locaDate.getSecond());
        lblHora.setText(hours + ":" + minutes + ":" + seconds);

    }

    @FXML
    private void NewPaciente(ActionEvent event) {
        try {
            // Cargo la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/NewpatientView.fxml"));

            // Cargo la ventana
            Parent root = loader.load();

            // instanciar el controlador y enviar los datos de la tabla al 
            //controlador 2 para verificar si la persona existe
            NewpatientViewController controlador = loader.getController();
            controlador.initAttributtes(pacientes);

            // Creo el Scene
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

//            //cargar la tabla con el nuevo paciente ingresado
            this.pacientes = cargarTabla();
            this.tblSignos.setItems(pacientes);
            this.tblSignos.refresh();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

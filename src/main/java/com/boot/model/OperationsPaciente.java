/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boot.model;

import com.boot.dataaccess.DataAcces;
import static com.boot.dataaccess.DataAcces.con;
import static com.boot.dataaccess.DataAcces.rs;
import static com.boot.dataaccess.DataAcces.stm;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author develop
 */
public class OperationsPaciente extends DataAcces{

    

    public ResultSet Mostrarpacientes() {
        String sql = "SELECT cedula,numhclinic,nombre,apellido_p,apellido_m, edad, foto, categoria FROM paciente ORDER BY nombre ASC";
        try {
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(OperationsPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public int InsertarPaciente(Paciente hc) {
        int band = 0;

        try {
            String sql = "INSERT INTO paciente(cedula,nombre,apellido_p,apellido_m,estadociv,telefono,edad,genero,direccion,"
                    + "fecha_nac,mail,estatura,peso,ocupacion,foto,categoria)"
                    + " values ('" + hc.getCedula()+ "','" + hc.getNombre()+ "','"+hc.getApellidop()+"','" +hc.getApellidom()+"','" +hc.getEstadocivil()+"','" + hc.getTelefono() + ""
                    + "','" + hc.getEdad() + "','" + hc.getGenero()+ "','" + hc.getDireccion() + "','" + hc.getFecha_nacimiento() + ""
                    + "','" + hc.getMail() + "','" + hc.getEstatura() + "','" + hc.getPeso() + "','" + hc.getOcupacion() + "','" + hc.getImagen() + ""
                    + "','P')";
            System.out.println(sql);
            stm = con.createStatement();
            band = stm.executeUpdate(sql);
        } catch (SQLException e) {
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, e);
        }
        return band;
    }
    
    
    public String getNumHistoria(){
    String numero = "";
    String sql= "SELECT max(numhclinic) FROM paciente";
        try {
            stm= con.createStatement();
            rs=stm.executeQuery(sql);
            while (rs.next()) {
                numero = rs.getString(1);
                
                
            }
        } catch (Exception e) {
        }
        System.out.println(numero);
        return numero;
    
    }

}

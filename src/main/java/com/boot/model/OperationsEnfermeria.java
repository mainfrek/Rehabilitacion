/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boot.model;

import com.boot.dataaccess.DataAcces;
import static com.boot.dataaccess.DataAcces.con;
import static com.boot.dataaccess.DataAcces.stm;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author develop
 */
public class OperationsEnfermeria extends DataAcces {

   

    public int InsertarEnfermeria(Enfermeria em) {
        int band = 0;

        try {
            String sql = "INSERT INTO public.enfermeria\n"
                    + "(cedula, nombre, apellido, direccion, telefono, genero, clave, correo, categoria, fecha_inicio, activo)"
                    + " values ('" + em.getCedula() + "','" + em.getNombre() + "','" + em.getApellido() + "','" + em.getDireccion() + "','"+ em.getTelefono() + ""
                    + "','" + em.getGenero() + "','" + em.getClave()+ "','" + em.getCorreo()+ "','" + em.getCategoria()+ "','" + em.getFecha_inicio()+ "','" + em.getActivo()+"');";
            
            stm = con.createStatement();
            band = stm.executeUpdate(sql);
        } catch (SQLException e) {
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, e);
        }
        System.out.println(band);
        return band;
    }
    
    public ResultSet MostrarMedico(){
        
        try {
            String sql = "SELECT * FROM enfermeria ORDER BY nombre ASC";
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(OperationsEnfermeria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}

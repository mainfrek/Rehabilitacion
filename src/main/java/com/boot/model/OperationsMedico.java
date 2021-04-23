/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boot.model;

import com.boot.dataaccess.DataAcces;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author develop
 */
public class OperationsMedico extends DataAcces {

    public int InsertarMedico(Medico m) {
        int flag = 0;
        try {  
            
            String sql = "INSERT INTO public.medico\n"
                    + "(cedula, nombre, apellido, especialidad, direccion, telefono, genero, clave, correo, categoria, activo, fecha_inicio)\n"
                    + "VALUES('"+m.getCedula()+"', '"+m.getNombre()+"', '"+m.getApellido()+"', '"+m.getEspecialidad()+"', '"+m.getDireccion()+"', "
                    + "'"+m.getTelefono()+"', '"+m.getGenero()+"', '"+m.getClave()+"', '"+m.getCorreo()+"', '"+m.getCategoria()+"'::bpchar, 'SI'::character varying, '"+m.getFecha_inicio()+"');";
            
            stm = con.createStatement();
            flag= stm.executeUpdate(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(OperationsMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    
    
    public  ResultSet MostrarMedico(){
    
        try {
            String sql = "SELECT * FROM medico ORDER BY nombre ASC";
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(OperationsMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}

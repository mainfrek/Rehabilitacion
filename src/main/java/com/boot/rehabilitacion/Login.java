/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boot.rehabilitacion;

import com.boot.dataaccess.DataAcces;
import static com.boot.dataaccess.DataAcces.con;
import static com.boot.dataaccess.DataAcces.rs;
import static com.boot.dataaccess.DataAcces.stm;
import com.boot.model.OperationsPaciente;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author develop
 */
public class Login extends DataAcces {

    public int loginadmin(String us, String pass) {

        int resultado = 0;

        try {
            String sql = "SELECT cedula, clave\n"
                    + "  FROM public.administrador WHERE nombre='" + us + "' AND clave='" + pass + "'";
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            if (rs.next()) {
                resultado = 1;
            }

            return resultado;
        } catch (SQLException ex) {
            Logger.getLogger(OperationsPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public int loginMedico(String us, String pass) {

        int resultado = 0;

        try {
            String sql = "SELECT cedula, clave\n"
                    + "  FROM public.medico WHERE nombre='" + us + "' AND clave='" + pass + "'";
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            if (rs.next()) {
                resultado = 2;
            }

            return resultado;
        } catch (SQLException ex) {
            Logger.getLogger(OperationsPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public int loginEnfermeria(String us, String pass) {

        int resultado = 0;

        try {
            String sql = "SELECT cedula, clave\n"
                    + "  FROM public.enfermeria WHERE nombre='" + us + "' AND clave='" + pass + "'";
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            if (rs.next()) {
                resultado = 3;
            }

            return resultado;
        } catch (SQLException ex) {
            Logger.getLogger(OperationsPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

}

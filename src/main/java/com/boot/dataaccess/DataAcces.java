/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boot.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author develop
 */
public class DataAcces {
     public static Connection con=null;
    public static  Statement stm=null;
    public static  ResultSet rs=null;

    public void Conectar() {
        try{
            String cadena="jdbc:postgresql://localhost:5432/hospital";
            con=DriverManager.getConnection(cadena,"postgres","03754");
            Class.forName("org.postgresql.Driver"); 
        }catch(ClassNotFoundException | SQLException e){
            Logger.getLogger(DataAcces.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    
    public void Desconectar(){
        try{
            con.close();
            stm.close();
            rs.close();
        }catch(SQLException e){
            Logger.getLogger(DataAcces.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    
}

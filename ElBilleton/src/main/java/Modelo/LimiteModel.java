/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Conexion.Conexion;
import Objeto.Limite;
import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author potz
 */
public class LimiteModel {
    public static final String BUSCAR_USUARIO = "Select * FROM " + Limite.LIMITE_DB_NAME;
    private final String EDITAR_LIMITE = "UPDATE " + Limite.LIMITE_DB_NAME + " SET " + Limite.LIMITE_REPORTE2_DB_NAME + "=?,"
            + Limite.LIMITE_REPORTE3_DB_NAME  + " =?";
    
    
    public Limite obtenerLimite() throws SQLException, UnsupportedEncodingException {
        PreparedStatement preSt = Conexion.getConnection().prepareStatement(BUSCAR_USUARIO);
        ResultSet result = preSt.executeQuery();

        Limite limite = null;

        while (result.next()) {
            limite = new Limite(
                    result.getDouble(limite.LIMITE_REPORTE2_DB_NAME),
                    result.getDouble(limite.LIMITE_REPORTE3_DB_NAME)
            );
        }
         
        return limite;
    }
    
    
    public long modificarLimite(Limite limite) throws SQLException, UnsupportedEncodingException {
        try{
             PreparedStatement preSt = Conexion.getConnection().prepareStatement(EDITAR_LIMITE, Statement.RETURN_GENERATED_KEYS);
        
        
        preSt.setDouble(1, limite.getLimiteReporte2());
        preSt.setDouble(2, limite.getLimiteReporte3());
        
        
       
        
        preSt.executeUpdate();

        }catch(SQLException e){
            //JOptionPane.showMessageDialog(null, e);
        }
       
        return -1;
    }
    
}

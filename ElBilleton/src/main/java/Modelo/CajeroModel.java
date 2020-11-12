/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Conexion.Conexion;
import Objeto.Cajero;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author potz
 */
public class CajeroModel {
    public static final String BUSCAR_USUARIO = "Select * FROM "+Cajero.CAJERO_DB_NAME;
    
    /**
     * Verifiva si existen las credenciales y si son correctas en el usuario
     *
     * @param id
     * @param pass
     * @return
     * @throws SQLException
     */
    public Cajero loginValidation(int id, String pass) throws SQLException {
        Cajero cajero = obtenerCliente(id);
        if (cajero != null && cajero.getPassword().equals(pass)) {
            return cajero;
            
        }
        return null;
    }
    /**
     * Realizamos una busqueda en base al id del usuario. De no existir la nota
     * nos devuelve un valor null.
     *
     * @param idUsuario
     * @return
     * @throws SQLException
     */
    public Cajero obtenerCliente(int idUsuario) throws SQLException {
        PreparedStatement preSt = Conexion.getConnection().prepareStatement(BUSCAR_USUARIO);
        ResultSet result = preSt.executeQuery();

        Cajero cajero = null;

        while (result.next()) {
            cajero = new Cajero(
                    result.getInt(cajero.CODIGO_DB_NAME),
                    result.getString(cajero.NOMBRE_DB_NAME),
                    result.getString(cajero.TURNO_DB_NAME),
                    result.getInt(cajero.DPI_DB_NAME), 
                     result.getString(cajero.DIRECCION_DB_NAME),
                    result.getString(cajero.SEXO_DB_NAME),
                    result.getString(cajero.PASSWORD_DB_NAME)
                   
                    
            );
        }
        return cajero;
    }
}

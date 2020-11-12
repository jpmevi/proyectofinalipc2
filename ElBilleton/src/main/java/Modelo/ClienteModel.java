/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Conexion.Conexion;
import Encriptar.Encriptar;
import Objeto.Cliente;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 *
 * @author potz
 */
public class ClienteModel {
    public static final String BUSCAR_USUARIO = "Select * FROM "+Cliente.CLIENTE_DB_NAME;
    
    /**
     * Verifiva si existen las credenciales y si son correctas en el usuario
     *
     * @param id
     * @param pass
     * @return
     * @throws SQLException
     */
    public Cliente loginValidation(int id, String pass) throws SQLException {
        Cliente cliente = obtenerCliente(id);
        if (cliente != null && cliente.getPassword().equals(pass)) {
            return cliente;
            
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
    public Cliente obtenerCliente(int idUsuario) throws SQLException {
        PreparedStatement preSt = Conexion.getConnection().prepareStatement(BUSCAR_USUARIO);
        ResultSet result = preSt.executeQuery();

        Cliente cliente = null;

        while (result.next()) {
            cliente = new Cliente(
                    result.getInt(cliente.CLIENTE_CODE_DB_NAME),
                    result.getString(cliente.NOMBRE_DB_NAME),
                    result.getInt(cliente.DPI_DB_NAME),
                    result.getString(cliente.SEXO_DB_NAME),
                    result.getString(cliente.PASSWORD_DB_NAME),
                    result.getString(cliente.DIRECCION_DB_NAME),
                    result.getDate(cliente.FECHA_DB_NAME),
                    result.getBinaryStream(cliente.PDF_DB_NAME)             
            );
        }
        return cliente;
    }

}

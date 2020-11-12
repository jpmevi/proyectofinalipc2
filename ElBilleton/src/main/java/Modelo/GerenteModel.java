/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Conexion.Conexion;
import Objeto.Gerente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author potz
 */
public class GerenteModel {
    public static final String BUSCAR_USUARIO = "Select * FROM "+Gerente.GERENTE_DB_NAME;
    
    /**
     * Verifiva si existen las credenciales y si son correctas en el usuario
     *
     * @param id
     * @param pass
     * @return
     * @throws SQLException
     */
    public Gerente loginValidation(int id, String pass) throws SQLException {
        Gerente cliente = obtenerCliente(id);
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
    public Gerente obtenerCliente(int idUsuario) throws SQLException {
        PreparedStatement preSt = Conexion.getConnection().prepareStatement(BUSCAR_USUARIO);
        ResultSet result = preSt.executeQuery();

        Gerente gerente = null;

        while (result.next()) {
            gerente = new Gerente(
                    result.getInt(gerente.CODIGO_DB_NAME),
                    result.getString(gerente.NOMBRE_DB_NAME),
                    result.getString(gerente.TURNO_DB_NAME),
                    result.getInt(gerente.DPI_DB_NAME), 
                     result.getString(gerente.DIRECCION_DB_NAME),
                    result.getString(gerente.SEXO_DB_NAME),
                    result.getString(gerente.PASSWORD_DB_NAME)
                   
                    
            );
        }
        return gerente;
    }
}

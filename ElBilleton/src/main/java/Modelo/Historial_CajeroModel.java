/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Conexion.Conexion;
import Encriptar.Encriptar;
import Objeto.Cajero;
import Objeto.Historial_Cajero;
import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author potz
 */
public class Historial_CajeroModel {
   
    
    public static final String BUSCAR_USUARIO = "Select * FROM " + Historial_Cajero.HISTORIAL_CAJERO_DB_NAME;
    private final String CREAR_USUARIO = "INSERT INTO " + Historial_Cajero.HISTORIAL_CAJERO_DB_NAME+ " (" + Cajero.NOMBRE_DB_NAME + "," + Cajero.TURNO_DB_NAME + "," + Cajero.DPI_DB_NAME + "," + Cajero.DIRECCION_DB_NAME + "," + Cajero.SEXO_DB_NAME + "," + Cajero.PASSWORD_DB_NAME+ "," + Historial_Cajero.CAJERO_CODIGO_DB_NAME + ") VALUES (?,?,?,?,?,?,?)";
    

    /**
     * Agregamos una nuevo usuario. Al completar la insercion devuelve el ID
     * autogenerado del usuario. De no existir nos devolvera <code>-1</code>.
     *
     * @param usuario
     * @return
     * @throws SQLException
     */
    public long agregarCajero(Cajero cajero) throws SQLException {
        try {
            PreparedStatement preSt = Conexion.getConnection().prepareStatement(CREAR_USUARIO, Statement.RETURN_GENERATED_KEYS);

        preSt.setString(1, cajero.getNombre());
        preSt.setString(2, cajero.getTurno());
        preSt.setString(3, cajero.getDPI());
        preSt.setString(4, cajero.getDireccion());
        preSt.setString(5, cajero.getSexo());
        preSt.setString(6, cajero.getPassword());
         preSt.setLong(7, cajero.getCodigo());

        preSt.executeUpdate();

        ResultSet result = preSt.getGeneratedKeys();
        if (result.first()) {
            return result.getLong(1);
        }
        } catch (SQLException e) {
        }
        

        return -1;
    }
    
     public long agregarCajeroSinCodigo(Cajero cajero, Long codigo) throws SQLException {
        try {
            PreparedStatement preSt = Conexion.getConnection().prepareStatement(CREAR_USUARIO, Statement.RETURN_GENERATED_KEYS);

        preSt.setString(1, cajero.getNombre());
        preSt.setString(2, cajero.getTurno());
        preSt.setString(3, cajero.getDPI());
        preSt.setString(4, cajero.getDireccion());
        preSt.setString(5, cajero.getSexo());
        preSt.setString(6, cajero.getPassword());
         preSt.setLong(7, codigo);

        preSt.executeUpdate();

        ResultSet result = preSt.getGeneratedKeys();
        if (result.first()) {
            return result.getLong(1);
        }
        } catch (SQLException e) {
        }
        

        return -1;
    }
     
     
      public ArrayList obtenerCajeros(String idUsuario) throws SQLException, UnsupportedEncodingException {
       
        PreparedStatement preSt = Conexion.getConnection().prepareStatement(BUSCAR_USUARIO+" WHERE codigo LIKE '%" + idUsuario + "%'");
        ResultSet result = preSt.executeQuery();
        ArrayList listaGerentes = new ArrayList();
        Historial_Cajero gerente = null;
        
        while (result.next()) {
            gerente = new Historial_Cajero(
                    result.getLong(gerente.CODIGO_DB_NAME),
                    result.getString(gerente.NOMBRE_DB_NAME),
                    result.getString(gerente.TURNO_DB_NAME),
                    result.getString(gerente.DPI_DB_NAME),
                    result.getString(gerente.DIRECCION_DB_NAME),
                    result.getString(gerente.SEXO_DB_NAME),
                    result.getString(gerente.PASSWORD_DB_NAME),
                    result.getLong(gerente.CAJERO_CODIGO_DB_NAME)
                    
            );
            gerente.setPassword(Encriptar.desencriptar(gerente.getPassword()));
            listaGerentes.add(gerente);
        }
        return listaGerentes;


    }
}

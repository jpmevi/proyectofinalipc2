/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Conexion.Conexion;
import Encriptar.Encriptar;
import Objeto.Gerente;
import Objeto.HIstorial_Gerente;
import Objeto.*;
import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author potz
 */
public class Historial_GerenteModel {

    public static final String BUSCAR_USUARIO = "Select * FROM " + HIstorial_Gerente.HISTORIAL_GERENTE_DB_NAME;
    private final String CREAR_USUARIO = "INSERT INTO " + HIstorial_Gerente.HISTORIAL_GERENTE_DB_NAME + " (" + Gerente.NOMBRE_DB_NAME + "," + Gerente.TURNO_DB_NAME + "," + Gerente.DPI_DB_NAME + "," + Gerente.DIRECCION_DB_NAME + "," + Gerente.SEXO_DB_NAME + "," + Gerente.PASSWORD_DB_NAME + "," + HIstorial_Gerente.GERENTE_CODIGO_DB_NAME + ") VALUES (?,?,?,?,?,?,?)";

    /**
     * Agregamos una nuevo usuario. Al completar la insercion devuelve el ID
     * autogenerado del usuario. De no existir nos devolvera <code>-1</code>.
     *
     * @param usuario
     * @return
     * @throws SQLException
     */
    public long agregarHistorialGerente(Gerente cajero) throws SQLException {
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

    public long agregarHistorialGerenteSinCodigo(Gerente cajero,Long codigo) throws SQLException {
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
        HIstorial_Gerente gerente = null;
        
        while (result.next()) {
            gerente = new HIstorial_Gerente(
                    result.getLong(gerente.CODIGO_DB_NAME),
                    result.getString(gerente.NOMBRE_DB_NAME),
                    result.getString(gerente.TURNO_DB_NAME),
                    result.getString(gerente.DPI_DB_NAME),
                    result.getString(gerente.DIRECCION_DB_NAME),
                    result.getString(gerente.SEXO_DB_NAME),
                    result.getString(gerente.PASSWORD_DB_NAME),
                    result.getLong(gerente.GERENTE_CODIGO_DB_NAME)
                    
            );
            gerente.setPassword(Encriptar.desencriptar(gerente.getPassword()));
            listaGerentes.add(gerente);
        }
        return listaGerentes;


    }
}

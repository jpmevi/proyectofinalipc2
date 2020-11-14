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
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author potz
 */
public class CajeroModel {

    public static final String BUSCAR_USUARIO = "Select * FROM " + Cajero.CAJERO_DB_NAME;
    private final String CREAR_USUARIO = "INSERT INTO " + Cajero.CAJERO_DB_NAME + " (" + Cajero.NOMBRE_DB_NAME + "," + Cajero.TURNO_DB_NAME + "," + Cajero.DPI_DB_NAME + "," + Cajero.DIRECCION_DB_NAME + "," + Cajero.SEXO_DB_NAME + "," + Cajero.PASSWORD_DB_NAME + ") VALUES (?,?,?,?,?,?)";
    private final String CREAR_USUARIO_CODIGO = "INSERT INTO " + Cajero.CAJERO_DB_NAME + " (" + Cajero.CODIGO_DB_NAME+ "," + Cajero.NOMBRE_DB_NAME + "," + Cajero.TURNO_DB_NAME + "," + Cajero.DPI_DB_NAME + "," + Cajero.DIRECCION_DB_NAME + "," + Cajero.SEXO_DB_NAME + "," + Cajero.PASSWORD_DB_NAME + ") VALUES (?,?,?,?,?,?,?)";

    /**
     * Agregamos una nuevo usuario. Al completar la insercion devuelve el ID
     * autogenerado del usuario. De no existir nos devolvera <code>-1</code>.
     *
     * @param usuario
     * @return
     * @throws SQLException
     */
    public long agregarCajero(Cajero cajero) throws SQLException {
        try{
            PreparedStatement preSt = Conexion.getConnection().prepareStatement(CREAR_USUARIO, Statement.RETURN_GENERATED_KEYS);

        preSt.setString(1, cajero.getNombre());
        preSt.setString(2, cajero.getTurno());
        preSt.setString(3, cajero.getDPI());
        preSt.setString(4, cajero.getDireccion());
        preSt.setString(5, cajero.getSexo());
        preSt.setString(6, cajero.getPassword());
        
        preSt.executeUpdate();

        
        ResultSet result = preSt.getGeneratedKeys();
        if (result.first()) {
            return result.getLong(1);
        }
        }catch(SQLException e){
            
        }
        

        return -1;
    }

    public long agregarCajeroCodigo(Cajero cajero) throws SQLException {
        try{
            PreparedStatement preSt = Conexion.getConnection().prepareStatement(CREAR_USUARIO_CODIGO, Statement.RETURN_GENERATED_KEYS);
        preSt.setLong(1, cajero.getCodigo());
        preSt.setString(2, cajero.getNombre());
        preSt.setString(3, cajero.getTurno());
        preSt.setString(4, cajero.getDPI());
        preSt.setString(5, cajero.getDireccion());
        preSt.setString(6, cajero.getSexo());
        preSt.setString(7, cajero.getPassword());
        preSt.executeUpdate();
        Historial_CajeroModel hist = new Historial_CajeroModel();
        hist.agregarCajero(cajero);
        ResultSet result = preSt.getGeneratedKeys();
        if (result.first()) {
            return result.getLong(1);
        }
        }catch(SQLException e){
            
        }
        

        return -1;
    }

    /**
     * Verifiva si existen las credenciales y si son correctas en el usuario
     *
     * @param id
     * @param pass
     * @return
     * @throws SQLException
     */
    public Cajero loginValidation(Long id, String pass) throws SQLException {
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
    public Cajero obtenerCliente(Long idUsuario) throws SQLException {
        PreparedStatement preSt = Conexion.getConnection().prepareStatement(BUSCAR_USUARIO+" WHERE codigo='"+idUsuario+"'");
        ResultSet result = preSt.executeQuery();

        Cajero cajero = null;

        while (result.next()) {
            cajero = new Cajero(
                    result.getLong(cajero.CODIGO_DB_NAME),
                    result.getString(cajero.NOMBRE_DB_NAME),
                    result.getString(cajero.TURNO_DB_NAME),
                    result.getString(cajero.DPI_DB_NAME),
                    result.getString(cajero.DIRECCION_DB_NAME),
                    result.getString(cajero.SEXO_DB_NAME),
                    result.getString(cajero.PASSWORD_DB_NAME)
            );
        }
        return cajero;
    }
}

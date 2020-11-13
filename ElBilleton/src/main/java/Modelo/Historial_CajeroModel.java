/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Conexion.Conexion;
import Objeto.Cajero;
import Objeto.Historial_Cajero;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author potz
 */
public class Historial_CajeroModel {
   
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

        return -1;
    }
}

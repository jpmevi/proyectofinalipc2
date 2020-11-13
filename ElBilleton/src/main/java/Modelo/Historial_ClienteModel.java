/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Conexion.Conexion;
import Objeto.Cliente;
import Objeto.Historial_Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author potz
 */
public class Historial_ClienteModel {
    private final String CREAR_USUARIO = "INSERT INTO " + Historial_Cliente.HISTORIAL_CLIENTE_DB_NAME + " (" + Cliente.NOMBRE_DB_NAME  + "," + Cliente.DPI_DB_NAME + "," + Cliente.FECHA_DB_NAME + "," + Cliente.DIRECCION_DB_NAME + "," + Cliente.SEXO_DB_NAME+ "," + Cliente.PDF_DB_NAME+ "," + Cliente.PASSWORD_DB_NAME+ Historial_Cliente.HIST_CLIENTE_CODE_DB_NAME + ") VALUES (?,?,?,?,?,?,?,?)";
    
    /**
     * Agregamos una nuevo usuario. Al completar la insercion devuelve el ID
     * autogenerado del usuario. De no existir nos devolvera <code>-1</code>.
     *
     * @param usuario
     * @return
     * @throws SQLException
     */
    public long agregarHistorialCliente(Cliente cajero) throws SQLException {
        PreparedStatement preSt = Conexion.getConnection().prepareStatement(CREAR_USUARIO, Statement.RETURN_GENERATED_KEYS);

        preSt.setString(1, cajero.getNombre());
        preSt.setString(2, cajero.getDpi());
        preSt.setDate(3, cajero.getFechaNacimiento());
        preSt.setString(4, cajero.getDireccion());
        preSt.setString(5, cajero.getSexo());
        preSt.setBlob(6, cajero.getPdfdpi());
        preSt.setString(7, cajero.getPassword());
        preSt.setLong(8, cajero.getCodigo());

        preSt.executeUpdate();

        ResultSet result = preSt.getGeneratedKeys();
        if (result.first()) {
            return result.getLong(1);
        }

        return -1;
    }
}

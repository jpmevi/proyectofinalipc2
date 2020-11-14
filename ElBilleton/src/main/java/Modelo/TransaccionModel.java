/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Conexion.Conexion;
import Objeto.Transaccion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author potz
 */
public class TransaccionModel {

    private final String CREAR_TRANSACCION_SIN_CODIGO = "INSERT INTO " + Transaccion.TRANSACCION_DB_NAME + " (" + Transaccion.FECHA_DB_NAME + "," + Transaccion.HORA_DB_NAME + "," + Transaccion.TIPO_DB_NAME + "," + Transaccion.MONTO_DB_NAME + "," + Transaccion.CUENTA_CODIGO_DB_NAME + "," + Transaccion.CAJERO_CODIGO_DB_NAME + ") VALUES (?,?,?,?,?,?)";
    private final String CREAR_TRANSACCION_CON_CODIGO = "INSERT INTO " + Transaccion.TRANSACCION_DB_NAME + " (" + Transaccion.CODIGO_DB_NAME + "," + Transaccion.FECHA_DB_NAME + "," + Transaccion.HORA_DB_NAME + "," + Transaccion.TIPO_DB_NAME + "," + Transaccion.MONTO_DB_NAME + "," + Transaccion.CAJERO_CODIGO_DB_NAME + "," + Transaccion.CUENTA_CODIGO_DB_NAME + ") VALUES (?,?,?,?,?,?,?)";

    /**
     * Agregamos una nueva transaccion desde la carga de archivos, al completar
     * la insercion devuelve el codigo autogenerado.
     *
     * @param transaccion
     * @return
     * @throws SQLException
     */
    public long agregartransaccionArchivo(Transaccion transaccion) throws SQLException {
        try {
             PreparedStatement preSt = Conexion.getConnection().prepareStatement(CREAR_TRANSACCION_CON_CODIGO, Statement.RETURN_GENERATED_KEYS);

        preSt.setLong(1, transaccion.getCodigo());
        preSt.setDate(2, transaccion.getFecha());
        preSt.setTime(3, transaccion.getHora());
        preSt.setString(4, transaccion.getTipo());
        preSt.setDouble(5, transaccion.getMonto());
        preSt.setLong(6, transaccion.getCajero_codigo());
        preSt.setLong(7, transaccion.getCuenta_codigo());

        preSt.executeUpdate();

        ResultSet result = preSt.getGeneratedKeys();
        if (result.first()) {
            return result.getLong(1);
        }
        } catch (SQLException e) {
        }
       

        return -1;
    }

    /**
     * Agregamos una nueva transaccion, al completar la insercion devuelve el
     * codigo autogenerado.
     *
     * @param transaccion
     * @return
     * @throws SQLException
     */
    public long agregartransaccion(Transaccion transaccion) throws SQLException {
        try {
            PreparedStatement preSt = Conexion.getConnection().prepareStatement(CREAR_TRANSACCION_SIN_CODIGO, Statement.RETURN_GENERATED_KEYS);

        preSt.setDate(1, transaccion.getFecha());
        preSt.setTime(2, transaccion.getHora());
        preSt.setString(3, transaccion.getTipo());
        preSt.setDouble(4, transaccion.getMonto());
        preSt.setDouble(5, transaccion.getCajero_codigo());
        preSt.setDouble(6, transaccion.getCuenta_codigo());

        preSt.executeUpdate();

        ResultSet result = preSt.getGeneratedKeys();
        if (result.first()) {
            return result.getLong(1);
        }
        } catch (SQLException e) {
        }
        

        return -1;
    }
}

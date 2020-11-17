/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Conexion.Conexion;
import Objeto.Cliente;
import Objeto.Cuenta;
import Objeto.Transaccion;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author potz
 */
public class TransaccionModel {

    private final String CREAR_TRANSACCION_SIN_CODIGO = "INSERT INTO " + Transaccion.TRANSACCION_DB_NAME + " (" + Transaccion.FECHA_DB_NAME + "," + Transaccion.HORA_DB_NAME + "," + Transaccion.TIPO_DB_NAME + "," + Transaccion.MONTO_DB_NAME + "," + Transaccion.CUENTA_CODIGO_DB_NAME + "," + Transaccion.CAJERO_CODIGO_DB_NAME + ") VALUES (?,?,?,?,?,?)";
    private final String CREAR_TRANSACCION_CON_CODIGO = "INSERT INTO " + Transaccion.TRANSACCION_DB_NAME + " (" + Transaccion.CODIGO_DB_NAME + "," + Transaccion.FECHA_DB_NAME + "," + Transaccion.HORA_DB_NAME + "," + Transaccion.TIPO_DB_NAME + "," + Transaccion.MONTO_DB_NAME + "," + Transaccion.CAJERO_CODIGO_DB_NAME + "," + Transaccion.CUENTA_CODIGO_DB_NAME + ") VALUES (?,?,?,?,?,?,?)";
    private final String REPORTE_2 = "SELECT T.*,C.codigo FROM " + Transaccion.TRANSACCION_DB_NAME + " T INNER JOIN " + Cuenta.CUENTA_DB_NAME + " CU ON T.cuenta_codigo=CU.codigo INNER JOIN " + Cliente.CLIENTE_DB_NAME+" C ON C.codigo=CU.cliente_codigo WHERE C.codigo=? && T.monto>?";
    private final String REPORTE_6 = "SELECT T.*,C.codigo FROM " + Transaccion.TRANSACCION_DB_NAME + " T INNER JOIN " + Cuenta.CUENTA_DB_NAME + " CU ON T.cuenta_codigo=CU.codigo INNER JOIN " + Cliente.CLIENTE_DB_NAME+" C ON C.codigo=CU.cliente_codigo WHERE C.codigo=?";
    private final String CREAR_TRANSACCION = "INSERT INTO " + Transaccion.TRANSACCION_DB_NAME + " (" + Transaccion.FECHA_DB_NAME + ","
            + Transaccion.HORA_DB_NAME + "," + Transaccion.TIPO_DB_NAME + "," + Transaccion.MONTO_DB_NAME + "," + Transaccion.CAJERO_CODIGO_DB_NAME + ","
            + Transaccion.CUENTA_CODIGO_DB_NAME + ") VALUES (?,?,?,?,?,?)";
    private final String OBTENER_TRANSACCIONES_CUENTA = "SELECT * FROM "+Transaccion.TRANSACCION_DB_NAME+" WHERE "+Transaccion.CUENTA_CODIGO_DB_NAME+"=?";
    private final String ULTIMAS_15_TRANSACCIONES="SELECT * FROM "+Transaccion.TRANSACCION_DB_NAME+" T INNER JOIN " + Cuenta.CUENTA_DB_NAME + " C ON C.codigo=T.cuenta_codigo WHERE C.codigo=? && T.fecha >= date_sub(CURDATE(), INTERVAL 1 YEAR) ORDER BY T.monto DESC LIMIT 15  ";
    private final String REPORTE3_CLIENTE = "SELECT * FROM " + Transaccion.TRANSACCION_DB_NAME + " WHERE "+Transaccion.CUENTA_CODIGO_DB_NAME+"=? && "+Transaccion.FECHA_DB_NAME+" BETWEEN ? AND CURDATE()";
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
    public long agregartransaccion2(Transaccion transaccion) throws SQLException {
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

        } catch (SQLException e) {

        }
        

        return -1;
    }
    
    
    public ArrayList obtenerTransaccionesReporte2(String cliente, Double monto) throws SQLException, UnsupportedEncodingException {
        PreparedStatement preSt = Conexion.getConnection().prepareStatement(REPORTE_2 );
         preSt.setString(1, cliente);
         preSt.setDouble(2, monto);
        ResultSet result = preSt.executeQuery();
        ArrayList listaclientes = new ArrayList();
        Transaccion trans = null;
        while (result.next()) {
            trans= new Transaccion(
                    result.getLong(trans.CODIGO_DB_NAME),
                    result.getDate(trans.FECHA_DB_NAME),
                    result.getTime(trans.HORA_DB_NAME),
                    result.getString(trans.TIPO_DB_NAME),
                    result.getDouble(trans.MONTO_DB_NAME),
                    result.getLong(trans.CAJERO_CODIGO_DB_NAME),
                    result.getLong(trans.CUENTA_CODIGO_DB_NAME)                   
            );
            listaclientes.add(trans);
        }
        return listaclientes;
    }
     public ArrayList obtenerTransaccionesReporte3Cliente(Date fecha1, Long cuenta) throws SQLException, UnsupportedEncodingException {
        PreparedStatement preSt = Conexion.getConnection().prepareStatement(REPORTE3_CLIENTE );
         preSt.setLong(1, cuenta);
         preSt.setDate(2, fecha1);
        ResultSet result = preSt.executeQuery();
        ArrayList listaclientes = new ArrayList();
        Transaccion trans = null;
        while (result.next()) {
            trans= new Transaccion(
                    result.getLong(trans.CODIGO_DB_NAME),
                    result.getDate(trans.FECHA_DB_NAME),
                    result.getTime(trans.HORA_DB_NAME),
                    result.getString(trans.TIPO_DB_NAME),
                    result.getDouble(trans.MONTO_DB_NAME),
                    result.getLong(trans.CAJERO_CODIGO_DB_NAME),
                    result.getLong(trans.CUENTA_CODIGO_DB_NAME)                   
            );
            listaclientes.add(trans);
        }
        return listaclientes;
    }
    
    public ArrayList obtenerTransacciones(String cliente) throws SQLException, UnsupportedEncodingException {
        PreparedStatement preSt = Conexion.getConnection().prepareStatement(REPORTE_6 );
         preSt.setString(1, cliente);
        ResultSet result = preSt.executeQuery();
        ArrayList listaclientes = new ArrayList();
        Transaccion trans = null;

        while (result.next()) {
            trans= new Transaccion(
                    result.getLong(trans.CODIGO_DB_NAME),
                    result.getDate(trans.FECHA_DB_NAME),
                    result.getTime(trans.HORA_DB_NAME),
                    result.getString(trans.TIPO_DB_NAME),
                    result.getDouble(trans.MONTO_DB_NAME),
                    result.getLong(trans.CAJERO_CODIGO_DB_NAME)  ,     
                    result.getLong(trans.CUENTA_CODIGO_DB_NAME)
                                
            );
            listaclientes.add(trans);
        }
        return listaclientes;
    }
    
    
    /**
     * Agregamos un nuevo Transaccion al completar la insercion devuelve el
     * codigo autogenerado del cajero. De no existir nos devolvera
     * <code>-1</code>.
     *
     * @param transaccion
     * @return
     * @throws SQLException
     */
    public long agregarTransaccion(Transaccion transaccion) throws SQLException {
        try {
            PreparedStatement preSt = Conexion.getConnection().prepareStatement(CREAR_TRANSACCION, Statement.RETURN_GENERATED_KEYS);

            preSt.setDate(1, transaccion.getFecha());
            preSt.setTime(2, transaccion.getHora());
            preSt.setString(3, transaccion.getTipo());
            preSt.setDouble(4, transaccion.getMonto());
            preSt.setLong(5, transaccion.getCuenta_codigo());
            preSt.setLong(6, transaccion.getCajero_codigo());

            preSt.executeUpdate();

            ResultSet result = preSt.getGeneratedKeys();
            if (result.first()) {
                return result.getLong(1);
            }
            return -1;
        } catch (Exception e) {
            return -1;
        }

    }
    
    
    
    public ArrayList<Transaccion> obtenerTransaccionesCuenta(long cuenta_codigo) {
        try {
            PreparedStatement preSt = Conexion.getConnection().prepareStatement(OBTENER_TRANSACCIONES_CUENTA);
            preSt.setLong(1, cuenta_codigo);

            ArrayList<Transaccion> listaTransacciones = new ArrayList<>();
            Transaccion transaccion = null;

            ResultSet result = preSt.executeQuery();

            while (result.next()) {
                transaccion = new Transaccion(
                        result.getLong(Transaccion.CODIGO_DB_NAME),
                        result.getDate(Transaccion.FECHA_DB_NAME),
                        result.getTime(Transaccion.HORA_DB_NAME),
                        result.getString(Transaccion.TIPO_DB_NAME),
                        result.getDouble(Transaccion.MONTO_DB_NAME),
                          result.getLong(Transaccion.CAJERO_CODIGO_DB_NAME),
                        result.getLong(Transaccion.CUENTA_CODIGO_DB_NAME)
                );
                listaTransacciones.add(transaccion);
            }
            return listaTransacciones;

        } catch (SQLException e) {
            System.out.println("Error al obtener las transacciones para estado de cuenta" + e);
            return null;
        }
    }
    
    
     public ArrayList obtenerUltimas15Transacciones(Long cuenta) throws SQLException, UnsupportedEncodingException {
        PreparedStatement preSt = Conexion.getConnection().prepareStatement(ULTIMAS_15_TRANSACCIONES);
         preSt.setDouble(1, cuenta);
        ResultSet result = preSt.executeQuery();
        ArrayList listaclientes = new ArrayList();
        Transaccion trans = null;
        while (result.next()) {
            trans= new Transaccion(
                    result.getLong(trans.CODIGO_DB_NAME),
                    result.getDate(trans.FECHA_DB_NAME),
                    result.getTime(trans.HORA_DB_NAME),
                    result.getString(trans.TIPO_DB_NAME),
                    result.getDouble(trans.MONTO_DB_NAME),
                    result.getLong(trans.CAJERO_CODIGO_DB_NAME),
                    result.getLong(trans.CUENTA_CODIGO_DB_NAME)                   
            );
            listaclientes.add(trans);
        }
        return listaclientes;
    }
}

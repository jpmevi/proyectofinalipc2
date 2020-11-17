/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Conexion.Conexion;
import Objeto.Asociacion;
import Objeto.Cliente;
import Objeto.Cuenta;
import Objeto.Solicitud;
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
public class CuentaModel {

    private final String CREAR_CUENTA_SIN_CODIGO = "INSERT INTO " + Cuenta.CUENTA_DB_NAME + " (" + Cuenta.FECHA_CREACION_DB_NAME + "," + Cuenta.MONTO_DB_NAME + "," + Cuenta.CLIENTE_CODIGO_DB_NAME + ") VALUES (?,?,?)";
    private final String CREAR_CUENTA_CON_CODIGO = "INSERT INTO " + Cuenta.CUENTA_DB_NAME + " (" + Cuenta.CODIGO_DB_NAME + "," + Cuenta.FECHA_CREACION_DB_NAME + "," + Cuenta.MONTO_DB_NAME + "," + Cuenta.CLIENTE_CODIGO_DB_NAME + ") VALUES (?,?,?,?)";
    public static final String BUSCAR_USUARIO = "Select * FROM " + Cuenta.CUENTA_DB_NAME + " WHERE CUENTA.cliente_codigo=? && CUENTA.codigo LIKE ?";
        public static final String BUSCAR_USUARIONO = "Select * FROM " + Cuenta.CUENTA_DB_NAME + " WHERE CUENTA.cliente_codigo=?";

     public static final String BUSCAR_CUENTAS_CLIENTE = "Select * FROM " + Cuenta.CUENTA_DB_NAME + " WHERE CUENTA.cliente_codigo=? && CUENTA.codigo!=?";
    public static final String BUSCAR_CUENTA = "Select * FROM " + Cuenta.CUENTA_DB_NAME + " WHERE CUENTA.codigo=? && CUENTA.cliente_codigo!=?";
    public static final String BUSCAR_CUENTA2 = "Select * FROM " + Cuenta.CUENTA_DB_NAME + " WHERE CUENTA.codigo=? && CUENTA.cliente_codigo=?";
    public static final String BUSCAR_CUENTAS_ASOCIADAS = "SELECT C.* FROM " + Cuenta.CUENTA_DB_NAME + " C INNER JOIN " + Solicitud.SOLICITUD_DB_NAME + " S ON (S.cuenta_codigoenvio=C.codigo||S.cueanta_codigorecibe=C.codigo) INNER JOIN " + Asociacion.ASOCIACION_DB_NAME + " A ON A.solicitud_codigo=S.codigo WHERE S.estado='Aceptada' && C.codigo!=? && C.cliente_codigo!=? GROUP BY C.codigo";
    private final String EDITAR_MONTO = "UPDATE " + Cuenta.CUENTA_DB_NAME + " SET " + Cuenta.MONTO_DB_NAME + "=? WHERE "+Cuenta.CODIGO_DB_NAME+"=?";
    private final String CUENTA = "Select * FROM " + Cuenta.CUENTA_DB_NAME + " WHERE CUENTA.codigo=?" ;

    /**
     * Agregamos una nueva cuenta desde la carga de archivos, al completar la
     * insercion devuelve el codigo autogenerado.
     *
     * @param cuenta
     * @return
     * @throws SQLException
     */
    public long agregarCuentaArchivo(Cuenta cuenta, Long codigo) throws SQLException {
        try {
            PreparedStatement preSt = Conexion.getConnection().prepareStatement(CREAR_CUENTA_CON_CODIGO, Statement.RETURN_GENERATED_KEYS);

            preSt.setLong(1, cuenta.getCodigo());
            preSt.setDate(2, cuenta.getFechaCreacion());
            preSt.setDouble(3, cuenta.getMonto());
            preSt.setLong(4, codigo);
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
     * Agregamos una nueva cuenta, al completar la insercion devuelve el codigo
     * autogenerado.
     *
     * @param cuenta
     * @return
     * @throws SQLException
     */
    public long agregarCuenta(Cuenta cuenta) throws SQLException {
        try {
            PreparedStatement preSt = Conexion.getConnection().prepareStatement(CREAR_CUENTA_SIN_CODIGO, Statement.RETURN_GENERATED_KEYS);

            preSt.setDate(1, cuenta.getFechaCreacion());
            preSt.setDouble(2, cuenta.getMonto());
            preSt.setLong(3, cuenta.getCliente_codigo());

            preSt.executeUpdate();

            ResultSet result = preSt.getGeneratedKeys();
            if (result.first()) {
                return result.getLong(1);
            }
        } catch (SQLException e) {
        }

        return -1;
    }

    public ArrayList obtenerCuentas(Long idUsuario,String filtro) throws SQLException, UnsupportedEncodingException {
        PreparedStatement preSt = Conexion.getConnection().prepareStatement(BUSCAR_USUARIO);
        preSt.setLong(1, idUsuario);
        preSt.setString(2, "%"+filtro+"%");
        ResultSet result = preSt.executeQuery();
        ArrayList listacuentas = new ArrayList();
        Cuenta cuenta = null;

        while (result.next()) {
            cuenta = new Cuenta(
                    result.getLong(cuenta.CODIGO_DB_NAME),
                    result.getDate(cuenta.FECHA_CREACION_DB_NAME),
                    result.getDouble(cuenta.MONTO_DB_NAME),
                    result.getLong(cuenta.CLIENTE_CODIGO_DB_NAME)
            );
            listacuentas.add(cuenta);
        }
        return listacuentas;
    }
    
   

     public ArrayList obtenerCuentasClientesin(Long idUsuario,Long cuentac) throws SQLException, UnsupportedEncodingException {
        PreparedStatement preSt = Conexion.getConnection().prepareStatement(BUSCAR_CUENTAS_CLIENTE);
        preSt.setLong(1, idUsuario);
        preSt.setLong(2, cuentac);
        ResultSet result = preSt.executeQuery();
        ArrayList listacuentas = new ArrayList();
        Cuenta cuenta = null;

        while (result.next()) {
            cuenta = new Cuenta(
                    result.getLong(cuenta.CODIGO_DB_NAME),
                    result.getDate(cuenta.FECHA_CREACION_DB_NAME),
                    result.getDouble(cuenta.MONTO_DB_NAME),
                    result.getLong(cuenta.CLIENTE_CODIGO_DB_NAME)
            );
            listacuentas.add(cuenta);
        }
        return listacuentas;
    }
    
    public Cuenta buscarCuenta(Long cuenta1, Long cliente) throws SQLException, UnsupportedEncodingException {
        try {
            PreparedStatement preSt = Conexion.getConnection().prepareStatement(BUSCAR_CUENTA);

            preSt.setLong(1, cuenta1);
            preSt.setLong(2, cliente);
            ResultSet result = preSt.executeQuery();
            Cuenta cuenta = null;

            while (result.next()) {
                cuenta = new Cuenta(
                        result.getLong(cuenta.CODIGO_DB_NAME),
                        result.getDate(cuenta.FECHA_CREACION_DB_NAME),
                        result.getDouble(cuenta.MONTO_DB_NAME),
                        result.getLong(cuenta.CLIENTE_CODIGO_DB_NAME)
                );
                return cuenta;
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public Cuenta buscarCuentaCodigo(Long cuenta1) throws SQLException, UnsupportedEncodingException {
        try {
            PreparedStatement preSt = Conexion.getConnection().prepareStatement(CUENTA);
            preSt.setLong(1, cuenta1);
            ResultSet result = preSt.executeQuery();
            Cuenta cuenta = null;

            while (result.next()) {
                cuenta = new Cuenta(
                        result.getLong(cuenta.CODIGO_DB_NAME),
                        result.getDate(cuenta.FECHA_CREACION_DB_NAME),
                        result.getDouble(cuenta.MONTO_DB_NAME),
                        result.getLong(cuenta.CLIENTE_CODIGO_DB_NAME)
                );
                return cuenta;
            }
        } catch (SQLException e) {

        }
        return null;
    }
    
    
    public Cuenta buscarCuenta2(Long cuenta1, Long cliente) throws SQLException, UnsupportedEncodingException {
        try {
            PreparedStatement preSt = Conexion.getConnection().prepareStatement(BUSCAR_CUENTA2);

            preSt.setLong(1, cuenta1);
            preSt.setLong(2, cliente);
            ResultSet result = preSt.executeQuery();
            Cuenta cuenta = null;

            while (result.next()) {
                cuenta = new Cuenta(
                        result.getLong(cuenta.CODIGO_DB_NAME),
                        result.getDate(cuenta.FECHA_CREACION_DB_NAME),
                        result.getDouble(cuenta.MONTO_DB_NAME),
                        result.getLong(cuenta.CLIENTE_CODIGO_DB_NAME)
                );
                return cuenta;
            }
        } catch (SQLException e) {

        }
        return null;
    }
    
    public ArrayList obtenerCuentasAsociadas(Long idUsuario,Long cliente) throws SQLException, UnsupportedEncodingException {
        PreparedStatement preSt = Conexion.getConnection().prepareStatement(BUSCAR_CUENTAS_ASOCIADAS);
        preSt.setLong(1, idUsuario);
        preSt.setLong(2, cliente);
        ResultSet result = preSt.executeQuery();
        ArrayList listacuentas = new ArrayList();
        Cuenta cuenta = null;

        while (result.next()) {
            cuenta = new Cuenta(
                    result.getLong(cuenta.CODIGO_DB_NAME),
                    result.getDate(cuenta.FECHA_CREACION_DB_NAME),
                    result.getDouble(cuenta.MONTO_DB_NAME),
                    result.getLong(cuenta.CLIENTE_CODIGO_DB_NAME)
            );
            listacuentas.add(cuenta);
        }
        return listacuentas;
    }
    
    public long modificarMonto(Cuenta cuenta) throws SQLException, UnsupportedEncodingException {
        try {
            PreparedStatement preSt = Conexion.getConnection().prepareStatement(EDITAR_MONTO, Statement.RETURN_GENERATED_KEYS);

            preSt.setDouble(1, cuenta.getMonto());
            preSt.setLong(2, cuenta.getCodigo());
            preSt.executeUpdate();

        } catch (SQLException e) {
        }

        return -1;
    }
}

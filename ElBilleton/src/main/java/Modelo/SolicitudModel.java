/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Conexion.Conexion;
import Objeto.Cuenta;
import Objeto.Solicitud;
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
public class SolicitudModel {

    private final String CREAR_SOLICITUD = "INSERT INTO " + Solicitud.SOLICITUD_DB_NAME + " (" + Solicitud.FECHA_DB_NAME + ","
            + Solicitud.ESTADO_DB_NAME + "," + Solicitud.CUENTA_ENVIA_CODIGO_DB_NAME + ","
            + Solicitud.CUENTA_RECIBE_CODIGO_DB_NAME + ") VALUES (?,?,?,?)";
    private final String BUSCAR_SOLICITUD_VECES = "SELECT COUNT(*) AS VECES FROM " + Solicitud.SOLICITUD_DB_NAME + " WHERE " + Solicitud.CUENTA_ENVIA_CODIGO_DB_NAME + " = ? &&  " + Solicitud.CUENTA_RECIBE_CODIGO_DB_NAME + " = ?";
    private final String BUSCAR_SOLICITUD_PENDIENTE = "SELECT * FROM " + Solicitud.SOLICITUD_DB_NAME + " WHERE " + Solicitud.CUENTA_RECIBE_CODIGO_DB_NAME + " = ? && " + Solicitud.ESTADO_DB_NAME + "='Pendiente'";
    private final String SOLICITUD_CODIGO = "SELECT * FROM " + Solicitud.SOLICITUD_DB_NAME + " WHERE " + Solicitud.CODIGO_DB_NAME + "=?";
    private final String EDITAR_SOLICITUD = "UPDATE " + Solicitud.SOLICITUD_DB_NAME + " SET " + Solicitud.FECHA_DB_NAME + "=?,"
            + Solicitud.ESTADO_DB_NAME + "=?," + Solicitud.CUENTA_ENVIA_CODIGO_DB_NAME + "=?,"
            + Solicitud.CUENTA_RECIBE_CODIGO_DB_NAME + "=? WHERE codigo=?";
    private final String REPORTE5 = "SELECT * FROM " + Solicitud.SOLICITUD_DB_NAME + " S INNER JOIN "+Cuenta.CUENTA_DB_NAME+" C ON C.codigo=S.cuenta_codigoenvio WHERE " + Cuenta.CLIENTE_CODIGO_DB_NAME + "=?";
    private final String REPORTE4 = "SELECT * FROM " + Solicitud.SOLICITUD_DB_NAME + " S INNER JOIN "+Cuenta.CUENTA_DB_NAME+" C ON C.codigo=S.cueanta_codigorecibe WHERE " + Cuenta.CLIENTE_CODIGO_DB_NAME + "=?";

    public long crearSolicitud(Solicitud solicitud) throws SQLException {
        try {
            PreparedStatement preSt = Conexion.getConnection().prepareStatement(CREAR_SOLICITUD, Statement.RETURN_GENERATED_KEYS);

            preSt.setDate(1, solicitud.getFecha());
            preSt.setString(2, solicitud.getEstado());
            preSt.setLong(3, solicitud.getCuentaEnvio());
            preSt.setLong(4, solicitud.getCuentaRecibe());
            preSt.executeUpdate();
            ResultSet result = preSt.getGeneratedKeys();
            if (result.first()) {
                return result.getLong(1);
            }
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
        }
        return -1;
    }

    /**
     * Realizamos una busqueda en base al codigo del cajero. De no existir la
     * nos devuelve un valor null.
     *
     * @param codigoEnvia
     * @param codigoRecibe
     * @return
     * @throws SQLException
     */
    public int comprobarSolicitud(Long codigoEnvia, Long codigoRecibe) throws SQLException {
        PreparedStatement preSt = Conexion.getConnection().prepareStatement(BUSCAR_SOLICITUD_VECES);
        preSt.setLong(1, codigoEnvia);
        preSt.setLong(2, codigoRecibe);
        ResultSet result = preSt.executeQuery();
        int veces = 0;
        while (result.next()) {
            veces = result.getInt("VECES");
        }
        return veces;
    }

    /**
     * Realizamos una busqueda en base a el estado de las solicitudes. De no
     * existir la nos devuelve un valor null.
     *
     * @param codigoRecibe
     * @return
     * @throws SQLException
     */
    public ArrayList obtenerSolicitudesPendiente(Long codigoRecibe) throws SQLException {
        PreparedStatement preSt = Conexion.getConnection().prepareStatement(BUSCAR_SOLICITUD_PENDIENTE);
        preSt.setLong(1, codigoRecibe);
        ResultSet result = preSt.executeQuery();
        ArrayList solicitudes = new ArrayList();
        Solicitud solicitud = null;
        while (result.next()) {
            solicitud = new Solicitud(
                    result.getInt(Solicitud.CODIGO_DB_NAME),
                    result.getDate(Solicitud.FECHA_DB_NAME),
                    result.getString(Solicitud.ESTADO_DB_NAME),
                    result.getLong(Solicitud.CUENTA_ENVIA_CODIGO_DB_NAME),
                    result.getLong(Solicitud.CUENTA_RECIBE_CODIGO_DB_NAME)
            );
            solicitudes.add(solicitud);
        }
        return solicitudes;
    }

    /**
     * Realizamos una busqueda en el codigo solicitudes. De no existir nos
     * devuelve un valor null.
     *
     * @return
     * @throws SQLException
     */
    public Solicitud obtenerSolicitudCodigo(int codigo) throws SQLException {
        PreparedStatement preSt = Conexion.getConnection().prepareStatement(SOLICITUD_CODIGO);
        preSt.setInt(1, codigo);
        ResultSet result = preSt.executeQuery();
        Solicitud solicitud = null;
        while (result.next()) {
            solicitud = new Solicitud(
                    result.getInt(Solicitud.CODIGO_DB_NAME),
                    result.getDate(Solicitud.FECHA_DB_NAME),
                    result.getString(Solicitud.ESTADO_DB_NAME),
                    result.getLong(Solicitud.CUENTA_ENVIA_CODIGO_DB_NAME),
                    result.getLong(Solicitud.CUENTA_RECIBE_CODIGO_DB_NAME)
            );
        }
        return solicitud;
    }

    /**
     * Editamos el la solicitud de asociacion
     *
     * @param solicitud
     * @param codigoSolicitud
     * @throws SQLException
     */
    public void actualizarAsociacion(Solicitud solicitud, int codigoSolicitud) throws SQLException {
        try {
            PreparedStatement preSt = Conexion.getConnection().prepareStatement(EDITAR_SOLICITUD, Statement.RETURN_GENERATED_KEYS);

            preSt.setDate(1, solicitud.getFecha());
            preSt.setString(2, solicitud.getEstado());
            preSt.setLong(3, solicitud.getCuentaEnvio());
            preSt.setLong(4, solicitud.getCuentaRecibe());
            preSt.setLong(5, codigoSolicitud);
            preSt.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    /**
     * Realizamos una busqueda en de la cantidad solicitudes. De no existir nos
     * devuelve un valor null.
     *
     * @return
     * @throws SQLException
     */
    public ArrayList obtenerReporte4(Long codigoCuenta) throws SQLException {
        PreparedStatement preSt = Conexion.getConnection().prepareStatement(REPORTE4);
        preSt.setLong(1, codigoCuenta);
        ResultSet result = preSt.executeQuery();
        ArrayList solicitudes = new ArrayList();
        Solicitud solicitud = null;
        while (result.next()) {
            solicitud = new Solicitud(
                    result.getInt(Solicitud.CODIGO_DB_NAME),
                    result.getDate(Solicitud.FECHA_DB_NAME),
                    result.getString(Solicitud.ESTADO_DB_NAME),
                    result.getLong(Solicitud.CUENTA_ENVIA_CODIGO_DB_NAME),
                    result.getLong(Solicitud.CUENTA_RECIBE_CODIGO_DB_NAME)
            );
            solicitudes.add(solicitud);
        }
        return solicitudes;
    }
    public ArrayList obtenerReporte5(Long codigoCuenta) throws SQLException {
        PreparedStatement preSt = Conexion.getConnection().prepareStatement(REPORTE5);
        preSt.setLong(1, codigoCuenta);
        ResultSet result = preSt.executeQuery();
        ArrayList solicitudes = new ArrayList();
        Solicitud solicitud = null;
        while (result.next()) {
            solicitud = new Solicitud(
                    result.getInt(Solicitud.CODIGO_DB_NAME),
                    result.getDate(Solicitud.FECHA_DB_NAME),
                    result.getString(Solicitud.ESTADO_DB_NAME),
                    result.getLong(Solicitud.CUENTA_ENVIA_CODIGO_DB_NAME),
                    result.getLong(Solicitud.CUENTA_RECIBE_CODIGO_DB_NAME)
            );
            solicitudes.add(solicitud);
        }
        return solicitudes;
    }
}

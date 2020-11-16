/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Conexion.Conexion;
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
        int veces=0;
        while (result.next()) {
            veces=result.getInt("VECES");
        }
        return veces;
    }
}

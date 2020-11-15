/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Conexion.Conexion;
import Encriptar.Encriptar;
import Objeto.Cliente;
import Objeto.Historial_Cliente;
import java.io.InputStream;
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
public class Historial_ClienteModel {

    private final String DPI_CLIENTE = "SELECT " + Historial_Cliente.PDF_DB_NAME + " FROM " + Historial_Cliente.HISTORIAL_CLIENTE_DB_NAME + " WHERE " + Historial_Cliente.CLIENTE_CODE_DB_NAME + "= ?";

    public static final String BUSCAR_USUARIO = "Select * FROM " + Historial_Cliente.HISTORIAL_CLIENTE_DB_NAME;
    private final String CREAR_USUARIO = "INSERT INTO " + Historial_Cliente.HISTORIAL_CLIENTE_DB_NAME + " (" + Cliente.NOMBRE_DB_NAME + "," + Cliente.DPI_DB_NAME + "," + Cliente.FECHA_DB_NAME + "," + Cliente.DIRECCION_DB_NAME + "," + Cliente.SEXO_DB_NAME + "," + Cliente.PDF_DB_NAME + "," + Cliente.PASSWORD_DB_NAME + "," + Historial_Cliente.HIST_CLIENTE_CODE_DB_NAME + ") VALUES (?,?,?,?,?,?,?,?)";

    /**
     * Agregamos una nuevo usuario. Al completar la insercion devuelve el ID
     * autogenerado del usuario. De no existir nos devolvera <code>-1</code>.
     *
     * @param usuario
     * @return
     * @throws SQLException
     */
    public long agregarHistorialCliente(Cliente cajero) throws SQLException {
        try {
            PreparedStatement preSt = Conexion.getConnection().prepareStatement(CREAR_USUARIO, Statement.RETURN_GENERATED_KEYS);

            preSt.setString(1, cajero.getNombre());
            preSt.setString(2, cajero.getDpi());
            preSt.setDate(3, cajero.getFechaNacimiento());
            preSt.setString(4, cajero.getDireccion());
            preSt.setString(5, cajero.getSexo());
            preSt.setBinaryStream(6, cajero.getPdfdpi());
            preSt.setString(7, cajero.getPassword());
            preSt.setLong(8, cajero.getCodigo());

            preSt.executeUpdate();

            ResultSet result = preSt.getGeneratedKeys();
            if (result.first()) {
                return result.getLong(1);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return -1;
    }

    public long agregarHistorialClienteSinCodigo(Cliente cajero, Long codigo) throws SQLException {
        try {
            PreparedStatement preSt = Conexion.getConnection().prepareStatement(CREAR_USUARIO, Statement.RETURN_GENERATED_KEYS);

            preSt.setString(1, cajero.getNombre());
            preSt.setString(2, cajero.getDpi());
            preSt.setDate(3, cajero.getFechaNacimiento());
            preSt.setString(4, cajero.getDireccion());
            preSt.setString(5, cajero.getSexo());
            preSt.setBinaryStream(6, cajero.getPdfdpi());
            preSt.setString(7, cajero.getPassword());
            preSt.setLong(8, codigo);

            preSt.executeUpdate();

            ResultSet result = preSt.getGeneratedKeys();
            if (result.first()) {
                return result.getLong(1);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return -1;
    }

    public ArrayList obtenerHistorialClientes(String idUsuario) throws SQLException, UnsupportedEncodingException {
        PreparedStatement preSt = Conexion.getConnection().prepareStatement(BUSCAR_USUARIO + " WHERE codigo LIKE '%" + idUsuario + "%'");
        ResultSet result = preSt.executeQuery();
        ArrayList listaHistorialclientes = new ArrayList();
        Historial_Cliente cliente = null;

        while (result.next()) {
            cliente = new Historial_Cliente(
                    result.getLong(cliente.CLIENTE_CODE_DB_NAME),
                    result.getString(cliente.NOMBRE_DB_NAME),
                    result.getString(cliente.DPI_DB_NAME),
                    result.getString(cliente.SEXO_DB_NAME),
                    result.getString(cliente.PASSWORD_DB_NAME),
                    result.getString(cliente.DIRECCION_DB_NAME),
                    result.getDate(cliente.FECHA_DB_NAME),
                    result.getBinaryStream(cliente.PDF_DB_NAME),
                    result.getLong(cliente.HIST_CLIENTE_CODE_DB_NAME)
            );
            listaHistorialclientes.add(cliente);
        }
        return listaHistorialclientes;
    }

    public InputStream obtenerDPI(long codigo) {
        try {
            PreparedStatement preSt = Conexion.getConnection().prepareStatement(DPI_CLIENTE);
            preSt.setLong(1, codigo);

            ResultSet result = preSt.executeQuery();

            while (result.next()) {
                return result.getBlob(1).getBinaryStream();
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener dpi de db " + e);
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        return null;
    }
}

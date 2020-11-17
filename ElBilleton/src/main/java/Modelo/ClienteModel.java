/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Conexion.Conexion;
import Encriptar.Encriptar;
import Objeto.Cliente;
import Objeto.Cuenta;
import Objeto.DuplicarPdf;
import Objeto.Transaccion;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
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
public class ClienteModel {

    public static final String BUSCAR_USUARIO = "Select * FROM " + Cliente.CLIENTE_DB_NAME;

    private final String CREAR_USUARIO = "INSERT INTO " + Cliente.CLIENTE_DB_NAME + " (" + Cliente.NOMBRE_DB_NAME + "," + Cliente.DPI_DB_NAME + "," + Cliente.FECHA_DB_NAME + "," + Cliente.DIRECCION_DB_NAME + "," + Cliente.SEXO_DB_NAME + "," + Cliente.PDF_DB_NAME + "," + Cliente.PASSWORD_DB_NAME + ") VALUES (?,?,?,?,?,?,?)";
    private final String CREAR_USUARIO_CODIGO = "INSERT INTO " + Cliente.CLIENTE_DB_NAME + " (" + Cliente.CLIENTE_CODE_DB_NAME + "," + Cliente.NOMBRE_DB_NAME + "," + Cliente.DPI_DB_NAME + "," + Cliente.FECHA_DB_NAME + "," + Cliente.DIRECCION_DB_NAME + "," + Cliente.SEXO_DB_NAME + "," + Cliente.PDF_DB_NAME + "," + Cliente.PASSWORD_DB_NAME + ") VALUES (?,?,?,?,?,?,?,?)";
    private final String EDITAR_CLIENTE = "UPDATE " + Cliente.CLIENTE_DB_NAME + " SET " + Cliente.NOMBRE_DB_NAME + "=?,"
            + Cliente.DPI_DB_NAME + "=?," + Cliente.FECHA_DB_NAME + "=?," + Cliente.DIRECCION_DB_NAME + "=?," + Cliente.SEXO_DB_NAME + "=?,"
            + Cliente.PDF_DB_NAME + "=?," + Cliente.PASSWORD_DB_NAME + "=? WHERE " + Cliente.CLIENTE_CODE_DB_NAME + " =?";
    private final String DPI_CLIENTE = "SELECT " + Cliente.PDF_DB_NAME + " FROM " + Cliente.CLIENTE_DB_NAME + " WHERE " + Cliente.CLIENTE_CODE_DB_NAME + "= ?";
    private final String REPORTE_2 = "SELECT C.* FROM " + Cliente.CLIENTE_DB_NAME + " C INNER JOIN " + Cuenta.CUENTA_DB_NAME + " CU ON C.codigo=CU.cliente_codigo INNER JOIN " + Transaccion.TRANSACCION_DB_NAME + " T ON T.cuenta_codigo=CU.codigo WHERE T.monto>? GROUP BY C.codigo";
    private final String REPORTE_3 = "SELECT C.*,SUM(T.monto) AS suma FROM " + Cliente.CLIENTE_DB_NAME + " C INNER JOIN " + Cuenta.CUENTA_DB_NAME + " CU ON C.codigo=CU.cliente_codigo INNER JOIN " + Transaccion.TRANSACCION_DB_NAME + " T ON T.cuenta_codigo=CU.codigo GROUP BY C.codigo HAVING suma>?";
    private final String REPORTE_4 = "SELECT C.*,SUM(CU.monto) AS suma FROM " + Cliente.CLIENTE_DB_NAME + " C INNER JOIN " + Cuenta.CUENTA_DB_NAME + " CU ON C.codigo=CU.cliente_codigo GROUP BY C.codigo ORDER BY suma DESC LIMIT 10";
    private final String REPORTE_5 = "SELECT * FROM " + Cliente.CLIENTE_DB_NAME + " WHERE nombre NOT IN(SELECT C.nombre FROM " + Cliente.CLIENTE_DB_NAME + " C INNER JOIN " + Cuenta.CUENTA_DB_NAME + " CU ON CU.cliente_codigo=C.codigo RIGHT JOIN " + Transaccion.TRANSACCION_DB_NAME + " T ON T.cuenta_codigo=CU.codigo WHERE T.fecha BETWEEN ? AND ? GROUP BY C.codigo)";
    private final String CLIENTE_REPORTE_6 = "SELECT C.* FROM " + Cliente.CLIENTE_DB_NAME + " C INNER JOIN " + Cuenta.CUENTA_DB_NAME + " CU ON CU.cliente_codigo=C.codigo WHERE CU.monto>? && C.nombre LIKE ? GROUP BY C.codigo ORDER BY C.nombre DESC";

    /**
     * Agregamos una nuevo usuario. Al completar la insercion devuelve el ID
     * autogenerado del usuario. De no existir nos devolvera <code>-1</code>.
     *
     * @param usuario
     * @return
     * @throws SQLException
     */
    public long agregarCliente(Cliente cajero) throws SQLException, UnsupportedEncodingException {
        try {
            PreparedStatement preSt = Conexion.getConnection().prepareStatement(CREAR_USUARIO, Statement.RETURN_GENERATED_KEYS);

            preSt.setString(1, cajero.getNombre());
            preSt.setString(2, cajero.getDpi());
            preSt.setDate(3, cajero.getFechaNacimiento());
            preSt.setString(4, cajero.getDireccion());
            preSt.setString(5, cajero.getSexo());
            preSt.setBinaryStream(6, cajero.getPdfdpi());
            preSt.setString(7, Encriptar.encriptar(cajero.getPassword()));
            preSt.executeUpdate();

            ResultSet result = preSt.getGeneratedKeys();
            if (result.first()) {
                return result.getLong(1);
            }
        } catch (SQLException e) {

        }

        return -1;
    }

    public long agregarClienteCodigo(Cliente cajero) throws SQLException, UnsupportedEncodingException {
        try {
            PreparedStatement preSt = Conexion.getConnection().prepareStatement(CREAR_USUARIO_CODIGO, Statement.RETURN_GENERATED_KEYS);
            DuplicarPdf crearPdf;
            crearPdf = new DuplicarPdf(cajero.getPdfdpi());
            InputStream pdf1 = new ByteArrayInputStream(crearPdf.obtenerArrayDatos());
            InputStream pdf2 = new ByteArrayInputStream(crearPdf.obtenerArrayDatos());
            preSt.setLong(1, cajero.getCodigo());
            preSt.setString(2, cajero.getNombre());
            preSt.setString(3, cajero.getDpi());
            preSt.setDate(4, cajero.getFechaNacimiento());
            preSt.setString(5, cajero.getDireccion());
            preSt.setString(6, cajero.getSexo());
            preSt.setBinaryStream(7, pdf1);
            preSt.setString(8, Encriptar.encriptar(cajero.getPassword()));
            preSt.executeUpdate();
            Historial_ClienteModel hist = new Historial_ClienteModel();
            cajero.setPdfdpi(pdf2);
            hist.agregarHistorialCliente(cajero);
            ResultSet result = preSt.getGeneratedKeys();
            if (result.first()) {
                return result.getLong(1);
            }
        } catch (SQLException e) {
        }

        return -1;
    }

    public long modificarCliente(Cliente cajero) throws SQLException, UnsupportedEncodingException {
        try {
            PreparedStatement preSt = Conexion.getConnection().prepareStatement(EDITAR_CLIENTE, Statement.RETURN_GENERATED_KEYS);

            preSt.setString(1, cajero.getNombre());
            preSt.setString(2, cajero.getDpi());
            preSt.setDate(3, cajero.getFechaNacimiento());
            preSt.setString(4, cajero.getDireccion());
            preSt.setString(5, cajero.getSexo());

            preSt.setBinaryStream(6, cajero.getPdfdpi());
            preSt.setString(7, Encriptar.encriptar(cajero.getPassword()));
            preSt.setLong(8, cajero.getCodigo());
            preSt.executeUpdate();
        } catch (SQLException e) {
            
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
    public Cliente loginValidation(Long id, String pass) throws SQLException, UnsupportedEncodingException {
        Cliente cliente = obtenerCliente(id);

        if (cliente != null && cliente.getPassword().equals(pass)) {
            return cliente;

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
    public Cliente obtenerCliente(Long idUsuario) throws SQLException, UnsupportedEncodingException {
        PreparedStatement preSt = Conexion.getConnection().prepareStatement(BUSCAR_USUARIO + " WHERE codigo='" + idUsuario + "'");
        ResultSet result = preSt.executeQuery();

        Cliente cliente = null;

        while (result.next()) {
            cliente = new Cliente(
                    result.getLong(cliente.CLIENTE_CODE_DB_NAME),
                    result.getString(cliente.NOMBRE_DB_NAME),
                    result.getString(cliente.DPI_DB_NAME),
                    result.getString(cliente.SEXO_DB_NAME),
                    result.getString(cliente.PASSWORD_DB_NAME),
                    result.getString(cliente.DIRECCION_DB_NAME),
                    result.getDate(cliente.FECHA_DB_NAME),
                    result.getBinaryStream(cliente.PDF_DB_NAME)
            );
        }
        cliente.setPassword(Encriptar.desencriptar(cliente.getPassword()));
        return cliente;
    }

    public ArrayList obtenerClientes(String idUsuario) throws SQLException, UnsupportedEncodingException {
        PreparedStatement preSt = Conexion.getConnection().prepareStatement(BUSCAR_USUARIO + " WHERE codigo LIKE '%" + idUsuario + "%'");
        ResultSet result = preSt.executeQuery();
        ArrayList listaclientes = new ArrayList();
        Cliente cliente = null;

        while (result.next()) {
            cliente = new Cliente(
                    result.getLong(cliente.CLIENTE_CODE_DB_NAME),
                    result.getString(cliente.NOMBRE_DB_NAME),
                    result.getString(cliente.DPI_DB_NAME),
                    result.getString(cliente.SEXO_DB_NAME),
                    result.getString(cliente.PASSWORD_DB_NAME),
                    result.getString(cliente.DIRECCION_DB_NAME),
                    result.getDate(cliente.FECHA_DB_NAME),
                    result.getBinaryStream(cliente.PDF_DB_NAME)
            );
            cliente.setPassword(Encriptar.desencriptar(cliente.getPassword()));
            listaclientes.add(cliente);
        }
        return listaclientes;
    }

    /**
     *
     * @param codigo
     * @return
     */
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

    public ArrayList obtenerClientesReporte2(Double monto) throws SQLException, UnsupportedEncodingException {
        try {

            PreparedStatement preSt = Conexion.getConnection().prepareStatement(REPORTE_2);

            preSt.setDouble(1, monto);
            ResultSet result = preSt.executeQuery();
            ArrayList listaclientes = new ArrayList();
            Cliente cliente = null;

            while (result.next()) {

                cliente = new Cliente(
                        result.getLong(cliente.CLIENTE_CODE_DB_NAME),
                        result.getString(cliente.NOMBRE_DB_NAME),
                        result.getString(cliente.DPI_DB_NAME),
                        result.getString(cliente.SEXO_DB_NAME),
                        result.getString(cliente.PASSWORD_DB_NAME),
                        result.getString(cliente.DIRECCION_DB_NAME),
                        result.getDate(cliente.FECHA_DB_NAME),
                        result.getBinaryStream(cliente.PDF_DB_NAME)
                );
                cliente.setPassword(Encriptar.desencriptar(cliente.getPassword()));
                listaclientes.add(cliente);
            }
            return listaclientes;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;

    }

    public ArrayList obtenerClientesReporte3(Double monto) throws SQLException, UnsupportedEncodingException {
        try {

            PreparedStatement preSt = Conexion.getConnection().prepareStatement(REPORTE_3);

            preSt.setDouble(1, monto);
            ResultSet result = preSt.executeQuery();
            ArrayList listaclientes = new ArrayList();
            Cliente cliente = null;

            while (result.next()) {

                cliente = new Cliente(
                        result.getLong(cliente.CLIENTE_CODE_DB_NAME),
                        result.getString(cliente.NOMBRE_DB_NAME),
                        result.getString(cliente.DPI_DB_NAME),
                        result.getString(cliente.SEXO_DB_NAME),
                        result.getString(cliente.DIRECCION_DB_NAME),
                        result.getDate(cliente.FECHA_DB_NAME),
                        result.getDouble(cliente.SUMA_DB_NAME)
                );
                listaclientes.add(cliente);
            }
            return listaclientes;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;

    }

    public ArrayList obtenerClientesReporte4() throws SQLException, UnsupportedEncodingException {
        try {

            PreparedStatement preSt = Conexion.getConnection().prepareStatement(REPORTE_4);
            ResultSet result = preSt.executeQuery();
            ArrayList listaclientes = new ArrayList();
            Cliente cliente = null;
            while (result.next()) {

                cliente = new Cliente(
                        result.getLong(cliente.CLIENTE_CODE_DB_NAME),
                        result.getString(cliente.NOMBRE_DB_NAME),
                        result.getString(cliente.DPI_DB_NAME),
                        result.getString(cliente.SEXO_DB_NAME),
                        result.getString(cliente.DIRECCION_DB_NAME),
                        result.getDate(cliente.FECHA_DB_NAME),
                        result.getDouble(cliente.SUMA_DB_NAME)
                );
                listaclientes.add(cliente);
            }
            return listaclientes;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;

    }

    public ArrayList obtenerClientesReporte5(Date fecha1, Date fecha2) throws SQLException, UnsupportedEncodingException {
        try {

            PreparedStatement preSt = Conexion.getConnection().prepareStatement(REPORTE_5);
            preSt.setDate(1, fecha1);
            preSt.setDate(2, fecha2);
            ResultSet result = preSt.executeQuery();
            ArrayList listaclientes = new ArrayList();
            Cliente cliente = null;
            while (result.next()) {

                cliente = new Cliente(
                        result.getLong(cliente.CLIENTE_CODE_DB_NAME),
                        result.getString(cliente.NOMBRE_DB_NAME),
                        result.getString(cliente.DPI_DB_NAME),
                        result.getString(cliente.SEXO_DB_NAME),
                        result.getString(cliente.PASSWORD_DB_NAME),
                        result.getString(cliente.DIRECCION_DB_NAME),
                        result.getDate(cliente.FECHA_DB_NAME),
                        result.getBinaryStream(cliente.PDF_DB_NAME)
                );
                listaclientes.add(cliente);
            }
            return listaclientes;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;

    }

    public ArrayList obtenerClientesReporte6(Double monto, String nombre) throws SQLException, UnsupportedEncodingException {
        try {

            PreparedStatement preSt = Conexion.getConnection().prepareStatement(CLIENTE_REPORTE_6);
            preSt.setDouble(1, monto);
            preSt.setString(2, "%" + nombre + "%");
            ResultSet result = preSt.executeQuery();
            ArrayList listaclientes = new ArrayList();
            Cliente cliente = null;
            while (result.next()) {

                cliente = new Cliente(
                        result.getLong(cliente.CLIENTE_CODE_DB_NAME),
                        result.getString(cliente.NOMBRE_DB_NAME),
                        result.getString(cliente.DPI_DB_NAME),
                        result.getString(cliente.SEXO_DB_NAME),
                        result.getString(cliente.PASSWORD_DB_NAME),
                        result.getString(cliente.DIRECCION_DB_NAME),
                        result.getDate(cliente.FECHA_DB_NAME),
                        result.getBinaryStream(cliente.PDF_DB_NAME)
                );
                listaclientes.add(cliente);
            }
            return listaclientes;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;

    }

}

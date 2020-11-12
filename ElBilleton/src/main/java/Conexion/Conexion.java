/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import static java.lang.System.out;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author potz
 */
public class Conexion {

    /**
     * Datos para conectar con la base de datos
     */
    public static final int MYSQL_ERROR = 1062;
    private static Connection conexion = null;
    private static  final String driver = "com.mysql.jdbc.Driver";
    static String user = "root";
    static String password = "juanpablo07";
    private static  final String url = "jdbc:mysql://localhost:3306/BANCO";
    
    /**
     * Metodo para conectar con la base de datos
     * Mandando el user, password y url
     */
    public static void conexionDB() {
       conexion = null;
        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(url, user, password);
             out.println("Conexion establecida");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectar " + e);
        }

    }
    
    /**
     * Obtenemos la conexion
     * @return 
     */
    public static Connection getConnection() {
        return (Connection) conexion;
    }

    /**
     * metodo encargado de desconectar de la base de datos
     */
    public static void disconnectDB() {
        conexion = null;
        if (conexion == null) {
            
        }
    }

}

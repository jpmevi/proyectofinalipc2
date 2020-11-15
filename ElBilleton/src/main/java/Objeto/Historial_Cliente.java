/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objeto;

import java.io.InputStream;
import java.sql.Date;

/**
 *
 * @author potz
 */
public class Historial_Cliente {
    //Variables estaticas para los atributos de la entidad
    public static final String HISTORIAL_CLIENTE_DB_NAME = "HISTORIAL_CLIENTE";
    public static final String CLIENTE_CODE_DB_NAME = "codigo";
    public static final String NOMBRE_DB_NAME = "nombre";
    public static final String DPI_DB_NAME = "dpi";
    public static final String SEXO_DB_NAME = "sexo";
    public static final String DIRECCION_DB_NAME = "direccion";
    public static final String FECHA_DB_NAME = "fechaNacimiento";
    public static final String PDF_DB_NAME = "pdfdpi";
    public static final String PASSWORD_DB_NAME = "password";
    public static final String HIST_CLIENTE_CODE_DB_NAME = "cliente_codigo";
    
    private Long codigo;
    private String nombre;
    private String DPI;
    private String sexo;
    private String password;
    private String direccion;
    private Date fechaNacimiento;
    private InputStream pdfdpi;
    private Long entidad_codigo;

    public Historial_Cliente() {
    }

    public Historial_Cliente(Long codigo, String nombre, String DPI, String sexo, String password, String direccion, Date fechaNacimiento, InputStream pdfdpi, Long entidad_codigo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.DPI = DPI;
        this.sexo = sexo;
        this.password = password;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.pdfdpi = pdfdpi;
        this.entidad_codigo = entidad_codigo;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDPI() {
        return DPI;
    }

    public void setDPI(String DPI) {
        this.DPI = DPI;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public InputStream getPdfdpi() {
        return pdfdpi;
    }

    public void setPdfdpi(InputStream pdfdpi) {
        this.pdfdpi = pdfdpi;
    }

    public Long getEntidad_codigo() {
        return entidad_codigo;
    }

    public void setEntidad_codigo(Long entidad_codigo) {
        this.entidad_codigo = entidad_codigo;
    }

    

   
}

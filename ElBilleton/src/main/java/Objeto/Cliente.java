/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objeto;

import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author potz
 */
public class Cliente {
    //Variables estaticas para los atributos de la entidad
    public static final String CLIENTE_DB_NAME = "CLIENTE";
    public static final String CLIENTE_CODE_DB_NAME = "codigo";
    public static final String NOMBRE_DB_NAME = "nombre";
    public static final String DPI_DB_NAME = "dpi";
    public static final String SEXO_DB_NAME = "sexo";
    public static final String DIRECCION_DB_NAME = "direccion";
    public static final String FECHA_DB_NAME = "fechaNacimiento";
    public static final String PDF_DB_NAME = "pdfdpi";
    public static final String PASSWORD_DB_NAME = "password";
    public static final String CLIENTE_CODIGO_DB_NAME = "cliente_codigo";
    public static final String SUMA_DB_NAME = "suma";
    
    private Long codigo;
    private String nombre;
    private String dpi;
    private String sexo;
    private String password;
    private String direccion;
    private Date fechaNacimiento;
    private InputStream pdfdpi;
    private Double suma;

    public Cliente(Long codigo, String nombre, String dpi, String sexo, String password, String direccion, Date fechaNacimiento, InputStream pdfdpi) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.dpi = dpi;
        this.sexo = sexo;
        this.password = password;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.pdfdpi = pdfdpi;
    }

    public Cliente(Long codigo, String nombre, String dpi, String sexo, String direccion, Date fechaNacimiento, Double suma) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.dpi = dpi;
        this.sexo = sexo;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.suma = suma;
    }

    public Double getSuma() {
        return suma;
    }

    public void setSuma(Double suma) {
        this.suma = suma;
    }
    

    
    public Cliente(){
        
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

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
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
    

    
}

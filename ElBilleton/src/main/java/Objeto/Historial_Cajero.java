/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objeto;

/**
 *
 * @author potz
 */
public class Historial_Cajero {
    //Variables estaticas para los atributos de la entidad
    public static final String HISTORIAL_CAJERO_DB_NAME = "HISTORIAL_CAJERO";
    public static final String CODIGO_DB_NAME = "codigo";
    public static final String NOMBRE_DB_NAME = "nombre";
    public static final String TURNO_DB_NAME = "turno";
    public static final String DPI_DB_NAME = "dpi";
    public static final String DIRECCION_DB_NAME = "direccion";
    public static final String SEXO_DB_NAME = "sexo";
    public static final String PASSWORD_DB_NAME = "password";
    public static final String CAJERO_CODIGO_DB_NAME = "cajero_codigo";
    
    
    private Long codigo;
    private String nombre;
    private String turno;
    private String DPI;
    private String direccion;
    private String sexo;
    private String password;
     private Long entidad_codigo;

    public Historial_Cajero(Long codigo, String nombre, String turno, String DPI, String direccion, String sexo, String password, Long entidad_codigo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.turno = turno;
        this.DPI = DPI;
        this.direccion = direccion;
        this.sexo = sexo;
        this.password = password;
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

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getDPI() {
        return DPI;
    }

    public void setDPI(String DPI) {
        this.DPI = DPI;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public Long getEntidad_codigo() {
        return entidad_codigo;
    }

    public void setEntidad_codigo(Long entidad_codigo) {
        this.entidad_codigo = entidad_codigo;
    }

    
   
     
}

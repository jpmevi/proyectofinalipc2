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
public class HIstorial_Gerente {
    //Variables estaticas para los atributos de la entidad
    public static final String HISTORIAL_GERENTE_DB_NAME = "HISTORIAL_GERENTE";
    public static final String CODIGO_DB_NAME = "codigo";
    public static final String NOMBRE_DB_NAME = "nombre";
    public static final String TURNO_DB_NAME = "turno";
    public static final String DPI_DB_NAME = "dpi";
    public static final String DIRECCION_DB_NAME = "direccion";
    public static final String SEXO_DB_NAME = "sexo";
    public static final String PASSWORD_DB_NAME = "password";
    public static final String GERENTE_CODIGO_DB_NAME = "gerente_codigo";
    
    
    private int codigo;
    private String nombre;
    private String turno;
    private int DPI;
    private String direccion;
    private String sexo;
    private String password;
     private int gerente_codigo;

    public HIstorial_Gerente(int codigo, String nombre, String turno, int DPI, String direccion, String sexo, String password, int gerente_codigo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.turno = turno;
        this.DPI = DPI;
        this.direccion = direccion;
        this.sexo = sexo;
        this.password = password;
        this.gerente_codigo = gerente_codigo;
    }
public HIstorial_Gerente(){
    
}
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
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

    public int getDPI() {
        return DPI;
    }

    public void setDPI(int DPI) {
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

    public int getGerente_codigo() {
        return gerente_codigo;
    }

    public void setGerente_codigo(int gerente_codigo) {
        this.gerente_codigo = gerente_codigo;
    }
     
     
}

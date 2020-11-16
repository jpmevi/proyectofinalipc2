/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objeto;

import java.sql.Date;

/**
 *
 * @author potz
 */
public class Solicitud {
    public static final String SOLICITUD_DB_NAME = "SOLICITUD";
    public static final String CODIGO_DB_NAME = "codigo";
    public static final String FECHA_DB_NAME = "fecha";
    public static final String ESTADO_DB_NAME = "estado";
    public static final String CUENTA_ENVIA_CODIGO_DB_NAME = "cuenta_codigoenvio";
    public static final String CUENTA_RECIBE_CODIGO_DB_NAME = "cueanta_codigorecibe";
    
    private int codigo;
    private Date fecha;
    private String estado;
    private Long cuentaEnvio;
    private Long cuentaRecibe;

    public Solicitud(int codigo, Date fecha, String estado, Long cuentaEnvio, Long cuentaRecibe) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.estado = estado;
        this.cuentaEnvio = cuentaEnvio;
        this.cuentaRecibe = cuentaRecibe;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getCuentaEnvio() {
        return cuentaEnvio;
    }

    public void setCuentaEnvio(Long cuentaEnvio) {
        this.cuentaEnvio = cuentaEnvio;
    }

    public Long getCuentaRecibe() {
        return cuentaRecibe;
    }

    public void setCuentaRecibe(Long cuentaRecibe) {
        this.cuentaRecibe = cuentaRecibe;
    }
    
    
}

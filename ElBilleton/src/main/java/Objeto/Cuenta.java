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
public class Cuenta {
    //Variables constantes de Querys
    public static final String CUENTA_DB_NAME = "CUENTA";
    public static final String CODIGO_DB_NAME = "codigo";
    public static final String FECHA_CREACION_DB_NAME = "fechaCreacion";
    public static final String MONTO_DB_NAME = "monto";
    public static final String CLIENTE_CODIGO_DB_NAME = "cliente_codigo";


    private Long codigo;
    private Date fechaCreacion;
    private Double monto;
    private Long cliente_codigo;

    public Cuenta(Long codigo, Date fechaCreacion, Double monto, Long cliente_codigo) {
        this.codigo = codigo;
        this.fechaCreacion = fechaCreacion;
        this.monto = monto;
        this.cliente_codigo = cliente_codigo;
    }

    public Cuenta(){
        
    }
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Long getCliente_codigo() {
        return cliente_codigo;
    }

    public void setCliente_codigo(Long cliente_codigo) {
        this.cliente_codigo = cliente_codigo;
    }

    
}

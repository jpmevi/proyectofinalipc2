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
public class Limite {
     //Variables estaticas para los atributos de la entidad
    public static final String LIMITE_DB_NAME = "LIMITE";
    public static final String LIMITE_REPORTE2_DB_NAME = "limiteReporte2";
    public static final String LIMITE_REPORTE3_DB_NAME = "limiteReporte3";

    
    private Double limiteReporte2;
    private Double limiteReporte3;

    public Limite(Double limiteReporte2, Double limiteReporte3) {
        this.limiteReporte2 = limiteReporte2;
        this.limiteReporte3 = limiteReporte3;
    }

    public Double getLimiteReporte2() {
        return limiteReporte2;
    }

    public void setLimiteReporte2(Double limiteReporte2) {
        this.limiteReporte2 = limiteReporte2;
    }

    public Double getLimiteReporte3() {
        return limiteReporte3;
    }

    public void setLimiteReporte3(Double limiteReporte3) {
        this.limiteReporte3 = limiteReporte3;
    }
    
    
}

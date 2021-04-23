/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boot.model;

/**
 *
 * @author develop
 */
public class NuneroHistoriaClinica {

    private int dato;
    String numero;

    public String NumHistoria(int dato) {
        this.dato = dato + 1;
        if (dato >= 1000000 && dato <= 10000000) {
            numero = "" + this.dato;
        }
        if (dato >= 100000 && dato <= 1000000) {
            numero = "0" + this.dato;
        }
        if (dato >= 10000 && dato <= 100000) {
            numero = "00" + this.dato;
        }
        if (dato >= 1000 && dato <= 10000) {
            numero = "000" + this.dato;
        }
        if (dato >= 100 && dato <= 1000) {
            numero = "0000" + this.dato;
        }
        if (dato >= 10 && dato <= 100) {
            numero = "00000" + this.dato;
        }
        if (dato < 10) {
            numero = "000000" + this.dato;
        }

        return numero;
    }

}

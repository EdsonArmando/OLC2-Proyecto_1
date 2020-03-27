/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instruccion;

import Entorno.Entorno;
import Entorno.Simbolo;
import Expresion.Expresion;
import Expresion.Literal;
import Views.Inicio;

/**
 *
 * @author EG
 */
public class Ncol extends Funcion{

    public Ncol(String id, Simbolo.EnumTipoDato tipo) {
        super(id, tipo);
    }

    @Override
    public Expresion obtenerValor(Entorno ent) {
        Object[][] matriz = (Object[][])param_Actuales.get(0).obtenerValor(ent).valor;
        return new Literal(Simbolo.EnumTipoDato.INT,matriz[0].length);
    }

    @Override
    public Simbolo.EnumTipoDato getTipo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retornar ejecutar(Entorno ent) {
        Object[][] matriz = (Object[][])param_Actuales.get(0).obtenerValor(ent).valor;
        Inicio.salidaConsola.append(String.valueOf(matriz[0].length)+"\n");
        return new Retornar();
    }
    
}

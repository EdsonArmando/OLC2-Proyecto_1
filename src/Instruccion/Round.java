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
public class Round extends Funcion{

    public Round(String id, Simbolo.EnumTipoDato tipo) {
        super(id, tipo);
    }

    @Override
    public Expresion obtenerValor(Entorno ent) {
        Double resultado = (Double)param_Actuales.get(0).obtenerValor(ent).valor;
        if(resultado!=null){
            Long temp = Math.round(resultado);
            return new Literal(Simbolo.EnumTipoDato.INT,temp.doubleValue());
        }else{
            return new Literal(Simbolo.EnumTipoDato.ERROR,null);
        }
    }

    @Override
    public Simbolo.EnumTipoDato getTipo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     @Override
    public Retornar ejecutar(Entorno ent) {
        Double resultado = (Double)param_Actuales.get(0).obtenerValor(ent).valor;
        if(resultado!=null){
            Inicio.salidaConsola.append(String.valueOf(Math.round(resultado))+"\n");
            return new Retornar();
        }else{
           Inicio.salidaConsola.append("Existio un error \n");
           return new Retornar();
        }
    }
    
}

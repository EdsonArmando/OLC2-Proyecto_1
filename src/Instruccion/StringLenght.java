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
public class StringLenght extends Funcion{

    public StringLenght(String id, Simbolo.EnumTipoDato tipo) {
        super(id, tipo);
    }

    @Override
    public Expresion obtenerValor(Entorno ent) {
        Object uno = param_Actuales.get(0).obtenerValor(ent).valor;
        if(uno instanceof String){
            String val = (String) uno;
            return new Literal(Simbolo.EnumTipoDato.INT,val.length());
        }else{
            Inicio.salidaConsola.append("La variable debe ser de tipo String \n");
            return new Literal(Simbolo.EnumTipoDato.ERROR,null);
        }
    }

    @Override
    public Simbolo.EnumTipoDato getTipo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retornar ejecutar(Entorno ent) {
        Object uno = param_Actuales.get(0).valor;
        if(uno instanceof String){
            String val = (String) uno;
            Inicio.salidaConsola.append(String.valueOf(val.length())+"\n");
            return new Retornar();
        }else{
            Inicio.salidaConsola.append("La variable debe ser de tipo String \n");
            return new Retornar();
        }
    }
    
}

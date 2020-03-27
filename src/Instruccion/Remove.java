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
public class Remove extends Funcion {

    public Remove(String id, Simbolo.EnumTipoDato tipo) {
        super(id, tipo);
    }

    @Override
    public Expresion obtenerValor(Entorno ent) {
        String resultado = param_Actuales.get(0).obtenerValor(ent).valor.toString();
        String remover = param_Actuales.get(1).obtenerValor(ent).valor.toString();
        if(resultado!=null&&remover!=null){
             return new Literal(Simbolo.EnumTipoDato.STRING,resultado.replace(remover, ""));
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
        String resultado = param_Actuales.get(0).obtenerValor(ent).valor.toString();
        String remover = param_Actuales.get(1).obtenerValor(ent).valor.toString();
        if(resultado!=null&&remover!=null){
            Inicio.salidaConsola.append(resultado.replace(remover, "")+"\n");
            return new Retornar();
        }else{
            Inicio.salidaConsola.append("Existio un error");
            return new Retornar();
        }
        
    }
    
}

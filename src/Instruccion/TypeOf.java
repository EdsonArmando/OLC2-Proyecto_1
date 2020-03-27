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
public class TypeOf extends Funcion{
     public TypeOf(String id,Simbolo.EnumTipoDato tipo){
        super(id, tipo);
    }
    @Override
    public Expresion obtenerValor(Entorno ent) {
        Expresion resultado = param_Actuales.get(0).obtenerValor(ent);
        return new Literal(Simbolo.EnumTipoDato.STRING,resultado.tipo.toString());
    }

    @Override
    public Simbolo.EnumTipoDato getTipo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retornar ejecutar(Entorno ent) {
        Expresion resultado = param_Actuales.get(0).obtenerValor(ent);
        Inicio.salidaConsola.append(resultado.tipo.toString()+" \n");
        return new Retornar();
    }
}

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
import java.util.LinkedList;
import java.util.Vector;

/**
 *
 * @author EG
 */
public class Function_C extends Funcion {
    Simbolo.EnumTipoDato tipo;
    public Function_C(String id,Simbolo.EnumTipoDato tipo){
        super(id, tipo);
    }
    
    @Override
    public Expresion obtenerValor(Entorno ent) {
       LinkedList var = new LinkedList();
       Vector vecto = new Vector();
        for(Expresion exp : param_Actuales){
            Expresion resultado = exp.obtenerValor(ent);
            if(resultado.tipo == Simbolo.EnumTipoDato.VECTOR){
                var.add(resultado);
                //vecto.addAll((Vector)resultado.valor);
            }else if(resultado.tipo==Simbolo.EnumTipoDato.LIST){
                LinkedList nueva=(LinkedList)resultado.valor;
                var.addAll(nueva);
            }else{
                vecto.add(resultado);
            }
        }
        if(var.size()!=0){
            if(vecto.size()!=0){
                var.addAll(vecto);
            }
            return new Literal(Simbolo.EnumTipoDato.LIST,var);
        }
        return new Literal(Simbolo.EnumTipoDato.VECTOR,vecto);
    }

    @Override
    public Simbolo.EnumTipoDato getTipo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retornar ejecutar(Entorno ent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

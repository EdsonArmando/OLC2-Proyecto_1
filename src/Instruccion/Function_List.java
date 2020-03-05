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
public class Function_List extends Funcion{

    public Function_List(String id, Simbolo.EnumTipoDato tipo) {
        super(id, tipo);
    }

    @Override
    public Expresion obtenerValor(Entorno ent) {
        LinkedList<Object> list = new LinkedList();
        for(Expresion exp : param_Actuales){
            Expresion resultado = exp.obtenerValor(ent);
            if(resultado.valor instanceof LinkedList){
                list.addAll((LinkedList)resultado.valor);
            }else{
                list.add(resultado);
            }
        }
        return new Literal(Simbolo.EnumTipoDato.LIST,list);
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

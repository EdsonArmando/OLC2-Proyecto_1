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
import java.util.Vector;

/**
 *
 * @author EG
 */
public class Function_C extends Funcion {
    public Function_C(String id,Simbolo.EnumTipoDato tipo){
        super(id, tipo);
    }
    @Override
    public Expresion obtenerValor(Entorno ent) {
       Vector vecto = new Vector();
        for(Expresion exp : param_Actuales){
            Expresion resultado = exp.obtenerValor(ent);
            if(resultado.valor instanceof Vector){
                vecto.addAll((Vector)resultado.valor);
            }else{
                vecto.add(resultado);
            }
        }
        return new Literal(Simbolo.EnumTipoDato.ARREGLO,vecto);
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

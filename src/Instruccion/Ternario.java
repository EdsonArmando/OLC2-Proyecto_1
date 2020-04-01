/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instruccion;

import Entorno.Entorno;
import Entorno.Simbolo;
import Expresion.Expresion;
import java.util.Vector;

/**
 *
 * @author EG
 */
public class Ternario extends Expresion implements Instruccion {
    Expresion condicion;
    Expresion expr1;
    Expresion expr2;
    public Ternario(Expresion condicion, Expresion expr1, Expresion expr2){
        this.condicion = condicion;
        this.expr1 = expr1;
        this.expr2 = expr2;
    }
    @Override
    public Retornar ejecutar(Entorno ent) {
       return new Retornar();
    }

    @Override
    public Expresion obtenerValor(Entorno ent) {
         Expresion resultado = condicion.obtenerValor(ent);
        if(resultado.tipo==Simbolo.EnumTipoDato.VECTOR){
            Vector<Expresion> nuevo = (Vector<Expresion>)resultado.valor;
            Expresion result = nuevo.get(0).obtenerValor(ent);
            if(Boolean.valueOf(result.valor.toString())){
                return expr1.obtenerValor(ent);
            }else{
                return expr2.obtenerValor(ent);
            }
        }else{
            if(Boolean.valueOf(resultado.valor.toString())){
                return expr1.obtenerValor(ent);
            }else{
                return expr2.obtenerValor(ent);
            }
        }
    }

    @Override
    public Simbolo.EnumTipoDato getTipo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

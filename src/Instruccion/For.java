/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instruccion;

import Entorno.Entorno;
import Entorno.Simbolo;
import Expresion.Expresion;
import java.util.LinkedList;

/**
 *
 * @author EG
 */
public class For implements Instruccion {
    Expresion val;
    String id;
    LinkedList<Instruccion> instrucciones;
    public For(String id,Expresion valor,LinkedList<Instruccion> instrucciones){
        this.id = id;
        this.val = valor;
        this.instrucciones = instrucciones;
    }
    @Override
    public Retornar ejecutar(Entorno ent) {
        LinkedList<Expresion> valor=null;
        Expresion resultado = val.obtenerValor(ent);
        if(resultado.tipo==Simbolo.EnumTipoDato.LIST){
            valor = (LinkedList<Expresion>)resultado.valor;
        }
        if(valor!=null){
            for(Expresion i : valor){
                Entorno tablaLocal = new Entorno(ent);
                tablaLocal.insertar(id, new Simbolo(i.tipo,i.obtenerValor(tablaLocal).valor,id));
                for(Instruccion ins : instrucciones){
                    ins.ejecutar(tablaLocal);
                }
            }
        }
        return new Retornar();
    }
    
}

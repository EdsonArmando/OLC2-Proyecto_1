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
import java.util.Vector;

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
        Vector<Expresion> vector=null;
        Expresion resultado = val.obtenerValor(ent);
        if(resultado.tipo==Simbolo.EnumTipoDato.LIST){
            valor = (LinkedList<Expresion>)resultado.valor;
        }else if(resultado.tipo==Simbolo.EnumTipoDato.VECTOR){
            vector = (Vector<Expresion>)resultado.valor;
        }
        if(valor!=null){
            for(Expresion i : valor){
                Entorno tablaLocal = new Entorno(ent);
                tablaLocal.insertar(id, new Simbolo(i.tipo,i.obtenerValor(tablaLocal).valor,id));
                for(Instruccion ins : instrucciones){
                    ins.ejecutar(tablaLocal);
                }
            }
        }else if(vector!=null){
            boolean isBreak=false;
            for(Expresion i : vector){
                if(isBreak){
                    break;
                }
                Entorno tablaLocal = new Entorno(ent);
                if(i==null){
                    tablaLocal.insertar(id, new Simbolo(Simbolo.EnumTipoDato.STRING,"null",id));
                }else{
                    tablaLocal.insertar(id, new Simbolo(i.tipo,i.obtenerValor(tablaLocal).valor,id));
                }
                for(Instruccion ins : instrucciones){
                    Retornar r =ins.ejecutar(tablaLocal);
                    if(r.isBreak){
                        isBreak=true;
                        break;
                    }else if(r.isReturn){
                        return r;
                    }
                }
            }
        }
        return new Retornar();
    }
    
}

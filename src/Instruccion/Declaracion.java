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
public class Declaracion implements Instruccion {
    String id;
    Expresion valor;
    Vector listVar;
    int tamanioVector=0;
    Expresion posValor;
    public Declaracion(String id,Expresion expr){
        this.id = id;
        this.valor = expr;
        this.listVar = new Vector();
        this.listVar.setSize(1);
    }
    public Declaracion(String id,Expresion tamanioVector,Expresion expr){
        this.id = id;
        this.valor = expr;
        this.posValor=tamanioVector;
        //this.listVar = new Object[];
    }
    @Override
    public Retornar ejecutar(Entorno ent) {
        int tamanio=0;
        boolean existe = ent.existeVariable(id,ent);
        Expresion exprValor = valor.obtenerValor(ent);
        if(this.posValor!=null){
            Double val = (Double) this.posValor.obtenerValor(ent).valor;
            this.listVar = new Vector();
            this.listVar.setSize(val.intValue());
            tamanio=val.intValue();
            tamanioVector = tamanio;
        }
        if(tamanioVector==0){
            if(exprValor.tipo==Simbolo.EnumTipoDato.VECTOR){
                listVar = (Vector)exprValor.valor;
            }else if(exprValor.tipo == Simbolo.EnumTipoDato.LIST){      
                ent.insertar(id, new Simbolo(exprValor.tipo,exprValor.valor,id));
                return new Retornar();
            }else if(exprValor.tipo==Simbolo.EnumTipoDato.MATRIX){
                ent.insertar(id, new Simbolo(exprValor.tipo,exprValor.valor,id));
                return new Retornar();
            }else{
                listVar.setElementAt(exprValor,0);
            }
        }else{
                if(existe && tamanio!=0){
                Simbolo sim = ent.obtener(id,ent);
                Vector temp=(Vector)sim.valor;  
                if(tamanio<=temp.size()){
                    temp.setElementAt(exprValor,tamanio-1);
                    if(sim.tipo==Simbolo.EnumTipoDato.VECTOR){
                        ent.modificarVariable(id, new Simbolo(Simbolo.EnumTipoDato.VECTOR,temp,id));
                    }else if(sim.tipo==Simbolo.EnumTipoDato.LIST){
                        ent.modificarVariable(id, new Simbolo(Simbolo.EnumTipoDato.LIST,temp,id));
                    }
                    return new Retornar();
                }    
                for(int i=0;i<temp.size();i++){
                    listVar.setElementAt(temp.get(i),i);
                }
                listVar.setElementAt(exprValor,tamanio-1);
                if(sim.tipo==Simbolo.EnumTipoDato.VECTOR){
                        ent.modificarVariable(id, new Simbolo(Simbolo.EnumTipoDato.VECTOR,listVar,id));
                    }else if(sim.tipo==Simbolo.EnumTipoDato.LIST){
                        ent.modificarVariable(id, new Simbolo(Simbolo.EnumTipoDato.LIST,listVar,id));
                    }   
                return new Retornar();
            }else if(existe){
                ent.modificarVariable(id, new Simbolo(Simbolo.EnumTipoDato.VECTOR,exprValor.valor,id));
                return new Retornar();
            }
            else{
                    listVar.setElementAt(exprValor, tamanio-1);
            }
        }
        ent.insertar(id, new Simbolo(Simbolo.EnumTipoDato.VECTOR,listVar,id));
        return new Retornar();
    }
    
}
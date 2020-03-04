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
        if(this.posValor!=null){
            Double val = (Double) this.posValor.obtenerValor(ent).valor;
            this.listVar = new Vector();
            this.listVar.setSize(val.intValue());
            tamanio=val.intValue();
            tamanioVector = tamanio;
        }
        boolean existe = ent.existeVariable(id,ent);
        Expresion exprValor = valor.obtenerValor(ent);
        if(tamanioVector==0){
            if(exprValor.valor instanceof Vector){
                listVar = (Vector)exprValor.valor;
            }else{
                listVar.setElementAt(exprValor,0);
            }
        }else{
                if(existe){
                Simbolo sim = ent.obtener(id,ent);
                Vector temp=(Vector)sim.valor;  
                if(tamanio<=temp.size()){
                    temp.setElementAt(exprValor,tamanio-1);
                    ent.modificarVariable(id, new Simbolo(exprValor.tipo,temp,id));
                    return new Retornar();
                }    
                for(int i=0;i<temp.size();i++){
                    listVar.setElementAt(temp.get(i),i);
                }
                
                listVar.setElementAt(exprValor,tamanio-1);
                ent.modificarVariable(id, new Simbolo(exprValor.tipo,listVar,id));
                return new Retornar();
            }
        }
        ent.insertar(id, new Simbolo(exprValor.tipo,listVar,id));
        return new Retornar();
    }
    
}
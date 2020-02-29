/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instruccion;

import Entorno.Entorno;
import Entorno.Simbolo;
import Expresion.Expresion;
import Expresion.LlamadaFuncion;
import java.util.Vector;

/**
 *
 * @author EG
 */
public class Declaracion implements Instruccion {
    String id;
    Expresion valor;
    Object listVar[];
    int tamanioVector=0;
    Expresion posValor;
    public Declaracion(String id,Expresion expr){
        this.id = id;
        this.valor = expr;
        this.listVar = new Object[1];
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
            this.listVar = new Object[val.intValue()];
            tamanio=val.intValue();
            tamanioVector = tamanio;
        }
        boolean existe = ent.existeVariable(id,ent);
        Expresion exprValor = valor.obtenerValor(ent);
        if(tamanioVector==0){
            listVar[0] = exprValor;
        }else{
                if(existe){
                Simbolo sim = ent.obtener(id,ent);
                Object temp[]=(Object[])sim.valor;  
                for(int i=0;i<temp.length;i++){
                    listVar[i]=temp[i];
                }
                
                listVar[tamanio-1]=exprValor;
                ent.modificarVariable(id, new Simbolo(exprValor.tipo,listVar,id));
                return new Retornar();
            }
        }
        ent.insertar(id, new Simbolo(exprValor.tipo,listVar,id));
        return new Retornar();
    }
    
}

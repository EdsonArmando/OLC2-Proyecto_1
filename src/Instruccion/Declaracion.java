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
    Object listVar[];
    int tamanioVector=0;
    public Declaracion(String id,Expresion expr){
        this.id = id;
        this.valor = expr;
        this.listVar = new Object[1];
    }
    public Declaracion(String id,int tamanioVector,Expresion expr){
        this.id = id;
        this.valor = expr;
        this.tamanioVector=tamanioVector;
        this.listVar = new Object[tamanioVector];
    }
    @Override
    public Retornar ejecutar(Entorno ent) {
        boolean existe = ent.existeVariable(id);
        Expresion exprValor = valor.obtenerValor(ent);
        if(tamanioVector==0){
            listVar[0] = exprValor;
        }else{
            listVar[tamanioVector-1]=exprValor;
                if(existe){
                Simbolo sim = ent.obtener(id);
                Object temp[]=(Object[])sim.valor;  
                for(int i=0;i<temp.length;i++){
                    listVar[i]=temp[i];
                }
                ent.modificarVariable(id, new Simbolo(exprValor.tipo,listVar,id));
                return new Retornar();
            }
        }
        ent.insertar(id, new Simbolo(exprValor.tipo,listVar,id));
        return new Retornar();
    }
    
}

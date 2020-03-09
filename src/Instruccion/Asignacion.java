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
import Expresion.Operacion;

/**
 *
 * @author EG
 */
public class Asignacion implements Instruccion{
    String id;
    Expresion valor;
    Expresion fila;
    Expresion columna;
    Operacion.Tipo_operacion tipo;
    public Asignacion(String id,Expresion fila,Expresion columna,Expresion valor,Operacion.Tipo_operacion tipo){
        this.id = id;
        this.fila = fila;
        this.columna = columna;
        this.valor = valor;
        this.tipo = tipo;
    }
    @Override
    public Retornar ejecutar(Entorno ent) {
        Simbolo sim = ent.obtener(id, ent);
        if(tipo == Operacion.Tipo_operacion.MODIFICACION_MATRIZ){
            Object[][] tempMatrix = (Object[][])sim.valor;
            Double fila = (Double)this.fila.obtenerValor(ent).valor;
            Double colum = (Double)this.columna.obtenerValor(ent).valor;
            tempMatrix[fila.intValue()-1][colum.intValue()-1]=valor.obtenerValor(ent);
            ent.modificarVariable(id,new Simbolo(Simbolo.EnumTipoDato.MATRIX,tempMatrix,id));
        }else if(tipo == Operacion.Tipo_operacion.MODIFICACION_FILA_MATRIZ){
            Object[][] tempMatrix = (Object[][])sim.valor;
            Double fila = (Double)this.fila.obtenerValor(ent).valor;
            for(int i=0;i<tempMatrix.length;i++){
                if(i==fila.intValue()-1){
                    for(int j=0;j<tempMatrix[i].length;j++){
                        tempMatrix[i][j]=valor.obtenerValor(ent);
                    }
                    ent.modificarVariable(id,new Simbolo(Simbolo.EnumTipoDato.MATRIX,tempMatrix,id));
                    return new Retornar();
                }
            }
        }else if(tipo == Operacion.Tipo_operacion.MODIFICACION_COLUMNA_MATRIZ){
            Object[][] tempMatrix = (Object[][])sim.valor;
            Double colum = (Double)this.columna.obtenerValor(ent).valor;
            for(int i=0;i<tempMatrix[0].length;i++){
                if(i==colum.intValue()-1){
                    for(int j=0;j<tempMatrix.length;j++){
                        tempMatrix[j][i]=valor.obtenerValor(ent);
                    }
                    ent.modificarVariable(id,new Simbolo(Simbolo.EnumTipoDato.MATRIX,tempMatrix,id));
                    return new Retornar();
                }
            }
        }
        return new Retornar();
    }
    
}

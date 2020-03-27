/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Expresion;

import Entorno.Entorno;
import Entorno.Simbolo;
import java.util.Vector;

/**
 *
 * @author EG
 */
public class Estructura extends Expresion{
    String id;
    Expresion fila;
    Expresion columna;
    Operacion.Tipo_operacion tipo;
    Expresion w;
    public Estructura(String id,Expresion fila,Expresion columna,Operacion.Tipo_operacion tipo){
        this.id = id;
        this.fila = fila;
        this.columna = columna;
        this.tipo = tipo;
    }
    public Estructura(String id,Expresion fila,Expresion columna,Expresion w,Operacion.Tipo_operacion tipo){
        this.id = id;
        this.fila = fila;
        this.columna = columna;
        this.w = w;
        this.tipo = tipo;
    }
    public Estructura(String id,Expresion fila,Operacion.Tipo_operacion tipo){
        this.id = id;
        this.fila = fila;
        this.columna = columna;
        this.tipo = tipo;
    }
    @Override
    public Expresion obtenerValor(Entorno ent) {
        Simbolo sim = ent.obtener(id, ent);
        if(tipo == Operacion.Tipo_operacion.IDENTIFICADOR_POS_ARRAY_MATRIX){
            Double fil = (Double)this.fila.obtenerValor(ent).valor;
            Double colum = (Double)this.columna.obtenerValor(ent).valor;
            Object[][] tempMatrix = (Object[][])sim.valor;
            Expresion expr = (Expresion)tempMatrix[fil.intValue()-1][colum.intValue()-1];
            return expr;
        }else if(tipo == Operacion.Tipo_operacion.DEV_FILA){
            Vector vect = new Vector();
            Object[][] tempMatrix = (Object[][])sim.valor;
            Double fil = (Double)this.fila.obtenerValor(ent).valor;
            for(int i=0;i<tempMatrix[fil.intValue()].length;i++){
                vect.add(tempMatrix[fil.intValue()-1][i]);
            }
            return new Literal(Simbolo.EnumTipoDato.VECTOR,vect);
        }else if(tipo == Operacion.Tipo_operacion.DEV_COLUMNA){
            Vector vect = new Vector();
            Object[][] tempMatrix = (Object[][])sim.valor;
            Double col = (Double)this.fila.obtenerValor(ent).valor;
            for(int i=0;i<tempMatrix.length;i++){
                vect.add(tempMatrix[i][col.intValue()-1]);
            }
            return new Literal(Simbolo.EnumTipoDato.VECTOR,vect);
        }else if(tipo == Operacion.Tipo_operacion.ARRAY_3){
            Object[] vec = (Object[])sim.valor;
            Double posicion_total= (((Double)fila.obtenerValor(ent).valor)+(sim.tamanioY)*((Double)columna.obtenerValor(ent).valor-1))+(sim.tamanioX)*(sim.tamanioY)*((Double)w.obtenerValor(ent).valor - 1);
            return (Expresion)vec[posicion_total.intValue()];
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Simbolo.EnumTipoDato getTipo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

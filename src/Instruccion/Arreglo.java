/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instruccion;

import Entorno.Entorno;
import Entorno.Simbolo;
import Entorno.Simbolo.EnumTipoDato;
import Expresion.Expresion;
import Expresion.Literal;
import Views.Inicio;
import java.util.Vector;

/**
 *
 * @author EG
 */
public class Arreglo extends Funcion{

    public Arreglo(String id, Simbolo.EnumTipoDato tipo) {
        super(id, tipo);
    }

    @Override
    public Expresion obtenerValor(Entorno ent) {
        int cont=0;
        Object[] temp;
        Expresion valor=param_Actuales.get(0).obtenerValor(ent);
        Vector<Expresion> dimensiones=(Vector)param_Actuales.get(1).obtenerValor(ent).valor;
        Double tamanioVector=1.0;
        for(Expresion expr : dimensiones){
            tamanioVector*=(Double)expr.obtenerValor(ent).valor;
        }
        temp = new Object[tamanioVector.intValue()];
        if(valor.tipo == EnumTipoDato.VECTOR){
            Vector<Expresion> temp1 = (Vector)valor.valor;
            int pos=0;
            while(cont != temp.length){
                if(pos<temp1.size()){
                    temp[cont]=temp1.get(pos);
                }else{
                    temp[cont]=temp1.get(pos-temp1.size());
                    pos=0;
                }
                cont++;
                pos++;
            }
        }
        return new Literal(EnumTipoDato.ARREGLO,temp,(Double)dimensiones.get(0).obtenerValor(ent).valor,(Double)dimensiones.get(1).obtenerValor(ent).valor,(Double)dimensiones.get(2).obtenerValor(ent).valor);
    }

    @Override
    public Simbolo.EnumTipoDato getTipo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retornar ejecutar(Entorno ent) {
        Inicio.salidaConsola.append(id);
        return new Retornar();
    }
    
}

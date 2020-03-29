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
import Views.Inicio;
import java.util.Vector;

/**
 *
 * @author EG
 */
public class Mean extends Funcion {

    public Mean(String id, Simbolo.EnumTipoDato tipo) {
        super(id, tipo);
    }

    @Override
    public Expresion obtenerValor(Entorno ent) {
        if(param_Actuales.size() == 1 && param_Actuales.get(0).obtenerValor(ent).tipo == Simbolo.EnumTipoDato.VECTOR){
            return promedio((Vector<Expresion>)param_Actuales.get(0).obtenerValor(ent).valor,ent,0.0);
        }else if(param_Actuales.size() == 2 && param_Actuales.get(0).obtenerValor(ent).tipo == Simbolo.EnumTipoDato.VECTOR){
            return promedio((Vector<Expresion>)param_Actuales.get(0).obtenerValor(ent).valor,ent,(Double)param_Actuales.get(1).obtenerValor(ent).valor);
        }else{
            Inicio.salidaConsola.append("Existio un error al calcular la media \n");
            return null;
        }
    }

    @Override
    public Simbolo.EnumTipoDato getTipo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retornar ejecutar(Entorno ent) {
        if(param_Actuales.size() == 1 && param_Actuales.get(0).obtenerValor(ent).tipo == Simbolo.EnumTipoDato.VECTOR){
            Literal lit =  (Literal)promedio((Vector<Expresion>)param_Actuales.get(0).obtenerValor(ent).valor,ent,0.0);
            Inicio.salidaConsola.append(String.valueOf(lit.valor)+"\n");
            return new Retornar();
        }else if(param_Actuales.size() == 2 && param_Actuales.get(0).obtenerValor(ent).tipo == Simbolo.EnumTipoDato.VECTOR){
            Literal lit = (Literal) promedio((Vector<Expresion>)param_Actuales.get(0).obtenerValor(ent).valor,ent,(Double)param_Actuales.get(1).obtenerValor(ent).valor);
            Inicio.salidaConsola.append(String.valueOf(lit.valor)+"\n");
            return new Retornar();
        }else{
            Inicio.salidaConsola.append("Existio un error al calcular la media \n");
            return new Retornar();
        }
       
    }
    
    public  Expresion promedio( Vector<Expresion> v ,Entorno ent,double trim) {
        double prom = 0.0;
        if(trim ==0.0){
            for ( int i = 0; i < v.size(); i++ ){
                prom += (Double)v.get(i).obtenerValor(ent).valor;
            }
            return new Literal(Simbolo.EnumTipoDato.INT,prom / ( double ) v.size());  
        }else{
            int cont=0;
            for ( int i = 0; i < v.size(); i++ ){
                if((Double)v.get(i).obtenerValor(ent).valor < trim){
                    cont++;
                }else{                    
                    prom += (Double)v.get(i).obtenerValor(ent).valor;
                }
            }
            return new Literal(Simbolo.EnumTipoDato.INT,prom / (( double ) v.size()-cont));  
        }
    }
}   

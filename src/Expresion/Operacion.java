/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Expresion;

import Entorno.Entorno;
import Entorno.Simbolo;
import Entorno.Simbolo.EnumTipoDato;
import Instruccion.Retornar;
import Views.Inicio;
import java.util.Vector;

/**
 *
 * @author EG
 */
public class Operacion extends Expresion{

    public static enum Tipo_operacion{
        SUMA,
        RESTA,
        MULTIPLICACION,
        DIVISION,
        NEGATIVO,
        NUMERO,
        IDENTIFICADOR,
        CADENA,
        MAYOR_QUE,
        MENOR_QUE,
        MAYOR_IGUAL_QUE,
        MENOR_IGUAL_QUE,
        IDENTIFICADOR_POS_ARRAY,
        DIFERENTE_QUE,
        IGUAL_QUE,
        NOT,
        AND,
        XOR,
        MODULO,
        OR,
        TRUE,
        FALSE,
        POTENCIA,
        CONCATENACION,
        ARRAYPOSICION
    }
     private Object vector[];
     private final Tipo_operacion tipo;
     public Expresion operadorIzq;
     public Expresion operadorDer;
     private Expresion posVariable;
     public Object valor;
     public Operacion(Expresion operadorIzq, Expresion operadorDer, Tipo_operacion tipo) {
        this.tipo = tipo;
        this.operadorIzq = operadorIzq;
        this.operadorDer = operadorDer;
    }
    public Operacion(Expresion operadorIzq, Tipo_operacion tipo) {
        this.tipo = tipo;
        this.operadorIzq = operadorIzq;
    }
    
    public Operacion(String a, Tipo_operacion tipo) {
        this.valor=a;
        this.tipo = tipo;
    }
    public Operacion(String a, Tipo_operacion tipo,Expresion pos) {
        this.valor=a;
        this.tipo = tipo;
        this.posVariable = pos;
    }
    public Operacion(Double a) {
        this.valor=a;
        this.tipo = Tipo_operacion.NUMERO;
    }
    
    @Override
    public Expresion obtenerValor(Entorno ent) {
         /* ======== OPERACIONES ARITMETICAS ======== */
        if(tipo== Tipo_operacion.DIVISION){
            Literal exprUno = (Literal)operadorIzq.obtenerValor(ent);
            Literal exprDos = (Literal)operadorDer.obtenerValor(ent);
            return new Literal(Simbolo.EnumTipoDato.INT,(Double)exprUno.valor / (Double)exprDos.valor);
        }else if(tipo== Tipo_operacion.MULTIPLICACION){
            Literal exprUno = (Literal)operadorIzq.obtenerValor(ent);
            Literal exprDos = (Literal)operadorDer.obtenerValor(ent);
            return new Literal(Simbolo.EnumTipoDato.INT,(Double)exprUno.valor * (Double)exprDos.valor);
        }else if(tipo== Tipo_operacion.MODULO){
            Literal exprUno = (Literal)operadorIzq.obtenerValor(ent);
            Literal exprDos = (Literal)operadorDer.obtenerValor(ent);
            return new Literal(Simbolo.EnumTipoDato.INT,(Double)exprUno.valor % (Double)exprDos.valor);
        }else if(tipo== Tipo_operacion.RESTA){
            Literal exprUno = (Literal)operadorIzq.obtenerValor(ent);
            Literal exprDos = (Literal)operadorDer.obtenerValor(ent);
            return new Literal(Simbolo.EnumTipoDato.INT,(Double)exprUno.valor - (Double)exprDos.valor);
        }else if(tipo== Tipo_operacion.SUMA){
            Literal exprUno = (Literal)operadorIzq.obtenerValor(ent);
            Literal exprDos = (Literal)operadorDer.obtenerValor(ent);
            if(exprUno.tipo==EnumTipoDato.INT && exprDos.tipo==EnumTipoDato.INT){
                return new Literal(Simbolo.EnumTipoDato.INT,(Double)exprUno.valor + (Double)exprDos.valor);
            }else{
                return new Literal(Simbolo.EnumTipoDato.STRING,exprUno.valor.toString() + exprDos.valor.toString());
            }
            
        }else if(tipo== Tipo_operacion.NEGATIVO){
            Literal exprUno = (Literal)operadorIzq.obtenerValor(ent);
            return new Literal(Simbolo.EnumTipoDato.INT,(Double)exprUno.valor *-1);
        
        }
         /* ======== OPERACIONES UNARIOS ======== */
        else if(tipo == Tipo_operacion.NUMERO){
            return new Literal(Simbolo.EnumTipoDato.INT,new Double(valor.toString()));
        }else if(tipo == Tipo_operacion.IDENTIFICADOR){
            Simbolo sim = ent.obtener(valor.toString(),ent);
            Expresion temp;
            String cadena="";
            if(sim==null){return null;};
            vector = (Object[])sim.valor;
            for(int i=0;i<vector.length;i++){
           temp=(Expresion)vector[i];
           if(temp==null){
               cadena+=",null,";
           }else{
               cadena+=temp.obtenerValor(ent).valor.toString() + " ";
           }
       }
            return new Literal(Simbolo.EnumTipoDato.ARREGLO,cadena);
        }else if(tipo == Tipo_operacion.IDENTIFICADOR_POS_ARRAY){
            Simbolo sim = ent.obtener(valor.toString(),ent);
            vector = (Object[])sim.valor;
            if(vector.length==1){
                return (Expresion)vector[0];
            }else{
                Double uno = (Double)posVariable.obtenerValor(ent).valor;
                return (Expresion)vector[uno.intValue()-1];
            }
        }else if(tipo == Tipo_operacion.CADENA){
            return new Literal(Simbolo.EnumTipoDato.STRING,valor.toString());
        }
        /* ======== OPERACIONES RELACIONALES ======== */
        else if(tipo== Tipo_operacion.MAYOR_QUE){
            Literal exprUno = (Literal)operadorIzq.obtenerValor(ent);
            Literal exprDos = (Literal)operadorDer.obtenerValor(ent);
            return new Literal(Simbolo.EnumTipoDato.BOOLEAN,Double.valueOf(exprUno.valor.toString()).doubleValue() > Double.valueOf(exprDos.valor.toString()).doubleValue());
        }else if(tipo== Tipo_operacion.MENOR_QUE){
            Literal exprUno = (Literal)operadorIzq.obtenerValor(ent);
            Literal exprDos = (Literal)operadorDer.obtenerValor(ent);
            return new Literal(Simbolo.EnumTipoDato.BOOLEAN,Double.valueOf(exprUno.valor.toString()).doubleValue() < Double.valueOf(exprDos.valor.toString()).doubleValue());
        }else if(tipo== Tipo_operacion.MAYOR_IGUAL_QUE){
            Literal exprUno = (Literal)operadorIzq.obtenerValor(ent);
            Literal exprDos = (Literal)operadorDer.obtenerValor(ent);
            return new Literal(Simbolo.EnumTipoDato.BOOLEAN,Double.valueOf(exprUno.valor.toString()).doubleValue() >= Double.valueOf(exprDos.valor.toString()).doubleValue());
        }else if(tipo== Tipo_operacion.MENOR_IGUAL_QUE){
            Literal exprUno = (Literal)operadorIzq.obtenerValor(ent);
            Literal exprDos = (Literal)operadorDer.obtenerValor(ent);
            return new Literal(Simbolo.EnumTipoDato.BOOLEAN,Double.valueOf(exprUno.valor.toString()).doubleValue() <= Double.valueOf(exprDos.valor.toString()).doubleValue());
        }else if(tipo== Tipo_operacion.IGUAL_QUE){
            Literal exprUno = (Literal)operadorIzq.obtenerValor(ent);
            Literal exprDos = (Literal)operadorDer.obtenerValor(ent);
            return new Literal(Simbolo.EnumTipoDato.BOOLEAN,Double.valueOf(exprUno.valor.toString()).doubleValue() == Double.valueOf(exprDos.valor.toString()).doubleValue());
        }else if(tipo== Tipo_operacion.DIFERENTE_QUE){
            Literal exprUno = (Literal)operadorIzq.obtenerValor(ent);
            Literal exprDos = (Literal)operadorDer.obtenerValor(ent);
            return new Literal(Simbolo.EnumTipoDato.BOOLEAN,Double.valueOf(exprUno.valor.toString()).doubleValue() != Double.valueOf(exprDos.valor.toString()).doubleValue());
        }else if(tipo== Tipo_operacion.AND){
            Literal exprUno = (Literal)operadorIzq.obtenerValor(ent);
            Literal exprDos = (Literal)operadorDer.obtenerValor(ent);
            return new Literal(Simbolo.EnumTipoDato.BOOLEAN,Boolean.valueOf(exprUno.valor.toString()) && Boolean.valueOf(exprDos.valor.toString()));
        }else if(tipo== Tipo_operacion.OR){
            Literal exprUno = (Literal)operadorIzq.obtenerValor(ent);
            Literal exprDos = (Literal)operadorDer.obtenerValor(ent);
            return new Literal(Simbolo.EnumTipoDato.BOOLEAN,Boolean.valueOf(exprUno.valor.toString()) | Boolean.valueOf(exprDos.valor.toString()));
        }else if(tipo== Tipo_operacion.XOR){
            Literal exprUno = (Literal)operadorIzq.obtenerValor(ent);
            Literal exprDos = (Literal)operadorDer.obtenerValor(ent);
            return new Literal(Simbolo.EnumTipoDato.BOOLEAN,Boolean.valueOf(exprUno.valor.toString()) ^ Boolean.valueOf(exprDos.valor.toString()));
        }else if(tipo== Tipo_operacion.TRUE){
            return new Literal(Simbolo.EnumTipoDato.BOOLEAN,true);
        }else if(tipo== Tipo_operacion.FALSE){
            return new Literal(Simbolo.EnumTipoDato.BOOLEAN,false);
        }else if(tipo== Tipo_operacion.CONCATENACION){
            Literal exprUno = (Literal)operadorIzq.obtenerValor(ent);
            Literal exprDos = (Literal)operadorDer.obtenerValor(ent);
            return new Literal(Simbolo.EnumTipoDato.INT,exprUno.valor.toString() + exprDos.valor.toString());
        }else{
            return null;
        }
    }
    public Tipo_operacion getTipoOp(){
        return this.tipo;
    }
    @Override
    public Simbolo.EnumTipoDato getTipo() {
        return null;
    }
     
}

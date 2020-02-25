/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Expresion;

import Entorno.Entorno;
import Entorno.Simbolo;
import Entorno.Simbolo.EnumTipoDato;

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
        DIFERENTE_QUE,
        IGUAL_QUE,
        NOT,
        AND,
        XOR,
        OR,
        TRUE,
        FALSE,
        POTENCIA,
        CONCATENACION,
        ARRAYPOSICION
    }
     private final Tipo_operacion tipo;
     public Expresion operadorIzq;
     public Expresion operadorDer;
     private Literal exprUno;
     private Literal exprDos;
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
    public Operacion(Double a) {
        this.valor=a;
        this.tipo = Tipo_operacion.NUMERO;
    }
    
    @Override
    public Expresion obtenerValor(Entorno ent) {
         /* ======== OPERACIONES ARITMETICAS ======== */
        if(tipo== Tipo_operacion.DIVISION){
            exprUno = (Literal)operadorIzq.obtenerValor(ent);
            exprDos = (Literal)operadorDer.obtenerValor(ent);
            return new Literal(Simbolo.EnumTipoDato.INT,(Double)exprUno.valor / (Double)exprDos.valor);
        }else if(tipo== Tipo_operacion.MULTIPLICACION){
            exprUno = (Literal)operadorIzq.obtenerValor(ent);
            exprDos = (Literal)operadorDer.obtenerValor(ent);
            return new Literal(Simbolo.EnumTipoDato.INT,(Double)exprUno.valor * (Double)exprDos.valor);
        }else if(tipo== Tipo_operacion.RESTA){
            exprUno = (Literal)operadorIzq.obtenerValor(ent);
            exprDos = (Literal)operadorDer.obtenerValor(ent);
            return new Literal(Simbolo.EnumTipoDato.INT,(Double)exprUno.valor - (Double)exprDos.valor);
        }else if(tipo== Tipo_operacion.SUMA){
            exprUno = (Literal)operadorIzq.obtenerValor(ent);
            exprDos = (Literal)operadorDer.obtenerValor(ent);
            if(exprUno.tipo==EnumTipoDato.INT && exprDos.tipo==EnumTipoDato.INT){
                return new Literal(Simbolo.EnumTipoDato.INT,(Double)exprUno.valor + (Double)exprDos.valor);
            }else{
                return new Literal(Simbolo.EnumTipoDato.STRING,exprUno.valor.toString() + exprDos.valor.toString());
            }
            
        }else if(tipo== Tipo_operacion.NEGATIVO){
            exprUno = (Literal)operadorIzq.obtenerValor(ent);
            return new Literal(Simbolo.EnumTipoDato.INT,(Double)exprUno.valor *-1);
        
        }
         /* ======== OPERACIONES UNARIOS ======== */
        else if(tipo == Tipo_operacion.NUMERO){
            return new Literal(Simbolo.EnumTipoDato.INT,new Double(valor.toString()));
        }else if(tipo == Tipo_operacion.IDENTIFICADOR){
            Simbolo sim = ent.obtener(valor.toString());
            return (Expresion)sim.valor;
        }else if(tipo == Tipo_operacion.CADENA){
            return new Literal(Simbolo.EnumTipoDato.STRING,valor.toString());
        }
        /* ======== OPERACIONES RELACIONALES ======== */
        else if(tipo== Tipo_operacion.MAYOR_QUE){
            exprUno = (Literal)operadorIzq.obtenerValor(ent);
            exprDos = (Literal)operadorDer.obtenerValor(ent);
            return new Literal(Simbolo.EnumTipoDato.INT,(Double)exprUno.valor > (Double)exprDos.valor);
        }else if(tipo== Tipo_operacion.MENOR_QUE){
            exprUno = (Literal)operadorIzq.obtenerValor(ent);
            exprDos = (Literal)operadorDer.obtenerValor(ent);
            return new Literal(Simbolo.EnumTipoDato.INT,(Double)exprUno.valor < (Double)exprDos.valor);
        }else if(tipo== Tipo_operacion.CONCATENACION){
            exprUno = (Literal)operadorIzq.obtenerValor(ent);
            exprDos = (Literal)operadorDer.obtenerValor(ent);
            return new Literal(Simbolo.EnumTipoDato.INT,exprUno.valor.toString() + exprDos.valor.toString());
        }else{
            return null;
        }
    }
    @Override
    public Simbolo.EnumTipoDato getTipo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
}

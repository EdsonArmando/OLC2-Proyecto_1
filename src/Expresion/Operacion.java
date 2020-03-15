/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Expresion;

import Entorno.Entorno;
import Entorno.Simbolo;
import Entorno.Simbolo.EnumTipoDato;
import Estructuras.NodoAST;
import Views.Inicio;
import java.util.LinkedList;
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
        IDENTIFICADOR_POS_ARRAY_MATRIX,
        DEV_FILA,
        DEV_COLUMNA,
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
        MODIFICACION_FILA_MATRIZ,
        MODIFICACION_COLUMNA_MATRIZ,
        MODIFICACION_MATRIZ,
        ARRAYPOSICION
    }
     private Vector vector;
     public NodoAST hijo;
     private final Tipo_operacion tipo;
     public  Expresion operadorIzq;
     public  Expresion operadorDer;
     private Expresion posVariable;
     private Expresion posFila;
     private Expresion posColumna;
     public  Object valor;
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
     public Operacion(String a, Tipo_operacion tipo,Expresion posx,Expresion posy) {
        this.valor=a;
        this.tipo = tipo;
        this.posFila = posx;
        this.posColumna = posx;
    }
    public Operacion(Double a) {
        this.valor=a;
        this.tipo = Tipo_operacion.NUMERO;
    }
    
    @Override
    public Expresion obtenerValor(Entorno ent) {
        Vector<Expresion> nuevo;
         /* ======== OPERACIONES ARITMETICAS ======== */
        if(tipo== Tipo_operacion.DIVISION){
            Literal exprUno = (Literal)operadorIzq.obtenerValor(ent);
            Literal exprDos = (Literal)operadorDer.obtenerValor(ent);
            if(exprUno.tipo==EnumTipoDato.VECTOR && exprDos.tipo==EnumTipoDato.INT){
            int cont=0;    
            Vector<Expresion> temp = (Vector)exprUno.valor;
            for(Expresion exp:temp){
                exp = new Literal(Simbolo.EnumTipoDato.INT,(Double)exp.valor/(Double)exprDos.valor);
                temp.setElementAt(exp, cont);
                cont++;
            }
            return new Literal (Simbolo.EnumTipoDato.VECTOR,temp);
            }else if(exprUno.tipo==EnumTipoDato.INT && exprDos.tipo==EnumTipoDato.VECTOR){
                int cont=0;
                Vector<Expresion> temp = (Vector)exprDos.valor;
                for(Expresion exp:temp){
                    exp = new Literal(Simbolo.EnumTipoDato.INT,(Double)exp.valor/(Double)exprUno.valor);
                    temp.setElementAt(exp, cont);
                    cont++;
                }
                return new Literal (Simbolo.EnumTipoDato.VECTOR,temp);
            }else if(exprUno.tipo==EnumTipoDato.VECTOR && exprDos.tipo==EnumTipoDato.VECTOR){
                int cont=0;
                Vector<Expresion> temp1 = (Vector)exprUno.valor;
                Vector<Expresion> temp2 = (Vector)exprDos.valor;
                Vector<Expresion> nuevo2 = new Vector();
                nuevo2.setSize(temp1.size());
                for(int i=0;i<temp2.size();i++){
                    nuevo2.setElementAt(new Literal(Simbolo.EnumTipoDato.INT,(Double)temp1.get(i).obtenerValor(ent).valor / (Double)temp2.get(i).obtenerValor(ent).valor), i);
                }
                
                return new Literal (Simbolo.EnumTipoDato.VECTOR,nuevo2);
            }
            return new Literal(Simbolo.EnumTipoDato.INT,(Double)exprUno.valor / (Double)exprDos.valor);
        }else if(tipo== Tipo_operacion.MULTIPLICACION){
            Literal exprUno = (Literal)operadorIzq.obtenerValor(ent);
            Literal exprDos = (Literal)operadorDer.obtenerValor(ent);
            if(exprUno.tipo==EnumTipoDato.VECTOR && exprDos.tipo==EnumTipoDato.INT){
            int cont=0;    
            Vector<Expresion> temp = (Vector)exprUno.valor;
            for(Expresion exp:temp){
                exp = new Literal(Simbolo.EnumTipoDato.INT,(Double)exp.valor*(Double)exprDos.valor);
                temp.setElementAt(exp, cont);
                cont++;
            }
            return new Literal (Simbolo.EnumTipoDato.VECTOR,temp);
            }else if(exprUno.tipo==EnumTipoDato.INT && exprDos.tipo==EnumTipoDato.VECTOR){
                int cont=0;
                Vector<Expresion> temp = (Vector)exprDos.valor;
                for(Expresion exp:temp){
                    exp = new Literal(Simbolo.EnumTipoDato.INT,(Double)exp.valor*(Double)exprUno.valor);
                    temp.setElementAt(exp, cont);
                    cont++;
                }
                return new Literal (Simbolo.EnumTipoDato.VECTOR,temp);
            }else if(exprUno.tipo==EnumTipoDato.VECTOR && exprDos.tipo==EnumTipoDato.VECTOR){
                int cont=0;
                Vector<Expresion> temp1 = (Vector)exprUno.valor;
                Vector<Expresion> temp2 = (Vector)exprDos.valor;
                Vector<Expresion> nuevo2 = new Vector();
                nuevo2.setSize(temp1.size());
                for(int i=0;i<temp2.size();i++){
                    nuevo2.setElementAt(new Literal(Simbolo.EnumTipoDato.INT,(Double)temp1.get(i).obtenerValor(ent).valor * (Double)temp2.get(i).obtenerValor(ent).valor), i);
                }
                
                return new Literal (Simbolo.EnumTipoDato.VECTOR,nuevo2);
            }
            return new Literal(Simbolo.EnumTipoDato.INT,(Double)exprUno.valor * (Double)exprDos.valor);
        }else if(tipo== Tipo_operacion.MODULO){
            Literal exprUno = (Literal)operadorIzq.obtenerValor(ent);
            Literal exprDos = (Literal)operadorDer.obtenerValor(ent);
            return new Literal(Simbolo.EnumTipoDato.INT,(Double)exprUno.valor % (Double)exprDos.valor);
        }else if(tipo== Tipo_operacion.RESTA){
            Literal exprUno = (Literal)operadorIzq.obtenerValor(ent);
            Literal exprDos = (Literal)operadorDer.obtenerValor(ent);
            if(exprUno.tipo==EnumTipoDato.VECTOR && exprDos.tipo==EnumTipoDato.INT){
            int cont=0;    
            Vector<Expresion> temp = (Vector)exprUno.valor;
            for(Expresion exp:temp){
                exp = new Literal(Simbolo.EnumTipoDato.INT,(Double)exp.valor-(Double)exprDos.valor);
                temp.setElementAt(exp, cont);
                cont++;
            }
            return new Literal (Simbolo.EnumTipoDato.VECTOR,temp);
            }else if(exprUno.tipo==EnumTipoDato.INT && exprDos.tipo==EnumTipoDato.VECTOR){
                int cont=0;
                Vector<Expresion> temp = (Vector)exprDos.valor;
                for(Expresion exp:temp){
                    exp = new Literal(Simbolo.EnumTipoDato.INT,(Double)exp.valor-(Double)exprUno.valor);
                    temp.setElementAt(exp, cont);
                    cont++;
                }
                return new Literal (Simbolo.EnumTipoDato.VECTOR,temp);
            }else if(exprUno.tipo==EnumTipoDato.VECTOR && exprDos.tipo==EnumTipoDato.VECTOR){
                int cont=0;
                Vector<Expresion> temp1 = (Vector)exprUno.valor;
                Vector<Expresion> temp2 = (Vector)exprDos.valor;
                Vector<Expresion> nuevo2 = new Vector();
                nuevo2.setSize(temp1.size());
                for(int i=0;i<temp2.size();i++){
                    nuevo2.setElementAt(new Literal(Simbolo.EnumTipoDato.INT,(Double)temp1.get(i).obtenerValor(ent).valor - (Double)temp2.get(i).obtenerValor(ent).valor), i);
                }
                
                return new Literal (Simbolo.EnumTipoDato.VECTOR,nuevo2);
            }
            return new Literal(Simbolo.EnumTipoDato.INT,(Double)exprUno.valor - (Double)exprDos.valor);
        }else if(tipo== Tipo_operacion.SUMA){
            Literal exprUno = (Literal)operadorIzq.obtenerValor(ent);
            Literal exprDos = (Literal)operadorDer.obtenerValor(ent);
            if(exprUno.tipo==EnumTipoDato.VECTOR && exprDos.tipo==EnumTipoDato.INT){
            int cont=0;    
            Vector<Expresion> temp = (Vector)exprUno.valor;
            for(Expresion exp:temp){
                exp = new Literal(Simbolo.EnumTipoDato.INT,(Double)exp.valor+(Double)exprDos.valor);
                temp.setElementAt(exp, cont);
                cont++;
            }
            return new Literal (Simbolo.EnumTipoDato.VECTOR,temp);
            }else if(exprUno.tipo==EnumTipoDato.INT && exprDos.tipo==EnumTipoDato.VECTOR){
                int cont=0;
                Vector<Expresion> temp = (Vector)exprDos.valor;
                for(Expresion exp:temp){
                    exp = new Literal(Simbolo.EnumTipoDato.INT,(Double)exp.valor+(Double)exprUno.valor);
                    temp.setElementAt(exp, cont);
                    cont++;
                }
                return new Literal (Simbolo.EnumTipoDato.VECTOR,temp);
            }else if(exprUno.tipo==EnumTipoDato.VECTOR && exprDos.tipo==EnumTipoDato.VECTOR){
                int cont=0;
                Vector<Expresion> temp1 = (Vector)exprUno.valor;
                Vector<Expresion> temp2 = (Vector)exprDos.valor;
                Vector<Expresion> nuevo2 = new Vector();
                nuevo2.setSize(temp1.size());
                for(int i=0;i<temp2.size();i++){
                    nuevo2.setElementAt(new Literal(Simbolo.EnumTipoDato.INT,(Double)temp1.get(i).obtenerValor(ent).valor + (Double)temp2.get(i).obtenerValor(ent).valor), i);
                }
                
                return new Literal (Simbolo.EnumTipoDato.VECTOR,nuevo2);
            }
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
            if(sim==null){return null;};
            if(sim.tipo==Simbolo.EnumTipoDato.VECTOR){
                vector = (Vector)sim.valor;
                return new Literal(Simbolo.EnumTipoDato.VECTOR,vector);
            }else if(sim.tipo==Simbolo.EnumTipoDato.LIST){
                LinkedList lista = (LinkedList) sim.valor;
                return new Literal(Simbolo.EnumTipoDato.LIST,lista);
            }else if(sim.tipo==Simbolo.EnumTipoDato.MATRIX){
                return new Literal(Simbolo.EnumTipoDato.MATRIX,sim.valor);
            }
            return null;
             /* ======== Dev POSICION ARRAY,MATRIZ,LISTA ======== */
        }else if(tipo == Tipo_operacion.IDENTIFICADOR_POS_ARRAY){
            Simbolo sim = ent.obtener(valor.toString(),ent);
            if(sim.tipo==Simbolo.EnumTipoDato.VECTOR)
            {
                vector = (Vector)sim.valor;
                if(vector.size()==1){
                    return (Expresion)vector.get(0);
                }else{
                    Double uno = (Double)posVariable.obtenerValor(ent).valor;
                    if(uno.intValue()>vector.size()){
                        return new Literal(Simbolo.EnumTipoDato.ERROR,null);
                    }
                    return (Expresion)vector.get(uno.intValue()-1);
                }
            }else if(sim.tipo == Simbolo.EnumTipoDato.LIST){
                LinkedList lista = (LinkedList)sim.valor;
                LinkedList temp = new LinkedList();
                Double uno = (Double)posVariable.obtenerValor(ent).valor;
                
                if(lista.size()==1){
                    return (Expresion)lista.get(0);
                }else{
                    return (Expresion)lista.get(uno.intValue()-1);
                }
            }else if(sim.tipo == Simbolo.EnumTipoDato.MATRIX){
                Object[][] matrix = (Object[][])sim.valor;
                Double pos = (Double)posVariable.obtenerValor(ent).valor;
                int cont=0;
                for(int i=0;i<matrix[0].length;i++){
                    for(int j=0;j<matrix.length;j++){
                        if(cont==pos.intValue()-1){
                            return (Expresion)matrix[j][i];
                        }
                        cont++;
                    }
                }
                return null;
            }
           return null;
        }else if(tipo == Tipo_operacion.CADENA){
            return new Literal(Simbolo.EnumTipoDato.STRING,valor.toString());
        }
        /* ======== OPERACIONES RELACIONALES ======== */
        else if(tipo== Tipo_operacion.MAYOR_QUE){
            Literal exprUno = (Literal)operadorIzq.obtenerValor(ent);
            Literal exprDos = (Literal)operadorDer.obtenerValor(ent);
            if(exprUno.tipo==EnumTipoDato.VECTOR && exprDos.tipo==EnumTipoDato.INT){
            int cont=0;    
            Vector<Expresion> temp = (Vector)exprUno.valor;
            nuevo = new Vector();
            nuevo.setSize(temp.size());
            for(Expresion exp:temp){
                nuevo.setElementAt(new Literal(Simbolo.EnumTipoDato.BOOLEAN,Double.valueOf(exp.valor.toString()).doubleValue() > Double.valueOf(exprDos.valor.toString()).doubleValue()), cont);
                cont++;
            }
            return new Literal (Simbolo.EnumTipoDato.VECTOR,nuevo);
            }else if(exprUno.tipo==EnumTipoDato.INT && exprDos.tipo==EnumTipoDato.VECTOR){
                int cont=0;
                Vector<Expresion> temp = (Vector)exprDos.valor;
                nuevo = new Vector();
                nuevo.setSize(temp.size());
                for(Expresion exp:temp){
                    nuevo.setElementAt(new Literal(Simbolo.EnumTipoDato.BOOLEAN,Double.valueOf(exp.valor.toString()).doubleValue()>Double.valueOf(exprUno.valor.toString()).doubleValue()), cont);
                    cont++;
                }
                return new Literal (Simbolo.EnumTipoDato.VECTOR,nuevo);
            }else if(exprUno.tipo==EnumTipoDato.VECTOR && exprDos.tipo==EnumTipoDato.VECTOR){
                int cont=0;
                Vector<Expresion> temp1 = (Vector)exprUno.valor;
                Vector<Expresion> temp2 = (Vector)exprDos.valor;
                Vector<Expresion> nuevo2 = new Vector();
                nuevo2.setSize(temp1.size());
                for(int i=0;i<temp2.size();i++){
                    nuevo2.setElementAt(new Literal(Simbolo.EnumTipoDato.BOOLEAN,(Double)temp1.get(i).obtenerValor(ent).valor > (Double)temp2.get(i).obtenerValor(ent).valor), i);
                }
                
                return new Literal (Simbolo.EnumTipoDato.VECTOR,nuevo2);
            }
            return new Literal(Simbolo.EnumTipoDato.BOOLEAN,Double.valueOf(exprUno.valor.toString()).doubleValue() > Double.valueOf(exprDos.valor.toString()).doubleValue());
        }else if(tipo== Tipo_operacion.MENOR_QUE){
            Literal exprUno = (Literal)operadorIzq.obtenerValor(ent);
            Literal exprDos = (Literal)operadorDer.obtenerValor(ent);
            if(exprUno.tipo==EnumTipoDato.VECTOR && exprDos.tipo==EnumTipoDato.INT){
            int cont=0;    
            Vector<Expresion> temp = (Vector)exprUno.valor;
            nuevo = new Vector();
            nuevo.setSize(temp.size());
            for(Expresion exp:temp){
                nuevo.setElementAt(new Literal(Simbolo.EnumTipoDato.BOOLEAN,Double.valueOf(exp.valor.toString()).doubleValue()<Double.valueOf(exprDos.valor.toString()).doubleValue()), cont);
                cont++;
            }
            return new Literal (Simbolo.EnumTipoDato.VECTOR,nuevo);
            }else if(exprUno.tipo==EnumTipoDato.INT && exprDos.tipo==EnumTipoDato.VECTOR){
                int cont=0;
                Vector<Expresion> temp = (Vector)exprDos.valor;
                nuevo = new Vector();
                nuevo.setSize(temp.size()); 
                for(Expresion exp:temp){
                    nuevo.setElementAt(new Literal(Simbolo.EnumTipoDato.BOOLEAN,Double.valueOf(exp.valor.toString()).doubleValue()<Double.valueOf(exprDos.valor.toString()).doubleValue()), cont);
                    cont++;
                }
                return new Literal (Simbolo.EnumTipoDato.VECTOR,nuevo);
            }else if(exprUno.tipo==EnumTipoDato.VECTOR && exprDos.tipo==EnumTipoDato.VECTOR){
                int cont=0;
                Vector<Expresion> temp1 = (Vector)exprUno.valor;
                Vector<Expresion> temp2 = (Vector)exprDos.valor;
                Vector<Expresion> nuevo2 = new Vector();
                nuevo2.setSize(temp1.size());
                for(int i=0;i<temp2.size();i++){
                    nuevo2.setElementAt(new Literal(Simbolo.EnumTipoDato.BOOLEAN,(Double)temp1.get(i).obtenerValor(ent).valor < (Double)temp2.get(i).obtenerValor(ent).valor), i);
                }
                
                return new Literal (Simbolo.EnumTipoDato.VECTOR,nuevo2);
            }
            return new Literal(Simbolo.EnumTipoDato.BOOLEAN,Double.valueOf(exprUno.valor.toString()).doubleValue() < Double.valueOf(exprDos.valor.toString()).doubleValue());
        }else if(tipo== Tipo_operacion.MAYOR_IGUAL_QUE){
            Literal exprUno = (Literal)operadorIzq.obtenerValor(ent);
            Literal exprDos = (Literal)operadorDer.obtenerValor(ent);
            if(exprUno.tipo==EnumTipoDato.VECTOR && exprDos.tipo==EnumTipoDato.INT){
            int cont=0;    
            Vector<Expresion> temp = (Vector)exprUno.valor;
            nuevo = new Vector();
            nuevo.setSize(temp.size());
            for(Expresion exp:temp){     
                nuevo.setElementAt(new Literal(Simbolo.EnumTipoDato.BOOLEAN,Double.valueOf(exp.valor.toString()).doubleValue()>=Double.valueOf(exprDos.valor.toString()).doubleValue()), cont);
                cont++;
            }
            return new Literal (Simbolo.EnumTipoDato.VECTOR,nuevo);
            }else if(exprUno.tipo==EnumTipoDato.INT && exprDos.tipo==EnumTipoDato.VECTOR){
                int cont=0;
                Vector<Expresion> temp = (Vector)exprDos.valor;
                nuevo = new Vector();
                nuevo.setSize(temp.size());
                for(Expresion exp:temp){                    
                    nuevo.setElementAt(new Literal(Simbolo.EnumTipoDato.BOOLEAN,Double.valueOf(exp.valor.toString()).doubleValue()>=Double.valueOf(exprUno.valor.toString()).doubleValue()), cont);
                    cont++;
                }
                return new Literal (Simbolo.EnumTipoDato.VECTOR,nuevo);
            }else if(exprUno.tipo==EnumTipoDato.VECTOR && exprDos.tipo==EnumTipoDato.VECTOR){
                int cont=0;
                Vector<Expresion> temp1 = (Vector)exprUno.valor;
                Vector<Expresion> temp2 = (Vector)exprDos.valor;
                Vector<Expresion> nuevo2 = new Vector();
                nuevo2.setSize(temp1.size());
                for(int i=0;i<temp2.size();i++){
                    nuevo2.setElementAt(new Literal(Simbolo.EnumTipoDato.BOOLEAN,(Double)temp1.get(i).obtenerValor(ent).valor >= (Double)temp2.get(i).obtenerValor(ent).valor), i);
                }
                
                return new Literal (Simbolo.EnumTipoDato.VECTOR,nuevo2);
            }
            return new Literal(Simbolo.EnumTipoDato.BOOLEAN,Double.valueOf(exprUno.valor.toString()).doubleValue() >= Double.valueOf(exprDos.valor.toString()).doubleValue());
        }else if(tipo== Tipo_operacion.MENOR_IGUAL_QUE){
            Literal exprUno = (Literal)operadorIzq.obtenerValor(ent);
            Literal exprDos = (Literal)operadorDer.obtenerValor(ent);
            if(exprUno.tipo==EnumTipoDato.VECTOR && exprDos.tipo==EnumTipoDato.INT){
            int cont=0;    
            Vector<Expresion> temp = (Vector)exprUno.valor;
            nuevo = new Vector();
            nuevo.setSize(temp.size());
            for(Expresion exp:temp){
                nuevo.setElementAt(new Literal(Simbolo.EnumTipoDato.BOOLEAN,Double.valueOf(exp.valor.toString()).doubleValue()<=Double.valueOf(exprDos.valor.toString()).doubleValue()), cont);
                cont++;
            }
            return new Literal (Simbolo.EnumTipoDato.VECTOR,nuevo);
            }else if(exprUno.tipo==EnumTipoDato.INT && exprDos.tipo==EnumTipoDato.VECTOR){
                int cont=0;
                Vector<Expresion> temp = (Vector)exprDos.valor;
                nuevo = new Vector();
                nuevo.setSize(temp.size());                
                for(Expresion exp:temp){
                    nuevo.setElementAt(new Literal(Simbolo.EnumTipoDato.BOOLEAN,Double.valueOf(exp.valor.toString()).doubleValue()<=Double.valueOf(exprUno.valor.toString()).doubleValue()), cont);
                    cont++;
                }
                return new Literal (Simbolo.EnumTipoDato.VECTOR,nuevo);
            }else if(exprUno.tipo==EnumTipoDato.VECTOR && exprDos.tipo==EnumTipoDato.VECTOR){
                int cont=0;
                Vector<Expresion> temp1 = (Vector)exprUno.valor;
                Vector<Expresion> temp2 = (Vector)exprDos.valor;
                Vector<Expresion> nuevo2 = new Vector();
                nuevo2.setSize(temp1.size());
                for(int i=0;i<temp2.size();i++){
                    nuevo2.setElementAt(new Literal(Simbolo.EnumTipoDato.BOOLEAN,(Double)temp1.get(i).obtenerValor(ent).valor <= (Double)temp2.get(i).obtenerValor(ent).valor), i);
                }
                
                return new Literal (Simbolo.EnumTipoDato.VECTOR,nuevo2);
            }
            return new Literal(Simbolo.EnumTipoDato.BOOLEAN,Double.valueOf(exprUno.valor.toString()).doubleValue() <= Double.valueOf(exprDos.valor.toString()).doubleValue());
        }else if(tipo== Tipo_operacion.IGUAL_QUE){
            Literal exprUno = (Literal)operadorIzq.obtenerValor(ent);
            Literal exprDos = (Literal)operadorDer.obtenerValor(ent);
            if(exprUno.tipo==EnumTipoDato.VECTOR && exprDos.tipo==EnumTipoDato.INT){
            int cont=0;    
            Vector<Expresion> temp = (Vector)exprUno.valor;
            nuevo = new Vector();
            nuevo.setSize(temp.size()); 
            for(Expresion exp:temp){
                nuevo.setElementAt(new Literal(Simbolo.EnumTipoDato.BOOLEAN,Double.valueOf(exp.valor.toString()).doubleValue()==Double.valueOf(exprDos.valor.toString()).doubleValue()), cont);
                cont++;
            }
            return new Literal (Simbolo.EnumTipoDato.VECTOR,nuevo);
            }else if(exprUno.tipo==EnumTipoDato.INT && exprDos.tipo==EnumTipoDato.VECTOR){
                int cont=0;
                Vector<Expresion> temp = (Vector)exprDos.valor;
                nuevo = new Vector();
                nuevo.setSize(temp.size()); 
                for(Expresion exp:temp){
            
                    nuevo.setElementAt(new Literal(Simbolo.EnumTipoDato.BOOLEAN,Double.valueOf(exp.valor.toString()).doubleValue()==Double.valueOf(exprUno.valor.toString()).doubleValue()), cont);
                    cont++;
                }
                return new Literal (Simbolo.EnumTipoDato.VECTOR,nuevo);
            }else if(exprUno.tipo==EnumTipoDato.VECTOR && exprDos.tipo==EnumTipoDato.VECTOR){
                int cont=0;
                Vector<Expresion> temp1 = (Vector)exprUno.valor;
                Vector<Expresion> temp2 = (Vector)exprDos.valor;
                Vector<Expresion> nuevo2 = new Vector();
                nuevo2.setSize(temp1.size());
                for(int i=0;i<temp2.size();i++){
                    nuevo2.setElementAt(new Literal(Simbolo.EnumTipoDato.BOOLEAN,(Double)temp1.get(i).obtenerValor(ent).valor == (Double)temp2.get(i).obtenerValor(ent).valor), i);
                }
                
                return new Literal (Simbolo.EnumTipoDato.VECTOR,nuevo2);
            }
            return new Literal(Simbolo.EnumTipoDato.BOOLEAN,Double.valueOf(exprUno.valor.toString()).doubleValue() == Double.valueOf(exprDos.valor.toString()).doubleValue());
        }else if(tipo== Tipo_operacion.DIFERENTE_QUE){
            Literal exprUno = (Literal)operadorIzq.obtenerValor(ent);
            Literal exprDos = (Literal)operadorDer.obtenerValor(ent);
            if(exprUno.tipo==EnumTipoDato.VECTOR && exprDos.tipo==EnumTipoDato.INT){
            int cont=0;    
            Vector<Expresion> temp = (Vector)exprUno.valor;
            nuevo = new Vector();
            nuevo.setSize(temp.size());
            for(Expresion exp:temp){
                nuevo.setElementAt(new Literal(Simbolo.EnumTipoDato.BOOLEAN,Double.valueOf(exp.valor.toString()).doubleValue()!=Double.valueOf(exprDos.valor.toString()).doubleValue()), cont);
                cont++;
            }
            return new Literal (Simbolo.EnumTipoDato.VECTOR,nuevo);
            }else if(exprUno.tipo==EnumTipoDato.INT && exprDos.tipo==EnumTipoDato.VECTOR){
                int cont=0;
                Vector<Expresion> temp = (Vector)exprDos.valor;
                nuevo = new Vector();
                nuevo.setSize(temp.size());
                for(Expresion exp:temp){
                    nuevo.setElementAt(new Literal(Simbolo.EnumTipoDato.BOOLEAN,Double.valueOf(exp.valor.toString()).doubleValue()!=Double.valueOf(exprUno.valor.toString()).doubleValue()), cont);
                    cont++;
                }
                return new Literal (Simbolo.EnumTipoDato.VECTOR,nuevo);
            }else if(exprUno.tipo==EnumTipoDato.VECTOR && exprDos.tipo==EnumTipoDato.VECTOR){
                int cont=0;
                Vector<Expresion> temp1 = (Vector)exprUno.valor;
                Vector<Expresion> temp2 = (Vector)exprDos.valor;
                Vector<Expresion> nuevo2 = new Vector();
                nuevo2.setSize(temp1.size());
                for(int i=0;i<temp2.size();i++){
                    nuevo2.setElementAt(new Literal(Simbolo.EnumTipoDato.BOOLEAN,(Double)temp1.get(i).obtenerValor(ent).valor != (Double)temp2.get(i).obtenerValor(ent).valor), i);
                }
                
                return new Literal (Simbolo.EnumTipoDato.VECTOR,nuevo2);
            }
            return new Literal(Simbolo.EnumTipoDato.BOOLEAN,Double.valueOf(exprUno.valor.toString()).doubleValue() != Double.valueOf(exprDos.valor.toString()).doubleValue());
        }else if(tipo== Tipo_operacion.AND){
            Literal exprUno = (Literal)operadorIzq.obtenerValor(ent);
            Literal exprDos = (Literal)operadorDer.obtenerValor(ent);
            if(exprUno.tipo==EnumTipoDato.VECTOR && exprDos.tipo==EnumTipoDato.BOOLEAN){
            int cont=0;    
            Vector<Expresion> temp = (Vector)exprUno.valor;
            nuevo = new Vector();
            nuevo.setSize(temp.size());
            for(Expresion exp:temp){
                nuevo.setElementAt(new Literal(Simbolo.EnumTipoDato.BOOLEAN,Boolean.valueOf(exp.valor.toString()) && Boolean.valueOf(exprDos.valor.toString())), cont);
                cont++;
            }
            return new Literal (Simbolo.EnumTipoDato.VECTOR,nuevo);
            }else if(exprUno.tipo==EnumTipoDato.BOOLEAN && exprDos.tipo==EnumTipoDato.VECTOR){
                int cont=0;
                Vector<Expresion> temp = (Vector)exprDos.valor;
                nuevo = new Vector();
                nuevo.setSize(temp.size());
                for(Expresion exp:temp){
                    nuevo.setElementAt(new Literal(Simbolo.EnumTipoDato.BOOLEAN,Boolean.valueOf(exp.valor.toString())&&Boolean.valueOf(exprUno.valor.toString())), cont);
                    cont++;
                }
                return new Literal (Simbolo.EnumTipoDato.VECTOR,nuevo);
            }else if(exprUno.tipo==EnumTipoDato.VECTOR && exprDos.tipo==EnumTipoDato.VECTOR){
                int cont=0;
                Vector<Expresion> temp1 = (Vector)exprUno.valor;
                Vector<Expresion> temp2 = (Vector)exprDos.valor;
                Vector<Expresion> nuevo2 = new Vector();
                nuevo2.setSize(temp1.size());
                for(int i=0;i<temp2.size();i++){
                    nuevo2.setElementAt(new Literal(Simbolo.EnumTipoDato.BOOLEAN,Boolean.valueOf(temp1.get(i).obtenerValor(ent).valor.toString()) && Boolean.valueOf(temp2.get(i).obtenerValor(ent).valor.toString())), i);
                }
                
                return new Literal (Simbolo.EnumTipoDato.VECTOR,nuevo2);
            }
            return new Literal(Simbolo.EnumTipoDato.BOOLEAN,Boolean.valueOf(exprUno.valor.toString()) && Boolean.valueOf(exprDos.valor.toString()));
        }else if(tipo== Tipo_operacion.OR){
            Literal exprUno = (Literal)operadorIzq.obtenerValor(ent);
            Literal exprDos = (Literal)operadorDer.obtenerValor(ent);
            if(exprUno.tipo==EnumTipoDato.VECTOR && exprDos.tipo==EnumTipoDato.BOOLEAN){
            int cont=0;    
            Vector<Expresion> temp = (Vector)exprUno.valor;
            nuevo = new Vector();
            nuevo.setSize(temp.size());
            for(Expresion exp:temp){
       
                nuevo.setElementAt(exp = new Literal(Simbolo.EnumTipoDato.BOOLEAN,Boolean.valueOf(exp.valor.toString()) | Boolean.valueOf(exprDos.valor.toString())), cont);
                cont++;
            }
            return new Literal (Simbolo.EnumTipoDato.VECTOR,nuevo);
            }else if(exprUno.tipo==EnumTipoDato.BOOLEAN && exprDos.tipo==EnumTipoDato.VECTOR){
                int cont=0;
                Vector<Expresion> temp = (Vector)exprDos.valor;
                nuevo = new Vector();
                nuevo.setSize(temp.size());
                for(Expresion exp:temp){
                    nuevo.setElementAt(new Literal(Simbolo.EnumTipoDato.BOOLEAN,Boolean.valueOf(exp.valor.toString()) | Boolean.valueOf(exprUno.valor.toString())), cont);
                    cont++;
                }
                return new Literal (Simbolo.EnumTipoDato.VECTOR,nuevo);
            }else if(exprUno.tipo==EnumTipoDato.VECTOR && exprDos.tipo==EnumTipoDato.VECTOR){
                int cont=0;
                Vector<Expresion> temp1 = (Vector)exprUno.valor;
                Vector<Expresion> temp2 = (Vector)exprDos.valor;
                Vector<Expresion> nuevo2 = new Vector();
                nuevo2.setSize(temp1.size());
                for(int i=0;i<temp2.size();i++){
                    nuevo2.setElementAt(new Literal(Simbolo.EnumTipoDato.BOOLEAN,Boolean.valueOf(temp1.get(i).obtenerValor(ent).valor.toString()) | Boolean.valueOf(temp2.get(i).obtenerValor(ent).valor.toString())), i);
                }
                
                return new Literal (Simbolo.EnumTipoDato.VECTOR,nuevo2);
            }
            return new Literal(Simbolo.EnumTipoDato.BOOLEAN,Boolean.valueOf(exprUno.valor.toString()) | Boolean.valueOf(exprDos.valor.toString()));
        }else if(tipo== Tipo_operacion.XOR){
            Literal exprUno = (Literal)operadorIzq.obtenerValor(ent);
            Literal exprDos = (Literal)operadorDer.obtenerValor(ent);
            if(exprUno.tipo==EnumTipoDato.VECTOR && exprDos.tipo==EnumTipoDato.BOOLEAN){
            int cont=0;    
            Vector<Expresion> temp = (Vector)exprUno.valor;
            nuevo = new Vector();
            nuevo.setSize(temp.size());
            for(Expresion exp:temp){
                nuevo.setElementAt(new Literal(Simbolo.EnumTipoDato.BOOLEAN,Boolean.valueOf(exp.valor.toString()) ^ Boolean.valueOf(exprDos.valor.toString())), cont);
                cont++;
            }
            return new Literal (Simbolo.EnumTipoDato.VECTOR,nuevo);
            }else if(exprUno.tipo==EnumTipoDato.BOOLEAN && exprDos.tipo==EnumTipoDato.VECTOR){
                int cont=0;
                Vector<Expresion> temp = (Vector)exprDos.valor;
                nuevo = new Vector();
                nuevo.setSize(temp.size());
                for(Expresion exp:temp){
                    nuevo.setElementAt(new Literal(Simbolo.EnumTipoDato.BOOLEAN,Boolean.valueOf(exp.valor.toString()) ^ Boolean.valueOf(exprUno.valor.toString())), cont);
                    cont++;
                }
                return new Literal (Simbolo.EnumTipoDato.VECTOR,nuevo);
            }else if(exprUno.tipo==EnumTipoDato.VECTOR && exprDos.tipo==EnumTipoDato.VECTOR){
                int cont=0;
                Vector<Expresion> temp1 = (Vector)exprUno.valor;
                Vector<Expresion> temp2 = (Vector)exprDos.valor;
                Vector<Expresion> nuevo2 = new Vector();
                nuevo2.setSize(temp1.size());
                for(int i=0;i<temp2.size();i++){
                    nuevo2.setElementAt(new Literal(Simbolo.EnumTipoDato.BOOLEAN,Boolean.valueOf(temp1.get(i).obtenerValor(ent).valor.toString()) ^ Boolean.valueOf(temp2.get(i).obtenerValor(ent).valor.toString())), i);
                }
                
                return new Literal (Simbolo.EnumTipoDato.VECTOR,nuevo2);
            }
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
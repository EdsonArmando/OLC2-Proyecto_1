/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instruccion;

import Entorno.Simbolo;
import Expresion.Expresion;
import java.util.LinkedList;

/**
 *
 * @author EG
 */
public abstract class Funcion extends Expresion implements Instruccion{
    public String id;
    Simbolo.EnumTipoDato tipo;
    Expresion valor;
    LinkedList<Instruccion> codigo;
    LinkedList<Expresion> param_Formales;
    LinkedList<Expresion> param_Actuales;
    int fila,columna;
    /*
    Atributos para las Graficas del sistema
    */
     /*
    Main: Nombre de la grafica
    x : valores numeriiocos del grafico
    Labels: vector indica la descirpciones
    */
    String main;
    Expresion H;
    Expresion x;
    String tipoLinea;
    String xlab;
    String ylab;
    Expresion labels;
    Expresion namesArg;
    TipoGrafica tipoGraf;
    Expresion xLim;
    Expresion yLim;
    Expresion matriz;
    Expresion booleana;
      public static enum TipoGrafica{
        PIE,
        HISTOGRAMA,
        BARRAS,
        LINEA,
        PUNTO,
        LINEA_PUNTOS
    }
    /*
      Constructores para la Graficas
      */
     
     /*
    Constructor para grafica Pie
    */
    
    public Funcion(String main,Expresion x,Expresion labels,TipoGrafica tipo){
        this.main = main;
        this.x = x;
        this.tipoGraf = tipo;
        this.labels = labels;       
    }
    /*
    Constructor para grafica Barras
    */
    public Funcion(String main,Expresion h,String xlab,String ylab,Expresion names,TipoGrafica tipo){
        this.main = main;
        this.H = h;
        this.xlab = xlab;   
        this.ylab = ylab;
        this.namesArg = names;
        this.tipoGraf = tipo;
    }
    /*
    Constructor para grafica Lineas
    */
    public Funcion(Expresion vector,String tipo,String xlab,String ylab,String main, TipoGrafica tipoGrafica){
        this.H= vector;
        this.tipoLinea = tipo;
        this.xlab = xlab;
        this.ylab = ylab;
        this.main = main;
        this.tipoGraf = tipoGrafica;
    }
    /*
    Constructor para grafica Histograma
    */
    public Funcion(Expresion vector,String xlab,Expresion xLim,Expresion yLim,String main, TipoGrafica tipoGrafica){
        this.H= vector;
        this.xlab = xlab;
        this.xLim = xLim;
        this.yLim = yLim;
        this.main = main;
        this.tipoGraf = tipoGrafica;
    }
    /*
    Constructor para grafica Dispersion
    */
    public Funcion(Expresion Matriz,String xlab,String yLab,Expresion xLim,Expresion yLim,String main, TipoGrafica tipoGrafica,Expresion booleana){
        this.matriz = Matriz;
        this.xlab = xlab;
        this.ylab = yLab;
        this.xLim = xLim;
        this.yLim = yLim;
        this.main = main;
        this.booleana = booleana;
        this.tipoGraf = tipoGrafica;
    }
    /*
    Constructores para otras funciiones
    */
    public Funcion(String id, Simbolo.EnumTipoDato tipo) {
        this.id = id;
        this.tipo = tipo;        
    }
        public Funcion(String id, Simbolo.EnumTipoDato tipo, LinkedList<Instruccion> codigo, LinkedList<Expresion> param_Formales) {
        this.id = id;
        this.tipo = tipo;
        this.codigo = codigo;
        this.param_Formales = param_Formales;        

    }
    
    public String getId() {
        return id;
    }
    public void setParametros(LinkedList<Expresion> parametros){
        this.param_Actuales= parametros;
    }
}
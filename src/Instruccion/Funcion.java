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
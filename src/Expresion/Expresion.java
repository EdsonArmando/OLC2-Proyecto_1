/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Expresion;

import Entorno.Entorno;
import Entorno.Simbolo.EnumTipoDato;
import Estructuras.NodoAST;
import Instruccion.Instruccion;

/**
 *
 * @author EG
 */
public abstract class Expresion{
    public Object valor=null;
    public NodoAST nodo;
    public EnumTipoDato tipo=null;
    public abstract Expresion obtenerValor(Entorno ent);
    public abstract EnumTipoDato getTipo();
}

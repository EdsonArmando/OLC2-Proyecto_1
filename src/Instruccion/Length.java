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
import java.util.LinkedList;
import java.util.Vector;

/**
 *
 * @author EG
 */
public class Length extends Funcion {

    public Length(String id, Simbolo.EnumTipoDato tipo) {
        super(id, tipo);
    }

    @Override
    public Expresion obtenerValor(Entorno ent) {
        Expresion resultado = param_Actuales.get(0).obtenerValor(ent);
        if(resultado.tipo==Simbolo.EnumTipoDato.VECTOR){
            Vector uno = (Vector)resultado.valor;
            return new Literal (Simbolo.EnumTipoDato.INT,uno.size());
        }else if(resultado.tipo==Simbolo.EnumTipoDato.LIST){
            LinkedList uno = (LinkedList)resultado.valor;
            return new Literal (Simbolo.EnumTipoDato.INT,uno.size());
        }else if(resultado.tipo==Simbolo.EnumTipoDato.MATRIX){
            Object[][] uno = (Object[][])resultado.valor;
            return new Literal (Simbolo.EnumTipoDato.INT,uno.length*uno[0].length);
        }else if(resultado.tipo==Simbolo.EnumTipoDato.ARREGLO){
            Object[] uno = (Object[])resultado.valor;
            return new Literal (Simbolo.EnumTipoDato.INT,uno.length);
        }
        return new Literal(Simbolo.EnumTipoDato.ERROR,null);
    }

    @Override
    public Simbolo.EnumTipoDato getTipo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retornar ejecutar(Entorno ent) {
        Expresion resultado = param_Actuales.get(0).obtenerValor(ent);
        if(resultado.tipo==Simbolo.EnumTipoDato.VECTOR){
            Vector uno = (Vector)resultado.valor;
            Inicio.salidaConsola.append(String.valueOf(uno.size())+"\n");
        }else if(resultado.tipo==Simbolo.EnumTipoDato.LIST){
            LinkedList uno = (LinkedList)resultado.valor;
            Inicio.salidaConsola.append(String.valueOf(uno.size())+"\n");
        }else if(resultado.tipo==Simbolo.EnumTipoDato.MATRIX){
            Object[][] uno = (Object[][])resultado.valor;
            Inicio.salidaConsola.append(String.valueOf(uno.length*uno[0].length)+"\n");
        }else if(resultado.tipo==Simbolo.EnumTipoDato.ARREGLO){
            Object[] uno = (Object[])resultado.valor;
            Inicio.salidaConsola.append(String.valueOf(uno.length)+"\n");
        }
        return new Retornar();
    }
    
}

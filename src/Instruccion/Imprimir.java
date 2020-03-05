/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instruccion;

import Entorno.Entorno;
import Entorno.Simbolo;
import Expresion.Expresion;
import Views.Inicio;
import java.util.LinkedList;
import java.util.Vector;

/**
 *
 * @author EG
 */
public class Imprimir implements Instruccion  {
    private Expresion expresion;
    public Imprimir(Expresion exp){
        this.expresion = exp;
    }
    @Override
    public Retornar ejecutar(Entorno ent) {
        Expresion resultado = this.expresion.obtenerValor(ent);
        if(resultado.tipo!=Simbolo.EnumTipoDato.ERROR){
            if(resultado.tipo == Simbolo.EnumTipoDato.VECTOR){
                Vector temp = (Vector)resultado.valor;
                for(Object expr: temp){
                    if(expr==null){
                        Inicio.salidaConsola.append(" null ,");
                    }else{
                        Expresion exp = (Expresion) expr;
                        Inicio.salidaConsola.append(exp.valor.toString()+" , ");
                    }
                }
                 Inicio.salidaConsola.append("\n");
            }else if(resultado.tipo == Simbolo.EnumTipoDato.LIST){
                LinkedList temp = (LinkedList)resultado.valor;
                for(Object expr: temp){
                    if(expr==null){
                        Inicio.salidaConsola.append(" null ,");
                    }else{
                        Expresion exp = (Expresion) expr;
                        Inicio.salidaConsola.append(exp.valor.toString()+" , ");
                    }
                }
            }else if(resultado.tipo==Simbolo.EnumTipoDato.MATRIX){
                Object[][] temp = (Object[][])resultado.valor;
                int filas = temp.length;
                for(int i=0;i<filas;i++){
                    for(int j=0;j<temp[i].length;j++){
                        Inicio.salidaConsola.append("[ " + i + "]"+ "[ " + j + "]");
                    }
                        Inicio.salidaConsola.append("\n");
                }
                Inicio.salidaConsola.append(resultado.tipo.toString()+"\n");
            }else{
                Inicio.salidaConsola.append(resultado.valor.toString()+"\n");
            }
        }else{
            Inicio.salidaConsola.setText("Existio un error");
        }
        
        return new Retornar();
    }
    
}

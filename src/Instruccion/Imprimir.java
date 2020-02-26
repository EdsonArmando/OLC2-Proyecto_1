/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instruccion;

import Entorno.Entorno;
import Expresion.Expresion;
import Views.Inicio;

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
        if(resultado!=null){
            Inicio.salidaConsola.setText(resultado.valor.toString()+"\n");
        }else{
            Inicio.salidaConsola.setText("Existio un error");
        }
        
        return new Retornar();
    }
    
}

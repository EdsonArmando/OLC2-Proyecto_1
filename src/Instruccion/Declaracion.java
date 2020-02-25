/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instruccion;

import Entorno.Entorno;
import Entorno.Simbolo;
import Expresion.Expresion;

/**
 *
 * @author EG
 */
public class Declaracion implements Instruccion {
    String id;
    Expresion valor;
    public Declaracion(String id,Expresion expr){
        this.id = id;
        this.valor = expr;
    }
    @Override
    public Retornar ejecutar(Entorno ent) {
        Object exprValor = valor.obtenerValor(ent);
        ent.insertar(id, new Simbolo(valor.tipo,exprValor,id));
        return new Retornar();
    }
    
}

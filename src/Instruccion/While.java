/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instruccion;

import Entorno.Entorno;
import Expresion.Literal;
import Expresion.Operacion;
import java.util.LinkedList;

/**
 *
 * @author EG
 */
public class While implements Instruccion{
    private Operacion condicion;
    private LinkedList<Instruccion> listaInstrucciones;
    public While(Operacion condicion,LinkedList<Instruccion> listaInstrucciones){
        this.condicion = condicion;
        this.listaInstrucciones = listaInstrucciones;
    }
    @Override
    public Retornar ejecutar(Entorno ent) {
        Entorno tablaLocal = new Entorno(ent);
        while((Boolean)condicion.obtenerValor(tablaLocal).valor){
            for(Instruccion ins : listaInstrucciones){
                ins.ejecutar(tablaLocal);
            }
        }
        ent.tabla.putAll(tablaLocal.tabla);
        return new Retornar();
    }
}

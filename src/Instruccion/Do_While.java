/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instruccion;

import Entorno.Entorno;
import Expresion.Operacion;
import java.util.LinkedList;

/**
 *
 * @author EG
 */
public class Do_While implements Instruccion{
    private Operacion condicion;
    private LinkedList<Instruccion> listaInstrucciones;
    public Do_While(Operacion condicion,LinkedList<Instruccion> listaInstrucciones){
        this.condicion = condicion;
        this.listaInstrucciones = listaInstrucciones;
    }
    @Override
    public Retornar ejecutar(Entorno ent) {
        Entorno tablaLocal = new Entorno(ent);
        for(Instruccion ins:listaInstrucciones){
            ins.ejecutar(tablaLocal);
        }
        while(Boolean.valueOf(condicion.obtenerValor(tablaLocal).toString())){
            for(Instruccion ins:listaInstrucciones){
                ins.ejecutar(tablaLocal);
            }
        }
        return new Retornar();
    }
    
}

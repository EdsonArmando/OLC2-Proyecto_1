/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Entorno.Simbolo;
import Instruccion.Funcion;
import Instruccion.Function_C;
import Instruccion.Instruccion;
import Instruccion.To_Lower;
import Instruccion.To_Upper;
import Views.Inicio;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author EG
 */
public class Singleton {
    private static Singleton singleton;
    public HashMap<String,Funcion> funciones;
    private Singleton(){
        funciones = new HashMap<>();
        /*Meter todos mis metodos propios del lenguaje*/
    }
    public void funcionesSistema(){
        funciones.put("toLower",new To_Lower("tolower",Simbolo.EnumTipoDato.STRING));
        funciones.put("toUpper",new To_Upper("toUpper",Simbolo.EnumTipoDato.STRING));
        funciones.put("c",new Function_C("c",Simbolo.EnumTipoDato.STRING));
    }
    public static Singleton getInstance(){
        if(singleton== null){
            singleton= new Singleton();
        }
        return singleton;
    }
    public void limpiarEntorno(){
        funciones.clear();
    }
    public void putFuncion(Funcion funcion,String id){
        if(funciones.containsKey(id)){
            Inicio.salidaConsola.append("La funcion ya existe\n");
            return ;
        }
        funciones.put(id, funcion);
    }
    public Funcion getFuncion(String id){
        if(funciones.containsKey(id)){
            return funciones.get(id);
        }
        return null;
    }
}

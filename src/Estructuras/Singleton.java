/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Entorno.Simbolo;
import Instruccion.Arreglo;
import Instruccion.Funcion;
import Instruccion.Function_C;
import Instruccion.Function_List;
import Instruccion.Function_Matrix;
import Instruccion.Grafica_Barras;
import Instruccion.Grafica_Dispersion;
import Instruccion.Grafica_Histograma;
import Instruccion.Grafica_Linea;
import Instruccion.Grafica_Pie;
import Instruccion.Instruccion;
import Instruccion.Length;
import Instruccion.Ncol;
import Instruccion.Nrow;
import Instruccion.Remove;
import Instruccion.StringLenght;
import Instruccion.To_Lower;
import Instruccion.To_Upper;
import Instruccion.TypeOf;
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
        funciones.put("tolowercase",new To_Lower("tolowercase",Simbolo.EnumTipoDato.FUNCION));
        funciones.put("touppercase",new To_Upper("toUppercase",Simbolo.EnumTipoDato.FUNCION));
        funciones.put("c",new Function_C("c",Simbolo.EnumTipoDato.FUNCION));
        funciones.put("list",new Function_List("list",Simbolo.EnumTipoDato.FUNCION));
        funciones.put("matrix",new Function_Matrix("matrix",Simbolo.EnumTipoDato.FUNCION));
        funciones.put("pie",new Grafica_Pie("pie",Simbolo.EnumTipoDato.FUNCION));
        funciones.put("barplot",new Grafica_Barras("barras",Simbolo.EnumTipoDato.FUNCION));
        funciones.put("plot",new Grafica_Linea("plot",Simbolo.EnumTipoDato.FUNCION));
        funciones.put("hist",new Grafica_Histograma("hist",Simbolo.EnumTipoDato.FUNCION));
        funciones.put("plot1",new Grafica_Dispersion("plot",Simbolo.EnumTipoDato.FUNCION));
        funciones.put("array",new Arreglo("array",Simbolo.EnumTipoDato.FUNCION));
        funciones.put("typeof", new TypeOf("typeof",Simbolo.EnumTipoDato.FUNCION));
        funciones.put("length", new Length("length",Simbolo.EnumTipoDato.FUNCION));
        funciones.put("ncol", new Ncol("ncol",Simbolo.EnumTipoDato.FUNCION));
        funciones.put("nrow", new Nrow("nrow",Simbolo.EnumTipoDato.FUNCION));
        funciones.put("stringlength", new StringLenght("stringlength",Simbolo.EnumTipoDato.FUNCION));
        funciones.put("remove", new Remove("remove",Simbolo.EnumTipoDato.FUNCION));
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
    public boolean putFuncion(Funcion funcion,String id){
        if(funciones.containsKey(id.toLowerCase())){
            Inicio.salidaConsola.append("La funcion ya existe\n");
            return false ;
        }
        funciones.put(id, funcion);
        return true;
    }
    public Funcion getFuncion(String id){
        if(funciones.containsKey(id.toLowerCase())){
            return funciones.get(id.toLowerCase());
        }
        return null;
    }
    public HashMap<String,Funcion> getTabla(){
        return this.funciones;
    }
    public Funcion getFuncion(String id,int param){
        if(funciones.containsKey(id.toLowerCase())&&param==8){
            return funciones.get("plot1");
        }else{
            return funciones.get(id.toLowerCase());
        }
    }
}

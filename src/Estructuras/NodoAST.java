/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author EG
 */
public class NodoAST {
    String valor;
    String text = "digraph lista{ rankdir=TB;node[shape = box, style = filled, color = white]; \n";
    String val ="";
    LinkedList<NodoAST> hijos;
    public NodoAST(String valor){
        this.valor = valor;
        this.hijos = new LinkedList<NodoAST>();
    }
    public void add(NodoAST hijo){
        this.hijos.add(hijo);
    }
    public void Graficar(NodoAST nodo){
       val+="nodo"+String.valueOf(nodo.hashCode())+"[label= \"" + nodo.valor+" \"" +"fillcolor=\"LightBlue\", style =\"filled\", shape=\"box\"]; \n";
       val+="nodo"+String.valueOf(nodo.hijos.get(0).hashCode())+"[label= \"" + nodo.hijos.get(0).valor+" \"" +"fillcolor=\"LightBlue\", style =\"filled\", shape=\"box\"]; \n";
       val +="nodo"+String.valueOf(nodo.hashCode())+" -> " + "nodo"+String.valueOf(nodo.hijos.get(0).hashCode())+"\n";
       Instrucciones(nodo.hijos.get(0));
    }
    public void Instrucciones(NodoAST nodo){
        switch(nodo.hijos.size()){
            case 1:
                Instruccion(nodo.hijos.get(0),nodo.hashCode());
                break;
            case 2:
               val+="nodo"+String.valueOf(nodo.hijos.get(0).hashCode())+"[label= \"" + nodo.hijos.get(0).valor+" \"" +"fillcolor=\"LightBlue\", style =\"filled\", shape=\"box\"]; \n";   
               val+="nodo"+String.valueOf(nodo.hijos.get(1).hashCode())+"[label= \"" + nodo.hijos.get(1).valor+" \"" +"fillcolor=\"LightBlue\", style =\"filled\", shape=\"box\"]; \n";   
               val +="nodo"+String.valueOf(nodo.hashCode())+" -> " + "nodo"+String.valueOf(nodo.hijos.get(0).hashCode())+"\n";
               val +="nodo"+String.valueOf(nodo.hashCode())+" -> " + "nodo"+String.valueOf(nodo.hijos.get(1).hashCode())+"\n";
               Instrucciones(nodo.hijos.get(0));
               Instruccion(nodo.hijos.get(1),nodo.hashCode());
                break;
        }
    }
    public void Instruccion(NodoAST nodo,int hash){
        switch(nodo.hijos.size()){
            case 4:
                val+="nodo"+String.valueOf(nodo.hashCode())+"[label= \"" + nodo.valor+" \"" +"fillcolor=\"LightBlue\", style =\"filled\", shape=\"box\"]; \n";   
                val +="nodo"+String.valueOf(hash)+" -> " + "nodo"+String.valueOf(nodo.hashCode())+"\n";
                break;
            case 5:
                val+="nodo"+String.valueOf(nodo.hashCode())+"[label= \"" + nodo.valor+" \"" +"fillcolor=\"LightBlue\", style =\"filled\", shape=\"box\"]; \n";   
                val +="nodo"+String.valueOf(hash)+" -> " + "nodo"+String.valueOf(nodo.hashCode())+"\n";
                break;
            case 1:
                Instruccion(nodo.hijos.get(0),nodo.hashCode());
                break;
            default:
                break;
        }
    }
    public String Expresion(NodoAST nodo){
        String val = nodo.hijos.get(1).valor;
        String temp;
        switch(val){
            case "+":
                
                break;
        }
        return null;
    }
    public void generarArchivo() throws IOException{
        FileWriter file = new FileWriter("AST.txt");
        file.write(text+val);
        file.write("}");
        file.close();
        Runtime.getRuntime().exec("cmd /c dot -Tpng AST.txt -o AST.png", null, new File(System.getProperty("user.dir")));
    }
}

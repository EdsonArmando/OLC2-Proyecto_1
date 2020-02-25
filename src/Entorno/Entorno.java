/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entorno;

import java.util.HashMap;

/**
 *
 * @author EG
 */
public class Entorno {
    Entorno anterior;
    public HashMap<String,Simbolo> tabla;
    public Entorno(Entorno anterior){
        this.tabla=new HashMap<>();
        this.anterior=anterior;
    }
    public void insertar(String nombre, Simbolo valor){   
        if (this.tabla.containsKey(nombre)) {
            System.out.println("La variable ya existe");
            return;
        }
        this.tabla.put(nombre, valor);
    }
    public boolean existeVariable(String nombre){
        return this.tabla.containsKey(nombre);
    }
    public Simbolo obtener(String nombre){
        if (tabla.containsKey(nombre)) {
            Simbolo sim = tabla.get(nombre);
            return sim;
        }
        //salidaConsola.append("La variable '" + nombre + "' NO existe");
        return null;
    }
    public void modificarVariable(String nombre, Simbolo valor){
          tabla.put(nombre, valor);
    }
}

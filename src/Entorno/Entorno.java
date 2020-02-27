/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entorno;

import Views.Inicio;
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
    public boolean existeVariable(String nombre,Entorno ent){
        boolean existe = false;
        if(ent.tabla.containsKey(nombre)){
            return true;
        }else if(ent.anterior!=null){
            existe = existeVariable(nombre,ent.anterior);
            return existe;
        }
        return existe;
    }
    public Simbolo obtener(String nombre,Entorno ent){
        Simbolo sim =null;
        if (ent.tabla.containsKey(nombre)) {
            sim = ent.tabla.get(nombre);
            return sim;
        }else if(ent.anterior!=null){
            sim =obtener(nombre,ent.anterior);
        }else{
            Inicio.salidaConsola.append("No existe la variable");
        }
        return sim;
    }
    public void modificarVariable(String nombre, Simbolo valor){
          tabla.put(nombre, valor);
    }
}

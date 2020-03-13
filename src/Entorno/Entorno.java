/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entorno;

import Estructuras.Singleton;
import Instruccion.Funcion;
import Views.Inicio;
import java.io.FileWriter;
import java.io.IOException;
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
            tabla.put(nombre, valor);
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
    public void graficarTabla() throws IOException{
        FileWriter archivo = new FileWriter("Entorno.html");
        HashMap<String,Funcion> funciones=Singleton.getInstance().getTabla();
        int cont=1;
        archivo.write("<html>");
        archivo.write("<head>");
        archivo.write("<style>"
                + "table{"
                + "  font-family: arial, sans-serif; border-collapse: collapse;    width: 100%;}"
                + "td, th{"
                + "border: 1px solid #dddddd;text-align: left;  padding: 8px;}"
                + "tr:nth-child(even){"
                + " background-color: #dddddd;}"
                + "</style>");
        archivo.write("</head>");
        archivo.write("<body>");
        archivo.write("<H1>Tabla de Simbolos</H1>");
        archivo.write("<br><br>");
        archivo.write("<table>");
        archivo.write("<tr><th>No</th><th>Nombre</th><th>Tipo</th><th>Valor</th><th>Tamaño</th></tr>");
        for (String key : tabla.keySet()) {
            Simbolo sim = tabla.get(key);
            archivo.write("<tr><td>" + cont + "</td><td>" + key + "</td><td>" + sim.tipo.toString() + "</td><td>" + sim.valor.toString() + "</td><td>" + String.valueOf(sim.valor.hashCode()) + "</td></tr>" );    
            cont++;
        }
        for(String key : funciones.keySet()){
            Funcion func = funciones.get(key);
            archivo.write("<tr><td>" + cont + "</td><td>" + key + "</td><td>" + func.tipo.toString() + "</td><td>" + func.tipo.toString() + "</td><td>" + func.tipo.toString() + "</td></tr>" );    
            cont++;
        }
        archivo.write("</table>");
        archivo.write("</body>");
        archivo.write("</html>");
        archivo.close();
        
    }
}

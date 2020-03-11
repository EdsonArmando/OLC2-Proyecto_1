/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Expresion;

import Entorno.Entorno;
import Entorno.Simbolo;
import Estructuras.Singleton;
import Instruccion.Funcion;
import Instruccion.Instruccion;
import Instruccion.Retornar;
import java.util.LinkedList;

/**
 *
 * @author EG
 */
public class LlamadaFuncion extends Expresion implements Instruccion{
    String id;
    LinkedList<Expresion> parametros;
    int fila,columna;
    public LlamadaFuncion(String id,LinkedList<Expresion> parametros){
        this.id = id;
        this.parametros = parametros;
    }
    @Override
    public Expresion obtenerValor(Entorno ent) {
        Funcion f = Singleton.getInstance().getFuncion(id);
         if(f!=null){
            f.setParametros(parametros);
            Expresion o = f.obtenerValor(ent);
            return (Expresion)o;
        }
        return null;
    }
    @Override
    public Simbolo.EnumTipoDato getTipo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public Retornar ejecutar(Entorno ent) {
        Funcion f = Singleton.getInstance().getFuncion(id,parametros.size());
         if(f!=null){
            f.setParametros(parametros);
            f.obtenerValor(ent);
        }
         return new Retornar();
    }
    
}

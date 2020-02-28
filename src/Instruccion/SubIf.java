/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instruccion;

import Entorno.Entorno;
import Expresion.Expresion;
import Expresion.Literal;
import Expresion.Operacion;
import java.util.LinkedList;

/**
 *
 * @author EG
 */
public class SubIf implements Instruccion{
    private Boolean valorCondicion=false;
    private Boolean SiEjecutado=false;
    private final boolean isElse;
    
    private final Operacion condicion;
    
    private final LinkedList<Instruccion> listaInstrucciones;
    
    public SubIf(Operacion a, LinkedList<Instruccion> b) {
        condicion=a;
        listaInstrucciones=b;
        isElse=false;
    }
    
    public SubIf(LinkedList<Instruccion> a) {
        condicion=null;
        listaInstrucciones=a;
        isElse=true;
    }
    public Boolean getValorCondicion() {
        return valorCondicion || isElse;
    }
    @Override
    public Retornar ejecutar(Entorno ent) {
        Literal valor=null;
        if(this.condicion!=null){
             valor= (Literal)condicion.obtenerValor(ent);
             valorCondicion= (Boolean) valor.valor;
        }
        if(valorCondicion==true){
            SiEjecutado=true;
            Entorno tablaLocal=new Entorno(null);
            tablaLocal.tabla.putAll(ent.tabla);
            for(Instruccion in: listaInstrucciones){
                Retornar retorn= in.ejecutar(tablaLocal);
                if(retorn.isReturn){
                return retorn;
                }
            }
            //ent.tabla.putAll(tablaLocal.tabla);
        }else if(isElse==true && SiEjecutado==false){
            Entorno tablaLocal=new Entorno(null);
            tablaLocal.tabla.putAll(ent.tabla);
            for(Instruccion in: listaInstrucciones){
                Retornar retorn=in.ejecutar(tablaLocal);
                if(retorn.isReturn){
                return retorn;
                }
            }
            //ent.tabla.putAll(tablaLocal.tabla);
        }
        return new Retornar();
    }
    
}

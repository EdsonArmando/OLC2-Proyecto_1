/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instruccion;

import Entorno.Entorno;
import Entorno.Simbolo;
import Expresion.Expresion;
import java.util.LinkedList;

/**
 *
 * @author EG
 */
public class FuncionPropia extends Funcion {
     public FuncionPropia(String id, Simbolo.EnumTipoDato tipo, LinkedList<Instruccion> codigo, LinkedList<String> parametros) {
        super(id, tipo, codigo, parametros);
    }

    @Override
    public Expresion obtenerValor(Entorno ent) {
        Entorno tablaLocal = new Entorno(ent);
        if(param_Actuales.size()==param_Formales.size()){
            //agregar nueva declaracion a lista
                    
        }
        /*
            for(Declaracion in declaraciones){
                declaracion.ejecutar(e,mensajes);
            }
        */
        //error
        
        
        for (Instruccion instr : codigo) {
            instr.ejecutar(tablaLocal);
        }
        return null;
    }

    @Override
    public Simbolo.EnumTipoDato getTipo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

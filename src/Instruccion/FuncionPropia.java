/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instruccion;

import Entorno.Entorno;
import Entorno.Simbolo;
import Estructuras.Singleton;
import Expresion.Expresion;
import Expresion.Literal;
import Expresion.Operacion;
import java.util.LinkedList;

/**
 *
 * @author EG
 */
public class FuncionPropia extends Funcion implements Instruccion {
     public FuncionPropia(String id, Simbolo.EnumTipoDato tipo, LinkedList<Instruccion> codigo, LinkedList<Expresion> parametros) {
        super(id, tipo, codigo, parametros);
    }

    @Override
    public Expresion obtenerValor(Entorno ent) {
        Entorno tablaLocal = new Entorno(ent);
        if(param_Actuales.size()==param_Formales.size()){
            for(int i=0;i<param_Formales.size();i++){
                Operacion op= (Operacion) param_Formales.get(i);
                Expresion val = param_Actuales.get(i).obtenerValor(ent);
                Declaracion declaracion = new Declaracion(op.valor.toString(),val);
                declaracion.ejecutar(tablaLocal);
            }      
        }
        for (Instruccion instr : codigo) {
            Retornar ret=instr.ejecutar(tablaLocal);
            if(ret.isReturn){
                return ret.valor;
            }
        }
        return null;
    }

    @Override
    public Simbolo.EnumTipoDato getTipo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retornar ejecutar(Entorno ent) {
        Funcion fun=this;
        Singleton.getInstance().putFuncion(fun, id.toLowerCase());
        return new Retornar();
    }
}
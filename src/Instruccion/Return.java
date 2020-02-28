/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instruccion;

import Entorno.Entorno;
import Entorno.Simbolo;
import Expresion.Expresion;
import Expresion.Literal;
import Expresion.Operacion;
import Instruccion.Retornar;

/**
 *
 * @author EG
 */
public class Return implements Instruccion{
    Expresion valorReturn;
    public Object valGuardado;
    public Return(Expresion valor){
            this.valorReturn = valor;
        }
    @Override
    public Retornar ejecutar(Entorno ent) {
        Retornar retornar = new Retornar();
        retornar.isReturn=true;
        retornar.valor=new Literal(Simbolo.EnumTipoDato.INT,valorReturn.obtenerValor(ent).valor);
        return retornar;                
    }
}

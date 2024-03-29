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
import Views.Inicio;

/**
 *
 * @author EG
 */
public class To_Lower extends Funcion {
    public To_Lower(String id,Simbolo.EnumTipoDato tipo){
        super(id, tipo);
    }

    @Override
    public Expresion obtenerValor(Entorno ent) {
        if(this.param_Actuales.size()==1){
            Expresion res = param_Actuales.get(0).obtenerValor(ent);
            if(res.valor instanceof String){
                return new Literal(Simbolo.EnumTipoDato.STRING,((String) res.valor).toLowerCase());
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
        if(this.param_Actuales.size()==1){
            Expresion res = param_Actuales.get(0).obtenerValor(ent);
            if(res.valor instanceof String){
                Inicio.salidaConsola.append(((String) res.valor).toLowerCase()+"\n");
            }
        }
        return new Retornar();
    }
   
}

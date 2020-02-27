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

/**
 *
 * @author EG
 */
public class To_Upper extends Funcion{
    public To_Upper(String id,Simbolo.EnumTipoDato tipo){
        super(id,tipo);
    }
    @Override
    public Expresion obtenerValor(Entorno ent) {
        if(this.param_Actuales.size()==1){
            Expresion res = param_Actuales.get(0).obtenerValor(ent);
            if(res.valor instanceof String){
                return new Literal(Simbolo.EnumTipoDato.STRING,((String) res.valor).toUpperCase());
            }
        }
        return null;
    }

    @Override
    public Simbolo.EnumTipoDato getTipo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

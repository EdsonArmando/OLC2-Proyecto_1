/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Expresion;

import Entorno.Entorno;
import Entorno.Simbolo;

/**
 *
 * @author EG
 */
public class Id implements Expresion {
    String id;
    public Id(String id){
        this.id=id;
    }
    @Override
    public Expresion obtenerValor(Entorno ent) {
        Simbolo sim = ent.obtener(id);
         if(sim!=null){
            // Devolver un objeto con el valor
            return new Literal(sim.getTipo(), sim.getValor());
        }
        return new Literal(Simbolo.EnumTipoDato.ERROR, "@Error@"); 
    }

    @Override
    public Simbolo.EnumTipoDato getTipo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

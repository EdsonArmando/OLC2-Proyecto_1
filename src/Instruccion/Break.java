/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instruccion;

import Entorno.Entorno;

/**
 *
 * @author EG
 */
public class Break implements Instruccion {
    public Break(){}
    @Override
    public Retornar ejecutar(Entorno ent) {
       Retornar r = new Retornar();
            r.isBreak = true;
            return r;
    }
    
}

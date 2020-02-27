/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instruccion;

import Entorno.Entorno;
import java.util.LinkedList;

/**
 *
 * @author EG
 */
public class If implements Instruccion{
    //SubIf que seran ejecutados
    private final LinkedList<Instruccion> subIf;
    //Solamente un IF
    public If(SubIf a){
        subIf=new LinkedList<>();
        subIf.add(a);
    }
    public If(SubIf a, LinkedList<SubIf> b) {
        subIf=new LinkedList<>();
        subIf.add(a);
        subIf.addAll(b);
    }
    public If(SubIf a, LinkedList<SubIf> b, SubIf c) {
        subIf=new LinkedList<>();
        subIf.add(a);
        subIf.addAll(b);
        subIf.add(c);
    }
    public If(SubIf a, SubIf b) {
        subIf=new LinkedList<>();
        subIf.add(a);
        subIf.add(b);
    }
    @Override
    public Retornar ejecutar(Entorno ent) {
        boolean isTrue=false;
        for(Instruccion in: subIf){
            if(isTrue==false){
                in.ejecutar(ent);
                isTrue=((SubIf)in).getValorCondicion();
            }
            //if(((SubSi)in).getValorCondicion());
        }
        return new Retornar();
    }
    
}

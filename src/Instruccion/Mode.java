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
import java.util.Vector;

/**
 *
 * @author EG
 */
public class Mode extends Funcion{

    public Mode(String id, Simbolo.EnumTipoDato tipo) {
        super(id, tipo);
    }

    @Override
    public Expresion obtenerValor(Entorno ent) {
        Vector<Expresion> temp = (Vector<Expresion>)param_Actuales.get(0).obtenerValor(ent).valor;
        if(param_Actuales.size() == 2){
            Double trim = (Double)param_Actuales.get(1).obtenerValor(ent).valor;
            if(trim !=0.0){
                int cont=0;
                Vector<Expresion> tempo = new Vector<Expresion>();

                for(int i=0;i<temp.size();i++){
                    if((Double)temp.get(i).obtenerValor(ent).valor<trim){
                    cont++;
                    }
                }
                tempo.setSize(temp.size()-cont);
                cont=0;
                for(int i=0;i<temp.size();i++){
                    if((Double)temp.get(i).obtenerValor(ent).valor<trim){
                    }else{
                        tempo.set(cont, temp.get(i));
                        cont++;
                    }
                }
                temp=tempo;
            }
        }    
        int a[] = new int[temp.size()];
        for(int i=0;i<temp.size();i++){
            Double uno = (Double)temp.get(i).obtenerValor(ent).valor;
            a[i] = uno.intValue();
        }
        if(param_Actuales.size() == 1 && param_Actuales.get(0).obtenerValor(ent).tipo == Simbolo.EnumTipoDato.VECTOR){
            return mode(a,0.0);
        }else if(param_Actuales.size() == 2 && param_Actuales.get(0).obtenerValor(ent).tipo == Simbolo.EnumTipoDato.VECTOR){
            return mode(a,(Double)param_Actuales.get(1).obtenerValor(ent).valor);
        }else{
            Inicio.salidaConsola.append("Existio un error al calcular la media \n");
            return null;
        }
    }

    @Override
    public Simbolo.EnumTipoDato getTipo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retornar ejecutar(Entorno ent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Expresion mode(int a[],Double trim) {
        int maxValue=0, maxCount=0;
        for (int i = 0; i < a.length; ++i) {
            int count = 0;
            for (int j = 0; j < a.length; ++j) {
                if (a[j] == a[i]) ++count;
            }
            if (count > maxCount) {
                maxCount = count;
                maxValue = a[i];
            }
        }
        return new Literal(Simbolo.EnumTipoDato.INT,maxValue);
    }
}

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
public class Median extends Funcion {

    public Median(String id, Simbolo.EnumTipoDato tipo) {
        super(id, tipo);
    }

    @Override
    public Expresion obtenerValor(Entorno ent) {
       if(param_Actuales.size() == 1 && param_Actuales.get(0).obtenerValor(ent).tipo == Simbolo.EnumTipoDato.VECTOR){
            return mediana((Vector<Expresion>)param_Actuales.get(0).obtenerValor(ent).valor,ent,0.0);
        }else if(param_Actuales.size() == 2 && param_Actuales.get(0).obtenerValor(ent).tipo == Simbolo.EnumTipoDato.VECTOR){
            return mediana((Vector<Expresion>)param_Actuales.get(0).obtenerValor(ent).valor,ent,(Double)param_Actuales.get(1).obtenerValor(ent).valor);
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
        if(param_Actuales.size() == 1 && param_Actuales.get(0).obtenerValor(ent).tipo == Simbolo.EnumTipoDato.VECTOR){
            Literal lit =  (Literal)mediana((Vector<Expresion>)param_Actuales.get(0).obtenerValor(ent).valor,ent,0.0);
            Inicio.salidaConsola.append(String.valueOf(lit.valor)+"\n");
            return new Retornar();
        }else if(param_Actuales.size() == 2 && param_Actuales.get(0).obtenerValor(ent).tipo == Simbolo.EnumTipoDato.VECTOR){
            Literal lit = (Literal) mediana((Vector<Expresion>)param_Actuales.get(0).obtenerValor(ent).valor,ent,(Double)param_Actuales.get(1).obtenerValor(ent).valor);
            Inicio.salidaConsola.append(String.valueOf(lit.valor)+"\n");
            return new Retornar();
        }else{
            Inicio.salidaConsola.append("Existio un error al calcular la mediana \n");
            return new Retornar();
        }
    }
    public  Expresion mediana (Vector<Expresion> v,Entorno ent,Double trim) {
    Double pos = 0.0, n = Double.valueOf(v.size());
    Double temp = 0.0, temp0 = 0.0;    
    // ordenar de menor a mayor
    if(trim !=0.0){
        int cont=0;
        Vector<Expresion> tempo = new Vector<Expresion>();
        
        for(int i=0;i<v.size();i++){
            if((Double)v.get(i).obtenerValor(ent).valor<trim){
            cont++;
            }
        }
        tempo.setSize(v.size()-cont);
        cont=0;
        for(int i=0;i<v.size();i++){
            if((Double)v.get(i).obtenerValor(ent).valor<trim){
            }else{
                tempo.set(cont, v.get(i));
                cont++;
            }
        }
        v=tempo;
        n = Double.valueOf(v.size());
    }
    v = burbuja ( v, 0,ent );

    temp = n / 2;
    if ( n % 2 == 0 ) {
      pos = temp-1;    
      temp0 = ((Double)v.get(pos.intValue()).obtenerValor(ent).valor + (Double)v.get(pos.intValue() + 1).obtenerValor(ent).valor)/2;
    }
    if ( n % 2 == 1 ) {
      pos = temp;
      temp0 = (Double)(v.get(pos.intValue()).obtenerValor(ent).valor);  
    }

    return new Literal(Simbolo.EnumTipoDato.INT,temp0);
  }
    public  Vector<Expresion> burbuja ( Vector<Expresion> v, int ord,Entorno ent ) {
    Double i, j, n =Double.valueOf(v.size()), aux = 0.0;
    
    for ( i = 0.0; i < n - 1; i++ ){
        for ( j = i + 1; j < n; j++ ){
            if ( ord == 0 )
            if ( (Double)v.get(i.intValue()).obtenerValor(ent).valor > (Double)v.get(j.intValue()).obtenerValor(ent).valor) {
              aux = (Double)v.get(j.intValue()).obtenerValor(ent).valor;
              v.set(j.intValue(), new Literal(Simbolo.EnumTipoDato.INT,(Double)v.get(i.intValue()).obtenerValor(ent).valor)) ;
              v.set(i.intValue(), new Literal(Simbolo.EnumTipoDato.INT,aux)) ;
          }
        }
    }
      
    return v;
  }
}

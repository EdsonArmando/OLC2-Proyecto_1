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
import java.util.Vector;

/**
 *
 * @author EG
 */
public class Function_Matrix extends Funcion{
    Expresion Data;
    Expresion filas;
    Expresion columnas;
    Vector vector;
    
    public Function_Matrix(String id, Simbolo.EnumTipoDato tipo) {
        super(id, tipo);
    }

    @Override
    public Expresion obtenerValor(Entorno ent) {
        Data = param_Actuales.get(0).obtenerValor(ent);
        filas = param_Actuales.get(1).obtenerValor(ent);
        columnas = param_Actuales.get(2).obtenerValor(ent);
        Double fila = (Double)filas.valor;
        Double colum = (Double)columnas.valor;
        int contVector =0;
        if(Data.tipo==Simbolo.EnumTipoDato.VECTOR){
            vector = (Vector)Data.valor;
        }
        Object[][] matrix = new Object[fila.intValue()][colum.intValue()];
        for(int i=0;i<colum.intValue();i++){
            for(int j=0;j<fila.intValue();j++){
                if(contVector>=vector.size()){
                    matrix[j][i]=vector.get(contVector-vector.size());
                    contVector=1;
                }else{
                    matrix[j][i]=vector.get(contVector);
                     contVector++;
                }
            }
        }
        return new Literal(Simbolo.EnumTipoDato.MATRIX,matrix);
    }

    @Override
    public Simbolo.EnumTipoDato getTipo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retornar ejecutar(Entorno ent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

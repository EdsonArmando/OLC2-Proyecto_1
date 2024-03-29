/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entorno;

/**
 *
 * @author EG
 */
public class Simbolo {
    public EnumTipoDato tipo;
    public String id;
    public int tamanioX,tamanioY,tamanioZ;
    public Object valor;
    public Simbolo(EnumTipoDato tipo,Object valor,String id){
        this.id = id;
        this.tipo = tipo;
        this.valor=valor;
    }
    public Simbolo(EnumTipoDato tipo,Object valor,String id,int x, int y, int z){
        this.id = id;
        this.tipo = tipo;
        this.valor=valor;
        this.tamanioX = x;
        this.tamanioY = y;
        this.tamanioZ = z;
    }
    public EnumTipoDato getTipo(){
        return this.tipo;
    }
    public Object getValor() {
        return this.valor;
    }
    void setValor(Object valor) {
        this.valor=valor;
    }
    public enum EnumTipoDato {
        CHAR,
        STRING,
        INT,
        ARREGLO,
        DOUBLE,
        BOOLEAN,
        NULL,
        ERROR,
        OBJETO,
        FUNCION,
        LIST,
        VECTOR, 
        MATRIX
    }
}

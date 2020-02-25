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
public interface Instruccion {
    public Retornar ejecutar(Entorno ent);
     public enum EnumTipoSentencia
        {
            DECLARACION,
            IMPRIMIR,
            REASIGNACION,
            COMPONENTE,
            LEERARCHIVO,
            GRAFICAR
        }
}

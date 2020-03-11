/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instruccion;

import Entorno.Entorno;
import Entorno.Simbolo;
import Expresion.Expresion;
import Views.Inicio;
import java.util.Vector;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author EG
 */
public class Grafica_Pie extends Funcion {
    public Grafica_Pie(String nombre,Simbolo.EnumTipoDato tipo){
        super(nombre,tipo);
        
    };
    public Grafica_Pie(String main, Expresion x, Expresion labels, TipoGrafica tipo) {
        super(main, x, labels, tipo);
    }
    
    @Override
    public Retornar ejecutar(Entorno ent) {
        
        return new Retornar();
    }

    @Override
    public Expresion obtenerValor(Entorno ent) {
        main = param_Actuales.get(2).obtenerValor(ent).valor.toString();
        x = param_Actuales.get(0).obtenerValor(ent);
        labels = param_Actuales.get(1).obtenerValor(ent);
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        Vector vectorNumerico = (Vector)x.valor;
        Vector vectorDescripciones = (Vector)labels.valor;
        if(vectorNumerico.size()!=vectorDescripciones.size()){
            Inicio.salidaConsola.append("La cantidad de parametros es diferente\n");
            return null;
        }
        for(int i=0;i<vectorDescripciones.size();i++){
            Expresion descr = (Expresion)vectorDescripciones.get(i);
            Expresion num = (Expresion)vectorNumerico.get(i);
            Double uno = (Double)num.obtenerValor(ent).valor;
            pieDataset.setValue(descr.obtenerValor(ent).valor.toString(), new Integer(uno.intValue()));
        }        
        JFreeChart chart = ChartFactory.createPieChart(
                main,
                pieDataset,
                true,
                true,
                false
        );

        //Mostramos la grafica en pantalla
        ChartFrame frame = new ChartFrame(main, chart);
        frame.pack();
        frame.setVisible(true);
        return null;
    }

    @Override
    public Simbolo.EnumTipoDato getTipo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

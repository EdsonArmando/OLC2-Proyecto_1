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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author EG
 */
public class Grafica_Barras extends Funcion {

    public Grafica_Barras(String main, Expresion h, String xlab, String ylab, Expresion names, TipoGrafica tipo) {
        super(main, h, xlab, ylab, names, tipo);
    }
    public Grafica_Barras(String nombre,Simbolo.EnumTipoDato tipo){
        super(nombre,tipo);
    }
    @Override
    public Retornar ejecutar(Entorno ent) {
       
        return new Retornar();
    }

    @Override
    public Expresion obtenerValor(Entorno ent) {
         Double uno=0.0;
        Expresion temp;
        Expresion name;
        main = param_Actuales.get(3).obtenerValor(ent).valor.toString();
        Vector numeros = (Vector)param_Actuales.get(0).obtenerValor(ent).valor;
        xlab = param_Actuales.get(1).obtenerValor(ent).valor.toString();
        ylab = param_Actuales.get(2).obtenerValor(ent).valor.toString();
        Vector names = (Vector)param_Actuales.get(4).obtenerValor(ent).valor;
        DefaultCategoryDataset categoria = new DefaultCategoryDataset();
        for(int i=0;i<names.size();i++){
            temp=(Expresion)numeros.get(i);
            name=(Expresion)names.get(i);
            uno = (Double)temp.obtenerValor(ent).valor;
            categoria.setValue(uno.intValue(), name.obtenerValor(ent).valor.toString(), name.obtenerValor(ent).valor.toString());
        }
        JFreeChart f = ChartFactory.createBarChart(main,xlab,ylab , categoria, PlotOrientation.VERTICAL, true, false, false);
        ChartFrame frame = new ChartFrame(main,f);
        frame.pack();
        frame.setVisible(true);
        return null;
    }

    @Override
    public Simbolo.EnumTipoDato getTipo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instruccion;

import Entorno.Entorno;
import Entorno.Simbolo;
import Expresion.Expresion;
import Instruccion.Funcion.TipoGrafica;
import java.util.Vector;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author EG
 */
public class Grafica_Dispersion extends Funcion {

    public Grafica_Dispersion(String nombre,Simbolo.EnumTipoDato tipo){
        super(nombre,tipo);
        
    };
    public Grafica_Dispersion(Expresion Matriz, String xlab, String yLab, Expresion xLim, Expresion yLim, String main, TipoGrafica tipoGrafica, Expresion booleana) {
        super(Matriz, xlab, yLab, xLim, yLim, main, tipoGrafica, booleana);
    }

    @Override
    public Retornar ejecutar(Entorno ent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Expresion obtenerValor(Entorno ent) {
        Expresion result1 = param_Actuales.get(0).obtenerValor(ent);
        Object[][] matrix = (Object[][])result1.valor;
        main = param_Actuales.get(2).obtenerValor(ent).valor.toString();
        xlab = param_Actuales.get(3).obtenerValor(ent).valor.toString();
        ylab = param_Actuales.get(4).obtenerValor(ent).valor.toString();
        Vector xlim = (Vector)param_Actuales.get(5).obtenerValor(ent).valor;
        Vector ylim = (Vector)param_Actuales.get(6).obtenerValor(ent).valor;
        boolean byrow=(boolean)param_Actuales.get(7).obtenerValor(ent).valor;
        XYSeries s = new XYSeries(id);
        XYSeriesCollection datos = new XYSeriesCollection();
        int cont=1;
        for(int j=0;j<matrix[0].length;j++){
            for(int i=0;i<matrix.length;i++){
                Expresion vec = (Expresion)matrix[i][j];
                Double val = (Double)vec.obtenerValor(ent).valor;
                s.add(cont, val.intValue());
                cont++;
            }
                
        }
        datos.addSeries(s);
        JFreeChart grafica = ChartFactory.createScatterPlot(main, xlab, ylab, datos, PlotOrientation.VERTICAL, true, true, true);
        ChartFrame frame = new ChartFrame(main, grafica);
        frame.pack();
        frame.setVisible(true);
        return null;
    }

    @Override
    public Simbolo.EnumTipoDato getTipo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

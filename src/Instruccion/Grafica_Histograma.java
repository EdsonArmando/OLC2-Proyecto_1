/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instruccion;

import Entorno.Entorno;
import Entorno.Simbolo;
import Expresion.Expresion;
import java.util.Vector;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.jfree.data.xy.IntervalXYDataset;

/**
 *
 * @author EG
 */
public class Grafica_Histograma extends Funcion{
     public Grafica_Histograma(String nombre,Simbolo.EnumTipoDato tipo){
        super(nombre,tipo);
        
    };
    public Grafica_Histograma(Expresion vector, String xlab, Expresion xLim, Expresion yLim, String main, TipoGrafica tipoGrafica) {
        super(vector, xlab, xLim, yLim, main, tipoGrafica);
    }

    @Override
    public Retornar ejecutar(Entorno ent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Expresion obtenerValor(Entorno ent) {
        //Valores numericos para la grafica}
        Vector valores=null;
        Vector xLim=null;
        Vector yLim=null;
        Expresion resultado = param_Actuales.get(0).obtenerValor(ent);
        Object[][] val_numericos = null;
        if(resultado.tipo==Simbolo.EnumTipoDato.VECTOR){
            valores = (Vector)param_Actuales.get(0).obtenerValor(ent).valor;
        }else{
            val_numericos = (Object[][])param_Actuales.get(0).obtenerValor(ent).valor;
        }
        double[] value = new double[valores.size()];
        main = param_Actuales.get(1).obtenerValor(ent).valor.toString();
        xlab = param_Actuales.get(2).obtenerValor(ent).valor.toString();
        xLim = (Vector)param_Actuales.get(3).obtenerValor(ent).valor;
        yLim = (Vector)param_Actuales.get(4).obtenerValor(ent).valor;
        HistogramDataset dataset = new HistogramDataset();
        for(int i=0;i<valores.size();i++){
            Expresion result = (Expresion)valores.get(i);
            Double uno = (Double)result.obtenerValor(ent).valor;
            value[i] = uno;
        }
        dataset.setType(HistogramType.FREQUENCY);
        dataset.addSeries("Histogram",value, 6);
        
        JFreeChart chart = ChartFactory.createHistogram(main, xlab, xlab, dataset, PlotOrientation.VERTICAL, true, true, true);
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

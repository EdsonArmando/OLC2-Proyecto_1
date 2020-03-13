/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instruccion;

import Entorno.Entorno;
import Entorno.Simbolo;
import Expresion.Expresion;
import java.awt.BasicStroke;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.Vector;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
/**
 *
 * @author EG
 */
public class Grafica_Linea extends Funcion{

    public Grafica_Linea(String nombre,Simbolo.EnumTipoDato tipo){
        super(nombre,tipo);
    }
    public Grafica_Linea(Expresion vector, String tipo, String xlab, String ylab, String main, TipoGrafica tipoGrafica) {
        super(vector, tipo, xlab, ylab, main, tipoGrafica);
    }

    @Override
    public Retornar ejecutar(Entorno ent) {
        
        return new Retornar();
    }

    @Override
    public Expresion obtenerValor(Entorno ent) {
        Expresion resultado,Coordenada;
        xlab = param_Actuales.get(2).obtenerValor(ent).valor.toString();
        ylab = param_Actuales.get(3).obtenerValor(ent).valor.toString();
        main = param_Actuales.get(4).obtenerValor(ent).valor.toString();
        Vector<Expresion> vector = (Vector)param_Actuales.get(0).obtenerValor(ent).valor;
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        int cont=1;
        for(int i=0;i<vector.size();i++){
            XYSeries series = new XYSeries("Grafica Numero"+(i+1));
            resultado = (Expresion)vector.get(i).obtenerValor(ent);
            if(resultado.tipo == Simbolo.EnumTipoDato.MATRIX){
                Object[][] tempMatriz = (Object[][])resultado.valor;
                for(int j=0;j<tempMatriz[0].length;j++){
                    for(int k=0;k<tempMatriz.length;k++){
                        Expresion result = (Expresion)tempMatriz[k][j];
                        Double uno = (Double)result.obtenerValor(ent).valor;
                        series.add(cont, uno.intValue());
                        cont++;
                    }
                }
            }else if(resultado.tipo == Simbolo.EnumTipoDato.VECTOR){
                Vector<Expresion> vec = (Vector)resultado.valor;
                for(int c=0;c<vec.size();c++){
                    Double val = (Double)vec.get(c).obtenerValor(ent).valor;
                    series.add(cont, val.intValue());
                    cont++;
                }
            }
            
            cont=1;
            dataset.addSeries(series);
            renderer.setSeriesPaint(i,retornarColor(i));
        }
        /*XYSeries series = new XYSeries("Grafica Numero1");
        XYSeries series2 = new XYSeries("Grafica Numero1");
        series.add(1,1);
        series.add(2,2);
        series.add(3,4);
        series.add(4,10);
        series2.add(1,3);
        series2.add(2,4);*/
        
        //renderer.setSeriesPaint(1, Color.BLUE);
        
        JFreeChart chart = ChartFactory.createXYLineChart(main,xlab,ylab,dataset,PlotOrientation.VERTICAL,true,false,false);
        //chart.getXYPlot().getRangeAxis().setRange(0, 50);
        ((NumberAxis) chart.getXYPlot().getRangeAxis()).setNumberFormatOverride(new DecimalFormat("#'%'"));
        chart.getXYPlot().setRenderer(renderer);
        ChartFrame frame = new ChartFrame("Nombre",chart);
        frame.pack();
        frame.setVisible(true);
        return null;
    }
    public Color retornarColor(int val){
        switch(val){
            case 1:
                return Color.BLUE;
            case 2:
                return Color.ORANGE;
        }
        return Color.CYAN;
    }
    @Override
    public Simbolo.EnumTipoDato getTipo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

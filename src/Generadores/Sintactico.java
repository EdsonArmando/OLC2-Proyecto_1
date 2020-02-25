/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generadores;

/**
 *
 * @author EG
 */
public class Sintactico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         int n = 0;
        String  opcionesARIT[] = new String[7];
        String destino = "-destdir",
        carpetaTexto = "src/AnalizadoresTexto/",
        carpetaCodigo = "src/Analizadores/",
        simbols = "-symbols",
        simbolsARIT = "Syma",
        par = "-parser",
        sintacticoARIT = "SintacticoArit";
        
        //Seleccionamos la opción de dirección de destino
        opcionesARIT[n] =  destino;
        
        //Le damos la dirección, carpeta donde se va a generar el parser.java & el simbolosxxx.java        
        n++;
        opcionesARIT[n]  = carpetaCodigo;
        
        //Seleccionamos la opción de nombre de archivo simbolos
        n++;
        opcionesARIT[n] = simbols;
        
        //Le damos el nombre que queremos que tenga
        n++;
        opcionesARIT[n] = simbolsARIT;
                
        //Seleccionamos la opcion de clase parser
        n++;
        opcionesARIT[n] = par;
        
        //Le damos nombre a esa clase del paso anterior
        n++;
        opcionesARIT[n] = sintacticoARIT; 
        
        //Le decimos donde se encuentra el archivo .cup 
        n++;
        opcionesARIT[n] = carpetaTexto + sintacticoARIT +".cup";
        try 
        {
            System.out.println("\n--------------------------------------------------------------------------");
            java_cup.Main.main(opcionesARIT);
        } 
        catch (Exception ex)
        {
            System.out.print(ex);
        }
    }
    
}

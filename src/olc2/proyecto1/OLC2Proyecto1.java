/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc2.proyecto1;

import Views.Inicio;

/**
 *
 * @author EG
 */
public class OLC2Proyecto1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Inicio inicio = new Inicio();
       inicio.setVisible(true);
       inicio.setLocale(null);
       //Hanoi(3,1,2,3);    
    }
    public static void Hanoi(int n,int origen,int auxiliar,int destino){
    if(n==1){
    System.out.println("mover disco de " + origen+ " a " + destino);
    }else{
    Hanoi(n-1, origen, destino, auxiliar);
    System.out.println("mover disco de "+ origen+ " a " + destino);
    Hanoi(n-1, auxiliar, origen, destino);
    }
    }
    public static int ackermann(int m,int n){
        if(m==0){
        return (n+1);
        }else{
        if((m>0) && (n==0)){
        return ackermann(m-1,1);
        }else{
        int in = ackermann(m,n-1);
        int nueva = ackermann(m-1,in);
        return nueva;
        }
        }
        }
    public static int fibonacci(int n)
{
    if (n>1){
       return fibonacci(n-1) + fibonacci(n-2);  //función recursiva
    }
    else if (n==1) {  // caso base
        return 1;
    }
    else if (n==0){  // caso base
        return 0;
    }
    else{ //error
        System.out.println("Debes ingresar un tamaño mayor o igual a 1");
        return -1; 
    }
}
    public static int fac(int n){
        if(n==0){
            return 1;
        }else{
            System.out.println(String.valueOf(n)+"*"+String.valueOf(n-1));
            return n *fac(n-1);
        }
    }
}

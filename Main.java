/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SOM;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author luis
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    //Este programa prueba el algoritmo 
    public static void main(String[] args) {
        Random  rnd = new Random();
        ArrayList<List<Double>> X=new ArrayList<List<Double>>();//vector de entradas
        ArrayList<List<Double>> X1=new ArrayList<List<Double>>();//vector de entradas
        ArrayList<List<Double>> X2=new ArrayList<List<Double>>();//vector de entradas
        ArrayList<List<Double>> X3=new ArrayList<List<Double>>();//vector de entradas
        
        
        
        for(int i=0;i<10;i++){
            List<Double> Coordenadas=new ArrayList<Double>();
            Coordenadas.add(8.0+rnd.nextDouble());
            Coordenadas.add(8.0+rnd.nextDouble());
            X.add(Coordenadas);
            X1.add(Coordenadas);
        }
        for(int i=0;i<10;i++){
            List<Double> Coordenadas=new ArrayList<Double>();
            Coordenadas.add(-8.0+rnd.nextDouble());
            Coordenadas.add(8.0+rnd.nextDouble());
            X.add(Coordenadas);
            X2.add(Coordenadas);
        }
        for(int i=0;i<10;i++){
            List<Double> Coordenadas=new ArrayList<Double>();
            Coordenadas.add(8.0+rnd.nextDouble());
            Coordenadas.add(-8.0+rnd.nextDouble());
            X.add(Coordenadas);
            X3.add(Coordenadas);
        }
        //se crea un histograma para puntos de 2 dimensiones(primer parametro)
        //, con Matriz de pesos de 10x10(segundo parametro)
        //La clase SOM ya es N- dimensional
        SOM SOM1=new SOM(2,10,X);
        SOM1.Run(20,1);
        //se obtiene el histograma del BMU, para verificar la efectividad del método
        System.out.print("\n Histograma clase 1 \n");
        System.out.print(SOM1.histograma(X1));
        System.out.print("\n Histograma clase 2 \n");
        System.out.print(SOM1.histograma(X2));
        System.out.print("\n Histograma clase 3 \n");
        System.out.print(SOM1.histograma(X3));
    }
}

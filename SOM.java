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
public class SOM {
    private final double delta0=1.5;
    private MatrizPesos W;//MATRIZ DE PESOS W
    private List<List<Double>> X; //Entradas
    private int N,M;//N tamaño del vector sinaptico, M tamaño del mapa
    private int n,m; //n y m es la posicion del BMU
    
    //Inicializamos la matriz de pesos de tamaño MXM y entrada N
    public SOM(int N,int M,ArrayList<List<Double>> X){
        this.X=X;
        this.M=M;
        W= new MatrizPesos(N,M);
    }
    public String histograma(ArrayList<List<Double>> Xprueba){
        double [][] histograma = new double[M][M];
        int numDatos;
        String cadena="\n";
        numDatos=Xprueba.size();
        for(int i=0;i<numDatos;i++){
            BMU(Xprueba.get(i));
            histograma[n][m]+=1;
        }
        for(int i=0;i<M;i++)
        {
            for(int j=0;j<M;j++)
            {
                cadena+=histograma[i][j];
                cadena+=" ";
            }
            cadena+="\n";
        }
        return cadena;
    }
    
    public void Run(int Ep,double alpha0){//recibe el numero de epocas
        int numDatos;
        numDatos=X.size();
        for(int ep=0;ep<Ep;ep++){   //Se repite por el numero de epocas
            for(int j=0;j<numDatos;j++){ // Se repite para todos los datos
                ModificaPesos(X.get(j),Ep,ep,alpha0); // Se modifican todos los pesos de la Matriz W, mediante la entrada X
            }
        }
    }
    
    private void ModificaPesos(List<Double> Xi,int Ep,int ep,double alpha0){//Vector de entrada i
        double h;
        double delta,alpha;
        BMU(Xi);                    //Se calcula el BMU  y se guardan sus posiciones en n y m
        for(int i=0;i<M;i++){       
            for(int j=0;j<M;j++)    //se realiza un recorrido por toda la matriz de pesos
            {
                delta=delta0*Math.exp(-((double)ep)/((double)Ep)); //calculamos delta para cada posicion
                alpha=alpha0*Math.exp(-((double)ep)/((double)Ep));  //calculamos alpha para cada posicion de la matriz
                h=Math.pow(n-i,2)+Math.pow(m-j,2);//calculamos h para cada posicion de la matriz
                h=Math.exp(-h/(2*Math.pow(delta, 2)));
                ModificaNeurona(Xi,i,j,alpha,h);    //Modificamos cada vector sinaptico asociado a la posicion de la matriz
            }
        }
    }
    
    private void ModificaNeurona(List<Double> Xi,int i,int j, double alpha,double h)
    {
        List<Double> vectorSinaptico;
        vectorSinaptico=W.getVectorSinaptico(i, j);
        //Actualizamos la posicion de cada vector sinaptico
        for(int k=0;k<N;k++){
            vectorSinaptico.set(k,vectorSinaptico.get(k)+h*alpha*(Xi.get(k)-vectorSinaptico.get(k)));
        }
        W.setVectorSinaptico(vectorSinaptico, i, j);
    }
    
    private void BMU(List<Double> Xi){
        double minimo;
        minimo=Distancia(Xi,W.getVectorSinaptico(0,0));
        for(int i=0;i<M;i++){
            for(int j=0;j<M;j++){
                if(Distancia(Xi,W.getVectorSinaptico(i,j))<minimo){
                    n=i;
                    m=j;
                    minimo=Distancia(Xi,W.getVectorSinaptico(i,j));
                }
            }
        }
    }
    
    
    private double Distancia(List<Double> a,List<Double> b){//distancia entre lista a y lista b
        int N;
        double dist = 0;
        N=a.size();
        for(int i=0;i<N;i++){
            dist+=Math.pow(a.get(i)-b.get(i),2);
        }
            dist=Math.sqrt(dist);
        return dist;
    }
}

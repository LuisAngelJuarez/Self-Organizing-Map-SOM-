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
public class MatrizPesos {
    private List<List<List<Double>>> W;//MATRIZ DE PESOS W
    private int N,M;
    
    public MatrizPesos(int N,int M){ //TAMAÑO DE LA ENTRADA, TAMAÑO DE LA REJILLA 
        W = new ArrayList<List<List<Double>>>();
        this.M=M;
        this.N=N;
        
        Random  rnd = new Random(); //se crea el objeto de tipo aleatorio
        for(int i=0;i<M;i++){
            W.add(new ArrayList<List<Double>>());
            for(int j=0;j<M;j++)
            {
                W.get(i).add(new ArrayList<Double>());
                for(int k=0;k<N;k++)
                {
                    W.get(i).get(j).add(rnd.nextDouble());
                }
            }
        }
    }
    
    
    public List<Double> getVectorSinaptico(int n,int m){
        return W.get(n).get(m);
    }
    public void setVectorSinaptico(List<Double> X,int n,int m){
        W.get(n).set(m, X);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.semaforos;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jmartin
 */
class acceso implements Runnable{

    private final Semaphore sem;
    private final int id;
    
    public acceso(Semaphore sem, int id){
        
        this.sem=sem;
        this.id=id;
    }
    
    public void run(){
        
        try {
            System.out.println("HILO"+id+"espera");
            sem.acquire();
            System.out.println("HILO"+id+"está");
            Thread.sleep(200);
            System.out.println("HILO"+id+"está saliendo");
            sem.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(acceso.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
public class Semaforos {

    public static void main(String[] args) {
        // Cuantos hilos pueden haber. Dos ventanillas
        Semaphore sem = new Semaphore(2);
        //Cuantos clientes llegan
        for (int i=1;i<=5;i++){
            Thread t = new Thread (new acceso (sem, i));
            t.start();
        }
    }
}

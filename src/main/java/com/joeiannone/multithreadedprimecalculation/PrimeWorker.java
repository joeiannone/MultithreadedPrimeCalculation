package com.joeiannone.multithreadedprimecalculation;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 *
 * @author josephiannone
 */
public class PrimeWorker implements Runnable {
    
    ArrayList<Integer> primes;
    int start, range;
    Semaphore semaphore;
    
    public PrimeWorker(Semaphore semaphore, ArrayList<Integer> primes, int start, int range) {
        this.semaphore = semaphore;
        this.primes = primes;
        this.start = start;
        this.range = range;
    }

    @Override
    public void run() {
        
        int s = this.start + 1;
        int e = this.start + this.range;
        
        
        //Let us know which thread is working 
        System.out.println(Thread.currentThread().getName()  + " starting on range " + s + " to " + e);
        
        // loop through range and test each value
        for (int i = s; i <= e; i++) {
           
            // if is prime
            if (PrimeFinder.isPrime(i)) {
                
                try {
                    
                    synchronized (this) {
                        
                        /**
                         * Lock ArrayList while adding prime for thread safety
                         */
                        this.semaphore.acquire();
                    
                        this.primes.add(i);
                    
                        this.semaphore.release();
                        
                    }
                    
                } catch (InterruptedException ex) {
                    
                    System.err.println(ex);
                
                }
              
            }
        }
        
    }
    
    
}

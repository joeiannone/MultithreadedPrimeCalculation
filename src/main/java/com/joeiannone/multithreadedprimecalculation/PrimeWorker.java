package com.joeiannone.multithreadedprimecalculation;

import java.util.ArrayList;

/**
 *
 * @author josephiannone
 */
public class PrimeWorker implements Runnable {
    
    ArrayList<Integer> primes;
    int start, range;
    
    public PrimeWorker(ArrayList<Integer> primes, int start, int range) {
        
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
                
                
                synchronized (this) {
                    /**
                     * Use synchronization on updating primes for thread safety
                     */
                    this.primes.add(i);
                    
                }
                
            }
        
        }
        
    }
    
    
}

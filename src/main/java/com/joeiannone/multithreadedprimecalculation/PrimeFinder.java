package com.joeiannone.multithreadedprimecalculation;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;


/**
 *
 * @author josephiannone
 */
public class PrimeFinder {
    
    public int MAX_VALUE, THREAD_COUNT, NUMBERS_PER_THREAD;
    
    public long start_ts, end_ts;
    
    private Thread[] threads;
    
    public final ArrayList<Integer> primes = new ArrayList<>();
    
    private static final Semaphore semaphore = new Semaphore(1, true);
    
    
    public PrimeFinder(int N, int THREAD_COUNT) {
        
        
        this.MAX_VALUE = N;
        this.THREAD_COUNT = THREAD_COUNT;
            
        this.NUMBERS_PER_THREAD = this.MAX_VALUE / this.THREAD_COUNT;
        
        // start timesstamp
	start_ts = System.currentTimeMillis();
        this.createThreads();
        end_ts = System.currentTimeMillis();
        
        
        
    }
    
    
    private void createThreads() {
        
        this.threads = new Thread[this.THREAD_COUNT];
        
        try {   
            /**
             *  create each thread and worker
             */
            for (int i=0; i < this.threads.length; i++) {
                
                /**
                 * Define this specific threads row count
                 */
                int range = this.NUMBERS_PER_THREAD;
                if (i == this.threads.length - 1)
                    // Last thread will take the excess
                    range += this.MAX_VALUE % this.THREAD_COUNT;
                
                /**
                 * Create thread and worker
                 */
                this.threads[i] = new Thread(new PrimeWorker(this.semaphore, this.primes, this.NUMBERS_PER_THREAD*i, range));
                this.threads[i].start();
                
            }
            
            /**
             * Wait for threads to die
             */
            for (Thread thread : this.threads) thread.join();
            
        } catch (InterruptedException ex) {
            System.err.println(ex);
        }
    }
    
    
    /**
     * Naive primality algorithm
     * 
     * @param n
     * @return 
     */
    public static boolean isPrime(int n) {
        /**
        * Prime number is number greater than 1 and are only divisible by 1 and itself
        */
        if (n <= 1) return false;
        
        for(int i = 2; i <= n / 2; ++i) {
            if(n % i == 0) return false;
        }
     
        return true;
    }
    
    
    public static void printPrimes(ArrayList<Integer> primes) {
        
        int i = 0;
        for (Integer prime : primes) {
            
            // new line every 20
            if (i % 20 == 0) System.out.println();
            
            System.out.print(prime + " ");
            i++;
        }
        System.out.println();
        
    }
       
    
}

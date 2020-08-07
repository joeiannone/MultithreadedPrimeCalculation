package com.joeiannone.multithreadedprimecalculation;

/**
 *
 * @author josephiannone
 */
public class Main {
    
    
    public static void main(String[] args) {
        
        PrimeFinder pf = new PrimeFinder(100000, 20);
        
        PrimeFinder.printPrimes(pf.primes);
        
        System.out.println();
        System.out.println("N = " + pf.MAX_VALUE);
        System.out.println("THREADS = " + pf.THREAD_COUNT);
        System.out.println("RANGE PER THREAD = " + pf.NUMBERS_PER_THREAD);
        System.out.println();
        System.out.println("-----------------------------------------------");
        System.out.println("Prime number count: " + pf.primes.size());
        System.out.println("Time to complete: " + (pf.end_ts - pf.start_ts) + "ms");
        System.out.println();
        
    }
}

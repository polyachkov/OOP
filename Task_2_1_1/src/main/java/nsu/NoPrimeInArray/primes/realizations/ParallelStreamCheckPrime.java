package nsu.NoPrimeInArray.primes.realizations;

import nsu.NoPrimeInArray.primes.AbstractCheckPrime;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class ParallelStreamCheckPrime extends AbstractCheckPrime {

    @Override
    public boolean hasNonePrime(int[] array, int NumberOfThreads){
        ForkJoinPool customThreadPool = new ForkJoinPool(NumberOfThreads);
        return customThreadPool.submit(() ->
                Arrays.stream(array)
                        .parallel()
                        .anyMatch(num -> !AbstractCheckPrime.isPrime(num))
        ).join();
    }
}
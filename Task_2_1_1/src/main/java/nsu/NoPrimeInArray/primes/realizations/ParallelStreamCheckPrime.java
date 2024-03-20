package nsu.NoPrimeInArray.primes.realizations;

import nsu.NoPrimeInArray.primes.AbstractCheckPrime;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class ParallelStreamCheckPrime extends AbstractCheckPrime {

    private final int NumberOfThreads;

    public ParallelStreamCheckPrime(int NumberOfThreads) {
        this.NumberOfThreads = NumberOfThreads;
    }

    @Override
    public boolean hasNonePrime(int[] array){
        ForkJoinPool customThreadPool = new ForkJoinPool(NumberOfThreads);
        return customThreadPool.submit(() ->
                Arrays.stream(array)
                        .parallel()
                        .anyMatch(num -> !AbstractCheckPrime.isPrime(num))
        ).join();
    }
}
package nsu.NoPrimeInArray.primes.realizations;

import nsu.NoPrimeInArray.primes.AbstractCheckPrime;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class ParallelStreamCheckPrime extends AbstractCheckPrime {

    @Override
    public boolean hasNonePrime(int[] array, int NumberOfThreads){
        ForkJoinPool customThreadPool = new ForkJoinPool(NumberOfThreads);
        try {
            return customThreadPool.submit(() ->
                    Arrays.stream(array)
                            .parallel()
                            .anyMatch(num -> !AbstractCheckPrime.isPrime(num))
            ).join();
        } finally {
            customThreadPool.close(); // закрыть пул потоков после использования
        }
    }
}
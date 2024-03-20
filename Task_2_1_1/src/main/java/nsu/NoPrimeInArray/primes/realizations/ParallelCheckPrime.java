package nsu.NoPrimeInArray.primes.realizations;

import nsu.NoPrimeInArray.primes.AbstractCheckPrime;
import nsu.NoPrimeInArray.primes.additions.PrimeCheckThread;
import nsu.NoPrimeInArray.primes.additions.ResultResource;

import java.util.Arrays;

public class ParallelCheckPrime extends AbstractCheckPrime {

    private final int NumberOfThreads;

    public ParallelCheckPrime(int NumberOfThreads) {
        this.NumberOfThreads = NumberOfThreads;
    }

    @Override
    public boolean hasNonePrime(int[] array) {
        ResultResource result = new ResultResource();
        Thread[] threads = new Thread[NumberOfThreads];
        int chunkSize = array.length / NumberOfThreads;
        for (int i = 0; i < NumberOfThreads; i++) {
            int start = i * chunkSize;
            int end = (i == NumberOfThreads - 1) ? array.length : start + chunkSize;

            int[] slicedArray = Arrays.copyOfRange(array, start, end);

            threads[i] = new PrimeCheckThread(slicedArray, result);
            threads[i].start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return result.isResult();
    }
}

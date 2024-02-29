package nsu.task_2_1_1.primes.realizations;

import nsu.task_2_1_1.primes.AbstractCheckPrime;
import nsu.task_2_1_1.primes.additions.PrimeCheckThread;
import nsu.task_2_1_1.primes.additions.ResultResource;

import java.util.Arrays;

public class ParallelCheckPrime extends AbstractCheckPrime {


    @Override
    public boolean hasNonePrime(int[] array, int NumberOfThreads) {
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

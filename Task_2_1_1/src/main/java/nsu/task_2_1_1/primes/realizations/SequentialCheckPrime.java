package nsu.task_2_1_1.primes.realizations;

import nsu.task_2_1_1.primes.AbstractCheckPrime;

import java.util.Arrays;

public class SequentialCheckPrime extends AbstractCheckPrime {

    public boolean hasNonePrime(int[] array, int NumberOfThreads){
        return Arrays.stream(array)
                .noneMatch(AbstractCheckPrime::isPrime);
    }
}

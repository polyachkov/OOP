package nsu.NoPrimeInArray.primes.realizations;

import nsu.NoPrimeInArray.primes.AbstractCheckPrime;

import java.util.Arrays;

public class SequentialCheckPrime extends AbstractCheckPrime {
    @Override
    public boolean hasNonePrime(int[] array){
        return Arrays.stream(array)
                .anyMatch(num -> !isPrime(num));
    }
}

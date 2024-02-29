package nsu.NoPrimeInArray.realizations;


import nsu.NoPrimeInArray.primes.realizations.ParallelCheckPrime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParallelCheckPrimeTest {
    @Test
    public void hasNonePrimeTest() {
        ParallelCheckPrime obj = new ParallelCheckPrime();

        int numberOfProcessors = Runtime.getRuntime().availableProcessors();
        int[] test1 = {1,2,3,4};
        int[] test2 = {2,3,5,7};

        assertTrue(obj.hasNonePrime(test1, numberOfProcessors));
        assertFalse(obj.hasNonePrime(test2, numberOfProcessors));
    }
}
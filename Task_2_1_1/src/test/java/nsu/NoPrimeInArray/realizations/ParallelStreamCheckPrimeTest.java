package nsu.NoPrimeInArray.realizations;

import nsu.NoPrimeInArray.primes.realizations.ParallelStreamCheckPrime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParallelStreamCheckPrimeTest {
    @Test
    public void hasNonePrimeTest() {
        int numberOfProcessors = Runtime.getRuntime().availableProcessors();
        ParallelStreamCheckPrime obj = new ParallelStreamCheckPrime(numberOfProcessors);


        int[] test1 = {1,2,3,4};
        int[] test2 = {2,3,5,7};

        assertTrue(obj.hasNonePrime(test1));
        assertFalse(obj.hasNonePrime(test2));
    }
}

package org.example;


import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;

public class TestPrimeNumbers {

    @Test
    public void testBfsIterator(){
        int[] test = {1, 2, 3};

        boolean ex = PrimeNumbers.hasNonPrimeSequential(test);
        assertTrue(ex);
    }
}

package nsu.task_2_1_1.graph;


import static org.junit.jupiter.api.Assertions.assertTrue;


import nsu.task_2_1_1.primes.additions.PrimeNumbers;
import org.junit.jupiter.api.Test;

public class TestPrimeNumbers {

    @Test
    public void testBfsIterator(){
        int[] test = {1, 2, 3};

        boolean ex = PrimeNumbers.hasNonPrimeSequential(test);
        assertTrue(ex);
    }
}

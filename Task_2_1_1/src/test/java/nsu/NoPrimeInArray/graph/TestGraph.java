package nsu.NoPrimeInArray.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Class foe test.
 */
public class TestGraph {

    /**
     * test method.
     */
    @Test
    public void generatePrimesTest() {
        int[] exp = {2,3,5,7};
        int[] test = Graph.generatePrimes(7);

        assertArrayEquals(exp, test);
    }

    @Test
    public void generateArrayTest() {
        int[] exp = {1,2,3,4,5,6};
        int[] test = Graph.generateArray(6);

        assertArrayEquals(exp, test);
    }
}

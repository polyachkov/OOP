package nsu.NoPrimeInArray.additions;

import nsu.NoPrimeInArray.primes.additions.PrimeCheckThread;
import nsu.NoPrimeInArray.primes.additions.ResultResource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPrimeCheckThread {

    @Test
    public void threadTest() throws InterruptedException {
        int[] test1 = {1,2,3,4};
        int[] test2 = {2,3,5,7};

        ResultResource res1 = new ResultResource();
        ResultResource res2 = new ResultResource();

        PrimeCheckThread thread1 = new PrimeCheckThread(test1, res1);
        PrimeCheckThread thread2 = new PrimeCheckThread(test2, res2);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        assertTrue(res1.isResult());
        assertFalse(res2.isResult());

    }
}

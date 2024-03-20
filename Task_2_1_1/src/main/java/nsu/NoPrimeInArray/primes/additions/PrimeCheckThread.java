package nsu.NoPrimeInArray.primes.additions;

import nsu.NoPrimeInArray.primes.AbstractCheckPrime;

import java.util.Arrays;

public class PrimeCheckThread extends Thread{

    private final int[] array;
    private final ResultResource resource;

    public PrimeCheckThread(int[] array, ResultResource resource) {
        this.array = array;
        this.resource = resource;
    }

    @Override
    public void run() {
        boolean result = Arrays.stream(array)
                .allMatch(AbstractCheckPrime::isPrime);
        synchronized (resource) {
            if (!result) {  // Меняем значение только в том случае, если нашли.
                resource.setResult(true);
            }
        }
    }

}

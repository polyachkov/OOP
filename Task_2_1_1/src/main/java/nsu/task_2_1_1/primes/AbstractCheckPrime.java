package nsu.task_2_1_1.primes;

abstract public class AbstractCheckPrime {

    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public abstract boolean hasNonePrime(int[] array, int NumberOfThreads);
}
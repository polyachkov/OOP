package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.List;

public class PrimeNumbers {

    public static int[] generatePrimes(int upperLimit) {
        List<Integer> primes = new ArrayList<>();
        // Перебираем числа от 2 до верхнего ограничения
        for (int i = 2; i <= upperLimit; i++) {
            // Проверяем, является ли текущее число простым
            boolean isPrime = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            // Если число простое, добавляем его в список
            if (isPrime) {
                primes.add(i);
            }
        }

        // Преобразуем список в массив
        int[] primesArray = new int[primes.size()];
        for (int i = 0; i < primes.size(); i++) {
            primesArray[i] = primes.get(i);
        }
        return primesArray;
    }
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

    // Последовательное решение
    public static boolean hasNonPrimeSequential(int[] nums) {
        for (int num : nums) {
            if (!isPrime(num)) {
                return true;
            }
        }
        return false;
    }

    // Параллельное решение с использованием Thread
    public static boolean hasNonPrimeParallelThread(int[] nums, int numThreads) throws InterruptedException {
        boolean[] results = new boolean[numThreads];
        Thread[] threads = new Thread[numThreads];
        int chunkSize = nums.length / numThreads;
        for (int i = 0; i < numThreads; i++) {
            final int index = i;
            threads[i] = new Thread(() -> {
                int start = index * chunkSize;
                int end = (index == numThreads - 1) ? nums.length : start + chunkSize;
                results[index] = hasNonPrimeSequential(Arrays.copyOfRange(nums, start, end));
            });
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        for (boolean result : results) {
            if (result) {
                return true;
            }
        }
        return false;
    }

    // Параллельное решение с использованием parallelStream
    public static boolean hasNonPrimeParallelStream(int[] nums) {
        return Arrays.stream(nums).parallel().anyMatch(num -> !isPrime(num));
    }
}

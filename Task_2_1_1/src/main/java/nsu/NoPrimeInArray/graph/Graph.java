package nsu.NoPrimeInArray.graph;

import nsu.NoPrimeInArray.primes.AbstractCheckPrime;
import nsu.NoPrimeInArray.primes.realizations.ParallelCheckPrime;
import nsu.NoPrimeInArray.primes.realizations.ParallelStreamCheckPrime;
import nsu.NoPrimeInArray.primes.realizations.SequentialCheckPrime;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Graph {

    static int numberOfProcessors = Runtime.getRuntime().availableProcessors();
    static int[] testData;

    public static int[] generateData(AbstractCheckPrime obj) {
        int[] result = new int[numberOfProcessors];
        for (int i = 1; i <= numberOfProcessors; i++) {
            long start = System.currentTimeMillis();
            obj.hasNonePrime(testData, i);
            long end = System.currentTimeMillis();
            result[i-1] = (int) (end - start);
        }
        return result;
    }

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

        return primes.stream().mapToInt(i -> i).toArray();
    }

    public static int[] generateArray(int N) {
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = i + 1;
        }
        return array;
    }

    private static XYSeries createSeries(String name, int[] xData, int[] yData) {
        XYSeries series = new XYSeries(name);
        for (int i = 0; i < xData.length; i++) {
            series.add(xData[i], yData[i]);
        }
        return series;
    }

    public static void main(String[] args) {
        int[] xData = generateArray(numberOfProcessors);

        testData = generatePrimes(5000000);
        testData[testData.length - 1] = 8;
        int[] yData1 = generateData(new SequentialCheckPrime());
        int[] yData2 = generateData(new ParallelCheckPrime());
        int[] yData3 = generateData(new ParallelStreamCheckPrime());



        XYSeries series1 = createSeries("Sequential", xData, yData1);
        XYSeries series2 = createSeries("Threads default", xData, yData2);
        XYSeries series3 = createSeries("parallel stream", xData, yData3);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);

        // Создаем график
        JFreeChart chart = ChartFactory.createXYLineChart(
                "a graph of some points",
                "Number Of Threads",
                "MS",
                dataset
        );


        // Отображаем график в окне
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Graph");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            ChartPanel chartPanel = new ChartPanel(chart);
            frame.add(chartPanel, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);
        });
    }
}

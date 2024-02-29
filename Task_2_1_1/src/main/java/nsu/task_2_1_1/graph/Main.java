package nsu.task_2_1_1.graph;

import nsu.task_2_1_1.primes.additions.PrimeNumbers;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.BorderLayout;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Создание тестового набора данных
        int[] testData = PrimeNumbers.generatePrimes(3000000);
        testData[testData.length - 1] = 7;

        int numThreads = Runtime.getRuntime().availableProcessors();
        System.out.println(numThreads);
        XYSeries series = new XYSeries("Data");


        long start = System.currentTimeMillis();
        PrimeNumbers.hasNonPrimeSequential(testData);
        long end = System.currentTimeMillis();
        series.add(1, end - start);

        for (int j = 2; j < numThreads + 2; j++) {
            start = System.currentTimeMillis();
            PrimeNumbers.hasNonPrimeParallelThread(testData, j - 1);
            end = System.currentTimeMillis();
            System.out.println(j - 1);
            series.add(j, end - start);
        }
        start = System.currentTimeMillis();
        PrimeNumbers.hasNonPrimeParallelStream(testData);
        end = System.currentTimeMillis();
        series.add(numThreads + 2, end - start);

        XYSeriesCollection dataset = new XYSeriesCollection(series);

        // Создаем график
        JFreeChart chart = ChartFactory.createXYLineChart(
                "a graph of some points",
                "Experiment Number",
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
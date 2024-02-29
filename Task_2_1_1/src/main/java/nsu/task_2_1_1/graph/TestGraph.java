package nsu.task_2_1_1.graph;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class TestGraph {
    public static void main(String[] args) {
        // Создаем набор данных
        XYSeries series = new XYSeries("Data");
        // Добавляем 17 точек
        for (int i = 0; i < 17; i++) {
            series.add(i, Math.random() * 100); // случайные значения для примера
        }
        XYSeriesCollection dataset = new XYSeriesCollection(series);

        // Создаем график
        JFreeChart chart = ChartFactory.createXYLineChart(
                "a graph of 17 points",
                "Experiment",
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

package nsu.pizza.additions;

import nsu.pizza.PizzeriaApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Queue;
import java.util.Scanner;

public class ReadInfo implements Runnable {
    static final Logger userLogger = LogManager.getLogger(ReadInfo.class);

    private final Queue<Order> orders;

    public ReadInfo(Queue<Order> orders){
        this.orders = orders;
    }

    public static String[] parseString1(String input) {
        String[] parts = input.split(" ");
        if (parts.length != 2 || !parts[0].matches("\\d+")) {
            throw new IllegalArgumentException("Неверный формат данных");
        }
        return parts;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            try{
                String[] parts = parseString1(input);
                int number = Integer.parseInt(parts[0]);
                String name = parts[1];
                synchronized (orders) {
                    orders.offer(new Order(number, name));
                }
            } catch (IllegalArgumentException Ex) {
                userLogger.error("НЕВЕРНЫЙ ФОРМАТ ДАННЫХ");
            }
        }
    }
}

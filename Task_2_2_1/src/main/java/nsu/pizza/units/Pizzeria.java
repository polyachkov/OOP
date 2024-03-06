package nsu.pizza.units;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import nsu.pizza.additions.BoolCooker;
import nsu.pizza.info.StructConfig;
import nsu.pizza.additions.Order;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Pizzeria {
    public static String[] parseString1(String input) {
        String[] parts = input.split(" ");
        if (parts.length != 2 || !parts[0].matches("\\d+")) {
            throw new IllegalArgumentException("Неверный формат данных");
        }
        return parts;
    }

    public static void main(String[] args) {
        BoolCooker cookerEnd = new BoolCooker();

        StructConfig myObject = null;
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("config.json")) {
            myObject = gson.fromJson(reader, StructConfig.class);
            System.out.println(myObject.storehouseNum);
        } catch (JsonSyntaxException | JsonIOException | IOException e) {
            e.printStackTrace();
        }

        Queue<Order> orders = new LinkedList<>();
        Queue<Order> storehouse = new LinkedList<>();

        long start = System.currentTimeMillis();
        long endTime = start + (myObject.timeToWork * 1000L);

        List<Thread> threadListCooker = new ArrayList<>();
        List<Thread> threadListDeliver = new ArrayList<>();

        for (int i = 0; i < myObject.cookers.length; i++) {
            Cooker tmp = new Cooker(myObject.cookers[i].timeToCook, orders, endTime, myObject.cookers[i].cookerNumber, storehouse, myObject.storehouseNum);
            tmp.start();
            threadListCooker.add(tmp);
        }

        for (int i = 0; i < myObject.delivers.length; i++) {
            Delivery tmp = new Delivery(myObject.delivers[i].numberOfPizzas, myObject.delivers[i].timeToDeliver, myObject.delivers[i].numberDeliver, storehouse, endTime, cookerEnd);
            tmp.start();
            threadListDeliver.add(tmp);
        }

        Thread userInputThread = new Thread(() -> {
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
                    System.out.println("НЕВЕРНЫЙ ФОРМАТ ДАННЫХ");
                }
            }
        });

        userInputThread.start();

        while(System.currentTimeMillis() < endTime) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for (Thread thread : threadListCooker) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Повара завершены");
        synchronized (cookerEnd){
            cookerEnd.setTrue();
        }

        for (Thread thread : threadListDeliver) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Все потоки завершили выполнение.");

    }
}

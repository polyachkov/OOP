package nsu.pizza;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import nsu.pizza.additions.BoolCooker;
import nsu.pizza.additions.ReadInfo;
import nsu.pizza.info.StructConfig;
import nsu.pizza.additions.Order;
import nsu.pizza.units.Cooker;
import nsu.pizza.units.Delivery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PizzeriaApplication {
    private static final Logger userLogger = LogManager.getLogger(PizzeriaApplication.class);
    public static void main(String[] args) { 
        BoolCooker cookerEnd = new BoolCooker();

        StructConfig myObject = null;
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("config.json")) {
            myObject = gson.fromJson(reader, StructConfig.class);
            System.out.println(myObject.storehouseNum);
        } catch (JsonSyntaxException | JsonIOException | IOException e) {
            userLogger.error("ERROR TO READ JSON");
            System.exit(1);
        }

        Queue<Order> orders = new LinkedList<>();
        Queue<Order> storehouse = new LinkedList<>();

        long start = System.currentTimeMillis();

        long endTime = 0;
        if (myObject != null) {
            endTime = start + (myObject.timeToWork * 1000L);
        }
        else {
            userLogger.error("ERROR TO READ JSON");
            System.exit(1);
        }

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

        Thread userInputThread = new Thread(new ReadInfo(orders));

        userInputThread.start();

        try {
            Thread.sleep(endTime - System.currentTimeMillis());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (Thread thread : threadListCooker) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                userLogger.error("Interrupted thread join Cookers");
            }
        }
        userLogger.info("Повара завершены");
        cookerEnd.setTrue();

        for (Thread thread : threadListDeliver) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                userLogger.error("Interrupted thread join Deliver");
            }
        }
        userLogger.info("Все потоки завершили выполнение");

    }
}

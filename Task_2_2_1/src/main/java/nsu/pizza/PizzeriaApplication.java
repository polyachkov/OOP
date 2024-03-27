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
    public static void endOfPizzeria(long endTime, List<Thread> threadListCooker, List<Thread> threadListDeliver, BoolCooker cookerEnd){
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

    private static void fillDelivers(StructConfig myObject, List<Thread> threadListDeliver, Queue<Order> storehouse, long endTime, BoolCooker cookerEnd){
        for (int i = 0; i < myObject.delivers.length; i++) {
            Delivery tmp = new Delivery(myObject.delivers[i].numberOfPizzas,
                    myObject.delivers[i].timeToDeliver,
                    myObject.delivers[i].numberDeliver,
                    storehouse,
                    endTime,
                    cookerEnd);
            tmp.start();
            threadListDeliver.add(tmp);
        }
    }
    private static void fillCookers(StructConfig myObject, List<Thread> threadListCooker, Queue<Order> orders,long endTime, Queue<Order> storehouse){
        for (int i = 0; i < myObject.cookers.length; i++) {
            Cooker tmp = new Cooker(myObject.cookers[i].timeToCook,
                    orders,
                    endTime,
                    myObject.cookers[i].cookerNumber,
                    storehouse,
                    myObject.storehouseNum);
            tmp.start();
            threadListCooker.add(tmp);
        }
    }

    private static StructConfig readJson(Gson gson){
        StructConfig myObject = null;
        try (FileReader reader = new FileReader("config.json")) {
            myObject = gson.fromJson(reader, StructConfig.class);
            userLogger.info(myObject.storehouseNum);
        } catch (JsonSyntaxException | JsonIOException | IOException e) {
            userLogger.error("ERROR TO READ JSON");
            System.exit(1);
        }
        return myObject;
    }

    private static final Logger userLogger = LogManager.getLogger(PizzeriaApplication.class);
    public static void main(String[] args) {
        BoolCooker cookerEnd = new BoolCooker();
        Gson gson = new Gson();

        Queue<Order> orders = new LinkedList<>();
        Queue<Order> storehouse = new LinkedList<>();

        List<Thread> threadListCooker = new ArrayList<>();
        List<Thread> threadListDeliver = new ArrayList<>();

        StructConfig myObject = readJson(gson);

        long start = System.currentTimeMillis();

        long endTime = 0;
        if (myObject != null) {
            endTime = start + (myObject.timeToWork * 1000L);
        }
        else {
            userLogger.error("ERROR TO READ JSON");
            System.exit(1);
        }



        fillDelivers(myObject, threadListDeliver, storehouse, endTime, cookerEnd);
        fillCookers(myObject, threadListCooker, orders, endTime, storehouse);

        Thread userInputThread = new Thread(new ReadInfo(orders));

        userInputThread.start();

        endOfPizzeria(endTime, threadListCooker, threadListDeliver, cookerEnd);

    }
}

package nsu.pizza.units;

import nsu.pizza.additions.BoolCooker;
import nsu.pizza.additions.Order;
import nsu.pizza.exceptions.NoOrderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Delivery extends Thread{
    private static final Logger userLogger = LogManager.getLogger(Delivery.class);
    private final int numberOfPizzas;
    private final int timeToDeliver;
    private final int deliverNumber;
    private final Queue<Order> storehouse;
    private final List<Order> currentOrders;
    private final long endTime;
    private final BoolCooker cookerEnd;

    public List<Order> getCurrentOrders() {
        return currentOrders;
    }

    public Delivery(int numberOfPizzas, int timeToDeliver, int deliverNumber, Queue<Order> storehouse, long endTime, BoolCooker cookerEnd) {
        this.numberOfPizzas = numberOfPizzas;
        this.deliverNumber = deliverNumber;
        this.storehouse = storehouse;
        this.timeToDeliver = timeToDeliver;
        this.endTime = endTime;
        this.currentOrders = new ArrayList<>();
        this.cookerEnd = cookerEnd;
    }

    private void wait(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String soutOrd(List<Order> orders) {
        StringBuilder tmp = new StringBuilder();
        for (Order ord : orders) {
            tmp.append(ord.getOrderNum());
            tmp.append(" ");
        }
        return tmp.toString();
    }

    @Override
    public void run() {
        userLogger.info("Deliver number " + deliverNumber + " started.");
        while(true){
            if (currentOrders.isEmpty()) {
                while (true) {
                    try{
                        currentOrders.addAll(checkAndGetOrders());
                        String numbersOrd = soutOrd(currentOrders);
                        userLogger.info("Deliver number "
                                + deliverNumber + " get orders with numbers " + numbersOrd + "\n");
                        break;
                    } catch (NoOrderException Ex) {
                        if(System.currentTimeMillis() > endTime && cookerEnd.value) {
                            interrupt();
                            break;
                        }
                        wait(1);
                    }
                }
                if(isInterrupted()) {
                    userLogger.info("Deliver " + deliverNumber + " ended");
                    break;
                }
            } else{
                wait(timeToDeliver);
                currentOrders.clear();
                userLogger.info("Deliver number " + deliverNumber + " ended order");
            }
        }
    }

    private List<Order> checkAndGetOrders() throws NoOrderException {
        synchronized (storehouse) {
            List<Order> tmp = new ArrayList<>();
            if (storehouse.isEmpty()) {
                throw new NoOrderException("no orders");
            } else {
                for (int i = 0; i < numberOfPizzas; i++) {
                    tmp.add(storehouse.poll());
                    if (storehouse.isEmpty()) {
                        break;
                    }
                }
                return tmp;
            }
        }
    }
}

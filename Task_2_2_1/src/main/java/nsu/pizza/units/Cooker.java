package nsu.pizza.units;

import nsu.pizza.additions.Order;
import nsu.pizza.exceptions.NoOrderException;
import nsu.pizza.exceptions.NotEnoughSpace;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Cooker extends Thread{
    private final Queue<Order> orderQueue;
    private final int cookerNumber;
    private final int timeToCook;
    private final long endTime;
    private final Queue<Order> storehouse;
    private final int spaceStoreHouse;
    Order currentOrder = null;

    public Cooker(int timeToCook, Queue<Order> orderQueue, long endTime, int cookerNumber, Queue<Order> storehouse, int spaceStoreHouse){
        this.timeToCook = timeToCook;
        this.orderQueue = orderQueue;
        this.endTime = endTime;
        this.cookerNumber = cookerNumber;
        this.storehouse = storehouse;
        this.spaceStoreHouse = spaceStoreHouse;
    }

    private void wait(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        System.out.println("Cooker number " + cookerNumber + " started.");
        while(true){
            if (currentOrder == null) {
                while (true) {
                    try{
                        currentOrder = checkAndGetOrder();
                        System.out.println("Cooker number "
                                + cookerNumber + " get order number "
                                + currentOrder.getOrderNum() + " "
                                + currentOrder.getOrderName());
                        break;
                    } catch (NoOrderException Ex) {
                        if(System.currentTimeMillis() > endTime) {
                            interrupt();
                            break;
                        }
                        wait(1);
                    }
                }
                if(isInterrupted()) {
                    System.out.println("Cooker " + cookerNumber + " ended");
                    break;
                }
            } else{
                wait(timeToCook);
                while (true) {
                    try {
                        pushOrder();
                        break;
                    } catch (NotEnoughSpace e) {
                        wait(1);
                    }
                }
            }
        }
    }

    private Order checkAndGetOrder() throws NoOrderException {
        synchronized (orderQueue){
            if (orderQueue.isEmpty()){
                throw new NoOrderException("no orders");
            }
            else{
                return orderQueue.poll();
            }
        }
    }

    private void pushOrder() throws NotEnoughSpace {
        synchronized (storehouse) {
            if(storehouse.size() == spaceStoreHouse) {
                throw new NotEnoughSpace("space is zero");
            }
            else {
                storehouse.offer(currentOrder);
                System.out.println("Cooker " + cookerNumber + " pushed order " + currentOrder.getOrderNum());
                currentOrder = null;
            }
        }
    }

}

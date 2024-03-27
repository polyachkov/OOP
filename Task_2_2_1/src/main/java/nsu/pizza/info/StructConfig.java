package nsu.pizza.info;

import nsu.pizza.units.Cooker;

public class StructConfig {
    public int storehouseNum;
    public CookerInfo[] cookers;
    public DeliveryInfo[] delivers;
    public int timeToWork;

    public int getStorehouseNum() {
        return storehouseNum;
    }

    public void setStorehouseNum(int storehouseNum) {
        this.storehouseNum = storehouseNum;
    }

    public CookerInfo[] getCookers() {
        return cookers;
    }

    public void setCookers(CookerInfo[] cookers) {
        this.cookers = cookers;
    }

    public DeliveryInfo[] getDelivers() {
        return delivers;
    }

    public void setDelivers(DeliveryInfo[] delivers) {
        this.delivers = delivers;
    }

    public int getTimeToWork() {
        return timeToWork;
    }

    public void setTimeToWork(int timeToWork) {
        this.timeToWork = timeToWork;
    }
}

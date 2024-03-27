package nsu.pizza.additions;

public class BoolCooker {
    public boolean value = false;

    public synchronized void setTrue() {
        value = true;
    }
}

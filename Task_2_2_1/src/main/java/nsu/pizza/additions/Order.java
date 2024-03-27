package nsu.pizza.additions;

public class Order {
    private int orderNum;
    private String orderName;

    public Order(int orderNum, String orderName) {
        this.orderName = orderName;
        this.orderNum = orderNum;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderName() {
        return String.valueOf(orderName);
    }

    public void setOrderName(String orderName) {
        this.orderName = String.valueOf(orderName);
    }
}

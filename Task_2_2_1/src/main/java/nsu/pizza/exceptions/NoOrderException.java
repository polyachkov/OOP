package nsu.pizza.exceptions;


public class NoOrderException extends Exception {
    public NoOrderException() {
        super();
    }
    public NoOrderException(String message) {
        super(message);
    }
}
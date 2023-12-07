package org.example.exceptions;


/**
 * my exception.
 */
public class IllegalExpressionException extends Throwable {

    /**
     * IllegalExpressionException constructor.
     */
    public IllegalExpressionException() {
        super();
    }
    /**
     * IllegalExpressionException with message.
     */
    public IllegalExpressionException(String message) {
        super(message);
    }
}
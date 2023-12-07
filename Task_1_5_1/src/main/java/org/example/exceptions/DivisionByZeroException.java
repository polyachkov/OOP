package org.example.exceptions;


/**
 * my exception.
 */
public class DivisionByZeroException extends Throwable {

    /**
     * DivisionByZeroException constructor.
     */
    public DivisionByZeroException() {
        super();
    }
    /**
     * DivisionByZeroException with message.
     */
    public DivisionByZeroException(String message) {
        super(message);
    }
}
package org.example.exceptions;

/**
 * my exception.
 */
public class RootLessThanZeroException extends Throwable {

    /**
     * RootLessThanZeroException constructor.
     */
    public RootLessThanZeroException() {
        super();
    }
    /**
     * RootLessThanZeroException with message.
     */
    public RootLessThanZeroException(String message) {
        super(message);
    }
}
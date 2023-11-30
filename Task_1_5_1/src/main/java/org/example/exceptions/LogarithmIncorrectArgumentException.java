package org.example.exceptions;

/**
 * my exception.
 */
public class LogarithmIncorrectArgumentException extends Throwable {

    /**
     * LogarithmIncorrectArgumentException constructor.
     */
    public LogarithmIncorrectArgumentException() {
        super();
    }
    /**
     * LogarithmIncorrectArgumentException with message.
     */
    public LogarithmIncorrectArgumentException(String message) {
        super(message);
    }
}
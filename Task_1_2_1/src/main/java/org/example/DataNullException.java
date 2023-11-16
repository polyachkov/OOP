package org.example;


/**
 * my exception
 */
public class DataNullException extends Throwable {

    /**
     * Data null exception without message constructor.
     */
    public DataNullException() {
        super();
    }

    /**
     * Data null exception without message constructor.
     */
    public DataNullException(String message) {
        super(message);
    }
}

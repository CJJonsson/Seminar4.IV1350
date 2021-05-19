package se.kth.iv1350.saleProcess.integration;

/**
 * This exception is thrown if the inventory system cannot access the item database.
 */
public class DatabaseDownException extends Exception{

    /**
     * Creates a new DatabaseDownException instance.
     *
     * @param msg The message created when the exception occurs.
     */
    public DatabaseDownException (String msg){
        super(msg);
    }
}

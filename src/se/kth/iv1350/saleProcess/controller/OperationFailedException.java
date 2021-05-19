package se.kth.iv1350.saleProcess.controller;

/**
 * This exception is thrown when an operation fails.
 */
public class OperationFailedException extends Exception{

    /**
     * Creates a new OperationFailedException instance.
     *
     * @param msg   The exception message created when the exception occurs.
     * @param cause The cause of the exception.
     */
    public OperationFailedException (String msg, Exception cause){
        super(msg, cause);
    }
}

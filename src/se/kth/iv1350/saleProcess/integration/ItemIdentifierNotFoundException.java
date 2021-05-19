package se.kth.iv1350.saleProcess.integration;

/**
 * This exception is thrown if an invalid item identifier is entered.
 */
public class ItemIdentifierNotFoundException extends Exception{

    /**
     * Creates a new ItemIdentifierNotFoundException instance.
     *
     * @param msg The message created when the exception occurs.
     */
    public ItemIdentifierNotFoundException (String msg){
        super(msg);
    }
}

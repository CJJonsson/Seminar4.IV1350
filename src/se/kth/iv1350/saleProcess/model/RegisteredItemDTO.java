package se.kth.iv1350.saleProcess.model;

/**
 * Represents an particular registered item in terms of description and price.
 * Also includes the current running total.
 */
public class RegisteredItemDTO {
    private String itemDescription;
    private double itemPrice;
    private double runningTotalIncVAT;

    /**
     * Creates a new RegisteredItemDTO instance.
     *
     * @param itemDescription       The description of the registered item.
     * @param itemPrice             The price of the registered item.
     * @param runningTotalIncVAT    The current running total of the sale.
     */
    public RegisteredItemDTO (String itemDescription, double itemPrice, double runningTotalIncVAT){
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.runningTotalIncVAT = runningTotalIncVAT;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public double getRunningTotalIncVAT() {
        return runningTotalIncVAT;
    }
}

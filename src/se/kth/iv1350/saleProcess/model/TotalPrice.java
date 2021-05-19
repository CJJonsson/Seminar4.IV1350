package se.kth.iv1350.saleProcess.model;

import se.kth.iv1350.saleProcess.integration.ItemDTO;

/**
 * Represents the total price of a sale.
 */
public class TotalPrice {
    private double runningTotal;
    private double totalVAT;

    /**
     * Creates a new TotalPrice instance.
     */
    public TotalPrice(){
    this.runningTotal = 0;
    this.totalVAT = 0;
    }

    public double getRunningTotal(){
        return runningTotal;
    }

    public double getTotalVat(){
        return totalVAT;
    }

    /**
     * Increases the total price and the total VAT.
     *
     * @param item An itemDTO which includes information about item price and item VAT rate.
     */
    public void increasePriceOfSale(ItemDTO item){
        double itemPriceIncludingVAT = item.getPrice() * (1 + item.getVATrate());
        double itemVAT = item.getPrice() * item.getVATrate();
        totalVAT = totalVAT + itemVAT;
        runningTotal = runningTotal + itemPriceIncludingVAT;
    }

    /**
     * Calculates the running total after discount.
     *
     * @param discountRate The discount rate of the sale.
     */

    public void addDiscount (double discountRate){
        runningTotal = runningTotal * (1 - discountRate);
    }
}

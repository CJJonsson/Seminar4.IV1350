package se.kth.iv1350.saleProcess.model;

/**
 * An interface for calculating the discount rate.
 */
public interface DiscountCalculator {

    /**
     * Calculates the discount rate.
     *
     * @param sale          The current sale.
     * @param totalPrice    The total price of the sale.
     * @return              The discount rate.
     */
    double calculateDiscount(Sale sale, double totalPrice);

}

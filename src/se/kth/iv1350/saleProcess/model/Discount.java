package se.kth.iv1350.saleProcess.model;

/**
 *  Represents the discount in a sale.
 */
public class Discount {
    private double discountRate;

    /**
     * Initializes the discount rate according to the membership status of the customer.
     *
     * @param membershipStatus  The membership status of the customer.
     * @param sale              The current sale.
     * @param totalPrice        The total price of the sale.
     */
    public void checkDiscountRate (String membershipStatus, Sale sale, double totalPrice){

        DiscountCalculatorFactory factory = new DiscountCalculatorFactory();
        if (factory.getDiscountCalculator(membershipStatus) != null)
            discountRate = factory.getDiscountCalculator(membershipStatus).calculateDiscount(sale, totalPrice);
        else
            discountRate = 0;
    }

    public double getDiscountRate() {
        return discountRate;
    }
}

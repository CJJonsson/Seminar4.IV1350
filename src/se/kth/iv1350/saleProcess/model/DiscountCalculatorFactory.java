package se.kth.iv1350.saleProcess.model;

import se.kth.iv1350.saleProcess.util.HighDiscountCalculator;
import se.kth.iv1350.saleProcess.util.LowDiscountCalculator;
import se.kth.iv1350.saleProcess.util.MediumDiscountCalculator;

/**
 *  A factory class that creates instances of the algorithm used to calculate
 *  the discount rate dependent on the the customers membership.
 */
public class DiscountCalculatorFactory {

    /**
     * Returns a DiscountCalculator according to the customers membership.
     *
     * @param membershipStatus  The membership of the customer.
     * @return                  The DiscountCalculator according to the customers membership.
     */
    public DiscountCalculator getDiscountCalculator(String membershipStatus) {
        if (membershipStatus.equals("high"))
            return new HighDiscountCalculator();
        else if (membershipStatus.equals("medium"))
            return new MediumDiscountCalculator();
        else if (membershipStatus.equals("low"))
            return new LowDiscountCalculator();
        else
            return null;
    }
}

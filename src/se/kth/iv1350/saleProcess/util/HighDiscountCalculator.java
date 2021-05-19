package se.kth.iv1350.saleProcess.util;

import se.kth.iv1350.saleProcess.integration.ItemDTO;
import se.kth.iv1350.saleProcess.model.DiscountCalculator;
import se.kth.iv1350.saleProcess.model.Sale;
import se.kth.iv1350.saleProcess.model.SaleItem;
import java.util.Set;

/**
 * Calculates the discount rate for customers with a gold membership.
 */
public class HighDiscountCalculator implements DiscountCalculator {
    private int itemQuantity;
    private double discountRate;

    /**
     * Creates a new HighDiscountCalculator instance.
     */
    public HighDiscountCalculator(){
    this.itemQuantity = 0;
    this.discountRate = 0;
    }

    @Override
    public double calculateDiscount(Sale sale, double totalPrice) {
        itemQuantity = checkItemQuantity(sale);

        if (itemQuantity >= 3)
            discountRate = 0.1;
        else
            discountRate = 0.05;

        if (totalPrice >= 50)
            discountRate += 0.1;
        else
            discountRate += 0.05;

        return discountRate;
    }

    private int checkItemQuantity(Sale sale) {
        Set<ItemDTO> setOfItemDTOs = sale.getSaleItems().keySet();
        for (ItemDTO keys: setOfItemDTOs) {
            SaleItem itemForInfo = sale.getSaleItems().get(keys);
            itemQuantity += itemForInfo.getQuantity();
        }
        return itemQuantity;
    }

}

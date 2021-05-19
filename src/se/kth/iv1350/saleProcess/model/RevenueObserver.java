package se.kth.iv1350.saleProcess.model;

/**
 * An observer interface for receiving notification about the revenue of finished sales.
 */
public interface RevenueObserver {
    /**
     * Invoked when an sale i finished, updates the total revenue.
     *
     * @param totalSalePrice the total price/revenue of the finished sale.
     */
    void updateRevenue(double totalSalePrice);

}

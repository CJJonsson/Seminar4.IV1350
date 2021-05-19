package se.kth.iv1350.saleProcess.view;

import se.kth.iv1350.saleProcess.model.RevenueObserver;

/**
 * Prints the total revenue of all sales to the display.
 */
public class TotalRevenueView implements RevenueObserver {
    private double totalRevenue;

    /**
     * Creates a new TotalRevenueView instance with total revenue initiated to 0.
     */
    public TotalRevenueView(){
        totalRevenue = 0;
    }

    @Override
    public void updateRevenue (double saleRevenue){
        totalRevenue += saleRevenue;
        displayRevenue();
    }

    private void displayRevenue(){
        String newLine = "\n";
        StringBuilder builder = new StringBuilder();
        builder.append("--Total revenue--");
        builder.append(newLine);
        builder.append(String.format("%.02f", totalRevenue));
        builder.append(newLine);
        builder.append("-----------------");
        builder.append(newLine);
        System.out.println(builder);
    }
}

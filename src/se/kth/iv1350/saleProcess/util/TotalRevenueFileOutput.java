package se.kth.iv1350.saleProcess.util;

import se.kth.iv1350.saleProcess.model.RevenueObserver;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Prints the total revenue of all sales to a text file.
 */
public class TotalRevenueFileOutput implements RevenueObserver {
    private double totalRevenue;
    private PrintWriter logRevenueFile;

    /**
     * Creates a new TotalRevenueFileOutput instance with total revenue initiated to 0.
     */
    public TotalRevenueFileOutput(){
        totalRevenue = 0;
        try {
            logRevenueFile = new PrintWriter(new FileWriter("totalRevenue.txt"), true);
        }
        catch (IOException exception){
            System.out.println("Unable to create revenue log-file");
            exception.printStackTrace();
        }
    }

    @Override
    public void updateRevenue (double saleRevenue){
        totalRevenue += saleRevenue;
        logRevenueFile.println("Total revenue: " + String.format("%.02f", totalRevenue));
    }


}

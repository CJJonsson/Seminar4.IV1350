package se.kth.iv1350.saleProcess.startup;

import se.kth.iv1350.saleProcess.controller.Controller;
import se.kth.iv1350.saleProcess.integration.DiscountRegister;
import se.kth.iv1350.saleProcess.integration.SaleRegistry;
import se.kth.iv1350.saleProcess.integration.SystemCreator;
import se.kth.iv1350.saleProcess.view.View;

/**
 * Contains the <code>main</code> method which do the startup of the application.
 */
public class Main {
    /**
    * Starts the application.
    *
    * @param args The application do not take any command line parameters.
    */
    public static void main(String[] args){
        SaleRegistry saleRegistry = new SaleRegistry();
        DiscountRegister discountRegister = new DiscountRegister();
        SystemCreator systemCreator = SystemCreator.getSystemCreator();
        Controller controller = new Controller(saleRegistry, systemCreator, discountRegister);
        new View(controller).sampleExecution();
    }
}

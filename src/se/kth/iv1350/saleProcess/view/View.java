package se.kth.iv1350.saleProcess.view;

import se.kth.iv1350.saleProcess.controller.Controller;
import se.kth.iv1350.saleProcess.controller.OperationFailedException;
import se.kth.iv1350.saleProcess.model.RegisteredItemDTO;
import se.kth.iv1350.saleProcess.util.TotalRevenueFileOutput;

/**
 * This application does not have a view. As a substitute, this class acts as
 * a placeholder for the view and conducts a sample execution.
 */
public class View {
    private Controller controller;
    private ErrorMessageHandler errorMessageHandler = new ErrorMessageHandler();

    /**
     * Creates a new view instance.
     *
     * @param controller The controller that is used for every operation.
     */
    public View(Controller controller) {
        this.controller = controller;
        controller.addRevenueObserver(new TotalRevenueView());
        controller.addRevenueObserver(new TotalRevenueFileOutput());
    }

    /**
     * A sample execution that simulates a sale process
     * and thereby makes calls to every system operation.
     */
    public void sampleExecution(){
    System.out.println("Cashier starts a new sale\n");
    controller.startNewSale();

    System.out.println("Cashier enters item identifiers\n");
    System.out.println("Cashier enters 111:");
    scanItem(111);
    System.out.println("Cashier enters 112:");
    scanItem(112);
    System.out.println("Cashier enters 114:");
    scanItem(114);
    System.out.println("Cashier enters 113:");
    scanItem(113);
    System.out.println("Cashier enters 111:");
    scanItem(111);
    System.out.println("Cashier enters 115:");
    scanItem(115);

    System.out.println("Cashier ends the sale\n");
    double totalPrice = controller.endSale();
    System.out.println("Total price: " +  String.format("%.02f", totalPrice));

    System.out.println("\nCustomer says they are eligible for a discount\n");
    totalPrice = controller.discountRequest(19980808);
    System.out.println("Total price after discount: " +  String.format("%.02f", totalPrice));

    System.out.println("\nCashier enters amount paid: 110\n");
    double change = controller.pay( 110);
    System.out.println("Change: " +  String.format("%.02f", change));
    }

    private void scanItem (int identifier){
        try{
            RegisteredItemDTO regItemDTO = controller.scanCurrentItem(identifier);
            displayRegisteredItemInfo(regItemDTO);
        }
        catch (OperationFailedException exception){
            manageException(exception);
        }
    }

    private void displayRegisteredItemInfo(RegisteredItemDTO regItemDTO){
        StringBuilder builder = new StringBuilder();
        builder.append(regItemDTO.getItemDescription());
        builder.append("\nPrice (ex. VAT): ");
        builder.append(regItemDTO.getItemPrice());
        builder.append("\nRunning Total (inc. VAT): ");
        builder.append(String.format("%.02f", regItemDTO.getRunningTotalIncVAT()));
        builder.append("\n");
        System.out.println(builder);
    }

    private void manageException (Exception exception){
        errorMessageHandler.showErrorMessage(exception.getMessage());
    }
}

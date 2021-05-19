package se.kth.iv1350.saleProcess.controller;

import se.kth.iv1350.saleProcess.integration.*;
import se.kth.iv1350.saleProcess.model.*;
import se.kth.iv1350.saleProcess.util.ExceptionLogger;

import java.util.ArrayList;
import java.util.List;

/**
 * The application's only controller class, all model calls pass through this class.
 */
public class Controller {
    private InventorySystem inventorySystem;
    private AccountingSystem accountingSystem;
    private Printer printer;
    private SaleRegistry saleRegistry;
    private CashRegister cashRegister;
    private DiscountRegister discountRegister;
    private Sale sale;
    private List<RevenueObserver> revenueObservers = new ArrayList<>();
    private ExceptionLogger exceptionLogger = new ExceptionLogger();

    /**
     * Creates a new controller instance.
     *
     * @param saleRegistry  Reference to saleRegistry.
     * @param systemCreator Used to get all classes that are external systems.
     */
    public Controller(SaleRegistry saleRegistry, SystemCreator systemCreator, DiscountRegister discountRegister) {
        this.inventorySystem = systemCreator.getInventorySystem();
        this.accountingSystem = systemCreator.getAccountingSystem();
        this.printer = systemCreator.getPrinter();
        this.saleRegistry = saleRegistry;
        this.cashRegister = new CashRegister();
        this.discountRegister = discountRegister;
    }

    /**
     * Creates a new sale.
     */
    public void startNewSale(){
        this.sale = new Sale();
        sale.addRevenueObservers(revenueObservers);
    }

    /**
     * Adds the scanned item to the sale.
     * 
     * @param itemIdentifier    Used to identify the scanned item.
     * @return                  Information about the scanned item and the runningTotal.
     * @throws OperationFailedException  If the operation cannot be performed properly.
     */
    public RegisteredItemDTO scanCurrentItem(int itemIdentifier) throws OperationFailedException{

        try {
            ItemDTO item = inventorySystem.getItemInformation(itemIdentifier);
            RegisteredItemDTO regItemDTO = sale.registerItemInSale(item);
            return regItemDTO;
        }
        catch (ItemIdentifierNotFoundException exception) {
            throw new OperationFailedException("Invalid item identifier entered, there is no item with " +
                    "item identifier: " + itemIdentifier, exception);
        }
        catch (DatabaseDownException exception){
            exceptionLogger.logError(exception);
            throw new OperationFailedException("Item cannot be scanned, try again later", exception);
        }

    }

    /**
     * Ends the sale process.
     * 
     * @return Total price as a string.
     */
    public double endSale(){
        return sale.endSale();
    }

    /**
     * Finalizes the sale.
     *
     * @param paidAmount The amount paid by the customer.
     * @return  Change as a string.
     */
    public double pay (double paidAmount){
        saleRegistry.logCompletedSale(sale);
        inventorySystem.updateInventory(sale);
        CashPayment payment = new CashPayment(paidAmount);
        cashRegister.registerPayment(payment);
        Receipt receipt = sale.payment (payment, accountingSystem);
        printer.printReceipt(receipt);
        return receipt.getChange();

    }

    /**
     * The specified observer will be notified when the sale is completed.
     *
     * @param revObs    The observer that will be notified.
     */
    public void addRevenueObserver (RevenueObserver revObs){
        revenueObservers.add(revObs);
    }

    /**
     * Check the membership status of the customer and makes a discount request.
     *
     * @param customerID    The customers ID.
     * @return              The discounted total price.
     */
    public double discountRequest (int customerID){
        String membershipStatus = discountRegister.getMembershipStatus(customerID);
        double discountedTotalPrice = sale.discountRequest (membershipStatus);

        return discountedTotalPrice;
    }
}

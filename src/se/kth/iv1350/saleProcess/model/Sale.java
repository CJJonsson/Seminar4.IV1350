package se.kth.iv1350.saleProcess.model;

import se.kth.iv1350.saleProcess.integration.AccountingSystem;
import se.kth.iv1350.saleProcess.integration.ItemDTO;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Represents one sale process, where one or more items are purchased
 * by a single customer. The table saleItems is used to store item information.
 */
public class Sale {
    private Store store;
    private TotalPrice totalPrice;
    private String saleTime;
    private String saleDate;
    private Hashtable<ItemDTO, SaleItem> saleItems = new Hashtable<>();
    private List <RevenueObserver> revenueObservers = new ArrayList<>();

    /**
     * Creates a new Sale instance.
     */
    public Sale(){
        this.totalPrice = new TotalPrice();
        this.store = new Store();
        setTimeOfSale();
        setDateOfSale();
    }

    /**
     * Registers the item entered by the Cashier in the current Sale.
     * If the item already exists in the current sale, the quantity is incremented.
     *
     * @param item Contains information about the item to register.
     * @return saleInformation that is to be displayed.
     */
    public RegisteredItemDTO registerItemInSale(ItemDTO item){
        if (itemAlreadyInSale (item)){
            updateItemQuantity (item);
        }
        else addItemToSale (item);

        totalPrice.increasePriceOfSale(item);
        RegisteredItemDTO regItemDTO = new RegisteredItemDTO(item.getItemDescription(), item.getPrice(), totalPrice.getRunningTotal());

        return regItemDTO;
    }

    private Boolean itemAlreadyInSale (ItemDTO item){
        return saleItems.containsKey(item);
    }

    private void updateItemQuantity (ItemDTO item){
        saleItems.get(item).increaseItemQuantityInSale();
    }

    private void addItemToSale (ItemDTO item){
        SaleItem currentItem = new SaleItem(item);
        saleItems.put(item, currentItem);
    }

    /**
     * Ends the sale.
     *
     * @return Total price as a String.
     */
    public double endSale(){
        return totalPrice.getRunningTotal();
    }

    /**
     * Creates a change instance and a receipt instance.
     * Send information about this sale to external systems and notifies observers.
     *
     * @param payment The payment made by the customer.
     * @return The created receipt.
     */
    public Receipt payment (CashPayment payment, AccountingSystem accountingSystem){
        accountingSystem.updateAccountingInformation(this, totalPrice);
        Change change = new Change(payment, totalPrice);
        Receipt receipt = new Receipt(this, store, totalPrice, payment, change);
        notifyObservers();
        return receipt;
    }

    public Hashtable<ItemDTO, SaleItem> getSaleItems(){
        return saleItems;
    }
    private void setTimeOfSale(){
        LocalTime time = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
        this.saleTime = time.toString();
    }

    private void setDateOfSale(){
        LocalDate date = LocalDate.now();
        this.saleDate = date.toString();
    }

    public String getSaleTime(){
        return this.saleTime;
    }
    public String getSaleDate(){
        return this.saleDate;
    }

    private void notifyObservers() {
        for (RevenueObserver revObs : revenueObservers) {
            revObs.updateRevenue(totalPrice.getRunningTotal());
        }
    }

    /**
     * All specified observers will be notified when the sale is completed.
     *
     * @param revObs    The observers that will be notified.
     */
    public void addRevenueObservers (List<RevenueObserver> revObs){
        revenueObservers.addAll(revObs);
    }

    /**
     * Calculates discount rate and the total price after discount.
     *
     * @param membershipStatus  The membership status of the customer.
     * @return                  The discounted total price.
     */
    public double discountRequest (String membershipStatus){
        Discount discount = new Discount();
        discount.checkDiscountRate(membershipStatus, this, totalPrice.getRunningTotal());
        totalPrice.addDiscount(discount.getDiscountRate());
        return totalPrice.getRunningTotal();
    }
}



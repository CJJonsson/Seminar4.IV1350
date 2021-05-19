package se.kth.iv1350.saleProcess.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.saleProcess.integration.*;
import se.kth.iv1350.saleProcess.model.CashPayment;
import se.kth.iv1350.saleProcess.model.Sale;
import se.kth.iv1350.saleProcess.model.SaleItem;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @BeforeEach
    public void setUp() {
        Controller instance = new Controller(new SaleRegistry(), SystemCreator.getSystemCreator(), new DiscountRegister());
    }

    @AfterEach
    public void tearDown() {
        Controller instance = null;
    }
    @Test
    public void correctItemInformationIsRetrieved() {
        try {
            ItemDTO itemDTO = new ItemDTO(20, 0.12, "Pickles (360g)");
            int itemIdentifier = 111;
            InventorySystem inventorySystem = new InventorySystem();
            String expResult = itemDTO.getItemDescription();
            String result = inventorySystem.getItemInformation(itemIdentifier).getItemDescription();
            assertEquals(expResult, result, "Wrong itemDTO itemIformation is retrieved from inventory system");
        }
        catch (Exception exception){
            fail("Exception thrown: " + exception.getMessage());
        }
    }

    @Test
    public void correctItemPriceIsRetrieved() {
        try {
            ItemDTO itemDTO = new ItemDTO(20, 0.12, "Pickles (360g)");
            int itemIdentifier = 111;
            InventorySystem inventorySystem = new InventorySystem();
            double expResult = itemDTO.getPrice();
            double result = inventorySystem.getItemInformation(itemIdentifier).getPrice();
            assertEquals(expResult, result, "Wrong itemDTO itemPrice is retrieved from inventory system");
        }
        catch (Exception exception){
            fail("Exception thrown: " + exception.getMessage());
        }
    }

    @Test
    public void correctItemVATrateIsRetrieved() {
        try {
            ItemDTO itemDTO = new ItemDTO(20, 0.12, "Pickles (360g)");
            int itemIdentifier = 111;
            InventorySystem inventorySystem = new InventorySystem();
            double expResult = itemDTO.getVATrate();
            double result = inventorySystem.getItemInformation(itemIdentifier).getVATrate();
            assertEquals(expResult, result, "Wrong itemDTO itemVATrate is retrieved from inventory system");
        }
        catch (Exception exception){
            fail("Exception thrown: " + exception.getMessage());
        }
    }

    @Test
    public void correctItemIsRegisteredInSale(){
        ItemDTO itemDTO = new ItemDTO(20, 0.12, "Pickles (360g)");
        Sale sale = new Sale();
        sale.registerItemInSale(itemDTO);
        String expResult = sale.getSaleItems().get(itemDTO).getItemDescription();
        String result = new SaleItem(itemDTO).getItemDescription();
        assertEquals(expResult, result, "The correct item is not registered in the sale");
    }

    @Test
    public void testEndSaleShowsCorrectTotalPrice() {
        try {
            Controller instance = new Controller(new SaleRegistry(), SystemCreator.getSystemCreator(), new DiscountRegister());
            instance.startNewSale();
            int itemIdentifier = 111;
            double price = 20;
            double VATrate = 0.12;
            instance.scanCurrentItem(itemIdentifier);
            double expResult = price * (1 + VATrate);
            double result = instance.endSale();
            assertEquals(expResult, result, "The correct totalPrice is not showed when the sale is ended");
        }
        catch (Exception exception){
            fail("Exception thrown: " + exception.getMessage());
        }
    }
    @Test
    public void testPay() {
        Controller instance = new Controller(new SaleRegistry(), SystemCreator.getSystemCreator(), new DiscountRegister());
        Sale sale = new Sale();
        double price = 20;
        double VATrate = 0.12;
        ItemDTO itemDTO = new ItemDTO(price, VATrate, "Pickles (360g)");
        sale.registerItemInSale(itemDTO);
        double totalPrice = price + (price * VATrate);
        double paidAmount = 100;
        String expResult = String.format("%.02f", paidAmount - totalPrice);
        String result = String.format("%.02f", sale.payment(new CashPayment(100), new AccountingSystem()).getChange());
        assertEquals(expResult, result, "Change is not equal to change with the same paidAmount and totalPrice");
    }
}
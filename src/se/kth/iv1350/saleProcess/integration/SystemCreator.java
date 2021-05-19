package se.kth.iv1350.saleProcess.integration;

/**
 * An singleton class that instantiates the external systems used when running the application,
 * accounting system, inventory system, and printer.
 */
public class SystemCreator {
    private static final SystemCreator SYSTEM_CREATOR = new SystemCreator();
    private AccountingSystem accountingSystem = new AccountingSystem();
    private InventorySystem inventorySystem = new InventorySystem();
    private Printer printer = new Printer();

    private SystemCreator(){
    }

    /**
     * Get the only SystemCreator instance.
     *
     * @return  the instance of this singleton.
     */
    public static SystemCreator getSystemCreator(){
        return SYSTEM_CREATOR;
    }

    /**
     * Get the AccountingSystem instance.
     *
     * @return the accountingSystem.
     */
    public AccountingSystem getAccountingSystem() {
        return accountingSystem;
    }

    /**
     * Get the InventorySystem instance.
     *
     * @return the inventorySystem.
     */
    public InventorySystem getInventorySystem() {
        return inventorySystem;
    }

    /**
     * Get the Printer instance.
     *
     * @return the printer.
     */
    public Printer getPrinter() {
        return printer;
    }
}

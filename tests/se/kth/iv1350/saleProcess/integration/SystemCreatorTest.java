package se.kth.iv1350.saleProcess.integration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SystemCreatorTest {

    @Test
    public void testCreateAccountingSystem() {
        SystemCreator instance = SystemCreator.getSystemCreator();
        AccountingSystem result = instance.getAccountingSystem();
        assertTrue(result instanceof AccountingSystem, "SystemCreator did not create AccountingSystem");
    }

    @Test
    public void testCreateInventorySystem() {
        SystemCreator instance = SystemCreator.getSystemCreator();
        InventorySystem result = instance.getInventorySystem();
        assertTrue(result instanceof InventorySystem, "SystemCreator did not create InventorySystem");
    }

    @Test
    public void testCreatePrinter() {
        SystemCreator instance = SystemCreator.getSystemCreator();
        Printer result = instance.getPrinter();
        assertTrue(result instanceof Printer, "SystemCreator did not create Printer");
    }
}
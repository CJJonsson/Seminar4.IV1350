package se.kth.iv1350.saleProcess.integration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventorySystemTest {
    @Test
    public void testDifferentItemIdentifiersNotEqual() {
        try {
            int oneItemIdentifier = 111;
            int otherItemIdentifier = 112;
            InventorySystem instance = new InventorySystem();
            boolean expResult = false;
            boolean result = instance.getItemInformation(oneItemIdentifier)
                    .equals(instance.getItemInformation(otherItemIdentifier));
            assertEquals(expResult, result, "Different itemIdentifers return the same ItemDTO");
        } catch (Exception exception) {
            fail("Exception thrown: " + exception.getMessage());
        }
    }

    @Test
    public void testValidItemIdentifier() {
        try {
            int itemIdentifier = 111;
            InventorySystem instance = new InventorySystem();
            ItemDTO expResult = instance.getItemInformation(111);
            ItemDTO result = instance.getItemInformation(itemIdentifier);
            assertEquals(expResult, result, "Valid identifier returns wrong ItemDTO");
        } catch (Exception exception) {
            fail("Exception thrown: " + exception.getMessage());
        }
    }

    @Test
    public void testNotExistingItemIdentifierThrowsItemIdentifierNotFoundException() {
        try {
            int itemIdentifier = 114;
            InventorySystem instance = new InventorySystem();
            ItemDTO result = instance.getItemInformation(itemIdentifier);
        } catch (Exception exception) {
            assertTrue(exception.getMessage().contains("Item identifier: 114 is invalid"),
                    "Wrong exception message was thrown");
        }
    }

    @Test
    public void testExceptionsNotThrownWhenExecutionIsSuccessful() {

        int itemIdentifier = 111;
        InventorySystem instance = new InventorySystem();
        assertDoesNotThrow(() -> instance.getItemInformation(itemIdentifier),
                "An exception was thrown when execution was successful");
    }

    @Test
    public void testDatabaseDownThrowsDatabaseDownException() {
        try {
            int itemIdentifier = 115;
            InventorySystem instance = new InventorySystem();
            ItemDTO result = instance.getItemInformation(itemIdentifier);
        } catch (Exception exception) {
            assertTrue(exception.getMessage().contains("Cannot access item database, database server is down."),
                    "Wrong exception message was thrown");
        }
    }

    @Test
    public void testItemIdentifierNotFoundExceptionThrown() {
        int itemIdentifier = 114;
        InventorySystem instance = new InventorySystem();
        assertThrows(ItemIdentifierNotFoundException.class,() -> instance.getItemInformation(itemIdentifier),
                "An exception was not thrown when execution was unsuccessful");
    }

    @Test
    public void testDatabaseDownExceptionThrown() {
        int itemIdentifier = 115;
        InventorySystem instance = new InventorySystem();
        assertThrows(DatabaseDownException.class,() -> instance.getItemInformation(itemIdentifier),
                "An exception was not thrown when execution was unsuccessful");
    }
}

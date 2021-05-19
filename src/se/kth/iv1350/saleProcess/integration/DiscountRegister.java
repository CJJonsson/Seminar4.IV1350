package se.kth.iv1350.saleProcess.integration;

import java.util.Hashtable;

/**
 *  Represents a simple discount register where the membership status of customers are stored.
 */
public class DiscountRegister {
    private Hashtable<Integer, String> membershipList = new Hashtable<>();

    /**
     * Creates a new DiscountRegister instance.
     */
    public DiscountRegister() {
        createMembershipList();
    }

    private void createMembershipList() {
        membershipList.put(19990909, "high");
        membershipList.put(19980808, "medium");
        membershipList.put(19970707, "low");
    }

    /**
     * Checks if the customer is a member at the store.
     *
     * @param customerID    The ID of the customer.
     * @return              The membership status of the customer.
     */
    public String getMembershipStatus (int customerID){
        if (membershipList.containsKey(customerID))
            return membershipList.get(customerID);

        else
            return "Not eligible for discount";

    }
}
package com.techelevator.VendingMachine;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class VendingFunctions {

    private Map<String, List<Items>> itemsInTheMachine;
    private BigDecimal availableFunds = new BigDecimal(0);

    private static Inventory myInventory = new Inventory();
    private SalesReport mySalesReports = new SalesReport();
    public Inventory getMyInventory() {
        return myInventory;
    }

    public boolean isInStock(String key) {
        if (itemsInTheMachine.get(key).size() == 0) {
            return false;
        } else {
            return true;
        }
    }
    //constructor
    public VendingFunctions() {
        myInventory.loadInventory();
    }

    //getters, setters, and helper methods


    public boolean canPurchase(String key) {
        if (itemsInTheMachine.get(key).get(0).getPrice().doubleValue() > availableFunds.doubleValue()) {
            return false;
        }
        return true;
    }

    public String vend(String key) {
        SalesReport logger = new SalesReport();
        logger.logPurchase(key, itemsInTheMachine.get(key).get(0), availableFunds);
        availableFunds = availableFunds.subtract(itemsInTheMachine.get(key).get(0).getPrice());
        String sound = itemsInTheMachine.get(key).get(0).makeSound();
        //if(canPurchase(key) && isInStock(key)) {
        itemsInTheMachine.get(key).remove(0);
        //}
        return sound;
    }

    public void feedMoney(BigDecimal amountInserted) {
        SalesReport logger = new SalesReport();
        logger.logFeed(amountInserted, availableFunds);
        availableFunds = availableFunds.add(amountInserted);
        System.out.println("Sucessfully inserted: $" + amountInserted.toString());

    }


    //     * When the customer selects "(2) Purchase",
//     * they are guided through the purchasing process menu
//     * */
//
//    //Feed money
//    /**
//     * "(1) Feed Money" allows the customer to repeatedly feed money into the machine in valid,
//     * whole dollar amounts—for
//     * example, $1, $2, $5, or $10.
//     * */
    public BigDecimal feedMoney(String selection, BigDecimal currentAccumulatedBalance) {
        BigDecimal currentMoneyProvided = new BigDecimal(0);
        String transaction = "Feed Money Now: ";
        if (selection.equals("1 dollar")) {
            currentMoneyProvided = new BigDecimal(1.00);
        } else if (selection.equals("2 dollar")) {
            currentMoneyProvided = new BigDecimal(2.00);
        } else if (selection.equals("5 dollar")) {
            currentMoneyProvided = new BigDecimal(5.00);
        } else if (selection.equals("10 dollar")) {
            currentMoneyProvided = new BigDecimal(10.00);
        }
        return currentMoneyProvided;
    }

    public void refill() {

        Inventory im = new Inventory();
        itemsInTheMachine = im.stockMachine();

    }
    public String displayItems() {

        // Make sure the vending machine has been successfully loaded at least
        // once.

        if (itemsInTheMachine != null && itemsInTheMachine.size() != 0) {

            StringBuilder printedListOfItems = new StringBuilder();

            for (String key : itemsInTheMachine.keySet()) {

                List<Items> slotItems = itemsInTheMachine.get(key);

                // print out only available items
                if (slotItems.size() > 0) {

                    String name = slotItems.get(0).getItemName();
                    String price = "$" + slotItems.get(0).getPrice().toString();
                    printedListOfItems.append(key + "  ");
                    printedListOfItems.append(String.format("%-20s", name));
                    printedListOfItems.append(String.format("%1$6s", price));
                    printedListOfItems.append("  (" + slotItems.size() + ")");
                    printedListOfItems.append("\n");

                }

            }

            if (!printedListOfItems.toString().equals("")) {
                return printedListOfItems.toString();
            }
        }

        return "The vending machine is empty.";
    }

    public BigDecimal getAvailableFunds() {
        return availableFunds;
    }

    public Map<String, List<Items>> getItemsInTheMachine() {
        return itemsInTheMachine;
    }

    public void resetAvailableFunds(){
        BigDecimal reset = new BigDecimal("0.00");
        this.availableFunds = reset;

    }

}

//balancesubtraction
        //finish transaction
        //make sure to handle if someone tries to deposit a neg amount
//    //purchase method used by purchase menu
//    public String purchase(String itemCode) {
//        BigDecimal another0 = new BigDecimal("0.00");
//        if (myCash.getCustomerBalance().compareTo(another0) == 0) {
//            return "Error: Zero balance.";
//        } else if (!myInventory.checkForItemCode(itemCode)) {
//            return "Error: please select an item from the available options.";
//        } else if (priceByCode(itemCode).compareTo(myCash.getCustomerBalance()) > 0) {
//            return "Error: Deposit more money to purchase this item";
//        } else if (myInventory.itemSoldOut(itemCode)) {
//            return "Item sold out, choose something else.";

package com.techelevator.VendingMachine;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class VendingFunctions {

    private Map<String, List<Items>> itemsInTheMachine;
    private BigDecimal availableFunds = new BigDecimal(0);

    private static Inventory myInventory = new Inventory();
    private LogFile mySalesReports = new LogFile();
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

    public VendingFunctions() {
        myInventory.loadInventory();
    }

    public boolean canPurchase(String key) {
        if (itemsInTheMachine.get(key).get(0).getPrice().doubleValue() > availableFunds.doubleValue()) {
            return false;
        }
        return true;
    }

    public String vend(String key) {
        LogFile logger = new LogFile();
        logger.logPurchase(key, itemsInTheMachine.get(key).get(0), availableFunds);
        availableFunds = availableFunds.subtract(itemsInTheMachine.get(key).get(0).getPrice());
        String sound = itemsInTheMachine.get(key).get(0).makeSound();
        //if(canPurchase(key) && isInStock(key)) {
        itemsInTheMachine.get(key).remove(0);
        //}
        return sound;
    }

    public void feedMoney (BigDecimal amountInserted) {
        LogFile logger = new LogFile();
        logger.logFeed(amountInserted, availableFunds);
        availableFunds = availableFunds.add(amountInserted);
        System.out.println("Successfully added: $" + amountInserted.toString());
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

    public static void main(String[] args) {

    }

}



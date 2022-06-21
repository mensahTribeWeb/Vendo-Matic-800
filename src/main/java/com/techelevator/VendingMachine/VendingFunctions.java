package com.techelevator.VendingMachine;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class VendingFunctions {

    private static final Inventory myInventory = new Inventory();
    private Map<String, List<Items>> itemsInTheMachine;
    private BigDecimal availableFunds = new BigDecimal(0);
    private final LogFile myLog = new LogFile();

    public VendingFunctions() {
        myInventory.loadInventory();
    }

    public static void main(String[] args) {

    }

    public Inventory getMyInventory() {
        return myInventory;
    }

    public boolean isInStock(String key) {
        return itemsInTheMachine.get(key).size() != 0;
    }

    public boolean canPurchase(String key) {
        return !(itemsInTheMachine.get(key).get(0).getPrice().doubleValue() > availableFunds.doubleValue());
    }

    public String vend(String key) {
        LogFile logger = new LogFile();
        logger.logPurchase(key, itemsInTheMachine.get(key).get(0), availableFunds);
        availableFunds = availableFunds.subtract(itemsInTheMachine.get(key).get(0).getPrice());
        String sound = itemsInTheMachine.get(key).get(0).makeSound();
        if(canPurchase(key) && isInStock(key)) {
        itemsInTheMachine.get(key).remove(0);
        }
        return sound;
    }

    public void feedMoney(BigDecimal amountInserted) {
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

        // Make sure the vending machine has been successfully loaded

        if (itemsInTheMachine != null && itemsInTheMachine.size() != 0) {

            StringBuilder printedListOfItems = new StringBuilder();

            for (String key : itemsInTheMachine.keySet()) {

                List<Items> slotItems = itemsInTheMachine.get(key);

                // print out only available items
                if (slotItems.size() > 0) {

                    String name = slotItems.get(0).getItemName();
                    String price = "$" + slotItems.get(0).getPrice().toString();
                    printedListOfItems.append(key + "  ");
                    printedListOfItems.append(String.format("%-20s", name)); //%20s means your String will be left-padded if its length is less than 20.
                    printedListOfItems.append(String.format("%1$6s", price)); //%1$6s = string fmt1
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

    public void resetAvailableFunds() {
        BigDecimal reset = new BigDecimal("0.00");
        this.availableFunds = reset;

    }

}



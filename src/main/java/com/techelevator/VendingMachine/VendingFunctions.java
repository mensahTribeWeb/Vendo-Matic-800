package com.techelevator.VendingMachine;

import com.techelevator.Reports.SalesReports;

import java.math.BigDecimal;

public class VendingFunctions {

    //vending machine has instances of CashHandle, SalesReport and inventory
    private CashHandle myCash = new CashHandle();
    private static Inventory myInventory = new Inventory();
    private SalesReports mySalesReports = new SalesReports();

    //constructor
    public VendingFunctions() {
        myInventory.loadInventory();
    }

    //getters, setters, and helper methods
    public CashHandle getMyCash() {
        return getMyCash();
    }

    public String infoForPurchases() {
        return myInventory.displayInventory();
    }

    public BigDecimal priceByCode(String itemCode) {
        return myInventory.getAPrice(itemCode);
    }

    public SalesReports getMySalesReports() {
        return mySalesReports;
    }

    public Inventory getMyInventory() {
        return myInventory;
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
    public BigDecimal feedMoney(String selection, BigDecimal currentAccumulatedBalance){
        BigDecimal currentMoneyProvided = new BigDecimal(0);
        String transaction = "Feed Money Now: ";
        if (selection.equals("1 dollar")){
            currentMoneyProvided = new BigDecimal(1.00);
        }
        else if (selection.equals("2 dollar")){
            currentMoneyProvided = new BigDecimal(2.00);
        }
        else if (selection.equals("5 dollar")){
            currentMoneyProvided = new BigDecimal(5.00);
        }
        else if (selection.equals("10 dollar")){
            currentMoneyProvided = new BigDecimal(10.00);
        }
        return currentMoneyProvided;
        //balancesubtraction
        //finish transaction

    }    //make sure to handle if someone tries to deposit a neg amount

    //purchase method used by purchase menu
    public String purchase(String itemCode) {
        BigDecimal another0 = new BigDecimal("0.00");
        if (myCash.getCustomerBalance().compareTo(another0) == 0) {
            return "Error: Zero balance.";
        } else if (!myInventory.checkForItemCode(itemCode)) {
            return "Error: please select an item from the available options.";
        } else if (priceByCode(itemCode).compareTo(myCash.getCustomerBalance()) > 0) {
            return "Error: Deposit more money to purchase this item";
        } else if (myInventory.itemSoldOut(itemCode)) {
            return "Item sold out, choose something else.";
        } else {
            myCash.setCustomerBalance(myCash.getCustomerBalance().subtract(priceByCode(itemCode)));
            mySalesReports.addToTransactionLog(" " + myInventory.getItem(itemCode).getName() + " " + itemCode + " $" + myInventory.getAPrice(itemCode) + " $" + myCash.getCustomerBalance());
            myInventory.setQuantity(itemCode);
            mySalesReports.setMachineTotalSales(myInventory.getAPrice(itemCode));
        }
        return myInventory.getItem(itemCode).makeSound() + "\n" + "You have " + myCash.getCustomerBalance() + " left to spend.";
    }

    //set customer balance to zero and say "returned ___ change"
    public String giveChange() {
        String change = "Your change is " + myCash.getCustomerBalance() + "\n" + "Which is " + myCash.makeChange(myCash.getCustomerBalance());
        mySalesReports.addToTransactionLog(" GIVE CHANGE: $" + myCash.getCustomerBalance() + " " + "$0.00" + "\n");
        myCash.setCustomerBalance(new BigDecimal("0.00"));
        return change;
    }
}

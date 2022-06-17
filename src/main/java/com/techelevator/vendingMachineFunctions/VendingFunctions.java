package com.techelevator.vendingMachineFunctions;

import com.techelevator.inventoryItems.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingFunctions {
    List<Items> vendingMachineArray = new ArrayList<>();

    //load items
    /**idea for loading **/
    public void loadInventory(){
        File file = new File("vendingmachine.csv");
        try (Scanner input = new Scanner(file.getAbsoluteFile())){
            while(input.hasNextLine()){
                String line = input.nextLine();
                String[] inventoryInfo = line.split("\\|");
                String slot = inventoryInfo[0];
                String name = inventoryInfo[1];
                BigDecimal price = new BigDecimal(inventoryInfo[2]);
                String type = inventoryInfo[3];
                if (type.equals("Beverage")){
                    vendingMachineArray.add(new Beverages(slot, name, price, type,5));
                }
                else if (type.equals("Candy")){
                    vendingMachineArray.add(new Candy(slot, name, price, type,5));
                }
                else if (type.equals("Chips")){
                    vendingMachineArray.add(new Chips(slot, name, price, type,5));
                }
                else if (type.equals("Gum")){
                    vendingMachineArray.add(new Gum(slot, name, price, type,5));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * /
     * displayItems
     * "(1) Display Vending Machine Items"
     *
     * example
     * A1|Potato Crisps|3.05|Chip
     * B1|Moonpie|1.80|Candy
     * B2|Cowtales|1.50|Candy
     * C1|Cola|1.25|Drink
     * */

    public void displayInventory(){
        for (Items item : vendingMachineArray){
            System.out.println(item.getSlot()+" "+item.getName()+" $"+item.getPrice()+" Quantity: "+item.getQuantity());

            if(item.getQuantity() == 0){
                System.out.println(item.getName() + " Out of stock");
            }
        }
    }

    /**
     * /
     * When the customer selects "(2) Purchase",
     * they are guided through the purchasing process menu
     * */

    //Feed money
    /**
     * "(1) Feed Money" allows the customer to repeatedly feed money into the machine in valid,
     * whole dollar amounts—for
     * example, $1, $2, $5, or $10.
     * */
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
}
}//E.O.C

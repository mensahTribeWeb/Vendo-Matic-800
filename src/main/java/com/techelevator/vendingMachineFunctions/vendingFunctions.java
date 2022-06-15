package com.techelevator.vendingMachineFunctions;

import com.techelevator.inventoryItems.Beverages;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class vendingFunctions {
    List vendingMachineArray = new ArrayList<>();

    //load items
    /**idea for loading **/
    public void loadInventory(){
        File file = new File("vendingmachine.csv");
        try (Scanner inputScanner = new Scanner(file.getAbsoluteFile())){
            while(inputScanner.hasNextLine()){
                String line = inputScanner.nextLine();
                String[] inventoryInfo = line.split("\\|");
                String slot = inventoryInfo[0];
                String name = inventoryInfo[1];
                BigDecimal price = new BigDecimal(inventoryInfo[2]);
                String type = inventoryInfo[3];
                if (type.equals("Beverage")){
                    vendingMachineArray.add(new Beverages(slot, name, price, type,5));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    //displayItems
    //Feed money
    //productSelection
    //balancesubtraction
    //finish transaction
}

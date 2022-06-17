package com.techelevator.VendingMachine;


import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Inventory {
    List<Items> vendingMachineArray = new ArrayList<>();

    //inventory map: key is the item code, value is the item object
    private Map<String, Items> inventory = new LinkedHashMap<>();

    //getters, setters, and helper methods
    public BigDecimal getAPrice(String itemCode) {
        Items itemToFind = inventory.get(itemCode);
        return itemToFind.getPriceAsDecimal();
    }

    public void setQuantity(String itemCode) {
        Items itemToUpdate = inventory.get(itemCode);
        itemToUpdate.setQuantity(itemToUpdate.getQuantity() - 1);
    }

    public boolean checkForItemCode(String itemCode) {
        return inventory.containsKey(itemCode);
    }

    public Items getItem(String itemCode) {
        return inventory.get(itemCode);
    }

    //create inventory map from a formatted txt file
    //load items
//    /**idea for loading **/
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
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    //displays inventory at purchase menu
    public String displayInventory() {

        String inventoryUpdate = "";
        for (Map.Entry<String, Items> map : inventory.entrySet()) {
            if (map.getValue().getQuantity() == 0) {
                inventoryUpdate += map.getKey() + " | " + "SOLD OUT" + "\n";
            } else {
                inventoryUpdate += map.getKey() + " | " + map.getValue().getName() + " | " + map.getValue().getPrice() + "\n";
            }
        }
        return inventoryUpdate;
    }

    //displays sales report at sales report main menu option
    public String displayInventoryForSalesReport() {
        String salesUpdates = "";
        for (Map.Entry<String, Items> map : inventory.entrySet()) {
            salesUpdates += map.getValue().getName() + " | " + (5 - (map.getValue().getQuantity())) + "\n";
        }
        return salesUpdates;
    }

    //item sold out - used in display inventory
    public boolean itemSoldOut(String itemCode) {
        Items itemToCheck = inventory.get(itemCode);
        return itemToCheck.getQuantity() == 0;
    }
}

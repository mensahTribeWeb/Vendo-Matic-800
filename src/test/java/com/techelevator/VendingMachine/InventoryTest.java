package com.techelevator.VendingMachine;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.Assert;
import org.junit.Test;

public class InventoryTest {


        @Test
        public void file_is_read_properly_when_given_to_vending_machine() {
            File inventoryFile = new File("vendingmachine.csv");
            Inventory vm = new Inventory();
            String line = " ";
            try(FileReader fr = new FileReader(inventoryFile);
                BufferedReader br = new BufferedReader(fr)){

                while((line = br.readLine()) != null){
                    break;
                }
            }
            catch(Exception e){

            }
            String result = "A1|Potato Crisps|3.05|Chip";
            Assert.assertEquals(result, line);
        }

    @Test
    public void display_items_is_properly_displayed(){
        File inventoryFile = new File("vendingmachine.csv");
        Inventory vm = new Inventory();
        vm.loadInventory();
    }


}
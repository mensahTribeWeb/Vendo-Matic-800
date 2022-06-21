package com.techelevator.VendingMachine;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class InventoryTest {

    Inventory sut;
    Map<String, List<Items>> testMap;

    //test base reading of data file
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
    //test data file inventory load
    @Test
    public void display_items_is_properly_displayed(){
        File inventoryFile = new File("vendingmachine.csv");
        Inventory vm = new Inventory();
        vm.loadInventory();

}        @Before
        public void setUp() throws Exception {
            sut = new Inventory();
            sut.loadInventory();
            sut.createMap();
        }
    //This test every component of a piece of the list in the map based on its key. Checking item name, price, and sound.
    //This also tests the getters within the Items class and its subclasses.
    @Test
    public void testMapCreation() throws FileNotFoundException {
        assertEquals("Potato Crisps", sut.stockMachine().get("A1").get(0).getItemName());
        assertEquals("Triplemint", sut.stockMachine().get("D4").get(0).getItemName());
        assertEquals(new BigDecimal("0.75"), sut.stockMachine().get("D4").get(0).getPrice());
        assertEquals(new BigDecimal("3.05"), sut.stockMachine().get("A1").get(0).getPrice());
        assertEquals("Crunch Crunch, Yum!", sut.stockMachine().get("A1").get(0).makeSound());
    }

}

package com.techelevator.VendingMachine;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class InventoryTest {

    Inventory sut;
    Map<String, List<Items>> testMap;

    @Before
    public void setUp() throws Exception {
        sut = new Inventory();
        sut.loadInventory();
        sut.createMap();
    }

    //This test gets at every component of a piece of the list in the map based on its key. Checking item name, price, and sound.
    //This test simultaneously tests the getters within the Items class and its subclasses.
    @Test
    public void testMapCreation() throws FileNotFoundException {
        assertEquals("Potato Crisps", sut.stockMachine().get("A1").get(0).getItemName());
        assertEquals("Triplemint", sut.stockMachine().get("D4").get(0).getItemName());
        assertEquals(new BigDecimal("0.75"), sut.stockMachine().get("D4").get(0).getPrice());
        assertEquals(new BigDecimal("3.05"), sut.stockMachine().get("A1").get(0).getPrice());
        assertEquals("Crunch Crunch, Yum!", sut.stockMachine().get("A1").get(0).makeSound());
    }

}
package com.techelevator.inventoryItems;

import java.math.BigDecimal;
//All drink items print "Glug Glug, Yum!"

public class Beverages extends Items {
    //Methods/Constructors
    public Beverages(String slot, String name, BigDecimal price, String type, int quantity) {
        super(slot, name, price, type, quantity, defaultQuantity);
    }

    @Override
    public String makeSound() {
        return "Glug Glug, Yum!";
    }
}



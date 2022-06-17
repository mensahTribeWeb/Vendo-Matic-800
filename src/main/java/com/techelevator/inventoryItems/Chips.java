package com.techelevator.inventoryItems;
//All chip items print "Crunch Crunch, Yum!"
import java.math.BigDecimal;

public class Chips extends Items {
    //Methods/Constructors
    public Chips(String slot, String name, BigDecimal price, String type, int quantity) {
        super(slot, name, price, type, quantity);
    }

    @Override
    public String makeSound() {
        return "Crunch Crunch, Yum!";
    }
}



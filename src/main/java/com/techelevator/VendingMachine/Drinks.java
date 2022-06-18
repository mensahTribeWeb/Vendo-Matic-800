package com.techelevator.VendingMachine;

import java.math.BigDecimal;
//All drink items print "Glug Glug, Yum!"

public class Drinks extends Items {
    //Methods/Constructors
    public Drinks(String slot, BigDecimal price) {
        super(slot, price);
    }

    @Override
    public String makeSound() {
        return "Glug Glug, Yum!";
    }
}



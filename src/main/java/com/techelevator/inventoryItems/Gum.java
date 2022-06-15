package com.techelevator.inventoryItems;
//All gum items print "Chew Chew, Yum!"
import java.math.BigDecimal;

public class Gum extends Items {
    //Methods/Constructors
    public Gum(String slot, String name, BigDecimal price, String type, int quantity, int defaultQuantity) {
        super(slot, name, price, type, quantity, defaultQuantity);
    }

    @Override
    public String makeSound() {
        return "Chew Chew, Yum!";
    }
}



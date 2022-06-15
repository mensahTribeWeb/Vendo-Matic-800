package com.techelevator.inventoryItems;
//All candy items print "Munch Munch, Yum!"
import java.math.BigDecimal;

public class Candy extends Items {
    //Methods/Constructors
    public Candy(String slot, String name, BigDecimal price, String type, int quantity, int defaultQuantity) {
        super(slot, name, price, type, quantity, defaultQuantity);
    }

    @Override
    public String makeSound() {
        return "Munch Munch, Yum!";
    }
}



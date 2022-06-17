package com.techelevator.VendingMachine;
//All gum items print "Chew Chew, Yum!"
import java.math.BigDecimal;

public class Gum extends Items {
    //Methods/Constructors
    public Gum(String slot, String name, BigDecimal price, String type, int quantity) {
        super(slot, name, price, type, quantity);
    }

    @Override
    public String makeSound() {
        return "Chew Chew, Yum!";
    }
}



package com.techelevator.VendingMachine;
//All gum items print "Chew Chew, Yum!"
import java.math.BigDecimal;

public class Gum extends Items {
    //Methods/Constructors
    public Gum(String slot, BigDecimal price) {
        super(slot, price);
    }

    @Override
    public String makeSound() {
        return "Chew Chew, Yum!";
    }
}



package com.techelevator.VendingMachine;
//All candy items print "Munch Munch, Yum!"
import java.math.BigDecimal;

public class Candy extends Items {
    //Methods/Constructors
    public Candy(String slot, BigDecimal price) {
        super(slot, price);
    }

    @Override
    public String makeSound() {
        return "Munch Munch, Yum!";
    }
}



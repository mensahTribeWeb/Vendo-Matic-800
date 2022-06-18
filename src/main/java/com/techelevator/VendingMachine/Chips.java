package com.techelevator.VendingMachine;
//All chip items print "Crunch Crunch, Yum!"
import java.math.BigDecimal;

public class Chips extends Items {
    //Methods/Constructors
    public Chips(String name, BigDecimal price) {
        super(name, price);
    }

    @Override
    public String makeSound() {
        return "Crunch Crunch, Yum!";
    }
}



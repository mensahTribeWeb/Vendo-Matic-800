package com.techelevator.VendingMachine;

import java.math.BigDecimal;

abstract class Items {

    BigDecimal price;
    String itemName;


    public Items(String name, BigDecimal price) {
        itemName = name;
        this.price = price;

    }

    public BigDecimal getPrice() {
        return price;
    }
    public String getItemName() {
        return itemName;
    }
    abstract public String makeSound();
}



package com.techelevator.inventoryItems;

import java.math.BigDecimal;

public class Items {
    //Properties/ Instances
    private static final int defaultQuantity = 5;
    private String slot;
    private String name;
    private BigDecimal price;
    private String type;
    private  int quantity;

    public Items(String slot, String name, BigDecimal price, String type, int quantity) {
        this.slot = slot;
        this.name = name;
        this.price = price;
        this.type = type;
        this.quantity = defaultQuantity;
    }
    public String makeSound() {
        return "sound";
    }

    public String getSlot() {
        return slot;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getType() {
        return null;
    }

    public int getQuantity() {
        return quantity;
    }



}

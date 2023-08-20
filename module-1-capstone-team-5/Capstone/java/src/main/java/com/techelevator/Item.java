package com.techelevator;

// Parent Class: Item
public class Item {

    // Class-Object Relationships
    //      "has-a": Item "has-a" Slot

    Slot itemSlot = new Slot();
    private String itemName;
    private double itemPrice;

    // Constructor
    public Item(String itemName) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    // Getters and Setters: itemName, itemPrice
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
}

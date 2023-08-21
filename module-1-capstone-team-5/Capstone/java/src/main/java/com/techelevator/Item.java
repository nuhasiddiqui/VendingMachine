package com.techelevator;

// Parent Class: Item
public class Item {

    // Class-Object Relationships
    //      "has-a": Item "has-a" Slot

    private String slotNumber;
    private int slotQuantity;
    private Slot itemSlot = new Slot(slotNumber, slotQuantity);
    private String itemName;
    private double itemPrice;

    // Constructor
    public Item(Slot itemSlot, String itemName, double itemPrice) {
        this.itemSlot = itemSlot;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    // Getters and Setters: itemSlot, itemName, itemPrice
    public Slot getItemSlot() {
        return itemSlot;
    }

    public void setItemSlot(Slot itemSlot) {
        this.itemSlot = itemSlot;
    }

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
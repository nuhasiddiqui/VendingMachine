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
    private int itemSaleCount; // New property to track itemSaleCount count


    // Constructor
    public Item(Slot itemSlot, String itemName, double itemPrice) {
        this.itemSlot = itemSlot;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemSaleCount = 0; // Initialize itemSaleCount count to 0

    }

    // Method to update itemSaleCount count when a snack is purchased
    public void updateSales(int quantitySold) {
        itemSaleCount += quantitySold;
    }

    // Method to get the itemSaleCount count
    public int getItemSaleCount() {
        return itemSaleCount;
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
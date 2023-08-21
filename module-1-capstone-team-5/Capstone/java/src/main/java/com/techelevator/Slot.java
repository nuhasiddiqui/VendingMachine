package com.techelevator;

public class Slot {
    // Class-Object Relationships
    //      "is-a": Snack "is-a" Item
    //      "has-a": Item "has-a" Slot

    private String slotNumber;
    private int slotQuantity;


    // Constructor
    public Slot(String slotNumber, int slotQuantity) {
        this.slotNumber = slotNumber;
        this.slotQuantity = slotQuantity;
    }

    public void reduceSlotQuantity (int numOfItemBought){
        int slotQuantityMax = 5;
        this.slotQuantity = slotQuantityMax - numOfItemBought;
    }

    // Getters and Setters: slotQuantity, slotNumber
    public int getSlotQuantity() {
        return slotQuantity;
    }

    public void setSlotQuantity(int slotQuantity) {
        this.slotQuantity = slotQuantity;
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }
}

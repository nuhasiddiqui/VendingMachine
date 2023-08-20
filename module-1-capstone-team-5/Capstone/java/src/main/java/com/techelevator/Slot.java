package com.techelevator;

public class Slot {
    // Class-Object Relationships
    //      "is-a": Snack "is-a" Item
    //      "has-a": Item "has-a" Slot

    private int slotQuantity;
    private String slotNumber;


    // Constructor
    public Slot (){
    }

    public void reduceSlotQuantity (int numofItemBought){
        int slotQuantityMax = 5;
        this.slotQuantity = slotQuantityMax - numofItemBought;
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

package com.techelevator;

public class Snack extends Item {

    // Class-Object Relationships
    //      "is-a": Snack "is-a" Item

    private String snackType;

    // Constructor
    public Snack(Slot itemSlot, String itemName, double itemPrice, String snackType) {
        // Instantiate the parent class object (Item)
        super(itemSlot, itemName, itemPrice);
        this.snackType = snackType;
    }

    // Getters and Setter: snackType
    public String getSnackType() {
        return snackType;
    }

    public void setSnackType(String snackType) {
        this.snackType = snackType;
    }

    // Override the toString() method in your Snack class to provide a meaningful string representation of the Snack object.
    @Override
    public String toString() {
        return "Snack: " + getItemName() + ", Price: $ " + getItemPrice() + ", Type: " + getSnackType();
    }

}
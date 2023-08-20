package com.techelevator;

public class Snack extends Item {

    // Class-Object Relationships
    //      "is-a": Snack "is-a" Item

    private String snackType;

    // Constructor
    public Snack(String itemName, String snackType) {
        // Instantiate the parent class object (Item)
        super(itemName);
        this.snackType = snackType;
    }

    // Getters and Setter: snackType
    public String getSnackType() {
        return snackType;
    }

    public void setSnackType(String snackType) {
        this.snackType = snackType;
    }
}
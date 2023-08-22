package com.techelevator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VendingMachine {
    // Class-Object Relationships
    //      "has-a": VendingMachine "has-a" Snack(s)
    //               Snack: extension of Item "has-a" Slot(s)

    public static List<Snack> loadSnacks() {
        List<Snack> snacks = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("vendingmachine.csv"));
            String line;
            // Parsing the data from the vendingmachine.csv file
            //      Read each line once, stores it in a variable, and then split and process the values from that line
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split("\\|");
                String slotNumber = (values[0]);
                String itemName = values[1];
                double itemPrice = Double.parseDouble(values[2]);
                String snackType = values[3];

                Slot itemSlot = new Slot(slotNumber, 5);
                snacks.add(new Snack(itemSlot, itemName, itemPrice, snackType));
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
        return snacks;
    }
}
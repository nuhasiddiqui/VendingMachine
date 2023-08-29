package com.techelevator;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VendingMachine {
    // Class-Object Relationships
    //      "has-a": VendingMachine "has-a" Snack(s)
    //               Snack: extension of Item "has-a" Slot(s)
    List<Snack> snacks = new ArrayList<>();

    public List<Snack> loadSnacks() {
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

    public Snack getSnackBySlotNumber(String targetSlotNumber) {
        for (Snack snack : snacks) {
            Slot snackSlot = snack.getItemSlot();
            if (snackSlot != null && snackSlot.getSlotNumber().equals(targetSlotNumber)) {
                return snack; // Found the snack with the target slot number
            }
        }
        return null; // No snack found with the target slot number
    }

    public void dispenseSnack (Snack targetSnack) {
        if (targetSnack.getItemSlot().getSlotQuantity() > 0) {
            targetSnack.getItemSlot().setSlotQuantity((targetSnack.getItemSlot().getSlotQuantity()) - 1);
            // Display message depending on targetSnack snackType
            //      Item name, cost, and the money remaining.
            System.out.println("Item Selected | " + targetSnack);
            //      Message depending on the snackType
            switch (targetSnack.getSnackType()) {
                case "Chip":
                    System.out.println("Crunch Crunch, Yum!");
                    break;
                case "Candy":
                    System.out.println("Munch Munch, Yum !");
                    break;
                case "Drink":
                    System.out.println("Glug Glug, Yum!");
                    break;
                case "Gum":
                    System.out.println("Chew Chew, Yum!");
                    break;
            }
        }
    }


    public void logTransaction(String action, double amount, double balance) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Log.txt", true))) {
            LocalDateTime timestamp = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
            String formattedTimestamp = timestamp.format(formatter);

            String formattedAmount = String.format("$%.2f", amount);
            String formattedBalance = String.format("$%.2f", balance);

            writer.println(String.format("%-25s %-25s %-15s %s", formattedTimestamp, action, formattedAmount, formattedBalance));
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the log file.");
            e.printStackTrace();
        }
    }
}
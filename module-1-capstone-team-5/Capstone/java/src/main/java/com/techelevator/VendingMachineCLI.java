package com.techelevator;
/**************************************************************************************************************************
 *  This is your Vending Machine Command Line Interface (CLI) class
 *
 *  THIS IS NOT THE VENDING MACHINE!
 *
 *  It is the main process for the Vending Machine
 *
 *  THIS is where most, if not all, of your Vending Machine interactions should be coded
 *
 *  It is instantiated and invoked from the VendingMachineApp (main() application)
 *
 *  Your code vending machine related code should be placed in here
 ***************************************************************************************************************************/
import com.techelevator.view.Menu;         // Gain access to Menu class provided for the Capstone

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class VendingMachineCLI {

    // Main menu options defined as constants
	// Code to load (queue) snacks from Vending Machine
	//		.loadSnacks() method: returns List<Snack>
	VendingMachine vendingMachine = new VendingMachine();
	List<Snack> snacks = vendingMachine.loadSnacks();

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE      = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT          = "Exit";

	private static final String MAIN_MENU_OPTION_SALESREPORT   = "Sales Report";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
													    MAIN_MENU_OPTION_PURCHASE,
													    MAIN_MENU_OPTION_EXIT,
														MAIN_MENU_OPTION_SALESREPORT};
	
	private Menu vendingMenu;              // Menu object to be used by an instance of this class
	
	public VendingMachineCLI(Menu menu) {  // Constructor - user will pass a menu for this class to use
		this.vendingMenu = menu;           // Make the Menu the user object passed, our Menu
	}
	/**************************************************************************************************************************
	*  VendingMachineCLI main processing loop
	*  
	*  Display the main menu and process option chosen
	*
	*  It is invoked from the VendingMachineApp program
	*
	*  THIS is where most, if not all, of your Vending Machine objects and interactions 
	*  should be coded
	*
	*  Methods should be defined following run() method and invoked from it
	*
	***************************************************************************************************************************/

	public void run() {

		boolean shouldProcess = true;         // Loop control variable
		
		while(shouldProcess) {                // Loop until user indicates they want to exit
			
			String choice = (String)vendingMenu.getChoiceFromOptions(MAIN_MENU_OPTIONS);  // Display menu and get choice
			
			switch(choice) {                  // Process based on user menu choice
			
				case MAIN_MENU_OPTION_DISPLAY_ITEMS:
					displayItems();           // invoke method to display items in Vending Machine
					break;                    // Exit switch statement
			
				case MAIN_MENU_OPTION_PURCHASE:
					purchaseItems();          // invoke method to purchase items from Vending Machine
					break;                    // Exit switch statement
			
				case MAIN_MENU_OPTION_EXIT:
					endMethodProcessing();    // Invoke method to perform end of method processing
					shouldProcess = false;    // Set variable to end loop
					break;                    // Exit switch statement

				case MAIN_MENU_OPTION_SALESREPORT:
					generateSalesReport();
					break;
			}	
		}
		return;                               // End method and return to caller
	}
/********************************************************************************************************
 * Methods used to perform processing
 ********************************************************************************************************/
	public void displayItems() {      // static attribute used as method is not associated with specific object instance

		// Code to display items from Vending Machine
		int snacksPerRow = 4;
		int columnWidth = 20;
		int bufferWidth = 10; // Adjust this value to set the desired buffer between columns outputted

		for (int i = 0; i < snacks.size(); i += snacksPerRow) {
			for (int j = 0; j < snacksPerRow && i + j < snacks.size(); j++) {
				Snack snack = snacks.get(i + j);
				System.out.printf("%-" + (columnWidth + bufferWidth) + "s", "Slot Number: " + snack.getItemSlot().getSlotNumber());
			}
			System.out.println();

			for (int j = 0; j < snacksPerRow && i + j < snacks.size(); j++) {
				Snack snack = snacks.get(i + j);
				System.out.printf("%-" + (columnWidth + bufferWidth) + "s", "Snack: " + snack.getItemName());
			}
			System.out.println();

			for (int j = 0; j < snacksPerRow && i + j < snacks.size(); j++) {
				Snack snack = snacks.get(i + j);
				System.out.printf("%-" + (columnWidth + bufferWidth) + "s", "Type: " + snack.getSnackType());
			}
			System.out.println();

			for (int j = 0; j < snacksPerRow && i + j < snacks.size(); j++) {
				Snack snack = snacks.get(i + j);
				System.out.printf("%-" + (columnWidth + bufferWidth) + "s", "Price: $"  + String.format("%.2f", snack.getItemPrice()));
			}
			System.out.println();

			for (int j = 0; j < snacksPerRow && i + j < snacks.size(); j++) {
				Snack snack = snacks.get(i + j);
				System.out.printf("%-" + (columnWidth + bufferWidth) + "s", "Quantity: "  + snack.getItemSlot().getSlotQuantity());
			}
			System.out.println();

			for (int j = 0; j < snacksPerRow && i + j < snacks.size(); j++) {
				System.out.print("-".repeat(columnWidth + bufferWidth));
			}
			System.out.println();
		}
	}
	
	public void purchaseItems() {     // static attribute used as method is not associated with specific object instance
		// Code to purchase items from Vending Machine
		Calculator calculator = new Calculator();
		Scanner scanner = new Scanner(System.in);

		// To handle the different prompts within this do while loop and reprompt the user when needed,
		// use separate loops for each prompt and include error handling.

		while (true) {
			double currentBalance = calculator.getBalance();
			System.out.printf("\nCurrent Balance: $%.2f\n", currentBalance);
			System.out.println("\n(1) Feed Money\n(2) Select Product\n(3) Finish Transaction\n");
			System.out.print("Please choose an option >>> ");
			String userInput = scanner.nextLine().trim(); // Read user input as a string and trim whitespace

			switch (userInput){
				case "1":
					double depositAmount;
					boolean depositValidInput = false;

					while (!depositValidInput) {
						System.out.print("Please enter the amount to deposit >>> ");
						if (scanner.hasNextDouble()) {
							depositAmount = scanner.nextDouble();
							if (depositAmount > 0) {
								double newBalance = calculator.getBalance() + depositAmount;
								vendingMachine.logTransaction("FEED MONEY", depositAmount, newBalance);
								calculator.moneyDeposited(depositAmount);
								depositValidInput = true;
							} else {
								System.out.println("Please enter a valid deposit amount (greater than 0).");
								scanner.nextLine(); // Clear the input buffer
							}
						} else {
							System.out.println("Please enter a valid numeric value.");
							scanner.nextLine(); // Clear the input buffer
						}
					}
					scanner.nextLine(); // Clear the input buffer
					break;
				case "2":
					boolean snackValidInput = false; // Reset for each iteration
					if (currentBalance <= 0) {
						System.out.println("Insufficient balance. Please deposit money before selecting a product.");
						break; // Skip to the next iteration of the loop
					}
					displayItems();
					while (!snackValidInput) {
						System.out.print("Please enter the slot number >>> ");
						String slotNumber = scanner.next().trim().toUpperCase(); // Convert to uppercase, ensures that both variations of userInput (ex: d4 and D4) are recognized
						// Check if the entered slotNumber exists in the snacks List
						Snack snackSelected = vendingMachine.getSnackBySlotNumber(slotNumber);
						if (snackSelected != null) {
							if (snackSelected.getItemSlot().getSlotQuantity() > 0) {
								boolean balanceGreaterThanZero = currentBalance > 0;
								boolean isEnoughMoney = currentBalance >= snackSelected.getItemPrice();
								if (balanceGreaterThanZero && isEnoughMoney) {
									try {
										vendingMachine.dispenseSnack(snackSelected);
										double newBalance = calculator.getBalance() - snackSelected.getItemPrice();
										vendingMachine.logTransaction(snackSelected.getItemName() + " " + slotNumber, snackSelected.getItemPrice(), newBalance);
										calculator.updateBalanceAfterPurchase(snackSelected.getItemPrice());
										snackValidInput = true;

										// Update the sales for the purchased item
										snackSelected.updateSales(1);
									} catch (IllegalStateException e) {
										System.out.println(e.getMessage());
									}
								} else {
									System.out.println("\nNot enough money! Please insert cash or select another snack.");
									snackValidInput = true; // Allow user to retry entering a valid option
								}
							} else {
								System.out.println("\nThis snack is sold out. Please select another snack.");
								snackValidInput = true;
							}
						} else {
							System.out.println("Invalid slot number. Please enter a valid slot number.");
						}
					}
					scanner.nextLine(); // Clear the input buffer
					break;
				case "3":
					vendingMachine.logTransaction("GIVE CHANGE", calculator.getBalance(), 0.00);
					calculator.getChange();
					calculator.setBalance(0);
					return; // Exit the method and stop the loop
				default:
					System.out.println("*** "+userInput+" is not a valid option ***");
					break; // Continue the loop for invalid input
			}
		}
	}
	public void endMethodProcessing() { // static attribute used as method is not associated with specific object instance
		// Any processing that needs to be done before method ends
	}

	// Method to generate and save the sales report
	public void generateSalesReport() {
		LocalDateTime timestamp = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.dd.yyyy_hhmm.a");
		String formattedTimestamp = timestamp.format(formatter);
		String salesReportFileName = "SalesReport_" + formattedTimestamp + ".txt";

		double totalSales = 0;

		try (FileWriter writer = new FileWriter(salesReportFileName)) {
			writer.write(String.format("%-25s | %-10s | %-10s%n", "Product", "Quantity Sold", "Total Sales"));
			writer.write("-----------------------------------------\n");

			for (Snack snack : snacks) {
				String productName = snack.getItemName();
				int quantitySold = snack.getItemSaleCount();
				double productTotalSales = snack.getItemPrice() * snack.getItemSaleCount();

				writer.write(String.format("%-25s | %-10d | $%-10.2f%n", productName, quantitySold, productTotalSales));

				totalSales += productTotalSales;
			}

			writer.write("-----------------------------------------\n");
			writer.write(String.format("%-25s | %-10s | $%-10.2f%n", "**TOTAL SALES**", "", totalSales));
		} catch (IOException e) {
			System.out.println("Error writing sales report to file: " + e.getMessage());
		}
	}

}
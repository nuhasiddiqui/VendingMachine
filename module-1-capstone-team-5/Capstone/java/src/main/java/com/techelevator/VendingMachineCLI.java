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
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
													    MAIN_MENU_OPTION_PURCHASE,
													    MAIN_MENU_OPTION_EXIT
													    };
	
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
	
	public void purchaseItems() {	 // static attribute used as method is not associated with specific object instance
		// Code to purchase items from Vending Machine
		Calculator calculator = new Calculator();
		Scanner scanner = new Scanner(System.in);

		do {
			String choiceOne = "(1) Feed Money";
			String choiceTwo = "(2) Select Product";
			String choiceThree = "(3) Finish Transaction";
			// Format the output of calculator.getBalance() to be X.XX
			System.out.printf("Current Balance: $" + "%.2f", calculator.getBalance());
			System.out.println("\n\n" + choiceOne + "\n" + choiceTwo + "\n" + choiceThree + "\n");
			System.out.print("Please choose an option >>> ");
			int userInput = scanner.nextInt();

			boolean isFeedingMoney = userInput == 1;
			boolean isSelectingProduct = userInput == 2;
			boolean isFinishingTransaction = userInput == 3;
			double snackPrice = 0;
			double depositAmount = 0;

			if (isFeedingMoney) {
				System.out.print(">>> ");
				depositAmount = scanner.nextDouble();
				calculator.moneyDeposited(depositAmount);
			} else if (isSelectingProduct) {
				displayItems();
				System.out.print(">>> ");
				try {
					String slotNumber = scanner.next();
					Snack snackSelected = vendingMachine.getSnackBySlotNumber(slotNumber);
					double currentBalance = calculator.getBalance();
					boolean balanceGreaterThanZero = currentBalance > 0;
					boolean isEnoughMoney = currentBalance >= snackSelected.getItemPrice(); //Checking if customer has enough money to buy snack
					if (balanceGreaterThanZero && isEnoughMoney) {
						try {
							vendingMachine.dispenseSnack(snackSelected);
							calculator.updateBalanceAfterPurchase(snackSelected.getItemPrice());

						} catch (IllegalStateException e) { //Catches exception if there are not enough snacks
							System.out.println(e.getMessage());
						}
					} else {
						System.out.println("\n" + "Not enough money! Please insert cash or select another snack.");
					}
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
			} else if (isFinishingTransaction) {
				calculator.getChange();
				calculator.setBalance(0);
			}
		} while (calculator.getBalance() > 0);
	}
	public void endMethodProcessing() { // static attribute used as method is not associated with specific object instance
		// Any processing that needs to be done before method ends
	}
}
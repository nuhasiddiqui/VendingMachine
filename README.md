# VendingMachine
TechElevator, Module 1 Capstone

This is a Java-based simulation of a vending machine, designed to showcase the functionality of a basic vending machine system. The simulation allows users to deposit money, select products, and receive change.

## Table of Contents
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
- [File Structure](#file-structure)

## Features
- Deposit money into the vending machine.
- Select products from available options.
- Display product information, price, and quantity.
- Dispense products and calculate change.
- Keep track of sales and generate sales reports.
- Command-line interface for user interaction.
- Error handling for invalid inputs and insufficient balance.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or later
- Git (for cloning the repository)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/vending-machine.git
2. Navigate to the project directory
   ```bash
   cd vending-machine
3. Compile the Java files:
   ```bash
     javac com/techelevator/*.java

## Usage
1. Run the simulation:
   ```bash
   java com.techelevator.VendingMachineApp
2. Follow the prompts to interact with the vending machine:
  * Deposit money (1)
  * Select a product (2)
  * Finish the transaction (3)
  * Generate a sales report (4)

## File Structure
 * Calculator.java: Manages money-related operations.
  * Item.java: Represents an item in the vending machine.
  * Slot.java: Represents a slot in the vending machine.
  * Snack.java: Represents a snack product.
  * VendingMachine.java: Implements vending machine logic.
  * VendingMachineApp.java: Main entry point for the application.
  * VendingMachineCLI.java: Handles user interaction and menu prompts.
  ```mathematica
    vending-machine/
  ├── com/
  │   └── techelevator/
  │       ├── Calculator.java
  │       ├── Item.java
  │       ├── Slot.java
  │       ├── Snack.java
  │       ├── VendingMachine.java
  │       ├── VendingMachineApp.java
  │       └── VendingMachineCLI.java
  └── Log.txt
  └── SalesReport-YYYYMMDD-HHMMSS.txt

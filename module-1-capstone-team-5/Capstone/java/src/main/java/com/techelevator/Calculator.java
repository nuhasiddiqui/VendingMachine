package com.techelevator;

public class Calculator {
    private double balance;

    // Constructor
    public Calculator (double balance) {
        this.balance = 0;
    }

    // Getters and Setters: balance
    public double getBalance(){
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    private double moneyDeposited (double depositedAmount){
        return this.balance += depositedAmount;
    }

    public double updateBalanceAfterPurchase (double cost){
        return this.balance += cost;
    }

    public void getChange(){
        double remainingBlance = this.balance;
        double change = remainingBlance * 100;
        int numOfQuarters = (int) change / 25;
        change = change % 25;
        int numOfDimes = (int) change / 10;
        change = change % 10;
        int numOfNickels = (int) change / 5;
        change = change % 5;
        int numOfPennies = (int) change;

        if (numOfQuarters > 0) {
            System.out.print("Quarters: " + numOfQuarters + " ");
        }
        if (numOfDimes > 0) {
            System.out.println("Dimes: " + numOfDimes + " ");
        }
        if (numOfNickels > 0) {
            System.out.println("Nickels: " + numOfNickels + " ");
        }
        if (numOfPennies > 0) {
            System.out.println("Pennies: " + numOfPennies + " ");
        }
        System.out.println();

    }
}

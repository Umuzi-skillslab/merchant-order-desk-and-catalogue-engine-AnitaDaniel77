package com.paynestsystem.payment;

public class WalletPayment extends AbstractPaymentMethod implements TapToPay {

    private static final double DEFAULT_BALANCE = 100000; // starting balance for the demo
    private double balance; // money left in the wallet

    public WalletPayment() {
        this(DEFAULT_BALANCE);
    }

    public WalletPayment(double balance) {
        super("WALLET");
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public boolean processPayment(double amount) {
        checkAmount(amount); // shared rule from the parent
        if (amount > balance) { // wallet's own rule
            System.out.println("Wallet balance too low: " + rand(balance) + " available.");
            return false;
        }
        balance = balance - amount; // take the money off the wallet
        System.out.println("Processing wallet payment for " + rand(amount));
        return true;
    }

    @Override
    public boolean tapToPay(double amount) {
        System.out.println("Tap detected");
        return processPayment(amount);
    }
}
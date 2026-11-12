package com.paynestsystem.payment;

public class EftPayment extends AbstractPaymentMethod {

    public EftPayment() {
        super("EFT"); // label used in logs and receipts
    }

    @Override
    public boolean processPayment(double amount) {
        checkAmount(amount); // shared rule from the parent class
        System.out.println("Processing EFT payment for " + rand(amount)); // message owned by this rail
        return true; // simulated payment for the demo
    }
}
package com.paynestsystem.payment;

public class CardPayment extends AbstractPaymentMethod implements TapToPay {

    public CardPayment() {
        super("CARD"); // label used in logs and receipts
    }

    @Override
    public boolean processPayment(double amount) {
        checkAmount(amount); // shared rule from the parent class
        System.out.println("Processing card payment for " + rand(amount)); // message owned by this rail
        return true; // simulated payment for the demo
    }

    @Override
    public boolean tapToPay(double amount) {
        System.out.println("Tap detected"); // only rails with this ability can do it
        return processPayment(amount);
    }
}
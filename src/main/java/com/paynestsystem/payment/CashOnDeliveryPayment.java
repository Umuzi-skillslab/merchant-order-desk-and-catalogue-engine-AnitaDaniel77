package com.paynestsystem.payment;

public class CashOnDeliveryPayment extends AbstractPaymentMethod {

    public CashOnDeliveryPayment() {
        super("CASH_ON_DELIVERY"); // label used in logs and receipts
    }

    @Override
    public boolean processPayment(double amount) {
        checkAmount(amount); // shared rule from the parent class
        System.out.println("Processing cash on delivery payment for " + rand(amount)); // message owned by this rail
        return true; // simulated payment for the demo
    }
}
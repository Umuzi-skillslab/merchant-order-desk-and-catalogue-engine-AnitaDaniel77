package com.paynestsystem.payment;

// Parent class for every rail: holds the label and the checks they all share.
public abstract class AbstractPaymentMethod implements PaymentMethod {

    private final String paymentType;

    protected AbstractPaymentMethod(String paymentType) {
        this.paymentType = paymentType; // every rail must give us its name
    }

    @Override
    public String getPaymentType() {
        return paymentType;
    }

    // Shared rule: the amount must be above zero
    protected void checkAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
    }

    // Shared look for amounts in messages, e.g. R12700.00
    protected String rand(double amount) {
        return "R" + String.format("%.2f", amount);
    }
}
package com.paynestsystem.payment;

// The contract for how a customer pays.
// Order and PaymentProcessor only know about this interface, never a specific rail.
public interface PaymentMethod {
    boolean processPayment(double amount); // process this Rand amount, true if it worked
    String getPaymentType(); // label for logs and receipts, e.g. "CARD"
}
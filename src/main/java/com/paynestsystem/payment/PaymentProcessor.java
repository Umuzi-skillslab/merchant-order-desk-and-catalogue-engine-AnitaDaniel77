package com.paynestsystem.payment;

// The one place that runs a payment.
// It takes the PaymentMethod interface, so a new rail needs no change here.
public class PaymentProcessor {

    public boolean processPayment(PaymentMethod method, double amount) {
        boolean success = method.processPayment(amount); // whichever rail was passed in does the work

        if (success) {
            System.out.println("Payment successful via " + method.getPaymentType());
            System.out.println("Amount: R" + String.format("%.2f", amount));
        } else {
            System.out.println("Payment failed via " + method.getPaymentType());
        }

        return success;
    }
}
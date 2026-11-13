package com.paynestsystem.payment;

public class CardPayment extends AbstractPaymentMethod implements TapToPay {

    private static final String DEFAULT_CVV = "123"; // used by the demo and older tests

    public CardPayment() {
        this(DEFAULT_CVV);
    }

    public CardPayment(String cvv) {
        super("CARD");
        if (!isValidCvv(cvv)) { // card's own rule
            throw new IllegalArgumentException("CVV must be exactly 3 digits.");
        }
        // the CVV is only checked here, never stored
    }

    // true only when the text is exactly 3 digits
    private static boolean isValidCvv(String cvv) {
        if (cvv == null || cvv.length() != 3) {
            return false;
        }
        for (int i = 0; i < cvv.length(); i++) {
            if (!Character.isDigit(cvv.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean processPayment(double amount) {
        checkAmount(amount); // shared rule from the parent
        System.out.println("Processing card payment for " + rand(amount));
        return true;
    }

    @Override
    public boolean tapToPay(double amount) {
        System.out.println("Tap detected");
        return processPayment(amount);
    }
}
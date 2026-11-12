package com.paynestsystem.payment;

// An extra ability, not every rail has it: card and wallet can tap, EFT and cash cannot.
public interface TapToPay {
    boolean tapToPay(double amount); // pay by tapping the card or phone
}
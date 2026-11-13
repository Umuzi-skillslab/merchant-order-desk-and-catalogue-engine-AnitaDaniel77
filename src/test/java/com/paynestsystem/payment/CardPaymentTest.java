package com.paynestsystem.payment;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CardPaymentTest {

    @Test
    void cardAcceptsAThreeDigitCvv() {
        CardPayment card = new CardPayment("456");
        assertTrue(card.processPayment(100));
    }

    @Test
    void cardRejectsABadCvv() {
        assertThrows(IllegalArgumentException.class, () -> new CardPayment("12")); // too short
        assertThrows(IllegalArgumentException.class, () -> new CardPayment("1234")); // too long
        assertThrows(IllegalArgumentException.class, () -> new CardPayment("abc")); // not digits
        assertThrows(IllegalArgumentException.class, () -> new CardPayment(null)); // missing
    }
}
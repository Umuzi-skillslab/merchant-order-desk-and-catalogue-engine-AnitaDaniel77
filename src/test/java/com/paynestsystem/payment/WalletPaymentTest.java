package com.paynestsystem.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class WalletPaymentTest {

    @Test
    void walletRefusesPaymentAboveItsBalance() {
        WalletPayment wallet = new WalletPayment(100);
        assertFalse(wallet.processPayment(500)); // too much, so it fails
        assertEquals(100, wallet.getBalance(), 0.001); // balance untouched
    }

    @Test
    void walletBalanceGoesDownAfterPayment() {
        WalletPayment wallet = new WalletPayment(1000);
        assertTrue(wallet.processPayment(400));
        assertEquals(600, wallet.getBalance(), 0.001);
    }
}
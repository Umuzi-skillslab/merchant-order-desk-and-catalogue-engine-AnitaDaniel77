package com.paynestsystem.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class PaymentMethodTest {

    @Test
    void everyRailProcessesAndReportsItsType() {
        PaymentMethod card = new CardPayment();
        PaymentMethod eft = new EftPayment();
        PaymentMethod wallet = new WalletPayment();
        PaymentMethod cash = new CashOnDeliveryPayment();

        assertTrue(card.processPayment(12400));
        assertTrue(eft.processPayment(12400));
        assertTrue(wallet.processPayment(12400));
        assertTrue(cash.processPayment(12400));
        assertEquals("CARD", card.getPaymentType());
        assertEquals("EFT", eft.getPaymentType());
        assertEquals("WALLET", wallet.getPaymentType());
        assertEquals("CASH_ON_DELIVERY", cash.getPaymentType());
    }

    @Test
    void zeroAndNegativeAmountsAreRejected() {
        PaymentMethod card = new CardPayment();

        assertThrows(IllegalArgumentException.class, () -> card.processPayment(0));
        assertThrows(IllegalArgumentException.class, () -> card.processPayment(-5));
    }

    @Test
    void onlyCardAndWalletCanTapToPay() {
        assertTrue(new CardPayment() instanceof TapToPay);
        assertTrue(new WalletPayment() instanceof TapToPay);
        assertFalse(new EftPayment() instanceof TapToPay);
        assertFalse(new CashOnDeliveryPayment() instanceof TapToPay);
        assertTrue(new CardPayment().tapToPay(12400));
    }
}
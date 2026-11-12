package com.paynestsystem;

import com.paynestsystem.domain.Customer;
import com.paynestsystem.domain.Order;
import com.paynestsystem.domain.Product;
import com.paynestsystem.payment.CardPayment;
import com.paynestsystem.payment.CashOnDeliveryPayment;
import com.paynestsystem.payment.EftPayment;
import com.paynestsystem.payment.PaymentMethod;
import com.paynestsystem.payment.WalletPayment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutTest {

    // Test-only rail that remembers the amount it was asked to charge
    static class RecordingPayment implements PaymentMethod {
        double chargedAmount;

        @Override
        public boolean processPayment(double amount) {
            chargedAmount = amount;
            return true;
        }

        @Override
        public String getPaymentType() {
            return "RECORDING";
        }
    }

    // Test-only rail that always fails
    static class FailingPayment implements PaymentMethod {
        @Override
        public boolean processPayment(double amount) {
            return false;
        }

        @Override
        public String getPaymentType() {
            return "FAILING";
        }
    }

    // Stands in for a rail added next sprint: new class, no change to Order
    static class BuyNowPayLaterPayment implements PaymentMethod {
        @Override
        public boolean processPayment(double amount) {
            return true;
        }

        @Override
        public String getPaymentType() {
            return "BNPL";
        }
    }

    Customer customer = new Customer(1, "Siphokazi Dlamini", "siphokazi@email.com");
    Product laptop = new Product(1, "Laptop", new BigDecimal("12000.00"));
    Product mouse = new Product(2, "Wireless Mouse", new BigDecimal("350.00"));

    private Order orderWithItems() {
        Order order = new Order(1, customer);
        order.addItem(laptop, 1);
        order.addItem(mouse, 2);
        return order; // total is 12700.00
    }

    @Test
    void checkoutWorksWithEveryRail() {
        PaymentMethod[] rails = { new CardPayment(), new EftPayment(), new WalletPayment(), new CashOnDeliveryPayment() };

        for (PaymentMethod rail : rails) {
            assertTrue(orderWithItems().checkout(rail));
        }
    }

    @Test
    void amountChargedEqualsOrderTotal() {
        Order order = orderWithItems();
        RecordingPayment recorder = new RecordingPayment();

        order.checkout(recorder);

        assertEquals(order.calculateTotal().doubleValue(), recorder.chargedAmount);
        assertEquals(12700.00, recorder.chargedAmount);
    }

    @Test
    void failedPaymentMakesCheckoutReturnFalse() {
        assertFalse(orderWithItems().checkout(new FailingPayment()));
    }

    @Test
    void newRailWorksWithoutChangingOrder() {
        assertTrue(orderWithItems().checkout(new BuyNowPayLaterPayment()));
    }

    @Test
    void nullPaymentMethodIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> orderWithItems().checkout(null));
    }
}
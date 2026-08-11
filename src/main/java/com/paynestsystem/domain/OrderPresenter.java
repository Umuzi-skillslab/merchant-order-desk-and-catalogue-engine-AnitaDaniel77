package com.paynestsystem.domain;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

// Handles turning an Order into console output.
// Pulled out of the Order itself so the domain object only holds
// business data and rules, not display/formatting logic.

public class OrderPresenter {

    // South African Rand currency formatting
    private static final NumberFormat CURRENCY = NumberFormat.getCurrencyInstance(Locale.of("en", "ZA"));

    public static void printSummary(Order order) {
        System.out.println("PayNest Order Summary");
        System.out.println("Order ID  : " + order.getId());
        System.out.println("Customer  : " + order.getCustomer().getName());
        System.out.println("Email     : " + order.getCustomer().getEmail());
        System.out.println("---");

        // Print each line item
        for (OrderItem item : order.getItems()) {
            String name = item.getProduct().getName();
            int qty = item.getQuantity();
            BigDecimal subtotal = item.calculateTotal();
            System.out.println(name + " x" + qty + " = " + CURRENCY.format(subtotal));
        }

        System.out.println("---");
        System.out.println("Grand Total: " + CURRENCY.format(order.calculateTotal()));
    }
}
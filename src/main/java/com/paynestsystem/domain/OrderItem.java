package com.paynestsystem.domain;

import java.math.BigDecimal; // Change: needed for BigDecimal price

public class OrderItem {

    // An order item links one product to how many were bought
    // Change: added final, these values never change after construction
    private final Product product;
    private final int quantity;

    // Constructor: runs when you add a product to an order
    // Example: new OrderItem(laptop, 2)
    public OrderItem(Product product, int quantity) {
        // reject zero or negative quantities immediately
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be a positive integer.");
        }
        this.product = product;   // save the product
        this.quantity = quantity; // save the quantity
    }

    // Getters: other classes use these to read the values
    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    // Calculates the subtotal for this line
    // Example: Laptop R12000 x 2 = R24000
    // Change: uses Bigdecimal.multiply instead instead of the * operator,
    // and BigDecimal.valueOf to turn quantity (an int) into a BigDecimal
    public BigDecimal calculateTotal() {
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
package com.paynestsystem.domain;

public class OrderItem {

    // An order item links one product to how many were bought
    private Product product;
    private int quantity;

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
    public double calculateTotal() {
        return product.getPrice() * quantity;
    }
}
package com.paynestsystem.domain;

/**
 * Links one Product to a quantity within an Order.
 * Responsible for calculating its own line subtotal.
 */
public class OrderItem {

    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be a positive integer.");
        }
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double calculateTotal() {
        return product.getPrice() * quantity;
    }
}

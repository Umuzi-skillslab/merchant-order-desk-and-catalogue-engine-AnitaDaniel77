package com.paynestsystem.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a customer order containing one or more line items.
 * Owns its list of OrderItems — callers add items through addItem()
 * so totals can never be silently corrupted by outside modification.
 */
public class Order {

    private int id;
    private Customer customer;
    private List<OrderItem> items;

    public Order(int id, Customer customer) {
        this.id = id;
        this.customer = customer;
        this.items = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        items.add(new OrderItem(product, quantity));
    }

    public double calculateTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.calculateTotal();
        }
        return total;
    }

    public void printSummary() {
        System.out.println("========================================");
        System.out.println("PayNest Order Summary");
        System.out.println("========================================");
        System.out.println("Order ID : " + id);
        System.out.println("Customer : " + customer.getName());
        System.out.println("Email    : " + customer.getEmail());
        System.out.println("----------------------------------------");
        System.out.printf("%-20s %5s %12s%n", "Product", "Qty", "Subtotal");
        System.out.println("----------------------------------------");
        for (OrderItem item : items) {
            System.out.printf("%-20s %5d %10s%n",
                item.getProduct().getName(),
                item.getQuantity(),
                String.format("R%.2f", item.calculateTotal()));
        }
        System.out.println("----------------------------------------");
        System.out.printf("%-20s %5s %10s%n",
            "GRAND TOTAL", "",
            String.format("R%.2f", calculateTotal()));
        System.out.println("========================================");
    }
}

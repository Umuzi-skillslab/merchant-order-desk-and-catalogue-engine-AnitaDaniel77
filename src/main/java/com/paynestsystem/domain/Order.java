package com.paynestsystem.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {

    // Every order has an id, a customer, and a list of items
    private int id;
    private Customer customer;
    private List<OrderItem> items;

    // Constructor: creates a new empty order for a customer
    public Order(int id, Customer customer) {
        this.id = id;
        this.customer = customer;
        this.items = new ArrayList<>(); // empty list, grows as items are added
    }

    // Getters
    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    // Returns the list as read-only so nothing outside can change it
    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    // The only way to add a product to the order
    // This keeps control in one place
    public void addItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        items.add(new OrderItem(product, quantity));
    }

    // Adds up all the line subtotals to get the grand total
    public double calculateTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.calculateTotal();
        }
        return total;
    }

    // Prints the order to the console like a simple receipt
    public void printSummary() {
        System.out.println("PayNest Order Summary");
        System.out.println("Order ID  : " + id);
        System.out.println("Customer  : " + customer.getName());
        System.out.println("Email     : " + customer.getEmail());
        System.out.println("---");

        // Print each line item
        for (OrderItem item : items) {
            String name = item.getProduct().getName();
            int qty = item.getQuantity();
            double subtotal = item.calculateTotal();
            System.out.println(name + " x" + qty + " = R" + subtotal);
        }

        System.out.println("---");
        System.out.println("Grand Total: R" + calculateTotal());
    }
}
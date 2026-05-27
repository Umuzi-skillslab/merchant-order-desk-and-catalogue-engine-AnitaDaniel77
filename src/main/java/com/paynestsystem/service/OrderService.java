package com.paynestsystem.service;

import com.paynestsystem.domain.Customer;
import com.paynestsystem.domain.Order;
import com.paynestsystem.domain.Product;

public class OrderService {

    // Keeps track of the next order id
    // Every new order gets a unique number starting from 1
    private int nextOrderId = 1;

    // Creates a new empty order for a customer
    // The id increases by 1 each time a new order is created
    public Order createOrder(Customer customer) {
        return new Order(nextOrderId++, customer);
    }

    // Adds a product with a quantity to an existing order
    public void addItem(Order order, Product product, int quantity) {
        order.addItem(product, quantity);
    }
}
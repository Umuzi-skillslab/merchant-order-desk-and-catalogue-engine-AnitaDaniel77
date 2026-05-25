package com.paynestsystem.service;

import com.paynestsystem.domain.Customer;
import com.paynestsystem.domain.Order;
import com.paynestsystem.domain.Product;

/**
 * Creates and manages orders on behalf of the application layer.
 * Keeping order creation here means PayNestApplication stays thin
 * and order logic can be extended without touching the demo runner.
 */
public class OrderService {

    private int nextOrderId = 1;

    public Order createOrder(Customer customer) {
        return new Order(nextOrderId++, customer);
    }

    public void addItem(Order order, Product product, int quantity) {
        order.addItem(product, quantity);
    }
}

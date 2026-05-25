package com.paynestsystem.app;

import com.paynestsystem.domain.Customer;
import com.paynestsystem.domain.Order;
import com.paynestsystem.domain.Product;
import com.paynestsystem.service.OrderService;

/**
 * Entry point for the PayNest CLI demo.
 * Run with: mvn exec:java
 */
public class PayNestApplication {

    public static void main(String[] args) {

        // Product catalogue
        Product laptop = new Product(1, "Laptop", 12000.00);
        Product mouse = new Product(2, "Wireless Mouse", 350.00);
        Product hdmiCable = new Product(3, "HDMI Cable", 150.00);

        // Customer
        Customer customer = new Customer(1, "Siphokazi Dlamini", "siphokazi@email.com");

        // Create order via OrderService
        OrderService orderService = new OrderService();
        Order order = orderService.createOrder(customer);

        // Add line items
        orderService.addItem(order, laptop, 1);
        orderService.addItem(order, mouse, 2);
        orderService.addItem(order, hdmiCable, 3);

        // Print receipt
        order.printSummary();
    }
}

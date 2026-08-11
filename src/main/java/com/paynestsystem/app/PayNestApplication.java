package com.paynestsystem.app;

import java.math.BigDecimal; // Change: needed for BigDecimal price
import com.paynestsystem.domain.Customer;
import com.paynestsystem.domain.Order;
import com.paynestsystem.domain.OrderPresenter; // Change: new import for the presenter
import com.paynestsystem.domain.Product;
import com.paynestsystem.service.OrderService;

public class PayNestApplication {

    public static void main(String[] args) {

        // Step 1: Create the products the merchant sells
        // Change: prices are now BigDecimal instead of double
       Product laptop = new Product(1, "Laptop", new BigDecimal("12000.00"));
Product mouse = new Product(2, "Wireless Mouse", new BigDecimal("350.00"));
Product hdmiCable = new Product(3, "HDMI Cable", new BigDecimal("150.00"));

        // Step 2: Create the customer placing the order
        Customer customer = new Customer(1, "Anita Daniel", "anita@email.com");

        // Step 3: Use OrderService to create an empty order for the customer
        OrderService orderService = new OrderService();
        Order order = orderService.createOrder(customer);

        // Step 4: Add products to the order
        // laptop x1, mouse x2, hdmi cable x3
        orderService.addItem(order, laptop, 1);
        orderService.addItem(order, mouse, 2);
        orderService.addItem(order, hdmiCable, 3);

        // Step 5: Print the order summary to the console
        // CHANGE: printSummary moved out of Order - now called on
        // OrderPresenter instead, passing the order in
        OrderPresenter.printSummary(order);
    }
}
package com.paynestsystem.app;

import com.paynestsystem.domain.Customer;
import com.paynestsystem.domain.Order;
import com.paynestsystem.domain.OrderPresenter;
import com.paynestsystem.domain.Product;
import com.paynestsystem.payment.CardPayment;
import com.paynestsystem.payment.PaymentMethod;
import com.paynestsystem.service.OrderService;
import java.math.BigDecimal;

public class PayNestApplication {

    public static void main(String[] args) {

        // Step 1: Create the products the merchant sells
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
        OrderPresenter.printSummary(order);

        // Step 6: Pick a payment rail and check out
        // To demo another rail, change only this line (new EftPayment() or new WalletPayment())
        PaymentMethod paymentMethod = new CardPayment();
        boolean paid = order.checkout(paymentMethod);

        // Step 7: Show the outcome
        if (paid) {
            OrderPresenter.printOrderComplete(order, paymentMethod);
        } else {
            System.out.println("Order " + order.getId() + " was not completed.");
        }
    }
}
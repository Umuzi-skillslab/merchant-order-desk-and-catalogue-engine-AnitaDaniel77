package com.paynestsystem.domain;

import java.math.BigDecimal; // Change: needed for BigDecimal price
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.paynestsystem.payment.PaymentMethod; // needed for checkout
import com.paynestsystem.payment.PaymentProcessor; // needed for checkout

public class Order {

    // Every order has an id, a customer, and a list of items
    // Change: added final; id, customer, and items never get
    // reassigned after construction (items still grows via addItem,
    // but the LIST OBJECT itself never changes)
    private final int id;
    private final Customer customer;
    private final List<OrderItem> items;

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
    // Change: returns BigDecimal now, starts from BigDecimal.ZERO
    //  instead of 0.0, and uses BigDecimal.add instead of +
    public BigDecimal calculateTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem item : items) {
            total = total.add(item.calculateTotal());
        }
        return total;
    }

    // Capstone 3: one checkout path for every payment rail.
    // Order only knows the PaymentMethod interface, never CardPayment or EftPayment.
    public boolean checkout(PaymentMethod paymentMethod) {
        if (paymentMethod == null) {
            throw new IllegalArgumentException("Payment method cannot be null.");
        }
        BigDecimal total = calculateTotal(); // same total as Capstone 1, worked out once
        PaymentProcessor processor = new PaymentProcessor();
        return processor.processPayment(paymentMethod, total.doubleValue()); // brief's contract takes a double
    }

    // CHANGE: printSummary() removed from here entirely printing
    // is not the domain object's job. It now lives in a separate
    // OrderPresenter class (see OrderPresenter.java), so Order stays
    // focused purely on business data and rules
}
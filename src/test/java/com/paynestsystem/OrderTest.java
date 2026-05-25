package com.paynestsystem;

import com.paynestsystem.domain.Customer;
import com.paynestsystem.domain.Order;
import com.paynestsystem.domain.OrderItem;
import com.paynestsystem.domain.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    // Sample data
    Product laptop = new Product(1, "Laptop", 12000.00);
    Product mouse = new Product(2, "Wireless Mouse", 350.00);
    Customer customer = new Customer(1, "Siphokazi Dlamini", "siphokazi@email.com");

    @Test
    void orderItemCalculatesLineTotal() {
        OrderItem item = new OrderItem(laptop, 2);
        assertEquals(24000.00, item.calculateTotal());
    }

    @Test
    void orderTotalSumsAllLineItems() {
        Order order = new Order(1, customer);
        order.addItem(laptop, 1);
        order.addItem(mouse, 2);
        assertEquals(12700.00, order.calculateTotal());
    }

    @Test
    void emptyOrderHasZeroTotal() {
        Order order = new Order(1, customer);
        assertEquals(0.00, order.calculateTotal());
    }

    @Test
    void addItemIncreasesItemCount() {
        Order order = new Order(1, customer);
        order.addItem(laptop, 1);
        order.addItem(mouse, 3);
        assertEquals(2, order.getItems().size());
    }

    @Test
    void invalidQuantityThrowsException() {
        Order order = new Order(1, customer);
        assertThrows(IllegalArgumentException.class, () -> order.addItem(laptop, 0));
        assertThrows(IllegalArgumentException.class, () -> order.addItem(laptop, -1));
    }

    @Test
    void nullProductThrowsException() {
        Order order = new Order(1, customer);
        assertThrows(IllegalArgumentException.class, () -> order.addItem(null, 1));
    }
}

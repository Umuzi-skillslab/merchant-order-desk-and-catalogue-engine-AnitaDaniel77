package com.paynestsystem;

import com.paynestsystem.domain.Customer;
import com.paynestsystem.domain.Order;
import com.paynestsystem.domain.OrderItem;
import com.paynestsystem.domain.Product;
import com.paynestsystem.service.OrderService; // Change: new import, needed for the ID allocation test
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List; // Change: new import, needed for the immutability test

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    // Sample data
    Product laptop = new Product(1, "Laptop", new BigDecimal("12000.00"));
    Product mouse = new Product(2, "Wireless Mouse", new BigDecimal("350.00"));
    Customer customer = new Customer(1, "Siphokazi Dlamini", "siphokazi@email.com");

    @Test
    void orderItemCalculatesLineTotal() {
        OrderItem item = new OrderItem(laptop, 2);
        assertEquals(new BigDecimal("24000.00"), item.calculateTotal());
    }

    @Test
    void orderTotalSumsAllLineItems() {
        Order order = new Order(1, customer);
        order.addItem(laptop, 1);
        order.addItem(mouse, 2);
        assertEquals(new BigDecimal("12700.00"), order.calculateTotal());
    }

    @Test
    void emptyOrderHasZeroTotal() {
        Order order = new Order(1, customer);
        assertEquals(BigDecimal.ZERO, order.calculateTotal());
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

    // Change: new test - confirms getItems() genuinely can't be modified
    // from outside Order, closing the encapsulation gap from peer review
    @Test
    void getItemsReturnsUnmodifiableList() {
        Order order = new Order(1, customer);
        order.addItem(laptop, 1);

        List<OrderItem> items = order.getItems();

        assertThrows(UnsupportedOperationException.class, () -> {
            items.add(new OrderItem(mouse, 1));
            // trying to add directly to the returned list should fail -
            // the ONLY legitimate way to add an item is order.addItem(...)
        });
    }

    // Change: new test - confirms OrderService hands out unique,
    // incrementing order IDs rather than something arbitrary
    @Test
    void serviceAllocatesIncrementingOrderIds() {
        OrderService service = new OrderService();

        Order first = service.createOrder(customer);
        Order second = service.createOrder(customer);

        assertEquals(1, first.getId());
        assertEquals(2, second.getId());
        assertNotEquals(first.getId(), second.getId());
    }
}
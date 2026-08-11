package com.paynestsystem.domain;

import java.math.BigDecimal; // Change: needed for BigDecimal price

public class Product {

    // Each product has an id, a name, and a price
    // private means only this class can access them directly
    // Change: added final, these values never change after construction
    private final int id;
    private final String name;
    // Change: price is now BigDecimal instead of double avoids
    // floating-point rounding errors when doing money math
    private final BigDecimal price;

    // Constructor: runs when you create a new Product
    // Example: new Product(1, "Laptop", new BigDecimal("12000.00"))
    // change: price parameter os now BigDecimal, not double
    public Product(int id, String name, BigDecimal price) {
        this.id = id;       // save the id
        this.name = name;   // save the name
        this.price = price; // save the price
    }

    // Getters: other classes use these to read the values
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
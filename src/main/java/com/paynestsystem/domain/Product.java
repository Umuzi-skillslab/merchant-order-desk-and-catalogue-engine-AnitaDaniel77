package com.paynestsystem.domain;

public class Product {

    // Each product has an id, a name, and a price
    // private means only this class can access them directly
    private int id;
    private String name;
    private double price;

    // Default constructor: needed for some frameworks and deserialization
    public Product() {
    }

    // Constructor: runs when you create a new Product
    // Example: new Product(1, "Laptop", 12000.00)
    public Product(int id, String name, double price) {
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

    public double getPrice() {
        return price;
    }
}
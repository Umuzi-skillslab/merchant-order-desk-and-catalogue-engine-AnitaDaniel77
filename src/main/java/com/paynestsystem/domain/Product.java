package com.paynestsystem.domain;

/**
 * Represents a product in the PayNest merchant catalogue.
 * Each product has a unique id, a display name, and a unit price in Rand.
 */
public class Product {

    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

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

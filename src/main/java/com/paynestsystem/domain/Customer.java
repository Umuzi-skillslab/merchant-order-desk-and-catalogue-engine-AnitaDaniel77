package com.paynestsystem.domain;

public class Customer {

    // Each customer has an id, a name, and an email
    // private means only this class can access them directly
    private int id;
    private String name;
    private String email;

    // Default constructor: needed for some frameworks and deserialization
    public Customer() {
    }

    // Constructor: runs when you create a new Customer
    // Example: new Customer(1, "Anita Daniel", "anita@email.com")
    public Customer(int id, String name, String email) {
        this.id = id;       // save the id
        this.name = name;   // save the name
        this.email = email; // save the email
    }

    // Getters: other classes use these to read the values
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
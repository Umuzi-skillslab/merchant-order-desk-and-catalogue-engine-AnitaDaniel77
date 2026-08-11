package com.paynestsystem.domain;

public class Customer {

    // Each customer has an id, a name, and an email
    // private means only this class can access them directly
    // Change: added final as these values never change after construction
    private final int id;
    private final String name;
    private final String email;

    // Default constructor: needed for some frameworks and deserialization
    // Change: removed; can't have a no-arg constructor once fields are final
    // (final fields must be set in every constructor, so this one had to go)

    // Constructor: runs when you create a new Customer
    // Example: new Customer(1, "Anita Daniel", "anita@email.com")
    public Customer(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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
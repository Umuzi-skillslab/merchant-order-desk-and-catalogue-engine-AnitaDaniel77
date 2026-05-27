# PayNest Merchant Order Desk and Catalogue Engine

## Overview
PayNest is a lightweight commerce tool for small South African merchants.
Small businesses currently manage orders through spreadsheets and WhatsApp messages,
which breaks down under growth: line totals disagree, staff add duplicate products,
and there is no single reliable way to calculate what a customer owes.

This project solves that by building a minimal commerce kernel in plain Java 21:
products with prices, customers, orders with line items, and a printed order summary.
No frameworks, no database, just solid Java objects a developer can extend later.

## Prerequisites
- Java 21
- Apache Maven 3.9+

## How to Run the Demo

Open your terminal in the project folder and run:

    mvn exec:java

This will print an order summary to the console showing the customer name,
each product with its quantity and subtotal, and the grand total in Rand.

## How to Run the Tests

    mvn test

You should see 6 tests pass with no failures.

## Project Structure

The code is organised into three packages:

- domain: the core business objects: Product, Customer, OrderItem, Order
- service: OrderService creates orders and adds items
- app: PayNestApplication runs the demo from the command line

## Design Decisions

- OrderItem: calculates its own line subtotal so Order just adds them up
- Order: keeps its list of items private so nothing outside can change totals
- OrderService: sits between the app and the domain so PayNestApplication stays simple
- Invalid quantities and null products are rejected immediately with a clear error

## Business Rules

- Line subtotal is unit price multiplied by quantity
- Grand total is the sum of all line subtotals
- Quantities must be greater than zero
- Adding new fields to Product will not require changes to Order or OrderService
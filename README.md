# PayNest Merchant Order Desk and Catalogue Engine

## Overview

PayNest is a lightweight commerce tool for small South African merchants.
Small businesses currently manage orders through spreadsheets and WhatsApp messages,
which breaks down under growth: line totals disagree, staff add duplicate products,
and there is no single reliable way to calculate what a customer owes.

This project solves that by building a minimal commerce kernel in plain Java 21:
products with prices, customers, orders with line items, a printed order summary,
and (Capstone 3) one checkout path that accepts any payment rail.
No frameworks, no database, just solid Java objects a developer can extend later.

## Prerequisites

- Java 21
- Apache Maven 3.9+

## How to Run the Demo

Open your terminal in the project folder and run:

    mvn compile exec:java -Dexec.mainClass="com.paynestsystem.app.PayNestApplication"

This prints the order summary (customer, each product with its quantity and subtotal,
and the grand total in Rand), then checks the order out with a card payment and prints
the payment rail, the amount charged and an order completed line.

To try another rail, change one line in PayNestApplication:
`new CardPayment()` to `new EftPayment()`, `new WalletPayment()` or `new CashOnDeliveryPayment()`.

## How to Run the Tests

    mvn test

You should see 16 tests pass with no failures: the 8 from Capstone 1, three payment rail tests
(all four rails, bad amounts rejected, tap to pay only on card and wallet), and five checkout
tests (amount charged equals the order total, a failed payment, a new rail added without
touching Order, and a null payment method).

## Project Structure

The code is organised into four packages:

- domain: the core business objects: Product, Customer, OrderItem, Order, OrderPresenter
- service: OrderService creates orders and adds items
- payment: the PaymentMethod interface, the TapToPay ability, AbstractPaymentMethod (parent class), CardPayment, EftPayment, WalletPayment, CashOnDeliveryPayment and PaymentProcessor
- app: PayNestApplication runs the demo from the command line

The class diagram for the payment layer is in docs/class-diagram.md.

## Design Decisions

- OrderItem: calculates its own line subtotal so Order just adds them up
- Order: keeps its list of items private and returns an unmodifiable view via getItems(),
  so nothing outside the class can change totals by mutating the returned list
- OrderService: sits between the app and the domain so PayNestApplication stays simple,
  and allocates a unique, incrementing ID to every order it creates
- OrderPresenter: handles printing an order summary to the console, kept separate from
  Order so the domain object stays focused purely on business data and rules
- Prices and totals use BigDecimal instead of double, avoiding floating-point
  rounding errors when doing money calculations
- Product, Customer, OrderItem, and Order fields are all final, since none of
  these values should change after construction
- Invalid quantities and null products are rejected immediately with a clear error
- PaymentMethod: one interface every payment rail implements, so Order.checkout() accepts any rail
- AbstractPaymentMethod: parent class that holds each rail's label and the shared amount check,
  so a new rail only writes how it takes the money
- TapToPay: a separate ability interface, so only card and wallet can tap; EFT and cash on delivery cannot
- PaymentProcessor: the only class that runs a payment, and it only knows the interface
- Order.checkout(): works out the total once and passes it to the chosen rail
- Printing the completion line stays in OrderPresenter, so Order does not print anything

## Business Rules

- Line subtotal is unit price multiplied by quantity
- Grand total is the sum of all line subtotals
- Quantities must be greater than zero
- Adding new fields to Product will not require changes to Order or OrderService
- The amount charged at checkout equals order.calculateTotal(), with no discounts
- Every rail has a fixed label (CARD, EFT, WALLET, CASH_ON_DELIVERY) for logs and receipts
- Adding a new rail means one new class (extending AbstractPaymentMethod) plus one wiring line in PayNestApplication
- Assumption: payments are simulated and always succeed, and the payment layer takes a double as
  the brief specifies, so the BigDecimal order total is converted once at checkout (money maths
  in the order stays BigDecimal)

## Why an interface instead of one big method

A single checkout method would need an if or switch branch for every payment type, so each
new rail means editing code that already works and has been tested. With the PaymentMethod
interface, Order and PaymentProcessor depend on one contract (process this Rand amount, and
describe yourself) and never on CardPayment, EftPayment or WalletPayment. Each rail keeps its
own message and label, so adding BuyNowPayLaterPayment is one new class plus one line in
PayNestApplication. The order total calculation is not touched. It is like a wall socket:
the socket stays the same, and only the plug changes.

## Revision Notes

This project was revised following peer review feedback (thank you, Ngoako Ramokgopa)
to strengthen a few areas beyond the original submission:

- Switched Product, OrderItem, and Order from double to BigDecimal for all price
  and total calculations
- Made all domain object fields final
- Moved order-summary printing out of Order into a dedicated OrderPresenter class
- Added tests confirming getItems() returns a genuinely unmodifiable list, and that
  OrderService allocates unique, incrementing order IDs

Capstone 3 then added the payment layer and checkout flow on top of this model, and
Hilbert's SME session feedback shaped the parent class and the TapToPay ability.
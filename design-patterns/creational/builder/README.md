# Builder Design Pattern 🏗️

## Overview
The **Builder Pattern** is a creational design pattern that separates the construction of a complex object from its representation. It allows you to construct objects step-by-step, using only the steps you need, while maintaining a clean and readable API.

## Real-World Analogy 🌍
Think of **ordering a customized burger** at a restaurant:
- **Required**: Bun, patty (you must have these)
- **Optional**: Lettuce, tomato, cheese, onions, pickles, sauce, bacon...

Instead of having 50 different burger types pre-made, you **build** your burger step by step:
```
new BurgerBuilder()
    .patty("Chicken")
    .cheese("Cheddar")
    .addLettuce()
    .addOnions()
    .noPickles()
    .build();
```

---

## Problem It Solves ❌

### Without Builder Pattern (Telescoping Constructor):
```java
// Which parameter is which?! 😵
Transaction txn = new Transaction(
    "TXN001",     // What's this?
    1000.0,       // Amount?
    "INR",        // Currency?
    "User1",      // Sender?
    "User2",      // Receiver?
    null,         // What's null?
    "UPI",        // Payment method?
    "SUCCESS",    // Status?
    "Payment",    // Note?
    null,         // Another null?
    true,         // Boolean for what?
    1.0,          // What's 1.0?
    "GENERAL"     // Category?
);
```

**Problems:**
- ❌ Hard to read and maintain
- ❌ Easy to mix up parameter order
- ❌ Must pass `null` for optional fields
- ❌ No validation before object creation
- ❌ Constructor grows with each new field

### With Builder Pattern:
```java
Transaction txn = new Transaction.Builder("TXN001", 1000.0, "INR", "User1", "User2")
    .paymentMethod("UPI")
    .status("SUCCESS")
    .note("Payment for services")
    .refundable(true)
    .build();
```

**Benefits:**
- ✅ Crystal clear what each value means
- ✅ Only specify what you need
- ✅ Validation happens in `build()`
- ✅ Immutable object after construction
- ✅ Easy to add new optional fields

---

## Structure 🏗️

```mermaid
classDiagram
    class Transaction {
        -String transactionId
        -double amount
        -String currency
        -String senderId
        -String receiverId
        -LocalDateTime timestamp
        -String paymentMethod
        -String status
        -String note
        -String location
        -boolean isRefundable
        -double conversionRate
        -String category
        -Transaction(Builder builder)
        +getTransactionId() String
        +getAmount() double
        +displayDetails()
    }
    
    class Builder {
        -String transactionId
        -double amount
        -String currency
        -String senderId
        -String receiverId
        -LocalDateTime timestamp
        -String paymentMethod
        -String status
        -String note
        -String location
        -boolean isRefundable
        -double conversionRate
        -String category
        +Builder(id, amount, currency, senderId, receiverId)
        +paymentMethod(String) Builder
        +status(String) Builder
        +note(String) Builder
        +location(String) Builder
        +refundable(boolean) Builder
        +conversionRate(double) Builder
        +category(String) Builder
        +build() Transaction
        -validate()
    }
    
    Transaction +-- Builder : contains
    Builder --> Transaction : creates
    
    note for Builder "Inner static class\nFluent API with method chaining\nValidation in build()"
    note for Transaction "Immutable after construction\nPrivate constructor"
```

### Components:

1. **Product** ([Transaction.java](Transaction.java))
   - The complex object being built
   - Has many fields (required + optional)
   - Private constructor (only Builder can create it)
   - Immutable after construction

2. **Builder** (Inner class in Transaction.java)
   - Provides fluent API for step-by-step construction
   - Validates data before creating the product
   - Returns `this` for method chaining

---

## Fintech Use Cases 💰

### 1. **Transaction Builder** (Our Example)
Complex transactions with many optional fields

### 2. **Account Creation**
```java
Account account = new Account.Builder("ACC001", "John Doe")
    .accountType("SAVINGS")
    .branch("Mumbai")
    .initialDeposit(10000)
    .enableNetBanking()
    .enableDebitCard()
    .build();
```

### 3. **Loan Application**
```java
LoanApplication loan = new LoanApplication.Builder(500000, 60)
    .purpose("Home Renovation")
    .collateral("Property")
    .coApplicant("Jane Doe")
    .insuranceRequired(true)
    .build();
```

### 4. **Investment Order**
```java
Order order = new Order.Builder("INFY", 100, OrderType.BUY)
    .limitPrice(1500.00)
    .stopLoss(1400.00)
    .validity(Validity.DAY)
    .build();
```

---

## When to Use Builder Pattern? 🤔

✅ **Use when:**
- Object has many constructor parameters (4+)
- Some parameters are optional
- You want immutable objects
- Object needs validation before creation
- You want readable, self-documenting code
- Construction process involves multiple steps

❌ **Don't use when:**
- Object has only 2-3 fields
- All fields are required
- Object is mutable anyway
- Simplicity is more important

---

## How to Run 🚀

### From the `oops` directory (Recommended):

```bash
# Navigate to the project root
cd /path/to/project

# Compile with package structure
javac -d . design-patterns/creational/builder/*.java

# Run the demo
java creational.builder.BuilderPatternDemo
```

### From the `builder` directory:

```bash
# Navigate to the builder pattern directory
cd /path/to/project/design-patterns/creational/builder

# Compile all files
javac *.java

# Go back to project root to run
cd /path/to/project
java creational.builder.BuilderPatternDemo
```

---

## Key Principles 📖

### SOLID Principles Applied:

1. **Single Responsibility Principle (SRP)**
   - Builder handles construction logic
   - Product handles business logic

2. **Open/Closed Principle (OCP)**
   - Easy to add new optional fields without breaking existing code

3. **Interface Segregation**
   - Clients only call the methods they need

### Immutability Benefits:
- Thread-safe by default
- No unexpected state changes
- Easier to debug and reason about

---

## Builder vs Factory vs Other Patterns 🤔

| Aspect | Builder | Factory | Prototype |
|--------|---------|---------|-----------|
| **Purpose** | Step-by-step construction | Centralized creation | Clone existing |
| **Complexity** | Many parameters | Type selection | Copy & modify |
| **Flexibility** | High (each step optional) | Medium | Medium |
| **When to Use** | Complex objects | Family of objects | Expensive creation |
| **Example** | Transaction | PaymentMethod | Document template |

---

## Common Pitfalls ⚠️

### 1. **Forgetting Required Fields**
```java
// BAD: All fields optional
new Transaction.Builder()
    .amount(100)  // Oops, forgot transaction ID!
    .build();
```
**Solution:** Put required fields in Builder constructor

### 2. **Not Validating in build()**
```java
// BAD: No validation
public Transaction build() {
    return new Transaction(this);  // What if amount is negative?
}
```
**Solution:** Always validate before returning

### 3. **Mutable Builder State**
```java
// BAD: Reusing builder can cause issues
Builder builder = new Builder(...);
Transaction t1 = builder.note("First").build();
Transaction t2 = builder.note("Second").build();  // t1 now has "Second"?
```
**Solution:** Create new builder for each object

---

## Extending the Pattern 🔧

### Adding a Director (Optional):

For standardized building processes, you can add a Director class:

```java
public class TransactionDirector {
    
    public Transaction createUpiPayment(String sender, String receiver, double amount) {
        return new Transaction.Builder("TXN-" + System.currentTimeMillis(), 
                                        amount, "INR", sender, receiver)
            .paymentMethod("UPI")
            .status("SUCCESS")
            .refundable(false)
            .build();
    }
    
    public Transaction createInternationalPayment(...) {
        // Standard international transaction setup
    }
}
```

---

## Related Patterns 🔗

- **Factory Pattern** - Factory can use Builder internally
- **Singleton Pattern** - Builder can be singleton (rarely)
- **Prototype Pattern** - Alternative for creating similar objects
- **Fluent Interface** - Builder often uses fluent API

---

## Summary 📝

The Builder Pattern is essential for fintech applications where:
- Transactions have many fields
- Some fields are required, others optional
- Validation is critical before object creation
- Code readability matters for maintenance
- Immutability improves thread safety

**Key Takeaways:**
- ✅ Separate construction from representation
- ✅ Use fluent API for readability
- ✅ Put required fields in constructor
- ✅ Validate in `build()` method
- ✅ Make products immutable
- ⚠️ Don't overuse for simple objects

**Remember:** Builder Pattern = Step-by-step construction of complex objects! 🏗️

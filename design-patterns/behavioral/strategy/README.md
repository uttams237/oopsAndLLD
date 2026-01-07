# Strategy Design Pattern 🎯

## Overview
The **Strategy Pattern** is a behavioral design pattern that defines a family of algorithms, encapsulates each one, and makes them interchangeable. Strategy lets the algorithm vary independently from clients that use it.

## Real-World Analogy 🌍
Think of different payment methods at a checkout counter:
- You can pay with **UPI** (fast, instant)
- You can pay with **Card** (secure, widely accepted)
- You can pay with **Net Banking** (direct from bank)

The cashier doesn't care HOW you pay, they just need you to pay. The payment method (strategy) can be chosen at runtime!

---

## Problem It Solves ❌

### Without Strategy Pattern:
```java
public void processPayment(String paymentType, double amount) {
    if (paymentType.equals("UPI")) {
        // UPI payment logic
    } else if (paymentType.equals("CARD")) {
        // Card payment logic
    } else if (paymentType.equals("NETBANKING")) {
        // Net banking logic
    }
    // Adding new payment method = modifying this code!
}
```

**Problems:**
- ❌ Lots of if-else statements
- ❌ Hard to maintain
- ❌ Violates Open/Closed Principle
- ❌ Adding new payment method requires modifying existing code

### With Strategy Pattern:
```java
PaymentContext context = new PaymentContext(new UpiPayment("user@paytm"));
context.executePayment(1500.00);

// Change strategy at runtime
context.setPaymentStrategy(new CardPayment(...));
context.executePayment(2500.00);
```

**Benefits:**
- ✅ Clean, maintainable code
- ✅ Easy to add new payment methods
- ✅ No conditional statements
- ✅ Follows SOLID principles

---

## Structure 🏗️

```
┌─────────────────┐
│ PaymentContext  │ ──────► Uses ──────► ┌──────────────────┐
└─────────────────┘                      │ PaymentStrategy  │ (Interface)
                                         └──────────────────┘
                                                  △
                                                  │ Implements
                        ┌─────────────────────────┼─────────────────────────┐
                        │                         │                         │
                ┌───────────────┐         ┌──────────────┐         ┌──────────────────┐
                │  UpiPayment   │         │ CardPayment  │         │ NetBankingPayment│
                └───────────────┘         └──────────────┘         └──────────────────┘
```

### Components:

1. **Strategy Interface** ([PaymentStrategy.java](file:///Users/uttamsharma/Desktop/java/oops/design-patterns/behavioral/strategy/PaymentStrategy.java))
   - Defines the contract for all payment methods
   - `pay(double amount)` method

2. **Concrete Strategies**
   - [UpiPayment.java](file:///Users/uttamsharma/Desktop/java/oops/design-patterns/behavioral/strategy/UpiPayment.java) - UPI payment implementation
   - [CardPayment.java](file:///Users/uttamsharma/Desktop/java/oops/design-patterns/behavioral/strategy/CardPayment.java) - Card payment implementation
   - [NetBankingPayment.java](file:///Users/uttamsharma/Desktop/java/oops/design-patterns/behavioral/strategy/NetBankingPayment.java) - Net banking implementation

3. **Context** ([PaymentContext.java](file:///Users/uttamsharma/Desktop/java/oops/design-patterns/behavioral/strategy/PaymentContext.java))
   - Maintains reference to a Strategy object
   - Delegates payment processing to the strategy
   - Allows changing strategy at runtime

---

## Fintech Use Cases 💰

### 1. **Payment Processing** (Our Example)
Different payment methods: UPI, Card, Net Banking, Wallet, etc.

### 2. **Interest Calculation**
Different interest strategies: Simple Interest, Compound Interest, Fixed Deposit, Recurring Deposit

### 3. **Transaction Fees**
Different fee calculation strategies: Percentage-based, Flat fee, Tiered pricing

### 4. **Fraud Detection**
Different fraud detection algorithms: Rule-based, ML-based, Behavioral analysis

### 5. **Currency Conversion**
Different conversion strategies: Real-time rates, Fixed rates, Premium rates

---

## When to Use Strategy Pattern? 🤔

✅ **Use when:**
- You have multiple algorithms/behaviors for the same task
- You want to avoid conditional statements (if-else, switch)
- You need to change behavior at runtime
- You want to isolate algorithm implementation from the code that uses it

❌ **Don't use when:**
- You only have one algorithm
- The algorithm never changes
- The overhead of creating multiple classes isn't worth it

---

## How to Run 🚀

### From the `oops` directory (Recommended):

```bash
# Navigate to the oops directory
cd /Users/uttamsharma/Desktop/java/oops

# Compile with package structure
javac -d . design-patterns/behavioral/strategy/*.java

# Run the demo
java behavioral.strategy.StrategyPatternDemo
```

### From the `strategy` directory:

```bash
# Navigate to the strategy pattern directory
cd /Users/uttamsharma/Desktop/java/oops/design-patterns/behavioral/strategy

# Compile all files
javac *.java

# Go back to oops directory to run
cd /Users/uttamsharma/Desktop/java/oops
java behavioral.strategy.StrategyPatternDemo
```

---

## Expected Output 📺

```
═══════════════════════════════════════════════════════
     STRATEGY PATTERN - FINTECH PAYMENT SYSTEM
═══════════════════════════════════════════════════════

📱 SCENARIO 1: Customer chooses UPI Payment
─────────────────────────────────────────────────────

💳 Processing payment of ₹1500.0 using UPI...

╔════════════════════════════════════════╗
║        UPI PAYMENT PROCESSING          ║
╠════════════════════════════════════════╣
║ UPI ID: customer@paytm
║ Amount: ₹1500.0
║ Status: Payment Successful ✓
╚════════════════════════════════════════╝

... (more scenarios)
```

---

## Key Principles 📖

### SOLID Principles Applied:

1. **Single Responsibility Principle (SRP)**
   - Each payment class has ONE responsibility: process its specific payment type

2. **Open/Closed Principle (OCP)**
   - Open for extension: Add new payment methods by creating new classes
   - Closed for modification: No need to modify existing code

3. **Liskov Substitution Principle (LSP)**
   - Any PaymentStrategy can be substituted without breaking the code

4. **Interface Segregation Principle (ISP)**
   - PaymentStrategy interface is focused and minimal

5. **Dependency Inversion Principle (DIP)**
   - PaymentContext depends on abstraction (PaymentStrategy), not concrete classes

---

## Extending the Pattern 🔧

### Adding a New Payment Method (e.g., Wallet):

```java
public class WalletPayment implements PaymentStrategy {
    private String walletId;
    
    public WalletPayment(String walletId) {
        this.walletId = walletId;
    }
    
    @Override
    public boolean pay(double amount) {
        System.out.println("Processing wallet payment...");
        // Wallet payment logic
        return true;
    }
    
    @Override
    public String getPaymentMethodName() {
        return "Wallet";
    }
}
```

**That's it!** No need to modify any existing code. Just create a new class and use it:

```java
paymentContext.setPaymentStrategy(new WalletPayment("wallet123"));
paymentContext.executePayment(1000.00);
```

---

## Common Pitfalls ⚠️

1. **Creating too many strategies** - Only create strategies when behavior truly differs
2. **Not using interfaces** - Always use an interface/abstract class for the strategy
3. **Tight coupling** - Context should only know about the interface, not concrete classes
4. **Forgetting runtime flexibility** - The power of Strategy is changing behavior at runtime!

---

## Related Patterns 🔗

- **State Pattern** - Similar structure, but State represents different states of an object
- **Factory Pattern** - Can be used to create Strategy objects
- **Template Method** - Alternative to Strategy when algorithm structure is fixed

---

## Summary 📝

The Strategy Pattern is perfect for fintech applications where:
- Multiple payment methods exist
- New payment methods are frequently added
- Payment method selection happens at runtime
- Clean, maintainable code is essential

**Remember:** Strategy Pattern = Interchangeable algorithms/behaviors at runtime! 🎯

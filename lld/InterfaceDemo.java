package lld;

/**
 * =====================================================
 * 5. INTERFACE (Advanced OOP Concept)
 * =====================================================
 * 
 * DEFINITION:
 * An interface is a blueprint/contract that specifies what a class must do,
 * but not how it does it. It achieves 100% abstraction.
 * 
 * KEY CHARACTERISTICS:
 * - All methods are implicitly public and abstract (before Java 8).
 * - Java 8+ allows default and static methods in interfaces.
 * - All fields are implicitly public, static, and final (constants).
 * - A class can implement MULTIPLE interfaces (solves multiple inheritance).
 * - Use 'implements' keyword to implement an interface.
 * 
 * INTERFACE vs ABSTRACT CLASS:
 * | Feature              | Interface                  | Abstract Class         |
 * |----------------------|----------------------------|------------------------|
 * | Methods              | Abstract (+ default/static)| Abstract + Concrete    |
 * | Fields               | public static final only   | Any type               |
 * | Multiple Inheritance | Yes (implements A, B, C)   | No (extends one only)  |
 * | Constructor          | No                         | Yes                    |
 * | Use Case            | Define a contract          | Share code + contract  |
 * 
 * LLD RELEVANCE:
 * - Strategy Pattern: Define strategy interface, multiple implementations.
 * - Dependency Injection: Depend on interface, not concrete class.
 * - Loose Coupling: Code to interface, not implementation.
 * - SOLID Principles: Interface Segregation, Dependency Inversion.
 * =====================================================
 */

// =========================================
// INTERFACE 1: Payment Contract
// Any payment method must implement this.
// =========================================
interface PaymentMethod {
    // All methods in interface are public abstract by default
    void pay(double amount);
    
    boolean validatePayment();
    
    // Java 8+: Default method with implementation
    default void printReceipt(double amount) {
        System.out.println("[Receipt] Amount paid: $" + amount);
    }
}

// =========================================
// INTERFACE 2: Logging Contract
// Demonstrates multiple interface implementation.
// =========================================
interface Loggable {
    void logTransaction(String message);
    
    // Java 8+: Static method in interface
    static String getLogPrefix() {
        return "[LOG]";
    }
}

// =========================================
// INTERFACE 3: Refundable Contract
// Not all payment methods support refunds.
// =========================================
interface Refundable {
    void refund(double amount);
}

// =========================================
// CONCRETE IMPLEMENTATION 1
// Implements single interface.
// =========================================
class CreditCardPayment implements PaymentMethod {
    
    private String cardNumber;
    
    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    @Override
    public void pay(double amount) {
        System.out.println("Charging $" + amount + " to Credit Card ending in " + 
                           cardNumber.substring(cardNumber.length() - 4));
    }
    
    @Override
    public boolean validatePayment() {
        System.out.println("Validating credit card...");
        return cardNumber.length() == 16;
    }
}

// =========================================
// CONCRETE IMPLEMENTATION 2
// Implements single interface differently.
// =========================================
class PayPalPayment implements PaymentMethod {
    
    private String email;
    
    public PayPalPayment(String email) {
        this.email = email;
    }
    
    @Override
    public void pay(double amount) {
        System.out.println("Sending $" + amount + " via PayPal to " + email);
    }
    
    @Override
    public boolean validatePayment() {
        System.out.println("Validating PayPal account...");
        return email.contains("@");
    }
}

// =========================================
// CONCRETE IMPLEMENTATION 3
// MULTIPLE INTERFACE IMPLEMENTATION
// Implements PaymentMethod, Loggable, and Refundable.
// =========================================
class SecurePaymentProcessor implements PaymentMethod, Loggable, Refundable {
    
    private String processorId;
    
    public SecurePaymentProcessor(String processorId) {
        this.processorId = processorId;
    }
    
    @Override
    public void pay(double amount) {
        System.out.println("SecureProcessor [" + processorId + "]: Processing $" + amount);
        logTransaction("Payment of $" + amount + " initiated.");
    }
    
    @Override
    public boolean validatePayment() {
        logTransaction("Validating payment...");
        return true;
    }

    @Override
    public void logTransaction(String message) {
        // Using static method from interface
        System.out.println(Loggable.getLogPrefix() + " " + message);
    }
    
    @Override
    public void refund(double amount) {
        System.out.println("SecureProcessor: Refunding $" + amount);
        logTransaction("Refund of $" + amount + " processed.");
    }
}

public class InterfaceDemo {
    public static void main(String[] args) {
        System.out.println("=== Interface Demo ===\n");

        // =========================================
        // LOOSE COUPLING DEMONSTRATION
        // Variable type is INTERFACE, not concrete class.
        // We can swap implementations easily!
        // =========================================
        System.out.println("--- Loose Coupling (Interface Type) ---\n");
        
        PaymentMethod payment; // Interface type
        
        // Using CreditCard implementation
        payment = new CreditCardPayment("1234567890123456");
        payment.validatePayment();
        payment.pay(100);
        payment.printReceipt(100); // Default method from interface
        
        System.out.println();
        
        // Swap to PayPal implementation - no code change needed!
        payment = new PayPalPayment("user@example.com");
        payment.validatePayment();
        payment.pay(250);
        payment.printReceipt(250);

        System.out.println("\n--- Multiple Interface Implementation ---\n");
        
        // =========================================
        // MULTIPLE INHERITANCE VIA INTERFACES
        // One class implements multiple contracts.
        // =========================================
        SecurePaymentProcessor secureProcessor = new SecurePaymentProcessor("SP-001");
        secureProcessor.validatePayment();
        secureProcessor.pay(500);
        secureProcessor.printReceipt(500);
        secureProcessor.refund(50);

        System.out.println("\n--- Polymorphism with Interfaces ---\n");
        
        // =========================================
        // ARRAY OF INTERFACE TYPE
        // Different implementations, same interface.
        // =========================================
        PaymentMethod[] payments = {
            new CreditCardPayment("4111111111111111"),
            new PayPalPayment("john@email.com"),
            new SecurePaymentProcessor("SP-002")
        };
        
        double[] amounts = {100, 200, 300};
        
        for (int i = 0; i < payments.length; i++) {
            payments[i].pay(amounts[i]);
        }

        System.out.println("\n=== KEY TAKEAWAY ===");
        System.out.println("Interface = Contract defining WHAT to do, not HOW.");
        System.out.println("Use 'implements' to implement. A class can implement multiple interfaces.");
        System.out.println("Code to interface, not implementation = Loose Coupling.");
    }
}

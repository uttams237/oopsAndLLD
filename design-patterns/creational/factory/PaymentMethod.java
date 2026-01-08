package creational.factory;

/**
 * FACTORY PATTERN - Product Interface
 * 
 * This is the Product interface that defines the contract for all payment methods.
 * All concrete payment products will implement this interface.
 * 
 * WHY USE FACTORY PATTERN?
 * - Centralizes object creation logic
 * - Hides complex creation logic from clients
 * - Makes code more maintainable and testable
 * - Follows Open/Closed Principle (easy to add new products)
 * - Reduces coupling between client code and concrete classes
 * 
 * REAL-WORLD ANALOGY:
 * Think of a car factory - you don't build the car yourself, you just tell the
 * factory "I want a sedan" and it gives you a ready-made sedan. The factory
 * handles all the complex assembly work.
 */
public interface PaymentMethod {
    
    /**
     * Process a payment using this payment method
     * 
     * @param amount The amount to be paid
     * @return true if payment is successful, false otherwise
     */
    boolean processPayment(double amount);
    
    /**
     * Validate the payment method details
     * 
     * @return true if validation passes, false otherwise
     */
    boolean validate();
    
    /**
     * Get the payment method type
     * 
     * @return Payment method type (e.g., "UPI", "CARD", "NET_BANKING")
     */
    String getPaymentType();
    
    /**
     * Get transaction fee for this payment method
     * 
     * @param amount Transaction amount
     * @return Fee amount
     */
    double getTransactionFee(double amount);
}

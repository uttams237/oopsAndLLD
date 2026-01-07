package behavioral.strategy;

/**
 * STRATEGY PATTERN - Interface
 * 
 * This is the Strategy interface that defines the contract for all payment methods.
 * Each concrete strategy (UPI, Card, NetBanking) will implement this interface.
 * 
 * WHY USE STRATEGY PATTERN?
 * - Allows selecting algorithm/behavior at runtime
 * - Eliminates conditional statements (if-else, switch-case)
 * - Makes it easy to add new payment methods without modifying existing code
 * - Follows Open/Closed Principle (open for extension, closed for modification)
 */
public interface PaymentStrategy {
    
    /**
     * Process payment using the specific payment method
     * 
     * @param amount The amount to be paid
     * @return true if payment is successful, false otherwise
     */
    boolean pay(double amount);
    
    /**
     * Get the name of the payment method
     * 
     * @return Payment method name (e.g., "UPI", "Credit Card")
     */
    String getPaymentMethodName();
}

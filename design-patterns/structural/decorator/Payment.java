package structural.decorator;

/**
 * Payment - Component Interface
 * 
 * This is the base interface for all payment components and decorators.
 * It defines the contract that both concrete components (BasePayment) and
 * decorators must follow.
 * 
 * In the Decorator pattern:
 * - Component: Defines the interface for objects that can have responsibilities added
 * - Both ConcreteComponent and Decorators implement this interface
 * 
 * This allows decorators to be used interchangeably with the concrete component.
 */
public interface Payment {
    
    /**
     * Process the payment and return the final amount to be charged.
     * This includes the base amount plus any additional charges from decorators.
     * 
     * @return The total amount after all decorations (fees, taxes, discounts)
     */
    double processPayment();
    
    /**
     * Get a detailed description of the payment including all applied decorations.
     * Each decorator adds its own description to the chain.
     * 
     * @return Detailed payment description
     */
    String getDescription();
    
    /**
     * Get the current amount (may include decorator additions).
     * This is used by decorators to calculate their additional charges.
     * 
     * @return Current payment amount
     */
    double getAmount();
}

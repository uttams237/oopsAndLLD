package structural.decorator;

/**
 * BasePayment - Concrete Component
 * 
 * This is the core payment object that can be decorated with additional functionality.
 * It represents a basic payment transaction without any fees, taxes, or discounts.
 * 
 * In the Decorator pattern:
 * - ConcreteComponent: The object to which additional responsibilities can be attached
 * - Provides the base implementation of the component interface
 * 
 * Decorators will wrap this object to add fees, taxes, etc.
 */
public class BasePayment implements Payment {
    
    private double baseAmount;
    private String paymentType;
    
    /**
     * Create a base payment with specified amount and type.
     * 
     * @param baseAmount The initial payment amount
     * @param paymentType Type of payment (UPI, Card, etc.)
     */
    public BasePayment(double baseAmount, String paymentType) {
        this.baseAmount = baseAmount;
        this.paymentType = paymentType;
    }
    
    /**
     * Process the base payment without any additional charges.
     * 
     * @return The base amount
     */
    @Override
    public double processPayment() {
        return baseAmount;
    }
    
    /**
     * Get the basic payment description.
     * 
     * @return Description of the base payment
     */
    @Override
    public String getDescription() {
        return "Base Payment (" + paymentType + ")";
    }
    
    /**
     * Get the base payment amount.
     * 
     * @return Base amount
     */
    @Override
    public double getAmount() {
        return baseAmount;
    }
}

package structural.decorator;

/**
 * PaymentDecorator - Abstract Decorator
 * 
 * This is the base class for all concrete decorators. It implements the Payment
 * interface and contains a reference to a Payment object (the component being decorated).
 * 
 * In the Decorator pattern:
 * - Decorator: Maintains a reference to a Component object and conforms to Component interface
 * - Delegates operations to the wrapped component
 * - Can add behavior before/after delegating
 * 
 * All concrete decorators (GSTDecorator, ProcessingFeeDecorator, etc.) extend this class.
 */
public abstract class PaymentDecorator implements Payment {
    
    // The Payment object being decorated (wrapped)
    protected Payment wrappedPayment;
    
    /**
     * Create a decorator wrapping the given payment.
     * 
     * @param payment The payment object to decorate
     */
    public PaymentDecorator(Payment payment) {
        this.wrappedPayment = payment;
    }
    
    /**
     * Default implementation delegates to the wrapped payment.
     * Concrete decorators can override to add their own logic.
     * 
     * @return The amount from the wrapped payment
     */
    @Override
    public double processPayment() {
        return wrappedPayment.processPayment();
    }
    
    /**
     * Default implementation delegates to the wrapped payment.
     * Concrete decorators typically override to add their description.
     * 
     * @return The description from the wrapped payment
     */
    @Override
    public String getDescription() {
        return wrappedPayment.getDescription();
    }
    
    /**
     * Default implementation delegates to the wrapped payment.
     * Used by decorators to get the current amount before adding their charges.
     * 
     * @return The current amount from the wrapped payment
     */
    @Override
    public double getAmount() {
        return wrappedPayment.getAmount();
    }
}

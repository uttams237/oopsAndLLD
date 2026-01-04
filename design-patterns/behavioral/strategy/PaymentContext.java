
/**
 * CONTEXT CLASS
 * 
 * This class maintains a reference to a Strategy object and delegates
 * the payment processing to the strategy.
 * 
 * KEY POINTS:
 * - The Context doesn't know which concrete strategy is being used
 * - It works with all strategies through the PaymentStrategy interface
 * - Strategy can be changed at runtime using setPaymentStrategy()
 * - This is the class that clients will interact with
 */
public class PaymentContext {
    
    // Reference to the current payment strategy
    private PaymentStrategy paymentStrategy;
    
    /**
     * Constructor to initialize with a payment strategy
     * 
     * @param paymentStrategy The payment strategy to use
     */
    public PaymentContext(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
    
    /**
     * Set or change the payment strategy at runtime
     * This is the KEY feature of Strategy Pattern - runtime flexibility
     * 
     * @param paymentStrategy The new payment strategy to use
     */
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
        System.out.println("\n✓ Payment method changed to: " + paymentStrategy.getPaymentMethodName());
    }
    
    /**
     * Execute payment using the current strategy
     * The Context delegates the work to the Strategy object
     * 
     * @param amount The amount to be paid
     * @return true if payment is successful
     */
    public boolean executePayment(double amount) {
        if (paymentStrategy == null) {
            System.out.println("❌ Error: No payment method selected!");
            return false;
        }
        
        System.out.println("\n💳 Processing payment of ₹" + amount + " using " + 
                         paymentStrategy.getPaymentMethodName() + "...\n");
        
        return paymentStrategy.pay(amount);
    }
    
    /**
     * Get the current payment method name
     * 
     * @return Current payment method name
     */
    public String getCurrentPaymentMethod() {
        return paymentStrategy != null ? paymentStrategy.getPaymentMethodName() : "None";
    }
}

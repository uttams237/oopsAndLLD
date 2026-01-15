package structural.decorator;

/**
 * GSTDecorator - Concrete Decorator
 * 
 * Adds GST (Goods and Services Tax) to the payment.
 * In India, GST is 18% on most digital payment services.
 * 
 * This decorator:
 * - Wraps an existing Payment object
 * - Calculates 18% GST on the current amount
 * - Adds the GST to the total
 * - Updates the description to show GST applied
 */
public class GSTDecorator extends PaymentDecorator {
    
    private static final double GST_RATE = 0.18; // 18% GST
    
    /**
     * Create a GST decorator wrapping the given payment.
     * 
     * @param payment The payment to add GST to
     */
    public GSTDecorator(Payment payment) {
        super(payment);
    }
    
    /**
     * Process payment with GST added.
     * Formula: BaseAmount + (BaseAmount * 18%)
     * 
     * @return Total amount including GST
     */
    @Override
    public double processPayment() {
        double baseAmount = wrappedPayment.processPayment();
        double gstAmount = baseAmount * GST_RATE;
        return baseAmount + gstAmount;
    }
    
    /**
     * Add GST information to the description.
     * 
     * @return Description with GST details
     */
    @Override
    public String getDescription() {
        double gstAmount = wrappedPayment.processPayment() * GST_RATE;
        return wrappedPayment.getDescription() + 
               " + GST @18% (₹" + String.format("%.2f", gstAmount) + ")";
    }
    
    /**
     * Get the current amount including GST.
     * This allows other decorators to calculate their charges on the GST-inclusive amount.
     * 
     * @return Amount with GST
     */
    @Override
    public double getAmount() {
        return processPayment();
    }
}

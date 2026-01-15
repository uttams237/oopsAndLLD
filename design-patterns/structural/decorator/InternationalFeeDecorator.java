package structural.decorator;

/**
 * InternationalFeeDecorator - Concrete Decorator
 * 
 * Adds international transaction fee for cross-border payments.
 * This typically includes currency conversion and international processing charges.
 * 
 * This decorator:
 * - Adds 2% international transaction fee
 * - Includes currency conversion markup
 * - Updates the description
 */
public class InternationalFeeDecorator extends PaymentDecorator {
    
    private static final double INTERNATIONAL_FEE_RATE = 0.02; // 2%
    private String targetCurrency;
    
    /**
     * Create an international fee decorator.
     * 
     * @param payment The payment to add international fee to
     * @param targetCurrency The target currency (e.g., "USD", "EUR")
     */
    public InternationalFeeDecorator(Payment payment, String targetCurrency) {
        super(payment);
        this.targetCurrency = targetCurrency;
    }
    
    /**
     * Process payment with international fee added.
     * Formula: BaseAmount + (BaseAmount * 2%)
     * 
     * @return Total amount including international fee
     */
    @Override
    public double processPayment() {
        double currentAmount = wrappedPayment.processPayment();
        double internationalFee = currentAmount * INTERNATIONAL_FEE_RATE;
        return currentAmount + internationalFee;
    }
    
    /**
     * Add international fee information to the description.
     * 
     * @return Description with international fee details
     */
    @Override
    public String getDescription() {
        double currentAmount = wrappedPayment.processPayment();
        double internationalFee = currentAmount * INTERNATIONAL_FEE_RATE;
        return wrappedPayment.getDescription() + 
               " + International Fee @2% to " + targetCurrency + 
               " (₹" + String.format("%.2f", internationalFee) + ")";
    }
    
    /**
     * Get the current amount including international fee.
     * 
     * @return Amount with international fee
     */
    @Override
    public double getAmount() {
        return processPayment();
    }
}

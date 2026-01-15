package structural.decorator;

/**
 * ProcessingFeeDecorator - Concrete Decorator
 * 
 * Adds a processing fee to the payment.
 * In fintech, payment gateways typically charge a small percentage with a maximum cap.
 * 
 * This decorator:
 * - Calculates 0.5% of the amount as processing fee
 * - Caps the fee at ₹500
 * - Adds the fee to the total
 * - Updates the description
 */
public class ProcessingFeeDecorator extends PaymentDecorator {
    
    private static final double FEE_PERCENTAGE = 0.005; // 0.5%
    private static final double MAX_FEE = 500.0; // Maximum fee cap
    
    /**
     * Create a processing fee decorator wrapping the given payment.
     * 
     * @param payment The payment to add processing fee to
     */
    public ProcessingFeeDecorator(Payment payment) {
        super(payment);
    }
    
    /**
     * Calculate the processing fee based on current amount.
     * 
     * @param amount The amount to calculate fee on
     * @return Processing fee (capped at ₹500)
     */
    private double calculateFee(double amount) {
        double fee = amount * FEE_PERCENTAGE;
        return Math.min(fee, MAX_FEE);
    }
    
    /**
     * Process payment with processing fee added.
     * 
     * @return Total amount including processing fee
     */
    @Override
    public double processPayment() {
        double currentAmount = wrappedPayment.processPayment();
        double fee = calculateFee(currentAmount);
        return currentAmount + fee;
    }
    
    /**
     * Add processing fee information to the description.
     * 
     * @return Description with processing fee details
     */
    @Override
    public String getDescription() {
        double currentAmount = wrappedPayment.processPayment();
        double fee = calculateFee(currentAmount);
        return wrappedPayment.getDescription() + 
               " + Processing Fee (₹" + String.format("%.2f", fee) + ")";
    }
    
    /**
     * Get the current amount including processing fee.
     * 
     * @return Amount with processing fee
     */
    @Override
    public double getAmount() {
        return processPayment();
    }
}

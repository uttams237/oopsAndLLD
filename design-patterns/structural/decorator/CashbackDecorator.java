package structural.decorator;

/**
 * CashbackDecorator - Concrete Decorator
 * 
 * Applies cashback/discount to the payment.
 * In fintech, promotional offers often provide cashback to encourage transactions.
 * 
 * This decorator:
 * - Subtracts a flat cashback amount from the total
 * - Updates the description to show cashback applied
 * - Can be combined with other decorators
 * 
 * Note: Unlike other decorators that add to the amount, this one reduces it.
 */
public class CashbackDecorator extends PaymentDecorator {
    
    private double cashbackAmount;
    
    /**
     * Create a cashback decorator with the specified cashback amount.
     * 
     * @param payment The payment to apply cashback to
     * @param cashbackAmount The flat cashback amount to deduct
     */
    public CashbackDecorator(Payment payment, double cashbackAmount) {
        super(payment);
        this.cashbackAmount = cashbackAmount;
    }
    
    /**
     * Process payment with cashback deducted.
     * Ensures final amount doesn't go below 0.
     * 
     * @return Total amount after cashback
     */
    @Override
    public double processPayment() {
        double currentAmount = wrappedPayment.processPayment();
        double finalAmount = currentAmount - cashbackAmount;
        
        // Ensure amount doesn't go negative
        return Math.max(finalAmount, 0);
    }
    
    /**
     * Add cashback information to the description.
     * 
     * @return Description with cashback details
     */
    @Override
    public String getDescription() {
        return wrappedPayment.getDescription() + 
               " - Cashback (₹" + String.format("%.2f", cashbackAmount) + ")";
    }
    
    /**
     * Get the current amount after cashback.
     * 
     * @return Amount after cashback
     */
    @Override
    public double getAmount() {
        return processPayment();
    }
}

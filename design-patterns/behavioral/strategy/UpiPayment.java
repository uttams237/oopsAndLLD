package behavioral.strategy;

/**
 * CONCRETE STRATEGY - UPI Payment
 * 
 * This class implements the PaymentStrategy interface for UPI payments.
 * It encapsulates the UPI payment algorithm/behavior.
 */
public class UpiPayment implements PaymentStrategy {
    
    private String upiId;  // UPI ID like user@paytm, user@gpay
    
    /**
     * Constructor to initialize UPI payment with UPI ID
     * 
     * @param upiId The UPI ID for payment (e.g., "user@paytm")
     */
    public UpiPayment(String upiId) {
        this.upiId = upiId;
    }
    
    /**
     * Process payment using UPI
     * 
     * @param amount The amount to be paid
     * @return true if payment is successful
     */
    @Override
    public boolean pay(double amount) {
        // In real-world, this would connect to UPI gateway
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║        UPI PAYMENT PROCESSING          ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ UPI ID: " + upiId);
        System.out.println("║ Amount: ₹" + amount);
        System.out.println("║ Status: Payment Successful ✓");
        System.out.println("╚════════════════════════════════════════╝");
        return true;
    }
    
    /**
     * Get the payment method name
     * 
     * @return "UPI"
     */
    @Override
    public String getPaymentMethodName() {
        return "UPI";
    }
}

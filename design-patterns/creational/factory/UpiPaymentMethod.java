package creational.factory;

/**
 * CONCRETE PRODUCT - UPI Payment
 * 
 * This class implements the PaymentMethod interface for UPI payments.
 * The Factory will create instances of this class when UPI payment is requested.
 */
public class UpiPaymentMethod implements PaymentMethod {
    
    private String upiId;
    
    /**
     * Constructor to initialize UPI payment method
     * 
     * @param upiId The UPI ID (e.g., "user@paytm", "user@gpay")
     */
    public UpiPaymentMethod(String upiId) {
        this.upiId = upiId;
    }
    
    /**
     * Validate UPI ID format
     * 
     * @return true if UPI ID is valid
     */
    @Override
    public boolean validate() {
        // Basic validation: UPI ID should contain @ symbol
        if (upiId == null || !upiId.contains("@")) {
            System.out.println("❌ Invalid UPI ID format");
            return false;
        }
        System.out.println("✓ UPI ID validated: " + upiId);
        return true;
    }
    
    /**
     * Process UPI payment
     * 
     * @param amount Amount to be paid
     * @return true if payment successful
     */
    @Override
    public boolean processPayment(double amount) {
        if (!validate()) {
            return false;
        }
        
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║        UPI PAYMENT PROCESSING          ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ UPI ID: " + upiId);
        System.out.println("║ Amount: ₹" + amount);
        System.out.println("║ Fee: ₹" + getTransactionFee(amount));
        System.out.println("║ Total: ₹" + (amount + getTransactionFee(amount)));
        System.out.println("║ Status: Payment Successful ✓");
        System.out.println("╚════════════════════════════════════════╝");
        
        return true;
    }
    
    /**
     * Get payment type
     * 
     * @return "UPI"
     */
    @Override
    public String getPaymentType() {
        return "UPI";
    }
    
    /**
     * UPI has zero transaction fee
     * 
     * @param amount Transaction amount
     * @return 0.0 (no fee for UPI)
     */
    @Override
    public double getTransactionFee(double amount) {
        return 0.0; // UPI is free!
    }
}

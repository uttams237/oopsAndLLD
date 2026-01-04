
/**
 * CONCRETE STRATEGY - Net Banking Payment
 * 
 * This class implements the PaymentStrategy interface for Net Banking payments.
 * It encapsulates the net banking payment algorithm/behavior.
 */
public class NetBankingPayment implements PaymentStrategy {
    
    private String bankName;    // Name of the bank (e.g., "HDFC", "SBI")
    private String accountNumber; // Bank account number
    private String ifscCode;    // IFSC code of the bank
    
    /**
     * Constructor to initialize net banking payment details
     * 
     * @param bankName Name of the bank
     * @param accountNumber Bank account number
     * @param ifscCode IFSC code
     */
    public NetBankingPayment(String bankName, String accountNumber, String ifscCode) {
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.ifscCode = ifscCode;
    }
    
    /**
     * Process payment using Net Banking
     * 
     * @param amount The amount to be paid
     * @return true if payment is successful
     */
    @Override
    public boolean pay(double amount) {
        // In real-world, this would redirect to bank's portal for authentication
        
        // Mask account number for security (show only last 4 digits)
        String maskedAccount = "XXXX" + accountNumber.substring(accountNumber.length() - 4);
        
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║     NET BANKING PAYMENT PROCESSING     ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ Bank: " + bankName);
        System.out.println("║ Account: " + maskedAccount);
        System.out.println("║ IFSC: " + ifscCode);
        System.out.println("║ Amount: ₹" + amount);
        System.out.println("║ Status: Redirecting to bank portal...");
        System.out.println("║ Status: Payment Successful ✓");
        System.out.println("╚════════════════════════════════════════╝");
        return true;
    }
    
    /**
     * Get the payment method name
     * 
     * @return "Net Banking"
     */
    @Override
    public String getPaymentMethodName() {
        return "Net Banking";
    }
}

package creational.factory;

/**
 * CONCRETE PRODUCT - Net Banking Payment
 * 
 * This class implements the PaymentMethod interface for Net Banking payments.
 * The Factory will create instances of this class when Net Banking is requested.
 */
public class NetBankingPaymentMethod implements PaymentMethod {
    
    private String bankName;
    private String accountNumber;
    private String ifscCode;
    
    /**
     * Constructor to initialize Net Banking payment method
     * 
     * @param bankName Name of the bank
     * @param accountNumber Account number
     * @param ifscCode IFSC code
     */
    public NetBankingPaymentMethod(String bankName, String accountNumber, String ifscCode) {
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.ifscCode = ifscCode;
    }
    
    /**
     * Validate net banking details
     * 
     * @return true if details are valid
     */
    @Override
    public boolean validate() {
        if (bankName == null || bankName.isEmpty()) {
            System.out.println("❌ Invalid bank name");
            return false;
        }
        if (ifscCode == null || ifscCode.length() != 11) {
            System.out.println("❌ Invalid IFSC code");
            return false;
        }
        System.out.println("✓ Net Banking details validated");
        return true;
    }
    
    /**
     * Process Net Banking payment
     * 
     * @param amount Amount to be paid
     * @return true if payment successful
     */
    @Override
    public boolean processPayment(double amount) {
        if (!validate()) {
            return false;
        }
        
        // Mask account number for security
        String maskedAccount = "XXXX" + accountNumber.substring(accountNumber.length() - 4);
        
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║     NET BANKING PAYMENT PROCESSING     ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ Bank: " + bankName);
        System.out.println("║ Account: " + maskedAccount);
        System.out.println("║ IFSC: " + ifscCode);
        System.out.println("║ Amount: ₹" + amount);
        System.out.println("║ Fee: ₹" + getTransactionFee(amount));
        System.out.println("║ Total: ₹" + (amount + getTransactionFee(amount)));
        System.out.println("║ Status: Redirecting to bank portal...");
        System.out.println("║ Status: Payment Successful ✓");
        System.out.println("╚════════════════════════════════════════╝");
        
        return true;
    }
    
    /**
     * Get payment type
     * 
     * @return "NET_BANKING"
     */
    @Override
    public String getPaymentType() {
        return "NET_BANKING";
    }
    
    /**
     * Net Banking has 1% transaction fee
     * 
     * @param amount Transaction amount
     * @return 1% of amount
     */
    @Override
    public double getTransactionFee(double amount) {
        return amount * 0.01; // 1% fee for net banking
    }
}

package creational.factory;

/**
 * CONCRETE PRODUCT - Wallet Payment
 * 
 * This class implements the PaymentMethod interface for Digital Wallet payments.
 * The Factory will create instances of this class when Wallet payment is requested.
 */
public class WalletPaymentMethod implements PaymentMethod {
    
    private String walletProvider;
    private String phoneNumber;
    private double walletBalance;
    
    /**
     * Constructor to initialize Wallet payment method
     * 
     * @param walletProvider Wallet provider (e.g., "Paytm", "PhonePe", "Amazon Pay")
     * @param phoneNumber Registered phone number
     * @param walletBalance Current wallet balance
     */
    public WalletPaymentMethod(String walletProvider, String phoneNumber, double walletBalance) {
        this.walletProvider = walletProvider;
        this.phoneNumber = phoneNumber;
        this.walletBalance = walletBalance;
    }
    
    /**
     * Validate wallet details
     * 
     * @return true if details are valid
     */
    @Override
    public boolean validate() {
        if (walletProvider == null || walletProvider.isEmpty()) {
            System.out.println("❌ Invalid wallet provider");
            return false;
        }
        if (phoneNumber == null || phoneNumber.length() != 10) {
            System.out.println("❌ Invalid phone number");
            return false;
        }
        System.out.println("✓ Wallet details validated");
        return true;
    }
    
    /**
     * Process Wallet payment
     * 
     * @param amount Amount to be paid
     * @return true if payment successful
     */
    @Override
    public boolean processPayment(double amount) {
        if (!validate()) {
            return false;
        }
        
        double totalAmount = amount + getTransactionFee(amount);
        
        // Check wallet balance
        if (walletBalance < totalAmount) {
            System.out.println("\n❌ Insufficient wallet balance!");
            System.out.println("   Required: ₹" + totalAmount);
            System.out.println("   Available: ₹" + walletBalance);
            return false;
        }
        
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║      WALLET PAYMENT PROCESSING         ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ Provider: " + walletProvider);
        System.out.println("║ Phone: +91-" + phoneNumber);
        System.out.println("║ Amount: ₹" + amount);
        System.out.println("║ Fee: ₹" + getTransactionFee(amount));
        System.out.println("║ Total: ₹" + totalAmount);
        System.out.println("║ Balance Before: ₹" + walletBalance);
        
        walletBalance -= totalAmount;
        
        System.out.println("║ Balance After: ₹" + walletBalance);
        System.out.println("║ Status: Payment Successful ✓");
        System.out.println("╚════════════════════════════════════════╝");
        
        return true;
    }
    
    /**
     * Get payment type
     * 
     * @return "WALLET"
     */
    @Override
    public String getPaymentType() {
        return "WALLET";
    }
    
    /**
     * Wallet has 0.5% transaction fee
     * 
     * @param amount Transaction amount
     * @return 0.5% of amount
     */
    @Override
    public double getTransactionFee(double amount) {
        return amount * 0.005; // 0.5% fee for wallet
    }
}

package structural.adapter;

/**
 * Adapter - Bridges the gap between LegacyBankAPI and PaymentProcessor
 * This class adapts the legacy bank API to work with our modern interface.
 */
public class BankAPIAdapter implements PaymentProcessor {
    private LegacyBankAPI legacyBankAPI;
    private String merchantAccount;
    
    public BankAPIAdapter(LegacyBankAPI legacyBankAPI, String merchantAccount) {
        this.legacyBankAPI = legacyBankAPI;
        this.merchantAccount = merchantAccount;
        System.out.println("🔌 Adapter initialized for merchant account: " + merchantAccount);
    }
    
    @Override
    public boolean processPayment(String accountId, double amount, String currency) {
        System.out.println("\n🔄 ADAPTER: Converting modern payment request to legacy format...");
        
        // Convert currency if needed (legacy system only supports INR)
        if (!currency.equals("INR")) {
            System.out.println("⚠️  ADAPTER: Converting " + currency + " to INR");
            // Simplified conversion logic
            amount = amount * 83.0; // Assuming 1 USD = 83 INR
        }
        
        // Adapt the call: modern signature -> legacy signature
        legacyBankAPI.makeTransaction(accountId, merchantAccount, amount);
        
        return true;
    }
    
    @Override
    public String getTransactionStatus(String transactionId) {
        System.out.println("🔄 ADAPTER: Forwarding status check to legacy system...");
        String legacyStatus = legacyBankAPI.checkTransactionStatus(transactionId);
        
        // Adapt legacy status codes to modern format
        return legacyStatus.equals("COMPLETED") ? "SUCCESS" : legacyStatus;
    }
    
    @Override
    public boolean refundPayment(String transactionId) {
        System.out.println("🔄 ADAPTER: Converting refund request to legacy reversal...");
        return legacyBankAPI.reverseTransaction(transactionId);
    }
    
    /**
     * Additional adapter-specific functionality
     * Exposes legacy-specific features if needed
     */
    public void requestStatement(String accountId) {
        legacyBankAPI.generateStatement(accountId);
    }
}

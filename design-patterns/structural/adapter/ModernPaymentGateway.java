import java.util.UUID;

/**
 * Modern Payment Gateway (for comparison)
 * This is a native implementation of PaymentProcessor interface.
 */
public class ModernPaymentGateway implements PaymentProcessor {
    private String gatewayName;
    
    public ModernPaymentGateway(String gatewayName) {
        this.gatewayName = gatewayName;
    }
    
    @Override
    public boolean processPayment(String accountId, double amount, String currency) {
        String txnId = UUID.randomUUID().toString().substring(0, 8);
        
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║  MODERN PAYMENT GATEWAY                              ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║  Gateway: " + gatewayName);
        System.out.println("║  Account: " + accountId);
        System.out.println("║  Amount: " + currency + " " + String.format("%.2f", amount));
        System.out.println("║  Transaction ID: " + txnId);
        System.out.println("║  Status: PROCESSED ✓");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        
        return true;
    }
    
    @Override
    public String getTransactionStatus(String transactionId) {
        return "SUCCESS";
    }
    
    @Override
    public boolean refundPayment(String transactionId) {
        System.out.println("💰 Modern Gateway: Processing refund for " + transactionId);
        return true;
    }
}

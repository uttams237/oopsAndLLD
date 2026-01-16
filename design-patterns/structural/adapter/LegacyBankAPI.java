/**
 * Adaptee - Legacy Bank API
 * This is an existing class with a different interface that we need to adapt.
 * It uses old method names and different parameter structures.
 */
public class LegacyBankAPI {
    private String bankName;
    
    public LegacyBankAPI(String bankName) {
        this.bankName = bankName;
    }
    
    /**
     * Legacy method to transfer money (different signature than modern interface)
     */
    public void makeTransaction(String fromAccount, String toAccount, double amt) {
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║  LEGACY BANK API TRANSACTION                         ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║  Bank: " + bankName);
        System.out.println("║  From Account: " + fromAccount);
        System.out.println("║  To Account: " + toAccount);
        System.out.println("║  Amount: ₹" + String.format("%.2f", amt));
        System.out.println("║  Status: SUCCESS ✓");
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }
    
    /**
     * Legacy method to check transaction
     */
    public String checkTransactionStatus(String txnId) {
        return "COMPLETED"; // Simplified for demo
    }
    
    /**
     * Legacy method to reverse a transaction
     */
    public boolean reverseTransaction(String txnId) {
        System.out.println("🔄 Legacy Bank: Reversing transaction " + txnId);
        return true;
    }
    
    /**
     * Legacy specific method that doesn't exist in modern interface
     */
    public void generateStatement(String accountId) {
        System.out.println("📄 Generating statement for account: " + accountId);
    }
}

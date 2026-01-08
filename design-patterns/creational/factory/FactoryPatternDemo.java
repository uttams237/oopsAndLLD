package creational.factory;

/**
 * FACTORY PATTERN DEMO - Fintech Payment Creation System
 * 
 * This demo shows how Factory Pattern works in a real-world fintech scenario.
 * 
 * SCENARIO:
 * Instead of creating payment objects directly with 'new' keyword everywhere,
 * we use a Factory to centralize the creation logic. This makes the code
 * cleaner, more maintainable, and easier to extend.
 * 
 * BENEFITS DEMONSTRATED:
 * 1. Client code doesn't know about concrete classes
 * 2. Creation logic is centralized
 * 3. Easy to add new payment methods
 * 4. Reduces coupling between client and products
 */
public class FactoryPatternDemo {
    
    public static void main(String[] args) {
        
        System.out.println("═".repeat(70));
        System.out.println("     FACTORY PATTERN - FINTECH PAYMENT CREATION SYSTEM");
        System.out.println("═".repeat(70));
        
        
        // ========== SCENARIO 1: Creating UPI Payment ==========
        System.out.println("\n📋 SCENARIO 1: Customer chooses UPI Payment");
        System.out.println("─".repeat(70));
        
        // Using factory to create UPI payment
        // Client doesn't need to know about UpiPaymentMethod class!
        PaymentMethod upiPayment = PaymentMethodFactory.createUpiPayment("customer@paytm");
        
        if (upiPayment != null) {
            System.out.println("✓ Payment method created: " + upiPayment.getPaymentType());
            upiPayment.processPayment(1500.00);
        }
        
        
        // ========== SCENARIO 2: Creating Card Payment ==========
        System.out.println("\n\n📋 SCENARIO 2: Customer chooses Card Payment");
        System.out.println("─".repeat(70));
        
        // Using factory to create Card payment
        PaymentMethod cardPayment = PaymentMethodFactory.createCardPayment(
            "1234567890123456",
            "John Doe",
            "12/25",
            "123"
        );
        
        if (cardPayment != null) {
            System.out.println("✓ Payment method created: " + cardPayment.getPaymentType());
            cardPayment.processPayment(2500.00);
        }
        
        
        // ========== SCENARIO 3: Creating Net Banking Payment ==========
        System.out.println("\n\n📋 SCENARIO 3: Customer chooses Net Banking");
        System.out.println("─".repeat(70));
        
        // Using factory to create Net Banking payment
        PaymentMethod netBankingPayment = PaymentMethodFactory.createNetBankingPayment(
            "HDFC Bank",
            "123456789012",
            "HDFC0001234"
        );
        
        if (netBankingPayment != null) {
            System.out.println("✓ Payment method created: " + netBankingPayment.getPaymentType());
            netBankingPayment.processPayment(5000.00);
        }
        
        
        // ========== SCENARIO 4: Creating Wallet Payment ==========
        System.out.println("\n\n📋 SCENARIO 4: Customer chooses Wallet Payment");
        System.out.println("─".repeat(70));
        
        // Using factory to create Wallet payment
        PaymentMethod walletPayment = PaymentMethodFactory.createWalletPayment(
            "Paytm",
            "9876543210",
            10000.00  // Wallet balance
        );
        
        if (walletPayment != null) {
            System.out.println("✓ Payment method created: " + walletPayment.getPaymentType());
            walletPayment.processPayment(3000.00);
        }
        
        
        // ========== SCENARIO 5: Invalid Payment Type ==========
        System.out.println("\n\n📋 SCENARIO 5: Customer tries invalid payment type");
        System.out.println("─".repeat(70));
        
        // Factory handles invalid types gracefully
        PaymentMethod invalidPayment = PaymentMethodFactory.createPaymentMethod(
            "CRYPTOCURRENCY",  // Not supported yet!
            null
        );
        
        if (invalidPayment == null) {
            System.out.println("⚠️  Payment method not supported. Please choose another method.");
        }
        
        
        // ========== SCENARIO 6: Insufficient Wallet Balance ==========
        System.out.println("\n\n📋 SCENARIO 6: Wallet payment with insufficient balance");
        System.out.println("─".repeat(70));
        
        PaymentMethod lowBalanceWallet = PaymentMethodFactory.createWalletPayment(
            "PhonePe",
            "9988776655",
            500.00  // Low balance
        );
        
        if (lowBalanceWallet != null) {
            System.out.println("✓ Payment method created: " + lowBalanceWallet.getPaymentType());
            lowBalanceWallet.processPayment(1000.00);  // Trying to pay more than balance
        }
        
        
        // ========== SCENARIO 7: Comparing Transaction Fees ==========
        System.out.println("\n\n📋 SCENARIO 7: Comparing transaction fees for ₹10,000");
        System.out.println("─".repeat(70));
        
        double amount = 10000.00;
        
        PaymentMethod upi = PaymentMethodFactory.createUpiPayment("user@gpay");
        PaymentMethod card = PaymentMethodFactory.createCardPayment("1111222233334444", "Jane", "06/26", "456");
        PaymentMethod netBanking = PaymentMethodFactory.createNetBankingPayment("SBI", "987654321098", "SBIN0001234");
        PaymentMethod wallet = PaymentMethodFactory.createWalletPayment("Amazon Pay", "8877665544", 15000.00);
        
        System.out.println("\n💰 Transaction Fee Comparison:");
        System.out.println("   UPI:         ₹" + upi.getTransactionFee(amount) + " (0%)");
        System.out.println("   Card:        ₹" + card.getTransactionFee(amount) + " (2%)");
        System.out.println("   Net Banking: ₹" + netBanking.getTransactionFee(amount) + " (1%)");
        System.out.println("   Wallet:      ₹" + wallet.getTransactionFee(amount) + " (0.5%)");
        System.out.println("\n   💡 Best choice: UPI (Zero fees!)");
        
        
        System.out.println("\n\n" + "═".repeat(70));
        System.out.println("✓ All scenarios completed successfully!");
        System.out.println("═".repeat(70));
        
        
        // ========== KEY TAKEAWAYS ==========
        System.out.println("\n\n📚 KEY TAKEAWAYS:");
        System.out.println("─".repeat(70));
        System.out.println("1. ✓ Factory centralizes object creation logic");
        System.out.println("2. ✓ Client code doesn't need to know concrete class names");
        System.out.println("3. ✓ Easy to add new payment methods (just update factory)");
        System.out.println("4. ✓ Reduces coupling between client and products");
        System.out.println("5. ✓ Follows Open/Closed Principle");
        System.out.println("6. ✓ Makes code more maintainable and testable");
        
        System.out.println("\n💡 COMPARISON WITH STRATEGY PATTERN:");
        System.out.println("   - Strategy: Focuses on HOW to execute payment");
        System.out.println("   - Factory:  Focuses on HOW to CREATE payment objects");
        System.out.println("   - They work great together! Use Factory to create, Strategy to execute.");
    }
}

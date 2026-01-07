package behavioral.strategy;

/**
 * STRATEGY PATTERN DEMO - Fintech Payment System
 * 
 * This demo shows how Strategy Pattern works in a real-world fintech scenario.
 * 
 * SCENARIO:
 * A customer wants to make payments using different methods (UPI, Card, NetBanking).
 * Instead of using if-else or switch statements, we use Strategy Pattern to
 * dynamically select and execute the payment method.
 * 
 * BENEFITS DEMONSTRATED:
 * 1. Easy to add new payment methods (just create a new class implementing PaymentStrategy)
 * 2. Payment method can be changed at runtime
 * 3. No conditional statements cluttering the code
 * 4. Each payment method is encapsulated in its own class
 */
public class StrategyPatternDemo {
    
    public static void main(String[] args) {
        
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("     STRATEGY PATTERN - FINTECH PAYMENT SYSTEM");
        System.out.println("═══════════════════════════════════════════════════════\n");
        
        // ========== SCENARIO 1: Customer pays using UPI ==========
        System.out.println("📱 SCENARIO 1: Customer chooses UPI Payment");
        System.out.println("─────────────────────────────────────────────────────");
        
        // Create UPI payment strategy
        PaymentStrategy upiPayment = new UpiPayment("customer@paytm");
        
        // Create payment context with UPI strategy
        PaymentContext paymentContext = new PaymentContext(upiPayment);
        
        // Execute payment
        paymentContext.executePayment(1500.00);
        
        
        // ========== SCENARIO 2: Customer switches to Card Payment ==========
        System.out.println("\n\n💳 SCENARIO 2: Customer switches to Card Payment");
        System.out.println("─────────────────────────────────────────────────────");
        
        // Create card payment strategy
        PaymentStrategy cardPayment = new CardPayment(
            "1234567812345678",  // Card number
            "John Doe",          // Cardholder name
            "123",               // CVV
            "12/25"              // Expiry date
        );
        
        // Change strategy at runtime - THIS IS THE POWER OF STRATEGY PATTERN!
        paymentContext.setPaymentStrategy(cardPayment);
        
        // Execute payment with new strategy
        paymentContext.executePayment(2500.00);
        
        
        // ========== SCENARIO 3: Customer uses Net Banking ==========
        System.out.println("\n\n🏦 SCENARIO 3: Customer uses Net Banking");
        System.out.println("─────────────────────────────────────────────────────");
        
        // Create net banking payment strategy
        PaymentStrategy netBankingPayment = new NetBankingPayment(
            "HDFC Bank",         // Bank name
            "123456789012",      // Account number
            "HDFC0001234"        // IFSC code
        );
        
        // Change strategy again at runtime
        paymentContext.setPaymentStrategy(netBankingPayment);
        
        // Execute payment with net banking
        paymentContext.executePayment(5000.00);
        
        
        // ========== SCENARIO 4: Multiple transactions with different methods ==========
        System.out.println("\n\n🔄 SCENARIO 4: Multiple transactions");
        System.out.println("─────────────────────────────────────────────────────");
        
        // Quick UPI payment
        paymentContext.setPaymentStrategy(new UpiPayment("merchant@gpay"));
        paymentContext.executePayment(500.00);
        
        // Quick card payment
        paymentContext.setPaymentStrategy(new CardPayment(
            "9876543298765432", "Jane Smith", "456", "06/26"
        ));
        paymentContext.executePayment(750.00);
        
        
        System.out.println("\n═══════════════════════════════════════════════════════");
        System.out.println("✓ All payments processed successfully!");
        System.out.println("═══════════════════════════════════════════════════════");
        
        
        // ========== KEY TAKEAWAYS ==========
        System.out.println("\n\n📚 KEY TAKEAWAYS:");
        System.out.println("─────────────────────────────────────────────────────");
        System.out.println("1. ✓ Payment method can be changed at RUNTIME");
        System.out.println("2. ✓ No if-else or switch statements needed");
        System.out.println("3. ✓ Easy to add new payment methods (just implement interface)");
        System.out.println("4. ✓ Each payment method is independent and testable");
        System.out.println("5. ✓ Follows Open/Closed Principle (open for extension, closed for modification)");
    }
}

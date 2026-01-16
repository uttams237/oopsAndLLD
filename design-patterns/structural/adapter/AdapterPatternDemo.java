/**
 * Adapter Pattern Demo
 * 
 * Demonstrates how the Adapter pattern allows incompatible interfaces to work together.
 * 
 * Scenario: A fintech application needs to integrate with both:
 * 1. Modern payment gateways (that implement PaymentProcessor)
 * 2. Legacy bank APIs (with different method signatures)
 * 
 * The Adapter pattern makes the legacy API compatible without modifying its code.
 */
public class AdapterPatternDemo {
    
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("        ADAPTER PATTERN - FINTECH INTEGRATION DEMO");
        System.out.println("═══════════════════════════════════════════════════════════════\n");
        
        // Scenario 1: Using Modern Payment Gateway (no adapter needed)
        System.out.println("📱 SCENARIO 1: Processing with Modern Payment Gateway");
        System.out.println("─────────────────────────────────────────────────────────────");
        PaymentProcessor modernGateway = new ModernPaymentGateway("Razorpay");
        processPaymentThroughGateway(modernGateway, "ACC123456", 5000.00, "INR");
        
        System.out.println("\n\n");
        
        // Scenario 2: Using Legacy Bank API through Adapter
        System.out.println("🏦 SCENARIO 2: Processing with Legacy Bank API (via Adapter)");
        System.out.println("─────────────────────────────────────────────────────────────");
        LegacyBankAPI hdfc = new LegacyBankAPI("HDFC Bank");
        PaymentProcessor adaptedBank = new BankAPIAdapter(hdfc, "MERCHANT789");
        processPaymentThroughGateway(adaptedBank, "CUST456789", 3000.00, "INR");
        
        System.out.println("\n\n");
        
        // Scenario 3: Currency Conversion via Adapter
        System.out.println("💱 SCENARIO 3: International Payment (Currency Conversion)");
        System.out.println("─────────────────────────────────────────────────────────────");
        LegacyBankAPI icici = new LegacyBankAPI("ICICI Bank");
        PaymentProcessor adaptedICICI = new BankAPIAdapter(icici, "MERCHANT100");
        processPaymentThroughGateway(adaptedICICI, "INTL987654", 100.00, "USD");
        
        System.out.println("\n\n");
        
        // Scenario 4: Demonstrating Polymorphism
        System.out.println("🔄 SCENARIO 4: Processing Multiple Payments Polymorphically");
        System.out.println("─────────────────────────────────────────────────────────────");
        
        PaymentProcessor[] processors = {
            new ModernPaymentGateway("PhonePe"),
            new BankAPIAdapter(new LegacyBankAPI("SBI"), "MERCHANT200"),
            new ModernPaymentGateway("Stripe")
        };
        
        for (int i = 0; i < processors.length; i++) {
            System.out.println("\n🔹 Processor " + (i + 1) + ":");
            processors[i].processPayment("BATCH" + i, 1000.00, "INR");
        }
        
        System.out.println("\n\n═══════════════════════════════════════════════════════════════");
        System.out.println("✅ All payments processed successfully using unified interface!");
        System.out.println("═══════════════════════════════════════════════════════════════");
    }
    
    /**
     * Client code - works with any PaymentProcessor
     * This method doesn't know or care whether it's using a modern gateway
     * or an adapted legacy API - that's the power of the Adapter pattern!
     */
    private static void processPaymentThroughGateway(
            PaymentProcessor processor, 
            String accountId, 
            double amount, 
            String currency) {
        
        // Process payment
        boolean success = processor.processPayment(accountId, amount, currency);
        
        if (success) {
            // Check status
            String status = processor.getTransactionStatus("TXN" + System.currentTimeMillis());
            System.out.println("✓ Payment Status: " + status);
        }
    }
}

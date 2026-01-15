package structural.decorator;

/**
 * DecoratorPatternDemo - Comprehensive demonstration of Decorator Design Pattern
 * 
 * This demo showcases how decorators can be dynamically added to payment objects
 * to add responsibilities like taxes, fees, and discounts without modifying the base class.
 * 
 * Key Concept: Decorators wrap objects and add functionality layer by layer.
 */
public class DecoratorPatternDemo {
    
    public static void main(String[] args) {
        
        printHeader("DECORATOR PATTERN - FINTECH PAYMENT SYSTEM");
        
        // Scenario 1: Basic Payment (No Decorators)
        demonstrateBasicPayment();
        
        // Scenario 2: Payment with GST
        demonstratePaymentWithGST();
        
        // Scenario 3: Payment with Multiple Decorators (GST + Processing Fee)
        demonstrateMultipleDecorators();
        
        // Scenario 4: Payment with All Decorators (GST + Fee + Cashback)
        demonstrateFullDecoration();
        
        // Scenario 5: International Payment
        demonstrateInternationalPayment();
        
        // Scenario 6: Order Matters - Demonstration
        demonstrateDecoratorOrdering();
        
        printFooter();
    }
    
    /**
     * SCENARIO 1: Basic Payment Without Any Decorators
     */
    private static void demonstrateBasicPayment() {
        printSectionHeader("SCENARIO 1: Basic Payment (No Decorations)");
        
        System.out.println("📌 Customer makes a simple UPI payment of ₹10,000\n");
        
        Payment payment = new BasePayment(10000, "UPI");
        
        displayPaymentDetails(payment);
        
        System.out.println("\n✅ Result: No additional charges applied");
        System.out.println("   Clean, straightforward payment\n");
    }
    
    /**
     * SCENARIO 2: Payment with GST Decorator
     */
    private static void demonstratePaymentWithGST() {
        printSectionHeader("SCENARIO 2: Payment with GST");
        
        System.out.println("📌 Customer makes a payment with 18% GST applied\n");
        
        // Start with base payment, then wrap with GST decorator
        Payment payment = new BasePayment(10000, "Card");
        payment = new GSTDecorator(payment);
        
        displayPaymentDetails(payment);
        
        System.out.println("\n✅ Result: GST added to base amount");
        System.out.println("   ₹10,000 + ₹1,800 (GST) = ₹11,800\n");
    }
    
    /**
     * SCENARIO 3: Multiple Decorators (GST + Processing Fee)
     */
    private static void demonstrateMultipleDecorators() {
        printSectionHeader("SCENARIO 3: Payment with GST + Processing Fee");
        
        System.out.println("📌 Customer makes a payment with GST and processing fee\n");
        
        // Stack decorators: Base → GST → Processing Fee
        Payment payment = new BasePayment(10000, "UPI");
        payment = new GSTDecorator(payment);
        payment = new ProcessingFeeDecorator(payment);
        
        displayPaymentDetails(payment);
        
        System.out.println("\n✅ Result: Multiple decorators stacked");
        System.out.println("   ₹10,000 → +GST (₹1,800) → +Fee (₹59) = ₹11,859\n");
    }
    
    /**
     * SCENARIO 4: Full Decoration (GST + Fee + Cashback)
     */
    private static void demonstrateFullDecoration() {
        printSectionHeader("SCENARIO 4: Payment with GST + Fee + Cashback");
        
        System.out.println("📌 Customer uses a promotional code for ₹500 cashback\n");
        
        // Stack all decorators including cashback
        Payment payment = new BasePayment(10000, "Net Banking");
        payment = new GSTDecorator(payment);
        payment = new ProcessingFeeDecorator(payment);
        payment = new CashbackDecorator(payment, 500);
        
        displayPaymentDetails(payment);
        
        System.out.println("\n✅ Result: Cashback reduces the final amount");
        System.out.println("   ₹10,000 → +GST → +Fee → -Cashback (₹500) = ₹11,359\n");
    }
    
    /**
     * SCENARIO 5: International Payment
     */
    private static void demonstrateInternationalPayment() {
        printSectionHeader("SCENARIO 5: International Payment");
        
        System.out.println("📌 Customer makes a cross-border payment to USD\n");
        
        // International payment with all fees
        Payment payment = new BasePayment(10000, "International Card");
        payment = new GSTDecorator(payment);
        payment = new ProcessingFeeDecorator(payment);
        payment = new InternationalFeeDecorator(payment, "USD");
        
        displayPaymentDetails(payment);
        
        System.out.println("\n✅ Result: International fee added for currency conversion");
        System.out.println("   Additional 2% fee for cross-border transaction\n");
    }
    
    /**
     * SCENARIO 6: Demonstrate that decorator order can matter
     */
    private static void demonstrateDecoratorOrdering() {
        printSectionHeader("SCENARIO 6: Does Decorator Order Matter?");
        
        System.out.println("📌 Comparing different decorator orderings\n");
        
        // Order 1: Base → GST → Processing Fee
        System.out.println("ORDER 1: Base → GST → Processing Fee");
        Payment payment1 = new BasePayment(10000, "UPI");
        payment1 = new GSTDecorator(payment1);
        payment1 = new ProcessingFeeDecorator(payment1);
        System.out.println("   Description: " + payment1.getDescription());
        System.out.println("   Final Amount: ₹" + String.format("%.2f", payment1.processPayment()));
        
        // Order 2: Base → Processing Fee → GST
        System.out.println("\nORDER 2: Base → Processing Fee → GST");
        Payment payment2 = new BasePayment(10000, "UPI");
        payment2 = new ProcessingFeeDecorator(payment2);
        payment2 = new GSTDecorator(payment2);
        System.out.println("   Description: " + payment2.getDescription());
        System.out.println("   Final Amount: ₹" + String.format("%.2f", payment2.processPayment()));
        
        double difference = Math.abs(payment1.processPayment() - payment2.processPayment());
        
        if (difference < 0.01) {
            System.out.println("\n✅ Result: Order doesn't matter for these decorators");
            System.out.println("   Both approaches yield the same result");
        } else {
            System.out.println("\n⚠️  Result: Order DOES matter!");
            System.out.println("   Difference: ₹" + String.format("%.2f", difference));
        }
        
        System.out.println("\n💡 Note: In this implementation, order doesn't matter because");
        System.out.println("   each decorator calculates its charge independently.");
        System.out.println("   In real scenarios, order may matter if decorators interact.\n");
    }
    
    // ==================== Utility Methods ====================
    
    /**
     * Display payment details in a formatted box
     */
    private static void displayPaymentDetails(Payment payment) {
        System.out.println("╔" + "═".repeat(70) + "╗");
        System.out.println("║" + centerText("PAYMENT DETAILS", 70) + "║");
        System.out.println("╠" + "═".repeat(70) + "╣");
        
        String description = payment.getDescription();
        System.out.println("║  Description: " + padRight(description, 54) + "║");
        System.out.println("║  " + " ".repeat(68) + "║");
        
        double finalAmount = payment.processPayment();
        String amountStr = "Final Amount: ₹" + String.format("%,.2f", finalAmount);
        System.out.println("║  " + padRight(amountStr, 68) + "║");
        
        System.out.println("╚" + "═".repeat(70) + "╝");
    }
    
    private static void printHeader(String title) {
        System.out.println("\n" + "═".repeat(80));
        System.out.println("  " + title);
        System.out.println("═".repeat(80) + "\n");
    }
    
    private static void printSectionHeader(String section) {
        System.out.println("\n" + "─".repeat(80));
        System.out.println("  " + section);
        System.out.println("─".repeat(80) + "\n");
    }
    
    private static void printFooter() {
        System.out.println("\n" + "═".repeat(80));
        System.out.println("  DEMO COMPLETED - Decorator Pattern demonstrated successfully!");
        System.out.println("═".repeat(80) + "\n");
    }
    
    private static String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        return " ".repeat(padding) + text + " ".repeat(width - padding - text.length());
    }
    
    private static String padRight(String text, int width) {
        if (text.length() >= width) {
            return text.substring(0, width);
        }
        return text + " ".repeat(width - text.length());
    }
}

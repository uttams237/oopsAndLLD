package creational.builder;

import java.time.LocalDateTime;

/**
 * BuilderPatternDemo - Comprehensive demonstration of Builder Design Pattern
 * 
 * This demo showcases how the Builder pattern simplifies the creation of complex objects
 * with many optional parameters, providing a clear and readable API.
 */
public class BuilderPatternDemo {
    
    public static void main(String[] args) {
        
        printHeader("BUILDER PATTERN - FINTECH TRANSACTION SYSTEM");
        
        // Scenario 1: Minimal Transaction (Required fields only)
        demonstrateMinimalTransaction();
        
        // Scenario 2: Full Transaction (All optional fields)
        demonstrateFullTransaction();
        
        // Scenario 3: UPI Transaction
        demonstrateUpiTransaction();
        
        // Scenario 4: International Transaction
        demonstrateInternationalTransaction();
        
        // Scenario 5: Refund Transaction
        demonstrateRefundTransaction();
        
        // Scenario 6: Validation Example
        demonstrateValidation();
        
        printFooter();
    }
    
    /**
     * SCENARIO 1: Minimal Transaction
     * Only required fields, all optionals use defaults
     */
    private static void demonstrateMinimalTransaction() {
        printSectionHeader("SCENARIO 1: Minimal Transaction (Required Fields Only)");
        
        System.out.println("📌 Creating a basic transaction with only required fields...\n");
        
        Transaction txn = new Transaction.Builder(
                "TXN-MIN-001",    // Transaction ID
                500.00,           // Amount
                "INR",            // Currency
                "USER-123",       // Sender
                "MERCHANT-456"    // Receiver
        ).build();
        
        txn.displayDetails();
        
        System.out.println("\n✅ Result: Transaction created with default values for optional fields");
    }
    
    /**
     * SCENARIO 2: Full Transaction
     * All fields specified using the fluent builder API
     */
    private static void demonstrateFullTransaction() {
        printSectionHeader("SCENARIO 2: Full Transaction (All Fields Specified)");
        
        System.out.println("📌 Creating a transaction with all optional fields...\n");
        
        Transaction txn = new Transaction.Builder(
                "TXN-FULL-002",
                15000.00,
                "INR",
                "CUSTOMER-AMIT",
                "FLIPKART-STORE"
        )
        .paymentMethod("Credit Card")
        .status("SUCCESS")
        .note("iPhone 15 Purchase - EMI Available")
        .location("Mumbai, Maharashtra")
        .category("ELECTRONICS")
        .refundable(true)
        .build();
        
        txn.displayDetails();
        
        System.out.println("\n✅ Result: Transaction created with all optional fields populated");
    }
    
    /**
     * SCENARIO 3: UPI Transaction
     * Typical Indian UPI payment
     */
    private static void demonstrateUpiTransaction() {
        printSectionHeader("SCENARIO 3: UPI Payment Transaction");
        
        System.out.println("📌 Creating a typical UPI payment...\n");
        
        Transaction txn = new Transaction.Builder(
                "TXN-UPI-003",
                250.00,
                "INR",
                "uttam@paytm",
                "chai-wala@upi"
        )
        .paymentMethod("UPI")
        .status("SUCCESS")
        .note("Morning chai and samosa")
        .category("FOOD")
        .location("Delhi")
        .refundable(false)
        .build();
        
        txn.displayDetails();
        
        System.out.println("\n✅ Result: Quick UPI transaction completed");
    }
    
    /**
     * SCENARIO 4: International Transaction
     * Cross-border payment with currency conversion
     */
    private static void demonstrateInternationalTransaction() {
        printSectionHeader("SCENARIO 4: International Payment");
        
        System.out.println("📌 Creating an international payment with USD to INR conversion...\n");
        
        Transaction txn = new Transaction.Builder(
                "TXN-INTL-004",
                100.00,
                "USD",
                "INDIAN-CUSTOMER",
                "US-MERCHANT-AMAZON"
        )
        .paymentMethod("International Card")
        .status("PROCESSING")
        .note("Amazon.com Purchase")
        .category("INTERNATIONAL")
        .conversionRate(83.50)  // 1 USD = 83.50 INR
        .refundable(true)
        .build();
        
        txn.displayDetails();
        
        System.out.println("\n✅ Result: International transaction with currency conversion");
        System.out.println("   Original: $100.00 USD");
        System.out.println("   Converted: ₹" + String.format("%,.2f", txn.getConvertedAmount()) + " INR");
    }
    
    /**
     * SCENARIO 5: Refund Transaction
     * Processing a refund for a previous transaction
     */
    private static void demonstrateRefundTransaction() {
        printSectionHeader("SCENARIO 5: Refund Transaction");
        
        System.out.println("📌 Processing a refund for a cancelled order...\n");
        
        Transaction refund = new Transaction.Builder(
                "TXN-REFUND-005",
                5000.00,
                "INR",
                "MERCHANT-STORE",  // Merchant is now sender
                "CUSTOMER-USER"   // Customer receives refund
        )
        .paymentMethod("Original Payment Method")
        .status("REFUND_INITIATED")
        .note("Order #ORD-789 Cancelled - Full Refund")
        .category("REFUND")
        .refundable(false)  // Refunds are not refundable!
        .build();
        
        refund.displayDetails();
        
        System.out.println("\n✅ Result: Refund transaction initiated");
    }
    
    /**
     * SCENARIO 6: Validation Example
     * Shows how the builder validates before building
     */
    private static void demonstrateValidation() {
        printSectionHeader("SCENARIO 6: Builder Validation Demo");
        
        System.out.println("📌 Attempting to create invalid transactions...\n");
        
        // Test 1: Negative amount
        System.out.println("Test 1: Negative Amount");
        try {
            new Transaction.Builder("TXN-BAD-001", -100, "INR", "A", "B").build();
            System.out.println("   ❌ Should have thrown an exception!");
        } catch (IllegalStateException e) {
            System.out.println("   ✅ Caught: " + e.getMessage());
        }
        
        // Test 2: Same sender and receiver
        System.out.println("\nTest 2: Same Sender and Receiver");
        try {
            new Transaction.Builder("TXN-BAD-002", 100, "INR", "SAME-USER", "SAME-USER").build();
            System.out.println("   ❌ Should have thrown an exception!");
        } catch (IllegalStateException e) {
            System.out.println("   ✅ Caught: " + e.getMessage());
        }
        
        // Test 3: Empty transaction ID
        System.out.println("\nTest 3: Empty Transaction ID");
        try {
            new Transaction.Builder("", 100, "INR", "A", "B").build();
            System.out.println("   ❌ Should have thrown an exception!");
        } catch (IllegalStateException e) {
            System.out.println("   ✅ Caught: " + e.getMessage());
        }
        
        System.out.println("\n✅ Result: Builder validation prevents invalid transactions");
    }
    
    // ==================== Utility Methods ====================
    
    private static void printHeader(String title) {
        System.out.println("\n" + "═".repeat(70));
        System.out.println("  " + title);
        System.out.println("═".repeat(70) + "\n");
    }
    
    private static void printSectionHeader(String section) {
        System.out.println("\n" + "─".repeat(70));
        System.out.println("  " + section);
        System.out.println("─".repeat(70) + "\n");
    }
    
    private static void printFooter() {
        System.out.println("\n" + "═".repeat(70));
        System.out.println("  DEMO COMPLETED - Builder Pattern demonstrated successfully!");
        System.out.println("═".repeat(70) + "\n");
    }
}

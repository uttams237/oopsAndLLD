package creational.builder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Transaction - The Product class in Builder Pattern
 * 
 * This class represents a complex fintech transaction with many fields.
 * Some fields are required, others are optional.
 * 
 * The Builder pattern is used here because:
 * 1. Transaction has many fields (some optional)
 * 2. A telescoping constructor would be confusing
 * 3. We want immutability after construction
 * 4. We need validation before creating the object
 * 
 * Note: The constructor is PRIVATE - only the Builder can create Transaction objects.
 */
public class Transaction {
    
    // Required fields
    private final String transactionId;
    private final double amount;
    private final String currency;
    private final String senderId;
    private final String receiverId;
    
    // Optional fields with defaults
    private final LocalDateTime timestamp;
    private final String paymentMethod;
    private final String status;
    private final String note;
    private final String location;
    private final boolean isRefundable;
    private final double conversionRate;
    private final String category;
    
    /**
     * PRIVATE constructor - Only TransactionBuilder can call this.
     * This ensures all transactions are created through the builder.
     */
    private Transaction(Builder builder) {
        this.transactionId = builder.transactionId;
        this.amount = builder.amount;
        this.currency = builder.currency;
        this.senderId = builder.senderId;
        this.receiverId = builder.receiverId;
        this.timestamp = builder.timestamp;
        this.paymentMethod = builder.paymentMethod;
        this.status = builder.status;
        this.note = builder.note;
        this.location = builder.location;
        this.isRefundable = builder.isRefundable;
        this.conversionRate = builder.conversionRate;
        this.category = builder.category;
    }
    
    // ==================== Getters (No Setters - Immutable!) ====================
    
    public String getTransactionId() { return transactionId; }
    public double getAmount() { return amount; }
    public String getCurrency() { return currency; }
    public String getSenderId() { return senderId; }
    public String getReceiverId() { return receiverId; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getPaymentMethod() { return paymentMethod; }
    public String getStatus() { return status; }
    public String getNote() { return note; }
    public String getLocation() { return location; }
    public boolean isRefundable() { return isRefundable; }
    public double getConversionRate() { return conversionRate; }
    public String getCategory() { return category; }
    
    /**
     * Calculate the converted amount if a different currency is involved
     */
    public double getConvertedAmount() {
        return amount * conversionRate;
    }
    
    /**
     * Display transaction details in a formatted way
     */
    public void displayDetails() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        System.out.println("\n╔" + "═".repeat(60) + "╗");
        System.out.println("║" + centerText("TRANSACTION DETAILS", 60) + "║");
        System.out.println("╠" + "═".repeat(60) + "╣");
        System.out.println("║  ID:             " + padRight(transactionId, 40) + "║");
        System.out.println("║  Amount:         " + padRight(currency + " " + String.format("%,.2f", amount), 40) + "║");
        System.out.println("║  From:           " + padRight(senderId, 40) + "║");
        System.out.println("║  To:             " + padRight(receiverId, 40) + "║");
        System.out.println("║  Payment Method: " + padRight(paymentMethod, 40) + "║");
        System.out.println("║  Status:         " + padRight(status, 40) + "║");
        System.out.println("║  Time:           " + padRight(timestamp.format(formatter), 40) + "║");
        
        if (note != null && !note.isEmpty()) {
            System.out.println("║  Note:           " + padRight(note, 40) + "║");
        }
        if (location != null && !location.isEmpty()) {
            System.out.println("║  Location:       " + padRight(location, 40) + "║");
        }
        if (category != null && !category.isEmpty()) {
            System.out.println("║  Category:       " + padRight(category, 40) + "║");
        }
        
        System.out.println("║  Refundable:     " + padRight(isRefundable ? "Yes" : "No", 40) + "║");
        
        if (conversionRate != 1.0) {
            System.out.println("║  Conversion Rate: " + padRight(String.valueOf(conversionRate), 39) + "║");
            System.out.println("║  Converted Amt:  " + padRight("INR " + String.format("%,.2f", getConvertedAmount()), 40) + "║");
        }
        
        System.out.println("╚" + "═".repeat(60) + "╝");
    }
    
    private String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        return " ".repeat(padding) + text + " ".repeat(width - padding - text.length());
    }
    
    private String padRight(String text, int width) {
        if (text == null) text = "N/A";
        if (text.length() >= width) return text.substring(0, width);
        return text + " ".repeat(width - text.length());
    }
    
    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + transactionId + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", from='" + senderId + '\'' +
                ", to='" + receiverId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
    
    // ==================== THE BUILDER CLASS (Inner Static Class) ====================
    
    /**
     * TransactionBuilder - The Builder for Transaction objects
     * 
     * This inner static class provides a fluent API for building Transaction objects.
     * 
     * Usage:
     *   Transaction txn = new Transaction.Builder("TXN001", 1000, "INR", "Sender", "Receiver")
     *       .paymentMethod("UPI")
     *       .note("Payment for services")
     *       .build();
     */
    public static class Builder {
        
        // Required parameters
        private final String transactionId;
        private final double amount;
        private final String currency;
        private final String senderId;
        private final String receiverId;
        
        // Optional parameters with defaults
        private LocalDateTime timestamp = LocalDateTime.now();
        private String paymentMethod = "UNKNOWN";
        private String status = "PENDING";
        private String note = "";
        private String location = "";
        private boolean isRefundable = true;
        private double conversionRate = 1.0;
        private String category = "GENERAL";
        
        /**
         * Constructor with REQUIRED fields only.
         * Optional fields use defaults and can be set via fluent methods.
         */
        public Builder(String transactionId, double amount, String currency, 
                       String senderId, String receiverId) {
            this.transactionId = transactionId;
            this.amount = amount;
            this.currency = currency;
            this.senderId = senderId;
            this.receiverId = receiverId;
        }
        
        /**
         * Set the payment method (UPI, Card, NetBanking, etc.)
         */
        public Builder paymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }
        
        /**
         * Set the transaction status
         */
        public Builder status(String status) {
            this.status = status;
            return this;
        }
        
        /**
         * Set a custom timestamp (default is current time)
         */
        public Builder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }
        
        /**
         * Add a note/memo to the transaction
         */
        public Builder note(String note) {
            this.note = note;
            return this;
        }
        
        /**
         * Set the transaction location
         */
        public Builder location(String location) {
            this.location = location;
            return this;
        }
        
        /**
         * Set whether the transaction is refundable
         */
        public Builder refundable(boolean isRefundable) {
            this.isRefundable = isRefundable;
            return this;
        }
        
        /**
         * Set currency conversion rate (for international transactions)
         */
        public Builder conversionRate(double rate) {
            this.conversionRate = rate;
            return this;
        }
        
        /**
         * Set transaction category
         */
        public Builder category(String category) {
            this.category = category;
            return this;
        }
        
        /**
         * Build and return the Transaction object.
         * This is where validation can happen before creating the object.
         */
        public Transaction build() {
            // Validation before building
            validate();
            return new Transaction(this);
        }
        
        /**
         * Validate the transaction data before building
         */
        private void validate() {
            if (transactionId == null || transactionId.isEmpty()) {
                throw new IllegalStateException("Transaction ID is required");
            }
            if (amount <= 0) {
                throw new IllegalStateException("Amount must be positive");
            }
            if (currency == null || currency.isEmpty()) {
                throw new IllegalStateException("Currency is required");
            }
            if (senderId == null || senderId.isEmpty()) {
                throw new IllegalStateException("Sender ID is required");
            }
            if (receiverId == null || receiverId.isEmpty()) {
                throw new IllegalStateException("Receiver ID is required");
            }
            if (senderId.equals(receiverId)) {
                throw new IllegalStateException("Sender and Receiver cannot be the same");
            }
        }
    }
}

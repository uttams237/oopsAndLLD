/**
 * Target Interface - Modern Payment Processor Interface
 * This is the interface that our application expects to work with.
 */
public interface PaymentProcessor {
    boolean processPayment(String accountId, double amount, String currency);
    String getTransactionStatus(String transactionId);
    boolean refundPayment(String transactionId);
}

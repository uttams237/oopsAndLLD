package behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * CONCRETE SUBJECT - Transaction
 * 
 * This class implements the TransactionSubject interface.
 * It maintains a list of observers and notifies them when a transaction occurs.
 * 
 * KEY FEATURES:
 * - Maintains a list of observers (subscribers)
 * - Allows observers to subscribe/unsubscribe
 * - Notifies all observers when transaction state changes
 * - Loose coupling - doesn't know concrete observer types
 */
public class Transaction implements TransactionSubject {
    
    // List of observers subscribed to this transaction
    private List<NotificationObserver> observers;
    
    // Transaction details
    private String transactionId;
    private double amount;
    private String status;
    private String message;
    
    /**
     * Constructor initializes the observers list
     */
    public Transaction() {
        this.observers = new ArrayList<>();
    }
    
    /**
     * Attach/Subscribe an observer
     * 
     * @param observer The observer to be added
     */
    @Override
    public void attach(NotificationObserver observer) {
        observers.add(observer);
        System.out.println("✓ " + observer.getObserverName() + " subscribed to transaction notifications");
    }
    
    /**
     * Detach/Unsubscribe an observer
     * 
     * @param observer The observer to be removed
     */
    @Override
    public void detach(NotificationObserver observer) {
        observers.remove(observer);
        System.out.println("✗ " + observer.getObserverName() + " unsubscribed from transaction notifications");
    }
    
    /**
     * Notify all observers about the transaction
     * This is the KEY method of the Observer pattern!
     */
    @Override
    public void notifyObservers() {
        System.out.println("\n🔔 Notifying " + observers.size() + " observer(s)...");
        
        // Loop through all observers and call their update() method
        for (NotificationObserver observer : observers) {
            observer.update(transactionId, amount, status, message);
        }
        
        System.out.println("\n✓ All observers notified successfully!\n");
    }
    
    /**
     * Process a transaction
     * This method simulates a transaction and triggers notifications
     * 
     * @param transactionId Unique transaction ID
     * @param amount Transaction amount
     * @param status Transaction status (SUCCESS, FAILED, PENDING)
     * @param message Additional message
     */
    public void processTransaction(String transactionId, double amount, String status, String message) {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("💳 PROCESSING TRANSACTION");
        System.out.println("═".repeat(60));
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Amount: ₹" + amount);
        System.out.println("Status: " + status);
        System.out.println("Message: " + message);
        System.out.println("═".repeat(60));
        
        // Update transaction state
        this.transactionId = transactionId;
        this.amount = amount;
        this.status = status;
        this.message = message;
        
        // Notify all observers - THIS IS THE MAGIC OF OBSERVER PATTERN!
        notifyObservers();
    }
}

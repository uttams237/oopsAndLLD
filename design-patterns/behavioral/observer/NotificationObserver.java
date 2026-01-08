package behavioral.observer;

/**
 * OBSERVER PATTERN - Observer Interface
 * 
 * This is the Observer interface that defines the contract for all notification observers.
 * Each concrete observer (SMS, Email, Push) will implement this interface.
 * 
 * WHY USE OBSERVER PATTERN?
 * - Allows one-to-many dependency between objects
 * - When one object changes state, all dependents are notified automatically
 * - Loose coupling between subject and observers
 * - Follows Open/Closed Principle (easy to add new observers)
 * 
 * REAL-WORLD ANALOGY:
 * Think of YouTube subscriptions - when a channel uploads a video (subject changes state),
 * all subscribers (observers) get notified automatically.
 */
public interface NotificationObserver {
    
    /**
     * This method is called by the Subject when a transaction event occurs
     * 
     * @param transactionId The unique ID of the transaction
     * @param amount The transaction amount
     * @param status The transaction status (SUCCESS, FAILED, PENDING)
     * @param message Additional message about the transaction
     */
    void update(String transactionId, double amount, String status, String message);
    
    /**
     * Get the name/type of this observer
     * 
     * @return Observer name (e.g., "SMS Notification", "Email Notification")
     */
    String getObserverName();
}

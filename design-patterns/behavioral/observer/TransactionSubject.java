package behavioral.observer;

/**
 * OBSERVER PATTERN - Subject Interface
 * 
 * This is the Subject interface that defines the contract for objects that can be observed.
 * The Subject maintains a list of observers and notifies them of state changes.
 * 
 * KEY RESPONSIBILITIES:
 * - Attach observers (subscribe)
 * - Detach observers (unsubscribe)
 * - Notify all observers when state changes
 * 
 * REAL-WORLD ANALOGY:
 * Think of a newspaper publisher (Subject) that maintains a list of subscribers (Observers).
 * When a new edition is published, all subscribers are notified.
 */
public interface TransactionSubject {
    
    /**
     * Attach/Subscribe an observer to this subject
     * 
     * @param observer The observer to be added
     */
    void attach(NotificationObserver observer);
    
    /**
     * Detach/Unsubscribe an observer from this subject
     * 
     * @param observer The observer to be removed
     */
    void detach(NotificationObserver observer);
    
    /**
     * Notify all attached observers about a state change
     * This method is called internally when the subject's state changes
     */
    void notifyObservers();
}

package behavioral.observer;

/**
 * CONCRETE OBSERVER - SMS Notification
 * 
 * This class implements the NotificationObserver interface for SMS notifications.
 * When a transaction occurs, it sends an SMS to the user's phone number.
 */
public class SmsNotificationObserver implements NotificationObserver {
    
    private String phoneNumber;  // User's phone number
    
    /**
     * Constructor to initialize SMS observer with phone number
     * 
     * @param phoneNumber The phone number to send SMS to
     */
    public SmsNotificationObserver(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    /**
     * This method is called when a transaction event occurs
     * It simulates sending an SMS notification
     * 
     * @param transactionId The unique ID of the transaction
     * @param amount The transaction amount
     * @param status The transaction status
     * @param message Additional message
     */
    @Override
    public void update(String transactionId, double amount, String status, String message) {
        // In real-world, this would connect to an SMS gateway (Twilio, AWS SNS, etc.)
        System.out.println("\n📱 ═══════════════ SMS NOTIFICATION ═══════════════");
        System.out.println("   To: " + phoneNumber);
        System.out.println("   ─────────────────────────────────────────────");
        System.out.println("   Transaction ID: " + transactionId);
        System.out.println("   Amount: ₹" + amount);
        System.out.println("   Status: " + status);
        System.out.println("   Message: " + message);
        System.out.println("   ═════════════════════════════════════════════");
    }
    
    /**
     * Get the observer name
     * 
     * @return "SMS Notification"
     */
    @Override
    public String getObserverName() {
        return "SMS Notification";
    }
}

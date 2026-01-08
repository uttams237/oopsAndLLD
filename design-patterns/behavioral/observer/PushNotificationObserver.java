package behavioral.observer;

/**
 * CONCRETE OBSERVER - Push Notification
 * 
 * This class implements the NotificationObserver interface for Push notifications.
 * When a transaction occurs, it sends a push notification to the user's mobile app.
 */
public class PushNotificationObserver implements NotificationObserver {
    
    private String deviceId;  // User's device ID for push notifications
    
    /**
     * Constructor to initialize Push observer with device ID
     * 
     * @param deviceId The device ID to send push notifications to
     */
    public PushNotificationObserver(String deviceId) {
        this.deviceId = deviceId;
    }
    
    /**
     * This method is called when a transaction event occurs
     * It simulates sending a push notification
     * 
     * @param transactionId The unique ID of the transaction
     * @param amount The transaction amount
     * @param status The transaction status
     * @param message Additional message
     */
    @Override
    public void update(String transactionId, double amount, String status, String message) {
        // In real-world, this would connect to FCM (Firebase Cloud Messaging) or APNs
        System.out.println("\n🔔 ═══════════════ PUSH NOTIFICATION ═══════════════");
        System.out.println("   Device: " + deviceId);
        System.out.println("   ─────────────────────────────────────────────");
        System.out.println("   💰 Transaction " + status);
        System.out.println("   ");
        System.out.println("   ₹" + amount + " - " + message);
        System.out.println("   ID: " + transactionId);
        System.out.println("   ");
        System.out.println("   Tap to view details");
        System.out.println("   ═════════════════════════════════════════════");
    }
    
    /**
     * Get the observer name
     * 
     * @return "Push Notification"
     */
    @Override
    public String getObserverName() {
        return "Push Notification";
    }
}

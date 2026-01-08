package behavioral.observer;

/**
 * CONCRETE OBSERVER - Email Notification
 * 
 * This class implements the NotificationObserver interface for Email notifications.
 * When a transaction occurs, it sends an email to the user's email address.
 */
public class EmailNotificationObserver implements NotificationObserver {
    
    private String emailAddress;  // User's email address
    
    /**
     * Constructor to initialize Email observer with email address
     * 
     * @param emailAddress The email address to send notifications to
     */
    public EmailNotificationObserver(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    /**
     * This method is called when a transaction event occurs
     * It simulates sending an email notification
     * 
     * @param transactionId The unique ID of the transaction
     * @param amount The transaction amount
     * @param status The transaction status
     * @param message Additional message
     */
    @Override
    public void update(String transactionId, double amount, String status, String message) {
        // In real-world, this would connect to an email service (SendGrid, AWS SES, etc.)
        System.out.println("\n📧 ═══════════════ EMAIL NOTIFICATION ═══════════════");
        System.out.println("   To: " + emailAddress);
        System.out.println("   Subject: Transaction " + status);
        System.out.println("   ─────────────────────────────────────────────");
        System.out.println("   Dear Customer,");
        System.out.println("   ");
        System.out.println("   Your transaction has been " + status.toLowerCase() + ".");
        System.out.println("   ");
        System.out.println("   Transaction Details:");
        System.out.println("   - Transaction ID: " + transactionId);
        System.out.println("   - Amount: ₹" + amount);
        System.out.println("   - Status: " + status);
        System.out.println("   - " + message);
        System.out.println("   ");
        System.out.println("   Thank you for using our service!");
        System.out.println("   ═════════════════════════════════════════════");
    }
    
    /**
     * Get the observer name
     * 
     * @return "Email Notification"
     */
    @Override
    public String getObserverName() {
        return "Email Notification";
    }
}

package behavioral.observer;

/**
 * OBSERVER PATTERN DEMO - Fintech Transaction Notification System
 * 
 * This demo shows how Observer Pattern works in a real-world fintech scenario.
 * 
 * SCENARIO:
 * When a transaction occurs, multiple notification channels (SMS, Email, Push)
 * need to be notified automatically. Instead of hardcoding notification logic
 * in the transaction class, we use Observer Pattern to decouple them.
 * 
 * BENEFITS DEMONSTRATED:
 * 1. One-to-many dependency (1 transaction → many notifications)
 * 2. Loose coupling (Transaction doesn't know about specific notification types)
 * 3. Easy to add/remove notification channels at runtime
 * 4. Follows Open/Closed Principle (add new observers without modifying Transaction)
 */
public class ObserverPatternDemo {
    
    public static void main(String[] args) {
        
        System.out.println("═".repeat(70));
        System.out.println("     OBSERVER PATTERN - FINTECH NOTIFICATION SYSTEM");
        System.out.println("═".repeat(70));
        
        // ========== SCENARIO 1: User subscribes to all notification channels ==========
        System.out.println("\n📋 SCENARIO 1: User subscribes to all notification channels");
        System.out.println("─".repeat(70));
        
        // Create the Subject (Transaction)
        Transaction transaction = new Transaction();
        
        // Create Observers (Notification channels)
        NotificationObserver smsObserver = new SmsNotificationObserver("+91-9876543210");
        NotificationObserver emailObserver = new EmailNotificationObserver("user@example.com");
        NotificationObserver pushObserver = new PushNotificationObserver("device-abc-123");
        
        // Subscribe observers to the transaction
        transaction.attach(smsObserver);
        transaction.attach(emailObserver);
        transaction.attach(pushObserver);
        
        // Process a successful transaction
        // All 3 observers will be notified automatically!
        transaction.processTransaction("TXN001", 5000.00, "SUCCESS", "Payment received successfully");
        
        
        // ========== SCENARIO 2: User unsubscribes from SMS ==========
        System.out.println("\n\n📋 SCENARIO 2: User unsubscribes from SMS notifications");
        System.out.println("─".repeat(70));
        
        // Unsubscribe SMS observer
        transaction.detach(smsObserver);
        
        // Process another transaction
        // Only Email and Push will be notified (SMS unsubscribed)
        transaction.processTransaction("TXN002", 2500.00, "SUCCESS", "Refund processed");
        
        
        // ========== SCENARIO 3: Failed transaction notification ==========
        System.out.println("\n\n📋 SCENARIO 3: Failed transaction notification");
        System.out.println("─".repeat(70));
        
        // Process a failed transaction
        // Email and Push observers will be notified
        transaction.processTransaction("TXN003", 10000.00, "FAILED", "Insufficient balance");
        
        
        // ========== SCENARIO 4: Adding a new observer at runtime ==========
        System.out.println("\n\n📋 SCENARIO 4: User re-subscribes to SMS");
        System.out.println("─".repeat(70));
        
        // Re-subscribe SMS observer
        transaction.attach(smsObserver);
        
        // Process another transaction
        // All 3 observers will be notified again
        transaction.processTransaction("TXN004", 7500.00, "SUCCESS", "Bill payment successful");
        
        
        // ========== SCENARIO 5: Only one observer ==========
        System.out.println("\n\n📋 SCENARIO 5: User keeps only Email notifications");
        System.out.println("─".repeat(70));
        
        // Unsubscribe all except email
        transaction.detach(smsObserver);
        transaction.detach(pushObserver);
        
        // Process transaction
        // Only Email will be notified
        transaction.processTransaction("TXN005", 1500.00, "SUCCESS", "Subscription renewed");
        
        
        System.out.println("\n" + "═".repeat(70));
        System.out.println("✓ All scenarios completed successfully!");
        System.out.println("═".repeat(70));
        
        
        // ========== KEY TAKEAWAYS ==========
        System.out.println("\n\n📚 KEY TAKEAWAYS:");
        System.out.println("─".repeat(70));
        System.out.println("1. ✓ One transaction can notify MULTIPLE observers automatically");
        System.out.println("2. ✓ Observers can be added/removed at RUNTIME");
        System.out.println("3. ✓ Transaction class doesn't know about specific notification types");
        System.out.println("4. ✓ Easy to add new notification channels (just implement interface)");
        System.out.println("5. ✓ Follows Open/Closed Principle (open for extension, closed for modification)");
        System.out.println("6. ✓ Loose coupling between Subject (Transaction) and Observers (Notifications)");
    }
}

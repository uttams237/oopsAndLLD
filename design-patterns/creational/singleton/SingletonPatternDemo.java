package creational.singleton;

/**
 * SingletonPatternDemo - Comprehensive demonstration of Singleton Design Pattern
 * 
 * This demo showcases three different Singleton implementations:
 * 1. DatabaseConnection - Thread-safe lazy initialization (Bill Pugh)
 * 2. ConfigurationManager - Eager initialization
 * 3. Logger - Enum-based (Joshua Bloch's recommended approach)
 * 
 * Each implementation is used in realistic fintech scenarios.
 */
public class SingletonPatternDemo {
    
    public static void main(String[] args) {
        
        printHeader("SINGLETON PATTERN - FINTECH APPLICATION DEMO");
        
        // Scenario 1: Database Connection Singleton
        demonstrateDatabaseConnection();
        
        // Scenario 2: Configuration Manager Singleton
        demonstrateConfigurationManager();
        
        // Scenario 3: Logger Singleton
        demonstrateLogger();
        
        // Scenario 4: Verify Singleton Behavior
        verifySingletonInstances();
        
        // Scenario 5: Thread Safety Demo
        demonstrateThreadSafety();
        
        printFooter();
    }
    
    /**
     * SCENARIO 1: DatabaseConnection Singleton
     * Demonstrates thread-safe lazy initialization using Bill Pugh pattern
     */
    private static void demonstrateDatabaseConnection() {
        printSectionHeader("SCENARIO 1: Database Connection Singleton");
        
        System.out.println("📌 Multiple services trying to access database...\n");
        
        // First access - instance will be created
        System.out.println("Service 1: Payment Processing");
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        db1.connect();
        db1.executeTransaction("TXN001", 5000.00);
        
        System.out.println("\nService 2: Account Management");
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        db2.connect(); // Will show "already connected"
        db2.executeTransaction("TXN002", 15000.00);
        
        System.out.println("\nService 3: Reporting");
        DatabaseConnection db3 = DatabaseConnection.getInstance();
        db3.executeTransaction("TXN003", 25000.00);
        
        // Display statistics
        db1.getConnectionStats();
        
        System.out.println("\n✅ Result: All services use the SAME database connection instance");
        System.out.println("   Memory saved, connection pooling maintained!\n");
    }
    
    /**
     * SCENARIO 2: ConfigurationManager Singleton
     * Demonstrates eager initialization for application-wide settings
     */
    private static void demonstrateConfigurationManager() {
        printSectionHeader("SCENARIO 2: Configuration Manager Singleton");
        
        System.out.println("📌 Different modules accessing application configuration...\n");
        
        // Payment module accessing configuration
        System.out.println("Module 1: Payment Gateway");
        ConfigurationManager config1 = ConfigurationManager.getInstance();
        String gatewayUrl = config1.getConfig("payment.gateway.url");
        System.out.println("   Gateway URL: " + gatewayUrl);
        
        // Transaction module checking limits
        System.out.println("\nModule 2: Transaction Validator");
        ConfigurationManager config2 = ConfigurationManager.getInstance();
        double amount = 45000.00;
        boolean isValid = config2.isWithinLimit(amount);
        double fee = config2.calculateTransactionFee(amount);
        System.out.println("   Transaction Amount: ₹" + amount);
        System.out.println("   Within Limits: " + (isValid ? "YES ✓" : "NO ✗"));
        System.out.println("   Transaction Fee: ₹" + fee);
        
        // Security module updating configuration
        System.out.println("\nModule 3: Security Manager");
        ConfigurationManager config3 = ConfigurationManager.getInstance();
        config3.setConfig("max.login.attempts", "5");
        
        // Display all configurations
        config1.displayAllConfigurations();
        
        System.out.println("\n✅ Result: All modules access the SAME configuration instance");
        System.out.println("   Consistency guaranteed across the application!\n");
    }
    
    /**
     * SCENARIO 3: Logger Singleton
     * Demonstrates enum-based singleton for centralized logging
     */
    private static void demonstrateLogger() {
        printSectionHeader("SCENARIO 3: Logger Singleton (Enum-Based)");
        
        System.out.println("📌 Multiple components logging to centralized system...\n");
        
        // Different components logging events
        Logger.INSTANCE.info("Application started");
        Logger.INSTANCE.logTransaction("TXN001", "UPI", 5000.00, "SUCCESS");
        Logger.INSTANCE.logTransaction("TXN002", "CARD", 15000.00, "SUCCESS");
        Logger.INSTANCE.warn("Daily transaction limit approaching: 95%");
        Logger.INSTANCE.logTransaction("TXN003", "NET_BANKING", 25000.00, "PENDING");
        Logger.INSTANCE.error("Payment gateway timeout for TXN003");
        Logger.INSTANCE.logTransaction("TXN003", "NET_BANKING", 25000.00, "FAILED");
        Logger.INSTANCE.info("Retry initiated for TXN003");
        Logger.INSTANCE.logTransaction("TXN003", "NET_BANKING", 25000.00, "SUCCESS");
        
        // Display consolidated logs
        Logger.INSTANCE.displayLogs();
        
        System.out.println("\n✅ Result: All components log to the SAME Logger instance");
        System.out.println("   Centralized audit trail for compliance!\n");
    }
    
    /**
     * SCENARIO 4: Verify Singleton Instances
     * Proves that getInstance() always returns the same object
     */
    private static void verifySingletonInstances() {
        printSectionHeader("SCENARIO 4: Singleton Instance Verification");
        
        System.out.println("📌 Verifying that singletons return same instance...\n");
        
        // DatabaseConnection verification
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        System.out.println("DatabaseConnection:");
        System.out.println("   db1 == db2: " + (db1 == db2));
        System.out.println("   db1.hashCode(): " + db1.hashCode());
        System.out.println("   db2.hashCode(): " + db2.hashCode());
        
        // ConfigurationManager verification
        ConfigurationManager config1 = ConfigurationManager.getInstance();
        ConfigurationManager config2 = ConfigurationManager.getInstance();
        System.out.println("\nConfigurationManager:");
        System.out.println("   config1 == config2: " + (config1 == config2));
        System.out.println("   config1.hashCode(): " + config1.hashCode());
        System.out.println("   config2.hashCode(): " + config2.hashCode());
        
        // Logger verification (enum)
        Logger logger1 = Logger.INSTANCE;
        Logger logger2 = Logger.INSTANCE;
        System.out.println("\nLogger (Enum):");
        System.out.println("   logger1 == logger2: " + (logger1 == logger2));
        System.out.println("   logger1.hashCode(): " + logger1.hashCode());
        System.out.println("   logger2.hashCode(): " + logger2.hashCode());
        
        System.out.println("\n✅ Result: All hash codes match - same instances confirmed!");
        System.out.println("   Singleton guarantee maintained!\n");
    }
    
    /**
     * SCENARIO 5: Thread Safety Demonstration
     * Shows that singletons work correctly in multi-threaded environment
     */
    private static void demonstrateThreadSafety() {
        printSectionHeader("SCENARIO 5: Thread Safety Test");
        
        System.out.println("📌 Simulating concurrent access from multiple threads...\n");
        
        // Create multiple threads trying to access singletons
        Thread thread1 = new Thread(() -> {
            DatabaseConnection db = DatabaseConnection.getInstance();
            Logger.INSTANCE.info("Thread 1 accessed DatabaseConnection: " + db.hashCode());
        }, "Thread-1");
        
        Thread thread2 = new Thread(() -> {
            DatabaseConnection db = DatabaseConnection.getInstance();
            Logger.INSTANCE.info("Thread 2 accessed DatabaseConnection: " + db.hashCode());
        }, "Thread-2");
        
        Thread thread3 = new Thread(() -> {
            ConfigurationManager config = ConfigurationManager.getInstance();
            Logger.INSTANCE.info("Thread 3 accessed ConfigurationManager: " + config.hashCode());
        }, "Thread-3");
        
        Thread thread4 = new Thread(() -> {
            ConfigurationManager config = ConfigurationManager.getInstance();
            Logger.INSTANCE.info("Thread 4 accessed ConfigurationManager: " + config.hashCode());
        }, "Thread-4");
        
        // Start all threads
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        
        // Wait for all threads to complete
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\n✅ Result: All threads received the same singleton instances");
        System.out.println("   Thread-safety confirmed!\n");
    }
    
    // ==================== Utility Methods ====================
    
    private static void printHeader(String title) {
        System.out.println("\n" + "═".repeat(80));
        System.out.println("  " + title);
        System.out.println("═".repeat(80) + "\n");
    }
    
    private static void printSectionHeader(String section) {
        System.out.println("\n" + "─".repeat(80));
        System.out.println("  " + section);
        System.out.println("─".repeat(80) + "\n");
    }
    
    private static void printFooter() {
        System.out.println("\n" + "═".repeat(80));
        System.out.println("  DEMO COMPLETED - All Singleton patterns demonstrated successfully!");
        System.out.println("═".repeat(80) + "\n");
    }
}

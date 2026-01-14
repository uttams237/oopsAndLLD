package creational.singleton;

/**
 * DatabaseConnection - Thread-Safe Lazy Initialization Singleton
 * 
 * This class demonstrates the Bill Pugh Singleton Design - the most widely recommended approach.
 * It provides lazy initialization and is thread-safe without using synchronized blocks.
 * 
 * In fintech applications, database connections are expensive resources that should be reused.
 * This singleton ensures only ONE connection pool exists throughout the application lifecycle.
 * 
 * Pattern: Bill Pugh Singleton (inner static helper class)
 * Thread-Safety: Yes (JVM guarantees thread safety for static inner class loading)
 * Lazy Loading: Yes (loaded only when getInstance() is called)
 */
public class DatabaseConnection {
    
    // Private field to store connection details
    private String connectionString;
    private boolean isConnected;
    private int activeConnections;
    
    /**
     * Private constructor prevents direct instantiation.
     * This is the KEY characteristic of the Singleton pattern.
     */
    private DatabaseConnection() {
        // Simulate database connection initialization
        this.connectionString = "jdbc:postgresql://fintech-db.example.com:5432/transactions";
        this.isConnected = false;
        this.activeConnections = 0;
        
        System.out.println("🔌 DatabaseConnection instance created");
        System.out.println("   Connection String: " + maskConnectionString(connectionString));
    }
    
    /**
     * Bill Pugh Singleton Design: Inner Static Helper Class
     * 
     * The SingletonHelper class is loaded ONLY when getInstance() is called.
     * The JVM guarantees that static inner classes are loaded in a thread-safe manner.
     * This eliminates the need for synchronized blocks.
     */
    private static class SingletonHelper {
        // The INSTANCE is created when SingletonHelper is loaded
        private static final DatabaseConnection INSTANCE = new DatabaseConnection();
    }
    
    /**
     * Public static method to get the singleton instance.
     * This is the ONLY way to access the DatabaseConnection object.
     * 
     * @return The single instance of DatabaseConnection
     */
    public static DatabaseConnection getInstance() {
        return SingletonHelper.INSTANCE;
    }
    
    /**
     * Simulate connecting to the database
     */
    public void connect() {
        if (!isConnected) {
            System.out.println("✅ Establishing database connection...");
            this.isConnected = true;
            System.out.println("   Status: CONNECTED");
        } else {
            System.out.println("ℹ️  Already connected to database");
        }
    }
    
    /**
     * Simulate executing a fintech transaction query
     */
    public void executeTransaction(String transactionId, double amount) {
        if (!isConnected) {
            System.out.println("❌ Error: Not connected to database");
            return;
        }
        
        activeConnections++;
        System.out.println("💳 Executing Transaction:");
        System.out.println("   Transaction ID: " + transactionId);
        System.out.println("   Amount: ₹" + amount);
        System.out.println("   Active Connections: " + activeConnections);
    }
    
    /**
     * Get connection statistics
     */
    public void getConnectionStats() {
        System.out.println("\n📊 Database Connection Statistics:");
        System.out.println("   Status: " + (isConnected ? "CONNECTED" : "DISCONNECTED"));
        System.out.println("   Total Queries Executed: " + activeConnections);
        System.out.println("   Connection String: " + maskConnectionString(connectionString));
    }
    
    /**
     * Mask sensitive connection details for security
     */
    private String maskConnectionString(String conn) {
        if (conn.length() > 20) {
            return conn.substring(0, 20) + "***";
        }
        return conn;
    }
    
    /**
     * Prevent cloning of the singleton instance
     * Important for maintaining singleton property
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone singleton instance");
    }
}

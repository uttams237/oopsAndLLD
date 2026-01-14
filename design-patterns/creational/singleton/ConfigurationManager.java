package creational.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * ConfigurationManager - Eager Initialization Singleton
 * 
 * This class demonstrates Eager Initialization - the simplest form of Singleton.
 * The instance is created at class loading time, before any thread can access it.
 * 
 * In fintech applications, configuration settings (like tax rates, API keys, transaction limits)
 * need to be accessed globally and should remain consistent across the application.
 * 
 * Pattern: Eager Initialization
 * Thread-Safety: Yes (instance created at class loading time)
 * Lazy Loading: No (instance created even if never used)
 */
public class ConfigurationManager {
    
    // Eager initialization: instance created immediately when class is loaded
    private static final ConfigurationManager INSTANCE = new ConfigurationManager();
    
    // Configuration data storage
    private Map<String, String> configurations;
    
    /**
     * Private constructor prevents direct instantiation.
     * Initializes default fintech configurations.
     */
    private ConfigurationManager() {
        configurations = new HashMap<>();
        loadDefaultConfigurations();
        System.out.println("⚙️  ConfigurationManager instance created (Eager Initialization)");
    }
    
    /**
     * Public static method to get the singleton instance.
     * 
     * @return The single instance of ConfigurationManager
     */
    public static ConfigurationManager getInstance() {
        return INSTANCE;
    }
    
    /**
     * Load default fintech application configurations
     */
    private void loadDefaultConfigurations() {
        // Payment gateway settings
        configurations.put("payment.gateway.url", "https://api.razorpay.com/v1");
        configurations.put("payment.gateway.key", "rzp_live_***MASKED***");
        
        // Transaction limits
        configurations.put("transaction.daily.limit", "100000");
        configurations.put("transaction.single.limit", "50000");
        configurations.put("transaction.min.amount", "1");
        
        // Tax and fee settings
        configurations.put("tax.gst.rate", "18");
        configurations.put("transaction.fee.percent", "0.5");
        configurations.put("transaction.fee.max", "500");
        
        // Security settings
        configurations.put("session.timeout.minutes", "30");
        configurations.put("max.login.attempts", "3");
        configurations.put("otp.expiry.minutes", "5");
        
        // Database settings
        configurations.put("db.pool.size", "20");
        configurations.put("db.connection.timeout", "30");
    }
    
    /**
     * Get a configuration value by key
     */
    public String getConfig(String key) {
        return configurations.getOrDefault(key, "CONFIGURATION_NOT_FOUND");
    }
    
    /**
     * Get a configuration value as integer
     */
    public int getConfigAsInt(String key) {
        String value = getConfig(key);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    /**
     * Get a configuration value as double
     */
    public double getConfigAsDouble(String key) {
        String value = getConfig(key);
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
    
    /**
     * Set or update a configuration value
     */
    public void setConfig(String key, String value) {
        String oldValue = configurations.put(key, value);
        System.out.println("🔧 Configuration updated:");
        System.out.println("   Key: " + key);
        System.out.println("   Old Value: " + (oldValue != null ? oldValue : "N/A"));
        System.out.println("   New Value: " + value);
    }
    
    /**
     * Display all configurations (masked for security)
     */
    public void displayAllConfigurations() {
        System.out.println("\n⚙️  Application Configurations:");
        System.out.println("═".repeat(50));
        
        for (Map.Entry<String, String> entry : configurations.entrySet()) {
            String displayValue = entry.getValue();
            
            // Mask sensitive values
            if (entry.getKey().contains("key") || entry.getKey().contains("password")) {
                displayValue = "***MASKED***";
            }
            
            System.out.println("   " + entry.getKey() + " = " + displayValue);
        }
        System.out.println("═".repeat(50));
    }
    
    /**
     * Calculate transaction fee based on configuration
     */
    public double calculateTransactionFee(double amount) {
        double feePercent = getConfigAsDouble("transaction.fee.percent");
        double maxFee = getConfigAsDouble("transaction.fee.max");
        
        double calculatedFee = (amount * feePercent) / 100;
        return Math.min(calculatedFee, maxFee);
    }
    
    /**
     * Check if transaction is within limits
     */
    public boolean isWithinLimit(double amount) {
        double singleLimit = getConfigAsDouble("transaction.single.limit");
        double minAmount = getConfigAsDouble("transaction.min.amount");
        
        return amount >= minAmount && amount <= singleLimit;
    }
    
    /**
     * Prevent cloning of the singleton instance
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone singleton instance");
    }
}

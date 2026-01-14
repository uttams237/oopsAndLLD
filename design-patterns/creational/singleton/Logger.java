package creational.singleton;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Logger - Enum-Based Singleton (Joshua Bloch's Approach)
 * 
 * This is the BEST way to implement Singleton in Java according to Joshua Bloch
 * (author of "Effective Java"). Enums are inherently serializable and provide
 * guaranteed protection against multiple instantiation.
 * 
 * In fintech applications, logging is critical for:
 * - Audit trails
 * - Compliance requirements
 * - Debugging transaction issues
 * - Security monitoring
 * 
 * Pattern: Enum Singleton
 * Thread-Safety: Yes (enum guarantee by JVM)
 * Serialization-Safe: Yes (automatically)
 * Reflection-Safe: Yes (enum cannot be instantiated via reflection)
 */
public enum Logger {
    
    // The single instance (this is all you need!)
    INSTANCE;
    
    // Logger state
    private List<String> logEntries;
    private DateTimeFormatter timeFormatter;
    
    /**
     * Enum constructor - called only once when enum is initialized
     */
    Logger() {
        logEntries = new ArrayList<>();
        timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("📝 Logger instance created (Enum Singleton)");
    }
    
    /**
     * Log an INFO level message
     */
    public void info(String message) {
        log("INFO", message);
    }
    
    /**
     * Log a WARNING level message
     */
    public void warn(String message) {
        log("WARN", message);
    }
    
    /**
     * Log an ERROR level message
     */
    public void error(String message) {
        log("ERROR", message);
    }
    
    /**
     * Log a transaction event
     */
    public void logTransaction(String transactionId, String type, double amount, String status) {
        String message = String.format(
            "Transaction[ID=%s, Type=%s, Amount=₹%.2f, Status=%s]",
            transactionId, type, amount, status
        );
        log("TRANSACTION", message);
    }
    
    /**
     * Internal logging method
     */
    private void log(String level, String message) {
        String timestamp = LocalDateTime.now().format(timeFormatter);
        String logEntry = String.format("[%s] [%s] %s", timestamp, level, message);
        
        logEntries.add(logEntry);
        System.out.println(logEntry);
    }
    
    /**
     * Display all log entries
     */
    public void displayLogs() {
        System.out.println("\n" + "═".repeat(80));
        System.out.println("📋 TRANSACTION AUDIT LOG");
        System.out.println("═".repeat(80));
        
        if (logEntries.isEmpty()) {
            System.out.println("No log entries found.");
        } else {
            for (int i = 0; i < logEntries.size(); i++) {
                System.out.println((i + 1) + ". " + logEntries.get(i));
            }
        }
        
        System.out.println("═".repeat(80));
        System.out.println("Total Entries: " + logEntries.size());
        System.out.println("═".repeat(80));
    }
    
    /**
     * Get total number of log entries
     */
    public int getLogCount() {
        return logEntries.size();
    }
    
    /**
     * Clear all logs (use with caution in production!)
     */
    public void clearLogs() {
        int count = logEntries.size();
        logEntries.clear();
        System.out.println("🗑️  Cleared " + count + " log entries");
    }
    
    /**
     * Get logs by level
     */
    public List<String> getLogsByLevel(String level) {
        List<String> filteredLogs = new ArrayList<>();
        for (String entry : logEntries) {
            if (entry.contains("[" + level + "]")) {
                filteredLogs.add(entry);
            }
        }
        return filteredLogs;
    }
    
    /**
     * Export logs to a formatted string (simulating file export)
     */
    public String exportLogs() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== FINTECH TRANSACTION LOG EXPORT ===\n");
        sb.append("Export Time: ").append(LocalDateTime.now().format(timeFormatter)).append("\n");
        sb.append("Total Entries: ").append(logEntries.size()).append("\n\n");
        
        for (String entry : logEntries) {
            sb.append(entry).append("\n");
        }
        
        return sb.toString();
    }
}

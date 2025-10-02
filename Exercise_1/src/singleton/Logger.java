package singleton;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Logger class implementing Singleton pattern
 * Thread-safe implementation for application logging
 */
public class Logger {
    // Static instance variable (lazy initialization)
    private static Logger instance;
    
    // Date formatter for timestamps
    private final DateTimeFormatter formatter;
    
    // Private constructor prevents external instantiation
    private Logger() {
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("[SYSTEM] Logger instance created at " + getCurrentTimestamp());
    }
    
    /**
     * Thread-safe getInstance method using synchronized block
     * @return Single instance of Logger
     */
    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }
    
    /**
     * Logs a message with timestamp and exception handling
     * @param message The message to log
     */
    public void logMessage(String message) {
        try {
            // Exception handling for null messages
            if (message == null) {
                throw new IllegalArgumentException("Log message cannot be null");
            }
            
            String timestamp = getCurrentTimestamp();
            System.out.println("[" + timestamp + "] " + message);
            
        } catch (IllegalArgumentException e) {
            System.err.println("[ERROR] " + getCurrentTimestamp() + " - " + e.getMessage());
        } catch (Exception e) {
            System.err.println("[ERROR] " + getCurrentTimestamp() + " - Unexpected error while logging: " + e.getMessage());
        }
    }
    
    /**
     * Logs an error message with exception details
     * @param message Error message
     * @param exception The exception that occurred
     */
    public void logError(String message, Exception exception) {
        try {
            String timestamp = getCurrentTimestamp();
            System.err.println("[ERROR] [" + timestamp + "] " + message + " - " + exception.getMessage());
        } catch (Exception e) {
            System.err.println("[CRITICAL] Failed to log error: " + e.getMessage());
        }
    }
    
    /**
     * Gets current timestamp as formatted string
     * @return Formatted timestamp
     */
    private String getCurrentTimestamp() {
        return LocalDateTime.now().format(formatter);
    }
}


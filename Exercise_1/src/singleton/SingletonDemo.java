package singleton;

/**
 * Demonstration of Singleton Pattern
 * Shows Logger service usage with exception handling
 */
public class SingletonDemo {
    
    public static void main(String[] args) {
        System.out.println("=== SINGLETON PATTERN DEMO ===");
        System.out.println("Demonstrating Logger Service (Singleton Pattern)\n");
        
        try {
            // Get first instance
            Logger logger1 = Logger.getInstance();
            logger1.logMessage("First logger instance obtained");
            
            // Get second instance - should be the same object
            Logger logger2 = Logger.getInstance();
            logger2.logMessage("Second logger instance obtained");
            
            // Verify both references point to same instance
            System.out.println("Are both logger instances the same object? " + (logger1 == logger2));
            
            // Test normal logging
            logger1.logMessage("This is a normal log message");
            logger2.logMessage("This message is from the 'second' logger instance");
            
            // Test exception handling - null message
            System.out.println("\n--- Testing Exception Handling ---");
            logger1.logMessage(null);  // This will trigger exception handling
            
            // Test error logging
            try {
                // Simulate an operation that might fail
                @SuppressWarnings("unused")
                int result = 10 / 0;  // This will cause ArithmeticException
            } catch (ArithmeticException e) {
                logger1.logError("Division by zero occurred", e);
            }
            
            // More normal logging
            logger1.logMessage("Singleton demo completed successfully");
            
        } catch (Exception e) {
            System.err.println("Unexpected error in singleton demo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


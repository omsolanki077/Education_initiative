package factory;

import singleton.Logger;

/**
 * Demonstration of Factory Pattern
 * Shows notification creation and sending with exception handling
 */
public class FactoryDemo {
    
    public static void main(String[] args) {
        System.out.println("\n=== FACTORY PATTERN DEMO ===");
        System.out.println("Demonstrating Notification Factory (Factory Pattern)\n");
        
        Logger logger = Logger.getInstance();
        NotificationFactory factory = new NotificationFactory();
        
        try {
            // Create and send different notification types
            logger.logMessage("Starting factory pattern demonstration");
            
            // Test Email notification
            System.out.println("--- Creating Email Notification ---");
            Notification emailNotification = factory.createNotification(NotificationFactory.NotificationType.EMAIL);
            emailNotification.send();
            
            System.out.println("\n--- Creating SMS Notification ---");
            Notification smsNotification = factory.createNotification(NotificationFactory.NotificationType.SMS);
            smsNotification.send();
            
            System.out.println("\n--- Creating Push Notification ---");
            Notification pushNotification = factory.createNotification(NotificationFactory.NotificationType.PUSH);
            pushNotification.send();
            
            // Test string-based creation
            System.out.println("\n--- Creating Notification from String ---");
            Notification emailFromString = factory.createNotification("email");
            System.out.println("Created notification type: " + emailFromString.getType());
            emailFromString.send();
            
            // Test exception handling - invalid notification type
            System.out.println("\n--- Testing Exception Handling ---");
            try {
                Notification invalidNotification = factory.createNotification("INVALID_TYPE");
                invalidNotification.send();
            } catch (NotificationException e) {
                System.out.println("Caught expected exception: " + e.getMessage());
                logger.logError("Expected exception for invalid type", e);
            }
            
            // Test null type handling
            try {
                Notification nullNotification = factory.createNotification((String) null);
                nullNotification.send();
            } catch (NotificationException e) {
                System.out.println("Caught expected exception for null type: " + e.getMessage());
                logger.logError("Expected exception for null type", e);
            }
            
            logger.logMessage("Factory pattern demonstration completed successfully");
            
        } catch (NotificationException e) {
            System.err.println("Notification error: " + e.getMessage());
            logger.logError("Notification error in factory demo", e);
        } catch (Exception e) {
            System.err.println("Unexpected error in factory demo: " + e.getMessage());
            logger.logError("Unexpected error in factory demo", e);
            e.printStackTrace();
        }
    }
}


package factory;

import singleton.Logger;

/**
 * Factory class for creating different types of notifications
 */
public class NotificationFactory {
    private final Logger logger;
    
    // Enum for notification types - provides type safety
    public enum NotificationType {
        EMAIL, SMS, PUSH
    }
    
    public NotificationFactory() {
        this.logger = Logger.getInstance();
    }
    
    /**
     * Creates notification based on type
     * @param type The type of notification to create
     * @return Notification instance
     * @throws NotificationException if invalid type is provided
     */
    public Notification createNotification(NotificationType type) throws NotificationException {
        try {
            if (type == null) {
                throw new IllegalArgumentException("Notification type cannot be null");
            }
            
            logger.logMessage("Creating notification of type: " + type);
            
            switch (type) {
                case EMAIL:
                    return new EmailNotification();
                case SMS:
                    return new SMSNotification();
                case PUSH:
                    return new PushNotification();
                default:
                    throw new NotificationException("Unsupported notification type: " + type);
            }
            
        } catch (IllegalArgumentException e) {
            logger.logError("Invalid notification type provided", e);
            throw new NotificationException("Invalid notification type", e);
        } catch (Exception e) {
            logger.logError("Failed to create notification", e);
            throw new NotificationException("Failed to create notification of type: " + type, e);
        }
    }
    
    /**
     * Creates notification based on string type (for demonstration)
     * @param typeString String representation of notification type
     * @return Notification instance
     * @throws NotificationException if invalid type is provided
     */
    public Notification createNotification(String typeString) throws NotificationException {
        try {
            if (typeString == null || typeString.trim().isEmpty()) {
                throw new IllegalArgumentException("Notification type string cannot be null or empty");
            }
            
            NotificationType type = NotificationType.valueOf(typeString.toUpperCase().trim());
            return createNotification(type);
            
        } catch (IllegalArgumentException e) {
            logger.logError("Invalid notification type string: " + typeString, e);
            throw new NotificationException("Invalid notification type: " + typeString + 
                ". Valid types are: EMAIL, SMS, PUSH", e);
        }
    }
}


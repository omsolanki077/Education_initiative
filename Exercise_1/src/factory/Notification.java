package factory;

/**
 * Base interface for all notification types
 */
public interface Notification {
    /**
     * Sends the notification
     * @throws NotificationException if sending fails
     */
    void send() throws NotificationException;
    
    /**
     * Gets the notification type
     * @return String representation of notification type
     */
    String getType();
}


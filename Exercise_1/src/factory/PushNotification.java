package factory;

import singleton.Logger;

/**
 * Push notification implementation
 */
public class PushNotification implements Notification {
    private final Logger logger;
    
    public PushNotification() {
        this.logger = Logger.getInstance();
    }
    
    @Override
    public void send() throws NotificationException {
        try {
            logger.logMessage("Preparing push notification...");
            
            // Simulate push notification sending process
            Thread.sleep(60); // Simulate network delay
            
            System.out.println("Sending Push Notification...");
            System.out.println("   Push notification sent successfully!");
            
            logger.logMessage("Push notification completed");
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new NotificationException("Push notification sending was interrupted", e);
        } catch (Exception e) {
            logger.logError("Failed to send push notification", e);
            throw new NotificationException("Failed to send push notification", e);
        }
    }
    
    @Override
    public String getType() {
        return "PUSH";
    }
}


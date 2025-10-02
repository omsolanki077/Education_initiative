package factory;

import singleton.Logger;

/**
 * SMS notification implementation
 */
public class SMSNotification implements Notification {
    private final Logger logger;
    
    public SMSNotification() {
        this.logger = Logger.getInstance();
    }
    
    @Override
    public void send() throws NotificationException {
        try {
            logger.logMessage("Preparing SMS notification...");
            
            // Simulate SMS sending process
            Thread.sleep(80); // Simulate network delay
            
            System.out.println("Sending SMS...");
            System.out.println("   SMS notification sent successfully!");
            
            logger.logMessage("SMS notification completed");
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new NotificationException("SMS sending was interrupted", e);
        } catch (Exception e) {
            logger.logError("Failed to send SMS notification", e);
            throw new NotificationException("Failed to send SMS notification", e);
        }
    }
    
    @Override
    public String getType() {
        return "SMS";
    }
}


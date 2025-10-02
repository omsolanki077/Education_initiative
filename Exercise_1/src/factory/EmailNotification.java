package factory;

import singleton.Logger;

/**
 * Email notification implementation
 */
public class EmailNotification implements Notification {
    private final Logger logger;
    
    public EmailNotification() {
        this.logger = Logger.getInstance();
    }
    
    @Override
    public void send() throws NotificationException {
        try {
            logger.logMessage("Preparing email notification...");
            
            // Simulate email sending process
            Thread.sleep(100); // Simulate network delay
            
            System.out.println("Sending Email...");
            System.out.println("   Email notification sent successfully!");
            
            logger.logMessage("Email notification completed");
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new NotificationException("Email sending was interrupted", e);
        } catch (Exception e) {
            logger.logError("Failed to send email notification", e);
            throw new NotificationException("Failed to send email notification", e);
        }
    }
    
    @Override
    public String getType() {
        return "EMAIL";
    }
}


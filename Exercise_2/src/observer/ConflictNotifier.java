package observer;

import util.Logger;

/**
 * Concrete observer that handles schedule conflict notifications
 */
public class ConflictNotifier implements ConflictObserver {
    private final String observerId;
    private final Logger logger;
    
    public ConflictNotifier(String observerId) {
        if (observerId == null || observerId.trim().isEmpty()) {
            throw new IllegalArgumentException("Observer ID cannot be null or empty");
        }
        
        this.observerId = observerId.trim();
        this.logger = Logger.getInstance();
        
        logger.logMessage("ConflictNotifier created: " + this.observerId);
    }
    
    @Override
    public void update(String message) {
        try {
            if (message == null || message.trim().isEmpty()) {
                logger.logWarning("Received empty conflict notification");
                return;
            }
            
            // Display conflict notification
            System.out.println();
            System.out.println("*** SCHEDULE CONFLICT DETECTED ***");
            System.out.println("Notifier: " + observerId);
            System.out.println("Message: " + message);
            System.out.println("Action: Task was NOT added to schedule");
            System.out.println("*********************************");
            System.out.println();
            
            // Log the conflict
            logger.logWarning("Schedule conflict detected: " + message);
            
        } catch (Exception e) {
            logger.logError("Failed to process conflict notification", e);
        }
    }
    
    @Override
    public String getObserverId() {
        return observerId;
    }
    
    @Override
    public String toString() {
        return "ConflictNotifier{id='" + observerId + "'}";
    }
}

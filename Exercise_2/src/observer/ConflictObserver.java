package observer;

/**
 * Observer interface for conflict notifications
 */
public interface ConflictObserver {
    /**
     * Called when a schedule conflict is detected
     * @param message The conflict message
     */
    void update(String message);
    
    /**
     * Gets the observer's identifier
     * @return String identifier for this observer
     */
    String getObserverId();
}

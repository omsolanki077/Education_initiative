package observer;

/**
 * Observer interface for Observer pattern
 */
public interface Observer {
    /**
     * Called when the subject's state changes
     * @param subject The subject that changed
     * @param data Additional data about the change
     * @throws ObserverException if update processing fails
     */
    void update(Subject subject, Object data) throws ObserverException;
    
    /**
     * Gets the observer's identifier
     * @return String identifier for this observer
     */
    String getObserverId();
}


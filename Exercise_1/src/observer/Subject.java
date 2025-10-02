package observer;

/**
 * Subject interface for Observer pattern
 */
public interface Subject {
    /**
     * Registers an observer to receive notifications
     * @param observer The observer to register
     * @throws ObserverException if registration fails
     */
    void registerObserver(Observer observer) throws ObserverException;
    
    /**
     * Removes an observer from notifications
     * @param observer The observer to remove
     * @throws ObserverException if removal fails
     */
    void removeObserver(Observer observer) throws ObserverException;
    
    /**
     * Notifies all registered observers of changes
     * @throws ObserverException if notification fails
     */
    void notifyObservers() throws ObserverException;
}


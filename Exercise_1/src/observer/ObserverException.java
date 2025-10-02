package observer;

/**
 * Custom exception for observer pattern related errors
 */
public class ObserverException extends Exception {
    
    public ObserverException(String message) {
        super(message);
    }
    
    public ObserverException(String message, Throwable cause) {
        super(message, cause);
    }
}


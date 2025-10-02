package factory;

/**
 * Custom exception for vehicle-related errors
 */
public class VehicleException extends Exception {
    
    public VehicleException(String message) {
        super(message);
    }
    
    public VehicleException(String message, Throwable cause) {
        super(message, cause);
    }
}

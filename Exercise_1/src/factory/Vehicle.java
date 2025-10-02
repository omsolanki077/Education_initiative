package factory;

/**
 * Abstract base class for all vehicle types
 * Defines common vehicle properties and behavior
 */
public abstract class Vehicle {
    protected String model;
    protected String color;
    protected String vehicleType;
    protected int maxSpeed;
    protected int fuelCapacity;
    
    public Vehicle(String model, String color, String vehicleType, int maxSpeed, int fuelCapacity) {
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Vehicle model cannot be null or empty");
        }
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Vehicle color cannot be null or empty");
        }
        if (maxSpeed <= 0) {
            throw new IllegalArgumentException("Max speed must be positive");
        }
        if (fuelCapacity <= 0) {
            throw new IllegalArgumentException("Fuel capacity must be positive");
        }
        
        this.model = model.trim();
        this.color = color.trim();
        this.vehicleType = vehicleType;
        this.maxSpeed = maxSpeed;
        this.fuelCapacity = fuelCapacity;
    }
    
    /**
     * Abstract method to start the vehicle
     */
    public abstract void start();
    
    /**
     * Abstract method to stop the vehicle
     */
    public abstract void stop();
    
    /**
     * Abstract method to get vehicle specifications
     */
    public abstract void displaySpecifications();
    
    /**
     * Gets the fuel efficiency rating
     * @return Fuel efficiency rating
     */
    public abstract String getFuelEfficiency();
    
    // Getters
    public String getModel() {
        return model;
    }
    
    public String getColor() {
        return color;
    }
    
    public String getVehicleType() {
        return vehicleType;
    }
    
    public int getMaxSpeed() {
        return maxSpeed;
    }
    
    public int getFuelCapacity() {
        return fuelCapacity;
    }
    
    @Override
    public String toString() {
        return String.format("%s %s (%s) - Max Speed: %d km/h, Fuel Capacity: %d L", 
            vehicleType, model, color, maxSpeed, fuelCapacity);
    }
}

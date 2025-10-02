package factory;

import singleton.Logger;
import java.util.ArrayList;
import java.util.List;

/**
 * Factory class for creating different types of vehicles
 * Follows Factory Pattern for vehicle creation
 */
public class VehicleFactory {
    private final Logger logger;
    
    // Enum for vehicle types - provides type safety
    public enum VehicleType {
        CAR, TRUCK, MOTORCYCLE
    }
    
    public VehicleFactory() {
        this.logger = Logger.getInstance();
    }
    
    /**
     * Creates a vehicle based on the specified type
     * @param type The type of vehicle to create
     * @param model The model of the vehicle
     * @param color The color of the vehicle
     * @return Vehicle instance
     * @throws VehicleException if vehicle creation fails
     */
    public Vehicle createVehicle(VehicleType type, String model, String color) throws VehicleException {
        try {
            if (type == null) {
                throw new IllegalArgumentException("Vehicle type cannot be null");
            }
            if (model == null || model.trim().isEmpty()) {
                throw new IllegalArgumentException("Vehicle model cannot be null or empty");
            }
            if (color == null || color.trim().isEmpty()) {
                throw new IllegalArgumentException("Vehicle color cannot be null or empty");
            }
            
            logger.logMessage("Creating vehicle of type: " + type + " - " + model);
            
            switch (type) {
                case CAR:
                    return new Car(model, color);
                case TRUCK:
                    return new Truck(model, color);
                case MOTORCYCLE:
                    return new Motorcycle(model, color);
                default:
                    throw new VehicleException("Unsupported vehicle type: " + type);
            }
            
        } catch (IllegalArgumentException e) {
            logger.logError("Invalid vehicle parameters", e);
            throw new VehicleException("Invalid vehicle parameters: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.logError("Failed to create vehicle", e);
            throw new VehicleException("Failed to create vehicle of type: " + type, e);
        }
    }
    
    /**
     * Creates a vehicle based on string type (for user input)
     * @param typeString String representation of vehicle type
     * @param model The model of the vehicle
     * @param color The color of the vehicle
     * @return Vehicle instance
     * @throws VehicleException if vehicle creation fails
     */
    public Vehicle createVehicle(String typeString, String model, String color) throws VehicleException {
        try {
            if (typeString == null || typeString.trim().isEmpty()) {
                throw new IllegalArgumentException("Vehicle type string cannot be null or empty");
            }
            
            VehicleType type = VehicleType.valueOf(typeString.toUpperCase().trim());
            return createVehicle(type, model, color);
            
        } catch (IllegalArgumentException e) {
            logger.logError("Invalid vehicle type string: " + typeString, e);
            throw new VehicleException("Invalid vehicle type: " + typeString + 
                ". Valid types are: CAR, TRUCK, MOTORCYCLE", e);
        }
    }
    
    /**
     * Gets all available vehicle types
     * @return Array of available vehicle types
     */
    public VehicleType[] getAvailableVehicleTypes() {
        return VehicleType.values();
    }
    
    /**
     * Gets vehicle type names as strings
     * @return Array of vehicle type names
     */
    public String[] getVehicleTypeNames() {
        VehicleType[] types = VehicleType.values();
        String[] names = new String[types.length];
        for (int i = 0; i < types.length; i++) {
            names[i] = types[i].name();
        }
        return names;
    }
    
    /**
     * Creates a fleet of vehicles
     * @param specifications List of vehicle specifications
     * @return List of created vehicles
     * @throws VehicleException if any vehicle creation fails
     */
    public List<Vehicle> createFleet(List<VehicleSpecification> specifications) throws VehicleException {
        List<Vehicle> fleet = new ArrayList<>();
        
        try {
            logger.logMessage("Creating vehicle fleet with " + specifications.size() + " vehicles");
            
            for (VehicleSpecification spec : specifications) {
                Vehicle vehicle = createVehicle(spec.getType(), spec.getModel(), spec.getColor());
                fleet.add(vehicle);
            }
            
            logger.logMessage("Fleet created successfully with " + fleet.size() + " vehicles");
            return fleet;
            
        } catch (Exception e) {
            logger.logError("Failed to create vehicle fleet", e);
            throw new VehicleException("Failed to create vehicle fleet", e);
        }
    }
    
    /**
     * Inner class for vehicle specifications
     */
    public static class VehicleSpecification {
        private final VehicleType type;
        private final String model;
        private final String color;
        
        public VehicleSpecification(VehicleType type, String model, String color) {
            this.type = type;
            this.model = model;
            this.color = color;
        }
        
        public VehicleType getType() { return type; }
        public String getModel() { return model; }
        public String getColor() { return color; }
    }
}

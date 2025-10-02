package factory;

/**
 * Motorcycle implementation of Vehicle
 * Represents a two-wheeled motorcycle
 */
public class Motorcycle extends Vehicle {
    
    public Motorcycle(String model, String color) {
        super(model, color, "Motorcycle", 300, 20); // Max speed 300 km/h, 20L fuel capacity
    }
    
    @Override
    public void start() {
        System.out.println("🏍️ Motorcycle " + model + " started with kick start");
    }
    
    @Override
    public void stop() {
        System.out.println("🏍️ Motorcycle " + model + " stopped and engine turned off");
    }
    
    @Override
    public void displaySpecifications() {
        System.out.println("MOTORCYCLE SPECIFICATIONS:");
        System.out.println("  Model: " + model);
        System.out.println("  Color: " + color);
        System.out.println("  Type: " + vehicleType);
        System.out.println("  Max Speed: " + maxSpeed + " km/h");
        System.out.println("  Fuel Capacity: " + fuelCapacity + " L");
        System.out.println("  Fuel Efficiency: " + getFuelEfficiency());
        System.out.println("  Features: 2 wheels, handlebars, manual transmission");
    }
    
    @Override
    public String getFuelEfficiency() {
        return "4.2 L/100km (City), 3.8 L/100km (Highway)";
    }
}

package factory;

/**
 * Truck implementation of Vehicle
 * Represents a heavy-duty truck
 */
public class Truck extends Vehicle {
    
    public Truck(String model, String color) {
        super(model, color, "Truck", 120, 200); // Max speed 120 km/h, 200L fuel capacity
    }
    
    @Override
    public void start() {
        System.out.println("🚛 Truck " + model + " started with diesel engine");
    }
    
    @Override
    public void stop() {
        System.out.println("🚛 Truck " + model + " stopped and diesel engine turned off");
    }
    
    @Override
    public void displaySpecifications() {
        System.out.println("TRUCK SPECIFICATIONS:");
        System.out.println("  Model: " + model);
        System.out.println("  Color: " + color);
        System.out.println("  Type: " + vehicleType);
        System.out.println("  Max Speed: " + maxSpeed + " km/h");
        System.out.println("  Fuel Capacity: " + fuelCapacity + " L");
        System.out.println("  Fuel Efficiency: " + getFuelEfficiency());
        System.out.println("  Features: 6+ wheels, air brakes, cargo bed");
    }
    
    @Override
    public String getFuelEfficiency() {
        return "15.2 L/100km (City), 12.8 L/100km (Highway)";
    }
}

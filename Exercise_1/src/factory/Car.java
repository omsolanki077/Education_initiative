package factory;

/**
 * Car implementation of Vehicle
 * Represents a standard automobile
 */
public class Car extends Vehicle {
    
    public Car(String model, String color) {
        super(model, color, "Car", 200, 60); // Max speed 200 km/h, 60L fuel capacity
    }
    
    @Override
    public void start() {
        System.out.println("🚗 Car " + model + " started with key ignition");
    }
    
    @Override
    public void stop() {
        System.out.println("🚗 Car " + model + " stopped and engine turned off");
    }
    
    @Override
    public void displaySpecifications() {
        System.out.println("CAR SPECIFICATIONS:");
        System.out.println("  Model: " + model);
        System.out.println("  Color: " + color);
        System.out.println("  Type: " + vehicleType);
        System.out.println("  Max Speed: " + maxSpeed + " km/h");
        System.out.println("  Fuel Capacity: " + fuelCapacity + " L");
        System.out.println("  Fuel Efficiency: " + getFuelEfficiency());
        System.out.println("  Features: 4 wheels, steering wheel, brake pedal");
    }
    
    @Override
    public String getFuelEfficiency() {
        return "8.5 L/100km (City), 6.2 L/100km (Highway)";
    }
}

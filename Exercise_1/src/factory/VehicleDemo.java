package factory;

import singleton.Logger;
import java.util.ArrayList;
import java.util.List;

/**
 * Demonstration of Factory Pattern - Vehicle Factory
 * Shows vehicle creation with factory pattern
 */
public class VehicleDemo {
    
    public static void main(String[] args) {
        System.out.println("\n=== FACTORY PATTERN DEMO - VEHICLE FACTORY ===");
        System.out.println("Demonstrating Vehicle Creation (Factory Pattern)\n");
        
        Logger logger = Logger.getInstance();
        
        try {
            logger.logMessage("Starting vehicle factory demonstration");
            
            VehicleFactory factory = new VehicleFactory();
            
            // Test 1: Create individual vehicles
            System.out.println("--- Creating Individual Vehicles ---");
            
            Vehicle car = factory.createVehicle(VehicleFactory.VehicleType.CAR, "Toyota Camry", "Silver");
            Vehicle truck = factory.createVehicle(VehicleFactory.VehicleType.TRUCK, "Ford F-150", "Blue");
            Vehicle motorcycle = factory.createVehicle(VehicleFactory.VehicleType.MOTORCYCLE, "Honda CBR", "Red");
            
            System.out.println("Created vehicles:");
            System.out.println("1. " + car);
            System.out.println("2. " + truck);
            System.out.println("3. " + motorcycle);
            System.out.println();
            
            // Test 2: Vehicle operations
            System.out.println("--- Vehicle Operations ---");
            List<Vehicle> vehicles = new ArrayList<>();
            vehicles.add(car);
            vehicles.add(truck);
            vehicles.add(motorcycle);
            
            for (int i = 0; i < vehicles.size(); i++) {
                Vehicle vehicle = vehicles.get(i);
                System.out.println("\nVehicle " + (i + 1) + ":");
                vehicle.start();
                Thread.sleep(300);
                vehicle.displaySpecifications();
                Thread.sleep(300);
                vehicle.stop();
                System.out.println();
            }
            
            // Test 3: Create fleet
            System.out.println("--- Creating Vehicle Fleet ---");
            List<VehicleFactory.VehicleSpecification> fleetSpecs = new ArrayList<>();
            fleetSpecs.add(new VehicleFactory.VehicleSpecification(
                VehicleFactory.VehicleType.CAR, "BMW X5", "Black"));
            fleetSpecs.add(new VehicleFactory.VehicleSpecification(
                VehicleFactory.VehicleType.TRUCK, "Chevrolet Silverado", "White"));
            fleetSpecs.add(new VehicleFactory.VehicleSpecification(
                VehicleFactory.VehicleType.MOTORCYCLE, "Yamaha R1", "Yellow"));
            
            List<Vehicle> fleet = factory.createFleet(fleetSpecs);
            System.out.println("Fleet created with " + fleet.size() + " vehicles:");
            for (Vehicle vehicle : fleet) {
                System.out.println("- " + vehicle);
            }
            System.out.println();
            
            // Test 4: Error handling
            System.out.println("--- Error Handling Test ---");
            try {
                factory.createVehicle("INVALID_TYPE", "Test Model", "Test Color");
            } catch (VehicleException e) {
                System.out.println("✅ Caught expected exception: " + e.getMessage());
            }
            
            try {
                factory.createVehicle(VehicleFactory.VehicleType.CAR, "", "Test Color");
            } catch (VehicleException e) {
                System.out.println("✅ Caught expected exception: " + e.getMessage());
            }
            
            System.out.println("\n✅ Vehicle Factory Demo Completed Successfully!");
            System.out.println("   - Factory creates different vehicle types");
            System.out.println("   - Type-safe vehicle creation with enums");
            System.out.println("   - Fleet creation with multiple vehicles");
            System.out.println("   - Proper error handling for invalid inputs");
            System.out.println("   - Each vehicle type has unique behavior");
            
        } catch (Exception e) {
            System.err.println("Error in Vehicle Factory Demo: " + e.getMessage());
            logger.logError("Error in vehicle factory demo", e);
            e.printStackTrace();
        }
    }
}

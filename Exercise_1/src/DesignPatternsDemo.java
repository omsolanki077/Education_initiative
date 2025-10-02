import singleton.SingletonDemo;
import singleton.DatabaseDemo;
import factory.FactoryDemo;
import factory.VehicleDemo;
import observer.ObserverDemo;
import observer.WeatherDemo;
import singleton.Logger;

/**
 * Main demonstration class for all design patterns with 6 use cases
 * Runs Singleton (2 use cases), Factory (2 use cases), and Observer (2 use cases) pattern demos
 */
public class DesignPatternsDemo {
    
    public static void main(String[] args) {
        System.out.println("==============================================================");
        System.out.println("                    DESIGN PATTERNS DEMO                     ");
        System.out.println("                     Exercise 1 - Java                      ");
        System.out.println("                  6 Use Cases - 3 Patterns                   ");
        System.out.println("==============================================================");
        System.out.println();
        
        Logger logger = Logger.getInstance();
        logger.logMessage("Starting design patterns demonstration");
        
        try {
            // Run all six use case demos (3 patterns × 2 use cases each)
            System.out.println("=== SINGLETON PATTERN - 2 USE CASES ===");
            runSingletonDemo();           // Use Case 1: Logger Service
            runDatabaseDemo();           // Use Case 2: Database Manager
            
            System.out.println("=== FACTORY PATTERN - 2 USE CASES ===");
            runFactoryDemo();            // Use Case 3: Notification Factory
            runVehicleDemo();            // Use Case 4: Vehicle Factory
            
            System.out.println("=== OBSERVER PATTERN - 2 USE CASES ===");
            runObserverDemo();           // Use Case 5: Stock Price Tracker
            runWeatherDemo();            // Use Case 6: Weather Station
            
            // Summary
            printSummary();
            
            logger.logMessage("All design pattern demonstrations completed successfully");
            
        } catch (Exception e) {
            System.err.println("Critical error in design patterns demo: " + e.getMessage());
            logger.logError("Critical error in main demo", e);
            e.printStackTrace();
        }
    }
    
    /**
     * Runs the Singleton pattern demonstration
     */
    private static void runSingletonDemo() {
        try {
            System.out.println("Running Singleton Pattern Demo...");
            SingletonDemo.main(new String[]{});
            System.out.println("Singleton Demo Completed\n");
            
        } catch (Exception e) {
            System.err.println("Error in Singleton Demo: " + e.getMessage());
            Logger.getInstance().logError("Error in singleton demo", e);
        }
    }
    
    /**
     * Runs the Factory pattern demonstration
     */
    private static void runFactoryDemo() {
        try {
            System.out.println("Running Factory Pattern Demo...");
            FactoryDemo.main(new String[]{});
            System.out.println("Factory Demo Completed\n");
            
        } catch (Exception e) {
            System.err.println("Error in Factory Demo: " + e.getMessage());
            Logger.getInstance().logError("Error in factory demo", e);
        }
    }
    
    /**
     * Runs the Database Manager Singleton demonstration
     */
    private static void runDatabaseDemo() {
        try {
            System.out.println("Running Database Manager Singleton Demo...");
            DatabaseDemo.main(new String[]{});
            System.out.println("Database Manager Demo Completed\n");
            
        } catch (Exception e) {
            System.err.println("Error in Database Manager Demo: " + e.getMessage());
            Logger.getInstance().logError("Error in database manager demo", e);
        }
    }
    
    /**
     * Runs the Vehicle Factory demonstration
     */
    private static void runVehicleDemo() {
        try {
            System.out.println("Running Vehicle Factory Demo...");
            VehicleDemo.main(new String[]{});
            System.out.println("Vehicle Factory Demo Completed\n");
            
        } catch (Exception e) {
            System.err.println("Error in Vehicle Factory Demo: " + e.getMessage());
            Logger.getInstance().logError("Error in vehicle factory demo", e);
        }
    }
    
    /**
     * Runs the Weather Station Observer demonstration
     */
    private static void runWeatherDemo() {
        try {
            System.out.println("Running Weather Station Observer Demo...");
            WeatherDemo.main(new String[]{});
            System.out.println("Weather Station Demo Completed\n");
            
        } catch (Exception e) {
            System.err.println("Error in Weather Station Demo: " + e.getMessage());
            Logger.getInstance().logError("Error in weather station demo", e);
        }
    }
    
    /**
     * Runs the Observer pattern demonstration
     */
    private static void runObserverDemo() {
        try {
            System.out.println("Running Stock Market Observer Demo...");
            ObserverDemo.main(new String[]{});
            System.out.println("Stock Market Observer Demo Completed\n");
            
        } catch (Exception e) {
            System.err.println("Error in Stock Market Observer Demo: " + e.getMessage());
            Logger.getInstance().logError("Error in stock market observer demo", e);
        }
    }
    
    /**
     * Prints a summary of the patterns
     */
    private static void printSummary() {
        System.out.println("==============================================================");
        System.out.println("                         SUMMARY                             ");
        System.out.println("==============================================================");
        System.out.println();
        
        System.out.println("Design Patterns Implemented - 6 Use Cases:");
        System.out.println();
        
        System.out.println("=== SINGLETON PATTERN - 2 USE CASES ===");
        System.out.println("1. LOGGER SERVICE");
        System.out.println("   - Private constructor prevents external instantiation");
        System.out.println("   - Thread-safe getInstance() method with double-checked locking");
        System.out.println("   - Single instance shared across application");
        System.out.println("   - Exception handling for null messages");
        System.out.println("   - Timestamp logging functionality");
        System.out.println();
        
        System.out.println("2. DATABASE MANAGER");
        System.out.println("   - Centralized database connection management");
        System.out.println("   - Thread-safe singleton implementation");
        System.out.println("   - Database query execution and connection lifecycle");
        System.out.println("   - Exception handling for database operations");
        System.out.println("   - Connection pooling and status monitoring");
        System.out.println();
        
        System.out.println("=== FACTORY PATTERN - 2 USE CASES ===");
        System.out.println("3. NOTIFICATION FACTORY");
        System.out.println("   - Notification interface with multiple implementations");
        System.out.println("   - EmailNotification, SMSNotification, PushNotification classes");
        System.out.println("   - NotificationFactory creates objects based on type");
        System.out.println("   - Exception handling for invalid notification types");
        System.out.println("   - Type-safe enum-based factory method");
        System.out.println();
        
        System.out.println("4. VEHICLE FACTORY");
        System.out.println("   - Vehicle interface with multiple implementations");
        System.out.println("   - Car, Truck, Motorcycle classes");
        System.out.println("   - VehicleFactory creates objects based on type");
        System.out.println("   - Fleet creation and vehicle specifications");
        System.out.println("   - Type-safe enum-based factory method");
        System.out.println();
        
        System.out.println("=== OBSERVER PATTERN - 2 USE CASES ===");
        System.out.println("5. STOCK PRICE TRACKER");
        System.out.println("   - Subject interface (registerObserver, removeObserver, notifyObservers)");
        System.out.println("   - Observer interface (update method)");
        System.out.println("   - StockMarket class as concrete Subject");
        System.out.println("   - Broker class as concrete Observer");
        System.out.println("   - Exception handling for empty observer lists");
        System.out.println("   - Thread-safe observer collection");
        System.out.println();
        
        System.out.println("6. WEATHER STATION");
        System.out.println("   - WeatherStation class as concrete Subject");
        System.out.println("   - WeatherDisplay class as concrete Observer");
        System.out.println("   - Real-time weather updates and notifications");
        System.out.println("   - Multiple display types (Mobile, Desktop, TV)");
        System.out.println("   - Weather data parsing and formatting");
        System.out.println();
        
        System.out.println("Design Principles Used:");
        System.out.println("   - Single Responsibility: Each class has one clear purpose");
        System.out.println("   - Open/Closed: Factory can be extended with new notification types");
        System.out.println("   - Interface Segregation: Small, focused interfaces");
        System.out.println("   - Dependency Inversion: Depend on abstractions, not concretions");
        System.out.println();
        
        System.out.println("Exception Handling Features:");
        System.out.println("   - Custom exceptions for each pattern");
        System.out.println("   - Try-catch blocks throughout");
        System.out.println("   - Proper error logging and user feedback");
        System.out.println("   - Graceful failure handling");
        System.out.println();
        
        System.out.println("Logging Features:");
        System.out.println("   - Centralized logging through Singleton Logger");
        System.out.println("   - Timestamped log messages");
        System.out.println("   - Error logging with exception details");
        System.out.println("   - Console-based output for easy debugging");
        System.out.println();
        
        System.out.println("All 6 use cases implemented with proper error handling!");
        System.out.println("3 Design Patterns × 2 Use Cases Each = 6 Total Use Cases");
        System.out.println("==============================================================");
    }
}

package observer;

import singleton.Logger;

/**
 * Demonstration of Observer Pattern - Weather Station
 * Shows weather station and display interaction with observer pattern
 */
public class WeatherDemo {
    
    public static void main(String[] args) {
        System.out.println("\n=== OBSERVER PATTERN DEMO - WEATHER STATION ===");
        System.out.println("Demonstrating Weather Station and Display System (Observer Pattern)\n");
        
        Logger logger = Logger.getInstance();
        
        try {
            logger.logMessage("Starting weather station observer demonstration");
            
            // Create weather station
            System.out.println("--- Creating Weather Station ---");
            WeatherStation weatherStation = new WeatherStation("WS001");
            System.out.println("Weather Station created: " + weatherStation.getStationId());
            System.out.println();
            
            // Create weather displays (observers)
            System.out.println("--- Creating Weather Displays ---");
            WeatherDisplay mobileDisplay = new WeatherDisplay("DISP001", "Mobile");
            WeatherDisplay desktopDisplay = new WeatherDisplay("DISP002", "Desktop");
            WeatherDisplay tvDisplay = new WeatherDisplay("DISP003", "TV");
            
            System.out.println("Created displays:");
            System.out.println("1. " + mobileDisplay);
            System.out.println("2. " + desktopDisplay);
            System.out.println("3. " + tvDisplay);
            System.out.println();
            
            // Register displays with weather station
            System.out.println("--- Registering Displays with Weather Station ---");
            weatherStation.registerObserver(mobileDisplay);
            weatherStation.registerObserver(desktopDisplay);
            weatherStation.registerObserver(tvDisplay);
            
            System.out.println("Total registered observers: " + weatherStation.getObserverCount());
            System.out.println();
            
            // Simulate weather changes
            System.out.println("--- Weather Updates ---");
            
            // Update 1: Morning weather
            System.out.println("\n1. Morning Weather Update:");
            weatherStation.setMeasurements(22.5f, 65.0f, 1013.2f, "Sunny");
            Thread.sleep(1000);
            
            // Update 2: Afternoon weather
            System.out.println("\n2. Afternoon Weather Update:");
            weatherStation.setMeasurements(28.3f, 45.0f, 1010.8f, "Cloudy");
            Thread.sleep(1000);
            
            // Update 3: Evening weather
            System.out.println("\n3. Evening Weather Update:");
            weatherStation.setMeasurements(18.7f, 80.0f, 1008.5f, "Rainy");
            Thread.sleep(1000);
            
            // Update 4: Night weather
            System.out.println("\n4. Night Weather Update:");
            weatherStation.setMeasurements(15.2f, 90.0f, 1005.3f, "Stormy");
            Thread.sleep(1000);
            
            // Test observer removal
            System.out.println("\n--- Testing Observer Removal ---");
            weatherStation.removeObserver(desktopDisplay);
            System.out.println("Desktop display removed. Remaining observers: " + weatherStation.getObserverCount());
            
            // Final weather update with fewer observers
            System.out.println("\n5. Final Weather Update (with fewer observers):");
            weatherStation.setMeasurements(12.8f, 95.0f, 1002.1f, "Foggy");
            Thread.sleep(1000);
            
            // Display current weather data
            System.out.println("\n--- Current Weather Data ---");
            System.out.println(weatherStation.getWeatherData());
            
            // Test error handling
            System.out.println("\n--- Error Handling Test ---");
            try {
                weatherStation.setMeasurements(25.0f, 50.0f, 1010.0f, "");
            } catch (IllegalArgumentException e) {
                System.out.println("✅ Caught expected exception: " + e.getMessage());
            }
            
            System.out.println("\n✅ Weather Station Observer Demo Completed Successfully!");
            System.out.println("   - Weather station notifies multiple displays");
            System.out.println("   - Different display types show weather information");
            System.out.println("   - Observers can be added and removed dynamically");
            System.out.println("   - Real-time weather updates with proper formatting");
            System.out.println("   - Thread-safe observer management");
            
        } catch (Exception e) {
            System.err.println("Error in Weather Station Demo: " + e.getMessage());
            logger.logError("Error in weather station demo", e);
            e.printStackTrace();
        }
    }
}

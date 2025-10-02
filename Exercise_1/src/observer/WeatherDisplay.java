package observer;

import singleton.Logger;

/**
 * WeatherDisplay class implementing Observer interface
 * Displays weather information on different types of displays
 */
public class WeatherDisplay implements Observer {
    private final String displayId;
    private final String displayType;
    private final Logger logger;
    private String lastWeatherUpdate;
    
    public WeatherDisplay(String displayId, String displayType) {
        if (displayId == null || displayId.trim().isEmpty()) {
            throw new IllegalArgumentException("Display ID cannot be null or empty");
        }
        if (displayType == null || displayType.trim().isEmpty()) {
            throw new IllegalArgumentException("Display type cannot be null or empty");
        }
        
        this.displayId = displayId.trim();
        this.displayType = displayType.trim();
        this.logger = Logger.getInstance();
        this.lastWeatherUpdate = "No weather data received yet";
        
        logger.logMessage("Weather Display created: " + displayId + " (" + displayType + ")");
    }
    
    @Override
    public void update(Subject subject, Object data) throws ObserverException {
        try {
            String weatherData = data != null ? data.toString() : "";
            if (weatherData.trim().isEmpty()) {
                logger.logMessage("Received empty weather update on display " + displayId);
                return;
            }
            
            this.lastWeatherUpdate = weatherData;
            
            // Display weather information based on display type
            displayWeatherInfo(weatherData);
            
            logger.logMessage("Weather display " + displayId + " updated with new weather data");
            
        } catch (Exception e) {
            logger.logError("Failed to update weather display " + displayId, e);
            throw new ObserverException("Failed to update weather display " + displayId, e);
        }
    }
    
    /**
     * Displays weather information based on display type
     * @param weatherData Weather data string
     */
    private void displayWeatherInfo(String weatherData) {
        System.out.println();
        System.out.println("=== WEATHER DISPLAY UPDATE ===");
        System.out.println("Display ID: " + displayId);
        System.out.println("Display Type: " + displayType);
        System.out.println("Weather Data: " + weatherData);
        
        // Parse and display formatted weather information
        if (weatherData.contains("°C")) {
            System.out.println("🌡️  Temperature: " + extractTemperature(weatherData));
        }
        if (weatherData.contains("% humidity")) {
            System.out.println("💧 Humidity: " + extractHumidity(weatherData));
        }
        if (weatherData.contains("hPa")) {
            System.out.println("📊 Pressure: " + extractPressure(weatherData));
        }
        if (weatherData.contains("Sunny") || weatherData.contains("Cloudy") || 
            weatherData.contains("Rainy") || weatherData.contains("Stormy")) {
            System.out.println("☁️  Condition: " + extractCondition(weatherData));
        }
        
        // Display type-specific information
        switch (displayType.toLowerCase()) {
            case "mobile":
                System.out.println("📱 Mobile display: Compact weather info");
                break;
            case "desktop":
                System.out.println("🖥️  Desktop display: Detailed weather dashboard");
                break;
            case "tv":
                System.out.println("📺 TV display: Large format weather broadcast");
                break;
            default:
                System.out.println("📺 Display: Standard weather information");
        }
        
        System.out.println("===============================");
        System.out.println();
    }
    
    /**
     * Extracts temperature from weather data string
     * @param weatherData Weather data string
     * @return Temperature string
     */
    private String extractTemperature(String weatherData) {
        try {
            int tempIndex = weatherData.indexOf("°C");
            if (tempIndex > 0) {
                int startIndex = weatherData.lastIndexOf(" ", tempIndex - 1);
                if (startIndex > 0) {
                    return weatherData.substring(startIndex + 1, tempIndex + 2);
                }
            }
        } catch (Exception e) {
            logger.logError("Failed to extract temperature", e);
        }
        return "Unknown";
    }
    
    /**
     * Extracts humidity from weather data string
     * @param weatherData Weather data string
     * @return Humidity string
     */
    private String extractHumidity(String weatherData) {
        try {
            int humidityIndex = weatherData.indexOf("% humidity");
            if (humidityIndex > 0) {
                int startIndex = weatherData.lastIndexOf(" ", humidityIndex - 1);
                if (startIndex > 0) {
                    return weatherData.substring(startIndex + 1, humidityIndex + 1);
                }
            }
        } catch (Exception e) {
            logger.logError("Failed to extract humidity", e);
        }
        return "Unknown";
    }
    
    /**
     * Extracts pressure from weather data string
     * @param weatherData Weather data string
     * @return Pressure string
     */
    private String extractPressure(String weatherData) {
        try {
            int pressureIndex = weatherData.indexOf("hPa");
            if (pressureIndex > 0) {
                int startIndex = weatherData.lastIndexOf(" ", pressureIndex - 1);
                if (startIndex > 0) {
                    return weatherData.substring(startIndex + 1, pressureIndex + 3);
                }
            }
        } catch (Exception e) {
            logger.logError("Failed to extract pressure", e);
        }
        return "Unknown";
    }
    
    /**
     * Extracts weather condition from weather data string
     * @param weatherData Weather data string
     * @return Weather condition string
     */
    private String extractCondition(String weatherData) {
        try {
            String[] conditions = {"Sunny", "Cloudy", "Rainy", "Stormy", "Snowy", "Foggy"};
            for (String condition : conditions) {
                if (weatherData.contains(condition)) {
                    return condition;
                }
            }
        } catch (Exception e) {
            logger.logError("Failed to extract weather condition", e);
        }
        return "Unknown";
    }
    
    @Override
    public String getObserverId() {
        return displayId;
    }
    
    /**
     * Gets the display type
     * @return Display type string
     */
    public String getDisplayType() {
        return displayType;
    }
    
    /**
     * Gets the last weather update
     * @return Last weather update string
     */
    public String getLastWeatherUpdate() {
        return lastWeatherUpdate;
    }
    
    @Override
    public String toString() {
        return "WeatherDisplay{id='" + displayId + "', type='" + displayType + "'}";
    }
}

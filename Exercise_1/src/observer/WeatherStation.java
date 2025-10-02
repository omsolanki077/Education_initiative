package observer;

import singleton.Logger;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * WeatherStation class implementing Subject interface
 * Notifies observers about weather changes
 */
public class WeatherStation implements Subject {
    private final List<Observer> observers;
    private final Logger logger;
    private final String stationId;
    
    // Weather data
    private float temperature;
    private float humidity;
    private float pressure;
    private String weatherCondition;
    
    public WeatherStation(String stationId) {
        if (stationId == null || stationId.trim().isEmpty()) {
            throw new IllegalArgumentException("Station ID cannot be null or empty");
        }
        
        this.stationId = stationId.trim();
        this.observers = new CopyOnWriteArrayList<>(); // Thread-safe for concurrent access
        this.logger = Logger.getInstance();
        this.temperature = 0.0f;
        this.humidity = 0.0f;
        this.pressure = 0.0f;
        this.weatherCondition = "Unknown";
        
        logger.logMessage("Weather Station " + stationId + " initialized");
    }
    
    /**
     * Updates weather measurements and notifies observers
     * @param temperature Temperature in Celsius
     * @param humidity Humidity percentage (0-100)
     * @param pressure Atmospheric pressure in hPa
     * @param weatherCondition Weather condition description
     */
    public void setMeasurements(float temperature, float humidity, float pressure, String weatherCondition) {
        try {
            if (weatherCondition == null || weatherCondition.trim().isEmpty()) {
                throw new IllegalArgumentException("Weather condition cannot be null or empty");
            }
            
            this.temperature = temperature;
            this.humidity = humidity;
            this.pressure = pressure;
            this.weatherCondition = weatherCondition.trim();
            
            logger.logMessage("Weather Station " + stationId + " updated measurements: " +
                "Temp=" + temperature + "°C, Humidity=" + humidity + "%, Pressure=" + pressure + "hPa, Condition=" + weatherCondition);
            
            // Notify all observers about the weather change
            notifyObservers();
            
        } catch (IllegalArgumentException e) {
            logger.logError("Invalid weather measurement parameters", e);
            throw e;
        } catch (Exception e) {
            logger.logError("Failed to update weather measurements", e);
            throw new RuntimeException("Failed to update weather measurements", e);
        }
    }
    
    @Override
    public void registerObserver(Observer observer) {
        try {
            if (observer == null) {
                throw new IllegalArgumentException("Observer cannot be null");
            }
            
            if (observers.contains(observer)) {
                logger.logMessage("Observer " + observer.getObserverId() + " is already registered with Weather Station " + stationId);
                return;
            }
            
            observers.add(observer);
            logger.logMessage("Observer registered with Weather Station " + stationId + ": " + observer.getObserverId() + 
                " (Total observers: " + observers.size() + ")");
            
        } catch (Exception e) {
            logger.logError("Failed to register observer with Weather Station " + stationId, e);
        }
    }
    
    @Override
    public void removeObserver(Observer observer) {
        try {
            if (observer == null) {
                throw new IllegalArgumentException("Observer cannot be null");
            }
            
            boolean removed = observers.remove(observer);
            if (removed) {
                logger.logMessage("Observer removed from Weather Station " + stationId + ": " + observer.getObserverId() + 
                    " (Remaining observers: " + observers.size() + ")");
            } else {
                logger.logMessage("Observer not found for removal from Weather Station " + stationId + ": " + observer.getObserverId());
            }
            
        } catch (Exception e) {
            logger.logError("Failed to remove observer from Weather Station " + stationId, e);
        }
    }
    
    @Override
    public void notifyObservers() {
        try {
            if (observers.isEmpty()) {
                logger.logMessage("No observers to notify for Weather Station " + stationId);
                return;
            }
            
            String weatherData = String.format("Weather Update from %s: %.1f°C, %.1f%% humidity, %.1f hPa, %s", 
                stationId, temperature, humidity, pressure, weatherCondition);
            
            logger.logMessage("Notifying " + observers.size() + " observers about weather change from Station " + stationId);
            
            for (Observer observer : observers) {
                try {
                    observer.update(this, weatherData);
                } catch (ObserverException e) {
                    logger.logError("Failed to notify observer: " + observer.getObserverId(), e);
                }
            }
            
        } catch (Exception e) {
            logger.logError("Unexpected error during observer notification for Weather Station " + stationId, e);
        }
    }
    
    /**
     * Gets the current number of observers
     * @return Number of registered observers
     */
    public int getObserverCount() {
        return observers.size();
    }
    
    /**
     * Gets the station ID
     * @return Station identifier
     */
    public String getStationId() {
        return stationId;
    }
    
    /**
     * Gets current weather data as a formatted string
     * @return Weather data string
     */
    public String getWeatherData() {
        return String.format("Station %s: %.1f°C, %.1f%% humidity, %.1f hPa, %s", 
            stationId, temperature, humidity, pressure, weatherCondition);
    }
    
    /**
     * Gets current temperature
     * @return Temperature in Celsius
     */
    public float getTemperature() {
        return temperature;
    }
    
    /**
     * Gets current humidity
     * @return Humidity percentage
     */
    public float getHumidity() {
        return humidity;
    }
    
    /**
     * Gets current pressure
     * @return Atmospheric pressure in hPa
     */
    public float getPressure() {
        return pressure;
    }
    
    /**
     * Gets current weather condition
     * @return Weather condition description
     */
    public String getWeatherCondition() {
        return weatherCondition;
    }
}

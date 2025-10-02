package observer;

import singleton.Logger;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * StockMarket class implementing Subject interface
 * Manages stock prices and notifies observers of changes
 */
public class StockMarket implements Subject {
    private final Logger logger;
    private final List<Observer> observers;
    private String stockSymbol;
    private BigDecimal currentPrice;
    private BigDecimal previousPrice;
    
    public StockMarket(String stockSymbol, BigDecimal initialPrice) {
        if (stockSymbol == null || stockSymbol.trim().isEmpty()) {
            throw new IllegalArgumentException("Stock symbol cannot be null or empty");
        }
        if (initialPrice == null || initialPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Initial price cannot be null or negative");
        }
        
        this.logger = Logger.getInstance();
        this.observers = new CopyOnWriteArrayList<>(); // Thread-safe for concurrent access
        this.stockSymbol = stockSymbol.trim().toUpperCase();
        this.currentPrice = initialPrice;
        this.previousPrice = initialPrice;
        
        logger.logMessage("StockMarket created for " + this.stockSymbol + " with initial price: $" + initialPrice);
    }
    
    @Override
    public void registerObserver(Observer observer) throws ObserverException {
        try {
            if (observer == null) {
                throw new IllegalArgumentException("Observer cannot be null");
            }
            
            if (observers.contains(observer)) {
                logger.logMessage("Observer " + observer.getObserverId() + " is already registered");
                return;
            }
            
            observers.add(observer);
            logger.logMessage("Observer registered: " + observer.getObserverId() + 
                " (Total observers: " + observers.size() + ")");
            
        } catch (Exception e) {
            logger.logError("Failed to register observer", e);
            throw new ObserverException("Failed to register observer: " + 
                (observer != null ? observer.getObserverId() : "null"), e);
        }
    }
    
    @Override
    public void removeObserver(Observer observer) throws ObserverException {
        try {
            if (observer == null) {
                throw new IllegalArgumentException("Observer cannot be null");
            }
            
            boolean removed = observers.remove(observer);
            if (removed) {
                logger.logMessage("Observer removed: " + observer.getObserverId() + 
                    " (Remaining observers: " + observers.size() + ")");
            } else {
                logger.logMessage("Observer not found for removal: " + observer.getObserverId());
            }
            
        } catch (Exception e) {
            logger.logError("Failed to remove observer", e);
            throw new ObserverException("Failed to remove observer: " + 
                (observer != null ? observer.getObserverId() : "null"), e);
        }
    }
    
    @Override
    public void notifyObservers() throws ObserverException {
        try {
            if (observers.isEmpty()) {
                logger.logMessage("No observers to notify for stock: " + stockSymbol);
                return;
            }
            
            logger.logMessage("Notifying " + observers.size() + " observers about " + stockSymbol + " price change");
            
            // Create price data object to pass to observers
            StockPriceData priceData = new StockPriceData(stockSymbol, currentPrice, previousPrice);
            
            List<Exception> notificationErrors = new ArrayList<>();
            
            for (Observer observer : observers) {
                try {
                    observer.update(this, priceData);
                } catch (Exception e) {
                    logger.logError("Failed to notify observer: " + observer.getObserverId(), e);
                    notificationErrors.add(e);
                }
            }
            
            // If some notifications failed, throw exception with details
            if (!notificationErrors.isEmpty()) {
                throw new ObserverException("Failed to notify " + notificationErrors.size() + 
                    " out of " + observers.size() + " observers");
            }
            
        } catch (ObserverException e) {
            throw e; // Re-throw observer exceptions
        } catch (Exception e) {
            logger.logError("Unexpected error during observer notification", e);
            throw new ObserverException("Unexpected error during observer notification", e);
        }
    }
    
    /**
     * Updates the stock price and notifies observers
     * @param newPrice The new stock price
     * @throws ObserverException if notification fails
     */
    public void setStockPrice(BigDecimal newPrice) throws ObserverException {
        try {
            if (newPrice == null || newPrice.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Stock price cannot be null or negative");
            }
            
            this.previousPrice = this.currentPrice;
            this.currentPrice = newPrice;
            
            BigDecimal change = currentPrice.subtract(previousPrice);
            logger.logMessage(String.format("Stock price updated for %s: $%.2f -> $%.2f (Change: $%.2f)", 
                stockSymbol, previousPrice, currentPrice, change));
            
            // Notify all observers about the price change
            notifyObservers();
            
        } catch (ObserverException e) {
            throw e; // Re-throw observer exceptions
        } catch (Exception e) {
            logger.logError("Failed to update stock price", e);
            throw new ObserverException("Failed to update stock price for " + stockSymbol, e);
        }
    }
    
    // Getters
    public String getStockSymbol() {
        return stockSymbol;
    }
    
    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }
    
    public BigDecimal getPreviousPrice() {
        return previousPrice;
    }
    
    public int getObserverCount() {
        return observers.size();
    }
}

/**
 * Simple data class for stock price information
 */
class StockPriceData {
    private final String stockSymbol;
    private final BigDecimal price;
    private final BigDecimal previousPrice;
    private final long timestamp;
    
    public StockPriceData(String stockSymbol, BigDecimal price, BigDecimal previousPrice) {
        this.stockSymbol = stockSymbol;
        this.price = price;
        this.previousPrice = previousPrice;
        this.timestamp = System.currentTimeMillis();
    }
    
    public String getStockSymbol() { return stockSymbol; }
    public BigDecimal getPrice() { return price; }
    public BigDecimal getPreviousPrice() { return previousPrice; }
    public long getTimestamp() { return timestamp; }
    
    public BigDecimal getPriceChange() {
        return price.subtract(previousPrice);
    }
}

package observer;

import singleton.Logger;
import java.math.BigDecimal;

/**
 * Demonstration of Observer Pattern
 * Shows stock market and broker interaction with exception handling
 */
public class ObserverDemo {
    
    public static void main(String[] args) {
        System.out.println("\n=== OBSERVER PATTERN DEMO ===");
        System.out.println("Demonstrating Stock Price Tracker (Observer Pattern)\n");
        
        Logger logger = Logger.getInstance();
        
        try {
            logger.logMessage("Starting observer pattern demonstration");
            
            // Create stock market for AAPL with initial price
            System.out.println("--- Creating Stock Market ---");
            StockMarket appleStock = new StockMarket("AAPL", new BigDecimal("150.00"));
            
            // Create brokers (observers)
            System.out.println("\n--- Creating Brokers ---");
            Broker broker1 = new Broker("BRK001", "Goldman Sachs");
            Broker broker2 = new Broker("BRK002", "Morgan Stanley");
            Broker broker3 = new Broker("BRK003", "JP Morgan Chase");
            
            // Register brokers with stock market
            System.out.println("\n--- Registering Brokers ---");
            appleStock.registerObserver(broker1);
            appleStock.registerObserver(broker2);
            appleStock.registerObserver(broker3);
            
            System.out.println("Total registered observers: " + appleStock.getObserverCount());
            
            // Simulate stock price changes
            System.out.println("\n--- Stock Price Updates ---");
            
            // Price increase
            System.out.println("\n1. Price Increase:");
            appleStock.setStockPrice(new BigDecimal("155.50"));
            
            Thread.sleep(1000); // Brief pause for readability
            
            // Significant price drop
            System.out.println("\n2. Significant Price Drop:");
            appleStock.setStockPrice(new BigDecimal("145.25"));
            
            Thread.sleep(1000);
            
            // Minor price change
            System.out.println("\n3. Minor Price Change:");
            appleStock.setStockPrice(new BigDecimal("146.00"));
            
            Thread.sleep(1000);
            
            // Large price increase
            System.out.println("\n4. Large Price Increase:");
            appleStock.setStockPrice(new BigDecimal("162.75"));
            
            // Test removing an observer
            System.out.println("\n--- Removing Observer ---");
            appleStock.removeObserver(broker2);
            System.out.println("Remaining observers: " + appleStock.getObserverCount());
            
            // Price change after removing observer
            System.out.println("\n5. Price Change After Observer Removal:");
            appleStock.setStockPrice(new BigDecimal("158.90"));
            
            // Test exception handling - try to notify with empty observer list
            System.out.println("\n--- Testing Exception Handling ---");
            
            // Remove all observers
            appleStock.removeObserver(broker1);
            appleStock.removeObserver(broker3);
            
            System.out.println("All observers removed. Attempting price update...");
            appleStock.setStockPrice(new BigDecimal("160.00"));
            
            // Test invalid operations
            try {
                appleStock.registerObserver(null);
            } catch (ObserverException e) {
                System.out.println("Caught expected exception for null observer: " + e.getMessage());
            }
            
            try {
                appleStock.setStockPrice(new BigDecimal("-10.00"));
            } catch (Exception e) {
                System.out.println("Caught expected exception for negative price: " + e.getMessage());
            }
            
            logger.logMessage("Observer pattern demonstration completed successfully");
            
        } catch (ObserverException e) {
            System.err.println("Observer error: " + e.getMessage());
            logger.logError("Observer error in demo", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Demo was interrupted");
            logger.logError("Demo interrupted", e);
        } catch (Exception e) {
            System.err.println("Unexpected error in observer demo: " + e.getMessage());
            logger.logError("Unexpected error in observer demo", e);
            e.printStackTrace();
        }
    }
}

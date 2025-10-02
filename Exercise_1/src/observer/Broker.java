package observer;

import singleton.Logger;
import java.math.BigDecimal;

/**
 * Broker class implementing Observer interface
 * Receives and processes stock price updates
 */
public class Broker implements Observer {
    private final String brokerId;
    private final String brokerName;
    private final Logger logger;
    
    public Broker(String brokerId, String brokerName) {
        if (brokerId == null || brokerId.trim().isEmpty()) {
            throw new IllegalArgumentException("Broker ID cannot be null or empty");
        }
        if (brokerName == null || brokerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Broker name cannot be null or empty");
        }
        
        this.brokerId = brokerId.trim();
        this.brokerName = brokerName.trim();
        this.logger = Logger.getInstance();
        
        logger.logMessage("Broker created: " + this.brokerName + " (ID: " + this.brokerId + ")");
    }
    
    @Override
    public void update(Subject subject, Object data) throws ObserverException {
        try {
            if (subject == null) {
                throw new IllegalArgumentException("Subject cannot be null");
            }
            
            if (!(subject instanceof StockMarket)) {
                throw new IllegalArgumentException("Expected StockMarket subject, got: " + 
                    subject.getClass().getSimpleName());
            }
            
            if (!(data instanceof StockPriceData)) {
                throw new IllegalArgumentException("Expected StockPriceData, got: " + 
                    (data != null ? data.getClass().getSimpleName() : "null"));
            }
            
            StockMarket stockMarket = (StockMarket) subject;
            StockPriceData priceData = (StockPriceData) data;
            
            // Process the stock price update
            processStockUpdate(stockMarket, priceData);
            
        } catch (IllegalArgumentException e) {
            logger.logError("Invalid update parameters for broker " + brokerId, e);
            throw new ObserverException("Invalid update parameters for broker " + brokerId, e);
        } catch (Exception e) {
            logger.logError("Failed to process stock update for broker " + brokerId, e);
            throw new ObserverException("Failed to process stock update for broker " + brokerId, e);
        }
    }
    
    /**
     * Processes stock price update and displays information
     * @param stockMarket The stock market that changed
     * @param priceData The new price data
     */
    private void processStockUpdate(StockMarket stockMarket, StockPriceData priceData) {
        try {
            String symbol = priceData.getStockSymbol();
            BigDecimal currentPrice = priceData.getPrice();
            BigDecimal previousPrice = priceData.getPreviousPrice();
            BigDecimal change = priceData.getPriceChange();
            
            // Determine if price went up, down, or stayed the same
            String changeIndicator;
            if (change.compareTo(BigDecimal.ZERO) > 0) {
                changeIndicator = "UP";
            } else if (change.compareTo(BigDecimal.ZERO) < 0) {
                changeIndicator = "DOWN";
            } else {
                changeIndicator = "UNCHANGED";
            }
            
            // Display the update
            System.out.println(String.format(
                "[BROKER: %s] Stock Update Received:",
                brokerName
            ));
            System.out.println(String.format(
                "   %s: $%.2f -> $%.2f (%s $%.2f) [%s]",
                symbol, previousPrice, currentPrice, 
                change.compareTo(BigDecimal.ZERO) >= 0 ? "+" : "", change,
                changeIndicator
            ));
            
            // Log the update
            logger.logMessage(String.format(
                "Broker %s processed %s update: $%.2f -> $%.2f (Change: $%.2f)",
                brokerId, symbol, previousPrice, currentPrice, change
            ));
            
            // Simulate broker-specific actions based on price change
            simulateBrokerAction(symbol, change);
            
        } catch (Exception e) {
            logger.logError("Error processing stock update display for broker " + brokerId, e);
            throw new RuntimeException("Error processing stock update display", e);
        }
    }
    
    /**
     * Simulates broker-specific actions based on price changes
     * @param symbol Stock symbol
     * @param priceChange Price change amount
     */
    private void simulateBrokerAction(String symbol, BigDecimal priceChange) {
        try {
            BigDecimal threshold = new BigDecimal("5.00"); // $5 threshold for action
            
            if (priceChange.abs().compareTo(threshold) >= 0) {
                if (priceChange.compareTo(BigDecimal.ZERO) > 0) {
                    System.out.println("   " + brokerName + " Action: Consider selling " + symbol + " (significant gain)");
                } else {
                    System.out.println("   " + brokerName + " Action: Consider buying " + symbol + " (significant drop)");
                }
                logger.logMessage("Broker " + brokerId + " triggered action for " + symbol + " due to significant price change");
            } else {
                System.out.println("   " + brokerName + " Action: Monitoring " + symbol + " (minor change)");
            }
            
        } catch (Exception e) {
            logger.logError("Error in broker action simulation for " + brokerId, e);
        }
    }
    
    @Override
    public String getObserverId() {
        return brokerId;
    }
    
    public String getBrokerName() {
        return brokerName;
    }
    
    @Override
    public String toString() {
        return String.format("Broker{id='%s', name='%s'}", brokerId, brokerName);
    }
}

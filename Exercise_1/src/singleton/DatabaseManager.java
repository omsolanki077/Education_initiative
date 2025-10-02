package singleton;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * DatabaseManager class implementing Singleton pattern
 * Manages database connections with thread-safe implementation
 */
public class DatabaseManager {
    // Static instance variable (lazy initialization)
    private static DatabaseManager instance;
    
    // Database connection
    private Connection connection;
    private final String url;
    private final String username;
    private final DateTimeFormatter formatter;
    
    // Private constructor prevents external instantiation
    private DatabaseManager() {
        this.url = "jdbc:mysql://localhost:3306/astronaut_db";
        this.username = "astronaut_user";
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        System.out.println("[DATABASE] DatabaseManager instance created at " + getCurrentTimestamp());
        initializeConnection();
    }
    
    /**
     * Thread-safe getInstance method using synchronized block
     * @return Single instance of DatabaseManager
     */
    public static DatabaseManager getInstance() {
        if (instance == null) {
            synchronized (DatabaseManager.class) {
                if (instance == null) {
                    instance = new DatabaseManager();
                }
            }
        }
        return instance;
    }
    
    /**
     * Initializes database connection
     */
    private void initializeConnection() {
        try {
            // Simulate database connection (in real app, would use actual JDBC)
            System.out.println("[DATABASE] Initializing database connection...");
            System.out.println("[DATABASE] URL: " + url);
            System.out.println("[DATABASE] Username: " + username);
            System.out.println("[DATABASE] Connection established successfully");
            
        } catch (Exception e) {
            System.err.println("[DATABASE ERROR] Failed to initialize connection: " + e.getMessage());
        }
    }
    
    /**
     * Gets the database connection
     * @return Database connection
     */
    public Connection getConnection() {
        if (connection == null) {
            try {
                // In a real application, this would create an actual connection
                System.out.println("[DATABASE] Creating new database connection...");
                // connection = DriverManager.getConnection(url, username, password);
                System.out.println("[DATABASE] Connection created successfully");
            } catch (Exception e) {
                System.err.println("[DATABASE ERROR] Failed to create connection: " + e.getMessage());
            }
        }
        return connection;
    }
    
    /**
     * Executes a database query
     * @param query SQL query to execute
     */
    public void executeQuery(String query) {
        try {
            if (query == null || query.trim().isEmpty()) {
                throw new IllegalArgumentException("Query cannot be null or empty");
            }
            
            System.out.println("[DATABASE] Executing query: " + query);
            System.out.println("[DATABASE] Query executed successfully at " + getCurrentTimestamp());
            
        } catch (IllegalArgumentException e) {
            System.err.println("[DATABASE ERROR] " + getCurrentTimestamp() + " - " + e.getMessage());
        } catch (Exception e) {
            System.err.println("[DATABASE ERROR] " + getCurrentTimestamp() + " - Failed to execute query: " + e.getMessage());
        }
    }
    
    /**
     * Closes the database connection
     */
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                // connection.close();
                System.out.println("[DATABASE] Connection closed successfully");
            }
        } catch (Exception e) {
            System.err.println("[DATABASE ERROR] Failed to close connection: " + e.getMessage());
        }
    }
    
    /**
     * Gets current timestamp as formatted string
     * @return Formatted timestamp
     */
    private String getCurrentTimestamp() {
        return LocalDateTime.now().format(formatter);
    }
    
    /**
     * Gets database status information
     * @return Database status string
     */
    public String getStatus() {
        return "DatabaseManager Status: " + 
               "URL=" + url + 
               ", Username=" + username + 
               ", Connection=" + (connection != null ? "Active" : "Inactive");
    }
}

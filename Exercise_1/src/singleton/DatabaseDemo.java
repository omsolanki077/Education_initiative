package singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstration of Singleton Pattern - Database Manager
 * Shows database connection management with singleton pattern
 */
public class DatabaseDemo {
    
    public static void main(String[] args) {
        System.out.println("\n=== SINGLETON PATTERN DEMO - DATABASE MANAGER ===");
        System.out.println("Demonstrating Database Connection Management (Singleton Pattern)\n");
        
        try {
            // Test 1: Get multiple instances to prove singleton
            System.out.println("--- Testing Singleton Behavior ---");
            DatabaseManager db1 = DatabaseManager.getInstance();
            DatabaseManager db2 = DatabaseManager.getInstance();
            DatabaseManager db3 = DatabaseManager.getInstance();
            
            System.out.println("Database Manager 1: " + db1.hashCode());
            System.out.println("Database Manager 2: " + db2.hashCode());
            System.out.println("Database Manager 3: " + db3.hashCode());
            System.out.println("All instances are the same: " + (db1 == db2 && db2 == db3));
            System.out.println();
            
            // Test 2: Database operations
            System.out.println("--- Database Operations ---");
            DatabaseManager db = DatabaseManager.getInstance();
            
            // Simulate database queries
            List<String> queries = new ArrayList<>();
            queries.add("SELECT * FROM astronauts WHERE status = 'active'");
            queries.add("INSERT INTO tasks (name, start_time, end_time) VALUES ('Exercise', '09:00', '10:00')");
            queries.add("UPDATE schedule SET completed = true WHERE task_id = 1");
            queries.add("DELETE FROM old_tasks WHERE created_date < '2023-01-01'");
            
            for (String query : queries) {
                db.executeQuery(query);
                Thread.sleep(500); // Brief pause for readability
            }
            
            // Test 3: Database status
            System.out.println("\n--- Database Status ---");
            System.out.println(db.getStatus());
            
            // Test 4: Connection management
            System.out.println("\n--- Connection Management ---");
            db.getConnection();
            db.closeConnection();
            
            System.out.println("\n✅ Database Manager Singleton Demo Completed Successfully!");
            System.out.println("   - Single instance maintained across multiple calls");
            System.out.println("   - Thread-safe database connection management");
            System.out.println("   - Centralized database operations");
            System.out.println("   - Proper connection lifecycle management");
            
        } catch (Exception e) {
            System.err.println("Error in Database Manager Demo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

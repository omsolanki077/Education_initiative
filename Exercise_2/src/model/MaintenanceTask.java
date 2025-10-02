package model;

import java.time.LocalTime;

/**
 * Maintenance task implementation
 * Represents equipment and facility maintenance activities for astronauts
 */
public class MaintenanceTask extends Task {
    
    public MaintenanceTask(String name, LocalTime startTime, LocalTime endTime) {
        super(name, startTime, endTime, "Maintenance");
    }
    
    @Override
    public void displayTask() {
        System.out.println("MAINTENANCE TASK:");
        System.out.println("  Name: " + name);
        System.out.println("  Time: " + getFormattedTimeRange());
        System.out.println("  Duration: " + getDurationMinutes() + " minutes");
        System.out.println("  Description: Equipment and facility maintenance");
    }
}

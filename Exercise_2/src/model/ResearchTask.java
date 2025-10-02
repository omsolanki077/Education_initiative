package model;

import java.time.LocalTime;

/**
 * Research task implementation
 * Represents scientific research activities for astronauts
 */
public class ResearchTask extends Task {
    
    public ResearchTask(String name, LocalTime startTime, LocalTime endTime) {
        super(name, startTime, endTime, "Research");
    }
    
    @Override
    public void displayTask() {
        System.out.println("RESEARCH TASK:");
        System.out.println("  Name: " + name);
        System.out.println("  Time: " + getFormattedTimeRange());
        System.out.println("  Duration: " + getDurationMinutes() + " minutes");
        System.out.println("  Description: Scientific research and experimentation");
    }
}

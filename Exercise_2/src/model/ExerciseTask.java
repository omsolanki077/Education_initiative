package model;

import java.time.LocalTime;

/**
 * Exercise task implementation
 * Represents physical fitness and health activities for astronauts
 */
public class ExerciseTask extends Task {
    
    public ExerciseTask(String name, LocalTime startTime, LocalTime endTime) {
        super(name, startTime, endTime, "Exercise");
    }
    
    @Override
    public void displayTask() {
        System.out.println("EXERCISE TASK:");
        System.out.println("  Name: " + name);
        System.out.println("  Time: " + getFormattedTimeRange());
        System.out.println("  Duration: " + getDurationMinutes() + " minutes");
        System.out.println("  Description: Physical fitness and health maintenance");
    }
}

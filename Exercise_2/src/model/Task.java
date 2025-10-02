package model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Abstract base class for all task types
 * Follows Single Responsibility Principle - handles basic task properties
 */
public abstract class Task {
    protected String name;
    protected LocalTime startTime;
    protected LocalTime endTime;
    protected String taskType;
    
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    
    public Task(String name, LocalTime startTime, LocalTime endTime, String taskType) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Task name cannot be null or empty");
        }
        if (startTime == null || endTime == null) {
            throw new IllegalArgumentException("Start time and end time cannot be null");
        }
        if (startTime.isAfter(endTime) || startTime.equals(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time");
        }
        
        this.name = name.trim();
        this.startTime = startTime;
        this.endTime = endTime;
        this.taskType = taskType;
    }
    
    /**
     * Abstract method to be implemented by concrete task classes
     */
    public abstract void displayTask();
    
    /**
     * Checks if this task overlaps with another task
     * @param other The other task to check against
     * @return true if tasks overlap, false otherwise
     */
    public boolean overlapsWith(Task other) {
        if (other == null) {
            return false;
        }
        
        // Tasks overlap if one starts before the other ends and vice versa
        return this.startTime.isBefore(other.endTime) && other.startTime.isBefore(this.endTime);
    }
    
    /**
     * Gets the duration of the task in minutes
     * @return duration in minutes
     */
    public long getDurationMinutes() {
        return java.time.Duration.between(startTime, endTime).toMinutes();
    }
    
    // Getters
    public String getName() {
        return name;
    }
    
    public LocalTime getStartTime() {
        return startTime;
    }
    
    public LocalTime getEndTime() {
        return endTime;
    }
    
    public String getTaskType() {
        return taskType;
    }
    
    public String getFormattedTimeRange() {
        return startTime.format(TIME_FORMATTER) + " - " + endTime.format(TIME_FORMATTER);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Task task = (Task) obj;
        return name.equals(task.name) && 
               startTime.equals(task.startTime) && 
               endTime.equals(task.endTime);
    }
    
    @Override
    public int hashCode() {
        return name.hashCode() + startTime.hashCode() + endTime.hashCode();
    }
    
    @Override
    public String toString() {
        return String.format("%s: %s (%s) [%d min]", 
            taskType, name, getFormattedTimeRange(), getDurationMinutes());
    }
}

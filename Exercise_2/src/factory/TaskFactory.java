package factory;

import model.*;
import exception.TaskException;
import util.Logger;
import java.time.LocalTime;

/**
 * Factory class for creating different types of tasks
 * Follows Factory Pattern and Open/Closed Principle
 */
public class TaskFactory {
    private final Logger logger;
    
    // Enum for task types - provides type safety
    public enum TaskType {
        RESEARCH, EXERCISE, MAINTENANCE
    }
    
    public TaskFactory() {
        this.logger = Logger.getInstance();
    }
    
    /**
     * Creates a task based on the specified type
     * @param type The type of task to create
     * @param name The name of the task
     * @param startTime The start time of the task
     * @param endTime The end time of the task
     * @return Task instance
     * @throws TaskException if task creation fails
     */
    public Task createTask(TaskType type, String name, LocalTime startTime, LocalTime endTime) 
            throws TaskException {
        try {
            if (type == null) {
                throw new IllegalArgumentException("Task type cannot be null");
            }
            
            logger.logMessage("Creating task of type: " + type + " - " + name);
            
            switch (type) {
                case RESEARCH:
                    return new ResearchTask(name, startTime, endTime);
                case EXERCISE:
                    return new ExerciseTask(name, startTime, endTime);
                case MAINTENANCE:
                    return new MaintenanceTask(name, startTime, endTime);
                default:
                    throw new TaskException("Unsupported task type: " + type);
            }
            
        } catch (IllegalArgumentException e) {
            logger.logError("Invalid task parameters", e);
            throw new TaskException("Invalid task parameters: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.logError("Failed to create task", e);
            throw new TaskException("Failed to create task of type: " + type, e);
        }
    }
    
    /**
     * Creates a task based on string type (for user input)
     * @param typeString String representation of task type
     * @param name The name of the task
     * @param startTime The start time of the task
     * @param endTime The end time of the task
     * @return Task instance
     * @throws TaskException if task creation fails
     */
    public Task createTask(String typeString, String name, LocalTime startTime, LocalTime endTime) 
            throws TaskException {
        try {
            if (typeString == null || typeString.trim().isEmpty()) {
                throw new IllegalArgumentException("Task type string cannot be null or empty");
            }
            
            TaskType type = TaskType.valueOf(typeString.toUpperCase().trim());
            return createTask(type, name, startTime, endTime);
            
        } catch (IllegalArgumentException e) {
            logger.logError("Invalid task type string: " + typeString, e);
            throw new TaskException("Invalid task type: " + typeString + 
                ". Valid types are: RESEARCH, EXERCISE, MAINTENANCE", e);
        }
    }
    
    /**
     * Gets all available task types
     * @return Array of available task types
     */
    public TaskType[] getAvailableTaskTypes() {
        return TaskType.values();
    }
    
    /**
     * Gets task type names as strings
     * @return Array of task type names
     */
    public String[] getTaskTypeNames() {
        TaskType[] types = TaskType.values();
        String[] names = new String[types.length];
        for (int i = 0; i < types.length; i++) {
            names[i] = types[i].name();
        }
        return names;
    }
}

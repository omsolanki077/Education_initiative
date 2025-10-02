package manager;

import model.Task;
import observer.ConflictObserver;
import util.Logger;
import exception.TaskException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ScheduleManager class implementing Singleton pattern
 * Manages astronaut daily schedule with conflict detection
 */
public class ScheduleManager {
    // Static instance variable
    private static ScheduleManager instance;
    
    // Task storage and observers
    private final List<Task> tasks;
    private final List<ConflictObserver> observers;
    private final Logger logger;
    
    // Private constructor prevents external instantiation
    private ScheduleManager() {
        this.tasks = new ArrayList<>();
        this.observers = new CopyOnWriteArrayList<>(); // Thread-safe for concurrent access
        this.logger = Logger.getInstance();
        
        logger.logMessage("ScheduleManager instance created");
    }
    
    /**
     * Thread-safe getInstance method using synchronized block
     * @return Single instance of ScheduleManager
     */
    public static ScheduleManager getInstance() {
        if (instance == null) {
            synchronized (ScheduleManager.class) {
                if (instance == null) {
                    instance = new ScheduleManager();
                }
            }
        }
        return instance;
    }
    
    /**
     * Adds a task to the schedule after checking for conflicts
     * @param task The task to add
     * @throws TaskException if task addition fails
     */
    public void addTask(Task task) throws TaskException {
        try {
            if (task == null) {
                throw new IllegalArgumentException("Task cannot be null");
            }
            
            logger.logMessage("Attempting to add task: " + task.getName());
            
            // Check for time conflicts
            Task conflictingTask = findConflictingTask(task);
            if (conflictingTask != null) {
                String conflictMessage = String.format(
                    "Task '%s' (%s) conflicts with existing task '%s' (%s)",
                    task.getName(), task.getFormattedTimeRange(),
                    conflictingTask.getName(), conflictingTask.getFormattedTimeRange()
                );
                
                // Notify observers about the conflict
                notifyObservers(conflictMessage);
                
                throw new TaskException("Schedule conflict detected: " + conflictMessage);
            }
            
            // No conflict, add the task
            tasks.add(task);
            
            // Sort tasks by start time for better organization
            tasks.sort(Comparator.comparing(Task::getStartTime));
            
            logger.logMessage("Task added successfully: " + task.getName() + 
                " (Total tasks: " + tasks.size() + ")");
            
        } catch (TaskException e) {
            throw e; // Re-throw task exceptions
        } catch (Exception e) {
            logger.logError("Failed to add task", e);
            throw new TaskException("Failed to add task: " + task.getName(), e);
        }
    }
    
    /**
     * Removes a task from the schedule by name
     * @param taskName The name of the task to remove
     * @throws TaskException if task removal fails
     */
    public void removeTask(String taskName) throws TaskException {
        try {
            if (taskName == null || taskName.trim().isEmpty()) {
                throw new IllegalArgumentException("Task name cannot be null or empty");
            }
            
            logger.logMessage("Attempting to remove task: " + taskName);
            
            // Find and remove the task
            boolean removed = tasks.removeIf(task -> task.getName().equalsIgnoreCase(taskName.trim()));
            
            if (!removed) {
                throw new TaskException("Task not found: " + taskName);
            }
            
            logger.logMessage("Task removed successfully: " + taskName + 
                " (Remaining tasks: " + tasks.size() + ")");
            
        } catch (TaskException e) {
            throw e; // Re-throw task exceptions
        } catch (Exception e) {
            logger.logError("Failed to remove task", e);
            throw new TaskException("Failed to remove task: " + taskName, e);
        }
    }
    
    /**
     * Displays all tasks in the schedule
     */
    public void viewTasks() {
        try {
            logger.logMessage("Displaying schedule (Total tasks: " + tasks.size() + ")");
            
            if (tasks.isEmpty()) {
                System.out.println();
                System.out.println("=== ASTRONAUT DAILY SCHEDULE ===");
                System.out.println("No tasks scheduled for today.");
                System.out.println("===============================");
                System.out.println();
                return;
            }
            
            System.out.println();
            System.out.println("=== ASTRONAUT DAILY SCHEDULE ===");
            System.out.println("Total Tasks: " + tasks.size());
            System.out.println();
            
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.println((i + 1) + ". " + task.toString());
            }
            
            System.out.println();
            System.out.println("=== DETAILED VIEW ===");
            for (Task task : tasks) {
                System.out.println();
                task.displayTask();
            }
            
            System.out.println("===============================");
            System.out.println();
            
        } catch (Exception e) {
            logger.logError("Failed to display tasks", e);
            System.err.println("Error displaying schedule: " + e.getMessage());
        }
    }
    
    /**
     * Finds a task that conflicts with the given task
     * @param newTask The task to check for conflicts
     * @return The conflicting task, or null if no conflict
     */
    private Task findConflictingTask(Task newTask) {
        for (Task existingTask : tasks) {
            if (newTask.overlapsWith(existingTask)) {
                return existingTask;
            }
        }
        return null;
    }
    
    /**
     * Registers an observer for conflict notifications
     * @param observer The observer to register
     */
    public void addObserver(ConflictObserver observer) {
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
        }
    }
    
    /**
     * Removes an observer from conflict notifications
     * @param observer The observer to remove
     */
    public void removeObserver(ConflictObserver observer) {
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
        }
    }
    
    /**
     * Notifies all observers about a schedule conflict
     * @param message The conflict message
     */
    private void notifyObservers(String message) {
        try {
            if (observers.isEmpty()) {
                logger.logMessage("No observers to notify about conflict");
                return;
            }
            
            logger.logMessage("Notifying " + observers.size() + " observers about schedule conflict");
            
            for (ConflictObserver observer : observers) {
                try {
                    observer.update(message);
                } catch (Exception e) {
                    logger.logError("Failed to notify observer: " + observer.getObserverId(), e);
                }
            }
            
        } catch (Exception e) {
            logger.logError("Unexpected error during observer notification", e);
        }
    }
    
    /**
     * Gets the current number of tasks
     * @return Number of tasks in the schedule
     */
    public int getTaskCount() {
        return tasks.size();
    }
    
    /**
     * Gets the current number of observers
     * @return Number of registered observers
     */
    public int getObserverCount() {
        return observers.size();
    }
    
    /**
     * Checks if a task with the given name exists
     * @param taskName The task name to check
     * @return true if task exists, false otherwise
     */
    public boolean hasTask(String taskName) {
        if (taskName == null || taskName.trim().isEmpty()) {
            return false;
        }
        
        return tasks.stream()
                .anyMatch(task -> task.getName().equalsIgnoreCase(taskName.trim()));
    }
}

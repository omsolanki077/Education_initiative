import manager.ScheduleManager;
import factory.TaskFactory;
import observer.ConflictNotifier;
import model.Task;
import exception.TaskException;
import util.Logger;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Main application class for Astronaut Daily Schedule Organizer
 * Console-based application with menu system and user interaction
 */
public class AstronautScheduleOrganizer {
    
    private final ScheduleManager scheduleManager;
    private final TaskFactory taskFactory;
    private final Logger logger;
    private final Scanner scanner;
    private final DateTimeFormatter timeFormatter;
    
    public AstronautScheduleOrganizer() {
        this.scheduleManager = ScheduleManager.getInstance();
        this.taskFactory = new TaskFactory();
        this.logger = Logger.getInstance();
        this.scanner = new Scanner(System.in);
        this.timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        
        // Register conflict notifier
        ConflictNotifier conflictNotifier = new ConflictNotifier("Mission Control");
        scheduleManager.addObserver(conflictNotifier);
        
        logger.logMessage("Astronaut Schedule Organizer initialized");
    }
    
    /**
     * Main entry point of the application
     */
    public static void main(String[] args) {
        System.out.println("==============================================================");
        System.out.println("           ASTRONAUT DAILY SCHEDULE ORGANIZER               ");
        System.out.println("                    Exercise 2 - Java                      ");
        System.out.println("==============================================================");
        System.out.println();
        
        AstronautScheduleOrganizer app = new AstronautScheduleOrganizer();
        app.run();
    }
    
    /**
     * Main application loop
     */
    public void run() {
        try {
            logger.logMessage("Starting Astronaut Schedule Organizer");
            
            boolean running = true;
            while (running) {
                try {
                    displayMenu();
                    int choice = getUserChoice();
                    
                    switch (choice) {
                        case 1:
                            addTask();
                            break;
                        case 2:
                            viewTasks();
                            break;
                        case 3:
                            removeTask();
                            break;
                        case 4:
                            running = false;
                            break;
                        default:
                            System.out.println("Invalid choice. Please select 1-4.");
                    }
                    
                } catch (Exception e) {
                    logger.logError("Error in main application loop", e);
                    System.err.println("An error occurred: " + e.getMessage());
                    System.out.println("Please try again.");
                }
            }
            
            shutdown();
            
        } catch (Exception e) {
            logger.logError("Critical error in application", e);
            System.err.println("Critical application error: " + e.getMessage());
        }
    }
    
    /**
     * Displays the main menu
     */
    private void displayMenu() {
        System.out.println();
        System.out.println("=== ASTRONAUT SCHEDULE MENU ===");
        System.out.println("1. Add Task");
        System.out.println("2. View Tasks");
        System.out.println("3. Remove Task");
        System.out.println("4. Exit");
        System.out.println("==============================");
        System.out.print("Enter your choice (1-4): ");
    }
    
    /**
     * Gets user menu choice with input validation
     * @return User's menu choice
     */
    private int getUserChoice() {
        try {
            String input = scanner.nextLine().trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            logger.logWarning("Invalid menu choice entered");
            return -1; // Invalid choice
        }
    }
    
    /**
     * Handles adding a new task
     */
    private void addTask() {
        try {
            System.out.println();
            System.out.println("=== ADD NEW TASK ===");
            
            // Get task type
            String taskType = getTaskType();
            if (taskType == null) {
                return; // User cancelled or invalid input
            }
            
            // Get task name
            System.out.print("Enter task name: ");
            String taskName = scanner.nextLine().trim();
            if (taskName.isEmpty()) {
                System.out.println("Task name cannot be empty.");
                return;
            }
            
            // Check if task already exists
            if (scheduleManager.hasTask(taskName)) {
                System.out.println("A task with this name already exists: " + taskName);
                return;
            }
            
            // Get start time
            LocalTime startTime = getTimeInput("Enter start time (HH:MM format, e.g., 09:30): ");
            if (startTime == null) {
                return; // Invalid input
            }
            
            // Get end time
            LocalTime endTime = getTimeInput("Enter end time (HH:MM format, e.g., 11:30): ");
            if (endTime == null) {
                return; // Invalid input
            }
            
            // Validate time range
            if (startTime.isAfter(endTime) || startTime.equals(endTime)) {
                System.out.println("Start time must be before end time.");
                return;
            }
            
            // Create and add task
            Task task = taskFactory.createTask(taskType, taskName, startTime, endTime);
            scheduleManager.addTask(task);
            
            System.out.println();
            System.out.println("Task added successfully!");
            System.out.println("Task: " + task.toString());
            
        } catch (TaskException e) {
            System.err.println("Failed to add task: " + e.getMessage());
            logger.logError("Task addition failed", e);
        } catch (Exception e) {
            System.err.println("Unexpected error while adding task: " + e.getMessage());
            logger.logError("Unexpected error during task addition", e);
        }
    }
    
    /**
     * Gets task type from user input
     * @return Task type string or null if invalid
     */
    private String getTaskType() {
        try {
            System.out.println("Available task types:");
            String[] taskTypes = taskFactory.getTaskTypeNames();
            for (int i = 0; i < taskTypes.length; i++) {
                System.out.println((i + 1) + ". " + taskTypes[i]);
            }
            
            System.out.print("Select task type (1-" + taskTypes.length + "): ");
            String input = scanner.nextLine().trim();
            
            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= taskTypes.length) {
                    return taskTypes[choice - 1];
                } else {
                    System.out.println("Invalid choice. Please select 1-" + taskTypes.length);
                    return null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                return null;
            }
            
        } catch (Exception e) {
            logger.logError("Error getting task type", e);
            System.err.println("Error getting task type: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Gets time input from user with validation
     * @param prompt The prompt to display to user
     * @return LocalTime object or null if invalid
     */
    private LocalTime getTimeInput(String prompt) {
        try {
            System.out.print(prompt);
            String timeInput = scanner.nextLine().trim();
            
            return LocalTime.parse(timeInput, timeFormatter);
            
        } catch (DateTimeParseException e) {
            System.out.println("Invalid time format. Please use HH:MM format (e.g., 09:30)");
            logger.logWarning("Invalid time format entered: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("Error parsing time input: " + e.getMessage());
            logger.logError("Error parsing time input", e);
            return null;
        }
    }
    
    /**
     * Handles viewing all tasks
     */
    private void viewTasks() {
        try {
            scheduleManager.viewTasks();
        } catch (Exception e) {
            System.err.println("Error viewing tasks: " + e.getMessage());
            logger.logError("Error viewing tasks", e);
        }
    }
    
    /**
     * Handles removing a task
     */
    private void removeTask() {
        try {
            System.out.println();
            System.out.println("=== REMOVE TASK ===");
            
            if (scheduleManager.getTaskCount() == 0) {
                System.out.println("No tasks to remove. Schedule is empty.");
                return;
            }
            
            System.out.print("Enter the name of the task to remove: ");
            String taskName = scanner.nextLine().trim();
            
            if (taskName.isEmpty()) {
                System.out.println("Task name cannot be empty.");
                return;
            }
            
            scheduleManager.removeTask(taskName);
            System.out.println("Task removed successfully: " + taskName);
            
        } catch (TaskException e) {
            System.err.println("Failed to remove task: " + e.getMessage());
            logger.logError("Task removal failed", e);
        } catch (Exception e) {
            System.err.println("Unexpected error while removing task: " + e.getMessage());
            logger.logError("Unexpected error during task removal", e);
        }
    }
    
    /**
     * Handles application shutdown
     */
    private void shutdown() {
        try {
            System.out.println();
            System.out.println("=== APPLICATION SUMMARY ===");
            System.out.println("Total tasks in schedule: " + scheduleManager.getTaskCount());
            System.out.println("Total observers registered: " + scheduleManager.getObserverCount());
            System.out.println("==========================");
            System.out.println();
            System.out.println("Thank you for using Astronaut Schedule Organizer!");
            System.out.println("Mission Control out.");
            
            logger.logMessage("Application shutdown completed");
            
        } catch (Exception e) {
            logger.logError("Error during application shutdown", e);
        } finally {
            try {
                scanner.close();
            } catch (Exception e) {
                // Ignore scanner close errors
            }
        }
    }
}

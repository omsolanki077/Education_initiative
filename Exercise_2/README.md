# Astronaut Daily Schedule Organizer - Exercise 2

This project implements a console-based daily schedule organizer for astronauts using Java. It demonstrates the implementation of multiple design patterns (Singleton, Factory, Observer) along with SOLID principles, comprehensive exception handling, and logging.

## Project Structure

```
src/
├── AstronautScheduleOrganizer.java    # Main application class
├── model/
│   ├── Task.java                      # Abstract base task class
│   ├── ResearchTask.java              # Research task implementation
│   ├── ExerciseTask.java              # Exercise task implementation
│   └── MaintenanceTask.java           # Maintenance task implementation
├── factory/
│   └── TaskFactory.java               # Factory for creating tasks
├── manager/
│   └── ScheduleManager.java           # Singleton schedule manager
├── observer/
│   ├── ConflictObserver.java          # Observer interface
│   └── ConflictNotifier.java          # Conflict notification implementation
├── util/
│   └── Logger.java                    # Singleton logger utility
└── exception/
    └── TaskException.java             # Custom exception class
```

## Design Patterns Implemented

### 1. Singleton Pattern
- **ScheduleManager**: Ensures only one instance manages the daily schedule
- **Logger**: Provides centralized logging throughout the application
- Both use thread-safe double-checked locking implementation

### 2. Factory Pattern
- **TaskFactory**: Creates different types of tasks (Research, Exercise, Maintenance)
- Uses enum for type safety and extensibility
- Handles invalid task type exceptions

### 3. Observer Pattern
- **ConflictObserver Interface**: Defines contract for conflict notifications
- **ConflictNotifier**: Concrete observer that handles schedule conflict alerts
- **ScheduleManager**: Acts as subject, notifies observers when conflicts occur

## Key Features

### Task Management
- Add tasks with name, start time, end time, and type
- View all scheduled tasks in organized format
- Remove tasks by name
- Automatic conflict detection for overlapping time slots

### Conflict Detection
- Validates new tasks against existing schedule
- Prevents overlapping time slots
- Observer pattern notifies when conflicts are detected
- Tasks with conflicts are rejected and not added to schedule

### Exception Handling
- Custom TaskException for domain-specific errors
- Comprehensive try-catch blocks throughout
- Meaningful error messages for users
- Proper error logging for debugging

### Logging System
- Timestamped log messages for all operations
- Error logging with exception details
- Warning messages for potential issues
- Console-based output for easy monitoring

## SOLID Principles Applied

- **Single Responsibility**: Each class has one clear purpose
- **Open/Closed**: Factory can be extended with new task types
- **Liskov Substitution**: All task implementations are substitutable
- **Interface Segregation**: Small, focused interfaces
- **Dependency Inversion**: Depends on abstractions, not concretions

## How to Run

### Compile and run (Windows):
```bash
compile_and_run.bat
```

### Compile and run (Linux/Mac):
```bash
chmod +x compile_and_run.sh
./compile_and_run.sh
```

### Manual compilation:
```bash
javac -d out src/**/*.java src/*.java
java -cp out AstronautScheduleOrganizer
```

## Usage Instructions

1. **Start the application** - Run the main class
2. **Add Task** - Select option 1 from menu
   - Choose task type (Research, Exercise, Maintenance)
   - Enter task name
   - Enter start time (HH:MM format)
   - Enter end time (HH:MM format)
   - System will check for conflicts and add if no overlap
3. **View Tasks** - Select option 2 to see all scheduled tasks
4. **Remove Task** - Select option 3 and enter task name to remove
5. **Exit** - Select option 4 to quit application

## Sample Usage

```
=== ADD NEW TASK ===
Available task types:
1. RESEARCH
2. EXERCISE
3. MAINTENANCE
Select task type (1-3): 1
Enter task name: Mars Soil Analysis
Enter start time (HH:MM format, e.g., 09:30): 09:00
Enter end time (HH:MM format, e.g., 11:30): 11:00

Task added successfully!
Task: Research: Mars Soil Analysis (09:00 - 11:00) [120 min]
```

## Error Handling Examples

- **Time Overlap**: System detects conflicts and shows detailed notification
- **Invalid Time Format**: Prompts user to use correct HH:MM format
- **Invalid Task Type**: Shows available options and error message
- **Task Not Found**: Clear message when trying to remove non-existent task

## Requirements Met

- Console-based application with menu system
- All three design patterns implemented (Singleton, Factory, Observer)
- SOLID principles applied throughout
- Comprehensive exception handling with try-catch blocks
- Timestamped logging for all operations
- Task conflict detection and prevention
- Clean, maintainable code structure
- Professional error messages and user feedback

## Technical Features

- Thread-safe singleton implementations
- Time-based conflict detection algorithm
- Automatic task sorting by start time
- Input validation and sanitization
- Graceful error handling and recovery
- Comprehensive logging and monitoring

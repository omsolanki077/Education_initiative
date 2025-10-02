# Design Patterns Demo - Exercise 1

This project implements three fundamental design patterns in Java: Singleton, Factory, and Observer patterns. Each implementation includes proper exception handling and logging.

## Project Structure

```
src/
├── DesignPatternsDemo.java          # Main demo class
├── singleton/
│   ├── Logger.java                  # Singleton Logger service
│   └── SingletonDemo.java           # Singleton pattern demo
├── factory/
│   ├── Notification.java            # Notification interface
│   ├── NotificationException.java   # Custom exception
│   ├── EmailNotification.java       # Email implementation
│   ├── SMSNotification.java         # SMS implementation
│   ├── PushNotification.java        # Push notification implementation
│   ├── NotificationFactory.java     # Factory class
│   └── FactoryDemo.java             # Factory pattern demo
└── observer/
    ├── Subject.java                 # Subject interface
    ├── Observer.java                # Observer interface
    ├── ObserverException.java       # Custom exception
    ├── StockMarket.java             # Concrete Subject
    ├── Broker.java                  # Concrete Observer
    └── ObserverDemo.java            # Observer pattern demo
```

## Design Patterns Implemented

### 1. Singleton Pattern (Logger Service)
- **Purpose**: Ensures only one Logger instance exists throughout the application
- **Features**:
  - Thread-safe implementation with double-checked locking
  - Private constructor prevents external instantiation
  - Timestamped logging with exception handling
  - Error logging capabilities

### 2. Factory Pattern (Notification Factory)
- **Purpose**: Creates notification objects without exposing instantiation logic
- **Features**:
  - Interface-based design for extensibility
  - Three notification types: Email, SMS, Push
  - Type-safe enum-based factory method
  - Exception handling for invalid types

### 3. Observer Pattern (Stock Price Tracker)
- **Purpose**: Notifies multiple observers when stock prices change
- **Features**:
  - Subject-Observer interfaces for loose coupling
  - Thread-safe observer collection
  - Comprehensive stock price data handling
  - Broker-specific actions based on price changes

## Key Features

- **Clean Design**: Each class has a single, clear purpose
- **Extensible**: Factory can be extended with new notification types
- **Interface-based**: Uses interfaces for loose coupling
- **Exception Handling**: Proper error handling throughout
- **Thread Safety**: Singleton implementation is thread-safe

## Exception Handling

- Custom exceptions for each pattern domain
- Comprehensive try-catch blocks throughout
- Proper error logging and user feedback
- Graceful failure handling with meaningful messages

## How to Run

### Compile the project:
```bash
javac -d out src/**/*.java src/*.java
```

### Run the complete demo:
```bash
java -cp out DesignPatternsDemo
```

### Run individual pattern demos:
```bash
# Singleton Pattern
java -cp out singleton.SingletonDemo

# Factory Pattern
java -cp out factory.FactoryDemo

# Observer Pattern
java -cp out observer.ObserverDemo
```

## Sample Output

The demo produces colorful console output showing:
- Logger initialization and message timestamping
- Notification creation and sending simulation
- Stock price updates with broker reactions
- Exception handling demonstrations
- Comprehensive summary of implemented patterns

## Key Learning Points

1. **Singleton**: Controlled instantiation and global access
2. **Factory**: Object creation abstraction and extensibility
3. **Observer**: Loose coupling between subjects and observers
4. **Exception Handling**: Robust error management
5. **SOLID Principles**: Clean, maintainable code design

## Requirements Met

✅ Three design patterns implemented  
✅ Console-based logging throughout  
✅ Exception handling included  
✅ Short, clear implementations  
✅ Clean code structure  
✅ Easy to run and test  
✅ Well-documented code  

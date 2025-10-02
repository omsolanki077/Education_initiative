#!/bin/bash

echo "Compiling Astronaut Schedule Organizer..."
echo

# Create output directory
mkdir -p out

# Compile all Java files
javac -d out src/model/*.java src/factory/*.java src/util/*.java src/observer/*.java src/manager/*.java src/exception/*.java src/*.java

if [ $? -ne 0 ]; then
    echo "Compilation failed!"
    exit 1
fi

echo "Compilation successful!"
echo
echo "Running Astronaut Schedule Organizer..."
echo

# Run the main application
java -cp out AstronautScheduleOrganizer

echo
echo "Application completed!"

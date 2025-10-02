#!/bin/bash

echo "Compiling Design Patterns Demo..."
echo

# Create output directory
mkdir -p out

# Compile all Java files
javac -d out src/singleton/*.java src/factory/*.java src/observer/*.java src/*.java

if [ $? -ne 0 ]; then
    echo "Compilation failed!"
    exit 1
fi

echo "Compilation successful!"
echo
echo "Running Design Patterns Demo..."
echo

# Run the main demo
java -cp out DesignPatternsDemo

echo
echo "Demo completed!"

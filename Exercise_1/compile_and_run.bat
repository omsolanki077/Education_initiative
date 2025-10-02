@echo off
echo Compiling Design Patterns Demo...
echo.

REM Create output directory
if not exist "out" mkdir out

REM Compile all Java files
javac -d out src\singleton\*.java src\factory\*.java src\observer\*.java src\*.java

if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit /b 1
)

echo Compilation successful!
echo.
echo Running Design Patterns Demo...
echo.

REM Run the main demo
java -cp out DesignPatternsDemo

echo.
echo Demo completed!
pause

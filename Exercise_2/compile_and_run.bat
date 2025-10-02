@echo off
echo Compiling Astronaut Schedule Organizer...
echo.

REM Create output directory
if not exist "out" mkdir out

REM Compile all Java files
javac -d out src\model\*.java src\factory\*.java src\util\*.java src\observer\*.java src\manager\*.java src\exception\*.java src\*.java

if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit /b 1
)

echo Compilation successful!
echo.
echo Running Astronaut Schedule Organizer...
echo.

REM Run the main application
java -cp out AstronautScheduleOrganizer

echo.
echo Application completed!
pause

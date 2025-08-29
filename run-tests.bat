@echo off
echo ========================================
echo OrangeHRM Test Automation Framework
echo ========================================
echo.

:menu
echo Select an option:
echo.
echo === Test Execution Options ===
echo 1. Run All Tests (Default Suite)
echo 2. Run Smoke Tests Only
echo 3. Run Regression Tests Only
echo 4. Run Login Tests Only
echo 5. Run Dashboard Tests Only
echo.
echo === Browser-Specific Options ===
echo 6. Run Tests with Chrome
echo 7. Run Tests with Firefox
echo 8. Run Tests in Headless Mode
echo 9. Run Tests in Parallel Mode
echo.
echo === Utility Options ===
echo 10. Clean and Run All Tests
echo 11. Clean Project
echo 12. Install Dependencies
echo 0. Exit
echo.

set /p choice="Enter your choice (0-12): "

if "%choice%"=="1" goto runDefault
if "%choice%"=="2" goto runSmoke
if "%choice%"=="3" goto runRegression
if "%choice%"=="4" goto runLogin
if "%choice%"=="5" goto runDashboard
if "%choice%"=="6" goto runChrome
if "%choice%"=="7" goto runFirefox
if "%choice%"=="8" goto runHeadless
if "%choice%"=="9" goto runParallel
if "%choice%"=="10" goto cleanAndRun
if "%choice%"=="11" goto cleanProject
if "%choice%"=="12" goto installDeps
if "%choice%"=="0" goto exit
echo Invalid choice. Please try again.
echo.
goto menu

:runDefault
echo Running all tests with default suite...
mvn clean test
goto end

:runSmoke
echo Running smoke tests...
mvn clean test -Psmoke
goto end

:runRegression
echo Running regression tests...
mvn clean test -Pregression
goto end

:runLogin
echo Running login tests only...
mvn test -Dgroups=login
goto end

:runDashboard
echo Running dashboard tests only...
mvn test -Dgroups=dashboard
goto end

:runChrome
echo Running tests with Chrome...
mvn clean test -Pchrome
goto end

:runFirefox
echo Running tests with Firefox...
mvn clean test -Pfirefox
goto end

:runHeadless
echo Running tests in headless mode...
mvn clean test -Pheadless
goto end

:runParallel
echo Running tests in parallel mode...
mvn clean test -Pparallel
goto end

:cleanAndRun
echo Cleaning and running all tests...
mvn clean test
goto end

:cleanProject
echo Cleaning project...
mvn clean
echo Project cleaned successfully!
pause
goto menu

:installDeps
echo Installing dependencies...
mvn clean install -DskipTests
echo Dependencies installed successfully!
pause
goto menu

:end
echo.
echo Test execution completed!
echo.
echo === Report Locations ===
echo ExtentReports: test-output/ExtentReport.html
echo TestNG Reports: target/surefire-reports/
echo Screenshots: screenshots/
echo.
pause
goto menu

:exit
echo Goodbye!
pause

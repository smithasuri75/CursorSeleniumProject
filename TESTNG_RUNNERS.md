# TestNG Test Runners Documentation

This document provides comprehensive information about all available TestNG test runners in the OrangeHRM Selenium Test Automation Framework.

## 📁 Available Test Runners

### 1. **Main Test Runner** (`testng.xml`)
- **Purpose**: Complete test suite with all tests
- **Location**: `src/test/resources/testrunners/testng.xml`
- **Features**: 
  - All test groups (smoke, regression, login, dashboard, critical, functional)
  - Parallel execution (2 threads)
  - Comprehensive test coverage

### 2. **Smoke Test Runner** (`testng-smoke.xml`)
- **Purpose**: Quick validation of critical functionality
- **Location**: `src/test/resources/testrunners/testng-smoke.xml`
- **Features**:
  - Only critical tests (smoke + critical groups)
  - Single thread execution for stability
  - Fast execution for CI/CD pipelines

### 3. **Regression Test Runner** (`testng-regression.xml`)
- **Purpose**: Comprehensive testing of all functionality
- **Location**: `src/test/resources/testrunners/testng-regression.xml`
- **Features**:
  - All test groups included
  - 3 parallel threads for faster execution
  - Full test coverage

### 4. **Chrome Browser Runner** (`testng-chrome.xml`)
- **Purpose**: Tests specifically for Chrome browser
- **Location**: `src/test/resources/testrunners/testng-chrome.xml`
- **Features**:
  - Chrome browser configuration
  - 2 parallel threads
  - All test groups

### 5. **Firefox Browser Runner** (`testng-firefox.xml`)
- **Purpose**: Tests specifically for Firefox browser
- **Location**: `src/test/resources/testrunners/testng-firefox.xml`
- **Features**:
  - Firefox browser configuration
  - 2 parallel threads
  - All test groups

### 6. **Headless Test Runner** (`testng-headless.xml`)
- **Purpose**: CI/CD and server environments
- **Location**: `src/test/resources/testrunners/testng-headless.xml`
- **Features**:
  - Headless browser execution
  - 4 parallel threads for maximum speed
  - All test groups

### 7. **Parallel Test Runner** (`testng-parallel.xml`)
- **Purpose**: Maximum test execution speed
- **Location**: `src/test/resources/testrunners/testng-parallel.xml`
- **Features**:
  - 4 parallel threads
  - Headless execution
  - Optimized for speed

## 🚀 How to Run Tests

### Using Maven Commands

#### Run with Default Suite
```bash
mvn clean test
```

#### Run with Specific Profile
```bash
# Smoke tests
mvn clean test -Psmoke

# Regression tests
mvn clean test -Pregression

# Chrome browser
mvn clean test -Pchrome

# Firefox browser
mvn clean test -Pfirefox

# Headless execution
mvn clean test -Pheadless

# Parallel execution
mvn clean test -Pparallel
```

#### Run with Custom Parameters
```bash
# Custom browser
mvn test -Dbrowser=firefox

# Custom headless mode
mvn test -Dheadless=true

# Custom thread count
mvn test -DthreadCount=4

# Custom suite file
mvn test -DsuiteXmlFile=src/test/resources/testrunners/testng-smoke.xml
```

### Using Windows Batch File

Run `run-tests.bat` and select from the menu:
1. **Run All Tests (Default Suite)**
2. **Run Smoke Tests Only**
3. **Run Regression Tests Only**
4. **Run Login Tests Only**
5. **Run Dashboard Tests Only**
6. **Run Tests with Chrome**
7. **Run Tests with Firefox**
8. **Run Tests in Headless Mode**
9. **Run Tests in Parallel Mode**
10. **Clean and Run All Tests**
11. **Clean Project**
12. **Install Dependencies**

## 🔧 Test Groups

### Available Groups
- **`smoke`**: Critical functionality tests
- **`regression`**: Comprehensive functionality tests
- **`login`**: Login-related tests
- **`dashboard`**: Dashboard-related tests
- **`critical`**: Essential functionality tests
- **`functional`**: Detailed functionality tests

### Group Combinations
- **Smoke Tests**: `smoke + critical`
- **Regression Tests**: All groups
- **Critical Tests**: `critical` only
- **Functional Tests**: `functional` only

## 📊 Execution Configuration

### Parallel Execution
- **Default**: 2 threads
- **Smoke**: 1 thread (stability)
- **Regression**: 3 threads (balance)
- **Headless**: 4 threads (speed)
- **Parallel**: 4 threads (maximum speed)

### Browser Configuration
- **Chrome**: Default browser with options
- **Firefox**: Firefox-specific configuration
- **Edge**: Edge browser support
- **Safari**: macOS Safari support

### Headless Mode
- **Default**: `false` (visible browser)
- **CI/CD**: `true` (headless execution)
- **Performance**: Optimized for speed

## 🎯 Use Cases

### Development
```bash
# Quick validation
mvn test -Psmoke

# Full testing
mvn test -Pregression
```

### CI/CD Pipeline
```bash
# Fast feedback
mvn test -Psmoke

# Comprehensive testing
mvn test -Pheadless
```

### Cross-Browser Testing
```bash
# Chrome
mvn test -Pchrome

# Firefox
mvn test -Pfirefox

# All browsers (sequential)
mvn test -Pchrome && mvn test -Pfirefox
```

### Performance Testing
```bash
# Maximum speed
mvn test -Pparallel

# Balanced approach
mvn test -Pregression
```

## 📈 Performance Optimization

### Thread Count Guidelines
- **Development**: 1-2 threads
- **Testing**: 2-3 threads
- **CI/CD**: 3-4 threads
- **Performance**: 4+ threads

### Memory Configuration
```xml
<argLine>-Xmx1024m</argLine>
```

### Browser Options
- **Headless**: Faster execution
- **No-sandbox**: Linux compatibility
- **Disable-dev-shm-usage**: Memory optimization

## 🔍 Troubleshooting

### Common Issues

#### Tests Not Running
```bash
# Check suite file path
mvn test -DsuiteXmlFile=src/test/resources/testrunners/testng.xml

# Verify profile activation
mvn help:active-profiles
```

#### Parallel Execution Issues
```bash
# Reduce thread count
mvn test -DthreadCount=1

# Check for thread-safe code
# Verify WebDriver management
```

#### Browser Issues
```bash
# Check browser installation
# Verify WebDriver versions
# Check system compatibility
```

### Debug Mode
```bash
# Enable debug logging
mvn test -Dmaven.surefire.debug

# Verbose output
mvn test -Dverbose=true
```

## 📋 Best Practices

### Test Organization
1. **Group Tests Logically**: Use meaningful group names
2. **Separate Concerns**: Different runners for different purposes
3. **Maintain Consistency**: Follow naming conventions

### Execution Strategy
1. **Smoke First**: Run critical tests before full suite
2. **Parallel When Possible**: Use parallel execution for speed
3. **Browser Coverage**: Test across multiple browsers

### Maintenance
1. **Regular Updates**: Keep TestNG and dependencies updated
2. **Profile Management**: Organize profiles logically
3. **Documentation**: Keep runner configurations documented

## 🔄 Integration

### IDE Integration
- **Eclipse**: TestNG plugin
- **IntelliJ IDEA**: Built-in TestNG support
- **VS Code**: TestNG extension

### CI/CD Integration
- **Jenkins**: Maven integration
- **GitHub Actions**: Maven workflow
- **Azure DevOps**: Maven task

### Reporting Integration
- **ExtentReports**: HTML reports
- **TestNG Reports**: XML reports
- **Custom Listeners**: Additional reporting

---

**Note**: Always verify your test environment and dependencies before running tests. Use appropriate profiles for different scenarios and environments.

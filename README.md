# OrangeHRM Selenium Test Automation Framework

A comprehensive Java Selenium Maven project for testing the OrangeHRM application with TestNG and ExtentReports.

## 🚀 Features

- **Selenium WebDriver 4.15.0** - Latest Selenium version with enhanced capabilities
- **TestNG 7.7.1** - Powerful testing framework with parallel execution support
- **ExtentReports 5.0.9** - Beautiful HTML test reports with screenshots
- **WebDriverManager** - Automatic WebDriver management
- **Page Object Model (POM)** - Maintainable and scalable test structure
- **Configuration Management** - Externalized configuration for easy maintenance
- **Screenshot Capture** - Automatic screenshots on test failures
- **Multi-browser Support** - Chrome, Firefox, Edge, and Safari
- **Parallel Execution** - Configurable parallel test execution

## 🏗️ Project Structure

```
src/
├── main/java/com/qa/orangehrm/
│   ├── constants/
│   │   └── AppConstants.java          # Application constants and expected data
│   ├── factory/
│   │   └── DriverFactory.java         # WebDriver factory and management
│   ├── pages/
│   │   ├── BasePage.java              # Base page with common methods
│   │   ├── LoginPage.java             # Login page object
│   │   └── DashboardPage.java         # Dashboard page object
│   └── utils/
│       ├── ConfigReader.java          # Configuration file reader
│       ├── ExtentManager.java         # ExtentReports management
│       └── ScreenshotUtil.java        # Screenshot utilities
├── test/java/com/qa/orangehrm/
│   ├── base/
│   │   └── BaseTest.java              # Base test class
│   └── tests/
│       ├── LoginTest.java             # Login functionality tests
│       └── DashboardTest.java         # Dashboard functionality tests
└── test/resources/
    ├── config/
    │   └── config.properties          # Configuration properties
    └── testrunners/
        └── testng.xml                 # TestNG test runner configuration
```

## 📋 Prerequisites

- **Java 8 or higher**
- **Maven 3.6 or higher**
- **Chrome, Firefox, Edge, or Safari browser**
- **Internet connection** (for WebDriverManager to download drivers)

## 🛠️ Installation & Setup

### 1. Clone the Repository
```bash
git clone <repository-url>
cd selenium-test-automation
```

### 2. Install Dependencies
```bash
mvn clean install
```

### 3. Configure Browser Settings
Edit `src/test/resources/config/config.properties`:
```properties
# Browser Configuration
browser.name=chrome
browser.headless=false
browser.implicit.wait=10
```

## 🚀 Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run Specific Test Suite
```bash
# Run only smoke tests
mvn test -Dgroups=smoke

# Run only regression tests
mvn test -Dgroups=regression

# Run only login tests
mvn test -Dgroups=login

# Run only dashboard tests
mvn test -Dgroups=dashboard
```

### Run Tests with Specific Browser
```bash
mvn test -Dbrowser=firefox
```

### Run Tests in Headless Mode
```bash
mvn test -Dheadless=true
```

### Run Tests with Custom Configuration
```bash
mvn test -Dconfig.file=src/test/resources/config/custom-config.properties
```

## 📊 Test Reports

### ExtentReports
After test execution, find the HTML report at:
```
test-output/ExtentReport.html
```

### TestNG Reports
TestNG generates reports in:
```
target/surefire-reports/
```

## 🔧 Configuration

### Application Configuration
```properties
# Application URLs and Credentials
app.url=https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
app.username=Admin
app.password=admin123
```

### Browser Configuration
```properties
# Supported browsers: chrome, firefox, edge, safari
browser.name=chrome
browser.headless=false
browser.implicit.wait=10
browser.page.load.timeout=30
```

### Test Configuration
```properties
# Screenshot and reporting
test.screenshot.path=screenshots/
test.extent.report.path=test-output/ExtentReport.html
test.retry.count=1
```

## 🧪 Test Cases

### Login Tests
- ✅ Successful login with valid credentials
- ✅ Login failure with invalid credentials
- ✅ Login page elements validation
- ✅ Empty credentials validation
- ✅ Logout functionality

### Dashboard Tests
- ✅ Dashboard elements display after login
- ✅ Navigation menu functionality
- ✅ Page title and URL validation
- ✅ User dropdown functionality
- ✅ Dashboard refresh functionality

## 📱 Supported Browsers

| Browser | Status | Notes |
|---------|--------|-------|
| Chrome  | ✅     | Default browser, fully supported |
| Firefox | ✅     | Fully supported |
| Edge    | ✅     | Fully supported |
| Safari  | ⚠️     | Limited support (macOS only) |

## 🔄 Parallel Execution

The framework supports parallel test execution:
- **Method-level parallelism** (default: 2 threads)
- **Configurable thread count** via TestNG XML
- **Thread-safe WebDriver management**

## 📸 Screenshot Features

- **Automatic screenshots** on test failures
- **Optional screenshots** for successful tests
- **Timestamped filenames** for easy identification
- **Base64 encoding** for ExtentReports integration

## 🚨 Troubleshooting

### Common Issues

1. **WebDriver not found**
   - Ensure internet connection for WebDriverManager
   - Check browser installation

2. **Element not found**
   - Verify application is accessible
   - Check element locators in page objects

3. **Test failures**
   - Check ExtentReports for detailed error information
   - Verify screenshots in `screenshots/` folder

### Debug Mode
Enable debug logging:
```bash
mvn test -Dmaven.surefire.debug
```

## 📈 Performance Optimization

- **Implicit waits** for element visibility
- **Explicit waits** for specific conditions
- **Fluent waits** for complex scenarios
- **Parallel execution** for faster test runs

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 📞 Support

For questions and support:
- Create an issue in the repository
- Check the ExtentReports for test execution details
- Review the configuration files for setup issues

## 🔄 Updates

### Recent Changes
- Updated to Selenium 4.15.0
- Enhanced ExtentReports integration
- Improved parallel execution support
- Added comprehensive test coverage

### Planned Features
- API testing integration
- Mobile testing support
- Cloud testing integration (BrowserStack, Sauce Labs)
- Performance testing capabilities

---

**Happy Testing! 🎯**

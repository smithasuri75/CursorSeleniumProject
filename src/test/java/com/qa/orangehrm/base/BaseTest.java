package com.qa.orangehrm.base;

import com.qa.orangehrm.factory.DriverFactory;
import com.qa.orangehrm.utils.ExtentManager;
import com.qa.orangehrm.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

/**
 * Base Test class with common test setup and teardown methods
 */
public class BaseTest {
    
    protected WebDriver driver;
    
    /**
     * Setup method to initialize WebDriver before each test
     */
    @BeforeMethod(alwaysRun = true)
    public void setUp(ITestContext context) {
        // Initialize WebDriver
        driver = DriverFactory.initDriver();
        
        // Start ExtentReports test
        String testName = context.getCurrentXmlTest().getName();
        String testDescription = testName; // Use test name as description since getDescription() is not available
        ExtentManager.startTest(testName, testDescription);
        
        // Navigate to application
        driver.get(com.qa.orangehrm.utils.ConfigReader.getAppUrl());
    }
    
    /**
     * Teardown method to clean up after each test
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        try {
            // Capture screenshot on test failure
            if (result.getStatus() == ITestResult.FAILURE) {
                ScreenshotUtil.captureScreenshotOnFailure(result.getName());
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                // Capture screenshot for successful tests (optional)
                ScreenshotUtil.captureScreenshotForReport(result.getName());
            }
            
            // End ExtentReports test
            ExtentManager.endTest();
            ExtentManager.removeTest();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Quit WebDriver
            DriverFactory.quitDriver();
        }
    }
    
    /**
     * Setup method to initialize ExtentReports before test suite
     */
    @BeforeSuite(alwaysRun = true)
    public void setUpSuite() {
        // ExtentReports will be initialized when first test starts
    }
    
    /**
     * Teardown method to flush ExtentReports after test suite
     */
    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() {
        try {
            // Flush ExtentReports
            if (ExtentManager.getExtent() != null) {
                ExtentManager.getExtent().flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Setup method to initialize ExtentReports before test group
     */
    @BeforeGroups(alwaysRun = true)
    public void setUpGroups() {
        // Group setup if needed
    }
    
    /**
     * Teardown method after test group
     */
    @AfterGroups(alwaysRun = true)
    public void tearDownGroups() {
        // Group teardown if needed
    }
    
    /**
     * Get current WebDriver instance
     */
    protected WebDriver getDriver() {
        return driver;
    }
    
    /**
     * Navigate to specific URL
     */
    protected void navigateTo(String url) {
        driver.get(url);
    }
    
    /**
     * Get current page title
     */
    protected String getPageTitle() {
        return driver.getTitle();
    }
    
    /**
     * Get current page URL
     */
    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    /**
     * Wait for page to load
     */
    protected void waitForPageLoad() {
        try {
            Thread.sleep(2000); // Simple wait, can be enhanced with WebDriverWait
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

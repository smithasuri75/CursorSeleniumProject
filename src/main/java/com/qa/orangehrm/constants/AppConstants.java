
package com.qa.orangehrm.constants;

/**
 * Application Constants class to store all expected data and constants
 */
public class AppConstants {
    
    // Application URLs
    public static final String LOGIN_PAGE_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    public static final String DASHBOARD_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
    
    // Login Credentials
    public static final String DEFAULT_USERNAME = "Admin";
    public static final String DEFAULT_PASSWORD = "admin123";
    
    // Page Titles
    public static final String LOGIN_PAGE_TITLE = "OrangeHRM";
    public static final String DASHBOARD_PAGE_TITLE = "OrangeHRM";
    
    // Expected Messages
    public static final String LOGIN_SUCCESS_MESSAGE = "Dashboard";
    public static final String LOGIN_ERROR_MESSAGE = "Invalid credentials";
    public static final String LOGOUT_SUCCESS_MESSAGE = "Login";
    
    // Timeouts
    public static final int DEFAULT_TIMEOUT = 10;
    public static final int PAGE_LOAD_TIMEOUT = 30;
    public static final int SCRIPT_TIMEOUT = 30;
    
    // Browser Names
    public static final String CHROME_BROWSER = "chrome";
    public static final String FIREFOX_BROWSER = "firefox";
    public static final String EDGE_BROWSER = "edge";
    
    // Test Data
    public static final String EMPLOYEE_FIRST_NAME = "John";
    public static final String EMPLOYEE_LAST_NAME = "Doe";
    public static final String EMPLOYEE_ID = "EMP001";
    
    // File Paths
    public static final String SCREENSHOT_PATH = "screenshots/";
    public static final String EXTENT_REPORT_PATH = "test-output/ExtentReport.html";
    
    // Wait Constants
    public static final int EXPLICIT_WAIT = 10;
    public static final int FLUENT_WAIT_TIMEOUT = 10;
    public static final int FLUENT_WAIT_POLLING = 2;
}

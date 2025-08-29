package com.qa.orangehrm.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * ExtentReports Manager class to handle report configuration and management
 */
public class ExtentManager {
    
    private static ExtentReports extent;
    private static Map<Long, ExtentTest> testMap = new HashMap<>();
    
    /**
     * Initialize ExtentReports
     */
    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }
    
    /**
     * Create ExtentReports instance with configuration
     */
    private static void createInstance() {
        String reportPath = ConfigReader.getExtentReportPath();
        
        // Create directory if it doesn't exist
        File reportDir = new File(reportPath).getParentFile();
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }
        
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        
        // Configure spark reporter
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("OrangeHRM Test Automation Report");
        sparkReporter.config().setReportName("OrangeHRM Test Results");
        sparkReporter.config().setTimeStampFormat("dd/MM/yyyy HH:mm:ss");
        
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        
        // System information
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Application", "OrangeHRM");
        extent.setSystemInfo("URL", ConfigReader.getAppUrl());
        extent.setSystemInfo("Browser", ConfigReader.getBrowserName());
    }
    
    /**
     * Start test
     */
    public static ExtentTest startTest(String testName, String description) {
        ExtentTest test = getInstance().createTest(testName, description);
        testMap.put(Thread.currentThread().getId(), test);
        return test;
    }
    
    /**
     * Get current test
     */
    public static ExtentTest getCurrentTest() {
        return testMap.get(Thread.currentThread().getId());
    }
    
    /**
     * End test
     */
    public static void endTest() {
        ExtentTest test = getCurrentTest();
        if (test != null) {
            getInstance().flush();
        }
    }
    
    /**
     * Remove test from map
     */
    public static void removeTest() {
        testMap.remove(Thread.currentThread().getId());
    }
    
    /**
     * Get extent instance
     */
    public static ExtentReports getExtent() {
        return extent;
    }
}

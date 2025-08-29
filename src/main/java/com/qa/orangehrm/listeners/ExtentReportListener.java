package com.qa.orangehrm.listeners;

import com.qa.orangehrm.utils.ExtentManager;
import com.qa.orangehrm.utils.ScreenshotUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * ExtentReports Listener for TestNG integration
 */
public class ExtentReportListener implements ITestListener {
    
    @Override
    public void onTestStart(ITestResult result) {
        // Test started - ExtentReports test is already created in BaseTest
        System.out.println("Test Started: " + result.getName());
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        // Test passed
        if (ExtentManager.getCurrentTest() != null) {
            ExtentManager.getCurrentTest().pass("Test Passed: " + result.getName());
        }
        System.out.println("Test Passed: " + result.getName());
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        // Test failed
        String testName = result.getName();
        
        // Check if ExtentTest exists before using it
        if (ExtentManager.getCurrentTest() != null) {
            ExtentManager.getCurrentTest().fail("Test Failed: " + testName);
            
            // Log failure details
            if (result.getThrowable() != null) {
                ExtentManager.getCurrentTest().fail("Failure Reason: " + result.getThrowable().getMessage());
            }
        }
        
        // Capture screenshot on failure
        ScreenshotUtil.captureScreenshotOnFailure(testName);
        
        System.out.println("Test Failed: " + testName);
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        // Test skipped
        if (ExtentManager.getCurrentTest() != null) {
            ExtentManager.getCurrentTest().skip("Test Skipped: " + result.getName());
        }
        System.out.println("Test Skipped: " + result.getName());
    }
    
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Test failed but within success percentage (retry mechanism)
        if (ExtentManager.getCurrentTest() != null) {
            ExtentManager.getCurrentTest().warning("Test Failed but within success percentage: " + result.getName());
        }
        System.out.println("Test Failed but within success percentage: " + result.getName());
    }
    
    @Override
    public void onStart(ITestContext context) {
        // Test suite started
        System.out.println("Test Suite Started: " + context.getName());
    }
    
    @Override
    public void onFinish(ITestContext context) {
        // Test suite finished
        System.out.println("Test Suite Finished: " + context.getName());
        
        // Flush ExtentReports
        if (ExtentManager.getExtent() != null) {
            ExtentManager.getExtent().flush();
        }
    }
}

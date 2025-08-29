package com.qa.orangehrm.utils;

import com.qa.orangehrm.factory.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Screenshot utility class to capture screenshots during test execution
 */
public class ScreenshotUtil {
    
    /**
     * Capture screenshot and save to file
     */
    public static String captureScreenshot(String testName) {
        String screenshotPath = "";
        try {
            WebDriver driver = DriverFactory.getDriver();
            if (driver != null) {
                TakesScreenshot ts = (TakesScreenshot) driver;
                File source = ts.getScreenshotAs(OutputType.FILE);
                
                // Create screenshot directory if it doesn't exist
                String screenshotDir = ConfigReader.getScreenshotPath();
                File dir = new File(screenshotDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                
                // Generate unique filename with timestamp
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String fileName = testName + "_" + timestamp + ".png";
                screenshotPath = screenshotDir + fileName;
                
                File destination = new File(screenshotPath);
                FileUtils.copyFile(source, destination);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenshotPath;
    }
    
    /**
     * Capture screenshot and return as base64 string
     */
    public static String captureScreenshotAsBase64() {
        try {
            WebDriver driver = DriverFactory.getDriver();
            if (driver != null) {
                TakesScreenshot ts = (TakesScreenshot) driver;
                return ts.getScreenshotAs(OutputType.BASE64);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
    /**
     * Capture screenshot and attach to ExtentReports
     */
    public static void captureScreenshotForReport(String testName) {
        try {
            String screenshotPath = captureScreenshot(testName);
            if (!screenshotPath.isEmpty()) {
                // Attach screenshot to ExtentReports
                com.qa.orangehrm.utils.ExtentManager.getCurrentTest().addScreenCaptureFromPath(screenshotPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Capture screenshot on test failure
     */
    public static void captureScreenshotOnFailure(String testName) {
        try {
            String screenshotPath = captureScreenshot(testName + "_FAILED");
            if (!screenshotPath.isEmpty()) {
                // Attach failure screenshot to ExtentReports
                com.qa.orangehrm.utils.ExtentManager.getCurrentTest().fail("Test Failed - Screenshot captured")
                    .addScreenCaptureFromPath(screenshotPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

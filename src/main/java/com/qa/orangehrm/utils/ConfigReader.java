package com.qa.orangehrm.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Configuration Reader utility class to read properties from config file
 */
public class ConfigReader {
    
    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "src/test/resources/config/config.properties";
    
    static {
        try {
            properties = new Properties();
            FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH);
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file");
        }
    }
    
    /**
     * Get property value as String
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    /**
     * Get property value as Integer
     */
    public static int getIntProperty(String key) {
        String value = getProperty(key);
        return Integer.parseInt(value);
    }
    
    /**
     * Get property value as Boolean
     */
    public static boolean getBooleanProperty(String key) {
        String value = getProperty(key);
        return Boolean.parseBoolean(value);
    }
    
    /**
     * Get application URL
     */
    public static String getAppUrl() {
        return getProperty("app.url");
    }
    
    /**
     * Get username
     */
    public static String getUsername() {
        return getProperty("app.username");
    }
    
    /**
     * Get password
     */
    public static String getPassword() {
        return getProperty("app.password");
    }
    
    /**
     * Get browser name
     */
    public static String getBrowserName() {
        return getProperty("browser.name");
    }
    
    /**
     * Check if browser should run in headless mode
     */
    public static boolean isHeadless() {
        return getBooleanProperty("browser.headless");
    }
    
    /**
     * Get implicit wait time
     */
    public static int getImplicitWait() {
        return getIntProperty("browser.implicit.wait");
    }
    
    /**
     * Get page load timeout
     */
    public static int getPageLoadTimeout() {
        return getIntProperty("browser.page.load.timeout");
    }
    
    /**
     * Get script timeout
     */
    public static int getScriptTimeout() {
        return getIntProperty("browser.script.timeout");
    }
    
    /**
     * Get screenshot path
     */
    public static String getScreenshotPath() {
        return getProperty("test.screenshot.path");
    }
    
    /**
     * Get extent report path
     */
    public static String getExtentReportPath() {
        return getProperty("test.extent.report.path");
    }
    
    /**
     * Get retry count
     */
    public static int getRetryCount() {
        return getIntProperty("test.retry.count");
    }
    
    /**
     * Get fluent wait timeout
     */
    public static int getFluentWaitTimeout() {
        return getIntProperty("browser.fluent.wait.timeout");
    }
    
    /**
     * Get fluent wait polling interval
     */
    public static int getFluentWaitPolling() {
        return getIntProperty("browser.fluent.wait.polling");
    }
}

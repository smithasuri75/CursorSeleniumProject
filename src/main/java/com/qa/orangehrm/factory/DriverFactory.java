package com.qa.orangehrm.factory;

import com.qa.orangehrm.utils.ConfigReader;
// import io.github.bonigarcia.wdm.WebDriverManager; // Commented out - using Selenium Manager instead
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

/**
 * Driver Factory class to create and manage WebDriver instances
 */
public class DriverFactory {
    
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    
    /**
     * Initialize WebDriver based on configuration
     */
    public static WebDriver initDriver() {
        String browserName = ConfigReader.getBrowserName();
        
        if (driver.get() == null) {
            switch (browserName.toLowerCase()) {
                case "chrome":
                    // Using Selenium Manager (built-in WebDriver management)
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (ConfigReader.isHeadless()) {
                        chromeOptions.addArguments("--headless");
                    }
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--window-size=1920,1080");
                    driver.set(new ChromeDriver(chromeOptions));
                    break;
                    
                case "firefox":
                    // Using Selenium Manager (built-in WebDriver management)
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (ConfigReader.isHeadless()) {
                        firefoxOptions.addArguments("--headless");
                    }
                    driver.set(new FirefoxDriver(firefoxOptions));
                    break;
                    
                case "edge":
                    // Using Selenium Manager (built-in WebDriver management)
                    EdgeOptions edgeOptions = new EdgeOptions();
                    if (ConfigReader.isHeadless()) {
                        edgeOptions.addArguments("--headless");
                    }
                    driver.set(new EdgeDriver(edgeOptions));
                    break;
                    
                case "safari":
                    SafariOptions safariOptions = new SafariOptions();
                    driver.set(new SafariDriver(safariOptions));
                    break;
                    
                default:
                    throw new IllegalArgumentException("Browser " + browserName + " is not supported");
            }
            
            // Set timeouts
            driver.get().manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(ConfigReader.getImplicitWait()));
            driver.get().manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(ConfigReader.getPageLoadTimeout()));
            driver.get().manage().timeouts().scriptTimeout(java.time.Duration.ofSeconds(ConfigReader.getScriptTimeout()));
            
            // Maximize window
            driver.get().manage().window().maximize();
        }
        
        return driver.get();
    }
    
    /**
     * Get current WebDriver instance
     */
    public static WebDriver getDriver() {
        return driver.get();
    }
    
    /**
     * Quit and remove WebDriver instance
     */
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
    
    /**
     * Close current browser window
     */
    public static void closeDriver() {
        if (driver.get() != null) {
            driver.get().close();
        }
    }
}

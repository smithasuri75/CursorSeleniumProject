package com.qa.orangehrm.pages;

import com.qa.orangehrm.factory.DriverFactory;
import com.qa.orangehrm.utils.ConfigReader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Base Page class with common WebDriver operations and wait methods
 */
public class BasePage {
    
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected FluentWait<WebDriver> fluentWait;
    
    public BasePage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getImplicitWait()));
        this.fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(ConfigReader.getFluentWaitTimeout()))
                .pollingEvery(Duration.ofSeconds(ConfigReader.getFluentWaitPolling()))
                .ignoring(NoSuchElementException.class);
    }
    
    /**
     * Navigate to URL
     */
    public void navigateTo(String url) {
        driver.get(url);
    }
    
    /**
     * Get current page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    /**
     * Get current page URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    /**
     * Wait for element to be visible
     */
    public WebElement waitForElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    /**
     * Wait for element to be clickable
     */
    public WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    /**
     * Wait for element to be present
     */
    public WebElement waitForElementPresent(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    /**
     * Wait for page title to contain text
     */
    public boolean waitForPageTitleContains(String title) {
        return wait.until(ExpectedConditions.titleContains(title));
    }
    
    /**
     * Wait for URL to contain text
     */
    public boolean waitForUrlContains(String url) {
        return wait.until(ExpectedConditions.urlContains(url));
    }
    
    /**
     * Click element with wait
     */
    public void clickElement(By locator) {
        WebElement element = waitForElementClickable(locator);
        element.click();
    }
    
    /**
     * Send keys to element with wait
     */
    public void sendKeysToElement(By locator, String text) {
        WebElement element = waitForElementVisible(locator);
        element.clear();
        element.sendKeys(text);
    }
    
    /**
     * Get element text with wait
     */
    public String getElementText(By locator) {
        WebElement element = waitForElementVisible(locator);
        return element.getText();
    }
    
    /**
     * Get element attribute with wait
     */
    public String getElementAttribute(By locator, String attribute) {
        WebElement element = waitForElementPresent(locator);
        return element.getAttribute(attribute);
    }
    
    /**
     * Check if element is displayed
     */
    public boolean isElementDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    
    /**
     * Check if element is enabled
     */
    public boolean isElementEnabled(By locator) {
        try {
            return driver.findElement(locator).isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    
    /**
     * Find all elements
     */
    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }
    
    /**
     * Find single element
     */
    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }
    
    /**
     * Switch to frame by index
     */
    public void switchToFrame(int index) {
        driver.switchTo().frame(index);
    }
    
    /**
     * Switch to frame by name or id
     */
    public void switchToFrame(String nameOrId) {
        driver.switchTo().frame(nameOrId);
    }
    
    /**
     * Switch to default content
     */
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }
    
    /**
     * Accept alert
     */
    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
    
    /**
     * Dismiss alert
     */
    public void dismissAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
    }
    
    /**
     * Get alert text
     */
    public String getAlertText() {
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert().getText();
    }
    
    /**
     * Send keys to alert
     */
    public void sendKeysToAlert(String text) {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().sendKeys(text);
    }
    
    /**
     * Scroll to element
     */
    public void scrollToElement(By locator) {
        WebElement element = findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    /**
     * Execute JavaScript
     */
    public Object executeJavaScript(String script, Object... args) {
        return ((JavascriptExecutor) driver).executeScript(script, args);
    }
}

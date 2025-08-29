package com.qa.orangehrm.pages;

import com.qa.orangehrm.constants.AppConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Login Page class for OrangeHRM application
 */
public class LoginPage extends BasePage {
    
    // Page Locators
    private final By usernameField = By.name("username");
    private final By passwordField = By.name("password");
    private final By loginButton = By.xpath("//button[@type='submit']");
    private final By loginForm = By.className("orangehrm-login-form");
    private final By loginError = By.className("oxd-alert-content-text");
    private final By forgotPasswordLink = By.className("oxd-text--p");
    private final By orangeHRMLogo = By.className("orangehrm-login-branding");
    
    /**
     * Navigate to login page
     */
    public void navigateToLoginPage() {
        navigateTo(AppConstants.LOGIN_PAGE_URL);
    }
    
    /**
     * Enter username
     */
    public void enterUsername(String username) {
        sendKeysToElement(usernameField, username);
    }
    
    /**
     * Enter password
     */
    public void enterPassword(String password) {
        sendKeysToElement(passwordField, password);
    }
    
    /**
     * Click login button
     */
    public void clickLoginButton() {
        clickElement(loginButton);
    }
    
    /**
     * Login with credentials
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
    
    /**
     * Login with default credentials
     */
    public void loginWithDefaultCredentials() {
        login(AppConstants.DEFAULT_USERNAME, AppConstants.DEFAULT_PASSWORD);
    }
    
    /**
     * Check if login form is displayed
     */
    public boolean isLoginFormDisplayed() {
        return isElementDisplayed(loginForm);
    }
    
    /**
     * Check if login error is displayed
     */
    public boolean isLoginErrorDisplayed() {
        return isElementDisplayed(loginError);
    }
    
    /**
     * Get login error message
     */
    public String getLoginErrorMessage() {
        return getElementText(loginError);
    }
    
    /**
     * Check if forgot password link is displayed
     */
    public boolean isForgotPasswordLinkDisplayed() {
        return isElementDisplayed(forgotPasswordLink);
    }
    
    /**
     * Check if OrangeHRM logo is displayed
     */
    public boolean isOrangeHRMLogoDisplayed() {
        return isElementDisplayed(orangeHRMLogo);
    }
    
    /**
     * Get page title
     */
    public String getLoginPageTitle() {
        return getPageTitle();
    }
    
    /**
     * Get current URL
     */
    public String getLoginPageUrl() {
        return getCurrentUrl();
    }
    
    /**
     * Wait for login form to be visible
     */
    public void waitForLoginForm() {
        waitForElementVisible(loginForm);
    }
    
    /**
     * Wait for login error to be visible
     */
    public void waitForLoginError() {
        waitForElementVisible(loginError);
    }
    
    /**
     * Clear username field
     */
    public void clearUsername() {
        WebElement element = findElement(usernameField);
        element.clear();
    }
    
    /**
     * Clear password field
     */
    public void clearPassword() {
        WebElement element = findElement(passwordField);
        element.clear();
    }
    
    /**
     * Check if username field is enabled
     */
    public boolean isUsernameFieldEnabled() {
        return isElementEnabled(usernameField);
    }
    
    /**
     * Check if password field is enabled
     */
    public boolean isPasswordFieldEnabled() {
        return isElementEnabled(passwordField);
    }
    
    /**
     * Check if login button is enabled
     */
    public boolean isLoginButtonEnabled() {
        return isElementEnabled(loginButton);
    }
}

package com.qa.orangehrm.tests;

import com.qa.orangehrm.base.BaseTest;
import com.qa.orangehrm.constants.AppConstants;
import com.qa.orangehrm.pages.DashboardPage;
import com.qa.orangehrm.pages.LoginPage;
import com.qa.orangehrm.utils.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class for OrangeHRM Login functionality
 */
public class LoginTest extends BaseTest {
    
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    
    /**
     * Test successful login with valid credentials
     */
    @Test(description = "Verify successful login with valid credentials", groups = {"smoke", "login", "critical"})
    public void testSuccessfulLogin() {
        try {
            // Initialize page objects
            loginPage = new LoginPage();
            dashboardPage = new DashboardPage();
            
            // Wait for login page to load
            waitForPageLoad();
            
            // Wait for login form to be visible
            loginPage.waitForLoginForm();
            
            // Verify login page is loaded
            Assert.assertTrue(loginPage.isLoginFormDisplayed(), "Login form should be displayed");
            Assert.assertEquals(loginPage.getLoginPageTitle(), AppConstants.LOGIN_PAGE_TITLE, 
                "Login page title should match");
            
            // Perform login
            loginPage.loginWithDefaultCredentials();
            
            // Wait for dashboard to load
            waitForPageLoad();
            
            // Verify successful login
            Assert.assertTrue(dashboardPage.verifySuccessfulLogin(), "Dashboard should be loaded after successful login");
            Assert.assertTrue(dashboardPage.isDashboardLoaded(), "Dashboard should be displayed");
            Assert.assertEquals(dashboardPage.getDashboardPageTitle(), AppConstants.DASHBOARD_PAGE_TITLE, 
                "Dashboard page title should match");
            
            // Verify dashboard elements
            Assert.assertTrue(dashboardPage.isAdminMenuDisplayed(), "Admin menu should be displayed");
            Assert.assertTrue(dashboardPage.isPimMenuDisplayed(), "PIM menu should be displayed");
            Assert.assertTrue(dashboardPage.isLeaveMenuDisplayed(), "Leave menu should be displayed");
            
            ExtentManager.getCurrentTest().pass("Login test passed successfully");
            
        } catch (Exception e) {
            ExtentManager.getCurrentTest().fail("Login test failed: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Test login with invalid credentials
     */
    @Test(description = "Verify login fails with invalid credentials", groups = {"regression", "login", "functional"})
    public void testLoginWithInvalidCredentials() {
        try {
            // Initialize page objects
            loginPage = new LoginPage();
            
            // Wait for login page to load
            waitForPageLoad();
            
            // Wait for login form to be visible
            loginPage.waitForLoginForm();
            
            // Verify login page is loaded
            Assert.assertTrue(loginPage.isLoginFormDisplayed(), "Login form should be displayed");
            
            // Perform login with invalid credentials
            loginPage.login("InvalidUser", "InvalidPassword");
            
            // Wait for error message
            waitForPageLoad();
            
            // Verify error message is displayed
            Assert.assertTrue(loginPage.isLoginErrorDisplayed(), "Login error should be displayed");
            Assert.assertTrue(loginPage.getLoginErrorMessage().contains("Invalid"), 
                "Error message should contain 'Invalid'");
            
            // Verify still on login page
            Assert.assertTrue(loginPage.isLoginFormDisplayed(), "Should remain on login page");
            
            ExtentManager.getCurrentTest().pass("Invalid login test passed successfully");
            
        } catch (Exception e) {
            ExtentManager.getCurrentTest().fail("Invalid login test failed: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Test login page elements
     */
    @Test(description = "Verify all login page elements are displayed and enabled", groups = {"regression", "login", "functional"})
    public void testLoginPageElements() {
        try {
            // Initialize page objects
            loginPage = new LoginPage();
            
            // Wait for login page to load
            waitForPageLoad();
            
            // Wait for login form to be visible
            loginPage.waitForLoginForm();
            
            // Verify login page is loaded
            Assert.assertTrue(loginPage.isLoginFormDisplayed(), "Login form should be displayed");
            
            // Verify all elements are displayed
            Assert.assertTrue(loginPage.isOrangeHRMLogoDisplayed(), "OrangeHRM logo should be displayed");
            Assert.assertTrue(loginPage.isForgotPasswordLinkDisplayed(), "Forgot password link should be displayed");
            
            // Verify form fields are enabled
            Assert.assertTrue(loginPage.isUsernameFieldEnabled(), "Username field should be enabled");
            Assert.assertTrue(loginPage.isPasswordFieldEnabled(), "Password field should be enabled");
            Assert.assertTrue(loginPage.isLoginButtonEnabled(), "Login button should be enabled");
            
            // Verify page title and URL
            Assert.assertEquals(loginPage.getLoginPageTitle(), AppConstants.LOGIN_PAGE_TITLE, 
                "Login page title should match");
            Assert.assertTrue(loginPage.getLoginPageUrl().contains("auth/login"), 
                "Login page URL should contain 'auth/login'");
            
            ExtentManager.getCurrentTest().pass("Login page elements test passed successfully");
            
        } catch (Exception e) {
            ExtentManager.getCurrentTest().fail("Login page elements test failed: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Test login with empty credentials
     */
    @Test(description = "Verify login validation with empty credentials", groups = {"regression", "login", "functional"})
    public void testLoginWithEmptyCredentials() {
        try {
            // Initialize page objects
            loginPage = new LoginPage();
            
            // Wait for login page to load
            waitForPageLoad();
            
            // Wait for login form to be visible
            loginPage.waitForLoginForm();
            
            // Verify login page is loaded
            Assert.assertTrue(loginPage.isLoginFormDisplayed(), "Login form should be displayed");
            
            // Clear fields and try to login
            loginPage.clearUsername();
            loginPage.clearPassword();
            loginPage.clickLoginButton();
            
            // Wait for validation
            waitForPageLoad();
            
            // Verify still on login page (validation should prevent submission)
            Assert.assertTrue(loginPage.isLoginFormDisplayed(), "Should remain on login page");
            
            ExtentManager.getCurrentTest().pass("Empty credentials test passed successfully");
            
        } catch (Exception e) {
            ExtentManager.getCurrentTest().fail("Empty credentials test failed: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Test logout functionality
     */
    @Test(description = "Verify logout functionality works correctly", groups = {"smoke", "login", "functional"})
    public void testLogout() {
        try {
            // Initialize page objects
            loginPage = new LoginPage();
            dashboardPage = new DashboardPage();
            
            // Login first
            loginPage.loginWithDefaultCredentials();
            waitForPageLoad();
            
            // Verify dashboard is loaded
            Assert.assertTrue(dashboardPage.verifySuccessfulLogin(), "Dashboard should be loaded");
            
            // Perform logout
            dashboardPage.clickLogout();
            waitForPageLoad();
            
            // Verify returned to login page
            Assert.assertTrue(loginPage.isLoginFormDisplayed(), "Should return to login page after logout");
            Assert.assertEquals(loginPage.getLoginPageTitle(), AppConstants.LOGIN_PAGE_TITLE, 
                "Login page title should match after logout");
            
            ExtentManager.getCurrentTest().pass("Logout test passed successfully");
            
        } catch (Exception e) {
            ExtentManager.getCurrentTest().fail("Logout test failed: " + e.getMessage());
            throw e;
        }
    }
}

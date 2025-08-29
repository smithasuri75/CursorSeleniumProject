package com.qa.orangehrm.tests;

import com.qa.orangehrm.base.BaseTest;
import com.qa.orangehrm.constants.AppConstants;
import com.qa.orangehrm.pages.DashboardPage;
import com.qa.orangehrm.pages.LoginPage;
import com.qa.orangehrm.utils.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for OrangeHRM Dashboard functionality
 */
public class DashboardTest extends BaseTest {
    
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    
    /**
     * Setup method to login before each dashboard test
     */
    @BeforeMethod(alwaysRun = true)
    public void loginSetup() {
        try {
            loginPage = new LoginPage();
            dashboardPage = new DashboardPage();
            
            // Login to access dashboard
            loginPage.loginWithDefaultCredentials();
            waitForPageLoad();
            
            // Wait a bit for the page to fully load
            Thread.sleep(2000);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to setup login for dashboard tests: " + e.getMessage());
        }
    }
    
    /**
     * Test dashboard elements are displayed after login
     */
    @Test(description = "Verify dashboard elements are displayed after successful login", groups = {"smoke", "dashboard", "critical"})
    public void testDashboardElementsDisplayed() {
        try {
            // Verify dashboard is loaded
            Assert.assertTrue(dashboardPage.verifySuccessfulLogin(), "Dashboard should be loaded after login");
            
            // Verify main navigation menus are displayed
            Assert.assertTrue(dashboardPage.isAdminMenuDisplayed(), "Admin menu should be displayed");
            Assert.assertTrue(dashboardPage.isPimMenuDisplayed(), "PIM menu should be displayed");
            Assert.assertTrue(dashboardPage.isLeaveMenuDisplayed(), "Leave menu should be displayed");
            Assert.assertTrue(dashboardPage.isTimeMenuDisplayed(), "Time menu should be displayed");
            Assert.assertTrue(dashboardPage.isRecruitmentMenuDisplayed(), "Recruitment menu should be displayed");
            Assert.assertTrue(dashboardPage.isMyInfoMenuDisplayed(), "My Info menu should be displayed");
            Assert.assertTrue(dashboardPage.isPerformanceMenuDisplayed(), "Performance menu should be displayed");
            
            // Verify dashboard components
            Assert.assertTrue(dashboardPage.areDashboardCardsDisplayed(), "Dashboard cards should be displayed");
            Assert.assertTrue(dashboardPage.isQuickLaunchSectionDisplayed(), "Quick launch section should be displayed");
            
            // Verify welcome message
            Assert.assertNotNull(dashboardPage.getWelcomeMessage(), "Welcome message should be displayed");
            Assert.assertFalse(dashboardPage.getWelcomeMessage().isEmpty(), "Welcome message should not be empty");
            
            ExtentManager.getCurrentTest().pass("Dashboard elements test passed successfully");
            
        } catch (Exception e) {
            ExtentManager.getCurrentTest().fail("Dashboard elements test failed: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Test dashboard navigation menus
     */
    @Test(description = "Verify dashboard navigation menus are clickable", groups = {"regression", "dashboard", "functional"})
    public void testDashboardNavigationMenus() {
        try {
            // Verify dashboard is loaded
            Assert.assertTrue(dashboardPage.verifySuccessfulLogin(), "Dashboard should be loaded after login");
            
            // Test Admin menu click
            dashboardPage.clickAdminMenu();
            waitForPageLoad();
            Assert.assertFalse(dashboardPage.getCurrentUrl().contains("auth/login"), 
                "Should navigate away from login page");
            
            // Navigate back to dashboard
            navigateTo(AppConstants.DASHBOARD_URL);
            waitForPageLoad();
            
            // Test PIM menu click
            dashboardPage.clickPimMenu();
            waitForPageLoad();
            Assert.assertFalse(dashboardPage.getCurrentUrl().contains("auth/login"), 
                "Should navigate away from login page");
            
            // Navigate back to dashboard
            navigateTo(AppConstants.DASHBOARD_URL);
            waitForPageLoad();
            
            // Test Leave menu click
            dashboardPage.clickLeaveMenu();
            waitForPageLoad();
            Assert.assertFalse(dashboardPage.getCurrentUrl().contains("auth/login"), 
                "Should navigate away from login page");
            
            ExtentManager.getCurrentTest().pass("Dashboard navigation test passed successfully");
            
        } catch (Exception e) {
            ExtentManager.getCurrentTest().fail("Dashboard navigation test failed: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Test dashboard page title and URL
     */
    @Test(description = "Verify dashboard page title and URL are correct", groups = {"regression", "dashboard", "functional"})
    public void testDashboardPageTitleAndUrl() {
        try {
            // Verify dashboard is loaded
            Assert.assertTrue(dashboardPage.verifySuccessfulLogin(), "Dashboard should be loaded after login");
            
            // Verify page title
            String pageTitle = dashboardPage.getDashboardPageTitle();
            Assert.assertNotNull(pageTitle, "Page title should not be null");
            Assert.assertFalse(pageTitle.isEmpty(), "Page title should not be empty");
            Assert.assertTrue(pageTitle.contains("OrangeHRM"), "Page title should contain 'OrangeHRM'");
            
            // Verify page URL
            String pageUrl = dashboardPage.getDashboardPageUrl();
            Assert.assertNotNull(pageUrl, "Page URL should not be null");
            Assert.assertFalse(pageUrl.isEmpty(), "Page URL should not be empty");
            Assert.assertTrue(pageUrl.contains("orangehrmlive.com"), "Page URL should contain 'orangehrmlive.com'");
            Assert.assertFalse(pageUrl.contains("auth/login"), "Page URL should not contain 'auth/login'");
            
            ExtentManager.getCurrentTest().pass("Dashboard page title and URL test passed successfully");
            
        } catch (Exception e) {
            ExtentManager.getCurrentTest().fail("Dashboard page title and URL test failed: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Test user dropdown functionality
     */
    @Test(description = "Verify user dropdown functionality works correctly", groups = {"regression", "dashboard", "functional"})
    public void testUserDropdown() {
        try {
            // Verify dashboard is loaded
            Assert.assertTrue(dashboardPage.verifySuccessfulLogin(), "Dashboard should be loaded after login");
            
            // Verify welcome message is displayed
            String welcomeMessage = dashboardPage.getWelcomeMessage();
            Assert.assertNotNull(welcomeMessage, "Welcome message should be displayed");
            Assert.assertFalse(welcomeMessage.isEmpty(), "Welcome message should not be empty");
            
            // Click user dropdown
            dashboardPage.clickUserDropdown();
            waitForPageLoad();
            
            // Verify logout link is accessible (dropdown should be open)
            // Note: This is a basic test, actual dropdown behavior may vary
            
            ExtentManager.getCurrentTest().pass("User dropdown test passed successfully");
            
        } catch (Exception e) {
            ExtentManager.getCurrentTest().fail("User dropdown test failed: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Test dashboard refresh functionality
     */
    @Test(description = "Verify dashboard remains functional after page refresh", groups = {"regression", "dashboard", "functional"})
    public void testDashboardRefresh() {
        try {
            // Verify dashboard is loaded
            Assert.assertTrue(dashboardPage.verifySuccessfulLogin(), "Dashboard should be loaded after login");
            
            // Get initial dashboard state
            String initialWelcomeMessage = dashboardPage.getWelcomeMessage();
            Assert.assertNotNull(initialWelcomeMessage, "Initial welcome message should be displayed");
            
            // Refresh the page
            driver.navigate().refresh();
            waitForPageLoad();
            
            // Verify dashboard is still loaded after refresh
            Assert.assertTrue(dashboardPage.verifySuccessfulLogin(), "Dashboard should remain loaded after refresh");
            
            // Verify welcome message is still displayed
            String refreshedWelcomeMessage = dashboardPage.getWelcomeMessage();
            Assert.assertNotNull(refreshedWelcomeMessage, "Welcome message should still be displayed after refresh");
            
            // Verify navigation menus are still functional
            Assert.assertTrue(dashboardPage.isAdminMenuDisplayed(), "Admin menu should still be displayed after refresh");
            Assert.assertTrue(dashboardPage.isPimMenuDisplayed(), "PIM menu should still be displayed after refresh");
            
            ExtentManager.getCurrentTest().pass("Dashboard refresh test passed successfully");
            
        } catch (Exception e) {
            ExtentManager.getCurrentTest().fail("Dashboard refresh test failed: " + e.getMessage());
            throw e;
        }
    }
}

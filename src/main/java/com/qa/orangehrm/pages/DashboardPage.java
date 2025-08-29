package com.qa.orangehrm.pages;

import com.qa.orangehrm.constants.AppConstants;
import org.openqa.selenium.By;

/**
 * Dashboard Page class for OrangeHRM application
 */
public class DashboardPage extends BasePage {
    
    // Page Locators
    private final By dashboardHeader = By.className("oxd-topbar-header-breadcrumb");
    private final By welcomeMessage = By.className("oxd-userdropdown-name");
    private final By userDropdown = By.className("oxd-userdropdown-tab");
    private final By logoutLink = By.xpath("//a[contains(text(),'Logout')]");
    private final By adminMenu = By.xpath("//span[text()='Admin']");
    private final By pimMenu = By.xpath("//span[text()='PIM']");
    private final By leaveMenu = By.xpath("//span[text()='Leave']");
    private final By timeMenu = By.xpath("//span[text()='Time']");
    private final By recruitmentMenu = By.xpath("//span[text()='Recruitment']");
    private final By myInfoMenu = By.xpath("//span[text()='My Info']");
    private final By performanceMenu = By.xpath("//span[text()='Performance']");
    private final By dashboardCards = By.className("oxd-grid-item");
    private final By quickLaunchSection = By.className("quickLaunch");
    
    /**
     * Check if dashboard is loaded
     */
    public boolean isDashboardLoaded() {
        return isElementDisplayed(dashboardHeader);
    }
    
    /**
     * Get dashboard header text
     */
    public String getDashboardHeaderText() {
        return getElementText(dashboardHeader);
    }
    
    /**
     * Get welcome message
     */
    public String getWelcomeMessage() {
        return getElementText(welcomeMessage);
    }
    
    /**
     * Click user dropdown
     */
    public void clickUserDropdown() {
        clickElement(userDropdown);
    }
    
    /**
     * Click logout link
     */
    public void clickLogout() {
        clickUserDropdown();
        clickElement(logoutLink);
    }
    
    /**
     * Check if admin menu is displayed
     */
    public boolean isAdminMenuDisplayed() {
        return isElementDisplayed(adminMenu);
    }
    
    /**
     * Check if PIM menu is displayed
     */
    public boolean isPimMenuDisplayed() {
        return isElementDisplayed(pimMenu);
    }
    
    /**
     * Check if leave menu is displayed
     */
    public boolean isLeaveMenuDisplayed() {
        return isElementDisplayed(leaveMenu);
    }
    
    /**
     * Check if time menu is displayed
     */
    public boolean isTimeMenuDisplayed() {
        return isElementDisplayed(timeMenu);
    }
    
    /**
     * Check if recruitment menu is displayed
     */
    public boolean isRecruitmentMenuDisplayed() {
        return isElementDisplayed(recruitmentMenu);
    }
    
    /**
     * Check if my info menu is displayed
     */
    public boolean isMyInfoMenuDisplayed() {
        return isElementDisplayed(myInfoMenu);
    }
    
    /**
     * Check if performance menu is displayed
     */
    public boolean isPerformanceMenuDisplayed() {
        return isElementDisplayed(performanceMenu);
    }
    
    /**
     * Click admin menu
     */
    public void clickAdminMenu() {
        clickElement(adminMenu);
    }
    
    /**
     * Click PIM menu
     */
    public void clickPimMenu() {
        clickElement(pimMenu);
    }
    
    /**
     * Click leave menu
     */
    public void clickLeaveMenu() {
        clickElement(leaveMenu);
    }
    
    /**
     * Click time menu
     */
    public void clickTimeMenu() {
        clickElement(timeMenu);
    }
    
    /**
     * Click recruitment menu
     */
    public void clickRecruitmentMenu() {
        clickElement(recruitmentMenu);
    }
    
    /**
     * Click my info menu
     */
    public void clickMyInfoMenu() {
        clickElement(myInfoMenu);
    }
    
    /**
     * Click performance menu
     */
    public void clickPerformanceMenu() {
        clickElement(performanceMenu);
    }
    
    /**
     * Check if dashboard cards are displayed
     */
    public boolean areDashboardCardsDisplayed() {
        return isElementDisplayed(dashboardCards);
    }
    
    /**
     * Check if quick launch section is displayed
     */
    public boolean isQuickLaunchSectionDisplayed() {
        return isElementDisplayed(quickLaunchSection);
    }
    
    /**
     * Wait for dashboard to load
     */
    public void waitForDashboardToLoad() {
        waitForElementVisible(dashboardHeader);
    }
    
    /**
     * Wait for welcome message to be visible
     */
    public void waitForWelcomeMessage() {
        waitForElementVisible(welcomeMessage);
    }
    
    /**
     * Verify successful login
     */
    public boolean verifySuccessfulLogin() {
        waitForDashboardToLoad();
        return isDashboardLoaded() && getDashboardHeaderText().contains(AppConstants.LOGIN_SUCCESS_MESSAGE);
    }
    
    /**
     * Get current page title
     */
    public String getDashboardPageTitle() {
        return getPageTitle();
    }
    
    /**
     * Get current URL
     */
    public String getDashboardPageUrl() {
        return getCurrentUrl();
    }
}

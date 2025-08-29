package com.qa.orangehrm.listeners;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;

import java.util.List;
import java.util.Map;

/**
 * Custom TestNG Listener for additional reporting and monitoring
 */
public class TestNGListener implements IReporter {
    
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        
        System.out.println("=== TestNG Execution Report ===");
        System.out.println("Output Directory: " + outputDirectory);
        System.out.println("Number of Suites: " + suites.size());
        
        for (ISuite suite : suites) {
            System.out.println("\n--- Suite: " + suite.getName() + " ---");
            
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            for (Map.Entry<String, ISuiteResult> entry : suiteResults.entrySet()) {
                ISuiteResult suiteResult = entry.getValue();
                ITestContext testContext = suiteResult.getTestContext();
                
                System.out.println("Test: " + entry.getKey());
                System.out.println("  - Passed: " + testContext.getPassedTests().size());
                System.out.println("  - Failed: " + testContext.getFailedTests().size());
                System.out.println("  - Skipped: " + testContext.getSkippedTests().size());
                System.out.println("  - Total: " + testContext.getAllTestMethods().length);
                
                // Calculate execution time
                long startTime = testContext.getStartDate().getTime();
                long endTime = testContext.getEndDate().getTime();
                long duration = endTime - startTime;
                System.out.println("  - Duration: " + (duration / 1000) + " seconds");
            }
        }
        
        System.out.println("\n=== End of Report ===");
    }
}

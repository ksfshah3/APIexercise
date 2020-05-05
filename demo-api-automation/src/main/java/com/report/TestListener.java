package com.report;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

public class TestListener  implements ITestListener{
	
	private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
    
    /**
     * Before starting all tests, below method runs
     */
    @Override
    public void onStart(ITestContext iTestContext) {    	
    	 System.out.println("Started execution of test suite " + iTestContext.getSuite()); 
    }
 
 
    /**
     * After ending all tests, below method runs.
     */
    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("Finishing test suite " + iTestContext.getSuite());     
        ReportTestManager.endTest();
        ReportManager.getInstance().flush();       
    }
 
    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("Starting test " +  getTestMethodName(iTestResult) + " start");        
        ReportTestManager.startTest(iTestResult.getMethod().getMethodName(),
        		iTestResult.getInstance().getClass().getSimpleName());        
    }
 
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Test " +  getTestMethodName(iTestResult) + " has passed");
        ReportTestManager.getTest().log(Status.PASS, "Test passed");
    }
 
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Test " +  getTestMethodName(iTestResult) + " has failed"); 
        ReportTestManager.getTest().fail(iTestResult.getThrowable());
        
    }
 
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("Test "+  getTestMethodName(iTestResult) + " has skipped");
        ReportTestManager.getTest().log(Status.SKIP, "Test "+  getTestMethodName(iTestResult) + " has skipped");
    }
 
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }	
}

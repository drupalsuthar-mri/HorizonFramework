package com.mri.listeners;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.mri.util.ReportManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.*;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import static com.mri.factory.PlaywrightFactory.getScreenshotPath;

public class ReportListener implements ITestListener {
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    ExtentReports extent = ReportManager.extentReportGenerator();
    ExtentTest extentTest;

    public ReportListener() throws IOException {
    }


    public String getScreenshotPathInListener(String testCaseName) throws IOException, AWTException {
        try {
            String screenshotPath = getScreenshotPath(testCaseName);
            System.out.println("Screenshot path is not null");
            return getScreenshotPath(screenshotPath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void onStart(ITestContext context) {
        String testClassName = test.getClass().getName();
        System.out.println("Test suite Started:" + testClassName);
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        System.out.println("Test suite Finished:" + context.getName());
        extent.flush();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        Object[] params = result.getParameters();
        if (params != null && params.length > 0) {
            extentTest = extent.createTest(params[0].toString().replace("\"", ""));
            test.set(extentTest);
        } else {
            extentTest = extent.createTest(result.getMethod().getMethodName());
            test.set(extentTest);
        }
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
            test.get().getModel().setStartTime(getTime(result.getStartMillis()));
        test.get().log(Status.PASS, "Successful");
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        try {
            test.get().getModel().setEndTime(getTime(result.getEndMillis()));
            test.get().addScreenCaptureFromPath(getScreenshotPathInListener(result.getTestContext().getName()));
            test.get().log(Status.FAIL, "Test Failed");
            test.get().fail(result.getThrowable());
        } catch (IOException | AWTException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        try {
            System.out.println(result.getMethod().getMethodName() + " skipped!");
            test.get().getModel().setEndTime(getTime(result.getEndMillis()));
            test.get().addScreenCaptureFromPath(getScreenshotPathInListener(result.getTestContext().getName()));
            test.get().skip(result.getTestName());
            test.get().log(Status.SKIP, "Test Skipped");
        } catch (IOException | AWTException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        try {
            System.out.println(result.getMethod().getMethodName() + " failed but within success percentage!");
            test.get().getModel().setEndTime(getTime(result.getEndMillis()));
            test.get().addScreenCaptureFromPath(getScreenshotPathInListener(result.getTestContext().getName()));
            test.get().fail(result.getThrowable());
            test.get().log(Status.WARNING, "testFailedButWithinSuccessPercentage");
        } catch (IOException | AWTException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void onTestFailedWithTimeout(ITestResult result) {
        try {
            System.out.println(result.getMethod().getMethodName() + " failed with timeout!");
            test.get().getModel().setEndTime(getTime(result.getEndMillis()));
            test.get().addScreenCaptureFromPath(getScreenshotPathInListener(result.getTestContext().getName()));
            test.get().fail(result.getThrowable());
            test.get().log(Status.WARNING, "TimeOut");
        } catch (IOException | AWTException e) {
            throw new RuntimeException(e);
        }
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}
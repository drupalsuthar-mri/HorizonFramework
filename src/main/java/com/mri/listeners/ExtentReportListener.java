package com.mri.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.microsoft.playwright.Page;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

import static com.mri.factory.PlaywrightFactory.Screenshot;
import static com.mri.factory.PlaywrightFactory.getPage;

public class ExtentReportListener implements ITestListener {
    private static final String Output = "./test-output/Reports";
    private static final String ReportName = "extent.html";
    public static ExtentReports extent = init();
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
    private static ExtentReports extentReports;
    //private static String reportPath;

   public static ExtentReports init() {
        Path path = Paths.get(Output);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        extentReports = new ExtentReports();
        ExtentSparkReporter reporter = new ExtentSparkReporter(Output + "/" + ReportName);
        reporter.config().setReportName("Playwright Cucumber Test Report");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Tester", "Khushali");
        return extentReports;
    }

    @Override
    public synchronized void onStart(ITestContext context){
        System.out.println("Test Suite started: " + context.getName());
    }

    @Override
    public synchronized void onFinish(ITestContext context){
        System.out.println("Test Suite finished: " + context.getName());
        extent.flush();
        test.remove();
    }

    @Override
    public synchronized void onTestStart(ITestResult result){
        String methodName = result.getMethod().getMethodName();
        String qualifiedName = result.getMethod().getQualifiedName();
        int last = qualifiedName.lastIndexOf(".");
        int mid = qualifiedName.substring(0, last).lastIndexOf(".");
        String className = qualifiedName.substring(mid + 1, last);

        System.out.println(methodName + " started");
        ExtentTest extentTest =
                extent.createTest(result.getMethod().getMethodName(),
                        result.getMethod().getDescription());
        extentTest.assignCategory(result.getTestContext().getSuite().getName());
        extentTest.assignCategory(className);
        test.set(extentTest);
        test.get().getModel().setStartTime(getTime(result.getStartMillis()));
    }

    public synchronized void onTestSuccess(ITestResult result){
        System.out.println((result.getMethod().getMethodName() + " passed"));
        test.get().pass("Test passed");
        test.get().pass(result.getThrowable(),
                MediaEntityBuilder.createScreenCaptureFromPath(Screenshot()).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    public synchronized void onTestFailure(ITestResult result){
        System.out.println(result.getMethod().getMethodName() + " failed");
        byte[] bytes = getPage().screenshot(new Page.ScreenshotOptions().setFullPage(true));

        String base64 = Base64.getEncoder().encodeToString(bytes);

        test.get().fail(result.getThrowable(),
                MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
    }


    @Override
    public synchronized void onTestSkipped(ITestResult result){
        System.out.println(result.getMethod().getMethodName() +" skipped");
        test.get().skip(result.getThrowable(),
                MediaEntityBuilder.createScreenCaptureFromPath(Screenshot()).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result){
        System.out.println("onTestFailedButWithinSuccessPercentage: " + result.getMethod().getMethodName());
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}


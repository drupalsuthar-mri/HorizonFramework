package com.mri.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.mri.factory.PlaywrightFactory;
public class TestNGExtentReporter {
        public static ExtentReports report = null;
        public static ExtentSparkReporter sparkReporter = null;
        public static ExtentTest test;
        static ExtentReports extent;
        static boolean update = false;
        public static ExtentReports extentReportGenerator() throws IOException {

            Path reportPath = Paths.get(System.getProperty("user.dir") + "/MRITestExecutionReports/","cucumberExtentReports");
            // Step 3: Create the folder if it does not exist
            if (!Files.exists(reportPath)) {
                Files.createDirectories(reportPath);
            }
            String path = reportPath + "/extentReports/testNGExtentReports/html/extentReport_" + PlaywrightFactory.timeStamp() + ".html";
            ExtentHtmlReporter reporter = new ExtentHtmlReporter(path);
            reporter.config().setReportName("Horizon Automation Test Report");
            reporter.config().setDocumentTitle("Horizon Automation Test");
            reporter.config().getCss();
            reporter.config().setJs("document.getElementsByClassName('logo')[0].style.display='none';");

            String SparkPath = reportPath + "/extentReports/testNGExtentReports/spark/spark_" + PlaywrightFactory.timeStamp() + ".html";
            ExtentSparkReporter sparkDarkReporter = new ExtentSparkReporter(SparkPath);
            sparkDarkReporter.config().setCss("css-string");
            sparkDarkReporter.config().setDocumentTitle("Horizon Automation Test");
            sparkDarkReporter.config().setTimelineEnabled(true);
            sparkDarkReporter.config().setEncoding("utf-8");
            sparkDarkReporter.config().setJs("js-string");
            sparkDarkReporter.config().setProtocol(Protocol.HTTPS);
            sparkDarkReporter.config().setJs("document.getElementsByClassName('logo')[0].style.display='none';");
            sparkDarkReporter.config().setReportName("Horizon Automation Report");
            sparkDarkReporter.config().setTheme(Theme.DARK);
            sparkDarkReporter.config().getReporter();

            if (extent == null) {
                extent = new ExtentReports();
                extent.setSystemInfo("QA Name ", "Khushali Mehta");
                extent.setSystemInfo("os", "Windows");
                System.out.println("Reporter attached successfully.");
            } else {
                if (!update) {
                    // Assuming reporter and extent are initialized earlier
                    reporter.config().getReporter();
                    extent.attachReporter(reporter);
                    sparkDarkReporter.config().getReporter();
                    extent.attachReporter(sparkDarkReporter);
                    update = true;
                }
            }
            return extent;
        }
}

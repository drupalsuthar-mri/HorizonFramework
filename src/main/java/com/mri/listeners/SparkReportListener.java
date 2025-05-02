package com.mri.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class SparkReportListener {

        public static ExtentReports getReportObject() {
            String path = System.getProperty("user.dir") + "/test-output/spark.html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(path);
            reporter.config().setReportName("Playwright Cucumber Test Report");
            reporter.config().setDocumentTitle("Test Report");

            ExtentReports extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "Khushali");
            return extent;
        }
    }


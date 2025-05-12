package com.mri.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.mri.util.ScreenshotUtil.dateTime;

public class ReportManager {
    public static ExtentSparkReporter sparkReporter;
    public static ExtentReports extentReports;
    static ExtentReports extent;
    static boolean update = false;


    public static ExtentReports extentReportGenerator() throws IOException{
        Path reportPath = Paths.get(System.getProperty("user.dir") + "/MRIAutomationTestReports/");
        if(!Files.exists(reportPath)){
            Files.createDirectories(reportPath);
        }
        String path = reportPath + "/extentReports/html/extentReport_" + dateTime + ".html";
//        Extent Reports
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(path);
        htmlReporter.config().setReportName("MRI Horizon Automation Test Report");
        htmlReporter.config().setDocumentTitle("MRI Horizon Automation Report");
        htmlReporter.config().setTimelineEnabled(true);
        htmlReporter.config().setJs("js-string");
        htmlReporter.config().setCss("css-string");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setProtocol(Protocol.HTTPS);
        htmlReporter.config().setTheme(Theme.DARK);


//        Spark Reporter
        sparkReporter = new ExtentSparkReporter(path);
        sparkReporter.config().setReportName("MRI Horizon Automation Test Report");
        sparkReporter.config().setDocumentTitle("MRI Horizon Automation Report");
        sparkReporter.config().setTimelineEnabled(true);
        sparkReporter.config().setJs("js-string");
        sparkReporter.config().setCss("css-string");
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setProtocol(Protocol.HTTPS);
        sparkReporter.config().setTheme(Theme.DARK);

        if(extent == null){
            extent = new ExtentReports();
            extent.setSystemInfo("QA Name", "Drupal Suthar");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
        } else {
            if(!update) {
                htmlReporter.config().getReporter();
                extent.attachReporter(htmlReporter);
                sparkReporter.config().getReporter();
                extent.attachReporter(sparkReporter);
                update = true;
            }
        }
        return extent;
    }
}
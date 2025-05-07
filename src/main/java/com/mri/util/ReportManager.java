package com.mri.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.AbstractReporter;
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
        String path = reportPath + "/extentReports/html/sparkReport_" + dateTime + ".html";


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
        AbstractReporter reporter = sparkReporter.config().getReporter();
//        Extent Reports
        extentReports = new ExtentReports();
        extentReports.setSystemInfo("Environment", "QA");
        extentReports.setSystemInfo("Browser", "Chrome" );
        extentReports.setSystemInfo("Test Engineer", "Drupal Suthar");
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("OS Version", System.getProperty("os.version"));
        extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
        extentReports.attachReporter(sparkReporter);

        if(extent == null){
            extent = new ExtentReports();
            extent.setSystemInfo("QA Name", "Drupal Suthar");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("OS Version", System.getProperty("os.version"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.attachReporter(sparkReporter);
//            sparkReporter.loadXMLConfig(System.getProperty("user.dir") + "/src/test/resources/extent-config.xml");
        } else {
            if(!update) {
                extentReports.attachReporter(sparkReporter);
                sparkReporter.config().getReporter();
                extent.attachReporter(sparkReporter);
                update = true;
            }
        }
        return extentReports;
    }
}
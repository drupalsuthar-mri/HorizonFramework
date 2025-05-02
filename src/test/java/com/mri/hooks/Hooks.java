package com.mri.hooks;

import com.aventstack.extentreports.ExtentReports;
import com.microsoft.playwright.Page;
import com.mri.factory.PlaywrightFactory;
import com.mri.listeners.SparkReportListener;
import com.mri.pages.*;
import com.mri.util.ConfigReader;
import com.mri.util.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import com.aventstack.extentreports.ExtentTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Hooks {

    private final TestContext context;
    private PlaywrightFactory pf;
    private ConfigReader cr;
    private Properties prop;
    private Page page;
    public static LoginPage loginPage;
    public static MenuName menuName;
    public static Update update;
    public static AddProperty addProperty;
    public static AddUnit addUnit;
    public static ExtentReports extent;
    public static ExtentTest test;

    public Hooks(TestContext context) {
        this.context = context;
    }

        @Before
        public void setUp(Scenario scenario) {
            pf = new PlaywrightFactory();
            cr = new ConfigReader();
            prop = cr.initConfig();
            context.setProp(prop);
            page = pf.initBrowser(prop);
            context.setPage(page);
            context.setLoginPage(new LoginPage(page));
            extent = SparkReportListener.getReportObject();
            test = extent.createTest(scenario.getName());
        }

    public void MainMenu() {
        context.setMenuName(new MenuName(page));
    }

    public void update() {
        context.setUpdate(new Update(page));
    }

    public void addProperty() {
        context.setAddProperty(new AddProperty(page));
    }

    public void addUnit() {
        context.setAddUnit(new AddUnit(page));
    }

    public void accounting() {
        context.setAccounting(new Accounting(page));
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            // Take screenshot
            byte[] screenshot = context.getPage().screenshot();

            // Save screenshot to disk
            String screenshotPath = System.getProperty("user.dir") + "/test-output/screenshots/" + scenario.getName().replaceAll(" ", "_") + ".png";
            Files.write(Paths.get(screenshotPath), screenshot);

            // Attach to report
            test.fail("Scenario Failed").addScreenCaptureFromPath(screenshotPath);
        } else {
            test.pass("Scenario Passed");
        }

        if (context.getPage() != null) {
            context.getPage().close();
            extent.flush();
        }
    }
}

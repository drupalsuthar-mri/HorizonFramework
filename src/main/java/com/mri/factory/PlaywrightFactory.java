package com.mri.factory;

import com.microsoft.playwright.*;
import com.mri.util.TestNGExtentReporter;
import lombok.SneakyThrows;

import java.awt.*;
import java.io.IOException;
import java.nio.file.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class PlaywrightFactory {
    private static final ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> browserContext = new ThreadLocal<>();
    public static final ThreadLocal<Page> page = new ThreadLocal<>();
    private static final ThreadLocal<Playwright> playwright = new ThreadLocal<>();

    public static Properties prop = new Properties();
    public static boolean headless;

    public static Playwright getPlaywright() {
        return playwright.get();
    }

    public static Browser getBrowser() {
        return browser.get();
    }

    public static BrowserContext getBrowserContext() {
        return browserContext.get();
    }

    public static Page getPage() {
        return page.get();
    }

    @SneakyThrows
    public Page initBrowser(Properties properties) throws IOException {
        prop = properties; // store for use in static methods

        String browserName = prop.getProperty("browser", "chrome").trim();
        System.out.println("Browser name is: " + browserName);

        playwright.set(Playwright.create());

        switch (browserName.toLowerCase()) {
            case "chromium":
                browser.set(getPlaywright().chromium().launch(
                        new BrowserType.LaunchOptions().setHeadless(headless).setArgs(Collections.singletonList("--start-maximized")).setSlowMo(2000)));
                break;
            case "firefox":
                browser.set(getPlaywright().firefox().launch(
                        new BrowserType.LaunchOptions().setHeadless(headless).setArgs(Collections.singletonList("--start-maximized")).setSlowMo(2000)));
                break;
            case "safari":
                browser.set(getPlaywright().webkit().launch(
                        new BrowserType.LaunchOptions().setHeadless(headless).setArgs(Collections.singletonList("--start-maximized")).setSlowMo(2000)));
                break;
            case "chrome":
                browser.set(getPlaywright().chromium().launch(
                        new BrowserType.LaunchOptions().setHeadless(headless).setArgs(Collections.singletonList("--start-maximized")).setChannel("chrome")));
                break;
            case "edge":
                browser.set(getPlaywright().chromium().launch(
                        new BrowserType.LaunchOptions().setHeadless(headless).setChannel("msedge").setArgs(Collections.singletonList("--start-maximized")).setSlowMo(2000)));
                break;
            default:
                System.out.println("Please pass the correct browser name: " + browserName);
        }
        browserContext.set(getBrowser().newContext(
                new Browser.NewContextOptions().setViewportSize(null)
                        .setRecordVideoDir(Paths.get("videos"))
                        .setRecordVideoSize(1280, 720)
        ));
        page.set(getBrowserContext().newPage());
        getPage().navigate(prop.getProperty("url").trim());
        TestNGExtentReporter.extentReportGenerator();
        return getPage();
    }

    public static String timeStamp() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
        return dateFormat.format(new Date());
    }

    public String getScreenShotPath(String testName) throws IOException, AWTException {
        String path = System.getProperty("user.dir") + "/MRITestExecutionReports/screenShots/" + testName + "_" + PlaywrightFactory.timeStamp() + ".png";
        page.get().screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(path))
                .setFullPage(true));
        return "/MRITestExecutionReports/screenShots/" + testName + "_" + PlaywrightFactory.timeStamp() + ".png";
    }
}

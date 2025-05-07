package com.mri.factory;

import com.microsoft.playwright.*;
import com.mri.util.ReportManager;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Properties;

public class PlaywrightFactory {
    private static final ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> browserContext = new ThreadLocal<>();
    private static final ThreadLocal<Page> page = new ThreadLocal<>();
    private static final ThreadLocal<Playwright> playwright = new ThreadLocal<>();

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

    public Page initBrowser(Properties prop) throws IOException {
        String browserName = prop.getProperty("browser").trim();
        boolean headless = Boolean.parseBoolean(prop.getProperty("headless"));
        System.out.println("Browser name is: " + browserName);

        playwright.set(Playwright.create());
        switch (browserName.toLowerCase()){
            case "chromium":
                browser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setArgs(Collections.singletonList("--start-maximized")).setHeadless(headless)));
                break;
            case "firefox":
                browser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setArgs(Collections.singletonList("--start-maximized")).setHeadless(headless)));
                break;
            case "safari":
                browser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setArgs(Collections.singletonList("--start-maximized")).setHeadless(headless)));
                break;
            case "chrome":
                browser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setArgs(Collections.singletonList("--start-maximized")).setHeadless(headless).setChannel("chrome")));
                break;
            case "edge":
                browser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setArgs(Collections.singletonList("--start-maximized")).setHeadless(headless).setChannel(
                        "msedge")));
                break;
            default:
                System.out.println("Please pass the correct browser name: " + browserName);
        }

        browserContext.set(getBrowser().newContext(new Browser.NewContextOptions().setViewportSize(null)));
        page.set(getBrowserContext().newPage());
        getPage().navigate(prop.getProperty("url").trim());
        ReportManager.extentReportGenerator();
        return getPage();
    }

    public static String getScreenshotPath(String TestName) throws IOException, AWTException {
        String destinationPath = "/MRIAutomationTestReports" + "/screenshots/" + TestName + "_" + System.currentTimeMillis() + ".png";
        getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(System.getProperty("user.dir") + destinationPath)).setFullPage(true));
        return destinationPath;
    }
}
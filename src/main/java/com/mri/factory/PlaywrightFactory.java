package com.mri.factory;

import com.microsoft.playwright.*;

import java.util.Collections;
import java.util.Properties;

public class PlaywrightFactory {
    private static final ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> browserContext = new ThreadLocal<>();
    public static final ThreadLocal<Page> page = new ThreadLocal<>();
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

    public Page initBrowser(Properties prop){
        String browserName = prop.getProperty("browser").trim();
        System.out.println("Browser name is: " + browserName);

        playwright.set(Playwright.create());
        switch (browserName.toLowerCase()){
            case "chromium":
                browser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "firefox":
                browser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "safari":
                browser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "chrome":
                browser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setArgs(Collections.singletonList("--start-maximized")).setChannel("chrome")));
                break;
            case "edge":
                browser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel(
                        "msedge")));
                break;
            default:
                System.out.println("Please pass the correct browser name: " + browserName);
        }
        browserContext.set(getBrowser().newContext(
                new Browser.NewContextOptions().setViewportSize(null)
        ));
        //browserContext.set(getBrowser().newContext());
        page.set(getBrowserContext().newPage());
        getPage().navigate(prop.getProperty("url").trim());
        return getPage();
    }
}/**/
package com.mri.pages;

import com.microsoft.playwright.Page;

public class HomePage {
    private final Page page;
    private SideMenuHandler sideMenuHandler;

    Page.ScreenshotOptions screenshotOptions = new Page.ScreenshotOptions();

    public HomePage(Page page) {
        this.page = page;
    }

    public String getHomePageTitle() {
        return page.title();
    }

    public SideMenuHandler getSideMenuHandler() {
        if(sideMenuHandler == null){
            sideMenuHandler = new SideMenuHandler(page);
        }
        return sideMenuHandler;
    }


}
package com.mri.pages;

import com.microsoft.playwright.Page;
import com.mri.pages.handler.SideMenuHandler;

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

    public PropertyListPage navigateToPropertyListPage(){
        sideMenuHandler = new SideMenuHandler(page);
        sideMenuHandler.clickOnRequiredSideMenuName("DBM");
        System.out.println("DBM clicked");
        sideMenuHandler.clickOnRequiredSubMenuName("STC");
        System.out.println("STC clicked");
        sideMenuHandler.clickOnRequiredNextSubMenuName("PRO");
        System.out.println("PRO clicked");
        return new PropertyListPage(page);
    }


}
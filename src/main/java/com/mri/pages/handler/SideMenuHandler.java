package com.mri.pages.handler;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.function.BooleanSupplier;

public class SideMenuHandler {
    private final Page page;

    private final String sideMenuArrowIcon = "div.mri-menu div.main-menu-arrow-icon";
    private final String menuItems = "div.hzn-body #menu-level-one div[menu-code]";
    private final String closeBtn = ".hzn-dialog__header-icon-button";
    private final String levelTwoMenuTitle = ".hzn-body #menus #lvl-two-menu-title";
    private final String levelThreeMenuTitle = "#hzn-body .mri-menu #menu-level-1 #menu-level-1-title";
    private final String userPreferencesDialog = "#hzn-prompt-modal-form-0";
    private final String subMenuLocators = "#hzn-body #menu-level-0 li[mnopcode]";
    private final String nextSubMenuLocators = "#hzn-body .mri-menu #menu-level-1 li[mnopcode]";
    private Locator subMenuItems ;
    private Locator sideMenuItems ;
    private Locator nextSubMenuItems ;
    private List<String> menuNames;

    Page.ScreenshotOptions screenshotOptions = new Page.ScreenshotOptions();

    public SideMenuHandler(Page page) {
        this.page = page;
    }

    public void clickSideMenuArrowIcon() {
        page.click(sideMenuArrowIcon);
    }

    public boolean captureSideMenu() {
        try {

            sideMenuItems = page.locator(menuItems);
            System.out.println("Number of Menu Items: " + sideMenuItems.count());
            menuNames = sideMenuItems.allInnerTexts();
            for (String menuName : menuNames) {
                System.out.println("Menu Name: " + menuName);
            }
            for (int i = 0; i < sideMenuItems.count(); i++) {
                String sideMenuCode = sideMenuItems.nth(i).getAttribute("menu-code");
                String sideMenuName = menuNames.get(i);
                if (!sideMenuCode.equals("HOMEHUB") || sideMenuName.equals("User Preferences")) {
                    sideMenuItems.nth(i).click();
                    if(sideMenuName.equals("User Preferences")) {
                        page.waitForSelector(userPreferencesDialog);
                        page.screenshot(screenshotOptions.setPath(
                                Paths.get("./Menu_ss/User Preferences.png")));
                        page.click(closeBtn);
                        break;
                    }
                    BooleanSupplier isLevelTwoMenuVisible =
                            () -> sideMenuName.equals(page.locator(levelTwoMenuTitle).innerText());
                    page.waitForCondition(isLevelTwoMenuVisible);
                    if (Objects.equals(sideMenuCode, "PRM")) {
                        page.screenshot(screenshotOptions.setPath(
                                Paths.get("./Menu_ss/Process&Reports.png")));
                    }else {
                        page.screenshot(screenshotOptions.setPath(
                                Paths.get("./Menu_ss/" + sideMenuName + ".png")));
                    }
                } else {
                    continue;
                }


            }
            System.out.println("Side menu captured successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    public void clickOnRequiredSideMenuName(String menuCode) {
        page.locator(sideMenuArrowIcon).click();
        sideMenuItems = page.locator(menuItems);
        menuNames = sideMenuItems.allInnerTexts();
        for (int i = 0; i < sideMenuItems.count(); i++) {
            String sideMenuCode = sideMenuItems.nth(i).getAttribute("menu-code");
            String sideMenuName = menuNames.get(i);
            System.out.println("Menu Code: " + sideMenuCode + " - Menu Name: " + sideMenuName);
            if(sideMenuCode.equals(menuCode)) {
                sideMenuItems.nth(i).click();
                BooleanSupplier isLevelTwoMenuVisible =
                        () -> sideMenuName.equals(page.locator(levelTwoMenuTitle).innerText());
                page.waitForCondition(isLevelTwoMenuVisible);
                break;
            }
        }
    }

    public void clickOnRequiredSubMenuName(String menuCode) {
        subMenuItems = page.locator(subMenuLocators);
        menuNames = subMenuItems.allInnerTexts();
        for (int i = 0; i < subMenuItems.count(); i++) {
            String subMenuCode = subMenuItems.nth(i).getAttribute("mnopcode");
            String subMenuName = menuNames.get(i);
            if(subMenuCode.equals(menuCode)) {
                subMenuItems.nth(i).click();
                System.out.println("Sub Menu Code: " + subMenuCode + " - Sub Menu Name: " + subMenuName);
                BooleanSupplier isLevelThreeMenuVisible =
                        () -> subMenuName.equals(page.locator(levelThreeMenuTitle).innerText());
                page.waitForCondition(isLevelThreeMenuVisible);
                break;
            }
        }
    }

    public void clickOnRequiredNextSubMenuName(String menuCode) {
        nextSubMenuItems = page.locator(nextSubMenuLocators);
        menuNames = nextSubMenuItems.allInnerTexts();
        for (int i = 0; i < nextSubMenuItems.count(); i++) {
            String nextSubMenuCode = nextSubMenuItems.nth(i).getAttribute("mnopcode");
            String nextSubMenuName = menuNames.get(i);
            System.out.println("Next Sub Menu Code: " + nextSubMenuCode + " - Next Sub Menu Name: " + nextSubMenuName);
            if(nextSubMenuCode.equals(menuCode)) {
                nextSubMenuItems.nth(i).click();
                System.out.println("Next Sub Menu Code: " + nextSubMenuCode + " - Next Sub Menu Name: " + nextSubMenuName);
                break;
            }
        }
    }

}
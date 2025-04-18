package com.mri.pages;

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
    private final String userPreferencesDialog = "#hzn-prompt-modal-form-0";
    private Locator sideMenuItems;
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
                System.out.println("Menu Code: " + sideMenuCode);
                String sideMenuName = menuNames.get(i);
                System.out.println("Menu Name: " + sideMenuName);
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
            System.out.println("test completed successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return true;
    }

}
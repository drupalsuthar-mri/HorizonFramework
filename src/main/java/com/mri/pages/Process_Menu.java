package com.mri.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class Process_Menu {
    private final Page page;
    private final String MenuIcon = "menu-component div.mri-menu div.main-menu-arrow-icon";
    private final String MainMenu = "menu-component  .mri-menu #menus #main-menu #menu-level-one [menu-code='MNM']";
    private final String MenuHeader = "menu-component  .mri-menu #menus  #menu-level-0 #lvl-two-menu-title";
    private final String SubMenu = "menu-component .mri-menu #menus  #menu-level-0 #menu-level-0-body li ";
    private final String wizard ="//header[@class='mri-page-header wizard-header']//h1[1]";
    private final String SubMenuHeader = "menu-component .mri-menu #extra-menus  #menu-level-1 .inner-menu-header";
    private final String SubMenu1 = "menu-component .mri-menu #extra-menus  #menu-level-1  #menu-level-1-body li";
    private final String dataList = ".k-filter-row";
    private final String InnerFrame="#Hzn-FormFrame";
    private final String CloseBtn = ".fa-times .fa-times-click";

    private String getFrameSelector(int tabNumber) {
        return "iframe#hzn-tab-" + tabNumber;
    }
    public Process_Menu(Page page) {
        this.page = page;
    }
    public void ClickSubMenu() {
        page.click(MenuIcon);
        page.click(MainMenu);
        int m=1;

        Locator element1 = page.locator(MenuHeader);
        element1.waitFor(new Locator.WaitForOptions().setTimeout(5000));
        String text1 = element1.textContent(); // Get text content
        System.out.println("Menu_Header: " + text1); // Menu Name For Assertion

        int count1 = page.locator(SubMenu).count(); // SubMenu count
        System.out.println("SubMenu Count1: " + count1);

        for (int i = 0; i < count1; i++) {
            Locator subMenuItem = page.locator(SubMenu).nth(i);
            subMenuItem.scrollIntoViewIfNeeded();
            subMenuItem.click();

            boolean visibleLocator = false;
            try {
                visibleLocator = page.locator(SubMenuHeader).isVisible();
            } catch (Exception e) {
                System.out.println("SubMenuHeader visibility check failed: " + e.getMessage());
            }

            System.out.println("SubMenuHeader visible: " + visibleLocator);
            System.out.println("SubMenuItem: " + subMenuItem.textContent());


            if (visibleLocator) {
                int count2 = page.locator(SubMenu1).count();
                System.out.println("SubMenu1 Count2: " + count2);

                for (int j = 0; j < count2; j++) {
                    Locator subSubMenuItem = page.locator(SubMenu1).nth(j);
                    subSubMenuItem.scrollIntoViewIfNeeded();
                    System.out.println("SubSubMenuItem: " + subSubMenuItem.textContent());
                    subSubMenuItem.click();
                    try {
                        // Access both locators immediately

                        Locator dataListLocator = page.frameLocator(getFrameSelector(m)).locator(dataList);
                        Locator wizardLocator = page.frameLocator(getFrameSelector(m)).locator(wizard);
                        m++;
                        boolean dataListVisible = false;
                        boolean wizardVisible = false;

                        // Use short waits to simulate "parallel checking"
                        try {
                            dataListLocator.waitFor(new Locator.WaitForOptions().setTimeout(3000));
                            dataListVisible = dataListLocator.isVisible();
                        } catch (Exception e) {
                            System.out.println("DataList not found or not visible within short wait.");
                        }

                        try {
                            wizardLocator.waitFor(new Locator.WaitForOptions().setTimeout(3000));
                            wizardVisible = wizardLocator.isVisible();
                        } catch (Exception e) {
                            System.out.println("Wizard not found or not visible within short wait.");
                        }


                        System.out.println("DataList visible: " + dataListVisible);
                        if (dataListVisible) {
                            System.out.println("DataList is visible.");
                        } else if (wizardVisible) {
                            String wizardText = wizardLocator.textContent();
                            System.out.println("Wizard Name: " + wizardText);
                        } else {
                            System.out.println("Neither DataList nor Wizard is visible. Page might be loading or Access Denied.");
                        }
                    } catch (Exception e) {
                        System.out.println("Exception in parallel check: " + e.getMessage());
                    }

                    // Click all Close buttons
                    clickAllCloseButtons();
                    if (j < count2 - 1) {
                        page.click(MenuIcon);
                        page.click(MainMenu);
                        page.locator(SubMenu).nth(i).scrollIntoViewIfNeeded();
                        page.locator(SubMenu).nth(i).click();
                    }
                }
                clickAllCloseButtons(); // After all sub-submenus
            }
            else {
                try {
                    // Access both locators immediately

                    Locator dataListLocator = page.frameLocator(getFrameSelector(m)).locator(dataList);
                    Locator wizardLocator = page.frameLocator(getFrameSelector(m)).locator(wizard);
                    m++;
                    boolean dataListVisible = false;
                    boolean wizardVisible = false;

                    // Use short waits to simulate "parallel checking"
                    try {
                        dataListLocator.waitFor(new Locator.WaitForOptions().setTimeout(3000));
                        dataListVisible = dataListLocator.isVisible();
                    } catch (Exception e) {
                        System.out.println("DataList not found or not visible within short wait.");
                    }

                    try {
                        wizardLocator.waitFor(new Locator.WaitForOptions().setTimeout(3000));
                        wizardVisible = wizardLocator.isVisible();
                    } catch (Exception e) {
                        System.out.println("Wizard not found or not visible within short wait.");
                    }


                    System.out.println("DataList visible: " + dataListVisible);
                    if (dataListVisible) {
                        System.out.println("DataList is visible.");
                    } else if (wizardVisible) {
                        String wizardText = wizardLocator.textContent();
                        System.out.println("Wizard Name: " + wizardText);
                    } else {
                        System.out.println("Neither DataList nor Wizard is visible. Page might be loading or Access Denied.");
                    }
                } catch (Exception e) {
                    System.out.println("Exception in parallel check: " + e.getMessage());
                }
                clickAllCloseButtons(); // If SubMenuHeader was not visible
            }
            if (i < count1 - 1) {
                page.click(MenuIcon);
                page.click(MainMenu);
            }
        }
    }

    // Utility method to click all CloseBtn elements
    private void clickAllCloseButtons() {
        Locator closeButtons = page.locator(CloseBtn);
        int closeBtnCount = closeButtons.count();
        for (int k = 0; k < closeBtnCount; k++) {
            try {
                Locator btn = closeButtons.nth(k);
                if (btn.isVisible()) {
                    btn.click(new Locator.ClickOptions().setTimeout(3000));
                }
            } catch (Exception e) {
                System.out.println("Error clicking CloseBtn at index " + k + ": " + e.getMessage());
            }
        }
    }

}
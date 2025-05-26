package com.mri.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.sql.SQLOutput;

public class All_menus {
    private final Page page;
    private final String MenuIcon="menu-component div.mri-menu div.main-menu-arrow-icon";
    private final String Menu1="menu-component .mri-menu #menus #main-menu #menu-level-one .menu_item";
    private final String SubMenuHeader = "menu-component .mri-menu #menus  #menu-level-0 #lvl-two-menu-title";
    private final String Menu2="menu-component .mri-menu #menus  #menu-level-0 #menu-level-0-body li";
    private final String SubSubMenuheader="menu-component .mri-menu #extra-menus  #menu-level-1  #menu-level-1-title";
    private final String Menu3="menu-component .mri-menu #extra-menus  #menu-level-1  #menu-level-1-body li";
    private final String pageframe=".k-textbox";
    private final String Page = "//input[contains(@aria-label, 'Page')]";
    private final String InnerFrame = "iframe#HznFormFrame";
    private final String CloseBtn = ".fa-times .fa-times-click";
    private final String wizard = "//header[@class='mri-page-header wizard-header']//h1[1]";
    private final String form = ".k-webkit .hzn-update-body-scrollable .hzn-update-body #canvas-container #update-container #zoom-container #mProcDesc  #mProcDesc_Label";

    private String getFrameSelector(int tabNumber) {
        return "iframe#hzn-tab-" + tabNumber;
    }

    public All_menus(Page page) {
        this.page = page;
    }
    public void ClickSubMenu() {
        page.click(MenuIcon);
        int countMainMenu = page.locator(Menu1).count(); // MainMenu count
        int m = 1;
        System.out.println("MainMenu Count: " + countMainMenu);
        for(int i=2;i<countMainMenu-2;i++) {
            Locator mainMenu = page.locator(Menu1).nth(i);
            System.out.println("MainMenu: " + mainMenu.innerText());

            page.waitForTimeout(3000);
            mainMenu.click();
            page.waitForSelector(SubMenuHeader);
            Locator element1 = page.locator(SubMenuHeader);
            String text1 = element1.textContent(); // Get text content
            System.out.println("Menu_Header: " + text1); // Menu Name For Assertion

            int countSubMenu = page.locator(Menu2).count(); // SubMenu count
            System.out.println("SubMenu Count: " + countSubMenu);
            for(int j=0;j<countSubMenu;j++) {
                Locator subMenu = page.locator(Menu2).nth(j);
                subMenu.scrollIntoViewIfNeeded();
                System.out.println("SubMenu: " + subMenu.textContent());
                Locator caret = subMenu.locator(".mri-button__caret");
                boolean visibleLocator = false;
                try {
                    if (caret.isVisible()) {
                        visibleLocator = true;
                    } else {
                        visibleLocator = false;
                    }
                } catch (Exception e) {
                    System.out.println("SubMenuHeader visibility check failed: " + e.getMessage());
                }
                System.out.println("SubMenuHeader visibility: " + visibleLocator);
                page.waitForTimeout(3000);
                Page newTab1 = null;
                try {
                    newTab1 = page.waitForPopup(() -> {
                        page.waitForSelector(Menu2);
                        subMenu.click(); // or subSubMenu.click();
                    });
                    handleNewTab(newTab1);
                    continue;
                } catch (Exception error) {
                    System.out.println("No new tab opened. Continuing with current page.");
                }
                if(visibleLocator){

                    int count = page.locator(Menu3).count(); // SubMenu count
                    System.out.println("Menu3 Count: " + count);
                    for(int k=0;k<count;k++) {
                        Locator subMenu2Header = page.locator(SubSubMenuheader);
                        subMenu.scrollIntoViewIfNeeded();
                        System.out.println("SubSubMenuheader: " + subMenu2Header.textContent());
                        Locator subSubMenu = page.locator(Menu3).nth(k);
                        System.out.println("SubSubMenu: " + subSubMenu.textContent());
                        if(count == 0){
                            continue;
                        }
                        Page newTab2 = null;
                        try {
                            newTab2 = page.waitForPopup(() -> {
                                subSubMenu.click(); // or subSubMenu.click();
                            });
                            handleNewTab(newTab2);
                            page.click(MenuIcon);
                            mainMenu.click();
                            page.locator(Menu2).nth(j).scrollIntoViewIfNeeded();
                            page.locator(Menu2).nth(j).click();
                            continue;
                        } catch (Exception error) {
                            System.out.println("No new tab opened. Continuing with current page.");
                        }
                        try {
                            System.out.println("Start frame check...");

                            // Try single frame
                            try {
                                Locator pageNum = page.frameLocator(getFrameSelector(m)).locator(pageframe).locator(Page);
                                if (pageNum != null && pageNum.isVisible()) {
                                    String ariaLabel = pageNum.getAttribute("aria-label");
                                    if (ariaLabel != null) {
                                        int pageNumber = Integer.parseInt(ariaLabel.replace("Page ", "").trim());
                                        System.out.println("Page Number (Single Frame): " + pageNumber);
                                    } else {
                                        System.out.println("aria-label in single frame is null.");
                                    }
                                } else {
                                    throw new Exception("Page element not visible in single frame.");
                                }
                            } catch (Exception eSingle) {
                                System.out.println("Single frame failed: " + eSingle.getMessage());

                                // Try double frame if single frame fails
                                try {
                                    Locator doubleframe = page.frameLocator(getFrameSelector(m)).locator(InnerFrame);
                                    int Dcount = doubleframe.count();
                                    System.out.println("Dcount: " + Dcount);
                                    if (Dcount > 0) {
                                        Locator doublePageLocator = page.frameLocator(getFrameSelector(m))
                                                .frameLocator(InnerFrame).locator(pageframe).locator(Page);
                                        if (doublePageLocator != null && doublePageLocator.isVisible()) {
                                            String doublePageAria = doublePageLocator.getAttribute("aria-label");
                                            if (doublePageAria != null) {
                                                int pageNumber = Integer.parseInt(doublePageAria.replace("Page ", "").trim());
                                                System.out.println("Page Number (Double Frame): " + pageNumber);
                                            } else {
                                                System.out.println("aria-label in double frame is null.");
                                            }
                                        } else {
                                            throw new Exception("Page element not visible in double frame.");
                                        }
                                    } else {
                                        throw new Exception("No double frame found.");
                                    }
                                } catch (Exception eDouble) {
                                    System.out.println("Double frame failed: " + eDouble.getMessage());

                                    // Try wizard if both single and double frames fail
                                    try {
                                        Locator wizardLocator = page.frameLocator(getFrameSelector(m)).locator(wizard);
                                        if (wizardLocator.isVisible()) {
                                            System.out.println("Wizard Name: " + wizardLocator.textContent());
                                        } else {
                                            throw new Exception("Wizard not visible.");
                                        }
                                    } catch (Exception eWizard) {
                                        System.out.println("Wizard check failed: " + eWizard.getMessage());

                                        // Try form report if all above fail
                                        try {
                                            Locator formReport = page.frameLocator(getFrameSelector(m)).locator(form);
                                            if (formReport.isVisible()) {
                                                System.out.println("Form Report: " + formReport.textContent());
                                            } else {
                                                System.out.println("Form Report not visible.");
                                            }
                                        } catch (Exception eForm) {
                                            System.out.println("Form Report check failed: " + eForm.getMessage());
                                        }
                                    }
                                }
                            }

                        } catch (Exception e) {
                            System.out.println("Unexpected top-level error: " + e.getMessage());
                            e.printStackTrace();
                        }
                        m++;
                        clickAllCloseButtons();
                        System.out.println(k);
                        if (k <= count) {
                            page.click(MenuIcon);
                            mainMenu.click();
                            page.locator(Menu2).nth(j).scrollIntoViewIfNeeded();
                            page.locator(Menu2).nth(j).click();
                        }
                    }
                }
                else {
                    try {
                        System.out.println("Start frame check...");

                        // Try single frame
                        try {
                            Locator pageNum = page.frameLocator(getFrameSelector(m)).locator(Page);
                            if (pageNum != null && pageNum.isVisible()) {
                                String ariaLabel = pageNum.getAttribute("aria-label");
                                if (ariaLabel != null) {
                                    int pageNumber = Integer.parseInt(ariaLabel.replace("Page ", "").trim());
                                    System.out.println("Page Number (Single Frame): " + pageNumber);
                                } else {
                                    System.out.println("aria-label in single frame is null.");
                                }
                            } else {
                                throw new Exception("Page element not visible in single frame.");
                            }
                        } catch (Exception eSingle) {
                            System.out.println("Single frame failed: " + eSingle.getMessage());

                            // Try double frame if single frame fails
                            try {
                                Locator doubleframe = page.frameLocator(getFrameSelector(m)).locator(InnerFrame);
                                int Dcount = doubleframe.count();
                                System.out.println("Dcount: " + Dcount);

                                if (Dcount > 0) {
                                    Locator doublePageLocator = page.frameLocator(getFrameSelector(m))
                                            .frameLocator(InnerFrame).locator(Page);
                                    if (doublePageLocator != null && doublePageLocator.isVisible()) {
                                        String doublePageAria = doublePageLocator.getAttribute("aria-label");
                                        if (doublePageAria != null) {
                                            int pageNumber = Integer.parseInt(doublePageAria.replace("Page ", "").trim());
                                            System.out.println("Page Number (Double Frame): " + pageNumber);
                                        } else {
                                            System.out.println("aria-label in double frame is null.");
                                        }
                                    } else {
                                        throw new Exception("Page element not visible in double frame.");
                                    }
                                } else {
                                    throw new Exception("No double frame found.");
                                }
                            } catch (Exception eDouble) {
                                System.out.println("Double frame failed: " + eDouble.getMessage());

                                // Try wizard if both single and double frames fail
                                try {
                                    Locator wizardLocator = page.frameLocator(getFrameSelector(m)).locator(wizard);
                                    if (wizardLocator.isVisible()) {
                                        System.out.println("Wizard Name: " + wizardLocator.textContent());
                                    } else {
                                        throw new Exception("Wizard not visible.");
                                    }
                                } catch (Exception eWizard) {
                                    System.out.println("Wizard check failed: " + eWizard.getMessage());

                                    // Try form report if all above fail
                                    try {
                                        Locator formReport = page.frameLocator(getFrameSelector(m)).locator(form);
                                        if (formReport.isVisible()) {
                                            System.out.println("Form Report: " + formReport.textContent());
                                        } else {
                                            System.out.println("Form Report not visible.");
                                        }
                                    } catch (Exception eForm) {
                                        System.out.println("Form Report check failed: " + eForm.getMessage());
                                    }
                                }
                            }
                        }

                    } catch (Exception e) {
                        System.out.println("Unexpected top-level error: " + e.getMessage());
                        e.printStackTrace();
                    }
                    m++;
                    clickAllCloseButtons();
                    if (j < countMainMenu - 1 ) {
                        page.click(MenuIcon);
                        mainMenu.click();
                    }
                }


            }
        }
    }
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
    private void handleNewTab(Page newTab) {
        try {
            newTab.waitForLoadState();
            System.out.println("New Tab URL: " + newTab.url());
            // You can add more logic like verifying headers, text, etc.
            newTab.close(); // Close the new tab when done
        } catch (Exception e) {
            System.out.println("Error handling new tab: " + e.getMessage());
        }
    }

}

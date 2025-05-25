package com.mri.pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import net.bytebuddy.asm.Advice;

public class Process_Menu {
    private final Page page;
    private final String MenuIcon = "menu-component div.mri-menu div.main-menu-arrow-icon";
    private final String MainMenu = "menu-component  .mri-menu #menus #main-menu #menu-level-one .menu_item";
    private final String SubMenuHeader = "menu-component  .mri-menu #menus  #menu-level-0 #lvl-two-menu-title";
    private final String SubMenu1 = "menu-component .mri-menu #menus  #menu-level-0 #menu-level-0-body li ";
    private final String Page = "//input[contains(@aria-label, 'Page')]";
    //private final String AccessDenied = ".mri-heading--1";
    private final String wizard = "//header[@class='mri-page-header wizard-header']//h1[1]";
    private final String SubMenuHeader2Icon = "menu-component .mri-menu #menus #menu-level-0 #menu-level-0-body";
    private final String SubMenu2 = "menu-component .mri-menu #extra-menus  #menu-level-1  #menu-level-1-body li";
    // private final String dataList = ".k-filter-row";
    private final String InnerFrame = "iframe#HznFormFrame";
    private final String CloseBtn = ".fa-times .fa-times-click";

    private String getFrameSelector(int tabNumber) {
        return "iframe#hzn-tab-" + tabNumber;
    }

    public Process_Menu(Page page) {
        this.page = page;
    }

    public void ClickSubMenu() {
        page.click(MenuIcon);
        int countMainMenu = page.locator(MainMenu).count(); // MainMenu count
        int m = 1;
        System.out.println("MainMenu Count: " + countMainMenu);
        for (int main = 3; main < countMainMenu - 2; main++) {
            Locator mainMenuItem = page.locator(MainMenu).nth(main);
            mainMenuItem.scrollIntoViewIfNeeded();
            mainMenuItem.click();
            System.out.println("MainMenuItem: " + mainMenuItem.textContent());
            Locator element1 = page.locator(SubMenuHeader);
            String text1 = element1.textContent(); // Get text content
            System.out.println("Menu_Header: " + text1); // Menu Name For Assertion

            int count1 = page.locator(SubMenu1).count(); // SubMenu count
            System.out.println("SubMenu Count1: " + count1);

            for (int i = 7; i < count1; i++) {
                Locator subMenuItem = page.locator(SubMenu1).nth(i);
                subMenuItem.scrollIntoViewIfNeeded();
                System.out.println("SubMenuItem: " + subMenuItem.textContent());
                Locator caret = subMenuItem.locator(".mri-button__caret");
                boolean visibleLocator = true;
                try {
                    if (caret.isVisible()) {
                        visibleLocator = true;
                    } else {
                        visibleLocator = false;
                    }
                } catch (Exception e) {
                    System.out.println("SubMenuHeader visibility check failed: " + e.getMessage());
                }
                System.out.println("SubsubMenuHeader visible: " + visibleLocator);
//                if(subMenuItem.textContent().equals("Help Centre") ){
//                    continue;
//                }
                subMenuItem.click();
                m++;
                if (visibleLocator) {
                    int count2 = page.locator(SubMenu2).count();
                    System.out.println("SubMenu1 Count2: " + count2);
                    if (count2 == 0) {
                        continue;
                    }
                    for (int j = 0; j < count2; j++) {
                        Locator subSubMenuItem = page.locator(SubMenu2).nth(j);
                        subSubMenuItem.scrollIntoViewIfNeeded();
                        System.out.println("SubSubMenuItem: " + subSubMenuItem.textContent());

//                        if(subSubMenuItem.textContent().equals("Reports Administration Home Page") ||
//                           subSubMenuItem.textContent().equals("Prolease Enterprise Documents")||
//                           subSubMenuItem.textContent().equals("Prolease Enterprise G-Mail")||
//                           subSubMenuItem.textContent().equals("Prolease Enterprise Calendar")||
//                           subSubMenuItem.textContent().equals("Prolease Enterprise Store")||
//                           subSubMenuItem.textContent().equals("G-Mail Profile") ||
//                           subSubMenuItem.textContent().equals("Document Search")){
//                            if(subSubMenuItem.textContent().equals("Document Search")){
//                                System.out.println("adansdnadpasdpwepowqjepoqwjepowqjepjwq");
//                                page.locator(SubMenu1).nth(i).scrollIntoViewIfNeeded();
//                                page.locator(SubMenu1).nth(i).click();
//                                mainMenuItem.click();
//                                page.click(MenuIcon);
//                            }
//                           continue;
//                        }                  sout                          sout.println("SubSubMenuItem: " + subSubMenuItem.textContent());
                        subSubMenuItem.click();
                        m++;
                        try {
                            Locator wizardLocator = page.frameLocator(getFrameSelector(m)).locator(wizard);
                            Locator pageInput = page.frameLocator(getFrameSelector(m)).locator(Page);
                            Locator doubleframe = page.frameLocator(getFrameSelector(m)).locator(InnerFrame);
                            try {
                                int Dcount = doubleframe.count();
                                if (doubleframe != null && Dcount > 0) {
                                    try {
                                        FrameLocator doublePageFrameLocator = page.frameLocator(getFrameSelector(m))
                                                .frameLocator("//iframe[@id='HznFormFrame']");
                                        Locator doublePageLocator = doublePageFrameLocator.locator(Page);
                                        page.waitForTimeout(5000);
                                        String doublePageAria = doublePageLocator.getAttribute("aria-label");
                                        if (doublePageAria != null && doublePageLocator.isVisible()) {
                                            int pageNumber = Integer.parseInt(doublePageAria.replace("Page ", "").trim());
                                            System.out.println("Page Number (Double Frame): " + pageNumber);
                                        } else {
                                            System.out.println("aria-label in double frame is null.");
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Double frame - Page loading or access denied: " + e.getMessage());
                                    }
                                } else {
                                    try {
                                        if (Page != null && pageInput.isVisible()) {
                                            String ariaLabel = pageInput.getAttribute("aria-label");
                                            int pageNumber = Integer.parseInt(ariaLabel.replace("Page ", "").trim());
                                            System.out.println("Page Number: " + pageNumber);
                                        } else {
                                            System.out.println("aria-label is null or element not found.");
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Single frame - Page loading or error: " + e.getMessage());
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("Error checking frame counts: " + e.getMessage());
                            }

                            try {
                                boolean isWizardVisible = wizardLocator.isVisible();
                                if (isWizardVisible) {
                                    System.out.println("Wizard Name: " + wizardLocator.textContent());
                                } else {
                                    System.out.println("Wizard not visible.");
                                }
                            } catch (Exception e) {
                                System.out.println("Wizard visibility check failed: " + e.getMessage());
                            }
                        } catch (Exception e) {
                            System.out.println("Top-level error in frame check: " + e.getMessage());
                            e.printStackTrace();
                        }

                        // Click all Close buttons
                        clickAllCloseButtons();
                        if (j < count2 - 1) {
                            System.out.println("count2asdsad");
                            page.click(MenuIcon);
                            mainMenuItem.click();
                            page.locator(SubMenu1).nth(i).scrollIntoViewIfNeeded();
                            page.locator(SubMenu1).nth(i).click();
                        }
                    }
                    clickAllCloseButtons(); // After all sub-submenus
                }
                else {
               /* try {
                    // Increment frame index and access all locators
                    Locator page0 = page.frameLocator(getFrameSelector(m)).locator(Pagination0);
                    Locator page1 = page.frameLocator(getFrameSelector(m)).locator(Pagination1);
                    Locator wizardLocator = page.frameLocator(getFrameSelector(m)).locator(wizard);
                    m++;

                    // Flags to store visibility status
                    boolean isPage0Visible = false;
                    boolean isPage1Visible = false;
                    boolean isWizardVisible = false;

                    // for debugging

                    // Check Page0 and Page1 visibility
                    try {
                        page0.waitFor(new Locator.WaitForOptions().setTimeout(3000));
                        isPage0Visible = page0.isVisible();

                        page1.waitFor(new Locator.WaitForOptions().setTimeout(3000));
                        isPage1Visible = page1.isVisible();

                        System.out.println("Page0 visible: " + isPage0Visible);
                        System.out.println("Page1 visible: " + isPage1Visible);

                        // Extract and print page number from aria-label if visible
                        if (isPage0Visible) {
                            String label = page0.getAttribute("aria-label");
                            int pageNum = extractPageNumber(label);
                            System.out.println("Page0 is visible. Page number: " + pageNum);
                            if (pageNum == 0 || pageNum == 1) {
                                System.out.println("Page number is 0 or 1.");
                            }
                        } else if (isPage1Visible) {
                            String label = page1.getAttribute("aria-label");
                            int pageNum = extractPageNumber(label);
                            System.out.println("Page1 is visible. Page number: " + pageNum);
                            if (pageNum == 0 || pageNum == 1) {
                                System.out.println("Page number is 0 or 1.");
                            }
                        } else {
                            System.out.println("Access Denied or page is loading or a form is open.");
                        }
                    } catch (Exception e) {
                        System.out.println("Pagination elements not found or not visible within short wait. " + e.getMessage());
                    }

                    // Check wizard visibility
                    try {
                        wizardLocator.waitFor(new Locator.WaitForOptions().setTimeout(3000));
                        isWizardVisible = wizardLocator.isVisible();

                        if (isWizardVisible) {
                            String wizardText = wizardLocator.textContent();
                            System.out.println("Wizard Name: " + wizardText);
                        } else {
                            System.out.println("Wizard not visible.");
                        }
                    } catch (Exception e) {
                        System.out.println("Wizard not found or not visible within short wait.");
                    }

                } catch (Exception e) {
                    System.out.println("Unexpected error occurred: " + e.getMessage());
                }
*/

                    try {
                        Locator wizardLocator = page.frameLocator(getFrameSelector(m)).locator(wizard);
                        Locator pageInput = page.frameLocator(getFrameSelector(m)).locator(Page);
                        Locator doubleframe = page.frameLocator(getFrameSelector(m)).locator(InnerFrame);

                        try {
                            int Dcount = doubleframe.count();
                            if (doubleframe != null && Dcount > 0) {
                                try {
                                    FrameLocator doublePageFrameLocator = page.frameLocator(getFrameSelector(m))
                                            .frameLocator("//iframe[@id='HznFormFrame']");
                                    Locator doublePageLocator = doublePageFrameLocator.locator(Page);
                                    page.waitForTimeout(5000);
                                    String doublePageAria = doublePageLocator.getAttribute("aria-label");
                                    if (doublePageAria != null && doublePageLocator.isVisible()) {
                                        int pageNumber = Integer.parseInt(doublePageAria.replace("Page ", "").trim());
                                        System.out.println("Page Number (Double Frame): " + pageNumber);
                                    } else {
                                        System.out.println("aria-label in double frame is null.");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Double frame - Page loading or access denied: " + e.getMessage());
                                }
                            } else {
                                try {
                                    if (Page != null && pageInput.isVisible()) {
                                        String ariaLabel = pageInput.getAttribute("aria-label");
                                        int pageNumber = Integer.parseInt(ariaLabel.replace("Page ", "").trim());
                                        System.out.println("Page Number: " + pageNumber);
                                    } else {
                                        System.out.println("aria-label is null or element not found.");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Single frame - Page loading or error: " + e.getMessage());
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Error checking frame counts: " + e.getMessage());
                        }

                        try {
                            boolean isWizardVisible = wizardLocator.isVisible();
                            if (isWizardVisible) {
                                System.out.println("Wizard Name: " + wizardLocator.textContent());
                            } else {
                                System.out.println("Wizard not visible.");
                            }
                        } catch (Exception e) {
                            System.out.println("Wizard visibility check failed: " + e.getMessage());
                        }

                    } catch (Exception e) {
                        System.out.println("Top-level error in frame check: " + e.getMessage());
                        e.printStackTrace();
                    }
                    clickAllCloseButtons(); // If SubMenuHeader was not visible
                }
                if (i < count1 - 1 ) {
                    page.click(MenuIcon);
                    mainMenuItem.click();
                }
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

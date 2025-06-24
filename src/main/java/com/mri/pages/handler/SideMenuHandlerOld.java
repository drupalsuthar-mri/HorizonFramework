package com.mri.pages.handler;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;

import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.function.BooleanSupplier;

public class SideMenuHandlerOld {
    private final Page page;

    private final String sideMenuArrowIcon = "div.mri-menu div.main-menu-arrow-icon";
    private final String menuItems = "div.hzn-body #menu-level-one div[menu-code]";
    private final String closeBtn = ".hzn-dialog__header-icon-button";
    private final String levelTwoMenuTitle = ".hzn-body #menus #lvl-two-menu-title";
    private final String levelThreeMenuTitle = "#hzn-body .mri-menu #menu-level-1 #menu-level-1-title";
    private final String userPreferencesDialog = "#hzn-prompt-modal-form-0";
    private final String subMenuLocators = "#hzn-body #menu-level-0 li[mnopcode]";
    private final String nextSubMenuLocators = "#hzn-body .mri-menu #menu-level-1 li[mnopcode]";
    private final String tableRowList = "tr[role='row']";
    private final String mainFrame = "#hzn-tab-";
    private final String innerFrame = "iframe#HznFormFrame";
    private final String tabCloseBtn = "i.mri-icon.fa-times";
    private final String ListCogIconLocator = "table.k-selectable tr button.hzn-update-cog";
    private final String sideMenuCaretBtn = ".mri-button__caret";
    private final String sideMenuCaretData = "mri-button__caret";
    private final String nextBtn = "button[data-action='Next']";
    private final String reportName = "input[name='mProcDesc']";

    private Locator subMenuItems;
    private Locator sideMenuItems;
    private Locator nextSubMenuItems;
    private FrameLocator mainFrameLocator;
    private Locator reportNameLocator;
    private List<String> menuNames;
    private List<String> subMenuNames;
    private List<String> nextSubMenuNames;
    FrameLocator innerFrameLocator;
    Locator tableRowListLoc;
    Locator cogIconLocator;

    private int nextSubMenuItemsCount;
    private int subMenuItemsCount;

    Page.ScreenshotOptions screenshotOptions = new Page.ScreenshotOptions();

    public String setMainFrameLocator(int tabNumber) {
        String mainframeloc = mainFrame + tabNumber;
        System.out.println(mainframeloc);
        return mainframeloc;
    }

    public SideMenuHandlerOld(Page page) {
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
                    if (sideMenuName.equals("User Preferences")) {
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
                    } else {
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
            if (!menuCode.isEmpty() && sideMenuCode.equals(menuCode)) {
                sideMenuItems.nth(i).click();
                BooleanSupplier isLevelTwoMenuVisible =
                        () -> sideMenuName.equals(page.locator(levelTwoMenuTitle).innerText());
                page.waitForCondition(isLevelTwoMenuVisible);
                break;
            } else if (menuCode.isEmpty()) {
//                sideMenuItems.nth(i).click();
//                BooleanSupplier isLevelTwoMenuVisible =
//                        () -> sideMenuName.equals(page.locator(levelTwoMenuTitle).innerText());
//                page.waitForCondition(isLevelTwoMenuVisible);
                System.out.println("Menu code is empty");
            }
        }
    }

    public void clickOnRequiredSubMenuName(String menuCode) {
        subMenuItems = page.locator(subMenuLocators);
        menuNames = subMenuItems.allInnerTexts();
        for (int i = 0; i < subMenuItems.count(); i++) {
            String subMenuCode = subMenuItems.nth(i).getAttribute("mnopcode");
            String subMenuName = menuNames.get(i);
            if (!menuCode.isEmpty() && subMenuCode.equals(menuCode)) {
                subMenuItems.nth(i).click();
                System.out.println("Sub Menu Code: " + subMenuCode + " - Sub Menu Name: " + subMenuName);
                BooleanSupplier isLevelThreeMenuVisible =
                        () -> subMenuName.equals(page.locator(levelThreeMenuTitle).innerText());
                page.waitForCondition(isLevelThreeMenuVisible);
                break;
            } else if (menuCode.isEmpty()) {
//                subMenuItems.nth(i).click();
//                BooleanSupplier isLevelThreeMenuVisible =
//                        () -> subMenuName.equals(page.locator(levelThreeMenuTitle).innerText());
//                page.waitForCondition(isLevelThreeMenuVisible);
                System.out.println("Menu code is empty");
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
            if (!menuCode.isEmpty() && nextSubMenuCode.equals(menuCode)) {
                nextSubMenuItems.nth(i).click();
                System.out.println("Next Sub Menu Code: " + nextSubMenuCode + " - Next Sub Menu Name: " + nextSubMenuName);
                break;
            } else if (menuCode.isEmpty()) {
//                nextSubMenuItems.nth(i).click();
                System.out.println("Menu code is empty");
            }
        }
    }

    public boolean checkAllPagesAreWorking() {

        try {
            int tabNumber = 0;
            int firstMenuIndex = 0;
            int secondMenuIndex = 4;
            int thirdMenuIndex = 0;
            page.locator(sideMenuArrowIcon).click();
            sideMenuItems = page.locator(menuItems);
            System.out.println("Number of Menu Items: " + sideMenuItems.count());
            menuNames = sideMenuItems.allInnerTexts();


            while (firstMenuIndex < sideMenuItems.count()) {
                String sideMenuCode = sideMenuItems.nth(firstMenuIndex).getAttribute("menu-code");
                String sideMenuName = menuNames.get(firstMenuIndex);
                System.out.println("Menu Code: " + sideMenuCode + " - Menu Name: " + sideMenuName);
                if (!sideMenuCode.equals("HOMEHUB")) {
                    sideMenuItems.nth(firstMenuIndex).click();
                    if (sideMenuName.equals("User Preferences")) {
                        page.waitForSelector(userPreferencesDialog);
                        page.click(closeBtn);
                        break;
                    }

                    BooleanSupplier isLevelTwoMenuVisible =
                            () -> {
                                try {
                                    String actualText = page.locator(levelTwoMenuTitle).textContent();
                                    System.out.println("actualText: " + actualText);
                                    System.out.println("expectedText: " + sideMenuName);
                                    return actualText != null && sideMenuName.equals(actualText.trim());

                                } catch (Exception e) {
                                    System.out.println("Exception: " + e.getMessage());
                                    return false;
                                }
                            };
                    page.waitForCondition(isLevelTwoMenuVisible);

                    subMenuItems = page.locator(subMenuLocators);
                    subMenuItemsCount = subMenuItems.count();
                    subMenuNames = subMenuItems.allInnerTexts();
                    while (secondMenuIndex < subMenuItemsCount) {
                        System.out.println("secondMenuIndex hitt: " + secondMenuIndex);
                        Locator sideMenuCaretLoc = page.locator(sideMenuCaretBtn);
                        String subMenuClasses = subMenuItems.nth(secondMenuIndex).locator(sideMenuCaretLoc).getAttribute("class");
                        System.out.println("subMenuClasses: " + subMenuClasses);
                        boolean containsCaret = subMenuClasses.contains(sideMenuCaretData);
                        System.out.println("containsCaret: " + containsCaret);
                        if (containsCaret) {
                            subMenuItems.nth(secondMenuIndex).click();

                            String subMenuName = subMenuNames.get(secondMenuIndex);
                            BooleanSupplier isLevelThreeMenuVisible =
                                    () -> subMenuName.equals(page.locator(levelThreeMenuTitle).innerText());
                            page.waitForCondition(isLevelThreeMenuVisible);

                            nextSubMenuItems = page.locator(nextSubMenuLocators);
                            nextSubMenuItemsCount = nextSubMenuItems.count();
                            nextSubMenuNames = nextSubMenuItems.allInnerTexts();
                            if (nextSubMenuItemsCount > 0) {
                                while (thirdMenuIndex < nextSubMenuItemsCount) {
                                    System.out.println("nextSubMenuItems hitt:" + nextSubMenuItems.count());
                                    nextSubMenuItems.nth(thirdMenuIndex).click();
                                    if(nextSubMenuNames.get(thirdMenuIndex).contains("Reports Administration Home Page")){
                                        System.out.println("Skipping Reports Administration Home Page");
                                        thirdMenuIndex++;
                                    } else {
                                        tabNumber++;
                                        // Check if the page is working or not
                                        boolean isPageWorkingOrNot = assertPageIsWorking(tabNumber);
                                        if (isPageWorkingOrNot) {
                                            System.out.println("Page: " + nextSubMenuNames.get(thirdMenuIndex) + " is working");
                                        } else {
                                            System.out.println("Page: " + nextSubMenuNames.get(thirdMenuIndex) + " is NOT working");
                                        }
                                    }
                                    thirdMenuIndex++;
                                    System.out.println("thirdMenuIndex hitt: " + thirdMenuIndex);
                                    System.out.println("nextSubMenuItems hitt:" + nextSubMenuItemsCount);
                                    if (thirdMenuIndex == nextSubMenuItemsCount - 1) {
                                        System.out.println("thirdMenuIndex hit in the if condition for thirdmenuindex == " +
                                                    "to nextsubmenuitems.count() - 1" +
                                                    ":" +
                                                    " " + thirdMenuIndex);
                                        secondMenuIndex++;
                                        thirdMenuIndex = 0;
                                        System.out.println("secondMenuIndex hitt: " + secondMenuIndex);
                                    }
                                    break;
                                }
                            } else {
                                secondMenuIndex++;
                                thirdMenuIndex = 0;
                                System.out.println("secondMenuIndex hitt when no nextsubmenuitems: " + secondMenuIndex);
                            }
                        } else {
                            subMenuItems.nth(secondMenuIndex).click();
                            tabNumber++;
                            boolean isPageWorkingOrNot = assertPageIsWorking(tabNumber);
                            if (isPageWorkingOrNot) {
                                System.out.println("Page: " + subMenuNames.get(secondMenuIndex) + " is working");
                            } else {
                                System.out.println("Page: " + subMenuNames.get(secondMenuIndex) + " is NOT working");
                            }
                            if (thirdMenuIndex == nextSubMenuItems.count() - 1) {
                                System.out.println("thirdMenuIndex hit in the if condition for thirdmenuindex == " +
                                        "to nextsubmenuitems.count() - 1" +
                                        ":" +
                                        " " + thirdMenuIndex);
                                secondMenuIndex++;
                                System.out.println("secondMenuIndex hitt: " + secondMenuIndex);
                            }
                        }
                        break;
                    }
                    if (secondMenuIndex == subMenuItemsCount - 1) {
                        firstMenuIndex++;
                        secondMenuIndex = 0;
                        System.out.println("firstMenuIndex hitt: " + firstMenuIndex);
                        page.locator(sideMenuArrowIcon).click();
                    }
                } else if (sideMenuCode.equals("HOMEHUB")) {
                    firstMenuIndex++;
                    System.out.println("firstMenuIndex hitt: " + firstMenuIndex);
                    continue;
                }
                page.locator(sideMenuArrowIcon).click();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }


//    public boolean assertPageIsWorking(int tabNumber) {
//        try {
//            System.out.println("asserting page is working or not");
//            String MFL = setMainFrameLocator(tabNumber);
//            mainFrameLocator = page.frameLocator(MFL);
//
//
//            BooleanSupplier isBodyVisible = () -> mainFrameLocator.locator("body").count() > 0;
//            Locator nextBtnLocator = null;
//            try {
//                page.waitForCondition(isBodyVisible, new Page.WaitForConditionOptions().setTimeout(60000));
//                if (isBodyVisible.getAsBoolean()) {
//                    try {
//
//                        // Check if the body is visible
//                        System.out.println("Body is visible");
//                        page.waitForTimeout(3000);
//                        tableRowListLoc = mainFrameLocator.locator(tableRowList);
//                        cogIconLocator = mainFrameLocator.locator(ListCogIconLocator).first();
//
//
//                        // Wait for table rows in either main frame or inner iframe (if present)
//
//                            BooleanSupplier isTableVisible = () -> {
//                                // Check if the table is visible in the main frame
//                                boolean outerTable = tableRowListLoc != null && tableRowListLoc.count() > 0;
//                                Locator innerFrameElement = mainFrameLocator.locator(innerFrame);
//                                boolean innerTable = false;
//                                if (innerFrameElement != null && innerFrameElement.count() > 0) {
//                                    innerFrameLocator = mainFrameLocator.frameLocator("//iframe[@id='HznFormFrame']");
//                                    Locator innerTableRows = innerFrameLocator.locator(tableRowList);
//                                    innerTable = innerTableRows != null && innerTableRows.count() > 0;
//                                }
//                                return outerTable || innerTable;
//                            };
//
//                            try{
//                                page.waitForCondition(isTableVisible,
//                                        new Page.WaitForConditionOptions().setTimeout(30000));
//                            }catch (PlaywrightException e){
//                                System.out.println("Table is not visible but continuing...: " + e.getMessage());
//                            }
//
//
//                        Locator innerFrameElement = mainFrameLocator.locator(innerFrame);
//                        if (innerFrameElement.count() > 0) {
//                            System.out.println("innerFrameElement" + innerFrameElement.count());
//                            innerFrameLocator = mainFrameLocator.frameLocator("//iframe[@id='HznFormFrame']");
//                            if (innerFrameLocator.locator(tableRowList).count() > 0) {
//                                System.out.println("innerFrameLocator is attached" + innerFrameLocator.locator(tableRowList).count());
//                                tableRowListLoc = innerFrameLocator.locator(tableRowList);
//                                cogIconLocator = innerFrameLocator.locator(ListCogIconLocator).first();
//                            } else if (innerFrameLocator.locator(nextBtn).isVisible()) {
//                                nextBtnLocator = innerFrameLocator.locator(nextBtn);
//                                System.out.println("next button is visible");
//                            }
//
//                        } else {
//                            System.out.println("tableRowListLoc.count() in the else part:" + tableRowListLoc.count());
//                            System.out.println("cogIconLocator.count() in the else part:" + cogIconLocator.count());
//                            System.out.println("innerFrameElement is not attached");
//
//                        }
//
//                    } catch (PlaywrightException e) {
//                        System.err.println("Table is not visible but continuing...: " + e.getMessage());
//                    }
//                } else {
//                    System.out.println("Body is not visible");
//                }
//            } catch (PlaywrightException e) {
//                System.err.println("Body is not visible but continuing...: " + e.getMessage());
//            }
//
//            if ((tableRowListLoc != null && cogIconLocator != null) && (tableRowListLoc.count() > 0 || cogIconLocator.count() > 0)) {
//                Locator finalTableRowListLoc = tableRowListLoc;
//                Locator finalCogIconLocator = cogIconLocator;
//                BooleanSupplier isPageLoaded = () -> {
//                    if (finalTableRowListLoc.first().isVisible()) return true;
//                    return finalCogIconLocator.isVisible();
//                };
//
//                page.waitForCondition(isPageLoaded);
//                System.out.println("number of rows: " + tableRowListLoc.count());
//                if (tableRowListLoc.count() > 0 || cogIconLocator.isVisible()) {
//                    System.out.println("Page is working");
//                    page.locator(tabCloseBtn).click();
//                    return true;
//                }
//            } else if (nextBtnLocator != null && nextBtnLocator.isVisible()) {
//                System.out.println("Wizard page is working");
//                page.locator(tabCloseBtn).click();
//                return true;
//            } else {
//                System.out.println("Page is NOT working");
//                page.locator(tabCloseBtn).click();
//                return false;
//            }
//
//        } catch (Exception e) {
//            System.err.println("Error while asserting page: " + e.getMessage());
//            return false;
//        }
//        return false;
//    }

    // This method is used to check if the page is working or not
    public boolean assertPageIsWorking(int tabNumber) {
        try {
            System.out.println("asserting page is working or not");
            String originalPage = page.context().pages().get(0).url();
            int initialTabCount = page.context().pages().size();
            System.out.println("initialTabCount: " + initialTabCount);

            String MFL = setMainFrameLocator(tabNumber);
            mainFrameLocator = page.frameLocator(MFL);

            BooleanSupplier isBodyVisible = () -> mainFrameLocator.locator("body").count() > 0;
            Locator nextBtnLocator = null;
            try {
                page.waitForCondition(isBodyVisible, new Page.WaitForConditionOptions().setTimeout(60000));
                if (isBodyVisible.getAsBoolean()) {
                    try {
                        System.out.println("Body is visible");
                        page.waitForTimeout(3000);
                        Locator reportNameLocator = mainFrameLocator.locator(reportName);
                        if (reportNameLocator.isVisible()) {
                            System.out.println("Report Name field is visible");
                            page.waitForTimeout(5000);
                            String reportNameFound = reportNameLocator.innerText();
                            System.out.println("Report Name: " + reportNameFound);
                            if (reportNameFound.isEmpty()) {
                                System.out.println("Report Name field is empty");
                            } else {
                                System.out.println("Report Name field is not empty");
                            }
                        } else {
                            System.out.println("Report Name field is not visible");
                        }
                        tableRowListLoc = mainFrameLocator.locator(tableRowList);
                        cogIconLocator = mainFrameLocator.locator(ListCogIconLocator).first();

                        BooleanSupplier isTableVisible = () -> {
                            boolean outerTable = tableRowListLoc != null && tableRowListLoc.count() > 0;
                            Locator innerFrameElement = mainFrameLocator.locator(innerFrame);
                            boolean innerTable = false;
                            if (innerFrameElement != null && innerFrameElement.count() > 0) {
                                innerFrameLocator = mainFrameLocator.frameLocator("//iframe[@id='HznFormFrame']");
                                Locator innerTableRows = innerFrameLocator.locator(tableRowList);
                                innerTable = innerTableRows != null && innerTableRows.count() > 0;
                            }
                            return outerTable || innerTable;
                        };

                        try {
                            page.waitForCondition(isTableVisible,
                                    new Page.WaitForConditionOptions().setTimeout(30000));
                        } catch (PlaywrightException e) {
                            System.out.println("Table is not visible but continuing...: " + e.getMessage());
                        }

                        Locator innerFrameElement = mainFrameLocator.locator(innerFrame);
                        if (innerFrameElement.count() > 0) {
                            System.out.println("innerFrameElement" + innerFrameElement.count());
                            innerFrameLocator = mainFrameLocator.frameLocator("//iframe[@id='HznFormFrame']");
                            if (innerFrameLocator.locator(tableRowList).count() > 0) {
                                System.out.println("innerFrameLocator is attached" + innerFrameLocator.locator(tableRowList).count());
                                tableRowListLoc = innerFrameLocator.locator(tableRowList);
                                cogIconLocator = innerFrameLocator.locator(ListCogIconLocator).first();
                            } else if (innerFrameLocator.locator(nextBtn).isVisible()) {
                                nextBtnLocator = innerFrameLocator.locator(nextBtn);
                                System.out.println("next button is visible");
                            }
                        } else {
                            System.out.println("tableRowListLoc.count() in the else part:" + tableRowListLoc.count());
                            System.out.println("cogIconLocator.count() in the else part:" + cogIconLocator.count());
                            System.out.println("innerFrameElement is not attached");
                        }
                    } catch (PlaywrightException e) {
                        System.err.println("Table is not visible but continuing...: " + e.getMessage());
                    }
                } else {
                    System.out.println("Body is not visible");
                }
            } catch (PlaywrightException e) {
                System.err.println("Body is not visible but continuing...: " + e.getMessage());
            }

//            // Check for new tab
//            int currentTabCount = page.context().pages().size();
//            if (currentTabCount > initialTabCount) {
//                System.out.println("A new tab has been opened.");
//                // Get the latest tab (last in the list)
//                Page newTab = page.context().pages().get(currentTabCount - 1);
//                // Wait for the new tab to load
//                newTab.waitForLoadState();
//                // Assert something on the new tab (e.g., check URL or title)
//                System.out.println("New tab URL: " + newTab.url());
//                boolean isTabWorking = !newTab.url().isEmpty();
//                // Close the new tab
//                newTab.close();
//                // Switch back to the original tab if needed
//                page.context().pages().get(0).bringToFront();
//                return isTabWorking;
//            }


            if(reportNameLocator != null)  {
                System.out.println("Report Name field is visible");
                String reportNameFound = reportNameLocator.innerText();
                System.out.println("Report Name: " + reportNameFound);
                if (reportNameFound.isEmpty()) {
                    System.out.println("Report Name field is empty");
                } else {
                    System.out.println("Report Name field is not empty");
                }
            }else if ((tableRowListLoc != null && cogIconLocator != null) && (tableRowListLoc.count() > 0 || cogIconLocator.count() > 0)) {
                Locator finalTableRowListLoc = tableRowListLoc;
                Locator finalCogIconLocator = cogIconLocator;
                BooleanSupplier isPageLoaded = () -> {
                    if (finalTableRowListLoc.first().isVisible()) return true;
                    return finalCogIconLocator.isVisible();
                };

                page.waitForCondition(isPageLoaded);
                System.out.println("number of rows: " + tableRowListLoc.count());
                if (tableRowListLoc.count() > 0 || cogIconLocator.isVisible()) {
                    System.out.println("Page is working");
                    page.locator(tabCloseBtn).click();
                    return true;
                }
            } else if (nextBtnLocator != null && nextBtnLocator.isVisible()) {
                System.out.println("Wizard page is working");
                page.locator(tabCloseBtn).click();
                return true;
            } else {
                System.out.println("Page is NOT working");
                page.locator(tabCloseBtn).click();
                return false;
            }

        } catch (Exception e) {
            System.err.println("Error while asserting page: " + e.getMessage());
            return false;
        }
        return false;
    }

//    public boolean checkAllPagesAreWorkingDemo() {
//        try {
//            page.locator(sideMenuArrowIcon).click();
//            sideMenuItems = page.locator(menuItems);
//            List<String> firstMenuNames = sideMenuItems.allInnerTexts();
//
//            for (int i = 0; i < sideMenuItems.count(); i++) {
//                String menuCode = sideMenuItems.nth(i).getAttribute("menu-code");
//                String menuName = firstMenuNames.get(i);
//
//                if (menuCode.equals("HOMEHUB")) continue;
//
//                System.out.println("Processing First Menu: " + menuName);
//                processFirstLevelMenu(i);
//            }
//        } catch (RuntimeException e) {
//            throw new RuntimeException(e);
//        }
//        return true;
//    }
//
//    public void processFirstLevelMenu(int index) {
//        sideMenuItems.nth(index).click();
//        String sideMenuName = sideMenuItems.nth(index).innerText();
//        if (sideMenuName.equals("User Preferences")) {
//            page.waitForSelector(userPreferencesDialog);
//            page.click(closeBtn);
//            return;
//        }
//        BooleanSupplier isLevelTwoMenuVisible =
//                () -> sideMenuName.equals(page.locator(levelTwoMenuTitle).innerText());
//        page.waitForCondition(isLevelTwoMenuVisible);
//
//        subMenuItems = page.locator(subMenuLocators);
//        List<String> secondMenuNames = subMenuItems.allInnerTexts();
//        for (int j = 0; j < subMenuItems.count(); j++) {
//            System.out.println("Processing Second Menu: " + secondMenuNames.get(j));
//            processSecondLevelMenu(index, j);
//        }
//        page.locator(sideMenuArrowIcon).click();
//    }
//
//    public void processSecondLevelMenu(int firstIndex, int secondIndex) {
//        subMenuItems.nth(secondIndex).click();
//        String subMenuName = subMenuItems.nth(secondIndex).innerText();
//        BooleanSupplier isLevelThreeMenuVisible =
//                () -> subMenuName.equals(page.locator(levelThreeMenuTitle).innerText());
//        page.waitForCondition(isLevelThreeMenuVisible);
//
//        nextSubMenuItems = page.locator(nextSubMenuLocators);
//        List<String> thirdMenuNames = nextSubMenuItems.allInnerTexts();
//
//        for (int k = 0; k < nextSubMenuItems.count(); k++) {
//            System.out.println("Processing Third Menu: " + thirdMenuNames.get(k));
//            processThirdLevelMenu(firstIndex, secondIndex, k);
//        }
//    }
//
//    public void processThirdLevelMenu(int firstIndex, int secondIndex, int thirdIndex) {
//        nextSubMenuItems.nth(thirdIndex).click();
//
//        int tabNumber = thirdIndex + 1;
//        boolean isWorking = assertPageIsWorking(tabNumber);
//        if (isWorking) {
//            System.out.println("Page is working");
//            page.locator(sideMenuArrowIcon).click();
//        } else {
//            System.out.println("Page is not working");
//            page.locator(sideMenuArrowIcon).click();
//        }
//    }
}
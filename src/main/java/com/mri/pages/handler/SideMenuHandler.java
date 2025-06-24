package com.mri.pages.handler;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.function.BooleanSupplier;

@Slf4j
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
    private int tabNumber=0;
    private int firstMenu = 2;
    private int secondMenu = 0;
    private int thirdMenu = 0;
    private int tabStripIndex = 0;

    private boolean firstMenuFlag = true;
    private boolean secondMenuFlag = false;
    private boolean thirdMenuFlag = false;


    Page.ScreenshotOptions screenshotOptions = new Page.ScreenshotOptions();

    public SideMenuHandler(Page page) {
        this.page = page;
    }

    public String setMainFrameLocator(int tabNumber) {
        String mainframeloc = mainFrame + tabNumber;
        System.out.println(mainframeloc);
        return mainframeloc;
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

    // This method is used to check if the side menu is working or not
    public boolean checkAllPagesAreWorking() {
        boolean isWorking = false;
        try {
            System.out.println("Checking all pages are working or not");
            page.locator(sideMenuArrowIcon).click();
            System.out.println("Clicked on side menu arrow icon in the checkAllPagesAreWorking method");
            sideMenuItems = page.locator(menuItems);
            System.out.println("Number of Menu Items: " + sideMenuItems.count());
            menuNames = sideMenuItems.allInnerTexts();

            if (firstMenuFlag) {
                for (int firstMenuIndex = 0; firstMenuFlag; firstMenuIndex++) {
                    System.out.println("Processing First Menu: " + firstMenuIndex);
                    System.out.println("sideMenuItems.count() in checkAllPagesAreWorking method: " + sideMenuItems.count());
                    String sideMenuCode = sideMenuItems.nth(firstMenu).getAttribute("menu-code");
                    String sideMenuName = menuNames.get(firstMenu);
                    System.out.println("Processing First Menu: " + sideMenuName);
                    if (sideMenuCode.equals("HOMEHUB")) {
                        firstMenu++;
                        continue;
                    }
                    if (sideMenuName.equals("User Preferences")) {
                        page.waitForSelector(userPreferencesDialog);
                        page.click(closeBtn);
                        break;
                    }
                    sideMenuItems.nth(firstMenu).click();
                    firstMenuFlag = false;
                    secondMenuFlag = true;
                    isWorking = processingSecondLevelMenu(sideMenuName);

//                    boolean isWorking = assertPageIsWorking(tabNumber);
//                    if (isWorking){
//                        System.out.println("Page is working");
//                        page.locator(sideMenuArrowIcon).click();
//                    } else {
//                        System.out.println("Page is not working");
//                        page.locator(sideMenuArrowIcon).click();
//                    }
                }
            }
        } catch (PlaywrightException e) {
            System.out.println("An error occurred while checking the side menu: " + e.getMessage());
            return false;
        }
        return isWorking;
    }


    private boolean processingSecondLevelMenu(String sideMenuName) {
        boolean isWorking = false;
        if (secondMenuFlag) {
            try {
                BooleanSupplier isLevelTwoMenuVisible = () -> {
                    try {
                        String actualText = page.locator(levelTwoMenuTitle).textContent();
                        System.out.println("actualText: " + actualText);
                        System.out.println("expectedText " + sideMenuName);
                        return actualText != null && sideMenuName.equals(actualText.trim());
                    } catch (Exception e) {
                        System.out.println("Error while checking level two menu visibility: " + e.getMessage());
                        return false;
                    }
                };
                page.waitForCondition(isLevelTwoMenuVisible);

                subMenuItems = page.locator(subMenuLocators);
                subMenuItemsCount = subMenuItems.count();
                subMenuNames = subMenuItems.allInnerTexts();
                System.out.println("Number of Sub Menu Items: " + subMenuItemsCount);
                for (int secondMenuIndex = 0; secondMenuIndex < subMenuItemsCount; secondMenuIndex++) {
                    Locator sideMenuCaretLoc = page.locator(sideMenuCaretBtn);
                    String subMenuClasses = subMenuItems.nth(secondMenu).locator(sideMenuCaretLoc).getAttribute("class");
                    System.out.println("Sub Menu Classes: " + subMenuClasses);
                    boolean containsCaret = subMenuClasses.contains(sideMenuCaretData);
                    System.out.println("Contains Caret: " + containsCaret);
                    if (containsCaret) {
                        subMenuItems.nth(secondMenu).click();
                        String subMenuName = subMenuNames.get(secondMenu);
                        secondMenuFlag = false;
                        thirdMenuFlag = true;
                        processingThirdLevelMenu(subMenuName, secondMenuIndex);

                    } else {
                        System.out.println("No caret icon found for this sub menu.");
                        subMenuItems.nth(secondMenu).click();
                        tabNumber++;
                        System.out.println("Tab Number: " + tabNumber);
                        secondMenuFlag = false;
                        thirdMenuFlag = false;
                        firstMenuFlag = true;
                        String subMenuName = subMenuNames.get(secondMenu);
                        System.out.println("Processing next Second Menu: " + subMenuName);
                    }


                    if (secondMenu == subMenuItemsCount - 1) {
                        firstMenuFlag = true;
                        firstMenu++;
                        System.out.println("Processing next first menu");
                        System.out.println("Tab Number before going into assertion: " + tabNumber);
                        for(int secondMenuProc =0; secondMenuProc <= tabNumber; secondMenuProc++){
                            isWorking = assertPageIsWorking(tabNumber, secondMenu, thirdMenu);
                            if (isWorking) {
                                System.out.println("Page is working");
                            } else {
                                System.out.println("Page is not working");
                            }
                        }
                        secondMenu = 0;
                    }

                    if (!secondMenuFlag) {
                        System.out.println("is second manue flag is false: " + secondMenuFlag);
                        break;
                    }
                }
            } catch (PlaywrightException e) {
                System.out.println("An error occurred while processing second level menu: " + e.getMessage());
            }
        } else {
            System.out.println("Second menu flag is false, skipping processing second level menu.");
        }
        return isWorking;
    }

    private void processingThirdLevelMenu(String subMenuName, int secondMenuIndex) {
        if (thirdMenuFlag) {
            try {
                BooleanSupplier isLevelThreeMenuVisible = () -> {
                    try {
                        String levelThreeMenuTitles = page.locator(levelThreeMenuTitle).innerText();
                        boolean isLevelThreeMenuTitleEqual = levelThreeMenuTitles.equals(subMenuName);
                        if (isLevelThreeMenuTitleEqual) {
                            return true;
                        }
                    } catch (Exception e) {
                        System.out.println("Error while checking level three menu visibility: " + e.getMessage());
                        return false;
                    }
                    return false;
                };
                page.waitForCondition(isLevelThreeMenuVisible);

                nextSubMenuItems = page.locator(nextSubMenuLocators);
                nextSubMenuItemsCount = nextSubMenuItems.count();
                nextSubMenuNames = nextSubMenuItems.allInnerTexts();
                if (nextSubMenuItemsCount > 0) {
                    System.out.println("Number of Next Sub Menu Items: " + nextSubMenuItemsCount);
                    for (int thirdMenuIndex = 0; thirdMenuFlag; thirdMenuIndex++) {
                        String nextSubMenuName = nextSubMenuNames.get(thirdMenu);
                        System.out.println("Processing Third Menu: " + nextSubMenuName);
                        nextSubMenuItems.nth(thirdMenu).click();
                        tabNumber++;
                        System.out.println("Tab Number in third menu: " + tabNumber);
                        thirdMenu++;
                        thirdMenuFlag = false;
                        firstMenuFlag = true;
                    }

                    if (thirdMenu == nextSubMenuItemsCount - 1) {
                        System.out.println("asserting page is working or not");
                        System.out.println("Tab Number before going into assertion in third menu: " + tabNumber);
                        for(int thirdMenuProc = 0; thirdMenuProc <= tabNumber ; thirdMenuProc++){
                            boolean isWorking = assertPageIsWorking(tabNumber, secondMenu, thirdMenu);
                            if (isWorking) {
                                System.out.println("Page is working");
                            } else {
                                System.out.println("Page is not working");
                            }
                        }
                        secondMenu++;
                        thirdMenu = 0;
                    }
                } else {
                    System.out.println("No Next Sub Menu Items found.");
                    secondMenu++;
                }
                page.click(sideMenuArrowIcon);
            } catch (PlaywrightException e) {
                System.out.println("An error occurred while processing third level menu: " + e.getMessage());
            }
        }
    }

    private boolean assertPageIsWorking(int tabNumber, int secondMenu, int thirdMenu) {
        boolean pageWorking = false;
        try {
            System.out.println("Tab Number: " + tabNumber);
            System.out.println("asserting page is working or not");
            String tabs = "#tabstrip-tab-";


            for (int tabIndex = 1; tabIndex <= tabNumber; tabIndex++) {
                String tabId = tabs + 1;
                System.out.println("Tab ID: " + tabId);
                String MFL = setMainFrameLocator(tabIndex);
                System.out.println("Main Frame Locator: " + MFL);
                mainFrameLocator = page.frameLocator(MFL);
                if (page.locator(tabId).first().isVisible()) {
                    System.out.println("Tab is visible: " + tabId);
                    page.click(tabId);

                    BooleanSupplier isBodyVisible = () -> mainFrameLocator.locator("body").count() > 0;
                    Locator nextBtnLoc = null;
                    try {
                        page.waitForCondition(isBodyVisible, new Page.WaitForConditionOptions().setTimeout(60000));
                        if (isBodyVisible.getAsBoolean()) {
                            try {
                                System.out.println("Body is visible");
//                                page.waitForTimeout(3000);
                                Locator reportNameLocator = mainFrameLocator.locator(reportName);
                                if (reportNameLocator.isVisible()) {
                                    System.out.println("Report Name field is visible");
//                                    page.waitForTimeout(5000);
                                    String reportNameValue = reportNameLocator.inputValue();
                                    System.out.println("Report Name Value: " + reportNameValue);
                                    if (reportNameValue.isEmpty()) {
                                        System.out.println("Report Name is empty");
                                    } else {
                                        System.out.println("Report Name is not empty");
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
                                    page.waitForCondition(isTableVisible, new Page.WaitForConditionOptions().setTimeout(60000));
                                } catch (PlaywrightException e) {
                                    System.out.println("Table is not visible: " + e.getMessage());
                                }

                                Locator innerFrameElement = mainFrameLocator.locator(innerFrame);
                                if (innerFrameElement.count() > 0) {
                                    System.out.println("innerFrameElement: " + innerFrameElement.count());
                                    innerFrameLocator = mainFrameLocator.frameLocator("//iframe[@id='HznFormFrame']");
                                    if (innerFrameLocator.locator(tableRowList).count() > 0) {
                                        System.out.println("innerFrameLocator is attached: " + innerFrameLocator.locator(tableRowList).count());
                                        tableRowListLoc = innerFrameLocator.locator(tableRowList);
                                        cogIconLocator = innerFrameLocator.locator(ListCogIconLocator).first();
                                    } else if (innerFrameLocator.locator(nextBtn).isVisible()) {
                                        nextBtnLoc = innerFrameLocator.locator(nextBtn);
                                        System.out.println("Next button is visible: " + nextBtnLoc.isVisible());
                                    } else {
                                        System.out.println("tableRowListLoc.count() in the else part:" + tableRowListLoc.count());
                                        System.out.println("cogIconLocator.count() in the else part:" + cogIconLocator.count());
                                        System.out.println("innerFrameLocator is not attached");
                                    }
                                }
                            } catch (PlaywrightException e) {
                                System.out.println("An error occurred while checking the inner frame: " + e.getMessage());
                            }
                        }
                    } catch (PlaywrightException e) {
                        System.out.println("An error occurred while checking the body visibility: " + e.getMessage());
                    }

                    if (reportNameLocator != null) {
                        System.out.println("Report Name is visible:");
                        String reportNameValue = reportNameLocator.inputValue();
                        System.out.println("Report Name Value: " + reportNameValue);
                        if (reportNameValue.isEmpty()) {
                            System.out.println("Report Name is empty");
                        } else {
                            System.out.println("Report Name is not empty");
                        }
                    } else if ((tableRowListLoc != null && tableRowListLoc.count() > 0) || (cogIconLocator != null && cogIconLocator.count() > 0)) {
                        Locator finalTableRowListLoc = tableRowListLoc;
                        Locator finalCogIconLocator = cogIconLocator;

                        BooleanSupplier isPageLoaded = () -> {
                            if (finalTableRowListLoc.first().isVisible()) return true;
                            return finalCogIconLocator.isVisible();
                        };
                        page.waitForCondition(isPageLoaded);
                        System.out.println("number of rows:" + tableRowListLoc.count());
                        if (tableRowListLoc.count() > 0 || cogIconLocator.isVisible()) {
                            System.out.println("Page is working");
                            page.click(tabCloseBtn);
                            pageWorking = true;
                        }
                    } else if (nextBtnLoc != null && nextBtnLoc.isVisible()) {
                        System.out.println("Wizard page is working");
                        page.click(tabCloseBtn);
                        pageWorking = true;
                    } else {
                        System.out.println("Page is NOT working");
                        page.click(tabCloseBtn);
                        pageWorking = false;
                    }

                } else {
                    System.out.println("Tab is not visible: " + tabId);
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while asserting the page: " + e.getMessage());
        }
        return pageWorking;
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
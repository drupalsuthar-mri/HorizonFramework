package com.mri.pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.mri.pages.handler.PropertyHandler;

import java.util.function.BooleanSupplier;

public class PropertyListPage {
    private final Page page;
    private FrameLocator mainFrameLocator;
    private FrameLocator innerFrameLocator;
    private final String mainFrame = "#hzn-tab-1";
    private final String innerFrame = "#HznFormFrame";

    public PropertyListPage(Page page) {
        this.page = page;
    }

    public String getPropertyListPageTitle() {
        System.out.println("page title "+ page.title());
        return page.title();
    }

    public boolean isPropertyListVisible() {
        try {
            String propertyListCogIconLocator = "table.k-selectable tr button.hzn-update-cog";
            mainFrameLocator = page.frameLocator(mainFrame);
            Locator propertyListCogIcon = mainFrameLocator.locator(propertyListCogIconLocator).first();
            BooleanSupplier isPropertyListVisible = propertyListCogIcon::isVisible;
            page.waitForCondition(isPropertyListVisible);
            return isPropertyListVisible.getAsBoolean();
        } catch (Exception e) {
            System.out.println("Property list is not visible: " + e.getMessage());
            return false;
        }
    }

    public PropertyHandler addNewProperty() {
        String addNewPropertyBtn = "button[data-action='New']";
        mainFrameLocator.locator(addNewPropertyBtn).click();
        System.out.println("Add new property button clicked");
        mainFrameLocator = page.frameLocator(mainFrame);
        innerFrameLocator = mainFrameLocator.frameLocator(innerFrame);
        return new PropertyHandler(page, mainFrameLocator, innerFrameLocator);
    }

    public PropertyHandler editProperty() {
        mainFrameLocator = page.frameLocator(mainFrame);
        innerFrameLocator = mainFrameLocator.frameLocator(innerFrame);
        return new PropertyHandler(page, mainFrameLocator, innerFrameLocator);
    }

    public PropertyHandler deleteProperty() {
        mainFrameLocator = page.frameLocator(mainFrame);
        innerFrameLocator = mainFrameLocator.frameLocator(innerFrame);
        return new PropertyHandler(page, mainFrameLocator, innerFrameLocator);
    }
}
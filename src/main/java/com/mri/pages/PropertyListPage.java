package com.mri.pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.mri.pages.handler.AddNewHandler;

import java.util.function.BooleanSupplier;

public class PropertyListPage {
    private final Page page;
    private final String GroupRef = "#propGropRef_TextBox";
    private FrameLocator mainFrameLocator;
    private FrameLocator innerFrameLocator;

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
            String mainFrame = "#hzn-tab-1";
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

    public AddNewHandler clickAddNewPropertyBtn() {
        String addNewPropertyBtn = "button[data-action='New']";
        mainFrameLocator.locator(addNewPropertyBtn).click();
        System.out.println("Add new property button clicked");

        String mainFrame = "#hzn-tab-1";
        mainFrameLocator = page.frameLocator(mainFrame);
        String innerFrame = "#HznFormFrame";
        innerFrameLocator = mainFrameLocator.frameLocator(innerFrame);
        return new AddNewHandler(page, mainFrameLocator, innerFrameLocator);
    }

//    public void selectGroupRef() {
//        Locator groupRefLoc = innerFrameLocator.locator(GroupRef);
//        groupRefLoc.pressSequentially("DAS");
//        groupRefLoc.press("Enter");
//    }
//
//    public void selectOperationRef() {
//        Locator operationRefLoc = innerFrameLocator.locator(OperationRef);
//        operationRefLoc.pressSequentially("DASGROP1");
//        operationRefLoc.press("Enter");
//    }
//
//    public void enterPropertyName() {
//        Locator propertyNameLoc = innerFrameLocator.locator(PropertyName);
//        propertyNameLoc.pressSequentially("Automation Framework testing DAS");
//    }
//
//    public void enterPropertyTown() {
//        Locator propertyTownLoc = innerFrameLocator.locator(PropTown);
//        propertyTownLoc.pressSequentially("Bristol");
//    }
//
//    public void enterPropertyAddress() {
//        Locator propertyAddressLoc = innerFrameLocator.locator(PropertyAddress);
//        propertyAddressLoc.pressSequentially("123 Automation Avenue DAS");
//    }
//
//    public void enterPropertyCounty() {
//        Locator propertyCountyLoc = innerFrameLocator.locator(PropertyCounty);
//        propertyCountyLoc.pressSequentially("Avon");
//        propertyCountyLoc.press("Enter");
//    }
//
//    public void enterPropertyPostCode() {
//        Locator propertyPostCodeLoc = innerFrameLocator.locator(PropertyPostCode);
//        propertyPostCodeLoc.pressSequentially("BS5 9HT");
//    }
//
//    public void enterPropertyCountry() {
//        Locator propertyCountryLoc = innerFrameLocator.locator(PropertyCountry);
//        propertyCountryLoc.pressSequentially("United Kingdom");
//        propertyCountryLoc.press("Enter");
//    }
//
//    public void enterPropertyRegion() {
//        Locator propertyRegionLoc = innerFrameLocator.locator(PropertyRegion);
//        propertyRegionLoc.pressSequentially("South East");
//        propertyRegionLoc.press("Enter");
//    }
//
//    public void clickSaveBtn() {
//        innerFrameLocator.locator(saveBtn).click();
//        System.out.println("Save button clicked");
//        String primaryMessage = innerFrameLocator.locator(toastPrimaryMessage).textContent();
//        String secondaryMessage = innerFrameLocator.locator(toastSecondaryMessage).textContent();
//
//        if (primaryMessage.contains("Success") && secondaryMessage.contains("Transaction Successfully Saved")) {
//            System.out.println("Record Created Successfully");
//        } else {
//            System.out.println("Record Creation Failed");
//        }
//    }
}
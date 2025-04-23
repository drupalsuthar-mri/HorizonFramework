package com.mri.pages.handler;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;
import lombok.Setter;

import java.util.function.BooleanSupplier;


public class AddNewHandler {
    private final Page page;

    private final String GroupRef = "input#propGropRef_TextBox";
    private final String OperationRef = "input#propCompRef_TextBox";
    private final String PropertyName = "input#propName_TextBox";
    private final String PropTown = "input#propTown_TextBox";
    private final String PropertyAddress = "input#propAddrL1_TextBox";
    private final String PropertyCounty = "input#dPropCounty_TextBox";
    private final String PropertyPostCode = "input#propPostcode_TextBox";
    private final String PropertyCountry = "input#propCountryIsoCode_TextBox";
    private final String PropertyRegion = "input#propRegionCode_TextBox";
    private final String saveBtn = "button[alt='Save Button']";
    private final String toastPrimaryMessage = ".mri-toast-message__primary-message";
    private final String toastSecondaryMessage = ".mri-toast-message__secondary-message";
    private FrameLocator mainFrameLocator;
    private FrameLocator innerFrameLocator;

    public AddNewHandler(Page page, FrameLocator mainFrameLocator, FrameLocator innerFrameLocator) {
        this.page = page;
        this.mainFrameLocator = mainFrameLocator;
        this.innerFrameLocator = innerFrameLocator;
    }

    public BooleanSupplier isAddNewPropertyPageVisible(){
        BooleanSupplier isGroupRefVisible = () -> innerFrameLocator.locator(GroupRef).isVisible();
        page.waitForCondition(isGroupRefVisible);
        System.out.println("Add form is visible");
        return isGroupRefVisible;
    }

    public void selectGroupRef() {
        Locator groupRefLoc = innerFrameLocator.locator(GroupRef);
        groupRefLoc.click();
        groupRefLoc.pressSequentially("DAS");
        groupRefLoc.press("Enter");
    }

    public String selectOperationRef() {
        Locator operationRefLoc = innerFrameLocator.locator(OperationRef);
        operationRefLoc.click();
        operationRefLoc.pressSequentially("DASCOMP1");
        operationRefLoc.press("Enter");
        String operationRefData = innerFrameLocator.locator(OperationRef).inputValue();
        if(operationRefData.contains("DASCOMP1")){
            System.out.println("Operation Ref is selected");
            return operationRefData;
        }
        return null;
    }

    public void enterPropertyName() {
        Locator propertyNameLoc = innerFrameLocator.locator(PropertyName);
        propertyNameLoc.pressSequentially("Automation Framework testing23 DAS");
    }

    public void enterPropertyTown() {
        Locator propertyTownLoc = innerFrameLocator.locator(PropTown);
        propertyTownLoc.pressSequentially("Bristol");
    }

    public void enterPropertyAddress() {
        Locator propertyAddressLoc = innerFrameLocator.locator(PropertyAddress);
        propertyAddressLoc.pressSequentially("123 Automation Avenue DAS");
    }

    public void enterPropertyCounty() {
        Locator propertyCountyLoc = innerFrameLocator.locator(PropertyCounty);
        propertyCountyLoc.pressSequentially("Avon");
        page.waitForTimeout(2000);
        propertyCountyLoc.press("Enter");
    }

    public void enterPropertyPostCode() {
        Locator propertyPostCodeLoc = innerFrameLocator.locator(PropertyPostCode);
        propertyPostCodeLoc.pressSequentially("BS5 9HT");
        propertyPostCodeLoc.press("Enter");
    }

    public void enterPropertyCountry() {
        Locator propertyCountryLoc = innerFrameLocator.locator(PropertyCountry);
        propertyCountryLoc.pressSequentially("United Kingdom");
        propertyCountryLoc.press("Enter");
    }

    public void enterPropertyRegion() {
        Locator propertyRegionLoc = innerFrameLocator.locator(PropertyRegion);
        propertyRegionLoc.pressSequentially("South East");
        propertyRegionLoc.press("Enter");
    }

    public void clickSaveBtn() {
        innerFrameLocator.locator(saveBtn).click();
        System.out.println("Save button clicked");
        page.locator("button[name='No']").click();
        String primaryMessage = page.locator(toastPrimaryMessage).textContent();
        String secondaryMessage = page.locator(toastSecondaryMessage).textContent();

        if (primaryMessage.contains("Success") && secondaryMessage.contains("Transaction Successfully Saved")) {
            System.out.println("Record Created Successfully");
        } else {
            System.out.println("Record Creation Failed");
        }

    }

}
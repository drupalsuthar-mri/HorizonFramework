package com.mri.pages.handler;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.function.BooleanSupplier;


public class PropertyHandler {
    private final Page page;

//    Property Details Form Fields
    private final String groupRef = "input#propGropRef_TextBox";
    private final String operationRef = "input#propCompRef_TextBox";
    private final String siteRef = "input#propHprpRef_TextBox";
    private final String parentPropRef = "input#propParentRef_TextBox";
    private final String propertyRef = "input#propRef_TextBox";
    private final String groupName = "input#dPropGropName_TextBox";
    private final String operationName = "input#dPropCompName_TextBox";
    private final String siteName = "input#dPropHprpName_TextBox";
    private final String parentPropName = "input#dPropParentName_TextBox";
    private final String propertyName = "input#propName_TextBox";
    private final String propTown = "input#propTown_TextBox";
    private final String propertyAddress = "input#propAddrL1_TextBox";
    private final String propertyCounty = "input#dPropCounty_TextBox";
    private final String propertyPostCode = "input#propPostcode_TextBox";
    private final String propertyCountry = "input#propCountryIsoCode_TextBox";
    private final String propertyRegion = "input#propRegionCode_TextBox";
    private final String subPropertyCheckbox = "input#propSubPropFlag";
    private final String acquisitionRef = "input#propAcquRef_TextBox";
    private final String disposalRef = "input#propPrslRef_TextBox";
    private final String externalRef = "input#propExtRef_TextBox";
    private final String propertyType = "input#propTypeCode_TextBox";
    private final String assetType = "input#propElmtType_TextBox";
    private final String tenure = "input#propTenureCode_TextBox";
    private final String status = "input#propLetStatusCode_TextBox";
    private final String manager = "input#propMngrCode_TextBox";
    private final String surveyor = "input#propSurveyCode_TextBox";
    private final String reportCategory = "input#propRepCatCode_TextBox";
    private final String ERV = "input#marketRent_TextBox";
    private final String passingRentREC = "input#passingRent_TextBox";
    private final String passingRentPAY = "input#passingRentPay_TextBox";
    private final String initialBookCost = "input#propBookCost_TextBox";
    private final String comment = "textarea#propComment_TextBox";
    private final String saveBtn = "button[alt='Save Button']";

//    For toast message
    private final String toastPrimaryMessage = ".mri-toast-message__primary-message";
    private final String toastSecondaryMessage = ".mri-toast-message__secondary-message";

//    Filters on the property List page
    private final String allSearchByFilters = "th[data-title]";
    private final String allSearchByFiltersInputFields = "input[title]";

//    View / update selector for the property
    private final String propertyListCogIconLocator = "table.k-selectable tr button.hzn-update-cog";

//    Page frame locators
    private final FrameLocator mainFrameLocator;
    private final FrameLocator innerFrameLocator;

    public PropertyHandler(Page page, FrameLocator mainFrameLocator, FrameLocator innerFrameLocator) {
        this.page = page;
        this.mainFrameLocator = mainFrameLocator;
        this.innerFrameLocator = innerFrameLocator;
    }

    public BooleanSupplier isPropertyDetailsFormVisible(){
        BooleanSupplier isGroupRefVisible = () -> innerFrameLocator.locator(groupRef).isVisible();
        page.waitForCondition(isGroupRefVisible);
        System.out.println("The form is visible");
        return isGroupRefVisible;
    }

    public void allSearchByFilters(String requiredFilter, String requiredFilterValue){
        Locator allSearchByFiltersInputFieldsLoc = mainFrameLocator.locator(allSearchByFiltersInputFields);
        System.out.println("All Search By Filters count is:" +allSearchByFiltersInputFieldsLoc.count());
        for (int i = 0; i < allSearchByFiltersInputFieldsLoc.count(); i++){
            if(allSearchByFiltersInputFieldsLoc.nth(i).getAttribute("title").trim().equals(requiredFilter)){
                allSearchByFiltersInputFieldsLoc.nth(i).click();
                allSearchByFiltersInputFieldsLoc.nth(i).fill(requiredFilterValue);
                allSearchByFiltersInputFieldsLoc.nth(i).press("Enter");
                break;
            }
        }
    }

    public void clickOnCogIconForProperty(){
        Locator cogIconLocator = mainFrameLocator.locator(propertyListCogIconLocator).first();
        BooleanSupplier isCogIconVisible = cogIconLocator::isVisible;
        page.waitForCondition(isCogIconVisible);
        cogIconLocator.click();
    }

    public void selectGroupRef(String givenGroupRef) {
        Locator groupRefLoc = innerFrameLocator.locator(groupRef);
        groupRefLoc.click();
        groupRefLoc.pressSequentially(givenGroupRef);
        groupRefLoc.press("Enter");
    }

    public String selectOperationRef(String givenOperationRef) {
        Locator operationRefLoc = innerFrameLocator.locator(operationRef);
        operationRefLoc.click();
        operationRefLoc.pressSequentially(givenOperationRef);
        operationRefLoc.press("Enter");
        String operationRefData = innerFrameLocator.locator(operationRef).inputValue();
        if(operationRefData.contains(givenOperationRef)){
            System.out.println("Operation Ref is selected");
            return operationRefData;
        }
        return null;
    }

    public void selectSiteRef(String givensSiteRef){
        Locator siteRefLoc = innerFrameLocator.locator(siteRef);
        siteRefLoc.click();
        siteRefLoc.pressSequentially(givensSiteRef);
        siteRefLoc.press("Enter");
        System.out.println("Site Ref is selected");
    }

    public void assertParentPropRef(String givenParentPropRef){
        String parentPropRefData = innerFrameLocator.locator(parentPropRef).inputValue();
        if(parentPropRefData.contains(givenParentPropRef)){
            System.out.println("Parent Prop Ref is being shown correctly");
        } else {
            System.out.println("Parent Prop Ref is null");
        }
    }

    public void assertPropertyReference(String givenPropertyReference){
        String propertyReferenceData = innerFrameLocator.locator(propertyRef).inputValue();
        if(propertyReferenceData.contains(givenPropertyReference)){
            System.out.println("Property Reference is being shown correctly");
        } else {
            System.out.println("Property Reference is null");
        }
    }

    public void assertGroupName (String givenGroupName){
        String groupNameData = innerFrameLocator.locator(groupName).inputValue();
        if(groupNameData.contains(givenGroupName)){
            System.out.println("Group Name is being shown correctly");
        } else {
            System.out.println("Group Name is null");
        }
    }

    public void assertOperationName(String givenOperationName){
        String operationNameData = innerFrameLocator.locator(operationName).inputValue();
        if(operationNameData.contains(givenOperationName)){
            System.out.println("Operation Name is being shown correctly");
        } else {
            System.out.println("Operation Name is null");
        }
    }

    public void assertSiteName(String givenSiteName){
        String siteNameData = innerFrameLocator.locator(siteName).inputValue();
        if(siteNameData.contains(givenSiteName)){
            System.out.println("Site Name is being shown correctly");
        } else {
            System.out.println("Site Name is null");
        }
    }

    public void assertParentPropName(String givenParentPropName){
        String parentPropNameData = innerFrameLocator.locator(parentPropName).inputValue();
        if(parentPropNameData.contains(givenParentPropName)){
            System.out.println("Parent Property Name is being shown correctly");
        } else {
            System.out.println("Parent Property Name is null");
        }
    }

    public void enterPropertyName(String givenPropertyName) {
        Locator propertyNameLoc = innerFrameLocator.locator(propertyName);
        propertyNameLoc.click();
        propertyNameLoc.pressSequentially(givenPropertyName);
    }

    public void enterPropertyTown(String givenPropertyTown) {
        Locator propertyTownLoc = innerFrameLocator.locator(propTown);
        propertyTownLoc.click();
        propertyTownLoc.pressSequentially(givenPropertyTown);
    }

    public void enterPropertyAddress(String givenPropertyAddress) {
        Locator propertyAddressLoc = innerFrameLocator.locator(propertyAddress);
        propertyAddressLoc.click();
        propertyAddressLoc.pressSequentially(givenPropertyAddress);
    }

    public void enterPropertyCounty(String givenPropertyCounty) {
        Locator propertyCountyLoc = innerFrameLocator.locator(propertyCounty);
        propertyCountyLoc.click();
        propertyCountyLoc.pressSequentially(givenPropertyCounty);
        page.waitForTimeout(2000);
        propertyCountyLoc.press("Enter");
    }

    public void enterPropertyPostCode(String givenPropertyPostCode) {
        Locator propertyPostCodeLoc = innerFrameLocator.locator(propertyPostCode);
        propertyPostCodeLoc.click();
        propertyPostCodeLoc.pressSequentially(givenPropertyPostCode);
        propertyPostCodeLoc.press("Enter");
    }

    public void enterPropertyCountry(String givenPropertyCountry) {
        Locator propertyCountryLoc = innerFrameLocator.locator(propertyCountry);
        propertyCountryLoc.click();
        propertyCountryLoc.pressSequentially(givenPropertyCountry);
        propertyCountryLoc.press("Enter");
    }

    public void enterPropertyRegion(String givenPropertyRegion) {
        Locator propertyRegionLoc = innerFrameLocator.locator(propertyRegion);
        propertyRegionLoc.pressSequentially(givenPropertyRegion);
        propertyRegionLoc.press("Enter");
    }

    public void clickSubPropertyCheckBox() {
        innerFrameLocator.locator(subPropertyCheckbox).click();
        System.out.println("Sub Property Checkbox clicked");
    }

    public void selectAcquisitionRef(String givenAcquisitionRef) {
        Locator acquisitionRefLoc = innerFrameLocator.locator(acquisitionRef);
        acquisitionRefLoc.click();
        acquisitionRefLoc.pressSequentially(givenAcquisitionRef);
        acquisitionRefLoc.press("Enter");
    }

    public void assertDisposalRef(String givenDisposalRef) {
        Locator disposalRefLoc = innerFrameLocator.locator(disposalRef);
        String actualDisposalRef = disposalRefLoc.inputValue();
        if(actualDisposalRef.equals(givenDisposalRef)){
            System.out.println("Disposal Ref is shown correctly");
        }else{
            System.out.println("Disposal Ref is null or not shown");
        }
    }

    public void enterExternalRef(String givenExternalRef) {
        Locator externalRefLoc = innerFrameLocator.locator(externalRef);
        externalRefLoc.click();
        externalRefLoc.pressSequentially(givenExternalRef);
        externalRefLoc.press("Enter");
    }

    public void selectPropertyType(String givenPropertyType){
        Locator typeLoc = innerFrameLocator.locator(propertyType);
        typeLoc.click();
        typeLoc.pressSequentially(givenPropertyType);
        typeLoc.press("Enter");
    }

    public void selectAssetType(String givenAssetType){
        Locator assetTypeLoc = innerFrameLocator.locator(assetType);
        assetTypeLoc.click();
        assetTypeLoc.pressSequentially(givenAssetType);
        assetTypeLoc.press("Enter");
    }

    public void selectTenure(String givenTenure){
        Locator tenureLoc = innerFrameLocator.locator(tenure);
        tenureLoc.click();
        tenureLoc.pressSequentially(givenTenure);
        tenureLoc.press("Enter");
    }

    public void selectStatus(String givenStatus){
        Locator statusLoc = innerFrameLocator.locator(status);
        statusLoc.click();
        statusLoc.pressSequentially(givenStatus);
        statusLoc.press("Enter");
    }

    public void selectManager(String givenManager){
        Locator managerLoc = innerFrameLocator.locator(manager);
        managerLoc.click();
        managerLoc.pressSequentially(givenManager);
        managerLoc.press("Enter");
    }

    public void selectSurveyor (String givenSurveyor){
        Locator surveyorLoc = innerFrameLocator.locator(surveyor);
        surveyorLoc.click();
        surveyorLoc.pressSequentially(givenSurveyor);
        surveyorLoc.press("Enter");
    }

    public void selectReportCategory(String givenReportCategory){
        Locator reportCategoryLoc = innerFrameLocator.locator(reportCategory);
        reportCategoryLoc.click();
        reportCategoryLoc.pressSequentially(givenReportCategory);
        reportCategoryLoc.press("Enter");
    }

    public void assertERV(String expectedERV){
        Locator ERVLoc = innerFrameLocator.locator(ERV);
        String actualERV = ERVLoc.inputValue();
        if(actualERV.equals(expectedERV)){
            System.out.println("ERV is shown correctly");
        } else {
            System.out.println("ERV is null or incorrect");
        }
    }

    public void assertPassingRentREC (String expectedPassingRentREC){
        Locator passingRentRECLoc = innerFrameLocator.locator(passingRentREC);
        String actualPassingRentREC = passingRentRECLoc.inputValue();
        if(actualPassingRentREC.equals(expectedPassingRentREC)){
            System.out.println("Passing Rent REC is shown correctly");
        } else {
            System.out.println("Passing Rent REC is null or incorrect");
        }
    }

    public void assertPassingRentPAY(String expectedPassingRentPAY){
        Locator passingRentPAYLoc = innerFrameLocator.locator(passingRentPAY);
        String actualPassingRentPAY = passingRentPAYLoc.inputValue();
        if(actualPassingRentPAY.equals(expectedPassingRentPAY)){
            System.out.println("Passing Rent PAY is shown correctly");
        } else {
            System.out.println("Passing Rent PAY is null or incorrect");
        }
    }

    public void enterInitialBookCost(String givenInitialBookCost){
        Locator initialBookCostLoc = innerFrameLocator.locator(initialBookCost);
        initialBookCostLoc.click();
        initialBookCostLoc.pressSequentially(givenInitialBookCost);
        initialBookCostLoc.press("Enter");
    }

    public void enterComment(String givenComment){
        Locator commentLoc = innerFrameLocator.locator(comment);
        commentLoc.click();
        commentLoc.pressSequentially(givenComment);
        commentLoc.press("Enter");
    }

    public void clickSaveBtn() {
        innerFrameLocator.locator(saveBtn).click();
        System.out.println("Save button clicked");
        if(page.locator("h1#hzn-prompt-modal-0-title").isVisible()){
            page.locator("button[name='No']").click();
        }
        String primaryMessage = page.locator(toastPrimaryMessage).textContent();
        String secondaryMessage = page.locator(toastSecondaryMessage).textContent();

        if (primaryMessage.contains("Success") && secondaryMessage.contains("Transaction Successfully Saved")) {
            System.out.println("Record Created Successfully");
        } else if (primaryMessage.contains("Success") && secondaryMessage.contains("No changes to save.")){
            System.out.println("No changes to save");
        }else {
            System.out.println("Record Creation Failed");
        }

    }

}
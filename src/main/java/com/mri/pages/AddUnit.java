package com.mri.pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import lombok.SneakyThrows;
import org.assertj.core.api.SoftAssertions;
public class AddUnit {
    private final Page page;
    private final String MenuIcon = "menu-component div.mri-menu div.main-menu-arrow-icon";
    private final String Database = "menu-component  .mri-menu #menus #main-menu #menu-level-one [menu-code='DBM']";
    private final String Unit = "menu-component  .mri-menu #extra-menus #menu-level-1 #menu-level-1-body  [entitycode='UNI']";
    private final String InnerFrame="iframe#HznFormFrame";
    private final String PropertyRef = "#unitPropRef_TextBox";
    private final String Floor = "#unitFlorFloorCode_TextBox";
    private final String Description = "#unitDesc_TextBox";
    private final String AssetType = "#unitElmtType_TextBox";
    private final String ZoneRef="#unitZoneRef_TextBox";
    private final String StartDate="#unitStartDate_TextBox";
    private  final String  EndDate="#unitEndDate_TextBox";
    private  final String  UnitType="#unitTypeCode_TextBox";
    private  final String  SearchUnit="input[title='Unit']";

    private final String UnitRef="#unitRef_TextBox";
    private final String toastPrimaryMessage = ".mri-toast-message__primary-message";
    private final String toastSecondaryMessage = ".mri-toast-message__secondary-message";
    private final String UnitPhysicalSector="#unitPhysicalSector_TextBox";
    private  String getFrameSelector(int tabNumber) {
        return "iframe#hzn-tab-" + tabNumber;
    }

    public AddUnit(Page page) {
        this.page = page;
    }
    public void ClickMenuIcon() {
        page.click(MenuIcon);
    }
    public void ClickDatabase() {
        page.click(Database);
    }
    public void ClickUnit() {
        page.click(Unit);
    }
    @SneakyThrows
    public void ClickNewBtn(){
        Thread.sleep(4000);
        Locator element1=page.frameLocator(getFrameSelector(1)).locator("(//td[@role='gridcell']//button)[1]");
        element1.waitFor(new Locator.WaitForOptions().setTimeout(0));
        page.frameLocator(getFrameSelector(1)).getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("New").setExact(true)).click();
    }

    public void  PropertyRef()
    {
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(PropertyRef).scrollIntoViewIfNeeded();
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(PropertyRef).pressSequentially("0000501");
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(PropertyRef).press("Tab");
    }
    public void Floor(String Floor)
    {
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(Floor).fill(Floor);
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(Floor).press("Tab");
    }
    public void UnitRef(String UnitRef){
        page.setDefaultTimeout(6000);
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(UnitRef).fill(UnitRef);
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(UnitRef).press("Tab");
    }
    public void Description(){
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(Description).fill("Unit Testing");
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(Description).press("Enter");
    }

    @SneakyThrows
    public void StartDate(String StartDate) {
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(StartDate).fill(StartDate);
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(StartDate).press("Enter");
    }
    @SneakyThrows
    public void EndDate(String EndDate) {
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(EndDate).fill(EndDate);
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(EndDate).press("Enter");
    }

    public void ClickSaveBtn() {
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("button[alt='Save Button']").click();
        Locator element= page.locator("(//p[@class='mri-toast-message__text']//span)[2]");
        if(element.isVisible()){
            System.out.println("Message is:" + element.textContent());
        }
        else{
            System.out.println("Button is not clicked!");
        }
    }

    @SneakyThrows
    public void UpdateUnit() {
        page.frameLocator(getFrameSelector(1)).locator("input[title='Unit']").fill("KMUNI100");
        page.frameLocator(getFrameSelector(1)).locator("//tr[@class='k-master-row k-state-selected']//td[1]").click();
    }

    public void UnitType(){
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(UnitType).fill("office");
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(UnitType).press("Enter");
    }
    public void PhysicalSector(){
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(UnitPhysicalSector).fill("Car Park");
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(UnitPhysicalSector).press("Enter");
    }

  /*  public void AssetType(){
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(AssetType).fill("Industrial");
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(AssetType).press("Enter");
    }*/

    public void ZoneRef(){
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(ZoneRef).fill("KMZONE12");
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(ZoneRef).press("Enter");
    }
    public void SearchUnit(){
        page.frameLocator(getFrameSelector(1)).locator(SearchUnit).fill("KMUNIT01");
        page.frameLocator(getFrameSelector(1)).locator(SearchUnit).press("Enter");
        page.waitForTimeout(3000);
    }
    @SneakyThrows
    public void DeleteUnit() {
        page.setDefaultTimeout(6000);
        Locator element1=page.frameLocator(getFrameSelector(1)).locator("[alt='Delete Button']");
        element1.waitFor(new Locator.WaitForOptions().setTimeout(0));
        //page.frameLocator(getFrameSelector(1)).locator("[alt='Delete Button']").click();
        page.frameLocator(getFrameSelector(1)).locator("(//div[@id='hzn-button-bar-bottom']//button)[2]").click();
        page.setDefaultTimeout(2000);
        Locator element= page.locator("(//p[@class='mri-toast-message__text']//span)[2]");
        if(element.isVisible()){
            System.out.println("Message is:" + element.textContent());
        }
        else{
            System.out.println("Button is not clicked!");
        }
    }
/*
    public void SaveBtn(){
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("button[alt='Save Button']").click();
        page.setDefaultTimeout(120000);
        Locator element= page.locator("(//p[@class='mri-toast-message__text']//span)[2]");
        if(element.isVisible()){
            System.out.println("Message is:" + element.textContent());
        }
    }*/
    public void SearchInvalidUnit(){
        Locator element1=page.frameLocator(getFrameSelector(1)).locator("(//td[@role='gridcell']//button)[1]");
        element1.waitFor(new Locator.WaitForOptions().setTimeout(0));
        page.frameLocator(getFrameSelector(1)).locator(SearchUnit).fill("KMUNI100");
        page.frameLocator(getFrameSelector(1)).locator(SearchUnit).press("Enter");
    }

    @SneakyThrows
    public void Delete() {
        SoftAssertions softly = new SoftAssertions();
        page.frameLocator(getFrameSelector(1)).getByRole(AriaRole.BUTTON,new FrameLocator.GetByRoleOptions().setName("Delete").setExact(true)).click();
        page.setDefaultTimeout(3000);
        page.frameLocator(getFrameSelector(1)).locator(SearchUnit).fill("KMUNI100");
        boolean isUnitPresent =page.frameLocator(getFrameSelector(1))
                .locator("(//td[@role='gridcell']//button)[1]")
                .isVisible();
        softly.assertThat(true)
                .as("Force failure for testing")
                .isFalse();
        softly.assertAll(); // This *should* now throw an error
    }

    public void filterUnit(){
        page.frameLocator(getFrameSelector(1)).locator(SearchUnit).fill("00002199");
        page.frameLocator(getFrameSelector(1)).locator(SearchUnit).press("Enter");
    }

}

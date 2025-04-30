package com.mri.pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import lombok.SneakyThrows;


public class AddUnit {
    private final Page page;
    private final String MenuIcon = "menu-component div.mri-menu div.main-menu-arrow-icon";
    private final String Database = "menu-component  .mri-menu #menus #main-menu #menu-level-one [menu-code='DBM']";
    private final String Unit = "menu-component  .mri-menu #extra-menus #menu-level-1 #menu-level-1-body  [entitycode='UNI']";
    private final String InnerFrame="iframe#HznFormFrame";
    private final String PropertyRef = "0000501";
    private final String Floor = "Ground";
    private final String Description = "Unit Test";
    private final String Type = "office";
    private final String AssetType = "Industrial";
    private final String ZoneRef="KMZONE12";
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
        page.frameLocator(getFrameSelector(1)).getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("New").setExact(true)).waitFor();
        page.frameLocator(getFrameSelector(1)).getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("New").setExact(true)).click();
        //page.frameLocator(getFrameSelector(1)).locator(".k-switch-track .k-switch-label-off").check();
    }

    public void  PropertyRef()
    {
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("#unitPropRef_TextBox").scrollIntoViewIfNeeded();
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("#unitPropRef_TextBox").pressSequentially(PropertyRef);
        page.setDefaultTimeout(6000);
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("#unitPropRef_TextBox").press("Enter");
    }

    public void Floor()
    {
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("#unitFlorFloorCode_TextBox").scrollIntoViewIfNeeded();
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("#unitFlorFloorCode_TextBox").pressSequentially(Floor);
        page.setDefaultTimeout(6000);
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("#unitFlorFloorCode_TextBox").press("Enter");
    }

    public void Description(){
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("#unitDesc_TextBox").scrollIntoViewIfNeeded();
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("#unitDesc_TextBox").pressSequentially(Description);
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("#unitDesc_TextBox").press("Enter");
    }

    public void StartDate() {
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("#unitStartDate_CalendarButton").scrollIntoViewIfNeeded();
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("#unitStartDate_TextBox").pressSequentially("2025/04/29");
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("#unitStartDate_TextBox").press("Enter");

    }
    public void EndDate(){
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("#unitEndDate_TextBox").pressSequentially("2025/05/03");
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("#unitEndDate_TextBox").press("Enter");
    }

    public void ClickSaveBtn() {
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Save")).click();
    }

    public void UpdateUnit() {
        page.frameLocator(getFrameSelector(1)).locator("input[title='Unit']").fill("00002199");
        page.setDefaultTimeout(30000);
        page.frameLocator(getFrameSelector(1)).locator("//tr[@class='k-master-row k-state-selected']//td[1]").click();
    }

    public void UnitType(){
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("#unitTypeCode_TextBox").scrollIntoViewIfNeeded();
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("#unitTypeCode_TextBox").press("Enter");
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("#unitTypeCode_TextBox").fill(Type);
    }

    public void AssetType(){
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("#unitElmtType_TextBox").scrollIntoViewIfNeeded();
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("#unitElmtType_TextBox").press("Enter");
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("#unitElmtType_TextBox").pressSequentially(AssetType);
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("#unitElmtType_TextBox").press("Enter");
    }

    public void ZoneRef(){
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("#unitZoneRef_TextBox").scrollIntoViewIfNeeded();
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("#unitZoneRef_TextBox").press("Enter");
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("#unitZoneRef_TextBox").pressSequentially(ZoneRef);
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("#unitZoneRef_TextBox").press("Enter");

    }
    public void SearchUnit(){
        page.frameLocator(getFrameSelector(1)).locator("input[title='Unit']").press("Enter");
        page.frameLocator(getFrameSelector(1)).locator("input[title='Unit']").fill("00001887");
        page.frameLocator(getFrameSelector(1)).locator("input[title='Unit']").press("Enter");
    }

    public void DeleteUnit() {
        page.setDefaultTimeout(30000);
        page.frameLocator(getFrameSelector(1)).getByRole(AriaRole.BUTTON,new FrameLocator.GetByRoleOptions().setName("Delete").setExact(true)).click();
    }

    public void SaveBtn(){
        page.pause();
        page.setDefaultTimeout(60000);
        page.frameLocator(getFrameSelector(1)).getByRole(AriaRole.BUTTON,new FrameLocator.GetByRoleOptions().setName("Save").setExact(true)).click();
        page.setDefaultTimeout(120000);
        Locator element= page.locator("(//p[@class='mri-toast-message__text']//span)[2]");
        if(element.isVisible()){
            System.out.println("Message is:" + element.textContent());
        }
    }

    public void filterUnit(){
        page.frameLocator(getFrameSelector(1)).locator("input[title='Unit']").fill("00002199");
        page.frameLocator(getFrameSelector(1)).locator("input[title='Unit']").press("Enter");
    }

}

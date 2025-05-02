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
    private final String PropertyRef = "#unitPropRef_TextBox";
    private final String Floor = "#unitFlorFloorCode_TextBox";
    private final String Description = "#unitDesc_TextBox";
    private final String AssetType = "#unitElmtType_TextBox";
    private final String ZoneRef="#unitZoneRef_TextBox";
    private final String StartDate="#unitStartDate_TextBox";
    private  final String  EndDate="#unitEndDate_TextBox";
    private  final String  UnitType="#unitTypeCode_TextBox";
    private  final String  SearchUnit="input[title='Unit']";
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
        page.pause();
        Thread.sleep(4000);
        //page.frameLocator(getFrameSelector(1)).getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("New").setExact(true)).waitFor();
        page.frameLocator(getFrameSelector(1)).getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("New").setExact(true)).click();
    }

    public void  PropertyRef()
    {
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(PropertyRef).scrollIntoViewIfNeeded();
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(PropertyRef).pressSequentially("0000501");
        page.setDefaultTimeout(6000);
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(PropertyRef).press("Enter");
    }

    public void Floor()
    {
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(Floor).scrollIntoViewIfNeeded();
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(Floor).pressSequentially("Ground");
        page.setDefaultTimeout(6000);
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(Floor).press("Enter");
    }

    public void Description(){
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(Description).scrollIntoViewIfNeeded();
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(Description).pressSequentially("Unit Testing");
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(Description).press("Enter");
    }

    public void StartDate() {
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(StartDate).scrollIntoViewIfNeeded();
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(StartDate).pressSequentially("29/04/2025");
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(StartDate).press("Enter");

    }
    public void EndDate(){
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(EndDate).pressSequentially("03/05/2025");
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(EndDate).press("Enter");
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
        page.pause();
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(UnitType).scrollIntoViewIfNeeded();
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(UnitType).pressSequentially("office");
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(UnitType).press("Enter");

    }

    public void AssetType(){
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(AssetType).scrollIntoViewIfNeeded();
       // page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(AssetType).press("Enter");
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(AssetType).pressSequentially("Industrial");
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(AssetType).press("Enter");
    }

    public void ZoneRef(){
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(ZoneRef).scrollIntoViewIfNeeded();
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(ZoneRef).press("Enter");
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(ZoneRef).pressSequentially("KMZONE12");
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator(ZoneRef).press("Enter");

    }
    public void SearchUnit(){
        page.frameLocator(getFrameSelector(1)).locator(SearchUnit).press("Enter");
        page.frameLocator(getFrameSelector(1)).locator(SearchUnit).fill("00001887");
        page.frameLocator(getFrameSelector(1)).locator(SearchUnit).press("Enter");
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
        page.frameLocator(getFrameSelector(1)).locator(SearchUnit).fill("00002199");
        page.frameLocator(getFrameSelector(1)).locator(SearchUnit).press("Enter");
    }

}

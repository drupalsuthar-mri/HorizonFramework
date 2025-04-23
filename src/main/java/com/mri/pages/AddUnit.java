package com.mri.pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;


public class AddUnit {
    private final Page page;
    private final String MenuIcon = "menu-component div.mri-menu div.main-menu-arrow-icon";
    private final String Database = "menu-component  .mri-menu #menus #main-menu #menu-level-one [menu-code='DBM']";
    private final String Unit = "menu-component  .mri-menu #extra-menus #menu-level-1 #menu-level-1-body  [entitycode='UNI']";
    private final String  InnerFrame="iframe#HznFormFrame";
    private final String PropertyRef = "0000501";
    private final String Floor = "Ground";
    private final String Description = "Unit Test";
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
    public void ClickNewBtn() {
        Locator element1=page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("button.hzn-update-cog");
        element1.waitFor(new Locator.WaitForOptions().setTimeout(0));
        page.frameLocator(getFrameSelector(1)).getByRole(AriaRole.BUTTON,new FrameLocator.GetByRoleOptions().setName("New").setExact(true)).click();
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
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("#unitStartDate_TextBox").press("Enter");
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("#unitStartDate_TextBox").fill("28/04/2025");

    }
    public void EndDate(){
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("#unitEndDate_TextBox").scrollIntoViewIfNeeded();
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("#unitEndDate_TextBox").press("Enter");
        page.frameLocator(getFrameSelector(1)).frameLocator(InnerFrame).locator("#unitEndDate_TextBox").fill("1/05/2025");
    }


    public void ClickSaveBtn() {
        page.frameLocator(getFrameSelector(1)).frameLocator("iframe#HznFormFrame").getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Save")).click();
    }


}

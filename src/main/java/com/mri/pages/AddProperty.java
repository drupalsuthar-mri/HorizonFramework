package com.mri.pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class AddProperty {
    private final Page page;
    private final String MenuIcon = "menu-component div.mri-menu div.main-menu-arrow-icon";
    private final String DatabaseTab = "menu-component  .mri-menu #menus #main-menu #menu-level-one [menu-code='DBM']";
    private final String PropertyTab = "menu-component  .mri-menu #extra-menus #menu-level-1 #menu-level-1-body  [mnopdesc='Property']";
    private final String InnerFrame="iframe#HznFormFrame";
    private final String GroupRef = "#propGropRef_TextBox";
    private final String OperationRef="#propCompRef_TextBox";
    private final String PropertyRef = "0000501";
    private  String MainFrame(int tabNumber) {
        return "iframe#hzn-tab-" + tabNumber;
    }
    public AddProperty(Page page) {
        this.page = page;
    }
    public void ClickMenuIcon() {
        page.click(MenuIcon);
    }
    public void ClickDatabase() {
        page.click(DatabaseTab);
    }
    public void ClickProperty() {
        page.click(PropertyTab);
        Locator element1=page.frameLocator(MainFrame(1)).locator("//small[@id='hzn-form-desc']/following-sibling::span[1]");
        element1.waitFor(new Locator.WaitForOptions().setTimeout(0));
        String text1 = element1.innerText(); // Get text content
        System.out.println("Element text: " + text1);
    }
    public void ClickNewBtn() {

        page.frameLocator(MainFrame(1)).getByRole(AriaRole.BUTTON,new FrameLocator.GetByRoleOptions().setName("New").setExact(true)).click();
    }
    public void FillGroupRef() {
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(GroupRef).scrollIntoViewIfNeeded();
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(GroupRef).pressSequentially("KMGROP01");
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(GroupRef).press("Enter");
    }
    public void FillOperationalRef() {
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(OperationRef).scrollIntoViewIfNeeded();
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(OperationRef).pressSequentially("KMCOMP01");
        page.setDefaultTimeout(6000);
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(OperationRef).press("Enter");
    }
    public void ClickSaveBtn() {
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Save")).click();
    }
}

package com.mri.pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class Update {
    private final Page page;
    private final String MenuIcon = "menu-component div.mri-menu div.main-menu-arrow-icon";
    private final String Database = "menu-component  .mri-menu #menus #main-menu #menu-level-one [menu-code='DBM']";
    private final String Property = "menu-component  .mri-menu #extra-menus #menu-level-1 #menu-level-1-body  [mnopdesc='Property']";
    private final String Frame = "iframe#hzn-tab-1";
    private final String InnerFrame="iframe#HznFormFrame";
    private final  String PropertyInput = "input[title='Property']";
    private final String PropertyRef = "0000501";
    private final String PropertyTown = "Rajkot";

    public Update(Page page) {
        this.page = page;
    }
    public void ClickMenuIcon() {
        page.click(MenuIcon);
    }
    public void ClickDatabase() {
        page.click(Database);
    }
    public void GetProperty() {
        page.click(Property);
        Locator element1=page.frameLocator(Frame).locator("//small[@id='hzn-form-desc']/following-sibling::span[1]");
        element1.waitFor(new Locator.WaitForOptions().setTimeout(0));
        String text1 = element1.innerText(); // Get text content
        System.out.println("Element text: " + text1);
    }
    public void SearchProperty() {
        page.frameLocator(Frame).locator(PropertyInput).fill(PropertyRef);
        page.frameLocator(Frame).locator(PropertyInput).press("Enter");
    }
    public void ClickDetailBtn() {
        page.frameLocator(Frame).locator("button.hzn-update-cog").first().click();
    }
    public void UpdateProperty() {
        page.locator(Frame).contentFrame().locator(InnerFrame).contentFrame().locator("#propTown input").fill(PropertyTown);
    }
    public void ClickSaveBtn() {
        page.frameLocator(Frame).frameLocator("iframe#HznFormFrame").getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Save")).click();
    }

}

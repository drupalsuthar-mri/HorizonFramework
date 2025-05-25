package com.mri.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

public class MenuName {
        private final Page page;
        private final String MenuIcon= "menu-component div.mri-menu div.main-menu-arrow-icon";
        private final String MenuName= "menu-component  .mri-menu #menus #main-menu #menu-level-one div[menu-code] .menu_text";


        public MenuName(Page page) {
            this.page = page;
        }
        public void ClickMenuIcon() {
            Locator element1=page.locator("menu-component div.mri-menu div.main-menu-arrow-icon");
            element1.waitFor(new Locator.WaitForOptions().setTimeout(0));
             page.click(MenuIcon);
        }
       public List<String> GetMenuItems() {
        return page.locator(MenuName)  // example: ".menu-item" or "ul > li"
                .allInnerTexts(); // This directly returns a List<String>
       }
}

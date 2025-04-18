package com.mri.pages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;

import java.util.ArrayList;
import java.util.List;

public class MenuName {
        private final Page page;
        private final String menuIcon= "menu-component div.mri-menu div.main-menu-arrow-icon";
        private final String menuName= "menu-component  .mri-menu #menus #main-menu #menu-level-one div[menu-code] .menu_text";

        public MenuName(Page page) {
            this.page = page;
        }
        public void clickMenuIcon() {
             page.click(menuIcon);
        }
      public List<String> getMenuItems() {
        return page.locator(menuName)  // example: ".menu-item" or "ul > li"
                .allInnerTexts(); // This directly returns a List<String>
       }

}

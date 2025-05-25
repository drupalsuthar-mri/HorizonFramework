package com.mri.pages;

import com.microsoft.playwright.Page;

public class All_Menu {
    private final Page page;
    private final String MainMenu = ".hzn-body-container #hzn-body menu-component #menus #main-menu #menu-level-one ";

    public All_Menu(Page page) {
        this.page = page;
    }
    public  void ClickMainMenu(){
        int count1=page.locator(MainMenu).count();

    }
}

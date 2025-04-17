package com.mri.util;

import com.microsoft.playwright.Page;
import com.mri.pages.LoginPage;

import java.util.Properties;

public class TestContext {
    private Page page;
    private Properties prop;
    private LoginPage loginPage;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Properties getProp() {
        return prop;
    }

    public void setProp(Properties prop) {
        this.prop = prop;
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(LoginPage loginPage) {
        this.loginPage = loginPage;
    }
}

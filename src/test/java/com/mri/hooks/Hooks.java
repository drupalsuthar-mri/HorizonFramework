package com.mri.hooks;

import com.microsoft.playwright.Page;
import com.mri.factory.PlaywrightFactory;
import com.mri.pages.SigninPage;
import com.mri.util.ConfigReader;
import com.mri.util.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.util.Properties;

public class Hooks {
    private final TestContext context;
    private PlaywrightFactory pf;
    private ConfigReader configReader;
    private Page page;
    private Properties prop;

    public static SigninPage signinPage;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before
    public void setup() {
        pf = new PlaywrightFactory();
        configReader = new ConfigReader();
        prop = configReader.initConfig();
        context.setProp(prop);
        page = pf.initBrowser(prop);
        context.setPage(page);
        context.setSigninPage(new SigninPage(page));
    }

    @After
    public void tearDown() {
        page.context().browser().close();
    }
}
package com.mri.hooks;

import com.microsoft.playwright.Page;
import com.mri.factory.PlaywrightFactory;
import com.mri.pages.SigninPage;
import com.mri.util.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.io.IOException;
import java.util.Properties;

public class Hooks {
    private final TestContext context;
    private Page page;

    public Hooks(TestContext context) {
        this.context = context;
    }


    @Before
    public void setup() throws IOException {
        PlaywrightFactory pf = new PlaywrightFactory();
        ConfigReader configReader = new ConfigReader();
        Properties prop = configReader.initConfig();
        context.setProp(prop);
        page = pf.initBrowser(prop);
        context.setPage(page);
        context.setSigninPage(new SigninPage(page));

    }
//    @After
//    public void tearDown(){
//        page.context().browser().close();
//    }
}
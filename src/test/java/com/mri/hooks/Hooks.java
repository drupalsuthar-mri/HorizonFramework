package com.mri.hooks;

import com.microsoft.playwright.Page;
import com.mri.factory.PlaywrightFactory;
import com.mri.pages.*;
import com.mri.util.ConfigReader;
import com.mri.util.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.testng.annotations.Test;

import java.util.Properties;

public class Hooks {

    private final TestContext context;
    private PlaywrightFactory pf;
    private ConfigReader cr;
    private Properties prop;
    private Page page;
    public static LoginPage loginPage;
    public static MenuName menuName;
    public static Update update;
    public static AddProperty addProperty;
    public static AddUnit addUnit;


    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before
    public void setUp() {
         pf = new PlaywrightFactory();
         cr = new ConfigReader();
         prop = cr.initConfig();
        context.setProp(prop);
        page = pf.initBrowser(prop);
        context.setPage(page);
        context.setLoginPage(new LoginPage(page));
    }
    @Test
    public void MainMenu() {
        context.setMenuName(new MenuName(page));
    }

    @Test
    public void update() {
        context.setUpdate(new Update(page));
    }

    @Test
    public void addProperty() {
        context.setAddProperty(new AddProperty(page));
    }
    @Test
    public void addUnit() {
        context.setAddUnit(new AddUnit(page));
    }
    @Test
    public void accounting() {
        context.setAccounting(new Accounting(page));
    }

    @After
    public void tearDown() {
        if (context.getPage() != null) {
            context.getPage().close();
        }
    }
}

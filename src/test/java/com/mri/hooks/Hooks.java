package com.mri.hooks;

import com.microsoft.playwright.Page;
import com.mri.factory.PlaywrightFactory;
import com.mri.pages.*;
import com.mri.util.ConfigReader;
import com.mri.util.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;



import java.io.IOException;
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
        public void setUp(Scenario scenario) throws IOException {
            pf = new PlaywrightFactory();
            cr = new ConfigReader();
            prop = cr.initConfig();
            context.setProp(prop);
            page = pf.initBrowser(prop);
            context.setPage(page);
            context.setLoginPage(new LoginPage(page));
        }

    public void MainMenu() {
        context.setMenuName(new MenuName(page));
    }

    public void update() {
        context.setUpdate(new Update(page));
    }

    public void addProperty() {
        context.setAddProperty(new AddProperty(page));
    }

    public void addUnit() {
        context.setAddUnit(new AddUnit(page));
    }

    public void accounting() {
        context.setAccounting(new Accounting(page));
    }

    public void allMenu() {
        context.setProcessMenu(new Process_Menu(page));
    }
//    @After
//    public void tearDown(Scenario scenario)  {
//        page.close();
//
//    }
    @After
    public void tearDown(){
        page.context().browser().close();
    }
}

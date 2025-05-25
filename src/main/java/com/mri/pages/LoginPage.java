package com.mri.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage {
    private final Page page;
    private final String oktaSignInBtn = "button#urn\\:mri\\:auth\\:proxy\\:link\\:redirect";
    private final String signInEmail = "input#okta-signin-username";
    private final String signInPassword = "input#okta-signin-password";
    private final String signInBtn = "input#okta-signin-submit";

    public LoginPage(Page page) {
        this.page = page;
    }
    public void clickOktaSignInButton() {
        page.click(oktaSignInBtn);
    }
    public void LoginEmail(String email) {
        page.fill(signInEmail, email);
    }
    public void LoginPassword(String password) {
        page.fill(signInPassword, password);
    }
    public void LoginButton() {
        page.click(signInBtn);
    }

    public void DoLogin(String email, String password) {
        Locator element1=page.locator("//label[normalize-space(text())='Sign in']");
        element1.waitFor(new Locator.WaitForOptions().setTimeout(0));
        String text = element1.innerText();
        System.out.println("Text: " + text);

        clickOktaSignInButton();
        LoginEmail(email);
        LoginPassword(password);
        LoginButton();
    }

    public MenuName login() {
        return new MenuName(page);
    }
    public Update update() {
        return new Update(page);
    }
    public AddProperty addProperty() {
        return new AddProperty(page);
    }
    public AddUnit addUnit() {
        return new AddUnit(page);
    }
    public Accounting accounting() {
        return new Accounting(page);
    }
    public Process_Menu ProcessMenu() {
        return new Process_Menu(page);
    }
    public All_menus allMenu() {
        return new All_menus(page);
    }
}
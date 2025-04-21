package com.mri.pages;

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

    public void dologin(String email, String password) {
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

}
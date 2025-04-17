package com.mri.pages;

import com.microsoft.playwright.Page;

public class SigninPage {
    private final Page page;
    private final String oktaSignInBtn = "button#urn\\:mri\\:auth\\:proxy\\:link\\:redirect";
    private final String signInEmail = "input#okta-signin-username";
    private final String signInPassword = "input#okta-signin-password";
    private final String rememberMeBtn = "//label[normalize-space()='Remember me']";
    private final String signInBtn = "input#okta-signin-submit";
    private final String menuArrowIcon = "div.mri-menu div.main-menu-arrow-icon";

    public SigninPage(Page page) {
        this.page = page;
    }

    public void ClickOktaSignInBtn(){
        page.click(oktaSignInBtn);
    }

    public String getSignInPageTitle() {
        return page.title();
    }

    public void enterEmailInEmailField(String email) {
        page.fill(signInEmail, email);
    }

    public void enterPasswordInPasswordField(String password) {
        page.fill(signInPassword, password);
    }

    public void clickRememberMeBtn() {
        page.click(rememberMeBtn);
    }

    public void clickSignInButton(){
        page.click(signInBtn);
    }

    public HomePage navigateToHomePage(){
        page.click(menuArrowIcon);
        boolean isMenuArrowVisible = page.isVisible(menuArrowIcon);
        if(isMenuArrowVisible){
            System.out.println("Sign in is successful");
        } else {
            System.out.println("Sign in failed");
        }
        return new HomePage(page);
    }
}
package com.mri.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    private final Page page;
    private final String oktaSignInBtn = "button#urn:mri:auth:proxy:link:redirect";
    private final String signInEmail = "input#okta-signin-username";
    private final String signInPassword = "input#okta-signin-password";
    private final String rememberMeBtn = "//label[normalize-space()='Remember me']";
    private final String signInBtn = "input#okta-signin-submit";


    public LoginPage(Page page) {
        this.page = page;
    }



}
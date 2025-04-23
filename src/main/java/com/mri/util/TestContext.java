package com.mri.util;

import com.microsoft.playwright.Page;
import com.mri.pages.HomePage;
import com.mri.pages.PropertyListPage;
import com.mri.pages.SigninPage;
import lombok.Getter;
import lombok.Setter;

import java.util.Properties;

@Setter
@Getter
public class TestContext {
    private Page page;

    private Properties prop;

    private SigninPage signinPage;

    private HomePage homePage;

    private PropertyListPage propertyListPage;

    public TestContext(){}
}
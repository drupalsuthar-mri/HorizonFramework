package com.mri.util;

import com.microsoft.playwright.Page;
import com.mri.pages.SigninPage;
import lombok.Getter;
import lombok.Setter;

import java.util.Properties;

@Setter
@Getter
public class TestContext {
    private Page page;

    private SigninPage signinPage;

    private Properties prop;

    public TestContext(){}
}
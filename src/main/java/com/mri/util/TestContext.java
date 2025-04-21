package com.mri.util;

import com.microsoft.playwright.Page;
import com.mri.pages.LoginPage;
import com.mri.pages.MenuName;
import com.mri.pages.Update;
import lombok.Getter;
import lombok.Setter;

import java.util.Properties;

@Getter
@Setter
public class TestContext {
    private Page page;
    private Properties prop;
    private LoginPage loginPage;
    private MenuName menuName;
    private Update  update;
}
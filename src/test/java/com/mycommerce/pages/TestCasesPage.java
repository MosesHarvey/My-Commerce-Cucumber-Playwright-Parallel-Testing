package com.mycommerce.pages;

import com.microsoft.playwright.Page;
import com.mycommerce.utilities.PlaywrightManager;

public class TestCasesPage {
    private Page page;

    public TestCasesPage(Page page) {
        this.page = PlaywrightManager.getPage();
    }
}

package com.mycommerce.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.mycommerce.utilities.PlaywrightManager;

public class CheckoutModal {
    private Page page;
    private final Locator registerAndLoginBtn;

    public CheckoutModal() {
        this.page = PlaywrightManager.getPage();
        this.registerAndLoginBtn = page.locator("a[href='/login'] u");
    }

   public void clickRegisterAndLoginBtn() {
        registerAndLoginBtn.click();
    }
}

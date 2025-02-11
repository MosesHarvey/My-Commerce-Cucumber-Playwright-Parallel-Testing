package com.mycommerce.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckoutModal {
    private final Page page;
    private final Locator registerAndLoginBtn;

    public CheckoutModal(Page page) {
        this.page = page;
        this.registerAndLoginBtn = page.locator("a[href='/login'] u");
    }

   public void clickRegisterAndLoginBtn() {
        registerAndLoginBtn.click();
    }
}

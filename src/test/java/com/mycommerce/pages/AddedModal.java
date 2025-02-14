package com.mycommerce.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.mycommerce.utilities.PlaywrightManager;

public class AddedModal {
    private  Page page;
     // ============== Locators ==================

    private final Locator viewCartOnAddedModal;
    private final Locator continueShoppingBtnOnAddedModal;

    public AddedModal() {
        this.page = PlaywrightManager.getPage();
        this.viewCartOnAddedModal = page.locator("a[href='/view_cart'] u");
        this.continueShoppingBtnOnAddedModal = page.locator("#cartModal .modal-content").getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Continue Shopping"));
    }

    public void clickViewCartOnAddedModal() {
        viewCartOnAddedModal.click();
    }
    public void clickContinueShoppingBtnOnAddedModal() {
        continueShoppingBtnOnAddedModal.click();
    }



}

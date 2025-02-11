package com.mycommerce.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class AddedModal {
    private final Page page;
     // ============== Locators ==================

    private final Locator viewCartOnAddedModal;
    private final Locator continueShoppingBtnOnAddedModal;

    public AddedModal(Page page) {
        this.page = page;
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

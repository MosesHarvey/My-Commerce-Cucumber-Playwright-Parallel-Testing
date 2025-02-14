package com.mycommerce.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.mycommerce.utilities.PlaywrightManager;
import lombok.Getter;


@Getter
public class HeaderComponent {

    private Page page;
    private final Locator homeTab;
    private final Locator productsTab;
    private final Locator cartTab;
    private final Locator loginTab;
    private final Locator testCasesTab;
    private final Locator contactTab;
    private final Locator deleteAccountTab;
    private final Locator loggedInAsUserNameTab;


    public HeaderComponent() {
        this.page = PlaywrightManager.getPage();
        this.homeTab = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Home"));
        this.productsTab = page.locator("//a[@href='/products']");
        this.cartTab = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Cart"));
        this.loginTab = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Signup / Login"));
        this.contactTab = page.locator("a[href='/contact_us']");
        this.testCasesTab = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("\uF03A Test Cases"));
        this.deleteAccountTab = page.locator("a[href='/delete_account']");
        this.loggedInAsUserNameTab = page.locator("#header").getByText("Logged in as").locator("b");
    }

    public void navigateToHomePage(){
          homeTab.click();


    }

    public void navigateToProductPage(){
        productsTab.click();

    }
    public void navigateToLoginPage(){
        loginTab.click();

    }

    public void navigateToTestCasesPage(){
        testCasesTab.click();

    }
    public void navigateToContactUsPage(){
        contactTab.click();


    }

    public void clickDeleteAccountTab (){
        deleteAccountTab.click();

    }


    public void navigateToCartPage() {
        cartTab.click();

    }



    public boolean isLogoVisible(){
        Locator logo = page.getByAltText("Website for automation practice");
        return logo.isVisible();
    }


    public boolean isTheTabWithGivenNameVisible(String tabName) {
        Locator tab = page.locator("#header").getByText(tabName);
        return tab.isVisible();
    }
}

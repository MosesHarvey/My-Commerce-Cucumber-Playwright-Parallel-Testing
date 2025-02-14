package com.mycommerce.pages;



import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.mycommerce.utilities.PlaywrightManager;

import static com.microsoft.playwright.options.WaitForSelectorState.VISIBLE;


public class BasePage {
    private  Page page;
    private final Locator consentBtn;
    private final Locator scrollUp;

    public BasePage() {
        this.page = PlaywrightManager.getPage();
        //Locators
        this.consentBtn = page.locator("button:has-text('Consent')");
        this.scrollUp = page.locator("#scrollUp");

    }

    public void clickConsentBtn() {
        consentBtn.waitFor(new Locator.WaitForOptions().setTimeout(5000));
        consentBtn.click();
    }

       public boolean isLocatorWithTextVisible(String visibleText){
        return page.locator(":has-text(\"" + visibleText + "\")").first().isVisible();

    }

    public void navigateToGivenUrl( String url){
        this.page.navigate(url);

    }

  public String getCurrentUrl(){
        return this.page.url();
  }


    public boolean isElementWithTextVisible(String visibleText) {
        try {
            // Locate the text and wait for its visibility
            Locator textLocator = page.getByText(visibleText).first();
            textLocator.waitFor(new Locator.WaitForOptions().setState(VISIBLE));

            // Check visibility and return true if visible
            if (textLocator.isVisible()) {
                System.out.println("Element with the given text is visible.");
                return true;
            }
        } catch (Exception e) {
            // Handle exceptions (e.g., timeout, locator not found, etc.)
            System.out.println("Element with the given text is not visible: " + e.getMessage());
        }
        // Default return if element is not visible or an exception occurs
        return false;
    }


    public String getPageTitle(){
        return this.page.title();
    }


    public void acceptAlert() {

        this.page.onDialog(dialog -> {
            System.out.println("Dialog message: " + dialog.message());
            dialog.accept(); // Click the OK button
        });
    }
    public void scrollToBottom(){
        page.evaluate("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scrollToTop() {
        scrollUp.click();

    }

    public void scrollToTopWithoutArrow() {
    page.evaluate("window.scrollTo(0, 0)");
    }

    public boolean isTheHeadingWithGivenTextVisible(String headingText) {
        Locator heading = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName(headingText));
        return heading.isVisible();

    }


}

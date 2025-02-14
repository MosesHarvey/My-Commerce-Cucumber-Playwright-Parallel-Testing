package com.mycommerce.steps;

import com.microsoft.playwright.Download;
import com.mycommerce.pages.CheckoutPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutStep {

    private CheckoutPage checkoutPage = new CheckoutPage();
    private Download download = null;

    @When("the user clicks Download Invoice button")
    public void the_user_clicks_download_invoice_button() {
        download = checkoutPage.clickDownload();
    }

    @Then("the user verifies that invoice is downloaded successfully")
    public void the_user_verifies_that_invoice_is_downloaded_successfully() {
       assertTrue(checkoutPage.isFileDownloaded(download));
    }



}

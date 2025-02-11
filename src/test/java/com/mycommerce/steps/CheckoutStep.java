package com.mycommerce.steps;

import com.microsoft.playwright.Download;
import com.microsoft.playwright.Page;
import com.mycommerce.pages.CheckoutPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.*;

public class CheckoutStep {
    private final Page page;
    private CheckoutPage checkoutPage;
    private Download download;

    public CheckoutStep() {
        this.page = Hooks.getPage();
        this.checkoutPage = new CheckoutPage(page);
        this.download = null;

    }


    @When("the user clicks Download Invoice button")
    public void the_user_clicks_download_invoice_button() {
        download = checkoutPage.clickDownload();
    }

    @Then("the user verifies that invoice is downloaded successfully")
    public void the_user_verifies_that_invoice_is_downloaded_successfully() {
       assertTrue(checkoutPage.isFileDownloaded(download));
    }


    // ===================

    @When("the user fills all details in Signup and creates an account")
    public void the_user_fills_all_details_in_signup_and_creates_an_account() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the user verifies {string}")
    public void the_user_verifies(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the user verifies that the cart page is displayed")
    public void the_user_verifies_that_the_cart_page_is_displayed() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the user verifies that the delivery address is the same as the address filled at the time of registration of account")
    public void the_user_verifies_that_the_delivery_address_is_the_same_as_the_address_filled_at_the_time_of_registration_of_account() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the user verifies that the billing address is the same as the address filled at the time of registration of account")
    public void the_user_verifies_that_the_billing_address_is_the_same_as_the_address_filled_at_the_time_of_registration_of_account() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("the user clicks on {string} tab")
    public void the_user_clicks_on_tab(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}

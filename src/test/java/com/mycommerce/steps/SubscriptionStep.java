package com.mycommerce.steps;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Page;
import com.mycommerce.pages.FooterComponent;
import com.mycommerce.pages.HeaderComponent;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static  org.junit.jupiter.api.Assertions.*;
public class SubscriptionStep {
    private final Page page;
    private HeaderComponent headerComponent;
    private FooterComponent footerComponent;

    public SubscriptionStep() {
        this.page = Hooks.getPage();
        headerComponent = new HeaderComponent(page);  // Initialize Header Component with Page object
        footerComponent = new FooterComponent(page);  // Initialize Footer Component with Page object
    }

    // ================Subscriptions to Newsletter  On Home Page ================
    @When("the user scrolls down to the footer")
    public void the_user_scrolls_down_to_the_footer() {
        System.out.println("Playwright Scrolls Down Automatically");
    }

    @When("the user enters an email address in the input field and clicks the arrow button")
    public void the_user_enters_an_email_address_in_the_input_field_and_clicks_the_arrow_button() {
      Faker faker = new Faker();
     footerComponent.subscribeToNewsletter(faker.internet().emailAddress());
    }

    @Then("the success message {string} should be visible on subscribe page")
    public void the_success_message_should_be_visible_on_subscribe_page(String successMessage) {
       assertTrue( footerComponent.isSubscribeSuccessMessageVisible());
       assertEquals(successMessage, footerComponent.subscribeSuccessText());
    }

    // ================ Unsubscribe from Newsletter  On Cart Page ================
    @When("the user clicks on Cart tab")
    public void the_user_clicks_on_cart_tab() {
        headerComponent.navigateToCartPage();
    }
}

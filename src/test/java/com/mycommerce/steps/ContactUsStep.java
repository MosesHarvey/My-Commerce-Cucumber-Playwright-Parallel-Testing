package com.mycommerce.steps;

import com.microsoft.playwright.Page;
import com.mycommerce.appdata.AppConstant;
import com.mycommerce.pages.BasePage;
import com.mycommerce.pages.ContactUsPage;
import com.mycommerce.pages.HeaderComponent;
import com.mycommerce.utilities.ConfigReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;


public class ContactUsStep {
    private final Page page;
    private BasePage basePage;
    private HeaderComponent headerComponent;
    private  ContactUsPage contactUsPage;

    public ContactUsStep() {
         page = Hooks.getPage();
         basePage = new BasePage(page);
         headerComponent = new HeaderComponent(page);
        this.contactUsPage = new ContactUsPage(page);
    }

    // ============= User fills and submits a contact us form successfully =================
    @When("the user clicks on Contact us tab")
    public void the_user_clicks_on_contact_us_tab() {
       headerComponent.navigateToContactUsPage();
    }



    @When("the user enters their name, email, subject, and message")
    public void the_user_enters_their_name_email_subject_and_message() {
        String name = ConfigReader.get("userName");
        String email = ConfigReader.get("email");
        String subject = "issue with login";
        String message = "I am facing issues with the login";


       contactUsPage.fillContactForm(name, email, subject, message);
    }

    @When("the user uploads a file")
    public void the_user_uploads_a_file() {

        contactUsPage.uploadFile(AppConstant.FILE_PATH);

    }

    @When("the user clicks the Submit button")
    public void the_user_clicks_the_submit_button() {
      contactUsPage.submitForm();
        System.out.println("Submit button clicked");
    }

    @When("the user clicks the OK button")
    public void the_user_clicks_the_ok_button() {
        basePage.acceptAlert(page);
        System.out.println("OK button clicked");
    }

    @Then("the success message {string} should be visible on contact us page")
    public void the_success_message_should_be_visible_on_contact_us_page(String alertText) {

       assertTrue(contactUsPage.isSuccessMessageVisible(alertText),"Success message is not visible!");

    }

    @When("the user clicks the Home button")
    public void the_user_clicks_the_home_button() {
       contactUsPage.clickHomeBtn();
    }


}

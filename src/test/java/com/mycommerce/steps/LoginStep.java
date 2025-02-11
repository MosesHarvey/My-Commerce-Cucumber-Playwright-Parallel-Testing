package com.mycommerce.steps;


import com.microsoft.playwright.Page;
import com.mycommerce.pages.HeaderComponent;
import com.mycommerce.pages.LoginSignUpPage;
import com.mycommerce.utilities.ConfigReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.*;



public class LoginStep {
    private final Page page;
    private  LoginSignUpPage loginPage;

    public LoginStep() {
        page = Hooks.getPage();
        HeaderComponent headerComponent = new HeaderComponent(page);
        this.loginPage = new LoginSignUpPage(page);
    }


// ===============login with valid credentials================



    @When("the user enters correct email address")
    public void the_user_enters_correct_email_address() {

       loginPage.fillEmailField(ConfigReader.get("email"));
    }

    @When("the user enters correct password")
    public void the_user_enters_correct_password() {
       loginPage.fillPasswordField(ConfigReader.get("password"));
    }

    @When("the user clicks login button")
    public void the_user_clicks_login_button() {
       loginPage.clickLoginButton();
    }
    @Then("Logged in as {string} should be visible")
    public void Logged_in_as_should_be_visible(String username) {
        assertEquals(ConfigReader.get("userName"),loginPage.getUserName());

    }

// ================ login with incorrect credentials =============

    @When("the user enters incorrect email address {string} or incorrect password {string}")
    public void the_user_enters_incorrect_email_address_or_incorrect_password(String email, String password) {
        loginPage.fillEmailField(email);
        loginPage.fillPasswordField(password);
    }

    @Then("the user should see {string} error message")
    public void the_user_should_see_error_message(String errorMessage) {
        assertTrue(loginPage.isErrorMessageVisible());
        assertEquals(errorMessage, loginPage.getErrorMessageText());

    }

// =============================== logout scenario ============================

    @When("the user logged in with valid credentials")
    public void the_user_logged_in_with_valid_credentials() {
    loginPage.login(ConfigReader.get("email"), ConfigReader.get("password"));
    }

    @When("the user clicks the Logout button")
    public void the_user_clicks_the_logout_button() {
        loginPage.clickLogoutButton();
    }







}

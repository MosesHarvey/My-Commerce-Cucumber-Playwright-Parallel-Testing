package com.mycommerce.steps;

import com.github.javafaker.Faker;
import com.mycommerce.appdata.AccountInfo;
import com.mycommerce.appdata.AddressInfo;
import com.mycommerce.appdata.AppConstant;
import com.mycommerce.pages.BasePage;
import com.mycommerce.pages.HeaderComponent;
import com.mycommerce.pages.LoginSignUpPage;
import com.mycommerce.pages.RegisterPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterSteps {

    private  LoginSignUpPage loginPage = new LoginSignUpPage();
    private  RegisterPage registerPage = new RegisterPage();
    private HeaderComponent headerComponent = new HeaderComponent();
    private BasePage basePage = new BasePage();
    Faker faker = new Faker();

    // ==========================Register Scenario =========================
    @When("the user enters name and email address")
    public void the_user_enters_name_and_email_address() {

        loginPage.fillRegisterNameField(faker.name().firstName());
        loginPage.fillRegisterEmailField(faker.internet().emailAddress());
    }



    @When("the user clicks Signup button")
    public void the_user_clicks_signup_button() {
        loginPage.clickSignupButton();
    }

    @When("the user clicks on SignupLogin tab")
    public void the_user_clicks_on_signup_login_tab() {
       headerComponent.navigateToLoginPage();
    }
    @Then("the user should see the heading {string}")
    public void the_user_should_see_the_heading(String heading) {
        assertTrue(basePage.isTheHeadingWithGivenTextVisible(heading), "The heading {string} is not visible");
    }

    @When("the user clicks Create Account button")
    public void the_user_clicks_create_account_button() {
        registerPage.clickCreateAccountButton();

    }

    @When("the user clicks Continue button")
    public void the_user_clicks_continue_button() {
        registerPage.clickContinueButton();

    }

    @Then("Logged in as Mr. with full Name should be visible")
    public void logged_in_as_mr_with_full_name_should_be_visible() {

        assertEquals(AccountInfo.name,loginPage.getUserName());
    }

    @When("the user clicks Delete Account button")
    public void the_user_clicks_delete_account_button() {
     headerComponent.clickDeleteAccountTab();
    }

    @Then("the user verifies that {string} is visible")
    public void the_user_verifies_that_is_visible(String registerHeading) {
        assertEquals(registerHeading, registerPage.getSignUpHeading(registerHeading) );

    }
    @When("the user fills details: Title, Name, Email, Password, Date of birth")
    public void the_user_fills_details_title_name_email_password_date_of_birth() {
        // generating account information
        String fullName = faker.name().fullName();
        String password = faker.internet().password();
        // saving account information before filling it in account info form
        AccountInfo.name =fullName;
        AccountInfo.password = password;
        registerPage.fillAccountInfo(fullName, password);
    }
    @When("the user selects checkbox {string}")
    public void the_user_selects_checkbox(String string) {
        System.out.println("User is selecting checkbox "+string );
    }
    @When("the user fills details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number")
    public void the_user_fills_details_first_name_last_name_company_address_address2_country_state_city_zipcode_mobile_number() {
        // generating address information
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String address = faker.address().streetAddress();
        String state = faker.address().state();
        String city = faker.address().city();
        String zipCode = faker.address().zipCode();
        String phoneNumber = faker.phoneNumber().cellPhone();
        // saving address information before filling it in address info form
        AddressInfo.firstName = firstName;
        AddressInfo.lastName = lastName;
        AddressInfo.address = address;
        AddressInfo.state = state;
        AddressInfo.city = city;
        AddressInfo.zipCode = zipCode;
        AddressInfo.mobileNumber = phoneNumber;
        registerPage.fillAddressInfo(firstName, lastName, address, city, state, zipCode, phoneNumber);
    }

    // ================Register with Already Registered User Email Address =================
    @When("the user enters a name and an already registered email address")
    public void the_user_enters_a_name_and_an_already_registered_email_address() {
        loginPage.fillRegisterNameField(faker.name().firstName());
        loginPage.fillRegisterEmailField(AppConstant.USER_EMAIL);
    }

    @Then("the user verifies that the error {string} is visible")
    public void the_user_verifies_that_the_error_is_visible(String errorMessage) {
        assertTrue(registerPage.isErrorMessageVisible(errorMessage));
    }



}

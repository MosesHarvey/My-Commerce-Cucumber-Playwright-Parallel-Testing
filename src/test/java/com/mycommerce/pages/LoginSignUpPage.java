package com.mycommerce.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.mycommerce.utilities.PlaywrightManager;
import lombok.Getter;

@Getter
public class LoginSignUpPage {


    private Page page;
    // Locators for login

    private final Locator userEmail;
    private final Locator passwordField;
    private final Locator loginBtn;
    private final Locator logoutLink;
    private final Locator userName;
    private final Locator loginHeader;
    private final Locator errorMessage;

// Locators for signup
    private final Locator signupHeading;
    private final Locator signupName;
    private final Locator signupEmail;
    private final Locator signupBtn;


    public LoginSignUpPage() {


        this.page = PlaywrightManager.getPage();
        //locators for login

        this.userEmail = page.locator("input[data-qa='login-email']");
        this.passwordField = page.locator("input[name='password']");
        this.loginBtn = page.locator("button[data-qa='login-button']");
        this.logoutLink = page.locator("a[href='/logout']");
        this.userName = page.locator("ul[class='nav navbar-nav'] li a b");
        this.loginHeader = page.locator("div[class='login-form'] h2");
        this.errorMessage = page.locator("//p[normalize-space()='Your email or password is incorrect!']");

        // Locators for signup
        this.signupHeading =  page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("New User Signup!"));
        this.signupName =  page.getByPlaceholder("Name");
        this.signupEmail =  page.locator("form").filter(new Locator.FilterOptions().setHasText("Signup")).getByPlaceholder("Email Address");
        this.signupBtn = page.locator(".signup-form button[type=submit]");
    }

    public void login(String email, String password) {
        userEmail.fill(email);
        passwordField.fill(password);
        loginBtn.click();
        logoutLink.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void fillEmailField(String email){
        userEmail.fill(email);
    }

    public void fillPasswordField(String password){
        passwordField.fill(password);
    }

    public void clickLoginButton(){
        loginBtn.click();
    }
    public void clickLogoutButton(){logoutLink.click();}

    public String getUserName(){
        userName.waitFor();
    return userName.textContent();
    }




    public boolean isErrorMessageVisible(){
        return errorMessage.isVisible();
    }

    public String getErrorMessageText(){
        return errorMessage.innerText();
    }

    public void fillRegisterEmailField(String email){
        signupEmail.fill(email);

    }

    public void fillRegisterNameField(String name){
        signupName.fill(name);
    }
    public void clickSignupButton(){
        signupBtn.click();
    }

}
